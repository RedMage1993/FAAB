package com.ammonf.game.states;

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
        bird = new Bird(50, 100); //Texture("bird-good1.png");
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
        sb.begin(); // open box up
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
