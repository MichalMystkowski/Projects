public class WagonPocztowy extends Wagon {
    private boolean wymagaPodlaczeniaDoSieci;
    private boolean sortowaniePrzesyłek;
    private boolean czyPrzesyłkiZagraniczne;

    public WagonPocztowy(String nadawca, boolean zabezpieczenia, double wagaBrutto, double wagaNetto, boolean sortowaniePrzesyłek, boolean czyPrzesyłkiZagraniczne) {
        super(nadawca, zabezpieczenia, wagaBrutto);
        this.wymagaPodlaczeniaDoSieci = true;
        this.sortowaniePrzesyłek=sortowaniePrzesyłek;
        this.czyPrzesyłkiZagraniczne=czyPrzesyłkiZagraniczne;

    }


    public boolean wymagaPodlaczeniaDoSieci() {
        return wymagaPodlaczeniaDoSieci;
    }

    public void setWymagaPodlaczeniaDoSieci(boolean wymagaPodlaczeniaDoSieci) {
        this.wymagaPodlaczeniaDoSieci = wymagaPodlaczeniaDoSieci;
    }
    public String toString() {
        return "Wagon pocztowy: " +
                "wymaga podłączenia do sieci=" + wymagaPodlaczeniaDoSieci +
                ", sortowanie przesyłek=" + sortowaniePrzesyłek +
                ", czy przesyłki zagraniczne=" + czyPrzesyłkiZagraniczne +
                ", " + super.toString();
    }

}
