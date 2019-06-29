import java.awt.*;

public enum ValueEnum {
  V_0(0,  0x776e65, 0xcdc0b4),

  V_2   (2,    0x776e65, 0xeee4df),
  V_4   (4,    0x776e65, 0xede0c1),
  V_8   (8,    0xf9f6f2, 0xf2b17a),
  V_16  (16,   0xf9f6f2, 0xf59566),
  V_32  (32,   0xf9f6f2, 0xf67c51),
  V_64  (64,   0xf9f6f2, 0xf65e3f),
  V_128 (128,  0xf9f6f2, 0xedcf74),
  V_256 (256,  0xf9f6f2, 0xedcc64),
  V_512 (512,  0xf9f6f2, 0xedc85a),
  V_1024(1024, 0xf9f6f2, 0xedc531),
  V_2048(2048, 0xf9f6f2, 0xedc225);

  private int value;
  private Color fontColor;
  private Color bgColor;

  ValueEnum(int i, int fontColor, int bgColor) {
    this.value = i;
    this.fontColor = new Color(fontColor);
    this.bgColor = new Color(bgColor);
  }

  public int getValue() {
    return this.value;
  }

  public Color getFontColor() {
    return fontColor;
  }

  public Color getBgColor() {
    return bgColor;
  }
}
