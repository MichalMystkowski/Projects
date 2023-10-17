public class PolaczenieStacji {

    public PolaczenieStacji(Stacja poczatek, Stacja koniec, double dlugosc, boolean finalna) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.dlugosc = dlugosc;
        this.finalna = finalna;
    }

    public PolaczenieStacji(Stacja poczatek, Stacja koniec, double dlugosc) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.dlugosc = dlugosc;
    }

    private Stacja poczatek;
    private Stacja koniec;
    private double dlugosc;
    private boolean finalna = false;
    public Stacja getPoczatek() {
        return poczatek;
    }

    public void setPoczatek(Stacja poczatek) {
        this.poczatek = poczatek;
    }

    public Stacja getKoniec() {
        return koniec;
    }

    public void setKoniec(Stacja koniec) {
        this.koniec = koniec;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    public boolean getFinalna(){
        return finalna;
    }
}
