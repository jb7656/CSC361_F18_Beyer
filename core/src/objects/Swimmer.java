package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetSwimmer;

public class Swimmer extends AbstractGameObject
{
	public AssetSwimmer swimmer;
	public Swimmer()
	{
		swimmer = Assets.swimmer;
	}
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(swimmer.image,1,-.5f,.25f,.25f);
		batch2.end();
	}
}
