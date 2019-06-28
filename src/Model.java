import java.util.ArrayList;

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

//  // Handle empty cell.
//  private boolean canBeMerged(int fromRow, int fromCol, int toRow, int toCol) {
//    ValueEnum fromValue = board.getEnumAtPosition(fromRow, fromCol);
//    ValueEnum toValue = board.getEnumAtPosition(toRow, toCol);
//    return fromValue == toValue;
//  }
//
//  // Need to handle merge with empty tile to
//  private void mergeTile(int fromRow, int fromCol, int toRow, int toCol) {
//    ValueEnum fromValue = board.getEnumAtPosition(fromRow, fromCol);
//    ValueEnum toValue = board.getEnumAtPosition(toRow, toCol);
//    board.setTile(toRow, toCol, mergeNum(fromValue));
//    board.setTile(fromRow, fromCol, ValueEnum.V_0);
//  }
//
//  private void shiftEmptyUp {
//    for (int i = 0; i < boardCol; i ++) {
//      for (int j = 0; j < boardRow; j++) {
//        if (board.getEnumAtPosition(j, i) == ValueEnum.V_0) {
//          int q = j;
//          while (q < boardRow) {
//            if (board.getEnumAtPosition(q, i) != ValueEnum.V_0) {
//              board.setTile(j, i, board.getEnumAtPosition(q, i));
//              board.setTile(q, i, ValueEnum.V_0);
//              break;
//            }
//            q++;
//          }
//        }
//      }
//    }
//  }

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
        enumList[i] = board.getEnumAtPosition(colNum, i);
      }
    }
    else {
      for (int i = fromRow; i >= toRow; i--) {
        enumList[fromRow - i] = board.getEnumAtPosition(colNum, i);
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

  }

//
//  @Override
//  public void up() {
//
//  }
//
//  @Override
//  public void down(IBoard board) {
//
//  }
//
//
//  @Override
//  public void right(IBoard board) {
//
//  }
}
