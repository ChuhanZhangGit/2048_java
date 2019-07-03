public interface IModel {

  /**
   * When player make a left move, model will perform certain action on the board.
   * @param board board object
   */
  void left(IBoard board);


  /**
   * When player make a right move, model will perform certain action on the board.
   * @param board board object
   */
  void right(IBoard board);

  /**
   * When player make a up move, model will perform certain action on the board.
   * @param board board object
   */
  void up(IBoard board);

  /**
   * When player make a down move, model will perform certain action on the board.
   * @param board board object
   */
  void down(IBoard board);

}
