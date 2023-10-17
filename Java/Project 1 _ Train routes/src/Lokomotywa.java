import java.util.ArrayList;
import java.util.Random;

public class Lokomotywa {
    private int maksymalnaLiczbaWagonow;
    private int maksymalnaWagaLadunku;
    private int liczbaWagonowElektrycznych;
    private String nazwa;
    private int numerIdentyfikacyjny;

    private static int nextId = 1;
    private int predkosc;

    private ArrayList<Wagon> wagony;

    public Lokomotywa(int maksymalnaLiczbaWagonow, int maksymalnaWagaLadunku, int liczbaWagonowElektrycznych, String nazwa, int predkosc) {
        this.maksymalnaLiczbaWagonow = maksymalnaLiczbaWagonow;
        this.maksymalnaWagaLadunku = maksymalnaWagaLadunku;
        this.liczbaWagonowElektrycznych = liczbaWagonowElektrycznych;
        this.nazwa = nazwa;
        this.numerIdentyfikacyjny = nextId++;
        this.predkosc = predkosc;
        wagony = new ArrayList<>();
    }



    public double getPredkosc() {
        return predkosc;
    }

    public int getNumerIdentyfikacyjny() {
        return numerIdentyfikacyjny;
    }

    public int getMaksymalnaLiczbaWagonow() {
        return maksymalnaLiczbaWagonow;
    }

    public int getMaksymalnaWagaLadunku() {
        return maksymalnaWagaLadunku;
    }

    public void dodajWagon(Wagon wagon) {
        wagony.add(wagon);
    }

    public void zmienPredkoscLosowo() {
        Random random = new Random();
        if (random.nextBoolean()) {
            predkosc += predkosc * 0.03;
        } else {
            predkosc -= predkosc * 0.03;
        }
        System.out.println("Aktualna prędkość lokomotywy o ID " + getNumerIdentyfikacyjny() + " " + predkosc);
    }

    public void setNumerIdentyfikacyjny(int numerIdentyfikacyjny) {
        this.numerIdentyfikacyjny = nextId;
    }

    @Override
    public String toString() {
        return "Lokomotywa{" +
                "numerIdentyfikacyjny=" + numerIdentyfikacyjny +
                ", maksLiczbaWagonow=" + maksymalnaLiczbaWagonow +
                ", maksWagaLadunku=" + maksymalnaWagaLadunku +
                ", liczbaWagonowElektrycznych=" + liczbaWagonowElektrycznych +
                ", nazwa='" + nazwa + '\'' +
                ", predkosc=" + predkosc +
                ", wagony=" + wagony +
                '}';
    }
}