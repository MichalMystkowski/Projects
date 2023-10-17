import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/*
Klasa Window jest rozszerzeniem klasy JFrame i implementuje interfejs KeyListener. Odpowiada za utworzenie i zarządzanie oknem gry.

Oto kilka istotnych części klasy:

Deklaracje zmiennych:

sizeX i sizeY: określają rozmiar mapy gry,
time: przechowuje czas gry,
end: flaga informująca o zakończeniu gry,
player: obiekt gracza,
grids: dwuwymiarowa tablica przechowująca elementy planszy,
scorePoints: punkty zdobyte przez gracza,
playerName: nazwa gracza.
Konstruktor:

Inicjalizuje zmienne i tworzy okno gry.
Wczytuje mapę gry.
Tworzy przycisk "Menu" oraz etykiety "Time" i "Score".
Uruchamia wątki odliczające czas gry i aktualizujące punkty gracza.
Dodaje słuchaczy zdarzeń klawiatury.
Ustala parametry okna gry.
Wywołuje metodę run().
Metoda loadMap():

Tworzy elementy planszy i dodaje je do okna gry.
Tworzy obiekt gracza i przeciwników (duchów) na losowych pozycjach.
Metoda addElement():

Zastępuje istniejący element planszy nowym elementem o określonym typie na podanej pozycji.
Metoda addPacman():

Losowo wybiera pustą pozycję na planszy.
Tworzy obiekt gracza (pacmana) na wybranej pozycji i dodaje go do okna gry.
Metoda addGhost():

Losowo wybiera pustą pozycję na planszy.
Tworzy obiekt przeciwnika (ducha) na wybranej pozycji i dodaje go do okna gry.
Metoda run():

Wykonuje pętlę gry, w której aktualizowane są elementy planszy, przeciwnicy poruszają się, a gracz ma możliwość ruchu.
Wątek zatrzymuje się na krótki czas (1000 ms) pomiędzy kolejnymi iteracjami.
Metoda countUpgrades():

Zlicza liczbę elementów na planszy o typach UPGRADE1, UPGRADE2 i UPGRADE3 i zwraca ich liczbę.
Metody interfejsu KeyListener:

keyTyped(), keyPressed(), keyReleased(): Reagują na wciśnięcie klawisza przez gracza i ustawiają odpowiednią wartość zmiennej direction gracza w zależności od klawisza.
W skrócie, klasa Window odpowiada za tworzenie okna gry, wczytywanie mapy, dodawanie elementów planszy (gracza i przeciwników), zarządzanie czasem gry, aktualizację punktów gracza oraz obsługę zdarzeń klawiatury dla poruszania się gracza.
 */

public class Window extends JFrame implements KeyListener {

    //wielkosc mapy
    public int sizeX = 10, sizeY = 11;

    //czas gry
    private int time;

    public boolean end = false;
    private Player player;
    private Grid[][] grids;
    private Window window;

    public int scorePoints = 0;
    private String playerName;

