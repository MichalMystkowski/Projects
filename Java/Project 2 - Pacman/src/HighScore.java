import java.io.Serializable;
import java.util.Comparator;

/*
Implementacja interfejsu Serializable: Klasa HighScore implementuje interfejs Serializable, co oznacza, że obiekty tej klasy mogą być serializowane, czyli przekształcane na postać, która może być zapisana i odczytana z pliku lub przesłana przez sieć.

Pola playerName i score: Klasa HighScore posiada dwa publiczne pola - playerName i score, które przechowują nazwę gracza i wynik w postaci tekstowej.

Konstruktor bezargumentowy HighScore(): Jest to konstruktor domyślny, który nie przyjmuje żadnych argumentów i nie wykonuje żadnych instrukcji. Może być używany do utworzenia obiektu HighScore z późniejszym ustawieniem wartości pól za pomocą setterów.

Konstruktor HighScore(String playerName, String score): Konstruktor ten przyjmuje dwa argumenty: playerName (nazwa gracza) i score (wynik) i ustawia odpowiednie pola obiektu HighScore na podane wartości.

Metoda compare(HighScore h1, HighScore h2): Jest to metoda zaimplementowana z interfejsu Comparator<HighScore>. Służy do porównywania dwóch obiektów typu HighScore na podstawie ich wyników. Metoda porównuje wyniki, przekształcając je z postaci tekstowej na liczbową, i zwraca odpowiednią wartość zgodnie z konwencją interfejsu Comparator. Jeśli wynik h1 jest mniejszy niż wynik h2, zwracana jest wartość 1. Jeśli są równe, zwracane jest 0. W przeciwnym razie zwracana jest wartość -1.

Dzięki implementacji interfejsu Serializable obiekty klasy HighScore mogą być łatwo zapisywane i odczytywane z pliku, co umożliwia przechowywanie i manipulację wynikami gry. Natomiast implementacja interfejsu Comparator pozwala na sortowanie obiektów HighScore według ich wyników.
 */
public class HighScore implements Serializable, Comparator<HighScore> {
    public String playerName; // Nazwa gracza
    public String score; // Wynik

    public HighScore(){

    }

    // Konstruktor z argumentami
    public HighScore(String playerName, String score) {
        this.playerName = playerName; // Ustawienie nazwy gracza
        this.score = score; // Ustawienie wyniku
    }

    // Implementacja metody compare z interfejsu Comparator
    @Override
    public int compare(HighScore h1, HighScore h2) {
        if(Integer.parseInt(h1.score) < Integer.parseInt(h2.score))
            return 1; // Jeśli wynik h1 jest mniejszy niż wynik h2, zwróć 1
        else if (Integer.parseInt(h1.score) == Integer.parseInt(h2.score))
            return 0; // Jeśli wyniki są równe, zwróć 0
        else
            return -1; // W przeciwnym razie zwróć -1
    }
}