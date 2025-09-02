package com.chess.containers;

import java.util.ArrayList;

public class BordContainer {
    public int size;
    public int[] bord;
    public BordContainer(int bordsize) {
        this.size = bordsize;
        this.bord = new int[bordsize*bordsize];
        ArrayList<Integer> bord = new ArrayList<>();
        for (int index = 0; index < bordsize*bordsize; index++) {
            bord.add(index%2);
        }
        for (int index = 0; index < bord.size(); index++) {
            this.bord[index] = bord.get(index);
        }
    }
}
