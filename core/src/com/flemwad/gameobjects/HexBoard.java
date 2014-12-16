package com.flemwad.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.flemwad.gameobjects.tiles.ArcherTile;
import com.flemwad.gameobjects.tiles.CatapultTile;
import com.flemwad.gameobjects.tiles.FootmanTile;
import com.flemwad.gameobjects.tiles.GrassTile;
import com.flemwad.gameobjects.tiles.KingTile;
import com.flemwad.gameobjects.tiles.KnightTile;
import com.flemwad.gameobjects.tiles.MageTile;
import com.flemwad.gameobjects.tiles.PeasantTile;
import com.flemwad.gameobjects.tiles.QueenTile;
import com.flemwad.gameobjects.tiles.RogueTile;
import com.flemwad.gameobjects.tiles.TileGridCell;
import com.flemwad.gameworld.GameRenderer;
import com.flemwad.gameworld.GameWorld;

public class HexBoard {

    public int tilesAcross = 30;
    public int tilesDown = 16;
    public int numTiles = 480;
	
    private int numHexCorners = 6;
    private int tileRadius = 25;
    
    public int[] mCornersx = new int[numHexCorners];
    public int[] mCornersy = new int[numHexCorners];
    
    //Tile map <Node ID - Point(x, y)>
    public ArrayList<TileGridCell> tileMap;
    public TileGridCell highlightCell;

    GameWorld gWorld;
    GameRenderer renderer;
    
    public HexBoard(GameWorld _gWorld)
    {
		initHexBoard();
		gWorld = _gWorld;

		highlightCell = new TileGridCell(tileRadius);
		highlightCell.setCellIndex(-1, -1); //so board doesn't default to 0,0 tile
    }

    public void setRenderer(GameRenderer _renderer) {
        renderer = _renderer;
    }
    
    private void initHexBoard() {   
    	int counter = 0;
    	tileMap = new ArrayList<TileGridCell>();
    	
        //cols
        for(int j = 0; j < tilesDown; j++)
        {
	        //rows
	        for(int i = 0; i < tilesAcross; i++) {
	        	//create a new tile for each part of the board.
	        	//i think this is where i should implement loading of saved maps and the maps themselves
	        	GrassTile grassTile = new GrassTile(tileRadius);
	        	grassTile.setId(counter);
	        	grassTile.setCellIndex(i, j);
	        	grassTile.computeCorners(mCornersx, mCornersy);
	        	
//                System.out.println("Before add to tileMap.");
//	        	System.out.println("[i]: " + Integer.toString(grassTile.getIndexI()) +
//	        			"\n[j]: " + Integer.toString(grassTile.getIndexJ()) +
//	        			"\nmCorners(X,Y) [ Top Left:(" + mCornersx[0] + ", " + mCornersy[0] + ") -- " +
//	        			"Top Right:(" + mCornersx[1] + ", " + mCornersy[1] + ") -- " +
//	        			"Mid Right:(" + mCornersx[2] + ", " + mCornersy[2] + ")\n" +
//	        			"Bottom Right:(" + mCornersx[3] + ", " + mCornersy[3] + ") -- " +
//	        			"Bottom Left:(" + mCornersx[4] + ", " + mCornersy[4] + ") --" +
//	        			"Mid Left:(" + mCornersx[5] + ", " + mCornersy[5] + ") ]" +
//	        			"\n-----------------------------------------------------------------------------------------------");
	        	
                tileMap.add(grassTile);

                counter++;
	        }
        }
    }
    
    public TileGridCell highTile(int x, int y) {
		//get the cell
		highlightCell.setCellByPoint(x, y);
    	
    	TileGridCell t;
		for(int i = 0; i < numTiles; i++) {
			t = tileMap.get(i);
			t.computeCorners(mCornersx, mCornersy);
			
			String touchInfo = 	"\n[i]: " + Integer.toString(t.getIndexI()) +
								"\n[j]: " + Integer.toString(t.getIndexJ());
			
			//System.out.println("Touch being processed:" + touchInfo);
	
			if(isHighlight(t)) {
				System.out.println("*** Hex Selected! *** Indexes:" + touchInfo);
				return t;
			}
		}
		
		return null;
    }

    public TileGridCell getDroppedTile(int x, int y) {
        TileGridCell t;
        TileGridCell dropTile = new TileGridCell(tileRadius);
        dropTile.setCellByPoint(x, y);

        for(int i = 0; i < numTiles; i++) {
            t = tileMap.get(i);
            t.computeCorners(mCornersx, mCornersy);

            String touchInfo = 	"\n[i]: " + Integer.toString(t.getIndexI()) +
                    "\n[j]: " + Integer.toString(t.getIndexJ());

            //System.out.println("Touch being processed:" + touchInfo);


            if(t.getIndexI() == dropTile.getIndexI() && t.getIndexJ() == dropTile.getIndexJ()) {
                return t;
            }
        }

        return null;
    }

    public void prepDropUnit(String name, int x, int y) {
        switch(GameWorld.ButtonName.valueOf(name)) {
            case footman:
                FootmanTile footTile = new FootmanTile(tileRadius);
                dropToBoard(footTile, x, y);
                break;
            case archer:
                ArcherTile archTile = new ArcherTile(tileRadius);
                dropToBoard(archTile, x, y);
                break;
            case king:
                KingTile kingTile = new KingTile(tileRadius);
                dropToBoard(kingTile, x, y);
                break;
            case queen:
                QueenTile queenTile = new QueenTile(tileRadius);
                dropToBoard(queenTile, x, y);
                break;
            case mage:
                MageTile mageTile = new MageTile(tileRadius);
                dropToBoard(mageTile, x, y);
                break;
            case rogue:
                RogueTile rogueTile = new RogueTile(tileRadius);
                dropToBoard(rogueTile, x, y);
                break;
            case catapult:
                CatapultTile catTile = new CatapultTile(tileRadius);
                dropToBoard(catTile, x, y);
                break;
            case knight:
                KnightTile knightTile = new KnightTile(tileRadius);
                dropToBoard(knightTile, x, y);
                break;
            case peasant:
                PeasantTile peasTile = new PeasantTile(tileRadius);
                dropToBoard(peasTile, x, y);
                break;
            default:
                Gdx.app.log("my app", "Couldn't drop drag unit " + name);
                break;
        }
    }

    public void dropToBoard(TileGridCell dropCell, int x, int y) {
        TileGridCell dropTile = getDroppedTile(x, y);

        if(dropTile != null) {
            Gdx.app.log("my app", "dropTile id is: " + dropTile.id);

            dropCell.setId(dropTile.id);
            dropCell.setCellIndex(dropTile.getIndexI(), dropTile.getIndexJ());
            dropCell.computeCorners(mCornersx, mCornersy);

            tileMap.remove(dropTile.id);
            tileMap.add(dropTile.id, dropCell);

            //dropTile = null;
        }
    }
    
    public boolean isHighlight(TileGridCell t) {
		if(t.getIndexI() == highlightCell.getIndexI() && t.getIndexJ() == highlightCell.getIndexJ()) {
			return true;
		}
		else {
			return false;
		}
    }
    
    public void clearHighlight() {
    	//so we can't see highlight anymore
    	highlightCell.setCellIndex(-1, -1);
    }
}
