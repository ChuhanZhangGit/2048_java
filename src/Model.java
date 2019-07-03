import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Model.
 */
public class Model implements IModel {

  private final int boardRow;
  private final int boardCol;


  /**
   * Instantiates a new Model.
   *
   * @param board the board object the model
   */
  public Model(IBoard board) {
    boardRow = board.getBoardRowNum();
    boardCol = board.getBoardColNum();
  }

  /**
   * Perform calculation when two same tiles merged.
   *
   * @param num the value of one of the tile to be merged
   * @return merged tile value
   */
  private ValueEnum mergeNum(ValueEnum num) {
    switch (num) {
      case V_0:
        return ValueEnum.V_0;
      case V_2:
        return ValueEnum.V_4;
      case V_4:
        return ValueEnum.V_8;
      case V_8:
        return ValueEnum.V_16;
      case V_16:
        return ValueEnum.V_32;
      case V_32:
        return ValueEnum.V_64;
      case V_64:
        return ValueEnum.V_128;
      case V_128:
        return ValueEnum.V_256;
      case V_256:
        return ValueEnum.V_512;
      case V_512:
        return ValueEnum.V_1024;
      case V_1024:
        return ValueEnum.V_2048;
      default:
        return null;
    }
  }

  /**
   * Move the all items in the enum list to left, leaving right part the remaining empty tiles.
   *
   * @param enumList the enum list to be processed.
   */
  private void moveToLeft(ValueEnum[] enumList) {
    int arrayLength = enumList.length;

    ValueEnum[] newList = new ValueEnum[arrayLength];
    int newListCounter = 0;
    for (int i = 0; i < arrayLength; i++) {
      if (enumList[i] != ValueEnum.V_0) {
        newList[newListCounter] = enumList[i];
        newListCounter++;
      }
    }
    while (newListCounter < arrayLength) {
      newList[newListCounter] = ValueEnum.V_0;
      newListCounter++;
    }

    for (int i = 0; i < arrayLength; i++) {
      enumList[i] = newList[i];
    }
  }

  /**
   * Merge the enum list to left, leaving behind empty tile when merged.
   *
   * @param enumList the enum lis to be processed.
   * @return score result from the merging.
   */
  private int mergeToLeft(ValueEnum[] enumList) {
    int arrayLength = enumList.length;
    int i = 0;
    // Add merge score later.
    int mergeScore = 0;
    while (i + 1 < arrayLength) {
      if (enumList[i] == enumList[i + 1]) {
        enumList[i] = mergeNum(enumList[i]);
        enumList[i + 1] = ValueEnum.V_0;
        mergeScore += enumList[i].getValue();
        i += 2;
        continue;
      }
      i++;
    }
    return mergeScore;
  }

  /**
   * Get the enum list from certain row. When fromCol < toCol, the row from left item to right. When
   * fromCOl > toCol, the row from right to left.
   *
   * @param rowNum  the row number of the target row.
   * @param fromCol the from column number inclusive.
   * @param toCol   the to column number inclusive.
   * @param board   the board object
   * @return the enum list represent the the row.
   */
  private ValueEnum[] getEnumListRow(int rowNum, int fromCol, int toCol, IBoard board) {
    // Some error handling here???
    int arrayLength = Math.abs(fromCol - toCol) + 1;
    ValueEnum[] enumList = new ValueEnum[arrayLength];
    if (fromCol < toCol) {
      for (int i = fromCol; i <= toCol; i++) {
        enumList[i] = board.getEnumAtPosition(rowNum, i);
      }
    } else {
      for (int i = fromCol; i >= toCol; i--) {
        enumList[fromCol - i] = board.getEnumAtPosition(rowNum, i);
      }
    }
    return enumList;
  }

