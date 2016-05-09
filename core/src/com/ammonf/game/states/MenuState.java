package com.ammonf.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Fritz on 5/6/2016.
 */
public class MenuState extends State {
    private Texture background;
    private Texture playButton;

    // protected -> public
    public MenuState(GameStateManager gsm) {
        super(gsm); // Calls State constructor with gsm

        background = new Texture("bg.png");
        playButton = new Texture("play.png"); // Shortcut: Ctrl+D = dup line.
    }

    // protected -> public
    @Override
    public void handleInput() {
        
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }
}