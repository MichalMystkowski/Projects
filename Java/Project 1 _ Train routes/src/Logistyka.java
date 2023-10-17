import java.util.ArrayList;
import java.util.Random;

public class Logistyka {
    private ArrayList<Lokomotywa> lokomotywy; // na dostępne
    private ArrayList<Lokomotywa> lokomotywyWUzyciu; // na te używane

    private ArrayList<Wagon> wagony; // na dostępne
    private ArrayList<Wagon> wagonyWUzyciu; // na te używane

    private ArrayList<Stacja> stacje; // tylko jedna bo nie zmieniają się

    private ArrayList<Trasa> trasy; // cały przejazd
    public Logistyka() {
        lokomotywy = new ArrayList<>();
        wagony = new ArrayList<>();
        stacje = new ArrayList<>();

        lokomotywyWUzyciu = new ArrayList<>();
        wagonyWUzyciu = new ArrayList<>();

        trasy = new ArrayList<>();
    }


    public void dodajLokomotywe(Lokomotywa lokomotywa) {
        lokomotywa.setNumerIdentyfikacyjny(lokomotywy.size() + 1);
        lokomotywy.add(lokomotywa);
    }

    public void dodajWagon(Wagon wagon){
        wagony.add(wagon);
    }
    public void dodajStacje(Stacja stacja){
        stacje.add(stacja);
    }


    public ArrayList<Lokomotywa> getListaLokomotyw() {
        return lokomotywy;
    }


    public void wyswietlListeLokomotyw() {
        ArrayList<Lokomotywa> listaLokomotyw = getListaLokomotyw();
        if (listaLokomotyw.isEmpty()) {
            System.out.println("Lista lokomotyw jest pusta.");
            return;
        }
        System.out.println("Lista lokomotyw:");
        for (Lokomotywa lokomotywa : listaLokomotyw) {
            System.out.println(lokomotywa.toString());
        }
    }

    public ArrayList<Stacja> getListaStacji() {
        return stacje;
    }

    public void wyswietlListeStacji(){
        ArrayList<Stacja> listaStacji = getListaStacji();
        if (listaStacji.isEmpty()) {
            System.out.println("Lista stacji jest pusta.");
            return;
        }
        System.out.println("Lista stacji:");
        for (Stacja s : listaStacji) {
            System.out.println(s.toString());
        }
    }

    public ArrayList<Wagon> getListaWagonow() {
        return wagony;
    }

    public void wyswietlListeWagonow(){
        ArrayList<Wagon> listaWagonow = getListaWagonow();
        if (listaWagonow.isEmpty()) {
            System.out.println("Lista stacji jest pusta.");
            return;
        }
        System.out.println("Lista stacji:");
        for (Wagon w : listaWagonow) {
            System.out.println(w.toString());
        }
    }

    public Lokomotywa uzyjLokomotywy(int id){
        Lokomotywa l = lokomotywy.stream().filter(e->e.getNumerIdentyfikacyjny() == id).findFirst().get();
        lokomotywy.remove(l);
        lokomotywyWUzyciu.add(l);
        return l;
    }

    public Wagon uzyjWagonu(String idWagonu, int idLokomotywy){
        Wagon w = wagony.stream().filter(e->e.getId().equals(idWagonu)).findFirst().get();
        wagony.remove(w);
        wagonyWUzyciu.add(w);

        Lokomotywa l = lokomotywy.stream().filter(e->e.getNumerIdentyfikacyjny() == idLokomotywy).findFirst().get();
        l.dodajWagon(w);

        return w;
    }

    public Stacja pobierzStacje(String id)
    {
        return stacje.stream().filter(e->e.getId().equals(id)).findFirst().get();
    }

    public ArrayList<PolaczenieStacji> zbudujMapePolaczenDlaPodanychDestynacji(Stacja start, Stacja koniec){
        // Metoda zwraca listę PolaczenieStacji, która jest budowana na podstawie wyniku działania prywatnej metody przeszukującej graf
        ArrayList<Stacja> stacje = przeszukajGraf(start, koniec);
        // Lista PolaczenieStacji do zwrócenia na koniec metody
        ArrayList<PolaczenieStacji> polaczenia = new ArrayList<>();

        // Wylosowanie odległości między stacjami i dodanie ich do tablicy
        double[] odleglosci = new double[stacje.size()-1];
        for(int i = 0; i < stacje.size()-1; i++)
            odleglosci[i] = new Random().nextInt(20, 200);

        // Dodanie PolaczenieStacji od stacji końcowej do stacji początkowej
        for(int i = stacje.size() - 1; i >= 1; i--)
        {
            polaczenia.add(new PolaczenieStacji(stacje.get(i), stacje.get(i-1), odleglosci[i-1]));
        }

        // Dodanie PolaczenieStacji od stacji początkowej do stacji końcowej
        for(int i = 0; i < stacje.size() - 1; i++)
        {
            // Sprawdzenie, czy dodawane PolaczenieStacji jest ostatnie na liście
            polaczenia.add(new PolaczenieStacji(stacje.get(i), stacje.get(i+1), odleglosci[i], i == stacje.size() - 1));
        }

        // Zwrócenie listy PolaczenieStacji
        return polaczenia;
    }

    private ArrayList<Stacja> przeszukajGraf(Stacja start, Stacja koniec) {
        ArrayList<Stacja> odwiedzone = new ArrayList<>(); // lista odwiedzonych stacji
        return przeszukajGraf(start, odwiedzone, koniec); // wywołanie prywatnej metody przeszukującej graf
    }

    private ArrayList<Stacja> przeszukajGraf(Stacja start, ArrayList<Stacja> odwiedzone, Stacja koniec) {
        odwiedzone.add(start); // dodanie bieżącej stacji do listy odwiedzonych
        System.out.println("odwiedzone:" + odwiedzone);
        System.out.println("aktualna:" + start.getNazwa());
        ArrayList<Stacja> stacje = new ArrayList<>(); // lista stacji, do których można dotrzeć z bieżącej stacji
        boolean finded = false; // flaga informująca, czy znaleziono ścieżkę prowadzącą do stacji docelowej

        // dla każdej stacji połączonej z bieżącą stacją
        for (Stacja s : start.getPolaczenia()) {
            if (!odwiedzone.contains(s)) { // jeśli stacja nie została jeszcze odwiedzona
                ArrayList<Stacja> stacje_ = przeszukajGraf(s, odwiedzone, koniec); // rekurencyjne wywołanie metody dla stacji s
                if (stacje_ != null) { // jeśli udało się znaleźć ścieżkę prowadzącą do stacji docelowej
                    finded = true; // ustawienie flagi na true
                    stacje.addAll(stacje_); // dodanie wyniku do listy stacji
                }
            }
        }

        if (start == koniec || finded) { // jeśli bieżąca stacja jest stacją docelową lub znaleziono ścieżkę prowadzącą do stacji docelowej
            stacje.add(start); // dodanie bieżącej stacji do listy stacji
            return stacje; // zwrócenie listy stacji jako wynik
        }
        return null; // jeśli nie udało się znaleźć ścieżki prowadzącej do stacji docelowej, zwrócenie wartości null
    }

    public void dodajTrase(Trasa trasa)
    {
        trasy.add(trasa);
    }

    public void uruchomSymulacje(){
        System.out.println("Uruchamiam symulacje!");
        for (Trasa t : trasy) {
            new Thread(t).start();
        }
        System.out.println("Symulacja zakonczona!");
    }
}

