package com.ammonf.game.sprites;

import com.ammonf.game.FAAB;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Fritz on 5/13/2016.
 */
public class Bird  {
    // If we wanted gravity we'd probably add it here
    // But for this game, we'll allow user to fly upwards
    // and also lower altitude
    // Only 3 different levels of altitude
    private static final int MOVEMENT = 190;

    private Vector3 position; // holds an x,y,z axis (only using xy)
    private Vector3 velocity;
    private Rectangle bounds;

    private Texture bird;

    private boolean moving;

    // x and y are our starting positions
    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0); // so not moving

        bird = new Texture("bird-good1.png");

        moving = false;

        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    // update to send delta time to bird class and allow calculations
    // TODO: Learn the mathematics, or the physics, required for motion below
    public void update(float dt) {
        // Use velocity.add and velocity.scale to adjust velocity
        // Use dt to correct based on FPS on machine
        position.add(MOVEMENT * dt, 0, 0);
        // Only allow movement if within bounds
        if ((velocity.y < 0 && position.y > 0) ||
                (velocity.y > 0 && ((position.y + bird.getHeight()) < (FAAB.HEIGHT >> 1)))) {
            velocity.scl(dt);
            // To update the Bird's position
            position.add(0, velocity.y, 0);

            // finish with velocity.scale(1/dt) to undo the dt effect.
            velocity.scl(1 / dt);

            // Make sure it's whole again for friction to work correctly
            velocity.y = Math.round(velocity.y);
        } else {
            // Cannot move, so skip to allowing new movement
            stopMoving();
        }

        // Apply bounds within viewport
        // Formula for rate of 10 and friction of 1 is (((10 + 1) * 10) / 2)
        // Calculated to be 55, so 300 + 55 = 355 is where we want to stay below
        if (position.y > 355) {
            position.y = 355;
            stopMoving(); // Need to avoid input lag at boundaries
        } else if (position.y < 245) {
            position.y = 245;
            stopMoving();
        }

        bounds.setPosition(position.x, position.y);

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

    private void stopMoving() {
        velocity.y = 0;
        moving = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        bird.dispose();

        position = null;
        velocity = null;
        bounds = null;

        System.gc();
    }
}
