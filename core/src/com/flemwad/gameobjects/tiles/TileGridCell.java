package com.flemwad.gameobjects.tiles;

//So TileGridCell is basically amazing. You just pass it a radius.
//Based off the indexes it calculates everything we need to build a hex grid
//When we process it we just need to iterate. I think I'll be able to extend this
//later on with other tile classes to make unit, etc. -- e.g. GrassTile

/**
 * Uniform hexagonal grid cell's metrics utility class.
 */
public class TileGridCell {
    private static final int[] NEIGHBORS_DI = { 0, 1, 1, 0, -1, -1 };
    private static final int[][] NEIGHBORS_DJ = { 
            { -1, -1, 0, 1, 0, -1 }, { -1, 0, 1, 1, 1, 0 } };

    private final int[] CORNERS_DX; // array of horizontal offsets of the cell's corners
    private final int[] CORNERS_DY; // array of vertical offsets of the cell's corners
    private final int SIDE;

    private int mX = 0; // cell's left coordinate
    private int mY = 0; // cell's top coordinate

    private int mI = 0; // cell's horizontal grid coordinate
    private int mJ = 0; // cell's vertical grid coordinate

    /**
     * Cell radius (distance from center to one of the corners)
     */
    public final int RADIUS;
    /**
     * Cell height
     */
    public final int HEIGHT;
    /**
     * Cell width
     */
    public final int WIDTH;

    public static final int NUM_NEIGHBORS = 6;
    
    public boolean highlighted = false;
    public int id = 0;
    public String type = "";

    /**
     * @param radius Cell radius (distance from the center to one of the corners)
     */
    public TileGridCell(int radius) {
        RADIUS = radius;
        WIDTH = radius * 2;
        HEIGHT = (int) (((float) radius) * Math.sqrt(3));
        SIDE = radius * 3 / 2;

        int cdx[] = { RADIUS / 2, SIDE, WIDTH, SIDE, RADIUS / 2, 0 };
        CORNERS_DX = cdx;
        int cdy[] = { 0, 0, HEIGHT / 2, HEIGHT, HEIGHT, HEIGHT / 2 };
        CORNERS_DY = cdy;
    }

    public String getType() {
        if (type != "") {
            return type;
        }
        return null;
    }

    public void setType(String value) {
        type = value;
    }

    /**
     * @return X coordinate of the cell's top left corner.
     */
    public int getLeft() {
        return mX;
    }

    /**
     * @return Y coordinate of the cell's top left corner.
     */
    public int getTop() {
        return mY;
    }

    /**
     * @return X coordinate of the cell's center
     */
    public int getCenterX() {
        return mX + RADIUS;
    }

    /**
     * @return Y coordinate of the cell's center
     */
    public int getCenterY() {
        return mY + HEIGHT / 2;
    }

    /**
     * @return Horizontal grid coordinate for the cell.
     */
    public int getIndexI() {
        return mI;
    }
    
    /**
     * @return Vertical grid coordinate for the cell.
     */
    public int getIndexJ() {
        return mJ;
    }

    /**
     * @return Horizontal grid coordinate for the given neighbor.
     */
    public int getNeighborI(int neighborIdx) {
        return mI + NEIGHBORS_DI[neighborIdx];
    }

    /**
     * @return Vertical grid coordinate for the given neighbor.
     */
    public int getNeighborJ(int neighborIdx) {
        return mJ + NEIGHBORS_DJ[mI % 2][neighborIdx];
    }

    public float[] getVertices() {
        int[] mCornersx = new int[6];
        int[] mCornersy = new int[6];
        for (int k = 0; k < NUM_NEIGHBORS; k++) {
            mCornersx[k] = mX + CORNERS_DX[k];
            mCornersy[k] = mY + CORNERS_DY[k];
        }

        float verts[] = new float[]{mCornersx[0], mCornersy[0],
                                    mCornersx[1], mCornersy[1],
                                    mCornersx[2], mCornersy[2],
                                    mCornersx[3], mCornersy[3],
                                    mCornersx[4], mCornersy[4],
                                    mCornersx[5], mCornersy[5]};

        return verts;
    }

    /**
     * Computes X and Y coordinates for all of the cell's 6 corners, clockwise,
     * starting from the top left.
     * 
     * @param cornersX Array to fill in with X coordinates of the cell's corners
     * @param cornersX Array to fill in with Y coordinates of the cell's corners
     */
    public void computeCorners(int[] cornersX, int[] cornersY) {
        for (int k = 0; k < NUM_NEIGHBORS; k++) {
            cornersX[k] = mX + CORNERS_DX[k];
            cornersY[k] = mY + CORNERS_DY[k];
        }
    }

    /**
     * Sets the cell's horizontal and vertical grid coordinates.
     */
    public void setCellIndex(int i, int j) {
        mI = i;
        mJ = j;
        mX = i * SIDE;
        mY = HEIGHT * (2 * j + (i % 2)) / 2;
    }
    
    /**
     * Sets the cell as corresponding to some point inside it (can be used for
     * e.g. mouse picking).
     */
    public void setCellByPoint(int x, int y) {
        int ci = (int)Math.floor((float)x/(float)SIDE);
        int cx = x - SIDE*ci;

        int ty = y - (ci % 2) * HEIGHT / 2;
        int cj = (int)Math.floor((float)ty/(float)HEIGHT);
        int cy = ty - HEIGHT*cj;

        if (cx > Math.abs(RADIUS / 2 - RADIUS * cy / HEIGHT)) {
            setCellIndex(ci, cj);
        } else {
            setCellIndex(ci - 1, cj + (ci % 2) - ((cy < HEIGHT / 2) ? 1 : 0));
        }
    }
}