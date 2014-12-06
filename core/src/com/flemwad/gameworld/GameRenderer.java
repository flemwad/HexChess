package com.flemwad.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonRegionLoader;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.flemwad.gameobjects.tiles.TileGridCell;
import com.flemwad.hchelpers.AssetLoader;
import com.flemwad.hchelpers.dialogs.CustomDialog;
import com.flemwad.hchelpers.buttons.HCImageButton;
import com.flemwad.hchelpers.buttons.HCTextButton;

public class GameRenderer {

	private GameWorld world;
    private Stage stage;
	public OrthographicCamera cam;

    //Polygons
	private PolygonSpriteBatch poly;
	PolygonRegionLoader loader;
	PolygonRegion tregion;
	PolygonRegion hregion;
    PolygonRegion footRegion;

    //Sprite stuffs
	SpriteBatch sBatch;
    ShapeRenderer sRend;
	Sprite optgear;
	Sprite pauseMenu;
    Sprite trayFootman;
	Sprite trialBackground;


    //Input Objects
    CustomDialog quitDialog;
    HCTextButton btnExit;
    HCImageButton btnOptGear;
    HCImageButton footImgBtn;

    Table unitTable;

    public enum TileName {
        grass, footman, hgrass
    }

	public GameRenderer(GameWorld _world, Stage _stage) {
		world = _world;
        stage = _stage;

		float w = Gdx.graphics.getWidth(); //1200.00
		float h = Gdx.graphics.getHeight(); //800.00
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, w, h); //WHAT THE EVER FUCK?! 3 DAYS!!!! PASS A FUCKING FLOAT NOT AN INT
		//cam.update();
		
		loader = new PolygonRegionLoader();

        sRend = new ShapeRenderer();
        sRend.setProjectionMatrix(cam.combined);
		
		//Init tile polygons

        tregion = loader.load(new TextureRegion(AssetLoader.tile), Gdx.files.internal("data/tile.psh"));
        hregion = loader.load(new TextureRegion(AssetLoader.htile), Gdx.files.internal("data/tile.psh"));

        TextureRegion ftRegion = new TextureRegion(AssetLoader.footman);
        ftRegion.flip(false, true); //have to flip the polygon regions since we have our y-axis pointing down in setToOrtho on cam
        footRegion = loader.load(ftRegion, Gdx.files.internal("data/tile.psh"));

		poly = new PolygonSpriteBatch();
		poly.setProjectionMatrix(cam.combined);
        
        //Init sprites
        sBatch = new SpriteBatch();
        optgear = new Sprite(AssetLoader.optgear);
        pauseMenu = new Sprite(AssetLoader.pausemenu);
        trayFootman = new Sprite(AssetLoader.footman);
        trialBackground = new Sprite(AssetLoader.trialbg);

        //init buttons
        btnOptGear = new HCImageButton("optgear", stage, sBatch, world, 50, 50, 25, 25);
        stage.addActor(btnOptGear.getButton());

        initUnitTable();
        initPauseButtons();
	}
	
	public void drawGame() {
		//System.out.println("GameRenderer - drawGame");

		//Draw a black background to prevent flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //cam.update();

        renderGameBoard();

        btnOptGear.render();
	}
	
	public void renderPause() {
        sBatch.begin();
        pauseMenu.setPosition(375, 40);
		pauseMenu.draw(sBatch);
		sBatch.end();

        if(!stage.getActors().contains(btnExit.getButton(), true)) {
            stage.addActor(btnExit.getButton());
        }
        btnExit.render();
	}

    public void initPauseButtons() {
        String btnExitTxt = "Exit";
        btnExit = new HCTextButton("btnexit", btnExitTxt, stage, sBatch, world, 200, 80, 450, 80);
    }

    public void initUnitTable() {
        unitTable = new Table();
        unitTable.setWidth(900);
        unitTable.setHeight(80);
        unitTable.setPosition(175, 5);

        TextureRegion tr = new TextureRegion(AssetLoader.unittray);
        NinePatch np = new NinePatch(tr);
        unitTable.setBackground(new NinePatchDrawable(np));

        stage.addActor(unitTable);

        createFootmanDrag();
    }

    public void createFootmanDrag() {
        if(footImgBtn != null) {
            unitTable.removeActor(footImgBtn.getButton());
            footImgBtn = null;
        }

        footImgBtn = new HCImageButton("footman", stage, sBatch, world, 50, 50, true);
        unitTable.add(footImgBtn.getButton()).expand().width(50).left().padLeft(5);
    }

    public void disposePause() {
        if(btnExit != null) {
            btnExit.dispose();
            btnExit = null;
        }
    }

    public void renderQuit() {
        quitDialog = new CustomDialog("Exit game", AssetLoader.uiSkin); // this is the dialog title
        quitDialog.text("Leave the game?"); // text appearing in the dialog
        quitDialog.button("EXIT", new InputListener() { // button to exit app
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //go back to main menu and save stuffs
                world.backToMainMainu();
                return false;
            }
        });
        quitDialog.button("Cancel",new InputListener() { // button to exit app
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                disposeQuit();
                return true;
            }
        });
        quitDialog.show(stage); // actually show the dialog
    }

    public void disposeQuit() {
        quitDialog.remove();
        quitDialog = null;
    }

	private void renderGameBoard() {
        TileGridCell highTile = null;

        sBatch.begin();
        trialBackground.setPosition(0, 0);
        trialBackground.draw(sBatch);
        sBatch.end();
        
        poly.begin();
        
        //draw game board
		for(int i = 0; i < world.gameBoard.numTiles; i++) {	
	        TileGridCell t = world.gameBoard.tileMap.get(i);
            String tileType = t.getType();
	        PolygonSprite sprite = getTileSprite(tileType);

            sprite.setPosition(t.getLeft(), t.getTop());

            sprite.draw(poly);

	        if(world.gameBoard.isHighlight(t)) { //set highlighted tile
                highTile = t;
	        }
        }

		poly.end();

        if(highTile != null) {
            sRend.begin(ShapeType.Line);
            sRend.setColor(Color.YELLOW);
            Gdx.gl20.glLineWidth(6);
            sRend.polygon(highTile.getVertices());
            sRend.end();
        }
	}

    public PolygonSprite getTileSprite(String tileType) {
        switch(TileName.valueOf(tileType)) {
            case grass:
                return new PolygonSprite(tregion);
            case hgrass:
                return new PolygonSprite(hregion);
            case footman:
                return new PolygonSprite(footRegion);
            default:
                Gdx.app.log("my app", "Couldn't get tile polygon sprite " + tileType);
                return null;
        }
    }
}
