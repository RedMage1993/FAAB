package com.ammonf.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Fritz on 5/12/2016.
 */
public class PlayState extends State {
    private Texture bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // open box up
        sb.draw(bird, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
