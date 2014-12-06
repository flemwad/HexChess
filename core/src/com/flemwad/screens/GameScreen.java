package com.flemwad.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flemwad.gameworld.GameWorld;
import com.flemwad.gameworld.GameRenderer;
import com.flemwad.hchelpers.InputHandler;
import com.flemwad.hexchess.HCGame;

public class GameScreen implements Screen {

    private HCGame theGame;
	private GameWorld world;
	private GameRenderer renderer;
    private InputMultiplexer multiplexer;

    public InputHandler handler;
    public Stage stage;
	
    public GameScreen(HCGame _theGame) {
        System.out.println("GameScreen Attached");

        theGame = _theGame;
        stage = new Stage();
        multiplexer = new InputMultiplexer();
        world = new GameWorld(theGame); // initialize world
        renderer = new GameRenderer(world, stage); // initialize renderer
        world.setRenderer(renderer); //to call certain rendering events from button input

        handler = new InputHandler(world, renderer.cam);
        multiplexer.addProcessor(handler);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
    	
    	switch(world.gameState) {
    		case Running:
    	        world.update(delta); // GameWorld updates 
    	        renderer.drawGame(); // GameRenderer renders
                //renderer.disposePause();
    			break;
    		case Paused:
    			//don't accept updates and drawGame pause

                //create pause menu here, but with a class instead of just drawing as sprite
    			world.updatePaused(delta);
    			renderer.drawGame();
    			renderer.renderPause();
    			break;
    		case Setup:
                world.updateSetup(delta); // GameWorld updates
                renderer.drawGame(); // GameRenderer renders
    			break;
    	}
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("GameScreen - resizing");
    }

    @Override
    public void show() {
        System.out.println("GameScreen - show called");
    }

    @Override
    public void hide() {
        System.out.println("GameScreen - hide called");     
    }

    @Override
    public void pause() {
        System.out.println("GameScreen - pause called");        
    }

    @Override
    public void resume() {
        System.out.println("GameScreen - resume called");       
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
