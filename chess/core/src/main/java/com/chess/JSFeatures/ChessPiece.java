package com.chess.JSFeatures;

public class ChessPiece {
    public String name;
    public boolean hasStartMove;
    public ChessMove startMove;
    public ChessMove baseMove;
    public ChessMove attackMove;
    public ChessMove interactMove;

    public ChessPiece(String name) {
        this.name = name;
    }
}
