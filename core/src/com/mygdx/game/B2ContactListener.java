package com.mygdx.game;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import objects.Swimmer;

public class B2ContactListener implements ContactListener {

	Fixture fa;
	Fixture fb;
	Swimmer swim;
	public B2ContactListener(Swimmer swimmer)
	{
		swim = swimmer;
	}

	@Override
	public void beginContact(Contact contact) 
	{
		fa = contact.getFixtureA();
		fb = contact.getFixtureB();
		//fa.getUserData();
		if(fa.getBody().getUserData().getClass().equals(Swimmer.class) || fa.getBody().getUserData().getClass().equals(Swimmer.class) )
		{
			//System.out.println("Swimmer contact");
			swim.hit();
		}
	}

	@Override
	public void endContact(Contact contact) {		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {		
	}

}
