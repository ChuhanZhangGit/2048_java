import java.awt.*;

import javax.swing.*;

public class GridFourByFour extends JPanel {
  JPanel grid = new JPanel();
  JButton first = new JButton("1");
  JButton second = new JButton("1");
  public GridFourByFour() {
    grid.setLayout(new GridLayout(2,1, 3,3));
    grid.add(first);
    grid.add(second);


  }

}
