package com.flemwad.menuworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flemwad.hchelpers.AssetLoader;
import com.flemwad.hchelpers.buttons.HCTextButton;
import com.flemwad.hchelpers.dialogs.QuitDialog;

public class MenuRenderer {

	public OrthographicCamera cam;
	
	private MenuWorld menuWorld;

    private Stage stage;
	private SpriteBatch sBatch;
	private Sprite mainMenu;

    private HCTextButton btnNewGame;
    private HCTextButton btnExit;
    private QuitDialog quitDialog;
	
	public MenuRenderer(MenuWorld _menuWorld, Stage _stage) {
		float w = Gdx.graphics.getWidth(); //1200.00
		float h = Gdx.graphics.getHeight(); //800.00
		
		menuWorld = _menuWorld;
        stage = _stage;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, w, h);
		
		//Init sprites
        sBatch = new SpriteBatch();
        mainMenu = new Sprite(AssetLoader.mainmenu);

        btnNewGame = new HCTextButton("btnnewgame", "New Game", stage, sBatch, menuWorld, 200, 80, 495, 527);
        stage.addActor(btnNewGame.getButton());
        btnExit = new HCTextButton("btnexit", "Exit", stage, sBatch, menuWorld, 200, 80, 495, 427);
        stage.addActor(btnExit.getButton());
	}
	
	public void render() {
		//Draw a black background to prevent flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        renderMainMenu();

        btnNewGame.render();
        btnExit.render();
        //set up transparent hit boxes for menu touching
	}

    public void renderQuit() {
        quitDialog = new QuitDialog("Exit game", AssetLoader.uiSkin, this); // this is the dialog title
        quitDialog.show(stage);
    }

    public void disposeQuit() {
        quitDialog.remove();
        quitDialog = null;
    }
	
	private void renderMainMenu() {
		sBatch.begin();
		mainMenu.setPosition(0, 0);
		mainMenu.draw(sBatch);
		sBatch.end();
	}
}
