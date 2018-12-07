package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetStingray;

public class Stingray extends AbstractGameObject 
{
	public AssetStingray stingray;
	private float original_x;
	private float x_position;
	private float y_position;
	
	public Stingray(float x, float y)
	{
		x_position = x;
		original_x = x;
		y_position = y;
		stingray = Assets.stingray;
	}
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(stingray.image,x_position,y_position,.20f,.20f); //draws stingray at its current location
		batch2.end();
	}
	
	public void update()
	{
		if(x_position > -1f)
		{
			x_position = x_position -.015f;
		}
	}
	@Override
	public void updateMotionY(float y)
	{
		y_position = y;
	}
	public float getXPosition()
	{
		return x_position;
	}
	public float getYPosition()
	{
		return y_position;
	}
}
