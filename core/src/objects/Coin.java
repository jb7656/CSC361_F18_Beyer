package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetCoin;
import com.mygdx.game.Assets.AssetJellyfish;

public class Coin 
{
	public AssetCoin coin = Assets.coin;
	BodyDef bodydef;
	Body body;
	FixtureDef fxdef;
	PolygonShape box;
	
	float x;
	float y;
	
	float Y_MAX = 400;
	float Y_MIN = 0;
	float X_MAX = 1000;
	float X_MIN = 0;
	
	public Coin(World world)
	{
		x = (float) (Math.random() % 1000) * 8.5f;
		y = (float) ((Math.random() % 1000) * 1.5f)-.4f;
		bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(x,y);
		
		body = world.createBody(bodydef); 
		body.setLinearVelocity(0f,0f);
		body.setUserData(this);
		fxdef = new FixtureDef();
		box = new PolygonShape();
		
		box.setAsBox(.4f,4f);
		fxdef.shape = box;
		fxdef.isSensor = true;
		body.createFixture(fxdef);
	}
	
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(coin.image,body.getPosition().x,body.getPosition().y,.1f,.1f); //draws at its current location
		batch2.end();
	}
	public void dispose()
	{
		box.dispose();
	}
}
