--1 Podaj ca³kowit¹ kwotê ubezpieczeñ dla danego ID rodzaju ubezpieczenia
CREATE PROCEDURE GetTotalInsuranceAmount
    @InsuranceID int,
    @TotalAmount numeric(12,2) OUTPUT
AS
BEGIN
    SELECT @TotalAmount = SUM(Kwota) FROM Ubezpieczenie WHERE Rodzaj_ubezpieczenia_ID = @InsuranceID;
END


-- Wywo³anie procedury
DECLARE @Amount numeric(12,2);
EXEC GetTotalInsuranceAmount @InsuranceID = 2, @TotalAmount = @Amount OUTPUT;
SELECT @Amount AS TotalAmount;

--2 Wyzwalacz zmieniaj¹cy kwotê ubezpieczenia na 10% wartoœci rynkowej auta po aktualizacji kolumny wartoœæ rynkowa w tabeli Samochod
CREATE TRIGGER UpdateInsuranceValue
ON Samochod
AFTER UPDATE
AS
BEGIN
    IF UPDATE(Wartosc_rynkowa)
    BEGIN
        UPDATE U
        SET Kwota = S.Wartosc_rynkowa * 0.1
        FROM Ubezpieczenie U
        INNER JOIN inserted I ON U.Samochod_ID = I.ID
        INNER JOIN Samochod S ON S.ID = I.ID;
    END
END


--Sprawdzenie dzia³ania
UPDATE Samochod SET Wartosc_rynkowa = 200000 WHERE ID = 7;


SELECT * FROM Ubezpieczenie;


--3 Funkcja wyœwietlaj¹ca dane o samochodzie wy³¹cznie po modelu z dodanym nowym tabelarycznym typem danych oraz konstrukcj¹ CTE definiuj¹ca tymczasowy wynik wewn¹trz zapytania
CREATE FUNCTION GetCarsByModel
(
    @ModelName varchar(30)
)
RETURNS @CarsTable TABLE
(
    ID int,
    Rok_produkcji int,
    Moc_silnika numeric(6,1),
    Wartosc_rynkowa numeric(15,2)
)
AS
BEGIN;
	WITH CTE AS (
        SELECT S.ID, S.Rok_produkcji, S.Moc_silnika, S.Wartosc_rynkowa
        FROM Samochod S
        INNER JOIN Model M ON S.Model_ID = M.ID
        WHERE M.nazwa = @ModelName
    )
    INSERT INTO @CarsTable
    SELECT *
    FROM CTE;

    RETURN;
END

-- U¿ycie funkcji GetCarsByModel jako SELECT
SELECT * FROM GetCarsByModel('RX-8');


-- Dodatkowa procedura wywo³uj¹ca funkcjê GetCarsByModel
CREATE PROCEDURE CallGetCarsByModel
    @ModelName varchar(30)
AS
BEGIN
    DECLARE @Results TABLE
    (
        ID int,
        Rok_produkcji int,
        Moc_silnika numeric(6,1),
        Wartosc_rynkowa numeric(15,2)
    )

    INSERT INTO @Results
    SELECT * FROM GetCarsByModel(@ModelName)

  
    SELECT * FROM @Results;
END;

--Wywo³anie
EXEC CallGetCarsByModel 'RX-8';
