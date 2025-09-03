package com.chess;

import static com.chess.Main.*;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.chess.JSFeatures.ChessInstace;
import com.chess.JSFeatures.ChessUtils;
import com.chess.containers.BordContainer;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class GameScreen implements Screen {
    final Main game;
    ChessInstace chessInstace;

    Rectangle drawRect = new Rectangle();
    
    public GameScreen(final Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        font.getData().scale(0.5f);
        chessInstace = new ChessInstace();
        try {
            Context context = Context.enter();
            context.setClassShutter(new JSClassShutter());
            Scriptable scope = context.initStandardObjects();
            context.evaluateString(scope, modContainer.scripts.get("init.js"), "scriptFile-init.js", 1, null);
            ScriptableObject.putProperty(scope, "chess", Context.javaToJS(chessInstace, scope));
            context.evaluateString(scope, "init('"+modContainer.name+"');", "scriptJava-init", 1, null);
        } finally {
            Context.exit();
        }


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        baseInputLogic();
        logic();
        draw();
    }

    void logic() {

    }

    void draw() {
        spriteBatch.begin();
        int tilesize = 64 / chessInstace.bord().getBordsize();
        int index = 0;
        for (int col = 0; col < chessInstace.bord().getBordsize()*tilesize; col+=tilesize) {
            if (col % (tilesize*2) == 0) {
                // Left to right
                for (int row = 0; row < chessInstace.bord().getBordsize()*tilesize; row+=tilesize) {
                    drawRect = getcords(col, row, tilesize, tilesize);
                    spriteBatch.draw(modContainer.tiles.get(chessInstace.bord().getBordTile(index)), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
                    index++;
                }
            } else {
                // Right to left
                for (int row = chessInstace.bord().getBordsize()*tilesize-tilesize; row >= 0; row-=tilesize) {
                    drawRect = getcords(col, row, tilesize, tilesize);
                    spriteBatch.draw(modContainer.tiles.get(chessInstace.bord().getBordTile(index)), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
                    index++;
                }
            }
        }
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {

    }
}