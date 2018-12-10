package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Assets.AssetFonts;
import com.mygdx.game.Assets.AssetSwimmer;

public class Assets implements Disposable, AssetErrorListener
{
	public static final String TAG = Assets.class.getName();
	
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	public static AssetSwimmer swimmer;
	public static AssetStingray stingray;
	public static AssetJellyfish jellyfish;
	public static AssetCoin coin;
	public static AssetFlipper flipper;
	public AssetFonts fonts;

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
		stingray = new AssetStingray(atlas);
		jellyfish = new AssetJellyfish(atlas);
		coin = new AssetCoin(atlas);
		flipper = new AssetFlipper(atlas);
		fonts = new AssetFonts();
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
	public class AssetJellyfish
	{
		public final AtlasRegion head;
		public final Sprite image;
		public AssetJellyfish(TextureAtlas atlas)
		{
			head = atlas.findRegion("jellyfish1");
			image = new Sprite(head);
		}
	}
	public class AssetCoin
	{
		public final AtlasRegion head;
		public final Sprite image;
		public AssetCoin(TextureAtlas atlas)
		{
			head = atlas.findRegion("coin");
			image = new Sprite(head);
		}
	}
	public class AssetFlipper
	{
		public final AtlasRegion head;
		public final Sprite image;
		public AssetFlipper(TextureAtlas atlas)
		{
			head = atlas.findRegion("flipper");
			image = new Sprite(head);
		}
	}
	public class AssetFonts 
	{
		 public final BitmapFont defaultSmall;
		 public final BitmapFont defaultNormal;
		 public final BitmapFont defaultBig;
		 public AssetFonts () {
			 // create three fonts using Libgdx's 15px bitmap font
			 //defaultSmall = new BitmapFont(new FileHandle("../core/assets/arial-15.fnt"), true);
			 //defaultNormal = new BitmapFont(new FileHandle("../core/assets/arial-15.fnt"), true);
			 //defaultBig = new BitmapFont(new FileHandle("../core/assets/arial-15.fnt"), true);
			 defaultSmall = new BitmapFont(new FileHandle("../assets/arial-15.fnt"), true);
			 defaultNormal = new BitmapFont(new FileHandle("../assets/arial-15.fnt"), true);
			 defaultBig = new BitmapFont(new FileHandle("../assets/arial-15.fnt"), true);
			 //defaultBig.
			 // set font sizes
			 defaultSmall.getData().setScale(0.75f);
			 defaultNormal.getData().setScale(1.0f);
			 defaultBig.getData().setScale(10.0f);
			 //defaultBig.getData().
			 
			 // enable linear texture filtering for smooth fonts
			 defaultSmall.getRegion().getTexture().setFilter(
			 TextureFilter.Linear, TextureFilter.Linear);
			 defaultNormal.getRegion().getTexture().setFilter(
			 TextureFilter.Linear, TextureFilter.Linear);
			 defaultBig.getRegion().getTexture().setFilter(
			 TextureFilter.Linear, TextureFilter.Linear);
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