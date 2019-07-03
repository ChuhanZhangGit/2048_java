import javax.swing.JPanel;

/**
 * The interface Board.
 */
public interface IBoard {

  /**
   * Sets tile.
   *
   * @param row   the row
   * @param col   the col
   * @param valueEnum the valueEnum
   */
  void setTile(int row, int col, ValueEnum valueEnum);

  /**
   * Gets value at position.
   *
   * @param row the row
   * @param col the col
   * @return the value at position
   */
  ValueEnum getEnumAtPosition(int row, int col);

  /**
   * Gets board.
   *
   * @return the board
   */
  JPanel getBoard();

  /**
   * Get the board row number.
   * @return the board row number
   */
  int getBoardRowNum();

  /**
   * Get the board column number.
   * @return the board column number
   */
  int getBoardColNum();

  /**
   * toString method for the board, it will display board matrix as a string.
   * @return a string represent the board.
   */
  String boardToString();
}
