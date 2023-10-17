public class WagonNaMateriałyWybuchowe extends WagonTowarowyCiężki {
    private boolean wzmocnioneSciany;
    private boolean specjalneOznaczenia;

    public WagonNaMateriałyWybuchowe(String nadawca, boolean zabezpieczenia, double wagaBrutto, boolean wzmocnienieScian, double dodatkowaŁadownosc, boolean wzmocnioneSciany, boolean specjalneOznaczenia) {
        super(nadawca, zabezpieczenia, wagaBrutto, wzmocnienieScian, dodatkowaŁadownosc);
        this.wzmocnioneSciany=wzmocnioneSciany;
        this.specjalneOznaczenia=specjalneOznaczenia;

    }
    @Override
    public String toString() {
        return "Wagon na materiały wybuchowe: " +
                "wzmocnienie ścian=" + hasWzmocnienieScian() +
                ", specjalne oznaczenia=" + specjalneOznaczenia +
                ", " + super.toString();
    }

    public boolean hasWzmocnienieScian() {
        return wzmocnioneSciany;
    }






}