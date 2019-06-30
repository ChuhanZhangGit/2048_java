import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Model implements IModel {

  private int boardRow;
  private int boardCol;

  public Model(IBoard board) {
    boardRow = board.getBoardRowNum();
    boardCol = board.getBoardColNum();
  }

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

  private void moveToLeft(ValueEnum[] EnumList) {
    int arrayLength = EnumList.length;

    ValueEnum[] newList = new ValueEnum[arrayLength];
    int newListCounter = 0;
    for (int i = 0; i < arrayLength; i++) {
      if (EnumList[i] != ValueEnum.V_0) {
        newList[newListCounter] = EnumList[i];
        newListCounter++;
      }
    }
    while (newListCounter < arrayLength) {
      newList[newListCounter] = ValueEnum.V_0;
      newListCounter++;
    }

    for (int i = 0; i < arrayLength; i++) {
      EnumList[i] = newList[i];
    }
  }

  private void mergeToLeft(ValueEnum[] EnumList) {
    int arrayLength = EnumList.length;
    int i = 0;
    // Add merge score later.
    int mergeScore = 0;
    while (i + 1 < arrayLength) {
      if (EnumList[i] == EnumList[i + 1]) {
        EnumList[i] = mergeNum(EnumList[i]);
        EnumList[i + 1] = ValueEnum.V_0;
        i += 2;
        continue;
      }
      i++;
    }
  }

  // Inclusive from and to.
  private ValueEnum[] getEnumListRow(int rowNum, int fromCol, int toCol, IBoard board) {
    // Some error handling here???
    int arrayLength = Math.abs(fromCol - toCol) + 1;
    ValueEnum[] enumList = new ValueEnum[arrayLength];
    if (fromCol < toCol) {
      for (int i = fromCol; i <= toCol; i++) {
        enumList[i] = board.getEnumAtPosition(rowNum, i);
      }
    }
    else {
      for (int i = fromCol; i >= toCol; i--) {
        enumList[fromCol - i] = board.getEnumAtPosition(rowNum, i);
      }
    }
    return enumList;
  }

  // Inclusive from and to.
  private ValueEnum[] getEnumListCol(int colNum, int fromRow, int toRow, IBoard board) {
    // Some error handling here???
    int arrayLength = Math.abs(fromRow - toRow) + 1;
    ValueEnum[] enumList = new ValueEnum[arrayLength];
    if (fromRow < toRow) {
      for (int i = fromRow; i <= toRow; i++) {
        enumList[i] = board.getEnumAtPosition(i, colNum);
      }
    }
    else {
      for (int i = fromRow; i >= toRow; i--) {
        enumList[fromRow - i] = board.getEnumAtPosition(i, colNum);
      }
    }
    return enumList;
  }

  // inclusive.
  private void setTileForRow(int rowNum, int fromCol, int toCol, ValueEnum[] enumList, IBoard board) {
    if (enumList.length != Math.abs(fromCol - toCol ) + 1) {
      throw new IllegalArgumentException(String.format
              ("Operation failed. Row Num %d, fromCol %d, toCol %d", rowNum, fromCol, toCol));
    }
    if (fromCol < toCol) {
      for (int i = fromCol; i <= toCol; i++) {
        board.setTile(rowNum, i, enumList[i]);
      }
    }
    else {
      for (int i = fromCol; i >= toCol; i--) {
        board.setTile(rowNum, i, enumList[fromCol - i]);
      }
    }
  }

  // inclusive.
  private void setTileForCol(int colNum, int fromRow, int toRow, ValueEnum[] enumList, IBoard board) {
    if (enumList.length != Math.abs(fromRow - toRow ) + 1) {
      throw new IllegalArgumentException(String.format
              ("Operation failed. Col Num %d, fromRow %d, toRow %d", colNum, fromRow, toRow));
    }
    if (fromRow < toRow) {
      for (int i = fromRow; i <= toRow; i++) {
        board.setTile(i, colNum, enumList[i]);
      }
    }
    else {
      for (int i = fromRow; i >= toRow; i--) {
        board.setTile(i, colNum, enumList[fromRow - i]);
      }
    }
  }

  @Override
  public void left(IBoard board) {

    for (int row = 0; row < boardRow; row++) {
      ValueEnum[] enumList = getEnumListRow(row, 0, boardCol - 1, board);
      moveToLeft(enumList);

      setTileForRow(row, 0, boardRow - 1, enumList, board);
      mergeToLeft(enumList);
      setTileForRow(row, 0, boardRow - 1, enumList, board);
      moveToLeft(enumList);
      setTileForRow(row, 0, boardRow - 1, enumList, board);
    }
    generateNewFromEmpty(board);
  }

  @Override
  public void up(IBoard board) {
    for (int col = 0; col < boardCol ; col++) {
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

  @Override
  public void down(IBoard board) {
    for (int col = 0; col < boardCol ; col++) {
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

  @Override
  public void right(IBoard board) {
    for (int row = 0; row < boardRow; row++) {
      ValueEnum[] enumList = getEnumListRow(row, boardCol - 1, 0, board);
      moveToLeft(enumList);
      setTileForRow(row, boardRow - 1, 0 , enumList, board);
      mergeToLeft(enumList);
      setTileForRow(row, boardRow -1, 0, enumList, board);
      moveToLeft(enumList);
      setTileForRow(row, boardRow -1, 0, enumList, board);
    }
    generateNewFromEmpty(board);
  }

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

  private void generateNewFromEmpty(IBoard board) {
    ArrayList<Point> emptyTileList = getEmptyTileList(board);
    int numEmpty = emptyTileList.size();
    if ( numEmpty == 0) {
      return;
    }
    Random rand = new Random();
    Point target = emptyTileList.get(rand.nextInt(numEmpty));
    ValueEnum numToSet = (rand.nextInt(100) > 30) ? ValueEnum.V_2: ValueEnum.V_4;
    board.setTile(target.x, target.y, numToSet);
  }


}



