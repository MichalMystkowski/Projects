import streamlit as st
import pandas as pd
import plotly.express as px
import statsmodels.api as sm
import numpy as np
import plotly.io as pio
import seaborn as sns
import matplotlib.pyplot as plt

# Wczytanie danych
df = pd.read_csv('clean_data.csv')

# Definiowanie zakresu cen na podstawie wczytanych danych
min_price, max_price = int(df['price'].min()), int(df['price'].max())

# Funkcja do eliminacji wstecznej
def backward_elimination(data, target, significance_level=0.05):
    features = data.columns.tolist()
    while len(features) > 0:
        features_with_constant = sm.add_constant(data[features])
        p_values = sm.OLS(target, features_with_constant).fit().pvalues[1:]
        max_p_value = p_values.max()
        if max_p_value > significance_level:
            feature_with_max_p_value = p_values.idxmax()
            features.remove(feature_with_max_p_value)
        else:
            break
    return features

# Sidebar - filtrowanie
st.sidebar.header('Opcje filtracji')
price_range = st.sidebar.slider('Zakres cen:', min_value=min_price, max_value=max_price, value=(min_price, max_price))

# Konfiguracji wykresów
st.sidebar.header('Opcje wykresów')
bin_size = st.sidebar.slider('Liczba binów histogramu:', min_value=10, max_value=100, value=20)

# Wybór motywu wykresów Plotly
st.sidebar.header("Motyw wykresów Plotly")
plotly_theme = st.sidebar.selectbox(
    "Wybierz motyw wykresów:",
    options=pio.templates.keys(),
    index=list(pio.templates.keys()).index("plotly")
)
pio.templates.default = plotly_theme


st.title('Dashboard Analizy Danych Diamentów')


st.markdown("""
### Wprowadzenie
Analizujemy zbiór danych dotyczących diamentów, aby zrozumieć, jak różne cechy wpływają na ich ceny.
Używamy modelu regresji liniowej oraz różnych wizualizacji danych, aby zbadać te zależności.
""")

# Filtrowanie danych według wybranego zakresu cenowego
df_filtered = df[(df['price'] >= price_range[0]) & (df['price'] <= price_range[1])]
st.markdown(f"### Analiza dla diamentów w zakresie cenowym: ${price_range[0]} - ${price_range[1]}")

# Próbka danych
st.header('Próbka danych')
st.markdown("Poniżej znajduje się próbka danych po filtracji cenowej. Daje to wgląd w typy i zakresy wartości, które będziemy analizować.")
st.write(df_filtered.sample(min(10, len(df_filtered))))

# Histogramy dla zmiennych numerycznych
st.header('Analiza rozkładu zmiennych numerycznych')
st.markdown("Histogramy poniżej pokazują rozkłady poszczególnych zmiennych numerycznych.")
numeric_columns = df_filtered.select_dtypes(include=['float64', 'int64']).columns.tolist()
for col in numeric_columns:
    fig = px.histogram(df_filtered, x=col, nbins=bin_size, title=f'Rozkład zmiennej {col}')
    st.plotly_chart(fig)

# Wykres rozrzutu dla zależności między carat a price z uwzględnieniem cut jako koloru
st.header('Zależność między rozmiarem a ceną z uwzględnieniem jakości szlifu')
st.markdown("""
Poniższy wykres rozrzutu ilustruje, jak rozmiar diamentu (carat) wpływa na jego cenę, z różnicowaniem według jakości szlifu. 
Większe diamenty są generalnie droższe, ale jakość szlifu również ma znaczenie w określaniu ceny.
""")
fig = px.scatter(df_filtered, x='carat', y='price', color='cut', title='Zależność między rozmiarem a ceną z uwzględnieniem jakości szlifu', labels={'carat': 'Rozmiar (carat)', 'price': 'Cena'})
st.plotly_chart(fig)


