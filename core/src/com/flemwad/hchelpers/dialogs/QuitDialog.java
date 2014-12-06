package com.flemwad.hchelpers.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.flemwad.gameworld.GameRenderer;
import com.flemwad.menuworld.MenuRenderer;

public class QuitDialog extends CustomDialog {

    private Skin buttonSkin;
    private GameRenderer gRend;
    private MenuRenderer mRend;

    public QuitDialog (String title, Skin _buttonSkin, GameRenderer _gRend) {
        super(title, _buttonSkin);
        buttonSkin = _buttonSkin;
        gRend = _gRend;
        initialize();
    }

    public QuitDialog (String title, Skin _buttonSkin, MenuRenderer _mRend) {
        super(title, _buttonSkin);
        buttonSkin = _buttonSkin;
        mRend = _mRend;
        initialize();
    }

    private void initialize() {
        padTop(60); // set padding on top of the dialog title
        getButtonTable().defaults().height(60); // set buttons height
        getButtonTable().defaults().width(120); // set buttons width
        setModal(true);
        setMovable(false);
        setResizable(false);

        if(gRend != null) {
            this.text("Leave the game?"); // text appearing in the dialog
            this.button("Exit", new InputListener() { // button to exit app
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //go back to main menu and save stuffs
                    Gdx.app.exit();
                    return false;
                }
            });
            this.button("Cancel",new InputListener() { // button to exit app
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    gRend.disposeQuit();
                    return true;
                }
            });
        }
        else if (mRend != null) {
            this.text("Are you sure you want to quit?"); // text appearing in the dialog
            this.button("Yes", new InputListener() { // button to exit app
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //go back to main menu and save stuffs
                    Gdx.app.exit();
                    return false;
                }
            });
            this.button("No",new InputListener() { // button to exit app
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    mRend.disposeQuit();
                    return true;
                }
            });
        }


    }
}