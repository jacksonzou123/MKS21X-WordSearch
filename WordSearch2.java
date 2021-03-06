public class WordSearch2{
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch2(int rows,int cols){
      data = new char[rows][cols];
      clear();
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int i = 0; i < data.length; i++) {
        for (int j = 0; j < data[i].length; j++) {
          data[i][j] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
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


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
      char[][] test = copyArray();
      for (int i = col; i < word.length() + col; i++) {
        if (i == test[0].length) {
          return false;
        }
        if (test[row][i] == '_') {
          test[row][i] = word.charAt(i - col);
        }
        if (!(test[row][i] == word.charAt(i - col))) {
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

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      char[][] test = copyArray();
      for (int i = row; i < word.length() + row; i++) {
        if (i == test.length) {
          return false;
        }
        if (test[i][col] == '_') {
          test[i][col] = word.charAt(i - row);
        }
        if (!(test[i][col] == word.charAt(i - row))) {
          return false;
        }
      }
      data = test;
      return true;
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top left to bottom right, must fit on the WordGrid,
     *and must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
    public boolean addWordDiagonal(String word,int row, int col){
      char[][] test = copyArray();
      int index = 0;
      while (index < word.length()) {
        if (row < test.length && col < test[0].length) {
          if (test[row][col] == '_') {
            test[row][col] = word.charAt(index);
          }
          if (test[row][col] != word.charAt(index)) {
            return false;
          }
          else {
            row++;
            col++;
            index++;
          }
        }
        else {
          return false;
        }
      }
      data = test;
      return true;
    }
}
