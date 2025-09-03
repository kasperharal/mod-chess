package com.chess.JSFeatures;

import com.chess.containers.BordContainer;

public class ChessInstace {
    private ChessInstace.Bord chessBord;
    public ChessInstace() {
        chessBord = this.new Bord();
    }
    public Bord bord() {
        return this.chessBord;
    }
    public class Bord {
        BordContainer gameBord;
        ChessPiece[] gamePieces;
        Bord() {
            this.gameBord = new BordContainer(0);
            this.gamePieces = new ChessPiece[0];
        }
        public void setBordsize(int bordsize) {
           this. gameBord = new BordContainer(bordsize);
        this.gamePieces = new ChessPiece[bordsize*bordsize];
        }
        public int getBordsize() {
            return this.gameBord.size;
        }
        public String getBordTile(int index) {
            return this.gameBord.bord[index];
        }
        public ChessPiece getPiece(int index) {
            return this.gamePieces[index];
        }
        public void initBord(String[] bordmap) {
            for (int index = 0; index < this.gameBord.bord.length; index++) {
                this.gameBord.bord[index] = bordmap[index];
                this.gamePieces[index] = null;
            }
        }
        public void addPiece(ChessPiece piece, int x, int y) {
            this.gamePieces[ChessUtils.gamePosToIndex(ChessInstace.this, x, y)] = new ChessPiece(piece.name+"_white");
            this.gamePieces[ChessUtils.gamePosToIndex(ChessInstace.this, gameBord.size-x-1, y)] = new ChessPiece(piece.name+"_black");
        }
    }
}
