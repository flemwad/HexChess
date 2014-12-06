package com.flemwad.hchelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.flemwad.gameworld.GameWorld;
import com.flemwad.menuworld.MenuWorld;

public class InputHandler implements InputProcessor {

	private GameWorld game;
	private MenuWorld menu;
	private OrthographicCamera cam;
	private Vector3 touchPoint;
	
	public InputHandler(GameWorld _game, OrthographicCamera _cam) {
		touchPoint = new Vector3();
		game = _game;
		cam = _cam;
	}
	
	public InputHandler(MenuWorld _menu, OrthographicCamera _cam) {
		touchPoint = new Vector3();
		menu = _menu;
		cam = _cam;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {	
		cam.unproject(touchPoint.set(screenX, screenY, 0));
		float x = touchPoint.x;
		float y = touchPoint.y;
		if(menu != null) {
			menu.touchInput(Gdx.input.getX(), Gdx.input.getY());
			return false; //return false so other multiplexed handlers may fire touchUp
		}
		else if (game != null) {
			game.touchInput(Gdx.input.getX(), Gdx.input.getY());
			return false;
		}
		
		System.out.println("No World initialized for touch!");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
