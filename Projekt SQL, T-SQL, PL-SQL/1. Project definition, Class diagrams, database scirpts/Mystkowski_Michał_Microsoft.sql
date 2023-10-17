CREATE TABLE Adres (
    ID int  NOT NULL,
    Miasto varchar(30)  NOT NULL,
    Ulica varchar(30)  NOT NULL,
    Numer int  NOT NULL,
    CONSTRAINT Adres_pk PRIMARY KEY  (ID)
);

-- Table: Klient_firma
CREATE TABLE Klient_firma (
    ID int  NOT NULL,
    Numer_REGON numeric(12,0)  NOT NULL,
    Adres_ID int  NOT NULL,
    CONSTRAINT Klient_firma_pk PRIMARY KEY  (ID)
);

-- Table: Klient_osoba_fizyczna
CREATE TABLE Klient_osoba_fizyczna (
    ID int  NOT NULL,
    Imie varchar(30)  NOT NULL,
    Nazwisko varchar(30)  NOT NULL,
    Numer_tel numeric(9,0)  NOT NULL,
    Adres_ID int  NOT NULL,
    CONSTRAINT Klient_osoba_fizyczna_pk PRIMARY KEY  (ID)
);

-- Table: Model
CREATE TABLE Model (
    ID int  NOT NULL,
    nazwa varchar(30)  NOT NULL,
    CONSTRAINT Model_pk PRIMARY KEY  (ID)
);

-- Table: Rodzaj_ubezpieczenia
CREATE TABLE Rodzaj_ubezpieczenia (
    ID int  NOT NULL,
    Nazwa varchar(30)  NOT NULL,
    CONSTRAINT Rodzaj_ubezpieczenia_pk PRIMARY KEY  (ID)
);

-- Table: Samochod
CREATE TABLE Samochod (
    ID int  NOT NULL,
    Rok_produkcji int  NOT NULL,
    Moc_silnika numeric(6,1)  NOT NULL,
    Model_ID int  NOT NULL,
    Wartosc_rynkowa numeric(15,2)  NOT NULL,
    CONSTRAINT Samochod_pk PRIMARY KEY  (ID)
);

-- Table: Ubezpieczenie
CREATE TABLE Ubezpieczenie (
    ID int  NOT NULL,
    Nazwa_ubezpieczyciela varchar(30)  NOT NULL,
    Rodzaj_ubezpieczenia_ID int  NOT NULL,
    Samochod_ID int  NOT NULL,
    Data_rozpoczecia datetime  NOT NULL,
    Data_zakonczenia datetime  NULL,
    Kwota numeric(8,2)  NOT NULL,
    CONSTRAINT Ubezpieczenie_pk PRIMARY KEY  (ID)
);

-- Table: Wypozyczenie
CREATE TABLE Wypozyczenie (
    ID int  NOT NULL,
    OD datetime  NOT NULL,
    DO datetime  NOT NULL,
    Klient_firma_ID int  NULL,
    Klient_osoba_fizyczna_ID int  NULL,
    CONSTRAINT Wypozyczenie_pk PRIMARY KEY  (ID)
);

-- Table: Wypozyczenie_Auta
CREATE TABLE Wypozyczenie_Auta (
    Wypozyczenie_ID int  NOT NULL,
    Samochod_ID int  NOT NULL,
    CONSTRAINT Wypozyczenie_Auta_pk PRIMARY KEY  (Wypozyczenie_ID,Samochod_ID)
);

-- foreign keys
-- Reference: Klient_firma_Adres (table: Klient_firma)
ALTER TABLE Klient_firma ADD CONSTRAINT Klient_firma_Adres
    FOREIGN KEY (Adres_ID)
    REFERENCES Adres (ID);

-- Reference: Klient_osoba_fizyczna_Adres (table: Klient_osoba_fizyczna)
ALTER TABLE Klient_osoba_fizyczna ADD CONSTRAINT Klient_osoba_fizyczna_Adres
    FOREIGN KEY (Adres_ID)
    REFERENCES Adres (ID);

