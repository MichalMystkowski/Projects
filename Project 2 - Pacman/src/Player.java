import javax.swing.*;
import java.awt.*;
import java.net.Proxy;

/*
Klasa Player dziedziczy po klasie Grid, co oznacza, że dziedziczy jej pola i metody.
Klasa Player ma dwa publiczne pola: direction i moved.
Pole direction reprezentuje kierunek poruszania się gracza i jest typu Direction. Może przyjąć wartość: up, down, left, right lub none (brak ruchu).
Pole moved jest typu boolean i wskazuje, czy gracz wykonał już ruch.
Konstruktor klasy Player przyjmuje trzy argumenty: posX, posY i window.
posX i posY określają początkowe położenie gracza.
window reprezentuje okno gry, w którym znajduje się gracz.
Klasa Player nadpisuje metodę action(). Wywołuje ona metodę move() i wypisuje "p " na standardowym wyjściu.
Prywatna metoda move() jest odpowiedzialna za ruch gracza w zależności od ustawionego kierunku.
Na początku sprawdza, czy gracz już się poruszył. Jeśli tak, to metoda kończy działanie.
Następnie pobiera siatkę (grids) gry z okna (window).
Ustawia flagę moved na true, aby oznaczyć, że gracz wykonał ruch.
Wykorzystując instrukcję switch i wartość pola direction, metoda sprawdza, w którym kierunku gracz chce się poruszyć. W zależności od kierunku, podejmuje odpowiednie działania.

Dla kierunku up:
Sprawdza, czy gracz nie wyjdzie poza górną granicę planszy. Jeśli tak, to metoda kończy działanie.
Sprawdza typ elementu znajdującego się nad graczem (o indeksach [getIndexX()][getIndexY()-1]):
Jeśli to Type.UPGRADE1, gracz zdobywa 50 punktów.
Jeśli to Type.UPGRADE2, gracz zdobywa 100 punktów.
Jeśli to Type.UPGRADE3, gracz zdobywa 250 punktów.
Jeśli to Type.GHOST, wyświetla komunikat "You lost!" za pomocą JOptionPane.showMessageDialog() i kończy grę.
Jeśli to Type.EMPTY_WITH_SCORE, gracz zdobywa 10 punktów.
Jeśli gracz może się poruszyć w górę (element nadejściowy jest pusty lub zawiera punkty), następuje aktualizacja siatki:
Usuwa element znajdujący się nad graczem z okna gry (window).
Ustawia aktualny element gracza na nową pozycję w siatce (grids[posX/20][posY/20-1] = this).
Tworzy nowy pusty element na poprzedniej pozycji gracza (grids[posX/20][posY/20] = new Element(Type.EMPTY, posX, posY, window)).
Aktualizuje pozycję gracza (posY-=20) i lokalizację elementu gracza.
Po wykonaniu ruchu, ustawia moved na true i kończy działanie metody.
Dla kierunku down, left i right postępowanie jest podobne, z różnicami w sprawdzaniu granic planszy i kierunku poruszania się gracza oraz w odpowiednich akcjach dla różnych typów elementów w siatce.
Klasa Player udostępnia metody getDirection() i setDirection(Direction direction), które pozwalają na pobranie i ustawienie kierunku gracza.

Uwaga: Kod nie zawiera definicji klasy Direction. Można przypuszczać, że jest to osobna klasa lub typ wyliczeniowy, który określa możliwe kierunki poruszania się gracza. Bez definicji tej klasy, nie można dokładnie określić, jakie wartości może przyjąć pole direction i jakie metody są dostępne w związku z tym typem.
 */


public class Player extends Grid {

    public Direction direction = Direction.none; //kierunek poruszania sie
    public boolean moved = false; // Flaga określająca czy, czy gracz może sięporuszać

    public Player(int posX, int posY, Window window) {
        super(Type.PACMAN, posX, posY, window);
    }

    @Override
    public void action() {
        System.out.print("p ");
        move(); // wywołanie metody poruszającej gracza
    }

