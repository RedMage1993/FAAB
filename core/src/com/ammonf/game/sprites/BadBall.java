package com.ammonf.game.sprites;

/**
 * Created by Fritz on 5/14/2016.
 */
public class BadBall extends Ball {
    public BadBall(int x) {
        super(x, "ball-bad1.png", BALL_TYPE.BAD);
    }

    // We may add some GoodBall specific methods or data below
    // For example, decrease points? Or lose game.
}
