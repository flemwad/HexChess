package com.flemwad.hchelpers.dialogs;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CustomDialog extends Dialog {

    private Skin buttonSkin;

    public CustomDialog (String title, Skin _buttonSkin) {
        super(title, _buttonSkin);
        buttonSkin = _buttonSkin;
        initialize();
    }

    private void initialize() {
        padTop(60); // set padding on top of the dialog title
        getButtonTable().defaults().height(60); // set buttons height
        getButtonTable().defaults().width(120); // set buttons width
        setModal(true);
        setMovable(false);
        setResizable(false);
    }

    @Override
    public CustomDialog text(String text) {
        super.text(new Label(text, buttonSkin));
        return this;
    }

    /**
     * Adds a text button to the button table.
     * @param listener the input listener that will be attached to the button.
     */
    public CustomDialog button(String buttonText, InputListener listener) {
        TextButton button = new TextButton(buttonText, buttonSkin);
        button.addListener(listener);
        button(button);
        return this;
    }

    @Override
    public float getPrefWidth() {
        // force dialog width
        return 480f;
    }

    @Override
    public float getPrefHeight() {
        // force dialog height
        return 240f;
    }
}