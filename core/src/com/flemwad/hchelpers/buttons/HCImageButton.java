package com.flemwad.hchelpers.buttons;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.flemwad.gameworld.GameWorld;
import com.flemwad.menuworld.MenuWorld;

public class HCImageButton implements ApplicationListener {

    private TextureAtlas buttonsAtlas;
    private SpriteBatch sBatch;
    private GameWorld gWorld;
    private MenuWorld mWorld;
    private Stage stage;
    private Skin buttonSkin;
    private ImageButton button;
    private String buttonName;
    private boolean draggable = false;

    private float height;
    private float width;
    private float xPos;
    private float yPos;

    public HCImageButton(String _buttonName, Stage _stage, SpriteBatch _sBatch, GameWorld _world,
                              float _width, float _height, float _xPos, float _yPos) {
        buttonName = _buttonName;
        gWorld = _world;
        sBatch = _sBatch;
        stage = _stage;
        height = _height;
        width = _width;
        xPos = _xPos;
        yPos = _yPos;

        this.create();
        this.setPosition(xPos, yPos);
    }

    public HCImageButton(String _buttonName, Stage _stage, SpriteBatch _sBatch, MenuWorld _world,
                         float _width, float _height, float _xPos, float _yPos) {
        buttonName = _buttonName;
        mWorld = _world;
        sBatch = _sBatch;
        stage = _stage;
        height = _height;
        width = _width;
        xPos = _xPos;
        yPos = _yPos;

        this.create();
        this.setPosition(xPos, yPos);
    }

    public HCImageButton(String _buttonName, Stage _stage, SpriteBatch _sBatch, GameWorld _world,
                         float _width, float _height, boolean _draggable) {
        buttonName = _buttonName;
        gWorld = _world;
        sBatch = _sBatch;
        stage = _stage;
        height = _height;
        width = _width;

        draggable = _draggable;
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

    private void callWorldButton(float x, float y) {
        if(gWorld != null) {
            gWorld.buttonInput(buttonName, x, y);
        }
        else if (mWorld != null) {
            mWorld.buttonInput(buttonName, x, y);
        }
    }

    public ImageButton getButton() {
        if(button != null) {
            return button;
        }
        return null;
    }

    public void initDrag() {
        button.addListener(new DragListener() {
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                //super.touchDragged(event, x, y, pointer);

                float dx = x-button.getWidth()*0.5f;
                float dy = y-button.getHeight()*0.5f;
                button.setPosition(button.getX() + dx, button.getY() + dy);
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Test");
                gWorld.btnDragging = true;

                return true;
                //make new replacement buttn here if they haven't exceeded unit count
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gWorld.handleUnitDrop(buttonName, Gdx.input.getX(), Gdx.input.getY());
                Gdx.app.log("my app", "Test");
            }
        });
    }

    @Override
    public void create() {
        buttonsAtlas = new TextureAtlas("data/hexchess.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);

        ImageButtonStyle style = new ImageButtonStyle();
        style.up = buttonSkin.getDrawable(buttonName);
        style.down = buttonSkin.getDrawable(buttonName);

        button = new ImageButton(style);
        button.setHeight(height);
        button.setWidth(width);

        if(draggable) {
            initDrag();
        }
        else {
            button.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("my app", "Pressed");
                    callWorldButton(x, y);
                    return true;
                }
            });
        }
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
        buttonSkin.dispose();
        buttonsAtlas.dispose();
        stage.getRoot().removeActor(button);
        button = null;
    }
}
