package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import util.CameraHelper;
import util.Constants;

public class MenuScreen extends AbstractGameScreen 
{
  private static final String TAG = MenuScreen.class.getName();
  private SpriteBatch batch;
  Pixmap px= new Pixmap(Gdx.files.internal("menu_background.png"));
  private OrthographicCamera camera;
  CameraHelper camhelper;
  Texture background1 = new Texture(px);
  
  public MenuScreen (Game game) 
  {
    super(game);
    camhelper = new CameraHelper();
    camhelper.setPosition(0, 0);
    batch = new SpriteBatch();
	camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
	camera.position.set(0, 0,0);
    
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
    	batch.end();
    if(Gdx.input.isTouched())
      game.setScreen(new GameScreen(game));
  }
  @Override public void resize (int width, int height) { }
  @Override public void show () { }
  @Override public void hide () { }
  @Override public void pause () { }
}
