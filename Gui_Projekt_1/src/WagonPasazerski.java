public class WagonPasazerski extends Wagon {
    private boolean wymagaPodlaczeniaDoSieci;
    private boolean czyKlimatyzacja;
    private int liczbaToalet;

    public WagonPasazerski(String nadawca, boolean zabezpieczenia, double wagaBrutto, boolean czyKlimatyzacja, int liczbaToalet) {
        super(nadawca, zabezpieczenia, wagaBrutto);
        this.wymagaPodlaczeniaDoSieci = true;
        this.liczbaToalet=liczbaToalet;
        this.czyKlimatyzacja = czyKlimatyzacja;
        this.wymagaPodlaczeniaDoSieci=wymagaPodlaczeniaDoSieci;
    }


    public boolean wymagaPodlaczeniaDoSieci() {
        return wymagaPodlaczeniaDoSieci;
    }

    public void setWymagaPodlaczeniaDoSieci(boolean wymagaPodlaczeniaDoSieci) {
        this.wymagaPodlaczeniaDoSieci = wymagaPodlaczeniaDoSieci;
    }
    @Override
    public String toString() {
        return "Wagon pasażerski: " +
                "wymaga podłączenia do sieci=" + wymagaPodlaczeniaDoSieci +
                ", czy klimatyzacja=" + czyKlimatyzacja +
                ", liczba toalet=" + liczbaToalet +
                ", " + super.toString();
    }


}