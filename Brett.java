public class Brett {
protected int lengde;
protected Rute[][] brett;
protected Rad[] radListe;
protected Kolonne[] kolonneListe;

protected int antallBokserTotalt;
protected Boks[] boksListe;

protected int antallRader;
protected int antallKolonner;
protected int ruteArrayfraFil[][];


  // Konstruktoer
  public Brett(int rader, int kolonner, int[][] innRuteArray) {
    this.antallRader = rader;
    this.antallKolonner = kolonner;

    // Regner ut lengde
    this.lengde = rader * kolonner;

    // Setter inn string arrayen med rutene fra Filleser;
    this.ruteArrayfraFil = innRuteArray;

    opprettDatastruktur();
  }

  public void opprettDatastruktur() {
    // Sett inn det den faar fra paramentere
    this.brett = new Rute[lengde][lengde];
    this.radListe = new Rad[lengde];
    this.kolonneListe = new Kolonne[lengde];

    // Lage boks array med riktig lengde
    this.boksListe = new Boks[lengde];

    // Oppretter to variabler til aa telle bokser.
    int tellerRad = 0;
    int tellerKolonne = 0;
    int tellerid = 0;

    for (int t = 0; t < lengde; t++) {
      // Lage kolonner og rader
      radListe[t] = new Rad(t, lengde, t);
      kolonneListe[t] = new Kolonne(t, lengde, t);

      // Lager bokser
      if (tellerKolonne > antallRader-1) {
        tellerKolonne = 0;
        tellerRad++;
      }

      System.out.println("Boks opprettet: " + t + " paa plass: (" + tellerRad + ", "+ tellerKolonne + ")");
      Boks boks = new Boks(t, antallRader, antallKolonner, tellerRad, tellerKolonne);
      boksListe[t] = boks;
      tellerKolonne++;
      tellerid++;
    }

    // Sette inn rutene.
    tellerRad = 0;
    tellerKolonne = 0;
    int boksPlass = 0;
    int antallRuter = lengde * lengde;

    for (int i = 0; i < antallRuter; i++) {
      // Teller ruter og finner ruteplassering
      if (tellerKolonne > lengde-1) {
          tellerKolonne = 0;
          tellerRad++;
      }



      int rutensVerdi = ruteArrayfraFil[tellerRad][tellerKolonne];
      nyRutePaaBrett(i, tellerRad, tellerKolonne, rutensVerdi);
      tellerKolonne++;
    }

  }

  public void nyRutePaaBrett(int i, int radPlass, int kolonnePlass, int verdi) {
    Rute rute = new Rute(i, radPlass, kolonnePlass, verdi);
    brett[radPlass][kolonnePlass] = rute;
    radListe[radPlass].settInnRute(rute.sinKolonne, rute);
    kolonneListe[kolonnePlass].settInnRute(rute.sinRad, rute);

    rute.sinRadObjekt = radListe[radPlass];
    rute.sinKolonneObjekt = kolonneListe[kolonnePlass];
    rute.maksVerdi = lengde;
    //double desLengdeRad = (double) antallRader;
    //double desLengdeKolonne = (double) antallKolonner;

    //double desRutensBoksRad = radPlass / desLengdeKolonne;
    //double desRutensBoksKolonne = kolonnePlass / desLengdeRad;

    //int rutensBoksRad = (int)Math.round(desRutensBoksRad);
    //int rutensBoksKolonne = (int)Math.round(desRutensBoksKolonne);

    int rutensBoksRad =  radPlass / antallRader;
    int rutensBoksKolonne = kolonnePlass / antallKolonner;
    //System.out.println(rutensBoksRad + " = " + desRutensBoksRad + " ||| " + rutensBoksKolonne +"=" + desRutensBoksKolonne);
    for (Boks b: boksListe) {
      if (b.boksPlasseringRad == rutensBoksRad && b.boksPlasseringKolonne == rutensBoksKolonne) {
        b.settInnRuteiBoks(rute);
        rute.sinBoks = b;
      }
    }

    //rute.sinBoks = boksListe[boks];
    // Finner ut hvilken boks som skal ha ruten
    /*double boksPlassRad = radPlass / lengde;
    double boksPlassKolonne = kolonnePlass / lengde;*/

    // To-Do: Rund opp eller ned og gjoer til en int!
  }

}
