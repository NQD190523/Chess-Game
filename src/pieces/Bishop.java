package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Bishop extends Piece {

    public Bishop(Board board, int columns, int rows,  boolean team) {
        super(board);
        this.board = board;
        this.columns = columns;
        this.rows = rows;
        this.x = columns * board.titleSize;
        this.y = rows * board.titleSize;

        this.team = team;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(4*sheetScale, team ? 0 : sheetScale, sheetScale, sheetScale ).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int toCol, int toRow){
        return Math.abs( this.columns - toCol) == Math.abs(this.rows - toRow);
    }
    public boolean moveCollidesWithPieces(int toCol, int toRow){
        //up left
        if(this.columns > toCol && this.rows > toRow ){
            for(int c = 1; c < Math.abs(this.columns - toCol); c ++ ) if(board.getPiece(this.columns - c, this.rows - c)!= null) return true;
        }
        //up right
        if(this.columns < toCol && this.rows > toRow ){
            for(int c = 1; c < Math.abs(this.columns - toCol); c ++ ) if(board.getPiece(this.columns + c, this.rows - c)!= null) return true;
        }
        //down left
        if(this.columns > toCol && this.rows < toRow ){
            for(int c = 1; c < Math.abs(this.columns - toCol); c ++ ) if(board.getPiece(this.columns - c, this.rows + c)!= null) return true;
        }
        //down right
        if(this.columns < toCol && this.rows < toRow ){
            for(int c = 1; c < Math.abs(this.columns - toCol); c ++ ) if(board.getPiece(this.columns + c, this.rows + c)!= null) return true;
        }
        return false;
    }
    

    
}