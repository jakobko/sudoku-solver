public class Kolonne {
  protected Rute[] kolonne;
  protected int plasseringPaaBrett;

  protected int id;

  // Konstruktoer
  public Kolonne(int id, int lengde, int plasseringPaaBrett) {
    this.id = id;
    this.kolonne = new Rute[lengde];
    this.plasseringPaaBrett = plasseringPaaBrett;
  }

  // Setter inn ruten paa riktig plass
  public void settInnRute(int kolonnePlasering, Rute rute) {
    this.kolonne[kolonnePlasering] = rute;
  }

}
