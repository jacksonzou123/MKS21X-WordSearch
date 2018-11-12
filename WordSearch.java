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

    public WordSearch( int rows, int cols, String fileName) {
      Scanner in = new Scanner(new File(fileName));

      data = new char[rows][cols];

      clear();

      Random ran = new Random();
      seed = ran.nextInt();
      randgen = new Random(seed);
    }

    public WordSearch( int rows, int cols, String fileName, int randSeed) {
      Scanner in = new Scanner(new File(fileName));

      data = new char[rows][cols];

      clear();

      seed = randSeed;
      randgen = new Random(seed);
    }







    private void clear(){
      for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[i].length; j++) {
          data[i][j] = '_';
        }
      }
    }
}
