SET SERVEROUTPUT ON;

-- 1 Kursor iteruj¹cy po adresach i wyœwietlaj¹cy szczegó³y kolejnych adresów
DECLARE
  CURSOR c_adres IS
    SELECT * FROM Adres;
  v_id Adres.ID%TYPE;
  v_miasto Adres.Miasto%TYPE;
  v_ulica Adres.Ulica%TYPE;
  v_numer Adres.Numer%TYPE;
  
  CURSOR c_adres_details(p_id IN Adres.ID%TYPE) IS
    SELECT Miasto, Ulica, Numer FROM Adres WHERE ID = p_id;
  v_adres_details c_adres_details%ROWTYPE;
BEGIN
  OPEN c_adres;
  LOOP
    BEGIN
      FETCH c_adres INTO v_id, v_miasto, v_ulica, v_numer;
      EXIT WHEN c_adres%NOTFOUND;
      DBMS_OUTPUT.PUT_LINE('Adres id: ' || v_id);
      
      OPEN c_adres_details(v_id);
      LOOP
        FETCH c_adres_details INTO v_adres_details;
        EXIT WHEN c_adres_details%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('  Szczegó³y adresu: ' || v_adres_details.Miasto || ', ' || v_adres_details.Ulica || ' ' || v_adres_details.Numer);
      END LOOP;
      CLOSE c_adres_details;
      
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Brak danych.');
      WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Wyst¹pi³ b³¹d: ' || SQLERRM);
    END;
  END LOOP;
  CLOSE c_adres;

END;
/
-- 2 Procedura wyœwietlaj¹ca szczegó³y wybranego adresu
CREATE OR REPLACE PROCEDURE get_adres_details(p_id IN Adres.ID%TYPE) AS
  v_miasto Adres.Miasto%TYPE;
  v_ulica Adres.Ulica%TYPE;
  v_numer Adres.Numer%TYPE;
BEGIN
  SELECT Miasto, Ulica, Numer INTO v_miasto, v_ulica, v_numer FROM Adres WHERE ID = p_id;
  DBMS_OUTPUT.PUT_LINE('Adres: ' || v_miasto || ', ' || v_ulica || ' ' || v_numer);
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Brak danych.');
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Wyst¹pi³ b³¹d: ' || SQLERRM);
END;
/

--Wywo³anie
DECLARE
  p_id Adres.ID%TYPE :=3; 
BEGIN
  get_adres_details(p_id);
END;
/

-- 3 Wyzwalacz zwiêkszaj¹cy wartoœæ rynkow¹ samochodu
CREATE OR REPLACE TRIGGER update_wartosc_rynkowa
BEFORE INSERT OR UPDATE ON Samochod
FOR EACH ROW
BEGIN
  IF :NEW.Moc_Silnika > 200 THEN
    :NEW.Wartosc_Rynkowa := :NEW.Wartosc_Rynkowa * 1.1;
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Wyst¹pi³ b³¹d: ' || SQLERRM);
END;
/

--Sprawdzenie


INSERT INTO Samochod (ID, Rok_produkcji, Moc_Silnika, Model_id, Wartosc_rynkowa)
VALUES (230, 2023, 450, 25, 57200000);

SELECT * FROM SAMOCHOD;


-- 4 Funkcja obliczaj¹ca koszty ubezpieczenia DLA PODANYCH WARTOŒCI
CREATE OR REPLACE FUNCTION calculate_koszt_ubezpieczenia(p_kwota_bazowa IN NUMBER, p_moc_silnika IN NUMBER) RETURN NUMBER AS
  v_koszt_ubezpieczenia NUMBER;
BEGIN
  v_koszt_ubezpieczenia := p_kwota_bazowa * p_moc_silnika * 0.01;
  RETURN v_koszt_ubezpieczenia;
END;
/
--wywo³anie
DECLARE
  p_kwota_bazowa NUMBER :=100000; 
  p_moc_silnika NUMBER :=200; 
  v_koszt NUMBER;
BEGIN
  v_koszt := calculate_koszt_ubezpieczenia(p_kwota_bazowa, p_moc_silnika);
  DBMS_OUTPUT.PUT_LINE('Koszt ubezpieczenia: ' || v_koszt);
END;
/
