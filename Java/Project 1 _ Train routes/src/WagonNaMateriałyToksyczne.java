public class WagonNaMateriałyToksyczne extends WagonTowarowyCiężki {
    private String materiał;
    private int LiczbaFiltrówPowietrza;


    public WagonNaMateriałyToksyczne(String nadawca, boolean zabezpieczenia, double wagaBrutto, boolean wzmocnienieScian, double dodatkowaŁadownosc, int liczbaFiltrówPowietrza, String materiał) {
        super(nadawca, zabezpieczenia, wagaBrutto, wzmocnienieScian, dodatkowaŁadownosc);
        this.materiał=materiał;
        this.LiczbaFiltrówPowietrza=liczbaFiltrówPowietrza;

    }
    public String toString() {
        return "WagonNaMateriałyToksyczne [materiał=" + materiał + ", liczba filtrów powietrza=" + LiczbaFiltrówPowietrza + ", nadawca=" + getNadawca() + ", zabezpieczenia=" + hasZabezpieczenia() + ", waga brutto=" + getWagaBrutto() + ", wzmocnienie ścian=" + hasWzmocnienieScian() + ", dodatkowa ładowność=" + getDodatkowaŁadowność() + "]";
    }







}