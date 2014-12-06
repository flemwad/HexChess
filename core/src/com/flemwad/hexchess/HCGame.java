package com.flemwad.hexchess;

import com.badlogic.gdx.Game;
import com.flemwad.hchelpers.AssetLoader;
import com.flemwad.screens.GameScreen;
import com.flemwad.screens.MainMenuScreen;

public class HCGame extends Game {
	
	public GameScreen gameScreen;
	public MainMenuScreen menuScreen;
	
	@Override
	public void create() {
		System.out.println("Hex Chess Game Created!");
		
		//Texture.setEnforcePotImages(false);
		
		//Load our sprites and shiz This would be pre-main screen loading when it gets heavy.
		AssetLoader.load();
		
		menuScreen = new MainMenuScreen(this);
		setScreen(menuScreen);
	}
	
	public void newGame() {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		
		menuScreen.dispose();
        menuScreen = null;
	}

    public void mainScreen() {
        menuScreen = new MainMenuScreen(this);
        setScreen(menuScreen);

        gameScreen.dispose();
        gameScreen = null;
    }
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
