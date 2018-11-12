import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{
    private char[][]data;

    //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;

    public WordSearch( int rows, int cols, String fileName) throws FileNotFoundException{
      Scanner in = new Scanner(new File(fileName));

      data = new char[rows][cols];

      clear();

      Random ran = new Random();
      seed = ran.nextInt();
      randgen = new Random(seed);
    }

    public WordSearch( int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
      Scanner in = new Scanner(new File(fileName));

      data = new char[rows][cols];

      clear();

      seed = randSeed;
      randgen = new Random(seed);
    }

    public String toString(){
      String f = "|";
      for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[i].length; j++) {
          if (j == data[i].length - 1) {
            f += data[i][j] + "|\n|";
          }
          else {
            f += data[i][j] + " ";
          }
        }
      }
      return f.substring(0,f.length() -1);
    }

    private void clear(){
      for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[i].length; j++) {
          data[i][j] = '_';
        }
      }
    }
}
