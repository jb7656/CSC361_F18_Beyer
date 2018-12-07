package util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import objects.Swimmer;
	
public class CameraHelper 
{
	private static final String TAG = CameraHelper.class.getName();
	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;
	private final float MIN_X = .25f;
	private final float MIN_Y = -.25f;
	private final float MAX_Y = .875f;
	private final float MAX_X = 7.5f;
	private Vector2 position;
	private float zoom;
 
	private Swimmer target;
	public CameraHelper () 
	{
		position = new Vector2();
		zoom = 1.0f;
	}
	
	public void update (float deltaTime) 
	{
		if (!hasTarget()) return;
		//position.x = target.getX() + target.getOriginX();
		position.x =  clamp( target.getXPosition(), MAX_X, MIN_X);
		position.y =  clamp(target.getYPosition(), MAX_Y, MIN_Y);
		//position.y = target.getY() + target.getOriginY();
	}
	
	public void setPosition (float x, float y) 
	{
		this.position.set(x, y);
	}
	
	public Vector2 getPosition () { return position; }
	public void addZoom (float amount) { setZoom(zoom + amount); }
	public void setZoom (float zoom) 
	{
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
	}
	public float getZoom () 
	{ 
		return zoom; 
	}
	public void setTarget (Swimmer target) 
	{ 
		this.target = target; 
	}
	public Swimmer getTarget () 
	{ 
		return target; 
	}
	public boolean hasTarget () 
	{ 
		return target != null; 
	}
	public boolean hasTarget (Sprite target) 
	{
		return hasTarget() && this.target.equals(target);
	}
	public void applyTo (OrthographicCamera camera) 
	{
		//Confines camera within tiled game background
		camera.position.x =  clamp( position.x, MAX_X, MIN_X);
		camera.position.y =  clamp(position.y, MAX_Y, MIN_Y);

		camera.zoom = zoom;
		camera.update();
	}
	/**
	 * Method to confine camera values within specified min/max
	 * @param var
	 * @param max
	 * @param min
	 * @return
	 */
	private float clamp(float var, float max, float min) {
	    if(var > min) {
	        if(var < max) {
	            return var;
	        } else return max;
	    } else return min;
	}
}
