package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	public Swimmer()
	{
		swimmer = Assets.swimmer;
		x_position = .5f; //initializes player to beginning of level
		y_position = -.5f;
	}
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(swimmer.image,x_position,y_position,.20f,.20f); //draws swimmer at its current location
		batch2.end();
	}
	@Override
	public void updateMotionX(float x)
	{
		x_position = clamp(x_position + x, 999, MIN_X);
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
	
	
}
