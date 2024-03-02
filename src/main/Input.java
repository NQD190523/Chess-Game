package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import pieces.Piece;

public class Input extends MouseAdapter {

    Board board;

    public Input(Board board) {
        this.board = board;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece!= null){
            board.selectedPiece.x = e.getX() - board.titleSize /2;
            board.selectedPiece.y = e.getY() - board.titleSize /2;
            board.repaint();
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        Piece pieceXY = board.getPiece(col, row);
        if(pieceXY != null){
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        int col = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        if(board.selectedPiece!= null){
            Move move = new Move( board, board.selectedPiece, col, row);
            if(board.isValidMove(move)){
                board.makeMove(move);
            } else {
                board.selectedPiece.x = board.selectedPiece.columns * board.titleSize;
                board.selectedPiece.y = board.selectedPiece.rows * board.titleSize;
            }
        }

        board.selectedPiece = null;
        board.repaint();
    }
    
}