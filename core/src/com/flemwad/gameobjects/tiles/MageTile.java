package com.flemwad.gameobjects.tiles;

public class MageTile extends TileGridCell {

    public String type = "mage";

    public MageTile (int radius) {
        super(radius);
        super.setType(type);
    }

    public boolean isHighlighted() {
        return super.highlighted;
    }

    public void highlight() {
        super.highlighted = true;
    }

    public void unhighlight() {
        super.highlighted = false;
    }
}