  /**
   * Get the enum list from certain row. When fromRow < toRow, the col from top item to bottom. When
   * fromCOl > toCol, the col from bottom to top.
   *
   * @param colNum  the target col num
   * @param fromRow the from row number inclusive
   * @param toRow   the to row number inclusive
   * @param board   the board object
   * @return the enum list represent the col
   */
  private ValueEnum[] getEnumListCol(int colNum, int fromRow, int toRow, IBoard board) {
    // Some error handling here???
    int arrayLength = Math.abs(fromRow - toRow) + 1;
    ValueEnum[] enumList = new ValueEnum[arrayLength];
    if (fromRow < toRow) {
      for (int i = fromRow; i <= toRow; i++) {
        enumList[i] = board.getEnumAtPosition(i, colNum);
      }
    } else {
      for (int i = fromRow; i >= toRow; i--) {
        enumList[fromRow - i] = board.getEnumAtPosition(i, colNum);
      }
    }
    return enumList;
  }


  /**
   * Set the tile for a row from the enum list. When fromCol smaller than toCol, set row from left
   * to right. When fromCol larger than toCol, set row from right to left.
   *
   * @param rowNum   the target row number
   * @param fromCol  the from col number inclusive
   * @param toCol    the toCol number inclusive
   * @param enumList the input enum list
   * @param board    the board object
   */
  private void setTileForRow(int rowNum,
                             int fromCol,
                             int toCol,
                             ValueEnum[] enumList,
                             IBoard board) {
    if (enumList.length != Math.abs(fromCol - toCol) + 1) {
      throw new IllegalArgumentException(String.format(
              "Operation failed. Row Num %d, fromCol %d, toCol %d", rowNum, fromCol, toCol));
    }
    if (fromCol < toCol) {
      for (int i = fromCol; i <= toCol; i++) {
        board.setTile(rowNum, i, enumList[i]);
      }
    } else {
      for (int i = fromCol; i >= toCol; i--) {
        board.setTile(rowNum, i, enumList[fromCol - i]);
      }
    }
  }

  /**
   * Set the tile for a col from the enum list. When fromRow smaller than toRow, set col from top to
   * bottom. When fromRow larger than toRow, set row from buttom to top.
   *
   * @param colNum   the target colNum
   * @param fromRow  the fromRow number the this column
   * @param toRow    the toRow number for this column
   * @param enumList the input enum list
   * @param board    the board object
   */
  private void setTileForCol(int colNum,
                             int fromRow,
                             int toRow, ValueEnum[] enumList,
                             IBoard board) {
    if (enumList.length != Math.abs(fromRow - toRow) + 1) {
      throw new IllegalArgumentException(String.format(
              "Operation failed. Col Num %d, fromRow %d, toRow %d", colNum, fromRow, toRow));
    }
    if (fromRow < toRow) {
      for (int i = fromRow; i <= toRow; i++) {
        board.setTile(i, colNum, enumList[i]);
      }
    } else {
      for (int i = fromRow; i >= toRow; i--) {
        board.setTile(i, colNum, enumList[fromRow - i]);
      }
    }
  }

  /**
   * Get empty tile coordinates as list of points(x, y).
   *
   * @return array list of point object contain the empty tile coordinates.
   */
  private ArrayList<Point> getEmptyTileList(IBoard board) {
    ArrayList<Point> emptyTileList = new ArrayList<>();
    for (int row = 0; row < boardRow; row++) {
      for (int col = 0; col < boardCol; col++) {
        if (board.getEnumAtPosition(row, col) == ValueEnum.V_0) {
          emptyTileList.add(new Point(row, col));
        }
      }
    }
    return emptyTileList;
  }

  /**
   * Generate the new 2 or 4 tile randomly in the empty tiles. 2 has larger chance of appearance.
   * @param board the board object.
   */
  private void generateNewFromEmpty(IBoard board) {
    ArrayList<Point> emptyTileList = getEmptyTileList(board);
    int numEmpty = emptyTileList.size();
    if (numEmpty == 0) {
      return;
    }
    Random rand = new Random();
    Point target = emptyTileList.get(rand.nextInt(numEmpty));
    ValueEnum numToSet = (rand.nextInt(100) > 30) ? ValueEnum.V_2 : ValueEnum.V_4;
    board.setTile(target.x, target.y, numToSet);
  }

