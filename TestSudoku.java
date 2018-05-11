public class TestSudoku {
  public static void main (String [] args) throws Exception{
    SudokuMain main = new SudokuMain();
    main.lesFil("EksempelSudoku.txt");
    main.skrivBrett();
  }
}
