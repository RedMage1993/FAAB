package com.ammonf.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Fritz on 5/14/2016.
 */
public abstract class Ball {
    // These are the possible locations for Ball
    private static final Integer LEVELS[] = {300, 355, 245};
    private static Array<Integer> nextBallLevel;

    protected Texture ball;
    private Vector2 position;
    private Random rand;
    private int level;

    protected Ball(int x, String spritePath) {
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

    public void reposition(int x) {
        if (nextBallLevel == null)
            nextBallLevel = new Array<Integer>();
        else if (nextBallLevel.size == 0) {
            nextBallLevel.addAll(LEVELS);
        }

        level = rand.nextInt(nextBallLevel.size);
        position = new Vector2(x, nextBallLevel.get(level));
        nextBallLevel.removeIndex(level);
    }
}
