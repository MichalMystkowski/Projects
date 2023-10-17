public abstract class Wagon {

    private String nadawca;
    private boolean zabezpieczenia;
    private double wagaBrutto;
    private int liczbaMiejscSiedzacych;
    private int counter = 0;
    private String wagonId;

    public Wagon(String nadawca, boolean zabezpieczenia, double waga) {
        this.wagonId = "w" + ++counter;
        this.zabezpieczenia = zabezpieczenia;
        this.wagaBrutto = waga;
        this.nadawca = nadawca;
    }

    public String getId() {
        return wagonId;
    }

    public String getNadawca() {
        return nadawca;
    }

    public void setNadawca(String nadawca) {
        this.nadawca = nadawca;
    }

    public boolean hasZabezpieczenia() {
        return zabezpieczenia;
    }

    public void setZabezpieczenia(boolean zabezpieczenia) {
        this.zabezpieczenia = zabezpieczenia;
    }

    public double getWagaBrutto() {
        return wagaBrutto;
    }

    public void setWagaBrutto(double wagaBrutto) {
        this.wagaBrutto = wagaBrutto;
    }

    public int getLiczbaMiejscSiedzacych() {
        return liczbaMiejscSiedzacych;
    }

    public void setLiczbaMiejscSiedzacych(int liczbaMiejscSiedzacych) {
        this.liczbaMiejscSiedzacych = liczbaMiejscSiedzacych;
    }
}