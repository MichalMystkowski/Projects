import javax.swing.*;
import java.util.Random;

/*
Klasa Enemy ma pole direction, które reprezentuje kierunek poruszania się przeciwnika, oraz pole moved, które oznacza, czy przeciwnik już się poruszył w bieżącym ruchu.

Konstruktor Enemy inicjalizuje obiekt przeciwnika, ustawiając typ na Type.GHOST za pomocą konstruktora klasy bazowej Grid. Następnie wywołuje metodę changeDirection().

Metoda action() jest implementacją metody abstrakcyjnej z klasy Grid i określa akcję przeciwnika. W tym przypadku, metoda action() po prostu wywołuje metodę move().

Metoda move() zawiera logikę poruszania się przeciwnika. Przeciwnik porusza się w jednym z czterech kierunków: góra, dół, lewo, prawo. Metoda sprawdza aktualny kierunek przeciwnika i podejmuje odpowiednie akcje w zależności od tego, co znajduje się na drodze przeciwnika.

Metoda changeDirection() zmienia kierunek poruszania się przeciwnika na losowy. Losowy kierunek jest wybierany spośród czterech dostępnych wartości enumeracji Direction, reprezentujących górę, dół, lewo i prawo.

Przy poruszaniu się, metoda move() korzysta z tablicy grids, która reprezentuje całą planszę gry. Na podstawie aktualnego kierunku przeciwnika, metoda sprawdza, czy przeciwnik może się poruszyć w danym kierunku.

Jeśli przeciwnik napotka na ścianę (komórkę o typie Type.WALL), metoda changeDirection() zostaje wywołana, zmieniając kierunek poruszania się przeciwnika.

Jeśli przeciwnik napotka na pacmana (komórkę o typie Type.PACMAN), zostaje wyświetlony komunikat "You lost!" za pomocą JOptionPane, a gra zostaje zakończona ustawiając flagę window.end na true.

Jeśli przeciwnik napotka na pustą komórkę (Type.EMPTY), zostaje dokonana odpowiednia aktualizacja tablicy grids, a pozycja przeciwnika zostaje zaktualizowana.

Jeśli przeciwnik napotka na komórkę z punktem (Type.EMPTY_WITH_SCORE), zachodzi podobna aktualizacja jak dla pustej komórki.

W skrócie, klasa Enemy reprezentuje przeciwnika w grze Pacman i definiuje jego zachowanie podczas poruszania się po planszy. Przeciwnik może poruszać się w czterech kierunkach, zmieniając kierunek w przypadku napotkania przeszkody.
 */
public class Enemy extends Grid {

    private Direction direction = Direction.none;
    public boolean moved = false;

    public Enemy(int posX, int posY, Window window) {
        super(Type.GHOST, posX, posY, window);
        changeDirection();
    }

    @Override
    public void action() {
        move();
    }

