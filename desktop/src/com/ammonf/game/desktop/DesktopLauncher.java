package com.ammonf.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ammonf.game.FAAB;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FAAB.WIDTH;
        config.height = FAAB.HEIGHT;
        config.title = FAAB.TITLE;
		new LwjglApplication(new FAAB(), config);
	}
}
