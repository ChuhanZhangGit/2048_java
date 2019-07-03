import javax.swing.JPanel;

/**
 * The interface Tile.
 */
public interface ITile  {
  /**
   * Gets value of the tile.
   *
   * @return the value
   */
  ValueEnum getEnum();

  /**
   * Set the tile with value.
   * @param value value of the number in the tile.
   */
  void setTile(ValueEnum value);

  /**
   * Gets tile.
   *
   * @return the tile
   */
  JPanel getTile();

  /**
   * Check if this tile is equal to other tile.
   * @param other other tile
   * @return true if equal, false otherwise.
   */
  boolean equals(ITile other);
}
