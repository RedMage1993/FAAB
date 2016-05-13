package com.ammonf.game.states;

import com.ammonf.game.FAAB;
import com.ammonf.game.sprites.Bird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Fritz on 5/12/2016.
 */
public class PlayState extends State {
    private Bird bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300); //Texture("bird-good1.png");
        // yDown set to false means our bottom-left part is 0,0
        cam.setToOrtho(false, FAAB.WIDTH >> 1, FAAB.HEIGHT >> 1);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        // We're limiting the view of the screen
        // Bird will appear bigger and this will allow moving left to right
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // open box up
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
