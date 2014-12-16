package com.flemwad.gameobjects.tiles;

public class CatapultTile extends TileGridCell {

    public String type = "catapult";

    public CatapultTile (int radius) {
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