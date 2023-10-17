import java.util.Random;

/*
Ta klasa reprezentuje elementy na siatce gry. Dziedziczy po klasie Grid i definiuje dodatkową logikę dla tych elementów.

Konstruktor klasy Element przyjmuje typ elementu (Type), pozycję posX i posY, oraz okno gry (Window). Wywołuje również konstruktor klasy nadrzędnej Grid, przekazując do niego te parametry.

Metoda action() jest przesłoniętą metodą z klasy nadrzędnej. W tej klasie, metoda ta zawiera logikę dla elementów typu EMPTY. Jeśli typ elementu jest EMPTY, a liczba ulepszeń (upgrade'ów) na planszy jest mniejsza niż 3 i losowo wylosowana liczba całkowita z przedziału od 0 do 19 wynosi 5, następuje akcja.

Wewnątrz metody action() pobierana jest siatka (Grid[][] grids) z okna gry (Window) i usuwany jest aktualny element z tej siatki. Następnie, w zależności od wylosowanej liczby całkowitej z przedziału od 0 do 2, tworzony jest nowy element o jednym z trzech możliwych typów ulepszeń (Type.UPGRADE1, Type.UPGRADE2, Type.UPGRADE3). Ten nowy element zostaje umieszczony na siatce zamiast elementu usuniętego wcześniej.

W skrócie, klasa Element definiuje zachowanie dla elementów typu EMPTY, które mogą losowo zamieniać się w ulepszenia (upgrade'y) na planszy gry.
 */
public class Element extends Grid{

    public Element(Type type, int posX, int posY, Window window) {
        super(type, posX, posY, window);
    }

    @Override
    public void action() {
        // Sprawdzamy, czy typ elementu to EMPTY, liczba ulepszeń na planszy jest mniejsza niż 3,
        // oraz czy losowo wylosowana liczba z przedziału 0-19 wynosi 5.
        if(type == Type.EMPTY && window.countUpgrades() < 3 && new Random().nextInt(20)==5)
        {
            Grid[][] grids = window.getGrids();
            // Usuwamy bieżący element z siatki gry.
            window.remove(grids[posX/20][posY/20]);

            // Losujemy nowy typ ulepszenia i tworzymy nowy element z wylosowanym typem.
            switch (new Random().nextInt(3)) {
                case 0 -> { grids[posX / 20][posY / 20] = new Element(Type.UPGRADE1, posX, posY, window); }
                case 1 -> { grids[posX / 20][posY / 20] = new Element(Type.UPGRADE2, posX, posY, window); }
                case 2 -> { grids[posX / 20][posY / 20] = new Element(Type.UPGRADE3, posX, posY, window); }
            }
        }
    }
}