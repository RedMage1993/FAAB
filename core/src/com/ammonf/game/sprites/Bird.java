package com.ammonf.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Fritz on 5/13/2016.
 */
public class Bird  {
    // If we wanted gravity we'd probably add it here
    // But for this game, we'll allow user to fly upwards
    // and also lower altitude
    // Only 3 different levels of altitude

    private Vector3 position; // holds an x,y,z axis (only using xy)
    private Vector3 velocity;

    private Texture bird;

    // x and y are our starting positions
    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0); // so not moving

        bird = new Texture("bird-good1.png");
    }

    // update to send delta time to bird class and allow calculations
    public void update(float dt) {
        // Use velocity.add and velocity.scale to modify velocity
        // Probably 1/dt to slow down movement in a direction rather than
        // gravity's effect of increasing (until terminal velocity)
        //
        // position.sub(0, velocity.y, 0) will be for going up
        // add will be for going down
        //
        // finish with velocity.scale(dt) to undo the 1/dt effect.
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
}
