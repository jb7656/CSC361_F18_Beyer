package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import util.Constants;

/**
 * Class for handling the graphics side of the game loop
 * @author jb7656
 *
 */
public class WorldRenderer implements Disposable 
{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController worldController;
	
	public WorldRenderer (WorldController worldController) 
	{ }
	
	private void init () { }
	
	public void render () { }
	
	public void resize (int width, int height) { }
	
	@Override public void dispose () { }
}

