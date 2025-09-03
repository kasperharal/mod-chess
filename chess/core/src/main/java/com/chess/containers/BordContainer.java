package com.chess.containers;


public class BordContainer {
    public int size;
    public int[] bord;
    public BordContainer(int bordsize) {
        this.size = bordsize;
        this.bord = new int[bordsize*bordsize];
    }
}
