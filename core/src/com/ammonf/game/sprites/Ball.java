package com.ammonf.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Fritz on 5/14/2016.
 */
public abstract class Ball {
    // These are the possible locations for Ball
    private static final Integer LEVELS[] = {300, 355, 245};
    private static Array<Integer> nextBallLevel = new Array<Integer>();

    public enum BALL_TYPE{GOOD, BAD};

    private BALL_TYPE ballType;
    protected Texture ball;
    private Vector2 position;
    private Random rand;
    private int level;
    private boolean shown;
    private Rectangle bounds;

    public static final int WIDTH = 30;

    protected Ball(int x, String spritePath, BALL_TYPE bt) {
        ballType = bt;
        ball = new Texture(spritePath);
        rand = new Random(); // Used to set ball at random Y-axis

        // Choose a random location
        // RNG between 0 (inclusive) and 3 (exclusive)
        reposition(x);
    }

    public Texture getTexture() {
        return ball;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isShown() { return shown; }

    public void reposition(float x) {
        if (nextBallLevel.size == 0)
            nextBallLevel.addAll(LEVELS);

        // TODO: Can update to be random
        // It would be better with a non-uniform dist
        shown = true;//rand.nextBoolean();

        level = rand.nextInt(nextBallLevel.size);
        position = new Vector2(x, nextBallLevel.get(level));
        nextBallLevel.removeIndex(level);

        bounds = new Rectangle(x, position.y, ball.getWidth(), ball.getHeight());
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public BALL_TYPE getBallType() {
        return ballType;
    }
}
