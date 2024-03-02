package pieces;

import java.awt.image.BufferedImage;

import main.Board;


public class King extends Piece {

    public King(Board board, int columns, int rows,  boolean team) {
        super(board);
        this.board = board;
        this.columns = columns;
        this.rows = rows;
        this.x = columns * board.titleSize;
        this.y = rows * board.titleSize;

        this.team = team;
        this.name = "King";

        this.sprite = sheet.getSubimage(0*sheetScale, team ? 0 : sheetScale, sheetScale, sheetScale ).getScaledInstance(board.titleSize, board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int toCol, int toRow){
        return Math.abs((toCol - this.columns) * (toRow - this.rows))==  1 || Math.abs(toCol - this.columns) + Math.abs(toRow - this.rows) == 1;
    }
   

    
}