package com.chess;

import static com.chess.Main.*;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.chess.containers.BordContainer;

public class GameScreen implements Screen {
    final Main game;

    Rectangle drawRect = new Rectangle();
    BordContainer gameBord = new BordContainer(8);
    
    public GameScreen(final Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        font.getData().scale(0.5f);
        System.out.println(modContainer.tiles.get(gameBord.bord[0]));

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
        int tilesize = 64 / gameBord.size;
        int index = 0;
        for (int col = 0; col < gameBord.size*tilesize; col+=tilesize) {
            if (col % (tilesize*2) == 0) {
                // Left to right
                for (int row = 0; row < gameBord.size*tilesize; row+=tilesize) {
                    drawRect = getcords(col, row, tilesize, tilesize);
                    spriteBatch.draw(modContainer.tiles.get(gameBord.bord[index]), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
                    index++;
                }
            } else {
                // Right to left
                for (int row = gameBord.size*tilesize-tilesize; row >= 0; row-=tilesize) {
                    drawRect = getcords(col, row, tilesize, tilesize);
                    spriteBatch.draw(modContainer.tiles.get(gameBord.bord[index]), drawRect.x, drawRect.y, drawRect.width, drawRect.height);
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