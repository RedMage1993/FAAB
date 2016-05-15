package com.ammonf.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Fritz on 5/15/2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime; // How long frame should stay in view until next
    private float currentFrameTime; // time current frame has used
    private int frameCount;
    private int frame; // current frame we are in

    // cycleTime is how long to get through frameCount frames in region
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();

        // A loop that splits apart a spritesheet
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount; // equal share among all frames
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) { // frame has been out long enough
            frame++;
            currentFrameTime = 0;
        }

        // Go back to first frame / cycle
        if (frame == frameCount)
            frame = 0;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

    public void dispose() {
        frames = null;
    }
}
