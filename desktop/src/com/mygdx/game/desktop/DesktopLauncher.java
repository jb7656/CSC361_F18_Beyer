package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.CSC361_F18_Beyer;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

/**
 * Class for launching the game on desktop
 * @author Jeff Beyer
 */
public class DesktopLauncher
{
	private static boolean rebuildAtlas = false;
	private static boolean drawDebugOutline = false;
	
	public static void main (String[] arg) 
	{
		if (rebuildAtlas) 
	 	{
		 	Settings settings = new Settings();
		 	settings.maxWidth = 1024;
			 settings.maxHeight = 1024;
			 settings.duplicatePadding = false;
			 settings.debug = drawDebugOutline;
			 TexturePacker.process(settings, "enemies","../assets","MainAssets.atlas");
			 //TexturePacker.process(settings, "/enemies","../core/assets","Main.atlas");
		 }
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CSC361_F18_Beyer(), config);
	}
}
