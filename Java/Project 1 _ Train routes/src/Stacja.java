import java.util.ArrayList;

public class Stacja {
    private final String nazwa;
    private final ArrayList<Stacja> polaczenia;
    private String id;
    private static int idCounter;
    public Stacja(String nazwa) {
        this.id = "s" + idCounter;
        idCounter++;
        this.nazwa = nazwa;
        this.polaczenia = new ArrayList<>();
    }

    public String getNazwa() {
        return nazwa;
    }

    public ArrayList<Stacja> getPolaczenia() {
        return polaczenia;
    }

    public void dodajPolaczenie(Stacja polaczenie) {
        polaczenia.add(polaczenie);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Stacja{" +
                "nazwa='" + nazwa + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
