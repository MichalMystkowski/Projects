public class WagonBagazowoPocztowy extends Wagon {
    private int maxLiczbaBagazy;
    private int maxLiczbaPrzesyłek;

    public WagonBagazowoPocztowy(String nadawca, boolean zabezpieczenia, double wagaBrutto, int maxLiczbaPrzesyłek, int maxLiczbaBagazy) {
        super(nadawca, zabezpieczenia, wagaBrutto);
        this.maxLiczbaBagazy=maxLiczbaBagazy;
        this.maxLiczbaPrzesyłek=maxLiczbaPrzesyłek;
    }


    public int getMaxLiczbaBagazy() {
        return maxLiczbaBagazy;
    }

    public void setMaxLiczbaBagazy(int maxLiczbaBagazy) {
        this.maxLiczbaBagazy = maxLiczbaBagazy;
    }

    public int getMaxLiczbaPrzesyłek() {
        return maxLiczbaPrzesyłek;
    }

    public void setMaxLiczbaPrzesyłek(int maxLiczbaPrzesyłek) {
        this.maxLiczbaPrzesyłek = maxLiczbaPrzesyłek;
    }

    @Override
    public String toString() {
        return "WagonBagazowoPocztowy [identyfikator=" + super.getId() + ", maxLiczbaBagazy=" + maxLiczbaBagazy
                + ", maxLiczbaPrzesyłek=" + maxLiczbaPrzesyłek + "]";
    }
}
