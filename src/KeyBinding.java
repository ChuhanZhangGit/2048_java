import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class KeyBinding {
  IBoard board;
  IModel model;
  JComponent frame;
  public KeyBinding(IBoard board, IModel model, JComponent frame) {
    this.board = board;
    this.model = model;
    this.frame = frame;
    keyBinding(frame, KeyEvent.VK_LEFT, "Move Left", (a) -> model.left(board));
    keyBinding(frame, KeyEvent.VK_RIGHT, "Move Right", (evt) -> model.right(board));

  }

  public void keyBinding(JComponent component, int keyCode, String actionName, ActionListener actionListener) {
    InputMap im = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = component.getActionMap();

    im.put(KeyStroke.getKeyStroke(keyCode, 0, false), actionName);
    am.put(actionName, new AbstractAction() {
      @Overrid
      public void actionPerformed(ActionEvent e) {
        actionListener.actionPerformed(e);
        System.out.println("performed " + actionName);
      }
    });
  }



//  private void moveLeft(IBoard board, IModel model) {
//    JPanel pane = new JPanel();
//    Action test = new AbstractAction() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        model.left(board);
//      }
//    };
//    pane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK), "file");
//    pane.getActionMap().put("file", test);
//    this.add(pane);
//  }
}
