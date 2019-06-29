import java.awt.*;

import javax.swing.*;

public class Board implements IBoard {
  private final int boardRow = 4;
  private final int boardCol = 4;

  JPanel board = new JPanel();
  ITile[][] tileMatrix;


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

    board.setLayout(new GridLayout(4, 4, 3, 3));
  }

  /**
   * Set a tile with ValueEnum.
   * @param row   the row 0 - 3
   * @param col   the col 0 - 3
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
   * Get the enum at the position(row, col)
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

  @Override
  public JPanel getBoard() {
    return this.board;
  }

  @Override
  public String boardToString() {
    String printBoard = "";
    for (ITile[] tileRow : tileMatrix) {
      String rowString = "";
      for (ITile tiles : tileRow) {
        if (rowString.length() != 0) {
          rowString +=  " "  + tiles.getEnum().getValue();
        }
        else {
          rowString += tiles.getEnum().getValue();
        }
      }
      printBoard +=  rowString + "\n";
    }
    return printBoard;
  }


  @Override
  public int getBoardRowNum() {
    return this.boardRow;
  }

  @Override
  public int getBoardColNum() {
    return this.boardCol;
  }


}