  private boolean isGameOver(IBoard board) {
    if (getEmptyTileList(board).size() != 0) {
      return false;
    }
    for (int i = 0; i < boardRow; i++) {
      for (int j = 0; j < boardCol; j++) {
        ValueEnum curr = board.getEnumAtPosition(i, j);
        if (((i < boardRow - 1) && (curr  == board.getEnumAtPosition(i+1, j))) ||
                ((j < boardCol - 1) && (curr == board.getEnumAtPosition(i, j+1)))) {
          return false;
        }
      }
    }
  }

  /**
   * When player make a left move, model will get enum list for every row from left to right. It
   * will modify the enum list by first eliminating empty tiles, then merge tiles, then eliminate
   * newly created empty cell again.
   *
   * @param board board object
   */
  @Override
  public void left(IBoard board) {

    for (int row = 0; row < boardRow; row++) {
      ValueEnum[] enumList = getEnumListRow(row, 0, boardCol - 1, board);
      moveToLeft(enumList);

      setTileForRow(row, 0, boardCol - 1, enumList, board);
      mergeToLeft(enumList);
      setTileForRow(row, 0, boardCol - 1, enumList, board);
      moveToLeft(enumList);
      setTileForRow(row, 0, boardCol - 1, enumList, board);
    }
    generateNewFromEmpty(board);
  }

  /**
   * When player make a left move, model will perform similar action as left move with one
   * difference in that it will get the enum list from right to left and set the enum list from
   * right to left as well.
   *
   * @param board board object
   */
  @Override
  public void right(IBoard board) {
    for (int row = 0; row < boardRow; row++) {
      ValueEnum[] enumList = getEnumListRow(row, boardCol - 1, 0, board);
      moveToLeft(enumList);
      setTileForRow(row, boardCol - 1, 0, enumList, board);
      mergeToLeft(enumList);
      setTileForRow(row, boardCol - 1, 0, enumList, board);
      moveToLeft(enumList);
      setTileForRow(row, boardCol - 1, 0, enumList, board);
    }
    generateNewFromEmpty(board);
  }


  /**
   * When player make a left move, model will perform similar action as left move with one
   * difference in that it will get the enum list from top to buttom and set the enum list from top
   * to buttom as well.
   *
   * @param board board object
   */
  @Override
  public void up(IBoard board) {
    for (int col = 0; col < boardCol; col++) {
      ValueEnum[] enumList = getEnumListCol(col, 0, boardRow - 1, board);
      moveToLeft(enumList);
      setTileForCol(col, 0, boardRow - 1, enumList, board);
      mergeToLeft(enumList);
      setTileForCol(col, 0, boardRow - 1, enumList, board);
      moveToLeft(enumList);
      setTileForCol(col, 0, boardRow - 1, enumList, board);
    }
    generateNewFromEmpty(board);

  }

  /**
   * When player make a left move, model will perform similar action as left move with one
   * difference in that it will get the enum list from bottom to top and set the enum list from
   * bottom to top as well.
   *
   * @param board board object
   */
  @Override
  public void down(IBoard board) {
    for (int col = 0; col < boardCol; col++) {
      ValueEnum[] enumList = getEnumListCol(col, boardRow - 1, 0, board);
      moveToLeft(enumList);

      setTileForCol(col, boardRow - 1, 0, enumList, board);
      mergeToLeft(enumList);
      setTileForCol(col, boardRow - 1, 0, enumList, board);
      moveToLeft(enumList);
      setTileForCol(col, boardRow - 1, 0, enumList, board);
    }
    generateNewFromEmpty(board);
  }


}