    public Window() {

        grids = new Grid[sizeX][sizeY];
        loadMap();
        window = this;
        // Wprowadzanie nazwy gracza
        playerName = JOptionPane.showInputDialog("Type name:");

        JButton menu = new JButton("Menu");
        menu.setSize(70,24);
        menu.setLocation(115, 222);
        menu.addActionListener(e -> {
            window.dispose();
            new Thread(() -> new Menu()).start();
        });
        add(menu);

        JLabel timer = new JLabel("Time:0");
        timer.setSize(100, 30);
        timer.setLocation(10, 222);

        JLabel score = new JLabel("Score:0");
        score.setSize(100, 30);
        score.setLocation(60, 222);
        // Wątek aktualizujący punkty gracza co sekundę
        new Thread(() -> {
            while (true) {
                if (end)
                    return;

                try {
                    Thread.sleep(1000);
                    score.setText("Score:" + scorePoints);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        add(score);

        this.addKeyListener(this);

        this.rootPane.addKeyListener(this);
        // Wątek odliczający czas gry co sekundę
        new Thread(() -> {
            while (true) {
                if (end)
                    return;

                try {
                    Thread.sleep(1000);
                    time++;
                    timer.setText("Time:" + time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        add(timer);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(sizeX * 20 + 16, sizeY * 20 + 68);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        run();
    }
    // Metoda wczytująca mapę gry
    private void loadMap() {
        for(int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                grids[i][j] = new Element(Grid.Type.EMPTY_WITH_SCORE, i * 20, j * 20, this);
                grids[i][j].addKeyListener(this);
                add(grids[i][j]);
            }
        }
        // Dodawanie elementów typu WALL na planszę
        addElement(Grid.Type.WALL, 1,1);
        addElement(Grid.Type.WALL, 1,2);
        addElement(Grid.Type.WALL, 1,3);
        addElement(Grid.Type.WALL, 1,4);
        addElement(Grid.Type.WALL, 1,5);
        addElement(Grid.Type.WALL, 1,6);
        addElement(Grid.Type.WALL, 1,7);
        addElement(Grid.Type.WALL, 1,8);
        addElement(Grid.Type.WALL, 1,9);

        addElement(Grid.Type.WALL, 2,5);
        addElement(Grid.Type.WALL, 2,2);
        addElement(Grid.Type.WALL, 3,5);
        addElement(Grid.Type.WALL, 4,5);
        addElement(Grid.Type.WALL, 5,5);

        addElement(Grid.Type.WALL, 3,7);
        addElement(Grid.Type.WALL, 3,9);

        addElement(Grid.Type.WALL, 3,1);
        addElement(Grid.Type.WALL, 2,1);
        addElement(Grid.Type.WALL, 3,3);
        addElement(Grid.Type.WALL, 2,3);
        addElement(Grid.Type.WALL, 3,2);

        addElement(Grid.Type.WALL, 5,1);
        addElement(Grid.Type.WALL, 5,2);
        addElement(Grid.Type.WALL, 5,3);
        addElement(Grid.Type.WALL, 5,4);
        addElement(Grid.Type.WALL, 5,5);

        addElement(Grid.Type.WALL, 4,8);
        addElement(Grid.Type.WALL, 5,8);
        addElement(Grid.Type.WALL, 6,8);
        addElement(Grid.Type.WALL, 7,8);
        addElement(Grid.Type.WALL, 8,8);
        addElement(Grid.Type.WALL, 7,1);

        addElement(Grid.Type.WALL, 8,1);
        addElement(Grid.Type.WALL, 8,2);
        addElement(Grid.Type.WALL, 8,3);
        addElement(Grid.Type.WALL, 8,4);
        addElement(Grid.Type.WALL, 8,5);
        addElement(Grid.Type.WALL, 8,6);
        addElement(Grid.Type.WALL, 8,7);
        // Dodawanie gracza i przeciwników
        addPacman();
        addGhost();
        addGhost();
        addGhost();
    }
    // Metoda dodająca element do planszy
    public void addElement(Grid.Type type, int indexX, int indexY){
        remove(grids[indexX][indexY]);
        grids[indexX][indexY] = new Element(type, indexX*20, indexY*20, this);
    }
    // Metoda dodająca gracza na planszę
    public void addPacman(){
        Random random = new Random();
        int rx, ry;
        do {
            rx = random.nextInt(sizeX);
            ry = random.nextInt(sizeY);
        }while (grids[rx][ry].type != Grid.Type.EMPTY_WITH_SCORE);
        remove(grids[rx][ry]);
        grids[rx][ry] = new Player(rx * 20, ry * 20, this);
        grids[rx][ry].setLocation(rx*20, ry*20);
        player = (Player) grids[rx][ry];
        add(grids[rx][ry]);
    }
    // Metoda dodająca przeciwnika na planszę
    public void addGhost(){
        Random random = new Random();
        int rx, ry;
        do {
            rx = random.nextInt(sizeX);
            ry = random.nextInt(sizeY);
        }while (grids[rx][ry].type != Grid.Type.EMPTY_WITH_SCORE);
        remove(grids[rx][ry]);
        grids[rx][ry] = new Enemy(rx * 20, ry * 20, this);
        grids[rx][ry].setLocation(rx*20, ry*20);
        add(grids[rx][ry]);
    }
    // Metoda uruchamiająca główną pętlę gry
    public void run(){
        while (!end)
        {
            System.out.println();
            for(int j = 0; j < sizeY; j++) {
                for (int i = 0; i < sizeX; i++) {
                    grids[i][j].action();
                    grids[i][j].repaint();

                    if(grids[i][j]instanceof Enemy)
                    {
                        ((Enemy)grids[i][j]).moved = false;
                    }

                }
            }
            player.moved = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        HighScores.HighScoreData.AddHighScore(playerName, scorePoints);
    }
    // Metoda zwracająca tablicę planszy
    public Grid[][] getGrids() {
        return grids;
    }
    // Metoda zliczająca ilość elementów typu UPGRADE na planszy
    public int countUpgrades(){
        int counter = 0;
        for(int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                if(grids[i][j].type == Grid.Type.UPGRADE1 || grids[i][j].type == Grid.Type.UPGRADE2 || grids[i][j].type == Grid.Type.UPGRADE3)
                    counter++;
            }
        }
        return counter;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP -> { player.direction = Direction.up; }
            case KeyEvent.VK_DOWN -> { player.direction = Direction.down; }
            case KeyEvent.VK_LEFT -> { player.direction = Direction.left; }
            case KeyEvent.VK_RIGHT -> { player.direction = Direction.right; }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP -> { player.direction = Direction.up; }
            case KeyEvent.VK_DOWN -> { player.direction = Direction.down; }
            case KeyEvent.VK_LEFT -> { player.direction = Direction.left; }
            case KeyEvent.VK_RIGHT -> { player.direction = Direction.right; }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}