-- Reference: Samochod_Model (table: Samochod)
ALTER TABLE Samochod ADD CONSTRAINT Samochod_Model
    FOREIGN KEY (Model_ID)
    REFERENCES Model (ID);

-- Reference: Ubezpiecznie_Rodzaj (table: Ubezpieczenie)
ALTER TABLE Ubezpieczenie ADD CONSTRAINT Ubezpiecznie_Rodzaj
    FOREIGN KEY (Rodzaj_ubezpieczenia_ID)
    REFERENCES Rodzaj_ubezpieczenia (ID);

-- Reference: Ubezpiecznie_Samochod (table: Ubezpieczenie)
ALTER TABLE Ubezpieczenie ADD CONSTRAINT Ubezpiecznie_Samochod
    FOREIGN KEY (Samochod_ID)
    REFERENCES Samochod (ID);

-- Reference: Wypozyczenie_Auta_S (table: Wypozyczenie_Auta)
ALTER TABLE Wypozyczenie_Auta ADD CONSTRAINT Wypozyczenie_Auta_S
    FOREIGN KEY (Samochod_ID)
    REFERENCES Samochod (ID);

-- Reference: Wypozyczenie_Auta_Wypozyczenie (table: Wypozyczenie_Auta)
ALTER TABLE Wypozyczenie_Auta ADD CONSTRAINT Wypozyczenie_Auta_Wypozyczenie
    FOREIGN KEY (Wypozyczenie_ID)
    REFERENCES Wypozyczenie (ID);

-- Reference: Wypozyczenie_Klient_firma (table: Wypozyczenie)
ALTER TABLE Wypozyczenie ADD CONSTRAINT Wypozyczenie_Klient_firma
    FOREIGN KEY (Klient_firma_ID)
    REFERENCES Klient_firma (ID);

-- Reference: Wypozyczenie_osoba_f (table: Wypozyczenie)
ALTER TABLE Wypozyczenie ADD CONSTRAINT Wypozyczenie_osoba_f
    FOREIGN KEY (Klient_osoba_fizyczna_ID)
    REFERENCES Klient_osoba_fizyczna (ID);





-- ENCJA MODEL
insert into Model (ID, nazwa) values (1, 'Rally Wagon 3500');
insert into Model (ID, nazwa) values (2, 'LS');
insert into Model (ID, nazwa) values (3, 'C-Class');
insert into Model (ID, nazwa) values (4, 'Amanti');
insert into Model (ID, nazwa) values (5, '458 Italia');
insert into Model (ID, nazwa) values (6, 'M5');
insert into Model (ID, nazwa) values (7, '190E');
insert into Model (ID, nazwa) values (8, 'Magnum');
insert into Model (ID, nazwa) values (9, 'SC');
insert into Model (ID, nazwa) values (10, 'Patriot');
insert into Model (ID, nazwa) values (11, '5 Series');
insert into Model (ID, nazwa) values (12, 'Tercel');
insert into Model (ID, nazwa) values (13, '911');
insert into Model (ID, nazwa) values (14, 'SC');
insert into Model (ID, nazwa) values (15, 'M5');
insert into Model (ID, nazwa) values (16, 'Coachbuilder');
insert into Model (ID, nazwa) values (17, 'Thunderbird');
insert into Model (ID, nazwa) values (18, 'Grand Am');
insert into Model (ID, nazwa) values (19, 'Armada');
insert into Model (ID, nazwa) values (20, 'Monterey');
insert into Model (ID, nazwa) values (21, 'Evora');
insert into Model (ID, nazwa) values (22, 'GT-R');
insert into Model (ID, nazwa) values (23, 'Starion');
insert into Model (ID, nazwa) values (24, 'RX-8');
insert into Model (ID, nazwa) values (25, 'E-Class');

