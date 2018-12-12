package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import objects.Stingray;
import objects.Swimmer;
import util.Constants;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    Texture background1 = new Texture(px);
    Texture background2 = new Texture(px);
	int width = background1.getWidth();
	int height = background1.getHeight();
	Swimmer swimmer;
	BitmapFont font = new BitmapFont();
    public World b2world;
    private Box2DDebugRenderer renderer;
	
    public WorldRenderer (WorldController worldController, World b2world) 
	{ 
		this.worldController = worldController;
		this.b2world = b2world;
		renderer = new Box2DDebugRenderer(true,true,true,true,false,true);
		
		init();
	}
	
	private void init () 
	{ 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0,0);
		worldController.cameraHelper.setPosition(0, 0);
		worldController.cameraHelper.setZoom(.25f);
		swimmer = this.worldController.getswimmer();
		camera.update();
		font.getData().setScale(.1f);
	}
	/**
	 * Part of game loop to render all game objects on screen
	 */
	public void render () 
	{ 
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		renderBackground();
		//renderTestObjects();
		renderPlayer();
		
		renderEnemies();
		renderItems();
		renderGUI();
		//renderer.render(worldController.b2world, camera.combined);
	}
	private void renderItems() 
	{
		if(worldController.coins.size() > 0)
		{
			for(int i = 0; i < worldController.coins.size(); i++)
			{
				worldController.coins.get(i).render(batch);	
			}
		}
		if(worldController.flippers.size() > 0)
		{
			for(int i = 0; i < worldController.flippers.size(); i++)
			{
				worldController.flippers.get(i).render(batch);	
			}
		}
	}

	private void renderEnemies() 
	{
		if(worldController.stingrays.size() > 0)
		{
			for(int i = 0; i < worldController.stingrays.size(); i++)
			{
				worldController.stingrays.get(i).render(batch);	
			}
		}
		if(worldController.jellyfish.size() > 0)
		{
			for(int i = 0; i < worldController.jellyfish.size(); i++)
			{
				worldController.jellyfish.get(i).render(batch);	
			}
		}
	}

	/**
	 * Draws background image infinitely tiled to keep game moving infinitely
	 */
	private void renderBackground()
	{
		//loop background draw so that the first draw begins at the players current location and is only updated
		//when they move forward. so all x values will be a variable + something
		batch.begin();
				batch.draw(background1,-1, -1, 2f, 2.5f);
				batch.draw(background2,1, -1, 2f, 2.5f);
				batch.draw(background1,3, -1, 2f, 2.5f);
				batch.draw(background2,5, -1, 2f, 2.5f);
				batch.draw(background1,7, -1, 2f, 2.5f);
		batch.end();
	}
	private void renderGUI()
	{
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		batch.begin();
			//font.draw(batch, "swim game", -2, 2);
			//Assets.instance.fonts.defaultBig.draw(batch,"Score: " + swimmer.score, worldController.cameraHelper.getPosition().x + .3f, worldController.cameraHelper.getPosition().y + 475.0f);
			//Assets.instance.fonts.defaultBig.draw(batch,"Lives: " + swimmer.lives, worldController.cameraHelper.getPosition().x + 525f, worldController.cameraHelper.getPosition().y + 475.0f);
			if(swimmer.score > worldController.highscore)
			{
				Assets.instance.fonts.defaultBig.draw(batch,"HighScore: " + swimmer.score, 1f, 475.0f);
			}
			else
			{
				Assets.instance.fonts.defaultBig.draw(batch,"HighScore: " + worldController.highscore, 1f, 475.0f);
			}
			Assets.instance.fonts.defaultBig.draw(batch,"Score: " + swimmer.score, 1f, 450.0f);
			Assets.instance.fonts.defaultBig.draw(batch,"Flippers: " + swimmer.flippers, 500f, 450.0f);
			Assets.instance.fonts.defaultBig.draw(batch,"Lives: " + swimmer.lives,  500f,  475.0f);
		batch.end();
	}
	/**
	 * Draws player character (handled by swimmer class)
	 */
	private void renderPlayer()
	{
		swimmer.render(batch);
	}
	public void resize (int width, int height) 
	{ 
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) *width;
		camera.update();
	}
	/**
	 * Disposes of unused assets
	 */
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

