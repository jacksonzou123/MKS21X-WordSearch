import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{
    private char[][]data;

    //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd = new ArrayList<>();

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded = new ArrayList<>();

    public static void main(String[] args) {
      WordSearch a;
      if (args.length == 3) {
        try {
          a = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
          System.out.println(a);
        }
        catch(FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      if (args.length == 4) {
        try {
          a = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
          System.out.println(a);
        }
        catch(FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      if (args.length != 3 && args.length != 4) {
        System.out.println("invalid args");
      }
    }

    public WordSearch( int rows, int cols, String fileName) throws FileNotFoundException{
      Scanner in = new Scanner(new File(fileName));
      while (in.hasNextLine()) {
        wordsToAdd.add(in.nextLine());
      }

      data = new char[rows][cols];

      clear();

      Random ran = new Random();
      seed = ran.nextInt();
      randgen = new Random(seed);
      addAllWords();
    }

    public WordSearch( int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
      Scanner in = new Scanner(new File(fileName));
      while (in.hasNextLine()) {
        wordsToAdd.add(in.nextLine());
      }

      data = new char[rows][cols];

      clear();

      seed = randSeed;
      randgen = new Random(seed);
      addAllWords();
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
      f = f.substring(0,f.length() -1);
      f += "\nseed: " + seed;
      return f;
    }

    private void clear(){
      for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[i].length; j++) {
          data[i][j] = '_';
        }
      }
    }

    private boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement) {
      char[][] test = copyArray();
      int index = 0;
      while (index < word.length()) {
        if (r > -1 && r < test.length && c > -1 && c < test[0].length) {
          if (test[r][c] == '_') {
            test[r][c] = word.charAt(index);
          }
          if (test[r][c] != word.charAt(index)) {
            return false;
          }
          else {
            index++;
            r += rowIncrement;
            c += colIncrement;
          }
        }
        else {
          return false;
        }
      }
      data = test;
      return true;
    }

    private char[][] copyArray() {
      char[][] f = new char[data.length][data[0].length];
      for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[i].length; j++) {
          f[i][j] = data[i][j];
        }
      }
      return f;
    }

    private void addAllWords() {
      for (int i = 0; i < wordsToAdd.size(); i+= 0) {
        String word = wordsToAdd.get(abs(randgen.nextInt()) % wordsToAdd.size());
        int h = (abs(randgen.nextInt()) % 3) - 1;
        int v = (abs(randgen.nextInt()) % 3) - 1;
        System.out.println(word);

        int x = 1000;
        while (x > 0) {
          if (addWord(word, abs(randgen.nextInt()) % data.length, abs(randgen.nextInt()) % data[0].length, h, v)) {
            x = -1;
          }
          x -= 1;
        }
        wordsToAdd.remove(word);
        System.out.println(wordsToAdd);
        wordsAdded.add(word);
      }
    }

    private static int abs(int input) {
      if (input < 0) {
        input*= -1;
      }
      return input;
    }
}
