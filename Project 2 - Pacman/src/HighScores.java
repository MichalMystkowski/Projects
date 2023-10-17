import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/*
Deklaracja pól klasy:

highScore1, highScore2, highScore3, highScore4, highScore5: Pola tekstowe, w których wyświetlane będą wyniki.
menu: Przycisk "Menu", który pozwala powrócić do menu głównego.
highScoreData: Obiekt przechowujący dane o wynikach.
showFrame: Flaga określająca, czy okno z wynikami powinno być widoczne.
Konstruktor HighScores(boolean showFrame): Tworzy nową instancję klasy HighScores. Jeśli showFrame ma wartość true, wywoływana jest metoda init(), która inicjalizuje okno z wynikami. Następnie wywoływana jest metoda loadData(), która wczytuje dane o wynikach.

Metoda init(): Inicjalizuje okno z wynikami. Ustawia jego rozmiar, operację zamknięcia, kolor tła, brak możliwości zmiany rozmiaru i ustawia layout na null. Komponenty są tworzone za pomocą metody createComponents(), a okno jest ustawiane jako widoczne.

Metoda createComponents(): Tworzy komponenty okna z wynikami. Tworzone są pola tekstowe dla pięciu najlepszych wyników oraz przycisk "Menu". Komponenty są ustawiane na odpowiednich pozycjach i dodawane do kontenera.

Metoda loadData(): Wczytuje dane o wynikach. Wywoływana jest metoda readHighScores() z klasy HighScoreData, która wczytuje dane z pliku "highscore". Jeśli dane są dostępne, wyniki są wyświetlane w odpowiednich polach tekstowych.

Metoda saveData(): Zapisuje domyślne dane o wynikach. Tworzone są pięć obiektów typu HighScore z przykładowymi danymi, a następnie są one dodawane do obiektu highScoreData. W końcu dane są zapisywane za pomocą metody saveHighScores() z klasy HighScoreData.

Klasa HighScoreData: Klasa wewnętrzna przechowująca dane o wynikach. Posiada pole highScores, które jest listą przechowującą obiekty HighScore.

Metoda statyczna readHighScores(): Wczytuje dane o wynikach z pliku "highscore". Jeśli plik istnieje, dane są odczytywane i sortowane malejąco według wyników. Jeśli plik nie istnieje, wyświetlany jest odpowiedni komunikat.

Metoda statyczna saveHighScores(HighScoreData highScoreData): Zapisuje dane o wynikach do pliku "highscore" za pomocą mechanizmu serializacji.

Metoda statyczna AddHighScore(String playerName, int score): Dodaje nowy wynik do listy wyników odczytanych z pliku "highscore". Nowy wynik jest tworzony na podstawie przekazanych parametrów, a następnie jest dodawany do listy. Ostatecznie lista jest zapisywana z powrotem do pliku.
 */

public class HighScores extends JFrame {

    private JTextField highScore1;
    private JTextField highScore2;
    private JTextField highScore3;
    private JTextField highScore4;
    private JTextField highScore5;
    private JButton menu;

    HighScoreData highScoreData;

    private boolean showFrame;

    public HighScores(boolean showFrame){
        this.showFrame = showFrame;
        if(showFrame)
            init(); // Inicjalizacja okna z wynikami
        loadData(); // Wczytanie danych o wynikach
    }

    public void init() {
        this.setSize(300,500); // Ustawienie rozmiaru okna
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ustawienie operacji zamknięcia
        this.setBackground(Color.darkGray); // Ustawienie koloru tła
        this.setResizable(false); // Wyłączenie możliwości zmiany rozmiaru okna
        this.setLayout(null); // Ustawienie null layoutu (brak automatycznego rozmieszczania komponentów)
        this.setLocation(1920/2-getWidth()/2,1080/2-getHeight()/2); // Wyśrodkowanie okna na ekranie
        createComponents(); // Tworzenie komponentów (np. pola tekstowego, przycisku)
        this.setVisible(true); // Ustawienie widoczności okna
    }

