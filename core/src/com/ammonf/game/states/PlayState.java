package com.ammonf.game.states;

import com.ammonf.game.FAAB;
import com.ammonf.game.sprites.BadBall;
import com.ammonf.game.sprites.Ball;
import com.ammonf.game.sprites.Bird;
import com.ammonf.game.sprites.GoodBall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Fritz on 5/12/2016.
 */
public class PlayState extends State {
    private static final int BALL_SPACING_HORI = 125; // space between Ball's x-axis
    private static final int BALL_INIT_SPACING = 200;
    private static final int BALL_COUNT = 3; // max No. of vertical Ball's at any given time

    private Bird bird;
    private Texture bg;
    private InputProcessor ip;
    private int lastKey;
    //private GoodBall goodBall; // Will generate certain random placement of either this
    //private BadBall badBall; // or this but whatever it is, good or empty required.
    private Random rand;

    private boolean goodBallAdded;

    // TODO: Create Balls as necessary
    // They will position themselves accordingly on the y-axis
    // Require first Ball to be a GoodBall, shown/hidden
    // The the next two can be GoodBall or BadBall, shown/hidden
    // Repeat that BALL_COUNT times but push the x position with BALL_SPACING_HORI
    private Array<Ball> balls;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        bird = new Bird(50, 300); //Texture("bird-good1.png");
        // yDown set to false means our bottom-left part is 0,0
        cam.setToOrtho(false, FAAB.WIDTH >> 1, FAAB.HEIGHT >> 1);
        bg = new Texture("bg-new.png");

        balls = new Array<Ball>();
        rand = new Random();

        // Generate the balls here
        // For every vertical line of Balls, a GoodBall must be present
        for (int i = 0; i < BALL_COUNT; i++) {
            goodBallAdded = false;
            for (int j = 1; j < 3; j++) {
                switch (rand.nextInt(2)) {
                    case 0:
                        if (!goodBallAdded) {
                            balls.add(new GoodBall(BALL_INIT_SPACING + (i + 1) * (BALL_SPACING_HORI + Ball.WIDTH)));
                            goodBallAdded = true;
                            break;
                        }
                    case 1:
                        balls.add(new BadBall(BALL_INIT_SPACING + (i + 1) * (BALL_SPACING_HORI + Ball.WIDTH)));
                        break;
                }
            }

            if (!goodBallAdded)
                balls.add(new GoodBall(BALL_INIT_SPACING + (i + 1) * (BALL_SPACING_HORI + Ball.WIDTH)));
            else
                balls.add(new BadBall(BALL_INIT_SPACING + (i + 1) * (BALL_SPACING_HORI + Ball.WIDTH)));
        }

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

        cam.position.x = bird.getPosition().x + 70;

        // If Ball outside of viewport on the left,
        // make it reappear on the other side
        for (Ball ball : balls) {
            if (cam.position.x - (cam.viewportWidth / 2) > ball.getPosition().x + ball.getTexture().getWidth()) {
                ball.reposition(ball.getPosition().x + ((ball.getTexture().getWidth() + BALL_SPACING_HORI) * BALL_COUNT));
            }

            if (ball.collides(bird.getBounds()) && ball.getBallType() == Ball.BALL_TYPE.BAD) {
                gsm.set(new PlayState(gsm));
                return;
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        // We're limiting the view of the screen
        // Bird will appear bigger and this will allow moving left to right
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // open box up
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        // Render balls only if shown
        for (Ball ball : balls) {
            if (ball.isShown()) {
                sb.draw(ball.getTexture(),
                        ball.getPosition().x, ball.getPosition().y);
            }
        }

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();

        for (int i = balls.size - 1; i >= 0; i--) {
            balls.get(i).dispose();
            balls.removeIndex(i); // Lose reference of Ball
        }

        balls = null;
        ip = null;
        rand = null;
    }
}
