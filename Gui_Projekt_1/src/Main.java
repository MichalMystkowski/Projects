import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);

        int wybranaOpcja = 0;
        Logistyka logistyka = new Logistyka();

        inicjalizacjaDanych(logistyka);

        while (wybranaOpcja != 10) {
            System.out.println("1. Stwórz nową lokomotywę");
            System.out.println("2. Stwórz nowy wagon");
            System.out.println("3. Stwórz nową stację kolejową");
            System.out.println("4. Stwórz nowe połączenie między stacjami");
            System.out.println("5. Przypisz wagon do lokomotywy");
            System.out.println("6. Wyśwetl listę lokomotyw");
            System.out.println("7. Wyśwetl listę stacji");
            System.out.println("8. Wyślij lokomotywe w trase");
            System.out.println("9. Uruchom symulacje");
            System.out.println("10. Wyjdź z programu");
            System.out.print("Wybierz opcję: ");
            wybranaOpcja = Integer.parseInt(skaner.nextLine());
            switch (wybranaOpcja) {
                case 1:
                    System.out.println("Podaj maksymalną liczbę wagonów:");
                    int maksLiczbaWagonow = Integer.parseInt(skaner.nextLine());
                    skaner.nextLine();

                    System.out.println("Podaj maksymalną wagę ładunku:");
                    int maksWagaLadunku = Integer.parseInt(skaner.nextLine());
                    skaner.nextLine();

                    System.out.println("Podaj liczbę wagonów elektrycznych:");
                    int liczbaWagonowElektrycznych = Integer.parseInt(skaner.nextLine());
                    skaner.nextLine();

                    System.out.println("Podaj nazwę:");
                    String nazwa = skaner.nextLine();

                    System.out.println("Podaj prędkość:");
                    int predkosc = Integer.parseInt(skaner.nextLine());
                    skaner.nextLine();

                    Lokomotywa lokomotywa = new Lokomotywa(maksLiczbaWagonow, maksWagaLadunku,
                            liczbaWagonowElektrycznych, nazwa, predkosc);
                    logistyka.dodajLokomotywe(lokomotywa);
                    System.out.println("Utworzono nową lokomotywę o numerze identyfikacyjnym " + lokomotywa.getNumerIdentyfikacyjny());
                    break;

                case 2: {
                    System.out.println("Podaj nadawce:");
                    String nadawca = skaner.nextLine();
                    skaner.nextLine();

                    System.out.println("Czy zabezpieczenia (t/n):");
                    String zabezpieczenia = skaner.nextLine();

                    System.out.println("Podaj wage wagonu:");
                    int waga = Integer.parseInt(skaner.nextLine());

                    final String[] typy = { "BAGAZOWO_POCZTOWY", "WAGON_CHLODNICZY", "WAGON_GAZOWY", "WAGON_NA_CIEKLE_MATERIALY_TOKSYCZNE",
                            "WAGON_NA_MATERIALY_CIEKLE", "WAGON_NA_MATERIALY_TOKSYCZNE", "WAGON_NA_MATERIALY_WYBUCHOWE", "WAGON_PASAZERSKI",
                            "WAGON_POCZTOWY", "WAGON_RESTAURACYJNY", "WAGON_TOWAROWY_CIEZKI", "WAGON_TOWAROWY_PODSTAWOWY"};


                    System.out.println("Dostępne typy wagonów:");
                    System.out.println(Arrays.asList(typy));

                    System.out.println("Podaj typ wagonu:");
                    String wybrany_typ = skaner.nextLine();

                    Wagon wagon = null;

                    boolean zab = zabezpieczenia.equals("t");

                    switch (wybrany_typ)
                    {
                        case "BAGAZOWO_POCZTOWY":
                        {
                            int maxLiczbaBagazy, maxLiczbaPrzesylek;

                            System.out.println("Max liczba bagaży:");
                            maxLiczbaBagazy = Integer.parseInt(skaner.nextLine());

                            System.out.println("Max liczba przesyłek:");
                            maxLiczbaPrzesylek = Integer.parseInt(skaner.nextLine());

                            wagon = new WagonBagazowoPocztowy(nadawca, zab, waga, maxLiczbaBagazy, maxLiczbaPrzesylek);
                            break;
                        }
                        case "WAGON_CHLODNICZY":
                        {
                            int szerokosc, wysokosc, temp;
                            String zawartosc;
                            System.out.println("Szerokosc:");
                            szerokosc = Integer.parseInt(skaner.nextLine());

                            System.out.println("Wysokosc:");
                            wysokosc = Integer.parseInt(skaner.nextLine());

                            System.out.println("Temperatura:");
                            temp = Integer.parseInt(skaner.nextLine());

                            System.out.println("Zawartość:");
                            zawartosc = skaner.nextLine();

                            wagon = new WagonChłodniczy(nadawca, zab, waga, szerokosc, wysokosc, temp, zawartosc);
                            break;
                        }
                        case "WAGON_GAZOWY":
                        {
                            break;
                        }
                        case "WAGON_NA_CIEKLE_MATERIALY_TOKSYCZNE":
                        {
                            break;
                        }
                        case "WAGON_NA_MATERIALY_CIEKLE":
                        {
                            break;
                        }
                        case "WAGON_NA_MATERIALY_TOKSYCZNE":
                        {
                            break;
                        }
                        case "WAGON_NA_MATERIALY_WYBUCHOWE":
                        {
                            break;
                        }
                        case "WAGON_PASAZERSKI":
                        {
                            break;
                        }
                        case "WAGON_POCZTOWY":
                        {
                            break;
                        }
                        case "WAGON_RESTAURACYJNY":
                        {
                            break;
                        }
                        case "WAGON_TOWAROWY_CIEZKI":
                        {
                            break;
                        }
                        case "WAGON_TOWAROWY_PODSTAWOWY":
                        {
                            break;
                        }
                    }

                    if(wagon == null)
                    {
                        wagon = new WagonTowarowyPodstawowy(nadawca,zab,waga,10,10);
                    }

                    logistyka.dodajWagon(wagon);
                    System.out.println("Utworzono nowy wagon o numerze identyfikacyjnym " + wagon.getId());
                }
                break;
                case 3:
                    System.out.println("Podaj nazwe stacji:");
                    String nazwaStacji = skaner.nextLine();
                    Stacja stacja = new Stacja(nazwaStacji);
                    logistyka.dodajStacje(stacja);
                    System.out.println("Stworzono nową stacje o id " + stacja.getId());
                    break;
                case 4:
                    String id1, id2;
                    System.out.println("Podaj id pierwszej stacji:");
                    id1 = skaner.nextLine();
                    System.out.println("Podaj id drugiej stacji:");
                    id2 = skaner.nextLine();

                    Stacja st1 = logistyka.pobierzStacje(id1);
                    Stacja st2 = logistyka.pobierzStacje(id2);

                    st1.dodajPolaczenie(st2);
                    st2.dodajPolaczenie(st1);

                    System.out.println("Dodano połączenie pomiędzy stacjami " + id1 + " oraz " + id2);
                    break;
                case 5:
                    System.out.println("Podaj id lokomotywy:");
                    int id = Integer.parseInt(skaner.nextLine());

                    System.out.println("Podaj id wagonu:");
                    String idwag = skaner.nextLine();

                    logistyka.uzyjWagonu(idwag, id);
                    System.out.println("Dołączono wagon do lokomotywy!");
                    break;
                case 6:
                    logistyka.wyswietlListeLokomotyw();
                    break;
                case 7:
                    logistyka.wyswietlListeStacji();
                    break;
                case 8:

                    int id_lok;
                    System.out.println("Podaj id lokomotywy:");
                    id_lok = Integer.parseInt(skaner.nextLine());
                    Lokomotywa lok = logistyka.uzyjLokomotywy(id_lok);

                    String ids1, ids2;
                    System.out.println("Podaj id stacji wyjazdu:");
                    ids1 = skaner.nextLine();
                    System.out.println("Podaj id stacji docelowej:");
                    ids2 = skaner.nextLine();

                    Stacja sts1 = logistyka.pobierzStacje(ids1);
                    Stacja sts2 = logistyka.pobierzStacje(ids2);

                    ArrayList<PolaczenieStacji> polaczenia = logistyka.zbudujMapePolaczenDlaPodanychDestynacji(sts1, sts2);

                    Trasa trasa = new Trasa(lok, polaczenia);
                    logistyka.dodajTrase(trasa);
                    break;
                case 9:
                    logistyka.uruchomSymulacje();
                    break;
                case 10:
                    System.out.println("Zakończono działanie aplikacji");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
                    break;
            }
        }

        skaner.close();
    }



    private static void inicjalizacjaDanych(Logistyka logistyka) {


        Random r = new Random();
        for(int i = 1; i <= 25; i++)
        {
            Stacja stacja1 = new Stacja("X"+i);
            Stacja stacja2 = new Stacja("B"+i);
            stacja1.dodajPolaczenie(stacja2);
            stacja2.dodajPolaczenie(stacja1);
            Stacja stacja3 = new Stacja("Z"+i);
            stacja2.dodajPolaczenie(stacja3);
            stacja3.dodajPolaczenie(stacja2);
            Stacja stacja4 = new Stacja("W"+i);
            stacja3.dodajPolaczenie(stacja4);
            stacja4.dodajPolaczenie(stacja3);
            Stacja stacja5 = new Stacja("E"+i);
            stacja4.dodajPolaczenie(stacja5);
            stacja5.dodajPolaczenie(stacja4);

            Lokomotywa lokomotywa = new Lokomotywa(r.nextInt(10), r.nextInt(10), r.nextInt(10), "TRAIN_"+i, r.nextInt(100,200));
            logistyka.dodajLokomotywe(lokomotywa);
            Trasa trasa = new Trasa(lokomotywa, logistyka.zbudujMapePolaczenDlaPodanychDestynacji(stacja1, stacja5));
            logistyka.dodajTrase(trasa);

            for(int j = 0; j < 10;j++)
            {
                Wagon w = new WagonBagazowoPocztowy("nd1",true, r.nextInt(100), r.nextInt(100), r.nextInt(100));
                logistyka.dodajWagon(w);
                logistyka.uzyjWagonu(w.getId(), lokomotywa.getNumerIdentyfikacyjny());
            }

        }
    }
}
