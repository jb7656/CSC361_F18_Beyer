package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetSwimmer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
/**
 * Class for handling the actions/rendering of the playable character
 * @author jb7656
 */
public class Swimmer extends AbstractGameObject
{
	public AssetSwimmer swimmer;
	private float x_position;
	private float y_position;
	private final float MIN_X = -.52f;
	private final float MIN_Y = -.875f;
	private final float MAX_Y = 1.28f;
	private final float MAX_X = 8f;
	int lives;
	public int score;
	int flippers = 0;
	float SPEED_CONSTANT = 1.0f;
	BodyDef bodydef;
	Body body;
	FixtureDef fxdef;
	PolygonShape box;
	ParticleEffect pe;
	boolean is_hit = false;
	
	public Swimmer(World world1)
	{
		swimmer = Assets.swimmer;
		x_position = .5f; //initializes player to beginning of level
		y_position = -.5f;
		bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		bodydef.position.set(.5f,-.5f);
		
		body = world1.createBody(bodydef);
		
		body.setUserData(this);
		fxdef = new FixtureDef();
		box = new PolygonShape();
		box.setAsBox(.01f,.01f);
		fxdef.shape = box;
		fxdef.isSensor = true;
		body.createFixture(fxdef);
		lives = 3;
		score = 0;
		
		pe = new ParticleEffect();
	    //pe.load(Gdx.files.internal("blood_particle.pfx"),Gdx.files.internal("../core/assets"));
	    //pe.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	    //pe.start();
	}
	public void render(SpriteBatch batch2)
	{
		if(is_hit)
		{
			batch2.begin();
				batch2.draw(swimmer.image,body.getPosition().x,body.getPosition().y,.20f,.20f); //draws swimmer at its current location
			//draw particle
			//pe.start();
			//pe.getEmitters().first().setPosition(body.getPosition().x,body.getPosition().y);
			//pe.draw(batch2);
			is_hit = false;
			batch2.end();
		}
		else
		{
			batch2.begin();
			batch2.draw(swimmer.image,body.getPosition().x,body.getPosition().y,.20f,.20f); //draws swimmer at its current location
			batch2.end();
		}
	}
	public void updateMotion(float x,float y)
	{
		if(flippers > 0)
		{
			x = x * SPEED_CONSTANT;
			y = y * SPEED_CONSTANT;
			x_position = clamp(x_position + x, MAX_X, MIN_X);
			y_position = clamp(y_position + y, MAX_Y, MIN_Y);
			body.setTransform(x_position, y_position, 0);
		}
		else
		{
			x_position = clamp(x_position + x, MAX_X, MIN_X);
			y_position = clamp(y_position + y, MAX_Y, MIN_Y);
			body.setTransform(x_position, y_position, 0);
		}
		
		
	}
	@Override
	public void updateMotionY(float y)
	{
		y_position = clamp(y_position + y, MAX_Y, MIN_Y);	
	}
	public float getXPosition()
	{
		return body.getPosition().x;
	}
	public float getYPosition()
	{
		return body.getPosition().y;
	}
	/**
	 * Used to confine player within level boundaries
	 * @param var desired value
	 * @param max maximum value
	 * @param min minimum value
	 * @return the variables if it lies bewteen min/max, otherwise the min or max
	 */
	private float clamp(float var, float max, float min) {
	    if(var > min) {
	        if(var < max) {
	            return var;
	        } else return max;
	    } else return min;
	}
	public void hit() 
	{
		lives--;
		System.out.println("Lives" + lives);
		is_hit = true;
	}
	public int getlives()
	{
		return lives;
	}
	public void Collected_coin() 
	{
		score += 5;
		System.out.println("Score: " + score);
	}
	public void Got_flipper() 
	{
		SPEED_CONSTANT += .03;
		flippers++;
		score += 15;
		System.out.println("Score: " + score);
		//Start timer;
	}
	
	
}
