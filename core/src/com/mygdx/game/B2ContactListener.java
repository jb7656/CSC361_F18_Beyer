package com.mygdx.game;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import objects.Coin;
import objects.Flipper;
import objects.Swimmer;
import util.AudioManager;
import util.Constants;

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
		//THis asserts true if the swimmer contacted something
		if(fa.getBody().getUserData().getClass().equals(Swimmer.class) || fb.getBody().getUserData().getClass().equals(Swimmer.class) )
		{
			//This asserts true if a coin is hit
			if(fa.getBody().getUserData().getClass().equals(Coin.class) || fb.getBody().getUserData().getClass().equals(Coin.class) )
			{
				//AudioManager.instance.play(Assets.instance.sounds.pickupCoin);
				if(Constants.play_sound == true)
				{
					Assets.instance.sounds.pickupCoin.play();
				}
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
			else if(fa.getBody().getUserData().getClass().equals(Flipper.class) || fb.getBody().getUserData().getClass().equals(Flipper.class) )
			{
				if(Constants.play_sound == true)
				{
					Assets.instance.sounds.pickupFlipper.play();
				}
				if(fa.getBody().getUserData().getClass().equals(Flipper.class))
				{
					//System.out.println("Destroyed A");
					wc.flippers.remove(fb.getBody().getUserData());
				}
				else
				{
					//System.out.println("Destroyed B");
					wc.flippers.remove(fb.getBody().getUserData());
				}
				swim.Got_flipper();
				
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