# Wykresy pudełkowe dla zmiennych kategorycznych
st.header('Analiza dystrybucji cen względem zmiennych kategorycznych')
st.markdown("Wykresy pudełkowe poniżej ilustrują, jak różne cechy kategoryczne wpływają na rozkład cen diamentów. Pozwalają one na szybką identyfikację mediany, kwartyli oraz wartości odstających.")
cat_columns = df_filtered.select_dtypes(include=['category', 'object']).columns.tolist()
for col in cat_columns:
    fig = px.box(df_filtered, x=col, y='price', title=f'Rozkład cen zależny od {col}')
    st.plotly_chart(fig)

# Przygotowanie danych do modelu i eliminacja wsteczna
st.header('Modelowanie regresji liniowej')
st.markdown("""
Analizujemy wpływ różnych cech diamentów na ich cenę za pomocą modelu regresji liniowej. 
Proces eliminacji wstecznej pomaga zredukować liczbę zmiennych do tych najbardziej znaczących dla modelu.
""")
X = df_filtered.drop(['price'], axis=1)
X = pd.get_dummies(X, drop_first=True)  
X = X.select_dtypes(include=[np.number])  
y = df_filtered['price']

selected_features = backward_elimination(X, y)
X_selected = sm.add_constant(X[selected_features]) 
model = sm.OLS(y, X_selected).fit()


st.write('Podsumowanie modelu regresji liniowej:')
st.text(model.summary().as_text())

# Analiza reszt
st.header('Analiza reszt')
st.markdown("""
Analiza reszt jest kluczowa, aby zrozumieć, jak dobrze model pasuje do danych. 
Reszty powinny być rozłożone losowo wokół linii y = 0, wskazując na brak systematycznych błędów.
""")
predictions = model.predict(X_selected)
residuals = y - predictions
residuals_fig = px.scatter(x=predictions, y=residuals, trendline="ols", 
                           title='Wykres reszt',
                           labels={'x': 'Przewidywane wartości', 'y': 'Reszty'})
residuals_fig.update_layout(height=600, width=800)
st.plotly_chart(residuals_fig)

# Heatmapa
st.header('Heatmapa korelacji')
st.markdown("""
Heatmapa korelacji przedstawia, jak zmienne są ze sobą powiązane. 
Wartości bliskie 1 lub -1 wskazują na silną korelację, co może być ważne przy modelowaniu.
""")
corr_matrix = df_filtered[numeric_columns].corr()
heatmap_fig = px.imshow(corr_matrix, text_auto=True,
                        title='Heatmapa korelacji dla zmiennych numerycznych',
                        height=600, width=800)
st.plotly_chart(heatmap_fig)

# Wybór zmiennych do wykresu regresji
st.sidebar.header('Wybór zmiennych do wykresu regresji')
selected_variables = st.sidebar.multiselect('Wybierz zmienne do analizy regresji:',
                                            options=numeric_columns,
                                            default=numeric_columns[:2])

# Wykresy regresji dla wybranych zmiennych
if selected_variables:
    st.header('Wykresy regresji liniowej')
    st.markdown("""
    Poniżej przedstawiamy wykresy regresji liniowej dla wybranych zmiennych. 
    Linie trendu pomagają zobaczyć zależności między zmiennymi a ceną diamentów.
    """)
    for variable in selected_variables:
        fig = px.scatter(df_filtered, x=variable, y='price', trendline='ols',
                         title=f'Regresja liniowa: Cena vs {variable}')
        st.plotly_chart(fig)

# Wnioski
st.header('Wnioski')
st.markdown("""
Na podstawie przeprowadzonej analizy możemy wyciągnąć wnioski dotyczące wpływu różnych cech na cenę diamentów.
Największy bezpośredni wpływ na cenę diamentów ma rozmiar diamentu w karatach. 

""")

# Informacje końcowe
st.markdown("""
---

### Podsumowanie

S31213
Michał Mystkowski

""")








