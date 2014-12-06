package com.flemwad.menuworld;

import com.flemwad.gameworld.GameRenderer;
import com.flemwad.hexchess.HCGame;

import java.awt.Menu;

public class MenuWorld {

    private enum ButtonName {
        btnnewgame, btnexit
    }

	private HCGame theGame;
    private MenuRenderer renderer;
	
	public MenuWorld(HCGame _theGame) {
		System.out.println("MenuWorld - Load menu");
		theGame = _theGame;
		//to detect where touching on main menu
	}
	
	public void touchInput(int x, int y) { 
    	System.out.println("menu tileTouch - Point passed from InputHandler: (X:" + Integer.toString(x) + ", Y:" + Integer.toString(y) + ")");
    }

    public void buttonInput(String name, float x, float y) {
        switch (ButtonName.valueOf(name)) {
            case btnnewgame:
                processNewGame();
                break;
            case btnexit:
                processExit();
                break;
            default:
                break;
        }
    }

    public void setRenderer (MenuRenderer _renderer) {
        renderer = _renderer;
    }

    public void processExit() {
        renderer.renderQuit();
    }

    public void processNewGame() {
    		theGame.newGame();
    }


	
	//private void renderTouchBoxes() {
	//	newRect = new Rectangle(495, 527, 415, 60);
	//}
}