-- ENCJA SAMOCHÓD
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (1, 1995, 789, 1, 1584442);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (2, 2003, 1214,2, 497268);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (3, 2011, 1381,3, 1421410);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (4, 1993, 1051,4, 1113595);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (5, 2011, 1104,5, 1755599);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (6, 1996, 687,6, 573021);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (7, 2002, 528,7, 224791);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (8, 1986, 614,8, 1364119);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (9, 1992, 881,9, 472887);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (10, 1992, 530,10, 305226);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (11, 2008, 456,11, 468787);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (12, 2011, 1307,12, 826549);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (13, 2010, 1348,13, 452984);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (14, 1991, 301,14, 956763);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (15, 1999, 1060,15, 1641367);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values(16, 1994, 1132,16, 1632692);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (17, 1964, 1186,17, 1750123);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (18, 2000, 699,18, 1491411);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (19, 2010, 947,19, 160707);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (20, 1993, 869,20, 1638043);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (21, 1994, 725,21, 324056);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (22, 2005, 713,22, 709724);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (23, 1998, 357,23, 632843);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (24, 2005, 736,24, 207151);
insert into Samochod (ID, Rok_produkcji, Moc_silnika, Model_ID, Wartosc_rynkowa) values (25, 2008, 469,25, 684548);


--ENCJA Rodzaj_ubezpiecznia

insert into Rodzaj_ubezpieczenia (ID, Nazwa) values (1, 'Od kradzie¿y');
insert into Rodzaj_ubezpieczenia (ID, Nazwa) values (2, 'Od powodzi');
insert into Rodzaj_ubezpieczenia (ID, Nazwa) values (3, 'Ubezpieczenie szyb');
insert into Rodzaj_ubezpieczenia (ID, Nazwa) values (4, 'Ubezpieczenie opon');

--Encja Ubezpieczenie

insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (1, 'Stiedemann-Wyman', 2, 1, '2022-09-09 09:14:14', '2024-01-03 21:31:54', 9072);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (2, 'Aufderhar LLC', 2, 2, '2022-11-11 21:29:44', '2024-01-23 02:38:59', 5163);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (3, 'Emard and Sons', 4, 3, '2022-11-27 06:09:15', '2023-05-15 18:12:10', 9866);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (4, 'Ortiz, Haag and Leannon', 2, 4, '2023-01-24 12:27:31', null, 7429);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (5, 'Doyle-Bogisich', 2, 5, '2022-07-06 04:21:42', '2024-04-07 02:39:32', 4887);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (6, 'Torphy-Monahan', 4, 6, '2022-09-21 09:48:48', '2023-08-01 00:50:28', 8270);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (7, 'Kovacek, Mraz and Bogisich', 2, 7, '2022-05-17 07:53:53', '2023-11-08 11:17:45', 7053);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (8, 'Thiel-Macejkovic', 4, 8, '2023-04-20 02:44:03', '2024-03-28 22:41:49', 5503);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (9, 'Fisher, Bosco and Bauch', 1, 9, '2022-10-06 02:11:36', '2024-03-03 20:18:18', 6686);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (10, 'Thiel and Sons', 1, 10, '2023-03-31 13:31:21', '2024-02-28 18:44:02', 13316);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (11, 'Wolff and Sons', 1, 11, '2022-05-28 07:07:37', '2023-10-02 23:01:14', 2721);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (12, 'Homenick Inc', 2, 12, '2023-02-02 11:23:46', '2024-03-30 15:16:17', 6329);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (13, 'Hoppe and Sons', 4, 13, '2022-06-17 19:17:57', '2023-10-14 13:39:01', 5010);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (14, 'Schaefer Inc', 4, 14, '2022-08-26 07:49:34', null, 5870);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (15, 'Denesik and Sons', 2, 15, '2022-06-19 06:31:37', '2023-10-16 21:32:05', 12174);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (16, 'Lockman-Feil', 2, 16, '2023-01-16 11:23:15', '2024-02-16 16:25:27', 12191);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (17, 'Stokes-Hettinger', 4, 17, '2023-01-20 10:45:19', '2023-11-10 21:03:15', 2784);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (18, 'Conroy and Sons', 3, 18, '2023-01-17 02:18:24', '2023-09-04 12:27:02', 10433);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (19, 'Doyle Inc', 1, 19, '2022-05-09 04:25:40', '2024-01-22 09:41:31', 4662);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (20, 'Larson Inc', 2, 20, '2023-04-08 08:55:52', null, 4139);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (21, 'Bartoletti Inc', 3, 21, '2023-03-04 04:49:48', '2023-07-09 17:39:27', 2410);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (22, 'Baumbach-Veum', 1, 22, '2023-02-22 13:37:45', '2023-09-13 21:57:01', 4405);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (23, 'Friesen Inc', 4, 23, '2023-02-13 18:12:41', '2023-11-18 06:03:18', 14645);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (24, 'Daugherty and Sons', 4, 24, '2022-07-15 21:28:44', '2023-11-08 14:19:51', 4195);
insert into Ubezpieczenie (ID, Nazwa_ubezpieczyciela, Rodzaj_ubezpieczenia_ID, Samochod_ID, Data_rozpoczecia, Data_zakonczenia, Kwota) values (25, 'Dicki, Krajcik and Ortiz', 1, 25, '2022-08-25 16:55:18', '2024-01-22 11:10:36', 4422);




