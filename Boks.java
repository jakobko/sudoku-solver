public class Boks {
protected Rute[][] boks;
protected int antallRader;
protected int antallKolonner;
protected int antallRuter;

protected int boksPlasseringRad;
protected int boksPlasseringKolonne;

protected int id;

  // Konstruktoer
  public Boks(int id, int rader, int kolonner, int plassRad, int plassKolonne) {
    this.id = id;
    this.antallRader = rader;
    this.antallKolonner = kolonner;
    this.boksPlasseringRad = plassRad;
    this.boksPlasseringKolonne = plassKolonne;


    this.boks = new Rute[antallRader][antallKolonner];
  }

  // Setter inn rute i boksen metode
  public void settInnRuteiBoks(Rute rute) {
    int rutensLokalRad = rute.sinRad % antallRader;
    int rutensLokalKolonne = rute.sinKolonne % antallKolonner;
    System.out.println("Boks: " + id + " Rute nr: " + rute.id + " Plass: ("+ rute.sinRad + ", " + rute.sinKolonne + ") satt inn paa: (" + rutensLokalRad + ", " + rutensLokalKolonne + ")");
    boks[rutensLokalRad][rutensLokalKolonne] = rute;
  }

/*  public void settInnNestePlass(Rute rute) {
    int tempKolonne = antallRuter / antallKolonner;
    int tempRad = antallRuter - (tempKolonne * antallKolonner);
    boks[tempKolonne][tempRad] = rute;
    antallRuter ++;
  }*/


}
