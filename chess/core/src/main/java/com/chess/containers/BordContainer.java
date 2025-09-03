package com.chess.containers;


public class BordContainer {
    public int size;
    public String[] bord;
    public BordContainer(int bordsize) {
        this.size = bordsize;
        this.bord = new String[bordsize*bordsize];
    }
}
