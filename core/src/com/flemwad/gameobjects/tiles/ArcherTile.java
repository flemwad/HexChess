package com.flemwad.gameobjects.tiles;

public class ArcherTile extends TileGridCell {

    public String type = "archer";

    public ArcherTile(int radius) {
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