    public void createComponents() {
        // Tworzenie pól tekstowych dla pięciu najlepszych wyników
        highScore1 = new JTextField("");
        highScore1.setSize(200,30);
        highScore1.setLocation(getWidth()/2-highScore1.getWidth()/2, 20);
        highScore1.setEditable(false);
        add(highScore1);

        highScore2 = new JTextField("");
        highScore2.setSize(200,30);
        highScore2.setLocation(getWidth()/2-highScore2.getWidth()/2, 100);
        highScore2.setEditable(false);
        add(highScore2);

        highScore3 = new JTextField("");
        highScore3.setSize(200,30);
        highScore3.setLocation(getWidth()/2-highScore3.getWidth()/2, 180);
        highScore3.setEditable(false);
        add(highScore3);

        highScore4 = new JTextField("");
        highScore4.setSize(200,30);
        highScore4.setLocation(getWidth()/2-highScore4.getWidth()/2, 260);
        highScore4.setEditable(false);
        add(highScore4);

        highScore5 = new JTextField("");
        highScore5.setSize(200,30);
        highScore5.setLocation(getWidth()/2-highScore5.getWidth()/2, 340);
        highScore5.setEditable(false);
        add(highScore5);

        // Tworzenie przycisku "Menu"
        menu = new JButton("Menu");
        menu.setSize(80,30);
        menu.setLocation(200,410);
        menu.addActionListener(e->{
            this.dispose(); // Zamknięcie okna z wynikami
            Menu menu = new Menu(); // Stworzenie nowej instancji klasy Menu
        });
        add(menu);
    }

    public void loadData(){
        highScoreData = HighScoreData.readHighScores(); // Wczytanie danych o wynikach

        if(highScoreData == null)
        {
            saveData(); // Jeśli dane o wynikach nie istnieją, zapisz domyślne dane
        }

        if(showFrame) {
            // Wyświetlanie wyników w polach tekstowych
            highScore1.setText(highScoreData.highScores.get(0).playerName + ":" + highScoreData.highScores.get(0).score);
            highScore2.setText(highScoreData.highScores.get(1).playerName + ":" + highScoreData.highScores.get(1).score);
            highScore3.setText(highScoreData.highScores.get(2).playerName + ":" + highScoreData.highScores.get(2).score);
            highScore4.setText(highScoreData.highScores.get(3).playerName + ":" + highScoreData.highScores.get(3).score);
            highScore5.setText(highScoreData.highScores.get(4).playerName + ":" + highScoreData.highScores.get(4).score);
        }
    }

    public void saveData(){
        // Tworzenie pięciu domyślnych wyników
        HighScore h1 = new HighScore();
        h1.playerName = "Player1";
        h1.score = "1";

        HighScore h2 = new HighScore();
        h2.playerName = "Player2";
        h2.score = "1";

        HighScore h3 = new HighScore();
        h3.playerName = "Player3";
        h3.score = "1";

        HighScore h4 = new HighScore();
        h4.playerName = "Player4";
        h4.score = "1";

        HighScore h5 = new HighScore();
        h5.playerName = "Player5";
        h5.score = "1";

        if(highScoreData == null) {
            highScoreData = new HighScoreData();
            highScoreData.highScores.add(h1);
            highScoreData.highScores.add(h2);
            highScoreData.highScores.add(h3);
            highScoreData.highScores.add(h4);
            highScoreData.highScores.add(h5);
        }
        HighScoreData.saveHighScores(highScoreData); // Zapisanie danych o wynikach
    }

    public class HighScoreData implements Serializable {

        public ArrayList<HighScore> highScores = new ArrayList<>();

        public static HighScoreData readHighScores(){

            try {
                FileInputStream fis = new FileInputStream("highscore");
                ObjectInputStream ois = new ObjectInputStream(fis);
                HighScoreData ret = (HighScoreData)ois.readObject();
                ret.highScores.sort((o1, o2) -> {
                    if(Integer.parseInt(o1.score) > Integer.parseInt(o2.score)) return -1;
                    return 1;
                });
                return ret;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Nie znaleziono pliku z najwyższymi wynikami punktów!");
            }

            return null;
        }

        public static void saveHighScores(HighScoreData highScoreData){
            try{
                FileOutputStream fos = new FileOutputStream("highscore");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(highScoreData);
                oos.close();
                fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void AddHighScore(String playerName, int score){
            HighScoreData hsd = HighScoreData.readHighScores();
            hsd.highScores.add(new HighScore(playerName, Integer.toString(score)));
            HighScoreData.saveHighScores(hsd);
        }
    }
}