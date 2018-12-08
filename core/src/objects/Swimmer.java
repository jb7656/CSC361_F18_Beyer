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
	int score;
	BodyDef bodydef;
	Body body;
	FixtureDef fxdef;
	PolygonShape box;
	
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
	}
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(swimmer.image,body.getPosition().x,body.getPosition().y,.20f,.20f); //draws swimmer at its current location
		batch2.end();
	}
	public void updateMotion(float x,float y)
	{
		x_position = clamp(x_position + x, MAX_X, MIN_X);
		y_position = clamp(y_position + y, MAX_Y, MIN_Y);
		body.setTransform(x_position, y_position, 0);
		
	}
	@Override
	public void updateMotionY(float y)
	{
		y_position = clamp(y_position + y, MAX_Y, MIN_Y);	
	}
	public float getXPosition()
	{
		return x_position;
	}
	public float getYPosition()
	{
		return y_position;
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
	}
	public int getlives()
	{
		return lives;
	}
	public void Collected_coin() 
	{
		score += 5;
	}
	
	
}
