public class Model implements IModel {

  private IBoard board;
  private int boardRow;
  private int boardCol;

  public Model() {
    board = new Board();
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
      default:
        return null;
    }
  }

  // Handle empty cell.
  private boolean canBeMerged(int fromRow, int fromCol, int toRow, int toCol) {
    ValueEnum fromValue = board.getEnumAtPosition(fromRow, fromCol);
    ValueEnum toValue = board.getEnumAtPosition(toRow, toCol);
    return fromValue == toValue;
  }

  // Need to handle merge with empty tile to
  private void mergeTile(int fromRow, int fromCol, int toRow, int toCol) {
    ValueEnum fromValue = board.getEnumAtPosition(fromRow, fromCol);
    ValueEnum toValue = board.getEnumAtPosition(toRow, toCol);
    board.setTile(toRow, toCol, mergeNum(fromValue));
    board.setTile(fromRow, fromCol, ValueEnum.V_0);
  }

  private void shiftEmptyUp {
    for (int i = 0; i < boardCol; i ++) {
      for (int j = 0; j < boardRow; j++) {
        if (board.getEnumAtPosition(j, i) == ValueEnum.V_0) {
          int q = j;
          while (q < boardRow) {
            if (board.getEnumAtPosition(q, i) != ValueEnum.V_0) {
              board.setTile(j, i, board.getEnumAtPosition(q, i));
              board.setTile(q, i, ValueEnum.V_0);
              break;
            }
            q++;
          }
        }
      }
    }
  }


  @Override
  public void up() {

  }

  @Override
  public void down(IBoard board) {

  }

  @Override
  public void left(IBoard board) {

  }

  @Override
  public void right(IBoard board) {

  }
}
