package com.ammonf.game.states;

import com.ammonf.game.FAAB;
import com.ammonf.game.sprites.BadBall;
import com.ammonf.game.sprites.Bird;
import com.ammonf.game.sprites.GoodBall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Fritz on 5/12/2016.
 */
public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    private InputProcessor ip;
    private int lastKey;
    private GoodBall goodBall; // Will generate certain random placement of either this
    //private BadBall badBall; // or this but whatever it is, good or empty required.

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300); //Texture("bird-good1.png");
        // yDown set to false means our bottom-left part is 0,0
        cam.setToOrtho(false, FAAB.WIDTH >> 1, FAAB.HEIGHT >> 1);
        bg = new Texture("bg.png");

        goodBall = new GoodBall(100);

        lastKey = -1;

        // Going to use this to watch for last key that was pressed
        ip = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                lastKey = keycode;
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                lastKey = -1;
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        };

        Gdx.input.setInputProcessor(ip);
    }

    @Override
    protected void handleInput() {
        if (!bird.isMoving()) {
            if (lastKey != -1) { // Will be -1 if keyUp event occurs
                switch (lastKey) {
                    case Input.Keys.UP:
                        bird.ascend();
                        break;
                    case Input.Keys.DOWN:
                        bird.descend();
                        break;
                    default:
                        // Donkey ;)
                }
            }
        }
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
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.draw(goodBall.getTexture(), goodBall.getPosition().x, goodBall.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
