import javax.swing.*;

public class GUI {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Untitled");
    IBoard board = new Board();


    frame.add(board.getBoard());

    frame.setSize(500, 500); //500 width and 500 height
    frame.setVisible(true); //making the frame visible
    IModel model = new Model(board);
    model.left(board);

  }
}
