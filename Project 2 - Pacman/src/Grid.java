import javax.swing.*;
import java.awt.*;

/*
Klasa zawiera pola takie jak sizeX i sizeY, które reprezentują rozmiar komórki tabeli, posX i posY określające położenie komórki, type reprezentujące typ elementu oraz referencję do obiektu Window.

Konstruktor Grid inicjalizuje wartości pól, ustawia rozmiar i położenie komórki, przypisuje typ elementu i ustawia odpowiedni kolor. Dodaje również komórkę do obiektu Window.

Metody getPosX() i getPosY() zwracają odpowiednio wartości posX i posY.

Metoda paint(Graphics g) odpowiada za rysowanie komórki. Sprawdza, czy dla danego typu elementu istnieje obraz i jeśli tak, rysuje go na komórce. Następnie wywołuje metodę paint(g) z klasy bazowej.

Metody getIndexX() i getIndexY() zwracają indeks x i y komórki na podstawie wartości posX i posY.

Metoda setType(Type type) ustawia typ elementu i wywołuje metodę setColor().

Metoda setColor() ustawia kolor tła komórki w zależności od typu elementu.

Metoda loadImageForType() wczytuje obraz dla danego typu elementu.

Klasa wewnętrzna TableColour rozszerza DefaultTableCellRenderer i jest odpowiedzialna za malowanie komórki w konkretnym kolorze.

Klasa Grid ma również zdefiniowane wyliczenie Type, które reprezentuje dostępne typy elementów.

Metoda action() jest zadeklarowana jako abstrakcyjna, co oznacza, że musi być zaimplementowana w klasach pochodnych.

 */
public abstract class Grid extends JTable {
    protected final int sizeX = 20, sizeY = 20;
    protected int posX, posY;
    protected Type type;
    protected Window window;

    // Konstruktor klasy Grid
    public Grid(Type type, int posX, int posY, Window window) {
        this.setSize(sizeX, sizeY);
        this.setLocation(posX, posY);
        this.window = window;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        setColor();
        window.add(this);
    }

    // Metoda zwracająca wartość posX
    public int getPosX() {
        return posX;
    }

    // Metoda zwracająca wartość posY
    public int getPosY() {
        return posY;
    }

    @Override
    public void paint(Graphics g) {
        Image i = loadImageForType();
        if (i != null) {
            g.drawImage(i, posX, posY, null);
            super.paint(g);
        } else {
            super.paint(g);
        }
    }

    // Metoda zwracająca indeks x
    public int getIndexX() {
        return posX / sizeX;
    }

    // Metoda zwracająca indeks y
    public int getIndexY() {
        return posY / sizeY;
    }

    // Metoda ustawiająca typ elementu
    public void setType(Type type) {
        this.type = type;
        setColor();
    }

    // Prywatna metoda ustawiająca kolor tła w zależności od typu
    private void setColor() {
        switch (type) {
            case EMPTY -> {
                this.setBackground(Color.LIGHT_GRAY);
            }
            case PACMAN -> {
                this.setBackground(Color.YELLOW);
            }
            case GHOST -> {
                this.setBackground(Color.BLUE);
            }
            case WALL -> {
                this.setBackground(Color.DARK_GRAY);
            }
            case EMPTY_WITH_SCORE -> {
                this.setBackground(Color.PINK);
            }
            case UPGRADE1 -> {
                this.setBackground(Color.GREEN);
            }
            case UPGRADE2 -> {
                this.setBackground(Color.RED);
            }
            case UPGRADE3 -> {
                this.setBackground(Color.MAGENTA);
            }
        }
    }

    // Prywatna metoda wczytująca obraz dla danego typu
    private Image loadImageForType() {
        Image ret = null;
        switch (type) {
            case PACMAN -> {
                ret = Toolkit.getDefaultToolkit().createImage("C:\\Users\\kubas\\Desktop\\Pacman\\pacman.png");
            }
            case GHOST -> {
                this.setBackground(Color.BLUE);
            }
            case WALL -> {
                this.setBackground(Color.DARK_GRAY);
            }
            case EMPTY_WITH_SCORE -> {
                this.setBackground(Color.PINK);
            }
        }
        return ret;
    }

    // Metoda abstrakcyjna, która zostanie zaimplementowana w klasach pochodnych
    public abstract void action();

    // Wyliczenie reprezentujące typ elementu
    public enum Type {
        EMPTY,
        PACMAN,
        GHOST,
        WALL,
        EMPTY_WITH_SCORE,
        UPGRADE1,
        UPGRADE2,
        UPGRADE3
    }

    // Wewnętrzna klasa TableColour rozszerzająca DefaultTableCellRenderer
    class TableColour extends javax.swing.table.DefaultTableCellRenderer {
        @Override
        public void paint(Graphics g) {
            g.setColor(Color.MAGENTA);
            g.fillRect(posX, posY, 20, 20);
        }
    }
}

