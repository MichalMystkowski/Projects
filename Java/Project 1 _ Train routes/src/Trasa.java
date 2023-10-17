import java.util.ArrayList;
import java.util.List;

public class Trasa implements Runnable {
    private final Lokomotywa lokomotywa;
    private final List<PolaczenieStacji> polaczenia;

    public Trasa(Lokomotywa lokomotywa, List<PolaczenieStacji> polaczenia) {
        this.lokomotywa = lokomotywa;
        this.polaczenia = new ArrayList<>(polaczenia);
    }

    public double getDlugoscTrasy() {
        double dlugosc = 0;
        for (PolaczenieStacji połączenie : polaczenia) {
            dlugosc += połączenie.getDlugosc();
        }
        return dlugosc;
    }

    private void start() throws InterruptedException {
        for(PolaczenieStacji ps : polaczenia)
        {
            System.out.println("Lokomotywa:" + lokomotywa + " wyjeźdża na trase " + ps.getPoczatek().getNazwa() + " do " + ps.getKoniec().getNazwa());
            double przejechane = 0;

            while (przejechane < ps.getDlugosc())
            {
                Thread.sleep(1000);
                System.out.println("Przejechano " + przejechane + " z " + ps.getDlugosc() + " odcinka" + ps.getPoczatek() + "/" + ps.getKoniec());
                przejechane += lokomotywa.getPredkosc() / 60;
                lokomotywa.zmienPredkoscLosowo();
            }

            if(ps.getFinalna()) {
                System.out.println("Lokomotywa o id = " + lokomotywa.getNumerIdentyfikacyjny() + " dojechała do stacji docelowej czeka 30s i wraca");
                Thread.sleep(30000);
            }
            else if (ps != polaczenia.get(polaczenia.size()-1)) {
                System.out.println("Lokomotywa o id = " + lokomotywa.getNumerIdentyfikacyjny() + " czeka 2s na stacji pośredniej");
                Thread.sleep(2000);
            }
        }
        System.out.println("Lokomotywa o id " + lokomotywa.getNumerIdentyfikacyjny() + " zakończyla podróż!");
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            System.out.println("Podczas działania symulacji napotkano na błąd -> " + e);
        }
    }
}