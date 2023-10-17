public class WagonTowarowyPodstawowy extends Wagon {

    private double wysokość;
    private double szerokość;


    public WagonTowarowyPodstawowy(String nadawca, boolean zabezpieczenia, double wagaBrutto, double szerokość, double wysokość) {
        super(nadawca, zabezpieczenia, wagaBrutto);
        this.wysokość=wysokość;
        this.szerokość=szerokość;
    }
    public double getSzerokość() {
        return szerokość;
    }

    public double getWysokość() {
        return wysokość;
    }
    @Override
    public String toString() {
        return "WagonTowarowyPodstawowy{" +
                "nadawca='" + super.getNadawca() + '\'' +
                ", zabezpieczenia=" + super.hasZabezpieczenia() +
                ", wagaBrutto=" + super.getWagaBrutto() +
                ", szerokość=" + szerokość +
                ", wysokość=" + wysokość +
                '}';
    }
}