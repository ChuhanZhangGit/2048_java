
import java.awt.GridLayout;

import javax.swing.JPanel;


/**
 * This is a board class, it represent a board in 2048 game.
 */
public class Board implements IBoard {
  private final int boardRow = 4;
  private final int boardCol = 4;

  JPanel board = new JPanel();
  ITile[][] tileMatrix;


  /**
   * Instantiates a new Board.
   */
  public Board() {
    tileMatrix = new ITile[boardRow][boardCol];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        ITile tile = new Tile(ValueEnum.V_0);
        tileMatrix[i][j] = tile;
        board.add(tileMatrix[i][j].getTile());
      }
    }
    tileMatrix[0][3].setTile(ValueEnum.V_2);
    tileMatrix[0][2].setTile(ValueEnum.V_2);
    tileMatrix[1][3].setTile(ValueEnum.V_4);
    tileMatrix[1][2].setTile(ValueEnum.V_64);

    tileMatrix[2][3].setTile(ValueEnum.V_64);
    tileMatrix[2][2].setTile(ValueEnum.V_64);

    board.setLayout(new GridLayout(boardRow, boardCol, 3, 3));
  }

  /**
   * Set a tile with ValueEnum.
   *
   * @param row       the row 0 - 3
   * @param col       the col 0 - 3
   * @param valueEnum the valueEnum
   */
  @Override
  public void setTile(int row, int col, ValueEnum valueEnum) throws
          IllegalArgumentException {
    if (!(0 <= row && row < boardRow && 0 <= col && col < boardCol)) {
      throw new IllegalArgumentException(String.format("row, col are %d, %d, not legal", row, col));
    }
    tileMatrix[row][col].setTile(valueEnum);
  }

  /**
   * Get the enum at the position(row, col).
   *
   * @param row the row 0 - 3
   * @param col the col 0 -3
   * @return ValueEnum at the position.
   * @throws IllegalArgumentException when row, col not out f range.
   */
  @Override
  public ValueEnum getEnumAtPosition(int row, int col) throws IllegalArgumentException {
    if (!(0 <= row && row < boardRow && 0 <= col && col < boardCol)) {
      throw new IllegalArgumentException(String.format("row, col are %d, %d, not legal", row, col));
    }

    return tileMatrix[row][col].getEnum();
  }

  /**
   * Get JPanel of the board stored in the board object.
   *
   * @return a JPanel of the board.
   */
  @Override
  public JPanel getBoard() {
    return this.board;
  }

  /**
   * toString method for the board, display board as a matrix with a new line character at the end
   * of every line. There is a space between every tile. The tile is represented by the number it
   * stores.
   * @return board string.
   */
  @Override
  public String boardToString() {
    String printBoard = "";
    for (ITile[] tileRow : tileMatrix) {
      String rowString = "";
      for (ITile tiles : tileRow) {
        if (rowString.length() != 0) {
          rowString += " " + tiles.getEnum().getValue();
        } else {
          rowString += tiles.getEnum().getValue();
        }
      }
      printBoard += rowString + "\n";
    }
    return printBoard;
  }


  /**
   * Get the board total number of rows.
   * @return The total number of the rows.
   */
  @Override
  public int getBoardRowNum() {
    return this.boardRow;
  }

  /**
   * Get the board total number of cols.
   * @return The total number of the columns.
   */
  @Override
  public int getBoardColNum() {
    return this.boardCol;
  }


}
