public class WagonRestauracyjny extends Wagon {
    private boolean wymagaPodlaczeniaDoSieci;
    private int liczbaKucharzy;
    private int liczbaStolików;

    public WagonRestauracyjny(String nadawca, boolean zabezpieczenia, double wagaBrutto, int liczbaKucharzy,int liczbaStolików) {
        super(nadawca, zabezpieczenia, wagaBrutto);
        this.wymagaPodlaczeniaDoSieci = true;
        this.liczbaKucharzy=liczbaKucharzy;
        this.liczbaStolików=liczbaStolików;
    }


    public boolean wymagaPodlaczeniaDoSieci() {
        return wymagaPodlaczeniaDoSieci;
    }

    public void setWymagaPodlaczeniaDoSieci(boolean wymagaPodlaczeniaDoSieci) {
        this.wymagaPodlaczeniaDoSieci = wymagaPodlaczeniaDoSieci;
    }
    public String toString() {
        return "WagonRestauracyjny [nadawca=" + getNadawca() + ", zabezpieczenia=" + hasZabezpieczenia()
                + ", wagaBrutto=" + getWagaBrutto() + ", wymagaPodlaczeniaDoSieci=" + wymagaPodlaczeniaDoSieci
                + ", liczbaKucharzy=" + liczbaKucharzy + ", liczbaStolików=" + liczbaStolików + "]";
    }


}