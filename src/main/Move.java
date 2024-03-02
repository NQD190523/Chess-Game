package main;

import pieces.Piece;

public class Move {
    
    public int fromCol;
    public int fromRow;

    public int toCol;
    public int toRow;

    Piece piece;
    Piece capturedPiece;

    Board board;

    public Move( Board board,Piece piece, int toCol, int toRow) {

        this.fromCol = piece.columns;
        this.fromRow = piece.rows;
        this.toCol = toCol;
        this.toRow = toRow;

        this.piece = piece;
        this.capturedPiece = board.getPiece(toCol,  toRow);

    }

}