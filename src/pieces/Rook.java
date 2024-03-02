package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Rook extends Piece {

    public Rook(Board board, int columns, int rows,  boolean team) {
        super(board);
        this.board = board;
        this.columns = columns;
        this.rows = rows;
        this.x = columns * board.titleSize;
        this.y = rows * board.titleSize;

        this.team = team;
        this.name = "Rook";

        this.sprite = sheet.getSubimage(2*sheetScale, team ? 0 : sheetScale, sheetScale, sheetScale ).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int toCol, int toRow){
        return this.columns == toCol || this.rows == toRow;
    }

    public boolean moveCollidesWithPieces(int toCol, int toRow){
        //left 
        if(this.columns > toCol){
            for(int c = this.columns -1; c >  toCol; c -- ) if(board.getPiece(c, this.rows)!= null) return true;
        }
        //right
        if(this.columns < toCol){
            for(int c = this.columns +1; c <  toCol; c ++ ) if(board.getPiece(c, this.rows)!= null) return true;
        }
        //up 
        if(this.rows > toRow){
            for(int r = this.rows -1; r >  toRow; r -- ) if(board.getPiece(this.columns, r)!= null) return true;
        }
        //down
        if(this.rows < toRow){
            for(int r = this.rows +1; r < toRow; r ++ ) if(board.getPiece(this.columns, r )!= null) return true;
        }
        return false;
    }

    
}