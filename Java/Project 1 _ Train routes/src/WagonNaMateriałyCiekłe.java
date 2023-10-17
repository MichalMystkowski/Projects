public class WagonNaMateriałyCiekłe extends WagonTowarowyPodstawowy {

    private String nazwaPłynu;
    private int pojemność;


    public WagonNaMateriałyCiekłe(String nadawca, boolean zabezpieczenia, double wagaBrutto, double szerokość, double wysokość, String nazwaPłynu, double pojemnosc) {
        super(nadawca, zabezpieczenia, wagaBrutto, szerokość, wysokość);
        this.nazwaPłynu=nazwaPłynu;
        this.pojemność=pojemność;
    }
    public String toString() {
        return "Wagon na materiały ciekłe:"
                + "  nazwa płynu: " + nazwaPłynu + ""
                + "  pojemność: " + pojemność + ""
                + "  szerokość: " + getSzerokość() + ""
                + "  wysokość: " + getWysokość() + ""
                + "  waga brutto: " + getWagaBrutto() + ""
                + "  zabezpieczenia: " + (hasZabezpieczenia() ? "tak" : "nie") + "";
    }
}