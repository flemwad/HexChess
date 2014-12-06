package com.flemwad.gameworld;

import com.badlogic.gdx.Gdx;
import com.flemwad.gameobjects.HexBoard;
import com.flemwad.gameobjects.tiles.TileGridCell;
import com.flemwad.hexchess.HCGame;

public class GameWorld {

    public enum GameState {
    	Setup, Running, Paused;
    }

    public enum ButtonName {
        optgear, btnexit, footman
    }

    private HCGame theGame;
    public GameState gameState;
    private GameRenderer renderer;
    
    public HexBoard gameBoard;

    public TileGridCell lastTile = null;
    public boolean btnDragging = false;
    public boolean setupDone = false;
    
	public GameWorld(HCGame _theGame) {
        theGame = _theGame;
		gameBoard = new HexBoard(this);
		//gameState = GameState.Running; //start off in setup mode, I should implement Ready, but I'm not sure if I need to
        gameState = GameState.Setup; //start off in setup mode, I should implement Ready, but I'm not sure if I need to
		System.out.println("GameWorld - Loaded Game Board");
	}
    
	public void update(float delta) {
		//System.out.println("GameWorld - update");
		gameState = GameState.Running;
	}

    public void updateSetup(float delta) {
        //System.out.println("GameWorld - updateSetup");
        gameState = GameState.Setup;

    }
	
	public void updatePaused(float delta) {
		gameState = GameState.Paused;
	}
	
    public void touchInput(int x, int y) { 
    	System.out.println("game tileTouch - Point passed from InputHandler: (X:" + Integer.toString(x) + ", Y:" + Integer.toString(y) + ")");

        if(btnDragging) { //skip the highlight if we're dragging a tile
            btnDragging = false;
            return;
        }

    	//only update tile if opt wasn't touched
    	if(gameState != GameState.Paused) {
        	//cell gets set in highTile
        	TileGridCell newTile = gameBoard.highTile(x, y);
        	if(newTile != null && newTile.highlighted && newTile.id == lastTile.id) { //clearing on a double press
        		lastTile = newTile;
        		newTile.highlighted = false;
        		gameBoard.clearHighlight();
        	}
        	else if (newTile != null && (lastTile != null || lastTile == null)) {
        		lastTile = newTile;
        		newTile.highlighted = true;
        	}
            else {
                //this will get hit even when the board isn't touched
                //e.g. optgear, unit tray, etc.
            }
    	}
    }

    public void setRenderer (GameRenderer _renderer) {
        renderer = _renderer;
        gameBoard.setRenderer(renderer);
    }



    public void buttonInput(String name, float x, float y) {
        switch (ButtonName.valueOf(name)) {
            case optgear:
                processOptGear();
                break;
            case btnexit:
                processPauseExit();
                break;
            default:
                break;
        }
    }

    public void handleUnitDrop(String buttonName, int x, int y) {
        handleNewDragUnit(buttonName);
        gameBoard.dropUnit(buttonName, x, y);
    }

    public void handleNewDragUnit(String buttonName) {
        switch(ButtonName.valueOf(buttonName)) {
            case footman:
                renderer.createFootmanDrag();
                break;
            default:
                Gdx.app.log("my app", "Couldn't create new drag unit " + buttonName);
                break;
        }
    }

    public void backToMainMainu() {
        theGame.mainScreen();
    }

    private void processPauseExit() {
        renderer.renderQuit();
    }

    private void processOptGear() {
        if(gameState == GameState.Running || gameState == GameState.Setup) {
            renderer.initPauseButtons();
            gameState = GameState.Paused;
        }
        else if (gameState == GameState.Paused && !setupDone) {
            gameState = GameState.Setup;
            renderer.disposePause();
        }
        else if (gameState == GameState.Paused && setupDone) {
            gameState = GameState.Running;
            renderer.disposePause();
        }
    }
}
