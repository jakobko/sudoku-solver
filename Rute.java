import java.util.*;

public class Rute {
protected int sinRad;
protected int sinKolonne;

protected Rad sinRadObjekt;
protected Kolonne sinKolonneObjekt;

protected int maksVerdi;

protected int sinVerdi;
protected Boks sinBoks;

protected int id;

  // Konstruktoer
  public Rute (int id, int sinRad, int sinKolonne, int sinVerdi) {
    this.id = id;
    this.sinRad = sinRad;
    this.sinKolonne = sinKolonne;

    this.sinVerdi = sinVerdi;
  }

  // Finner alle mulige tall en rute kan ha.
  public int[] finnAlleMuligeTall() {

    if (sinVerdi == 0) {
      ArrayList<Integer> alleMulige = new ArrayList<Integer>();

      // Sette inn alle mulige tall i arrayen, saa trekke dem fra etterhvert som de ikke kan brukes.
      for (int i = 0; i < maksVerdi; i++) {
        alleMulige.add(i+1);
      }

      // Rader
      for (Rute radRute: sinRadObjekt.rad) {
        int radVerdi = radRute.sinVerdi;
        for (int i = 0; i < alleMulige.size(); i++) {
          if (alleMulige.get(i) == radVerdi && radVerdi != 0) {
            alleMulige.remove(i);
          }
        }
      }

      // Kolonner
      for (Rute kolonneRute: sinKolonneObjekt.kolonne) {
        int kolonneVerdi = kolonneRute.sinVerdi;
        for (int i = 0; i < alleMulige.size(); i++) {
          if (alleMulige.get(i) == kolonneVerdi && kolonneVerdi != 0) {
            alleMulige.remove(i);
          }
        }
      }

      // Boks
      for (Rute[] boksRute: sinBoks.boks) {
        for (Rute r: boksRute) {
          int boksVerdi = r.sinVerdi;
          for (int i = 0; i < alleMulige.size(); i++) {
            if (alleMulige.get(i) == boksVerdi && boksVerdi != 0) {
              alleMulige.remove(i);
            }
          }
        }
      }

      int[] nyAlleMulige = new int[alleMulige.size()];

      // Gjoer linjene under synlige igjen for aa printe alle mulige
      /*
      System.out.print("Rute: " + id + " kan ha: ");
      for (int i = 0; i < alleMulige.size(); i++) {
        nyAlleMulige[i] = alleMulige.get(i);
        System.out.print(nyAlleMulige[i] + ", ");
      }
      System.out.println("");
      */
      return nyAlleMulige;

    } // Slutt if

    else {
      int[] bareVerdi = new int[1];
      bareVerdi[0] = sinVerdi;
      // Gjoer linjen under synlig for aa se mulige.
      //System.out.println("Rute: " + id + " kan ha: " + bareVerdi[0]);
      return bareVerdi;
    }
  }
}
