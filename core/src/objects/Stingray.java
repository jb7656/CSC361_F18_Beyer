package objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Assets;
import com.mygdx.game.Assets.AssetStingray;

public class Stingray extends AbstractGameObject 
{
	public AssetStingray stingray;
	BodyDef bodydef;
	public Body body;
	public FixtureDef fxdef;
	PolygonShape box;
	
	public Stingray(float x, float y, World world1)
	{
		//x_position = x;
		//y_position = y;
		stingray = Assets.stingray;
		bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(x,y);
		body = world1.createBody(bodydef);
		body.setLinearVelocity(-.9f,0f);
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
			//batch2.draw(stingray.image,x_position,y_position,.20f,.20f); //draws stingray at its current location
			batch2.draw(stingray.image,body.getPosition().x,body.getPosition().y,.20f,.20f);
		batch2.end();
	}

	public float getXPosition()
	{
		return body.getPosition().x;
	}
	public float getYPosition()
	{
		return body.getPosition().y;
	}
}

