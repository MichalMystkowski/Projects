public class WagonNaCiekłeMateriałyToksyczne extends WagonTowarowyCiężki {

    private String nazwaPłynu;
    private int pojemność;
    private boolean czyPowłokaOchronna;
    private double maxCzasTransportu;

    public WagonNaCiekłeMateriałyToksyczne(String nadawca, boolean zabezpieczenia, double wagaBrutto, boolean wzmocnienieScian, double dodatkowaŁadownosc,String nazwaPłynu, double pojemnosc,double maxCzasTransportu, boolean czyPowłokaOchronna) {
        super(nadawca, zabezpieczenia, wagaBrutto, wzmocnienieScian, dodatkowaŁadownosc);
        this.nazwaPłynu=nazwaPłynu;
        this.pojemność=pojemność;
        this.czyPowłokaOchronna=czyPowłokaOchronna;
        this.maxCzasTransportu=maxCzasTransportu;


    }
    @Override
    public String toString() {
        return "Wagon na ciekłe materiały toksyczne o nazwie płynu " + nazwaPłynu + " i pojemności " + pojemność + " litrów, z " + (czyPowłokaOchronna ? "" : "brakiem ") + "powłoką ochronną, który może być transportowany przez maksymalny czas " + maxCzasTransportu + " godzin. " + super.toString();
    }





}