package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetSwimmer;

public class Swimmer extends AbstractGameObject
{
	public AssetSwimmer swimmer;
	private float x_position;
	private float y_position;
	public Swimmer()
	{
		swimmer = Assets.swimmer;
		x_position = .5f;
		y_position = -.5f;
	}
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(swimmer.image,x_position,y_position,.20f,.20f);
		batch2.end();
	}
	
	
}
