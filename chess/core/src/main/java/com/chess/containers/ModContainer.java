package com.chess.containers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ModContainer {
    public String name;

    public ArrayList<Texture> pieces = new ArrayList<>();
    public ArrayList<Texture> tiles = new ArrayList<>();
    public ArrayList<Texture> misc = new ArrayList<>();

    public HashMap<String,String> data = new HashMap<>();
    public HashMap<String,String> scripts = new HashMap<>();

    public ModContainer(String name) {
        this.name = name;
    }

    public void loadPiecesFromDir(String dirpath) {
        try {
            Files.list(Paths.get(dirpath))
                 .forEach(path -> pieces.add(new Texture(Gdx.files.absolute(path.toAbsolutePath().toString()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTilesFromDir(String dirpath) {
        try {
            Files.list(Paths.get(dirpath))
                 .forEach(path -> tiles.add(new Texture(Gdx.files.absolute(path.toAbsolutePath().toString()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMiscFromDir(String dirpath) {
        try {
            Files.list(Paths.get(dirpath))
                 .forEach(path -> misc.add(new Texture(Gdx.files.absolute(path.toAbsolutePath().toString()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromDir(String dirpath) {
        try {
            Files.list(Paths.get(dirpath))
                 .forEach(path -> {
                    try {
                        data.put(path.getFileName().toString(),Files.readString(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadScriptsFromDir(String dirpath) {
        try {
            Files.list(Paths.get(dirpath))
                 .forEach(path -> {
                    try {
                        scripts.put(path.getFileName().toString(),Files.readString(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
