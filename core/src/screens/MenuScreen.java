package screens;

import java.util.EventListener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Assets;

import util.CameraHelper;
import util.Constants;

public class MenuScreen extends AbstractGameScreen 
{
  private static final String TAG = MenuScreen.class.getName();
  private SpriteBatch batch;
  Pixmap px1;//= new Pixmap(Gdx.files.internal("menu_background.png"));
  Pixmap px2;// = new Pixmap(Gdx.files.internal("title_logo.png"));
  Pixmap px3;
  private OrthographicCamera camera;
  CameraHelper camhelper;
  Texture background1;// = new Texture(px1);
  Texture logo;// = new Texture(px2);
  //BitmapFont font = new BitmapFont(Gdx.files.internal("data/rayanfont.fnt"), false);
  private Stage stage;
  private Texture musictexture;
  private TextureRegion musicTextureRegion;
  private TextureRegionDrawable musicTexRegionDrawable;
  private ImageButton music;
  
  private Texture playtexture;
  private TextureRegion playTextureRegion;
  private TextureRegionDrawable playTexRegionDrawable;
  private ImageButton play;
  
  public MenuScreen (Game game) 
  {
	super(game);
	
    camhelper = new CameraHelper();
    camhelper.setPosition(0, 0);
    batch = new SpriteBatch();
	camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
	camera.position.set(0,0,0);
	//font.getData().setScale(.1f);
	px1= new Pixmap(Gdx.files.internal("menu_background.png"));
	px2 = new Pixmap(Gdx.files.internal("title_logo.png"));
	
	background1 = new Texture(px1);
	logo = new Texture(px2);
	if(Constants.play_sound == true)
	{
		Assets.instance.music.song02.setLooping(true);
		Assets.instance.music.song02.play();
	}
	 musictexture = new Texture(Gdx.files.internal("mute.png"));
     musicTextureRegion = new TextureRegion(musictexture);
     musicTexRegionDrawable = new TextureRegionDrawable(musicTextureRegion);
     music= new ImageButton(musicTexRegionDrawable); //Set the button up
     music.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
				{onOptionsClicked();}
		});
     
     playtexture = new Texture(Gdx.files.internal("play.png"));
     playTextureRegion = new TextureRegion(playtexture);
     playTexRegionDrawable = new TextureRegionDrawable(playTextureRegion);
     play= new ImageButton(playTexRegionDrawable); //Set the button up
     play.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
				{onPlayClicked();}
		});
     

     stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
     stage.addActor(music);
     stage.addActor(play);
     play.setPosition(575, 0);
     //Add the button to the stage to perform rendering and take input.
     Gdx.input.setInputProcessor(stage); //Start taking input from the ui

  }
  @Override
  public void render (float deltaTime) 
  {
    Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camhelper.applyTo(camera);
	batch.setProjectionMatrix(camera.combined);
    	batch.begin();
    		batch.draw(background1,-2.5f, -2.5f, 5.5f, 5.0f);
    		batch.draw(logo,-1.5f, -1.75f, 3.5f, 3.5f);
    		//font.draw(batch, "swim game", -2, 2);
    	batch.end();
   // if(Gdx.input.isTouched())
   // {
    //	Assets.instance.music.song02.stop();
    //	game.setScreen(new GameScreen(game));
   // }
    stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
    stage.draw();
      
  }
  @Override public void resize (int width, int height) { }
  @Override public void show () { }
  @Override public void hide () { }
  @Override public void pause () { }
  
  private void onPlayClicked()
	{
	  Assets.instance.music.song02.stop();
  		game.setScreen(new GameScreen(game));
	}
  private void onOptionsClicked()
	{
	 
	  if(Constants.play_sound == false)
	  {
		  Constants.play_sound = true;
		  Assets.instance.music.song02.play();
	  }
	  else
	  {
		  Assets.instance.music.song02.stop();
		  Constants.play_sound = false;
	  }
		
	}
}
