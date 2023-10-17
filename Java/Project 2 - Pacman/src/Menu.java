import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
Klasa Menu reprezentuje okno menu gry. Poniżej znajduje się wyjaśnienie działania poszczególnych elementów klasy:

Klasa Menu dziedziczy po klasie JFrame, co oznacza, że tworzy ona okno graficzne.
Konstruktor Menu() inicjalizuje menu gry poprzez wywołanie metody init().
Metoda init() ustawia parametry okna, takie jak rozmiar, operację zamknięcia, układ (null layout) oraz nazwę okna.
Tworzony jest obiekt menu, który reprezentuje bieżące menu. Umożliwia to odwołanie do niego wewnątrz klas wewnętrznych ActionListener.
Tworzony jest przycisk start za pomocą klasy JButton. Określane są jego właściwości, takie jak rozmiar, położenie, przezroczystość tła oraz brak wyświetlania obramowania. Dodawany jest również ActionListener, który reaguje na kliknięcie przycisku. Po kliknięciu przycisku, okno menu zostaje zamknięte, a wątek zostaje uruchomiony, aby utworzyć i wyświetlić okno gry (Window).
Przycisk highScore jest tworzony w podobny sposób jak przycisk start. Po kliknięciu przycisku, okno menu zostaje zamknięte, a wątek zostaje uruchomiony, aby utworzyć i wyświetlić okno z wysokimi wynikami (HighScores).
Przycisk exit jest tworzony w podobny sposób jak poprzednie przyciski. Po kliknięciu przycisku, okno menu zostaje zamknięte.
Na koniec okno menu jest ustawiane jako widoczne.
Klasa Menu służy do tworzenia prostego interfejsu menu gry, w którym użytkownik może wybrać opcje takie jak rozpoczęcie gry, wyświetlenie wyników lub wyjście z gry.
 */
public class Menu extends JFrame {

    public Menu(){
        init(); // Inicjalizacja menu
    }

    public void init(){
        this.setSize(240,320); // Ustawienie rozmiaru okna
        this.setDefaultCloseOperation(3); // Ustawienie operacji zamknięcia
        this.setResizable(false); // Wyłączenie możliwości zmiany rozmiaru okna
        this.setLayout(null); // Ustawienie null layoutu (brak automatycznego rozmieszczania komponentów)
        this.setName("Menu"); // Ustawienie nazwy okna

        Menu menu = this; // Tworzenie referencji do bieżącego menu

        JButton start;

        start = new JButton("Start"); // Tworzenie przycisku "Start" z tekstem
        start.setSize(150,36); // Ustawienie rozmiaru przycisku
        start.setLocation(getWidth()/2-start.getWidth()/2, 30); // Ustawienie położenia przycisku na środku wzdłuż osi X
        start.setOpaque(false); // Ustawienie przezroczystości tła przycisku
        start.setContentAreaFilled(false); // Wyłączenie wypełnienia obszaru przycisku
        start.setBorderPainted(false); // Wyłączenie wyświetlania obramowania przycisku
        start.addActionListener(e -> {
            menu.dispose(); // Zamknięcie okna menu
            new Thread(() -> new Window()).start(); // Uruchomienie nowego wątku, który tworzy i wyświetla okno gry (Window)
        });

        add(start); // Dodanie przycisku do okna

        JButton highScore;
        highScore = new JButton("High scores"); // Tworzenie przycisku "High scores" z tekstem
        highScore.setSize(150,36); // Ustawienie rozmiaru przycisku
        highScore.setLocation(getWidth()/2-start.getWidth()/2, 110); // Ustawienie położenia przycisku
        highScore.setOpaque(false); // Ustawienie przezroczystości tła przycisku
        highScore.setContentAreaFilled(false); // Wyłączenie wypełnienia obszaru przycisku
        highScore.setBorderPainted(false); // Wyłączenie wyświetlania obramowania przycisku
        highScore.addActionListener(e -> {
            menu.dispose(); // Zamknięcie okna menu
            new Thread(() -> new HighScores(true)).start(); // Uruchomienie nowego wątku, który tworzy i wyświetla okno z wynikami (HighScores)
        });

        add(highScore); // Dodanie przycisku do okna

        JButton exit;

        exit = new JButton("Exit"); // Tworzenie przycisku "Exit" z tekstem
        exit.setSize(150,36); // Ustawienie rozmiaru przycisku
        exit.setLocation(getWidth()/2-start.getWidth()/2, 190); // Ustawienie położenia przycisku
        exit.setOpaque(false); // Ustawienie przezroczystości tła przycisku
        exit.setContentAreaFilled(false); // Wyłączenie wypełnienia obszaru przycisku
        exit.setBorderPainted(false); // Wyłączenie wyświetlania obramowania przycisku
        exit.addActionListener(e -> {
            menu.dispose(); // Zamknięcie okna menu
        });
        add(exit); // Dodanie przycisku do okna

        this.setVisible(true); // Ustawienie widoczności okna
    }
}