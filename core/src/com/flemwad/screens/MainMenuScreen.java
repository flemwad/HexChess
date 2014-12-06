package com.flemwad.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flemwad.hchelpers.AssetLoader;
import com.flemwad.hchelpers.InputHandler;
import com.flemwad.hexchess.HCGame;
import com.flemwad.menuworld.MenuRenderer;
import com.flemwad.menuworld.MenuWorld;

public class MainMenuScreen implements Screen { 

	public MenuRenderer renderer;
	public MenuWorld menuWorld;

    private InputMultiplexer multiplexer;
    public InputHandler handler;
	private HCGame theGame;
    private Stage stage;

	
	public MainMenuScreen(HCGame _theGame) {
		theGame = _theGame;
        stage = new Stage();
        multiplexer = new InputMultiplexer();
        menuWorld = new MenuWorld(theGame);
		renderer = new MenuRenderer(menuWorld, stage);
        menuWorld.setRenderer(renderer);

        handler = new InputHandler(menuWorld, renderer.cam);
        multiplexer.addProcessor(handler);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
	}
	
	@Override
	public void render(float delta) {
		renderer.render();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
//        renderer = null;
//        menuWorld = null;
//
//        multiplexer = null;
//        handler = null;
//
//        stage.dispose();
//        stage = null;
	}
	
}
