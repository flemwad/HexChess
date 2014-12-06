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


    public static Skin uiSkin;
    public static BitmapFont font;

	
	public static void load() {
		tile = new Texture(Gdx.files.internal("data/tile.png"));
		tile.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
		htile = new Texture(Gdx.files.internal("data/hightile.png"));
		htile.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
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

        footman = new Texture(Gdx.files.internal("data/footman.png"));
        footman.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

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
        footman.dispose();
        font.dispose();
        uiSkin.dispose();
    }
}
