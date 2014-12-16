package com.flemwad.hchelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;

public class AssetLoader {

	public static Texture tile;
	public static Texture htile;
	public static Texture optgear;
	public static Texture pausemenu;
	public static Texture trialbg;
	public static Texture mainmenu;
    public static Texture unittray;
    public static Texture footman;
    public static Texture archer;
    public static Texture king;
    public static Texture queen;
    public static Texture knight;
    public static Texture mage;
    public static Texture rogue;
    public static Texture catapult;
    public static Texture peasant;


    public static Skin uiSkin;
    public static BitmapFont font;

	
	public static void load() {
		tile = new Texture(Gdx.files.internal("data/tile.png"));
		tile.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		optgear = new Texture(Gdx.files.internal("data/optgear.png"));
		optgear.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		pausemenu = new Texture(Gdx.files.internal("data/pausemenu.png"));
		pausemenu.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		trialbg = new Texture(Gdx.files.internal("data/trialbg.png"));
		trialbg.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		mainmenu = new Texture(Gdx.files.internal("data/mainmenu.png"));
		mainmenu.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        unittray = new Texture(Gdx.files.internal("data/tabletray.9.png"));
        unittray.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        archer = new Texture(Gdx.files.internal("data/archer.png"));
        archer.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        //TODO: Use TexturePacker and Atlas files to read these all out of a single png of tiles
        footman = new Texture(Gdx.files.internal("data/footman.png"));
        footman.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        king = new Texture(Gdx.files.internal("data/king.png"));
        king.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        queen = new Texture(Gdx.files.internal("data/queen.png"));
        queen.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        knight = new Texture(Gdx.files.internal("data/knight.png"));
        knight.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        mage = new Texture(Gdx.files.internal("data/mage.png"));
        mage.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        rogue = new Texture(Gdx.files.internal("data/rogue.png"));
        rogue.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        catapult = new Texture(Gdx.files.internal("data/catapult.png"));
        catapult.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        peasant = new Texture(Gdx.files.internal("data/peasant.png"));
        peasant.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        font = new BitmapFont();
        uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
    }
	
	public static void dispose() {
        // We must dispose of the texture when we are finished.
		tile.dispose();
		htile.dispose();
		optgear.dispose();
		pausemenu.dispose();
		trialbg.dispose();
		mainmenu.dispose();
        unittray.dispose();
        font.dispose();
        uiSkin.dispose();

        footman.dispose();
        archer.dispose();
        mage.dispose();
        king.dispose();
        queen.dispose();
        catapult.dispose();
        peasant.dispose();
        rogue.dispose();
        knight.dispose();
    }
}
