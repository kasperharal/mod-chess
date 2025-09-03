package com.chess;

import static com.chess.Main.*;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.chess.JSFeatures.ChessInstace;

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
        chessInstace = ScriptExection.executeScripts(chessInstace);
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
        for (int y = 0; y < chessInstace.bord().getBordsize()*tilesize; y+=tilesize) {
            if (y % (tilesize*2) == 0) {
                // Left to right
                for (int x = 0; x < chessInstace.bord().getBordsize()*tilesize; x+=tilesize) {
                    drawRect = getcords(y, x, tilesize, tilesize);
                    spriteBatch.draw(modContainer.tiles.get(chessInstace.bord().getBordTile(index)), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
                    if (chessInstace.bord().getPiece(index) != null)
                        spriteBatch.draw(modContainer.pieces.get(chessInstace.bord().getPiece(index).name), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
                    index++;
                }
            } else {
                // Right to left
                for (int x = chessInstace.bord().getBordsize()*tilesize-tilesize; x >= 0; x-=tilesize) {
                    drawRect = getcords(y, x, tilesize, tilesize);
                    spriteBatch.draw(modContainer.tiles.get(chessInstace.bord().getBordTile(index)), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
                    if (chessInstace.bord().getPiece(index) != null)
                        spriteBatch.draw(modContainer.pieces.get(chessInstace.bord().getPiece(index).name), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
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