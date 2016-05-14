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

    private boolean moving;

    // x and y are our starting positions
    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0); // so not moving

        bird = new Texture("bird-good1.png");

        moving = false;
    }

    // update to send delta time to bird class and allow calculations
    public void update(float dt) {
        // Use velocity.add and velocity.scale to adjust velocity
        // Use dt to correct based on FPS on machine
        velocity.scl(dt);
        // To update the Bird's position
        position.add(0, velocity.y, 0);
        // finish with velocity.scale(1/dt) to undo the dt effect.
        velocity.scl(1/dt);

        // Make sure it's whole again for friction to work correctly
        velocity.y = Math.round(velocity.y);

        // apply friction
        if (velocity.y != 0) {
            if (velocity.y > 0) {
                velocity.y -= 25;
            } else {
                velocity.y += 25;
            }
        } else {
            if (moving) {
                moving = false;
            }
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }

    // prevent continuous movement
    // but allows paused-continuous movement
    // in case user wants to hold down
    public boolean isMoving() {
        return moving;
    }

    public void ascend() {
        velocity.y = 400;
        moving = true;
    }

    public void descend() {
        velocity.y = -400;
        moving = true;
    }
}
