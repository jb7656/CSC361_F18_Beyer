package com.mygdx.game;
	
import com.badlogic.gdx.ApplicationListener;
import com.mygdx.game.WorldController;
import com.mygdx.game.WorldRenderer;

/**
 * Main Class for starting the game loop
 * @author Jeff beyer
 *
 */
public class CSC361_F18_Beyer implements ApplicationListener 
{
		private static final String TAG = CSC361_F18_Beyer.class.getName();
		private WorldController worldController;
		private WorldRenderer worldRenderer;
		
		@Override public void create () { }
		@Override public void render () { }
		@Override public void resize (int width, int height) { }
		@Override public void pause () { }
		@Override public void resume () { }
		@Override public void dispose () { }
}
