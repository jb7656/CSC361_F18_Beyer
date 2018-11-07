package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
	
	Pixmap px= new Pixmap(Gdx.files.internal("background.png"));
    Texture background = new Texture(px);
    
	
    public WorldRenderer (WorldController worldController) 
	{ 
		this.worldController = worldController;
		init();
	}
	
	private void init () 
	{ 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		worldController.cameraHelper.setPosition(0, 0);
		worldController.cameraHelper.setZoom(.25f);
		
		background.setWrap(TextureWrap.Repeat, TextureWrap.ClampToEdge);
		camera.update();
	}
	
	public void render () 
	{ 
		//renderTestObjects();
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		renderBackground();
	}
	private void renderBackground()
	{
		batch.begin();
			//batch.draw(background, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				batch.draw(background,0, 0, 2, 1.25f);
			
		batch.end();
	}
	public void resize (int width, int height) 
	{ 
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) *width;
		camera.update();
	}
	
	@Override public void dispose () 
	{ 
		batch.dispose();
	}
	
	@SuppressWarnings("unused")
	private void renderTestObjects() 
	{
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(Sprite sprite : worldController.testSprites) 
		{
			sprite.draw(batch);
		}
		batch.end();
	}
	
}

