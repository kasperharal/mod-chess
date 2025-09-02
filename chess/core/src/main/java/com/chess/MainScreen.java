package com.chess;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.chess.Main.*;

import java.io.File;

import javax.swing.JFileChooser;

public class MainScreen implements Screen {
    Texture loadbutton,startbutton;
    Rectangle loadbuttoncol = new Rectangle(getcords(7.5f, 8, 48, 16));
    Rectangle startbuttonncol = new Rectangle(getcords(7.5f, 8, 48, 16));
    Rectangle drawRect = new Rectangle();
    String filepath = "";

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        loadbutton = new Texture("loadbutton.png");
        startbutton = new Texture("startbutton.png");
        font.getData().scale(0.5f);

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
        if (mouseColider.overlaps(loadbuttoncol)) {
            if (mousedown) {
                JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFolder = chooser.getSelectedFile();
                    String folderPath = selectedFolder.getAbsolutePath(); // ← This is your String
                    filepath = folderPath;
                }
            }
        } else if (mouseColider.overlaps(startbuttonncol)) {
            if (mousedown) {
                
            }
        }
    }

    void draw() {
        spriteBatch.begin();
        drawRect = getcords(7.5f, 8, 48, 16);
        spriteBatch.draw(loadbutton, drawRect.x, drawRect.y, drawRect.width, drawRect.height);
        drawRect = getcords(7.5f, 30, 48, 16);
        spriteBatch.draw(startbutton, drawRect.x, drawRect.y, drawRect.width, drawRect.height);
        drawRect = getcords(7.5f, 48);
        font.draw(spriteBatch, filepath, drawRect.x, drawRect.y);
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
        loadbutton.dispose();
        startbutton.dispose();
    }
}
