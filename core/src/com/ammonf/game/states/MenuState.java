package com.ammonf.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Fritz on 5/6/2016.
 */
public class MenuState extends State {

    // protected -> public
    public MenuState(GameStateManager gsm) {
        super(gsm); // Calls State constructor with gsm
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