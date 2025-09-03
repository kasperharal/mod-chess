package com.chess.JSFeatures;

import com.chess.containers.BordContainer;

public class ChessInstace {
    private ChessInstace.Bord chessBord;
    private ChessInstace.Pieces chessPieces;
    public ChessInstace() {
        chessBord = this.new Bord();
        chessPieces = this.new Pieces();
    }
    public Bord bord() {
        return this.chessBord;
    }
    public class Bord {
        BordContainer gameBord;
        Bord() {
            gameBord = new BordContainer(0);
        }
        public void setBordsize(int bordsize) {
            gameBord = new BordContainer(bordsize);
        }
        public int getBordsize() {
            return gameBord.size;
        }
        public int getBordTile(int index) {
            return gameBord.bord[index];
        }
        public void initBord(int[] bordmap) {
            for (int index = 0; index < this.gameBord.bord.length; index++) {
                this.gameBord.bord[index] = bordmap[index];
            }
        }
    }

    public class Pieces {

    }
}
