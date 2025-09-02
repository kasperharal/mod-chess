package com.chess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.chess.containers.ModContainer;



/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    static public SpriteBatch spriteBatch;
    static public FitViewport viewport;
    static public Rectangle mouseColider = new Rectangle(0, 0, 1, 1);
    static public boolean mousedown = false;
    static FreeTypeFontGenerator generator;
    static FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    static public BitmapFont font;

    static public ModContainer modContainer;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(1024, 1024);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Helvetica.ttf"));
        parameter.size = 22;
        font = generator.generateFont(parameter);
        this.setScreen(new MainScreen(this));
    }


    public static void baseInputLogic() {
        Vector3 screenCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(screenCoords);
        mouseColider.x = screenCoords.x;
        mouseColider.y = screenCoords.y;
        mousedown = Gdx.input.isButtonPressed(0);
    }

    public static float getcord(float cord) {
        return cord*16;
    }

    public static Rectangle getcords(float x, float y, float w, float h) {
        return new Rectangle(getcord(x),getcord(y),getcord(w),getcord(h));
    }

    public static Rectangle getcords(float x, float y) {
        return new Rectangle(getcord(x),getcord(y),1,1);
    }

    
    @Override
    public void dispose() {
        generator.dispose();
        spriteBatch.dispose();
    }
}
