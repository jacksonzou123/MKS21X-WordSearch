import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{
    private char[][]data;

    //the random seed used to produce this WordSearch
    private static int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd = new ArrayList<>();

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded = new ArrayList<>();

    public static void main(String[] args) {
      try {
        WordSearch a;
        boolean answer = false;
        if (args.length >= 5) {
          if (args[4].equals("key")) {
            answer = true;
          }
        }
        if (args.length >= 4) {
          seed = Integer.parseInt(args[3]);
        }
        else {
          Random rng = new Random();
          seed = rng.nextInt();
        }
        if (args.length <= 2) {
          System.out.println("Insufficient number of inputs");
          error();
        }
        System.out.println(a = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],seed,answer));
      }
      catch(FileNotFoundException e) {
        System.out.println("File does not exist; File must follow the format filename.txt");
        error();
      }
      catch(NumberFormatException e) {
        System.out.println("row, col, and seed must all be integer numbers");
        error();
      }
    }

    public WordSearch( int rows, int cols, String fileName, int randSeed, boolean answer) throws FileNotFoundException{
      Scanner in = new Scanner(new File(fileName));
      while (in.hasNextLine()) {
        wordsToAdd.add(in.nextLine());
      }

      data = new char[rows][cols];

      clear();
      randgen = new Random(randSeed);
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
      f += "\nseed: " + seed + "\nWords: " + wordsAdded;
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
        if (h == 0 && v == 0) {
          h = 1;
        }
        int x = 1000;
        while (x > 0) {
          if (addWord(word, abs(randgen.nextInt()) % data.length, abs(randgen.nextInt()) % data[0].length, h, v)) {
            x = -1;
          }
          x -= 1;
        }
        wordsToAdd.remove(word);
        wordsAdded.add(word);
      }
    }

    private static int abs(int input) {
      if (input < 0) {
        input*= -1;
      }
      return input;
    }

    public static void error() {
      System.out.println("Please follow the following format:\njava WordSearch rows cols filename seed key\nseed and key are optional");
      System.exit(1);
    }
}
