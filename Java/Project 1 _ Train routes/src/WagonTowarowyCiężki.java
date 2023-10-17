public class WagonTowarowyCiężki extends Wagon {

    private boolean wzmocnienieScian;
    private double dodatkowaŁadownosc;

    public WagonTowarowyCiężki(String nadawca, boolean zabezpieczenia, double wagaBrutto, boolean wzmocnienieScian, double dodatkowaŁadownosc ) {
        super(nadawca, zabezpieczenia, wagaBrutto);
        this.dodatkowaŁadownosc=dodatkowaŁadownosc;
        this.wzmocnienieScian=wzmocnienieScian;
    }
    public double getDodatkowaŁadowność() {
        return dodatkowaŁadownosc;
    }

    public boolean hasWzmocnienieScian() {
        return wzmocnienieScian;
    }

    @Override
    public String toString() {
        return "Wagon towarowy ciężki: " +
                "wzmocnienie ścian=" + wzmocnienieScian +
                ", dodatkowa ładowność=" + dodatkowaŁadownosc +
                ", " + super.toString();
    }





}