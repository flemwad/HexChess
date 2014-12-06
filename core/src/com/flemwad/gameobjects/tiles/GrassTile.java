package com.flemwad.gameobjects.tiles;

public class GrassTile extends TileGridCell {

    public String type = "grass";

	public GrassTile(int radius) {
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
	
	public void setId(int value) {
		super.id = value;
	}
	
	public int getId() {
		return super.id;
	}
}
