package com.ammonf.game.states;

import com.ammonf.game.FAAB;
import com.badlogic.gdx.Gdx;
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

        background = new Texture("bg-new.png");
        playButton = new Texture("play.png"); // Shortcut: Ctrl+D = dup line.
    }

    // protected -> public
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm)); // put PlayState at top of stack
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); // always checking input to see if user did anything
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open box up

        // Drawing begins from bottom-left
        sb.draw(background, 0, 0, FAAB.WIDTH, FAAB.HEIGHT);
        // Dim of button used without specification
        // Centered; using bit shifting instead of division
        sb.draw(playButton, (FAAB.WIDTH >> 1) - (playButton.getWidth() >> 1),
                (FAAB.HEIGHT >> 1) - (playButton.getHeight() >> 1));

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}