    private void move(){
        if(moved)
            return;

        Grid[][] grids = window.getGrids(); // pobranie siatki gry z okna

        moved = true; // oznaczenie, że gracz wykonał ruch

        switch (direction)
        {
            case up -> {

                if(posY/20-1 < 0)
                    return; // sprawdzenie czy nie wychodzimy poza gorna krawędź okna


                // Sprawdzenie typu elementu znajdującego się nad graczem
                if(grids[getIndexX()][getIndexY()-1].type == Type.UPGRADE1)
                {
                    window.scorePoints += 50; // Zdobywanie punktów

                }

                if(grids[getIndexX()][getIndexY() - 1].type == Type.UPGRADE2)
                {
                    window.scorePoints += 100;

                }

                if(grids[getIndexX()][getIndexY() - 1].type == Type.UPGRADE3)
                {
                    window.scorePoints += 250;

                }

                if(grids[getIndexX()][getIndexY() - 1].type == Type.GHOST)
                {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;
                    return;
                }

                if(grids[getIndexX()][getIndexY() - 1].type == Type.EMPTY_WITH_SCORE)
                {
                    window.scorePoints += 10;

                }

                if(grids[posX/20][posY/20-1].type == Type.EMPTY || grids[posX/20][posY/20-1].type == Type.EMPTY_WITH_SCORE || grids[getIndexX()][getIndexY() - 1].type == Type.UPGRADE3 || grids[getIndexX()][getIndexY() - 1].type == Type.UPGRADE2 || grids[getIndexX()][getIndexY() - 1].type == Type.UPGRADE1)
                {
                    window.remove(grids[posX/20][posY/20-1]);
                    grids[posX/20][posY/20-1] = this;
                    grids[posX/20][posY/20] = new Element(Type.EMPTY, posX, posY, window);
                    grids[posX/20][posY/20].setLocation(posX,posY);

                    posY-=20;
                    this.setLocation(posX, posY);
                }
            }
            case down -> {

                if(getIndexY() + 1 > window.sizeY - 1)
                    return;

                if(grids[getIndexX()][getIndexY() + 1].type == Type.UPGRADE1)
                {
                    window.scorePoints += 50;

                }

                if(grids[getIndexX()][getIndexY() + 1].type == Type.UPGRADE2)
                {
                    window.scorePoints += 100;

                }

                if(grids[getIndexX()][getIndexY() + 1].type == Type.UPGRADE3)
                {
                    window.scorePoints += 250;

                }

                if(grids[getIndexX()][getIndexY() + 1].type == Type.GHOST)
                {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;
                    return;
                }

                if(grids[getIndexX()][getIndexY() + 1].type == Type.EMPTY_WITH_SCORE)
                {
                    window.scorePoints += 10;

                }

                if(grids[posX/20][posY/20+1].type == Type.EMPTY || grids[posX/20][posY/20+1].type == Type.EMPTY_WITH_SCORE || grids[getIndexX()][getIndexY() + 1].type == Type.UPGRADE1 || grids[getIndexX()][getIndexY() + 1].type == Type.UPGRADE2 || grids[getIndexX()][getIndexY() + 1].type == Type.UPGRADE3)
                {
                    window.remove(grids[posX/20][posY/20+1]);
                    grids[posX/20][posY/20+1] = this;
                    grids[posX/20][posY/20] = new Element(Type.EMPTY, posX, posY, window);
                    grids[posX/20][posY/20].setLocation(posX,posY);

                    posY+=20;
                    this.setLocation(posX, posY);
                }

            }
            case left -> {

                if(getIndexX()-1 < 0)
                    return;

                if(grids[getIndexX() - 1][getIndexY()].type == Type.GHOST)
                {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;

                    return;
                }

                if(grids[getIndexX() - 1][getIndexY()].type == Type.UPGRADE1)
                {
                    window.scorePoints += 50;

                }

                if(grids[getIndexX() - 1][getIndexY()].type == Type.UPGRADE2)
                {
                    window.scorePoints += 100;

                }

                if(grids[getIndexX() - 1][getIndexY()].type == Type.UPGRADE3)
                {
                    window.scorePoints += 250;

                }

                if(grids[getIndexX() - 1][getIndexY()].type == Type.EMPTY_WITH_SCORE)
                {
                    window.scorePoints += 10;

                }

                if(grids[posX/20-1][posY/20].type == Type.EMPTY || grids[posX/20-1][posY/20].type == Type.EMPTY_WITH_SCORE || grids[getIndexX() - 1][getIndexY()].type == Type.UPGRADE1 || grids[getIndexX() - 1][getIndexY()].type == Type.UPGRADE2 || grids[getIndexX() - 1][getIndexY()].type == Type.UPGRADE3)
                {
                    window.remove(grids[posX/20-1][posY/20]);
                    grids[posX/20-1][posY/20] = this;
                    grids[posX/20][posY/20] = new Element(Type.EMPTY, posX, posY, window);
                    grids[posX/20][posY/20].setLocation(posX,posY);

                    posX-=20;
                    this.setLocation(posX, posY);
                }

                if(grids[getIndexX() + 1][getIndexY()].type == Type.GHOST)
                {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;

                    return;
                }

                if(grids[getIndexX() + 1][getIndexY()].type == Type.EMPTY_WITH_SCORE)
                {
                    window.scorePoints += 10;

                }
            }
            case right -> {

                if(getIndexX() + 1 > window.sizeX - 1)
                    return;

                if(grids[getIndexX() + 1][getIndexY()].type == Type.GHOST)
                {
                    JOptionPane.showMessageDialog(window, "You lost!");
                    window.end = true;

                    return;
                }

                if(grids[getIndexX() + 1][getIndexY()].type == Type.UPGRADE1)
                {
                    window.scorePoints += 50;

                }

                if(grids[getIndexX() + 1][getIndexY()].type == Type.UPGRADE2)
                {
                    window.scorePoints += 100;

                }

                if(grids[getIndexX() + 1][getIndexY()].type == Type.UPGRADE3)
                {
                    window.scorePoints += 250;

                }

                if(grids[getIndexX() + 1][getIndexY()].type == Type.EMPTY_WITH_SCORE)
                {
                    window.scorePoints += 10;

                }
                if(grids[getIndexX() + 1][getIndexY()].type == Type.EMPTY || grids[getIndexX() + 1][getIndexY()].type == Type.EMPTY_WITH_SCORE || grids[getIndexX() + 1][getIndexY()].type == Type.UPGRADE3 || grids[getIndexX() + 1][getIndexY()].type == Type.UPGRADE2 || grids[getIndexX() + 1][getIndexY()].type == Type.UPGRADE1)
                {
                    window.remove(grids[getIndexX() + 1][getIndexY()]);
                    grids[getIndexX() + 1][getIndexY()] = this;
                    grids[getIndexX()][getIndexY()] = new Element(Type.EMPTY, posX, posY, window);
                    posX+=20;
                    this.setLocation(posX, posY);
                }
            }
        }
    }

    public Direction getDirection() {
        return direction; // Pobranie kierunku poruszania się gracza
    }

    public void setDirection(Direction direction) {
        this.direction = direction; // Ustawienie kierunku poruszania się gracza
    }
}