    public void move() {

        if (moved)
            return;

        moved = true;

        Grid[][] grids = window.getGrids();
        System.out.println("enemy move");
        switch (direction) {
            case up -> {

                if (posY / 20 - 1 < 0) {
                    changeDirection();
                    return;
                }

                // Sprawdzenie, czy przeciwnik napotkał pacmana
                if (grids[getIndexX()][getIndexY() - 1].type == Type.PACMAN) {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;
                    return;
                }

                // Poruszanie się w górę
                if (grids[posX / 20][posY / 20 - 1].type == Type.EMPTY) {
                    // Aktualizacja tablicy grids
                    window.remove(grids[posX / 20][posY / 20 - 1]);
                    grids[posX / 20][posY / 20 - 1] = this;
                    grids[posX / 20][posY / 20] = new Element(Type.EMPTY, posX, posY, window);
                    grids[posX / 20][posY / 20].setLocation(posX, posY);

                    posY -= 20;
                    this.setLocation(posX, posY);
                } else if (grids[posX / 20][posY / 20 - 1].type == Type.EMPTY_WITH_SCORE) {
                    // Podobna aktualizacja dla komórki z punktem
                    window.remove(grids[posX / 20][posY / 20 - 1]);
                    grids[posX / 20][posY / 20 - 1] = this;
                    grids[posX / 20][posY / 20] = new Element(Type.EMPTY_WITH_SCORE, posX, posY, window);
                    grids[posX / 20][posY / 20].setLocation(posX, posY);

                    posY -= 20;
                    this.setLocation(posX, posY);
                } else {
                    changeDirection();
                }
            }
            case down -> {

                if (getIndexY() + 1 > window.sizeY - 1) {
                    changeDirection();
                    return;
                }

                // Sprawdzenie, czy przeciwnik napotkał pacmana
                if (grids[getIndexX()][getIndexY() + 1].type == Type.PACMAN) {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;
                    return;
                }

                // Poruszanie się w dół
                if (grids[posX / 20][posY / 20 + 1].type == Type.EMPTY) {
                    // Aktualizacja tablicy grids
                    window.remove(grids[posX / 20][posY / 20 + 1]);
                    grids[posX / 20][posY / 20 + 1] = this;
                    grids[posX / 20][posY / 20] = new Element(Type.EMPTY, posX, posY, window);
                    grids[posX / 20][posY / 20].setLocation(posX, posY);

                    posY += 20;
                    this.setLocation(posX, posY);
                } else if (grids[posX / 20][posY / 20 + 1].type == Type.EMPTY_WITH_SCORE) {
                    // Podobna aktualizacja dla komórki z punktem
                    window.remove(grids[posX / 20][posY / 20 + 1]);
                    grids[posX / 20][posY / 20 + 1] = this;
                    grids[posX / 20][posY / 20] = new Element(Type.EMPTY_WITH_SCORE, posX, posY, window);
                    grids[posX / 20][posY / 20].setLocation(posX, posY);

                    posY += 20;
                    this.setLocation(posX, posY);
                } else {
                    changeDirection();
                }

            }
            case left -> {

                if (getIndexX() - 1 < 0) {
                    changeDirection();
                    return;
                }

                // Sprawdzenie, czy przeciwnik napotkał pacmana
                if (grids[getIndexX() - 1][getIndexY()].type == Type.PACMAN) {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;
                    return;
                }

                // Poruszanie się w lewo
                if (grids[posX / 20 - 1][posY / 20].type == Type.EMPTY) {
                    // Aktualizacja tablicy grids
                    window.remove(grids[posX / 20 - 1][posY / 20]);
                    grids[posX / 20 - 1][posY / 20] = this;
                    grids[posX / 20][posY / 20] = new Element(Type.EMPTY, posX, posY, window);
                    grids[posX / 20][posY / 20].setLocation(posX, posY);

                    posX -= 20;
                    this.setLocation(posX, posY);
                } else if (grids[posX / 20 - 1][posY / 20].type == Type.EMPTY_WITH_SCORE) {
                    // Podobna aktualizacja dla komórki z punktem
                    window.remove(grids[posX / 20 - 1][posY / 20]);
                    grids[posX / 20 - 1][posY / 20] = this;
                    grids[posX / 20][posY / 20] = new Element(Type.EMPTY_WITH_SCORE, posX, posY, window);
                    grids[posX / 20][posY / 20].setLocation(posX, posY);

                    posX -= 20;
                    this.setLocation(posX, posY);
                } else {
                    changeDirection();
                }
            }
            case right -> {

                if (getIndexX() + 1 > window.sizeX - 1) {
                    changeDirection();
                    return;
                }

                // Sprawdzenie, czy przeciwnik napotkał pacmana
                if (grids[getIndexX() + 1][getIndexY()].type == Type.PACMAN) {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;
                    return;
                }

                // Poruszanie się w prawo
                if (grids[getIndexX() + 1][getIndexY()].type == Type.EMPTY) {
                    // Aktualizacja tablicy grids
                    window.remove(grids[getIndexX() + 1][getIndexY()]);
                    grids[getIndexX() + 1][getIndexY()] = this;
                    grids[getIndexX()][getIndexY()] = new Element(Type.EMPTY, posX, posY, window);
                    posX += 20;
                    this.setLocation(posX, posY);
                } else if (grids[getIndexX() + 1][getIndexY()].type == Type.EMPTY_WITH_SCORE) {
                    // Podobna aktualizacja dla komórki z punktem
                    window.remove(grids[getIndexX() + 1][getIndexY()]);
                    grids[getIndexX() + 1][getIndexY()] = this;
                    grids[getIndexX()][getIndexY()] = new Element(Type.EMPTY_WITH_SCORE, posX, posY, window);
                    posX += 20;
                    this.setLocation(posX, posY);
                } else {
                    changeDirection();
                }
            }
        }
    }

    public void changeDirection() {
        direction = Direction.values()[new Random().nextInt(4)];
    }
}