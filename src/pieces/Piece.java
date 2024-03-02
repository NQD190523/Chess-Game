package pieces;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;

import main.Board;

public class Piece {
    
    public  int columns;
    public  int rows;
    public  int x;
    public  int y;

    public  boolean team;
    public  String name ;
    public  int value ;

    public boolean isFirstStep = true;

    public boolean isValidMovement(int columns, int rows){return true;}
    public boolean moveCollidesWithPieces(int columns, int rows){return false;}

    BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(new File("./src/res/ChessPiecesArray.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected int sheetScale = sheet.getWidth()/6;

    Image sprite; 

    Board board; 

    public Piece(Board board){
        this.board = board;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, x, y, null);
    }

}