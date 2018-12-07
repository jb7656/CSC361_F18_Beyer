package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetJellyfish;
import com.mygdx.game.Assets.AssetStingray;

public class Jellyfish extends AbstractGameObject 
{
	public AssetJellyfish jellyfish = Assets.jellyfish;
	private float x_position;
	private float y_position;
	private final float SPEED = .015F;
	BodyDef bodydef;
	Body body;
	FixtureDef fxdef;
	PolygonShape box;
	
	public Jellyfish(float x, float y, World world1)
	{
		x_position = x;
		y_position = y;
		//jellyfish = Assets.jellyfish;
		bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(x,y);
	
		body = world1.createBody(bodydef);
		body.setLinearVelocity(0f,1f);
		body.setUserData(this);
		fxdef = new FixtureDef();
		box = new PolygonShape();
		//box.setAsBox(stingray.image.getWidth(), stingray.image.getHeight());
		box.setAsBox(.01f,.01f);
		fxdef.shape = box;
		fxdef.isSensor = true;
		body.createFixture(fxdef);
	}
	public void render(SpriteBatch batch2)
	{
		batch2.begin();
			batch2.draw(jellyfish.image,body.getPosition().x,body.getPosition().y,.20f,.20f); //draws JELLYFISH at its current location
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
