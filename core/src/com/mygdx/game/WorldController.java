package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import objects.Jellyfish;
import objects.Stingray;
import objects.Swimmer;
import util.CameraHelper;

import java.util.ArrayList;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import screens.MenuScreen;

/**
 * Class for handling all game logic and running the game loop
 * @author jb7656
 *
 */
public class WorldController extends InputAdapter
{
	private static final String TAG = WorldController.class.getName();
	public ArrayList <Stingray> stingrays;
	public ArrayList <Jellyfish> jellyfish;
	Stingray x;
	Jellyfish y;
	public Sprite[] testSprites;
	public int selectedSprite;
	public CameraHelper cameraHelper;
	public Swimmer swimmer1;
	private Game game;
	private final double PERCENT_CHANCE = 1000;
	private double hit = 10;
	private double rand;
	Vector2 vec;
	public World b2world;
	public float swimmer_x;
	public float swimmer_y;
	public B2ContactListener cl;
	
	private void backToMenu () 
	{
		    // switch to menu screen
		    game.setScreen(new MenuScreen(game));
	}
	
	public WorldController (Game game) 
	{ 
		this.game = game;
		init();
	}
	/**
	 * Sets up classes and variables necessary to start game
	 */
	private void init () 
	{ 
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		b2world = new World(new Vector2(0, 0), true);

		//Initialize assets and player character
		Assets asset = new Assets();
		asset.init(new AssetManager());
		swimmer1 = new Swimmer(b2world);
		//Need to set swimmer as selected test sprite
		//cameraHelper.setTarget(swimmer1.swimmer.image);??
		cameraHelper.setTarget(swimmer1);
		stingrays = new ArrayList <Stingray>();
		jellyfish = new ArrayList <Jellyfish>();
		cl = new B2ContactListener(swimmer1);
		b2world.setContactListener(cl);

	}
	/**
	 * Game loop to be repeated 60 times/second
	 * @param deltaTime
	 */
	public void update (float deltaTime) 
	{ 
		handleDebugInput(deltaTime);
		//updateTestObjects(deltaTime);
		cameraHelper.update(deltaTime);
		handle_enemies();
		b2world.step(1/60f, 6, 2);
		if(swimmer1.getlives() < 1)
		{
			backToMenu();
		}
	}
	public void handle_enemies()
	{
		//Handle stingrays first
		rand = Math.random() * PERCENT_CHANCE;
		swimmer_x = swimmer1.getXPosition();
		swimmer_y = swimmer1.getYPosition();
		if (rand < hit && stingrays.size() < 5)
		{
			vec = cameraHelper.getPosition();
			Stingray x;
			if(swimmer_x > 1)
			{
				x = new Stingray(swimmer_x + 1, swimmer_y,b2world);
			}
			else
			{
				x = new Stingray(swimmer_x + 3, swimmer_y,b2world);
			}
			stingrays.add(x);
		}
		for(int i = 0; i < stingrays.size();i++)
		{
			x = stingrays.get(i);
			x.update();
			if(x.getXPosition() < -1f)
			{
				stingrays.remove(x);
			}
		}
		//Handle jellyfish 2nd
		rand = Math.random() * PERCENT_CHANCE;
		if (rand < hit && jellyfish.size() < 5)
		{
			vec = cameraHelper.getPosition();
			y = new Jellyfish(swimmer_x , swimmer_y -1);
			jellyfish.add(y);
		}
		for(int i = 0; i < jellyfish.size();i++)
		{
			y = jellyfish.get(i);
			y.update();
			if(y.getYPosition() > 2f)
			{
				jellyfish.remove(y);
			}
		}
	}
	
	private void initTestObjects() 
	{
		// Create new array for 5 sprites
		testSprites = new Sprite[5];
		// Create empty POT-sized Pixmap with 8 bit RGBA pixel data
		int width = 32;
		int height = 32;
		Pixmap pixmap = createProceduralPixmap(width, height);
		// Create a new texture from pixmap data
		Texture texture = new Texture(pixmap);
		// Create new sprites using the just created texture
		for (int i = 0; i < testSprites.length; i++) {
		Sprite spr = new Sprite(texture);
		// Define sprite size to be 1m x 1m in game world
		spr.setSize(1, 1);
		// Set origin to sprite's center
		spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
		// Calculate random position for sprite
		float randomX = MathUtils.random(-2.0f, 2.0f);
		float randomY = MathUtils.random(-2.0f, 2.0f);
		spr.setPosition(randomX, randomY);
		// Put new sprite into array
		testSprites[i] = spr;
		}
		// Set first sprite as selected one
		selectedSprite = 0;
	}
	private Pixmap createProceduralPixmap (int width, int height) 
	{
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		// Fill square with red color at 50% opacity
		pixmap.setColor(1, 0, 0, 0.5f);
		pixmap.fill();
		// Draw a yellow-colored X shape on square
		pixmap.setColor(1, 1, 0, 1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		// Draw a cyan-colored border around square
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}
	private void updateTestObjects(float deltaTime) 
	{
		// Get current rotation from selected sprite
		float rotation = testSprites[selectedSprite].getRotation();
		// Rotate sprite by 90 degrees per second
		rotation += 90 * deltaTime;
		// Wrap around at 360 degrees
		rotation %= 360;
		// Set new rotation value to selected sprite
		testSprites[selectedSprite].setRotation(rotation);
	}
	private void handleDebugInput (float deltaTime) 
	{
		if (Gdx.app.getType() != ApplicationType.Desktop) return;
		// Selected Sprite Controls
		float sprMoveSpeed = 1 * deltaTime;
		if (Gdx.input.isKeyPressed(Keys.A)) moveSelectedSprite(
		-sprMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.D))
		moveSelectedSprite(sprMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.W)) moveSelectedSprite(0,
		sprMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.S)) moveSelectedSprite(0,
		-sprMoveSpeed);
		// Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) camMoveSpeed *=
		camMoveSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.LEFT)) moveCamera(-camMoveSpeed,
		0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) moveCamera(camMoveSpeed,
		0);
		if (Gdx.input.isKeyPressed(Keys.UP)) moveCamera(0, camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.DOWN)) moveCamera(0,
		-camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
		cameraHelper.setPosition(0, 0);
		// Camera Controls (zoom)
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) camZoomSpeed *=
		camZoomSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.COMMA))
		cameraHelper.addZoom(camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.PERIOD)) cameraHelper.addZoom(
		-camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.SLASH)) cameraHelper.setZoom(1);
	}

	private void moveCamera (float x, float y) 
	{
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}
	private void moveSelectedSprite (float x, float y) 
	{
		//testSprites[selectedSprite].translate(x, y);
		swimmer1.updateMotion(x,y);
		//swimmer1.updateMotionY(y);
	}
	
	@Override public boolean keyUp (int keycode) 
	{
		if (keycode == Keys.ESCAPE ) 
		{
			backToMenu();
		}
		return false;
	}
	public Swimmer getswimmer()
	{
		return swimmer1;
	}
}

