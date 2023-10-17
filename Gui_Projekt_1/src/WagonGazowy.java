public class WagonGazowy extends WagonTowarowyPodstawowy {

    private String rodzajGazu;
    private int strefaZagozeniaWybuchem;


    public WagonGazowy(String nadawca, boolean zabezpieczenia, double wagaBrutto, double szerokość, double wysokość, String rodzajGazu, int strefaZagozeniaWybuchem) {
        super(nadawca, zabezpieczenia, wagaBrutto, szerokość, wysokość);
        this.strefaZagozeniaWybuchem=strefaZagozeniaWybuchem;
        this.rodzajGazu=rodzajGazu;
    }
    public String toString() {
        return "WagonGazowy{" +
                "nadawca='" + super.getNadawca() + '\'' +
                ", zabezpieczenia=" + super.hasZabezpieczenia() +
                ", wagaBrutto=" + super.getWagaBrutto() +
                ", szerokość=" + super.getSzerokość() +
                ", wysokość=" + super.getWysokość() +
                ", rodzajGazu='" + rodzajGazu + '\'' +
                ", strefaZagozeniaWybuchem=" + strefaZagozeniaWybuchem +
                '}';
    }
}