import java.util.*;
import java.io.*;
import java.lang.*;

public class SudokuMain {
protected int antallRaderiHverBoks; // Forste tall
protected int antallKolonneriHverboks; // Andre tall
protected int lengdePaaBrett; // antallKolonneriHverboks * antallRaderiHverBoks
protected int totaltRuter; // lengdePaaBrett * lengdePaaBrett;
final char tom_rute_tegn = '.';
protected int[][] ruterArray;

protected Brett brettet;

  // Filleser
  public Brett lesFil(String filFraBruker) throws Exception{

    String filnavn = filFraBruker;
    Scanner scan = new Scanner(System.in);
    try {
      scan = new Scanner(new File(filnavn));
    }
    catch (java.io.FileNotFoundException e) {
      System.out.println("Error: Feil filnavn.");
      System.exit(0);
    }


    try {
      antallRaderiHverBoks = parseInt(scan.nextLine());
      antallKolonneriHverboks = parseInt(scan.nextLine());
      lengdePaaBrett = antallRaderiHverBoks * antallKolonneriHverboks;
      totaltRuter = lengdePaaBrett * lengdePaaBrett;

      if (lengdePaaBrett > 64) {
        throw new Exception ("Brettet er for stort.");
      }
    }

    catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      System.exit(0);
    }

    int tellerAntallRuter = 0;
    int rad = 0;
    ruterArray = new int[lengdePaaBrett][lengdePaaBrett];

      try {
        while (scan.hasNextLine()) {
          String lest = scan.nextLine();
          lest = lest.replaceAll("\\s+","");
          int forloopteller = 0;

          for (int kolonne = 0; kolonne < lest.length(); kolonne++) {
            char tegn = lest.charAt(kolonne);
            int verdi = tegnTilVerdi(tegn);
              if (verdi == -1) {
                throw new Exception ("Ugyldig tegn");
              }
              else if (verdi > 64) {
                throw new Exception("Verdien er utenfor lovlig intervall");
              }

            ruterArray[rad][kolonne] = verdi;
            System.out.println("Rute nr: " + tellerAntallRuter + " Verdi: " + verdi + " Rad: " + rad + " Kolonne: " + kolonne);
            tellerAntallRuter++;
          }
          forloopteller++;
          rad++;

        } // Slutt while-loop


        if (tellerAntallRuter > totaltRuter) {
          throw new Exception("For mange tegn.");
        }

      } // Slutt try

      catch (Exception e) {
        System.out.println("Error: " + e);
        System.exit(0);
      }

    brettet = new Brett(antallRaderiHverBoks, antallKolonneriHverboks, ruterArray);
    return brettet;
  }

     /**
     * Oversetter et tegn (char) til en tallverdi (int)
     *
     * @param tegn      tegnet som skal oversettes
     * @return          verdien som tegnet tilsvarer
     */
    public int tegnTilVerdi(char tegn) {
        if (tegn == tom_rute_tegn) {                // tom rute, DENNE KONSTANTEN MAA DEKLARERES
            return 0;
        } else if ('1' <= tegn && tegn <= '9') {    // tegn er i [1, 9]
            return tegn - '0';
        } else if ('A' <= tegn && tegn <= 'Z') {    // tegn er i [A, Z]
            return tegn - 'A' + 10;
        } else if (tegn == '@') {                   // tegn er @
            return 36;
        } else if (tegn == '#') {                   // tegn er #
            return 37;
        } else if (tegn == '&') {                   // tegn er &
            return 38;
        } else if ('a' <= tegn && tegn <= 'z') {    // tegn er i [a, z]
            return tegn - 'a' + 39;
        } else {                                    // tegn er ugyldig
            return -1;
        }
    }

    /**
     * Oversetter en tallverdi (int) til et tegn (char)
     *
     * @param verdi     verdien som skal oversettes
     * @param tom       tegnet som brukes for aa representere 0 (tom rute)
     * @return          tegnet som verdien tilsvarer
     */
    public char verdiTilTegn(int verdi, char tom) throws UgyldigVerdiUnntak{
        if (verdi == 0) {                           // tom
            return tom;
        } else if (1 <= verdi && verdi <= 9) {      // tegn er i [1, 9]
            return (char) (verdi + '0');
        } else if (10 <= verdi && verdi <= 35) {    // tegn er i [A, Z]
            return (char) (verdi + 'A' - 10);
        } else if (verdi == 36) {                   // tegn er @
            return '@';
        } else if (verdi == 37) {                   // tegn er #
            return '#';
        } else if (verdi == 38) {                   // tegn er &
            return '&';
        } else if (39 <= verdi && verdi <= 64) {    // tegn er i [a, z]
            return (char) (verdi + 'a' - 39);
        } else {                                    // tegn er ugyldig
            throw new UgyldigVerdiUnntak(verdi);    // HUSK DEFINISJON AV UNNTAKSKLASSE
        }
    }

    // Kastes hvis verdien til en rute er utenfor lovlig intervall.
    class UgyldigVerdiUnntak extends Exception {
      UgyldigVerdiUnntak(int verdi) {
        System.out.println("Error: Verdi "  + verdi + " er utenfor lovlig intervall.");
      }
    }

    // En metode som parser en string til en int, hvis det ikke gaar kaster den et unntak.
    public int parseInt(String s) {
      int inten = 0;
      try {
        inten = Integer.parseInt(s);
      }
      catch (NumberFormatException e) {
        System.out.println("Error: Feil tall format.");
        System.exit(0);
      }
      return inten;
    }

    // En metode som skriver ut brettet til skjerm.
    public void skrivBrett() throws Exception {
      int teller = 0;
      int tellerMaks = 0;
      int tellerHoyde = 0;
      for (int[] array: ruterArray) {
        for (int rute: array) {
          if (teller % antallKolonneriHverboks == 0 && teller != 0) {
            if (tellerMaks == lengdePaaBrett) {
              teller = 0;
              tellerHoyde++;
            }
            else {
              System.out.print("|");
              teller = 0;
            }
          }
          if (tellerMaks == lengdePaaBrett) {
            System.out.println("");
            tellerMaks = 0;
          }
          if (tellerHoyde == antallRaderiHverBoks) {
            //int lengdeMedStreker = lengdePaaBrett + ((lengdePaaBrett / antallKolonneriHverboks) -1);

            for (int i = 0; i < lengdePaaBrett; i++) {
                if (i % antallKolonneriHverboks == 0 && i != 0 && i != lengdePaaBrett) {
                  System.out.print("+-");
                }
                else {
                  System.out.print("-");
                }
            }
            System.out.println("");
            tellerHoyde = 0;
          }

          System.out.print(verdiTilTegn(rute, ' '));

          tellerMaks++;
          teller++;

        }
      }
      
      System.out.println("");
      // Gjoer denne synlig igjen for aa printer ale mulige talle en rute kan vaere.
      /*
      for (Rute[] arrayR: brettet.brett) {
        for (Rute r: arrayR) {
          r.finnAlleMuligeTall();
        }
      }
      */
      //brettet.brett[0][0].finnAlleMuligeTall();
    }
}
