package com.ammonf.game;

import com.ammonf.game.states.GameStateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FAAB extends ApplicationAdapter {
    // static so it is a class variable; a global one
    // shared by all instances of the class
	public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Fwee As A Boyd";

    private GameStateManager gsm;
    private SpriteBatch batch;
    //Texture img;
	
	@Override
	public void create () {
        gsm = new GameStateManager();
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

        Gdx.gl.glClearColor(1, 0, 0, 1); // Wipes screen for redraw
	}

	@Override
	public void render () {
        // Need to make gamestatemanager update before anything below executes
        // Moving glClearColor to create()
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // getDeltaTime provides float: dt
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}
}
