import java.awt.*;

public enum ValueEnum {
  V_0(0, Color.BLACK, Color.orange),
  V_2(2, Color.BLACK, Color.orange),
  V_4(4, Color.BLACK, Color.orange),
  V_8(8, Color.BLACK, Color.orange),
  V_16(16, Color.BLACK, Color.orange),
  V_32(32, Color.BLACK, Color.orange),
  V_64(64, Color.BLACK, Color.orange),
  V_128(128, Color.BLACK, Color.orange),
  V_256(256, Color.BLACK, Color.orange),
  V_512(512, Color.BLACK, Color.orange),
  V_1024(1024, Color.BLACK, Color.orange),
  V_2048(2048, Color.BLACK, Color.orange);

  private int value;
  private Color fontColor;
  private Color bgColor;

  ValueEnum(int i, Color fontColor, Color bgColor) {
    this.value = i;
    this.fontColor = fontColor;
    this.bgColor = bgColor;
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
