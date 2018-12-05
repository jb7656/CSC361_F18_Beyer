package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Assets.AssetSwimmer;

public class Assets implements Disposable, AssetErrorListener
{
	public static final String TAG = Assets.class.getName();
	
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	public static AssetSwimmer swimmer;

	Assets() {}
	
	public void init(AssetManager assetManager )
	{
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		//load texture atlas
		assetManager.load("../assets/MainAssets.atlas", TextureAtlas.class);
		//start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames());
		TextureAtlas atlas = new TextureAtlas("../assets/MainAssets.atlas");
		
		swimmer = new AssetSwimmer(atlas);
	}
	
	public AssetSwimmer Swimmer;
	 
	/**
	 * Class to handle the texture atlas side of the swimmer character
	 * @author jb7656
	 */
	public class AssetSwimmer
	{
		public final AtlasRegion head;
		public final Sprite image;
		public AssetSwimmer(TextureAtlas atlas)
		{
			head = atlas.findRegion("swimmer");
			image = new Sprite(head);
			image.flip(true, false);
		}
	}
	public class AssetStingray
	{
		public final AtlasRegion head;
		public final Sprite image;
		public AssetStingray(TextureAtlas atlas)
		{
			head = atlas.findRegion("stingray1");
			image = new Sprite(head);
			//image.flip(true, false);
		}
	}
	
	@Override
	public void error(AssetDescriptor asset, Throwable throwable)
	{
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}