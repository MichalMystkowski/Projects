public class WagonChłodniczy extends WagonTowarowyPodstawowy {
    private boolean wymagaPodlaczeniaDoSieci;
    private int temperatura;
    private String zawartosc;

    public WagonChłodniczy(String nadawca, boolean zabezpieczenia, double wagaBrutto, double szerokość, double wysokość, int temperatura, String zawartosc) {
        super(nadawca, zabezpieczenia, wagaBrutto, szerokość, wysokość);
        this.wymagaPodlaczeniaDoSieci = true;
        this.zawartosc=zawartosc;
        this.temperatura = temperatura;
    }

    public boolean wymagaPodlaczeniaDoSieci() {
        return wymagaPodlaczeniaDoSieci;
    }

    public void setWymagaPodlaczeniaDoSieci(boolean wymagaPodlaczeniaDoSieci) {
        this.wymagaPodlaczeniaDoSieci = wymagaPodlaczeniaDoSieci;
    }
    public String toString() {
        return "Wagon chłodniczy: " +
                "\n  Nadawca: " + getNadawca() +
                "\n  Waga brutto: " + getWagaBrutto() +
                "\n  Wymiary: " + getSzerokość() + " x " + getWysokość() +
                "\n  Zabezpieczenia: " + hasZabezpieczenia() +
                "\n  Wymaga podłączenia do sieci: " + wymagaPodlaczeniaDoSieci +
                "\n  Temperatura: " + temperatura +
                "\n  Zawartość: " + zawartosc;
    }

    public double getSzerokość() {
        return super.getSzerokość();
    }

    public double getWysokość() {
        return super.getWysokość();
    }



}