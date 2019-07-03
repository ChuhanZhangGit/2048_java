import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;



/**
 * The type Key binding.
 */
public class KeyBinding {
  /**
   * The Board.
   */
  IBoard board;
  /**
   * The Model.
   */
  IModel model;
  /**
   * The Frame.
   */
  JComponent frame;

  /**
   * Instantiates a new Key binding.
   *
   * @param board the board
   * @param model the model
   * @param frame the frame
   */
  public KeyBinding(IBoard board, IModel model, JComponent frame) {
    this.board = board;
    this.model = model;
    this.frame = frame;
    keyBinding(frame, KeyEvent.VK_LEFT, "Move Left", (evt) -> model.left(board));
    keyBinding(frame, KeyEvent.VK_RIGHT, "Move Right", (evt) -> model.right(board));
    keyBinding(frame, KeyEvent.VK_UP, "Move Up", (evt) -> model.up(board));
    keyBinding(frame, KeyEvent.VK_DOWN, "Move Down", (evt) -> model.down(board));

  }

  /**
   * Key binding.
   *
   * @param component      the component
   * @param keyCode        the key code
   * @param actionName     the action name
   * @param actionListener the action listener
   */
  public void keyBinding(JComponent component,
                         int keyCode,
                         String actionName,
                         ActionListener actionListener) {
    InputMap im = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = component.getActionMap();

    im.put(KeyStroke.getKeyStroke(keyCode, 0, false), actionName);
    am.put(actionName, new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionListener.actionPerformed(e);
        System.out.println("performed " + actionName);
      }
    });
  }


}