--Encja Adres
insert into Adres (ID, Miasto, Ulica, Numer) values (1, 'Osasco', 'Corscot', 368);
insert into Adres (ID, Miasto, Ulica, Numer) values (2, 'Bolyarovo', 'Haas', 79);
insert into Adres (ID, Miasto, Ulica, Numer) values (3, 'Amagasaki', 'Mifflin', 918);
insert into Adres (ID, Miasto, Ulica, Numer) values (4, 'Ängelholm', 'Reindahl', 826);
insert into Adres (ID, Miasto, Ulica, Numer) values (5, 'Alapayevsk', 'Kipling', 8);
insert into Adres (ID, Miasto, Ulica, Numer) values (6, 'Pengbu', 'Mallory', 91);
insert into Adres (ID, Miasto, Ulica, Numer) values (7, 'Q?r Mo?v', 'Debra', 668);
insert into Adres (ID, Miasto, Ulica, Numer) values (8, 'Dhar?n B?z?r', 'Sheridan', 895);
insert into Adres (ID, Miasto, Ulica, Numer) values (9, 'Maunatlala', 'Bluestem', 603);
insert into Adres (ID, Miasto, Ulica, Numer) values (10, 'Rydu³towy', 'Milwaukee', 791);
insert into Adres (ID, Miasto, Ulica, Numer) values (11, 'Stroitel’', 'Delladonna', 297);
insert into Adres (ID, Miasto, Ulica, Numer) values (12, 'Khashuri', 'High Crossing', 787);
insert into Adres (ID, Miasto, Ulica, Numer) values (13, 'Noginsk-9', 'Harbort', 486);
insert into Adres (ID, Miasto, Ulica, Numer) values (14, 'Roermond', 'Kennedy', 336);
insert into Adres (ID, Miasto, Ulica, Numer) values (15, 'Seso', 'Oneill', 246);
insert into Adres (ID, Miasto, Ulica, Numer) values (16, 'Jiangwan', 'Northview', 824);
insert into Adres (ID, Miasto, Ulica, Numer) values (17, 'Bira', 'Helena', 498);
insert into Adres (ID, Miasto, Ulica, Numer) values (18, 'Karangbenda', 'Sundown', 354);
insert into Adres (ID, Miasto, Ulica, Numer) values (19, 'José de Freitas', 'Rowland', 594);
insert into Adres (ID, Miasto, Ulica, Numer) values (20, 'Le Chambon-Feugerolles', 'Eggendart', 798);
insert into Adres (ID, Miasto, Ulica, Numer) values (21, 'Lidingö', 'Amoth', 232);
insert into Adres (ID, Miasto, Ulica, Numer) values (22, 'Plovdiv', 'Jenna', 614);
insert into Adres (ID, Miasto, Ulica, Numer) values (23, 'Limoges', 'Badeau', 435);
insert into Adres (ID, Miasto, Ulica, Numer) values (24, 'Al Jubayl', 'Lakewood', 712);
insert into Adres (ID, Miasto, Ulica, Numer) values (25, 'Lanchyn', 'Merry', 413);





