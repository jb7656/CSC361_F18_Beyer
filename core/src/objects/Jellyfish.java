package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetJellyfish;
import com.mygdx.game.Assets.AssetStingray;

public class Jellyfish extends AbstractGameObject 
{
	public AssetJellyfish jellyfish = Assets.jellyfish;
	private float x_position;
	private float y_position;
	private final float SPEED = .015F;
	
	public Jellyfish(float x, float y)
	{
		x_position = x;
		y_position = y;
		//jellyfish = Assets.jellyfish;
	}
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(jellyfish.image,x_position,y_position,.20f,.20f); //draws JELLYFISH at its current location
		batch2.end();
	}
	
	public void update()
	{
		if(y_position < 2f)
		{
			y_position = y_position + SPEED;
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
