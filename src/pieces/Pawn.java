package pieces;

import java.awt.image.BufferedImage;

import main.Board;

public class Pawn extends Piece {

    public Pawn(Board board, int columns, int rows,  boolean team) {
        super(board);
        this.board = board;
        this.columns = columns;
        this.rows = rows;
        this.x = columns * board.titleSize;
        this.y = rows * board.titleSize;

        this.team = team;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5*sheetScale, team ? 0 : sheetScale, sheetScale, sheetScale ).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int toCol, int toRow){
        int colorIndex = team ? 1 : -1 ;
        // push pawn 1 
        if(this.columns == toCol && this.rows - toRow == colorIndex && board.getPiece(toCol, toRow)== null) return true;

        // push pawn 2 
        if(isFirstStep == true && this.columns == toCol && this.rows - toRow == colorIndex * 2 && board.getPiece(toCol, toRow+ colorIndex)== null) return true;

        // capture left 
        if(this.columns - toCol == 1 && this.rows - toRow == colorIndex && board.getPiece(toCol, toRow) != null) return true;
        
        // capture right
        if(this.columns - toCol == -1 && this.rows - toRow == colorIndex && board.getPiece(toCol, toRow) != null) return true;

        //en Passant left 
        if(board.getTileNum(toCol, toRow) == board.enPassantTile && toCol == this.columns - 1 && toRow == this.rows - colorIndex && board.getPiece(toCol, toRow + colorIndex) != null) return true;

        //en Passant right
        if(board.getTileNum(toCol, toRow) == board.enPassantTile &&  toCol == this.columns + 1 && toRow == this.rows - colorIndex && board.getPiece(toCol, toRow + colorIndex) != null) return true;

        return false; 
    }

    

    
}