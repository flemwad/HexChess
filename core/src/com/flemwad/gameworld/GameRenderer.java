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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
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

    //Polygon Loaders and Renderers
	private PolygonSpriteBatch poly;
	PolygonRegionLoader loader;

    //Tile Polygon Regions
	PolygonRegion tregion;
    PolygonRegion footRegion;
    PolygonRegion archRegion;
    PolygonRegion kingRegion;
    PolygonRegion queenRegion;
    PolygonRegion mageRegion;
    PolygonRegion rogueRegion;
    PolygonRegion catapultRegion;
    PolygonRegion knightRegion;
    PolygonRegion peasantRegion;

    PolygonSprite spGrassRegion;
    PolygonSprite spFootRegion;
    PolygonSprite spArchRegion;
    PolygonSprite spKingRegion;
    PolygonSprite spQueenRegion;
    PolygonSprite spMageRegion;
    PolygonSprite spRogueRegion;
    PolygonSprite spKnightRegion;
    PolygonSprite spCataRegion;
    PolygonSprite spPeasRegion;

    //Sprite stuffs
	SpriteBatch sBatch;
    ShapeRenderer sRend;
	Sprite optgear;
	Sprite pauseMenu;
	Sprite trialBackground;


    //Input Objects
    CustomDialog quitDialog;
    HCTextButton btnExit;

    //Drag Buttons for Units
    HCImageButton btnOptGear;
    HCImageButton footImgBtn;
    HCImageButton archImgBtn;
    HCImageButton kingImgBtn;
    HCImageButton queenImgBtn;
    HCImageButton mageImgBtn;
    HCImageButton rogueImgBtn;
    HCImageButton knightImgBtn;
    HCImageButton cataImgBtn;
    HCImageButton peasImgBtn;

    Table unitTable;
    String[] unitNames = new String[] {"footman", "archer", "king", "queen", "mage", "rogue", "catapult", "knight", "peasant"};

    public enum TileName {
        grass, footman, hgrass, archer, king, queen, mage, rogue, catapult, knight, peasant
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

        initPolyRegions();

        sRend = new ShapeRenderer();
        sRend.setProjectionMatrix(cam.combined);

		//Init tile polygons
        tregion = loader.load(new TextureRegion(AssetLoader.tile), Gdx.files.internal("data/tile.psh"));

		poly = new PolygonSpriteBatch();
		poly.setProjectionMatrix(cam.combined);

        //Init sprites
        sBatch = new SpriteBatch();
        optgear = new Sprite(AssetLoader.optgear);
        pauseMenu = new Sprite(AssetLoader.pausemenu);
        trialBackground = new Sprite(AssetLoader.trialbg);

        //init buttons
        btnOptGear = new HCImageButton("optgear", stage, sBatch, world, 50, 50, 25, 25);
        stage.addActor(btnOptGear.getButton());

        initUnitTable();
        initPauseButtons();
	}

    public void initPolyRegions() {
        TextureRegion txFootRegion = new TextureRegion(AssetLoader.footman);
        txFootRegion.flip(false, true); //have to flip the polygon regions since we have our y-axis pointing down in setToOrtho on cam
        footRegion = loader.load(txFootRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txArchRegion = new TextureRegion(AssetLoader.archer);
        txArchRegion.flip(false, true);
        archRegion = loader.load(txArchRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txKingRegion = new TextureRegion(AssetLoader.king);
        txKingRegion.flip(false, true);
        kingRegion = loader.load(txKingRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txQueenRegion = new TextureRegion(AssetLoader.queen);
        txQueenRegion.flip(false, true);
        queenRegion = loader.load(txQueenRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txKnightRegion = new TextureRegion(AssetLoader.knight);
        txKnightRegion.flip(false, true);
        knightRegion = loader.load(txKnightRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txMageRegion = new TextureRegion(AssetLoader.mage);
        txMageRegion.flip(false, true);
        mageRegion = loader.load(txMageRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txRogueRegion = new TextureRegion(AssetLoader.rogue);
        txRogueRegion.flip(false, true);
        rogueRegion = loader.load(txRogueRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txCataRegion = new TextureRegion(AssetLoader.catapult);
        txCataRegion.flip(false, true);
        catapultRegion = loader.load(txCataRegion, Gdx.files.internal("data/tile.psh"));

        TextureRegion txPeasRegion = new TextureRegion(AssetLoader.peasant);
        txPeasRegion.flip(false, true);
        peasantRegion = loader.load(txPeasRegion, Gdx.files.internal("data/tile.psh"));
    }

	public void drawGame() {
		//System.out.println("GameRenderer - drawGame");

		//Draw a black background to prevent flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        unitTable.align(Align.left);

        TextureRegion tr = new TextureRegion(AssetLoader.unittray);
        NinePatch np = new NinePatch(tr);
        unitTable.setBackground(new NinePatchDrawable(np));

        stage.addActor(unitTable);

        createUnitDrags();
    }

    public HCImageButton getBtnByName(String btnName) {
        switch(TileName.valueOf(btnName)) {
            case footman:
                if(footImgBtn == null) {
                    footImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return footImgBtn;
            case archer:
                if(archImgBtn == null) {
                    archImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return archImgBtn;
            case king:
                if(kingImgBtn == null) {
                    kingImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return kingImgBtn;
            case queen:
                if(queenImgBtn == null) {
                    queenImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return queenImgBtn;
            case mage:
                if(mageImgBtn == null) {
                    mageImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return mageImgBtn;
            case rogue:
                if(rogueImgBtn == null) {
                    rogueImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return rogueImgBtn;
            case knight:
                if(knightImgBtn == null) {
                    knightImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return knightImgBtn;
            case catapult:
                if(cataImgBtn == null) {
                    cataImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return cataImgBtn;
            case peasant:
                if(peasImgBtn == null) {
                    peasImgBtn = new HCImageButton(btnName, stage, sBatch, world, 50, 50, true);
                }
                return peasImgBtn;
            default:
                Gdx.app.log("my app", "Couldn't get btn by name " + btnName);
                return null;
        }
    }

    public void createUnitDrags() {
        unitTable.clearChildren();
        int count = 0;

        for (String name : unitNames) {
            HCImageButton dragBtn = getBtnByName(name);

            if(count == 0) { //don't indent first unit as much
                unitTable.align(Align.left).add(dragBtn.getButton()).left().width(50).padLeft(5);
                count++;
                continue;
            }

            count ++;
            unitTable.add(dragBtn.getButton()).left().width(50).padLeft(40);
        }
    }

    //deprecated by generic above, but might be useful
/*    public void createFootmanDrag() {
        if(footImgBtn != null) {
            unitTable.removeActor(footImgBtn.getButton());
            unitTable.clearChildren(); //this resets the table and allows buttons to be placed in their original position
            //but it also forces me to redraw all the units not just this one.
            footImgBtn = null;
        }

        footImgBtn = new HCImageButton("footman", stage, sBatch, world, 50, 50, true);
        unitTable.add(footImgBtn.getButton()).expand().width(50).left().padLeft(5);
    }*/

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
                if(spGrassRegion == null) {
                    spGrassRegion =  new PolygonSprite(tregion);
                }
                return spGrassRegion;
            case footman:
                if(spFootRegion == null) {
                    spFootRegion =  new PolygonSprite(footRegion);
                }
                return spFootRegion;
            case archer:
                if(spArchRegion == null) {
                    spArchRegion =  new PolygonSprite(archRegion);
                }
                return spArchRegion;
            case king:
                if(spKingRegion == null) {
                    spKingRegion =  new PolygonSprite(kingRegion);
                }
                return spKingRegion;
            case queen:
                if(spQueenRegion == null) {
                    spQueenRegion =  new PolygonSprite(queenRegion);
                }
                return spQueenRegion;
            case mage:
                if(spMageRegion == null) {
                    spMageRegion =  new PolygonSprite(mageRegion);
                }
                return spMageRegion;
            case rogue:
                if(spRogueRegion == null) {
                    spRogueRegion =  new PolygonSprite(rogueRegion);
                }
                return spRogueRegion;
            case knight:
                if(spKnightRegion == null) {
                    spKnightRegion =  new PolygonSprite(knightRegion);
                }
                return spKnightRegion;
            case catapult:
                if(spCataRegion == null) {
                    spCataRegion =  new PolygonSprite(catapultRegion);
                }
                return spCataRegion;
            case peasant:
                if(spPeasRegion == null) {
                    spPeasRegion =  new PolygonSprite(peasantRegion);
                }
                return spPeasRegion;
            default:
                Gdx.app.log("my app", "Couldn't get tile polygon sprite " + tileType);
                return null;
        }
    }
}
