package com.ammonf.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Fritz on 5/6/2016.
 * Abstract because we'll create instances that extend State.
 *
 * TODO: Finish defining class
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    // gsm will be in charge of managing states so that an extended State
    // could popup on top of an active extended State
    protected GameStateManager gsm; // Will be defined later

    // Constructor
    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    // delta time; difference in time between one frame render and next frame render
    public abstract void update(float dt);
    // sb is container for everything (textures) for rendering to the screen
    public abstract void render(SpriteBatch sb);
}