--Encja Klient_Osoba_Fizyczna
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (1, 'Stearne', 'Chinge', 800118851, 1);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (2, 'Ketty', 'Dive', 266164950, 2);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (3, 'Georgetta', 'Philpotts', 509149672, 3);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (4, 'Matthus', 'Smithers', 286679692, 4);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (5, 'Harry', 'Deetlefs', 174257465, 5);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (6, 'Pennie', 'Travers', 179618354, 6);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (7, 'Gaby', 'Paulat', 668058227, 7);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (8, 'Nessa', 'Fines', 765611952, 8);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (9, 'Enrica', 'Vinten', 326123420, 9);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (10, 'Kristian', 'Crippin', 753555235, 10);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (11, 'Niles', 'Cancelier', 400042503, 11);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (12, 'Kienan', 'Valentinuzzi', 585768788, 12);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (13, 'Towny', 'Kynder', 223947249, 13);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (14, 'Gasper', 'Songist', 942675539, 14);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (15, 'Shirley', 'Ritzman', 714909133, 15);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (16, 'Ellissa', 'Digg', 256901847, 16);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (17, 'Berke', 'Jessope', 801434926, 17);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (18, 'Karyn', 'Nicklinson', 628720627, 18);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (19, 'Dionisio', 'Moehle', 398618889, 19);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (20, 'Hartley', 'Sunner', 736505293, 20);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (21, 'Carlie', 'Stembridge', 841506261, 21);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (22, 'Claudina', 'Nellen', 964852518, 22);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (23, 'Dougy', 'Callam', 898205473, 23);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (24, 'Delores', 'Gainsford', 873330526, 24);
insert into Klient_osoba_fizyczna (ID, Imie, Nazwisko, Numer_tel, Adres_ID) values (25, 'Gayle', 'Conisbee', 252627242, 25);




--Encja Klient_firma
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (1, 674257936, 1);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (2, 648197918, 2);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (3, 100206569, 3);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (4, 689917054, 4);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (5, 965806300, 5);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (6, 825769978, 6);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (7, 256160821, 7);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (8, 674826439, 8);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (9, 596148865, 9);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (10, 396512011, 10);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (11, 566121909, 11);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (12, 150991106, 12);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (13, 640267278, 13);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (14, 617197174, 14);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (15, 576943330, 15);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (16, 643032460, 16);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (17, 738846303, 17);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (18, 640656797, 18);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (19, 447083480, 19);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (20, 218843324, 20);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (21, 972662556, 21);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (22, 622031242, 22);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (23, 835756481, 23);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (24, 153186628, 24);
insert into Klient_firma (ID, Numer_REGON, Adres_ID) values (25, 687689897, 25);




