package com.flemwad.hchelpers.buttons;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.flemwad.gameworld.GameWorld;
import com.flemwad.hchelpers.AssetLoader;
import com.flemwad.menuworld.MenuWorld;

public class HCTextButton implements ApplicationListener {

    private SpriteBatch sBatch;
    private GameWorld gWorld;
    private MenuWorld mWorld;
    private Stage stage;
    private TextButton button;
    private String buttonName;
    private String text;

    private float height;
    private float width;
    private float xPos;
    private float yPos;

    public HCTextButton(String _buttonName, String _text, Stage _stage, SpriteBatch _sBatch, GameWorld _world,
                         float _width, float _height, float _xPos, float _yPos) {

        buttonName = _buttonName;
        text = _text;
        gWorld = _world;
        sBatch = _sBatch;
        stage = _stage;
        height = _height;
        width = _width;
        xPos = _xPos;
        yPos = _yPos;

        this.create();
    }

    public HCTextButton(String _buttonName, String _text, Stage _stage, SpriteBatch _sBatch, MenuWorld _world,
                        float _width, float _height, float _xPos, float _yPos) {

        buttonName = _buttonName;
        text = _text;
        mWorld = _world;
        sBatch = _sBatch;
        stage = _stage;
        height = _height;
        width = _width;
        xPos = _xPos;
        yPos = _yPos;

        this.create();
    }

    public void setHeight(float value) {
        if(button != null) {
            button.setHeight(value);
        }
    }

    public void setWidth(float value) {
        if(button != null) {
            button.setWidth(value);
        }
    }

    public void setPosition(float x, float y) {
        if(button != null) {
            button.setPosition(x, y);
        }
    }

    public TextButton getButton() {
        if(button != null) {
            return button;
        }
        return null;
    }

    private void callWorldButton(float x, float y) {
        if(gWorld != null) {
            gWorld.buttonInput(buttonName, x, y);
        }
        else if (mWorld != null) {
            mWorld.buttonInput(buttonName, x, y);
        }
    }

    @Override
    public void create() {
        button = new TextButton(text, AssetLoader.uiSkin, "toggle");
        button.setPosition(xPos, yPos);
        button.setHeight(height);
        button.setWidth(width);

        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed");
                callWorldButton(x, y);
                return true;
            }
        });

        //stage.addActor(button);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void render() {
        stage.act();
        sBatch.begin();
        stage.draw();
        sBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.getRoot().removeActor(button);
        button = null;
    }
}
