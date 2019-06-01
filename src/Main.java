import com.sun.deploy.panel.GeneralPanel;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Untitled");
    JPanel grid = new GridFourByFour();
    frame.add(grid);

    frame.setSize(400, 500);//400 width and 500 height
    frame.setLayout(null);//using no layout managers
    frame.setVisible(true);//making the frame visible
  }
}
