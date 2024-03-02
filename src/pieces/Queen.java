package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Queen extends Piece {

    public Queen (Board board, int columns, int rows,  boolean team) {
        super(board);
        this.board = board;
        this.columns = columns;
        this.rows = rows;
        this.x = columns * board.titleSize;
        this.y = rows * board.titleSize;

        this.team = team;
        this.name = "Queen";

        this.sprite = sheet.getSubimage(1*sheetScale, team ? 0 : sheetScale, sheetScale, sheetScale ).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int toCol, int toRow){
        return this.columns == toCol ||  this.rows == toRow || this.columns - toCol == this.rows - toRow || this.columns - toCol == toRow - this.rows;
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