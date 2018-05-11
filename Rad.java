public class Rad {
  protected Rute[] rad;
  protected int plasseringPaaBrett;

  protected int id;

  // Konstruktoer
  public Rad(int id, int lengde, int plasseringPaaBrett) {
    this.id = id;
    this.rad = new Rute[lengde];
    this.plasseringPaaBrett = plasseringPaaBrett;
  }

  // Setter inn ruten paa riktig plass
  public void settInnRute(int radPlassering, Rute rute) {
    this.rad[radPlassering] = rute;
  }

}