--ENCJA wYPOZYCZENIE
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (1, '2023-03-09 19:30:55', '2023-10-06 18:01:40', 1, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (2, '2023-04-01 02:24:51', '2023-09-06 18:01:31', 2, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (3, '2023-03-21 13:32:50', '2023-12-23 14:37:52', null, 3);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (4, '2023-03-29 15:47:20', '2024-02-15 18:55:10', 4, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (5,'2022-09-02 13:41:18', '2023-10-02 08:29:44', null, 5);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (6, '2022-06-22 07:50:15', '2024-01-22 17:53:12', 6, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (7,  '2022-09-09 19:58:07', '2024-01-22 17:53:12', null, 7);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (8, '2022-07-09 14:46:14', '2023-04-23 05:48:27',8, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (9, '2022-07-01 21:42:58', '2023-10-12 19:06:17', null, 9);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (10, '2022-09-26 08:19:04', '2023-09-27 21:34:09', 10, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (11, '2022-11-03 20:58:14', '2024-01-22 10:40:17', null, 11);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (12, '2022-10-10 21:53:24', '2023-05-06 23:02:48', 12, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (13, '2022-08-26 13:48:04', '2024-03-29 23:05:22', null, 13);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (14, '2022-10-22 21:14:29', '2024-02-18 23:17:41', 14, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (15, '2022-06-19 20:38:58', '2023-10-16 11:28:19', null, 15);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (16, '2022-12-01 05:35:04', '2023-11-15 16:39:32', 16, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (17, '2022-06-15 19:44:26', '2024-01-08 05:53:51', null, 17);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (18, '2022-04-27 22:24:36','2024-03-29 23:05:22', 18, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (19, '2022-06-26 14:10:30', '2024-03-29 23:05:22', null, 19);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (20, '2022-11-10 05:49:21', '2023-09-13 00:49:35', 20, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (21, '2022-06-15 14:22:02', '2024-02-29 22:53:24', null, 21);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (22, '2022-08-28 00:51:15', '2024-04-04 18:09:22', 22, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (23, '2022-05-26 21:17:25', '2023-10-24 09:20:34', null, 23);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (24, '2023-01-14 12:57:13', '2023-09-29 07:48:26', 24, null);
insert into Wypozyczenie (ID, OD, DO, Klient_firma_ID, Klient_osoba_fizyczna_ID) values (25, '2022-09-02 19:37:20', '2024-01-27 23:56:29', null, 25);

--ENCJA =WYPOZYCZENIE_AUTA
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (1, 1);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (2, 2);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (3, 3);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (4, 4);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (5, 5);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (6, 6);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (7, 7);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (8, 8);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (9, 9);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (10, 10);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (11, 11);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (12, 12);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (13, 13);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (14, 14);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (15, 15);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (16, 16);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (17, 17);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (18, 18);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (19, 19);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (20, 20);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (21, 21);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (22, 22);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (23, 23);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (24, 24);
insert into Wypozyczenie_Auta (Wypozyczenie_ID, Samochod_ID) values (25, 25);


--Zapytania wyœwietlaj¹ce wszystkie dane z poszczególnych tabel:

SELECT * FROM Adres;
SELECT * FROM Klient_firma;
SELECT * FROM Klient_osoba_fizyczna;
SELECT * FROM Model;
SELECT * FROM Rodzaj_ubezpieczenia;
SELECT * FROM Samochod;
SELECT * FROM Ubezpieczenie;
SELECT * FROM Wypozyczenie;
SELECT * FROM Wypozyczenie_auta;



--Proste zapytanie SELECT, które zwróci nazwy miast z tabeli Adres:
SELECT Miasto FROM Adres;



--Podzapytanie, które zwróci informacje z tablicy Samochod o samochodach, których modele zaczynaj¹ siê na literê "A"
SELECT *
FROM Samochod
WHERE Model_ID IN (
    SELECT ID
    FROM Model
    WHERE nazwa LIKE 'A%'
);

--Zapytanie z agregacj¹, które zwróci liczbê samochodów, które maj¹ moc silnika wiêksz¹ ni¿ 700 KM:

SELECT COUNT(*) FROM Samochod WHERE Moc_silnika > 700;



--Polecenie UPDATE, które zaktualizuje rok produkcji samochodu o ID równym 1:

UPDATE Samochod SET Rok_produkcji = 2022 WHERE ID = 1;



--Polecenie DELETE, które usunie ubezpieczenie o ID równym 3:

DELETE FROM Ubezpieczenie WHERE ID = 3;



