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
import com.mygdx.game.Assets.AssetFlipper;
import com.mygdx.game.Assets.AssetJellyfish;

public class Flipper 
{
	public AssetFlipper flipper = Assets.flipper;
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
	
	public Flipper(World world)
	{
		x = (float) (Math.random() % 1000) * 8.5f;
		//y = (float) ((Math.random() % 1000) * 1.5f)-.4f;
		y = -1f;
		bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(x,y);
		
		body = world.createBody(bodydef); 
		body.setLinearVelocity(0f,.4f);
		body.setUserData(this);
		fxdef = new FixtureDef();
		box = new PolygonShape();
		
		box.setAsBox(.1f,.1f);
		fxdef.shape = box;
		fxdef.isSensor = true;
		body.createFixture(fxdef);
	}
	
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(flipper.image,body.getPosition().x,body.getPosition().y,.2f,.2f); //draws at its current location
		batch2.end();
	}
	public void dispose()
	{
		box.dispose();
	}

	public float getYPosition() 
	{
		return body.getPosition().y;
	}
}