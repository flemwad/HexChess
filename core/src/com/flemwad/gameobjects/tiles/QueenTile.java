package com.flemwad.gameobjects.tiles;

public class QueenTile extends TileGridCell {

    public String type = "queen";

    public QueenTile(int radius) {
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