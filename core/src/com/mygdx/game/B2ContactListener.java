package com.mygdx.game;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import objects.Coin;
import objects.Swimmer;

public class B2ContactListener implements ContactListener {

	Fixture fa;
	Fixture fb;
	Swimmer swim;
	WorldController wc;
	public B2ContactListener(Swimmer swimmer, WorldController wc)
	{
		swim = swimmer;
		this.wc = wc;
	}

	@Override
	public void beginContact(Contact contact) 
	{
		fa = contact.getFixtureA();
		fb = contact.getFixtureB();
		//fa.getUserData();
		if(fa.getBody().getUserData().getClass().equals(Swimmer.class) || fb.getBody().getUserData().getClass().equals(Swimmer.class) )
		{
			//Swimmer has been contacted
			if(fa.getBody().getUserData().getClass().equals(Coin.class) || fb.getBody().getUserData().getClass().equals(Coin.class) )
			{
				System.out.println("Coin hit");
				if(fa.getBody().getUserData().getClass().equals(Coin.class))
				{
					//System.out.println("Destroyed A");
					wc.coins.remove(fb.getBody().getUserData());
				}
				else
				{
					//System.out.println("Destroyed B");
					wc.coins.remove(fb.getBody().getUserData());
				}
				//System.out.println("Got to coin hit");
				swim.Collected_coin();
			}
			else //stingray or jellyfish have hit swimmer
			{
				swim.hit();
			}
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
