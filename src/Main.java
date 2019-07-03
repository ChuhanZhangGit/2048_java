import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * It's the gui class.
 */
public class Main {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("Untitled");
    IBoard board = new Board();



    frame.add(board.getBoard());

    frame.setSize(500, 500); //500 width and 500 height
    frame.setVisible(true); //making the frame visible
    IModel model = new Model(board);
    KeyBinding keyB = new KeyBinding(board, model, board.getBoard());

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
