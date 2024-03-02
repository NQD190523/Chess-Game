package main;
// import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board extends JPanel {
    public int titleSize = 85;
    int rows = 8;
    int columns = 8;

    ArrayList<Piece> pieces = new ArrayList<>();

    public Piece selectedPiece;

    Input input = new Input(this);

    CheckScanner scanner = new CheckScanner(this);


    public int enPassantTile = -1;

    public Board() {
        this.setPreferredSize(new Dimension(columns * titleSize, rows * titleSize));
        addPiece();
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
                    
    }

    public Piece getPiece(int col, int row) {
        for (Piece piece : pieces) {
            if (piece.columns == col && piece.rows == row) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidMove(Move move) {
        if(sameTeam(move.piece, move.capturedPiece)){
            return false;
        }
        if(!move.piece.isValidMovement(move.toCol, move.toRow)){
            return false;
        }
        if(move.piece.moveCollidesWithPieces(move.toCol, move.toRow)){ 
            return false;
        }
        if(scanner.isKingCheck(move)){ 
            return false;
        };
        return true;
    }

    public void makeMove(Move move) {
        if(move.piece.name.equals("Pawn") ){
            movePawn(move);
        }else {
            move.piece.columns = move.toCol;
            move.piece.rows = move.toRow;
            move.piece.x = move.toCol * titleSize;
            move.piece.y = move.toRow * titleSize;

            move.piece.isFirstStep = false;

            capture(move.capturedPiece);
        }
    }

    public void movePawn(Move move) {
        //en Passant
        int colorIndex = move.piece.team ? 1 : -1 ;

        if(getTileNum(move.toCol, move.toRow) == enPassantTile){
            move.capturedPiece = getPiece(move.toCol, move.toRow + colorIndex);
        }
        if(Math.abs(move.piece.rows - move.toRow)==2){
            enPassantTile = getTileNum(move.toCol, move.toRow+ colorIndex);
        } else {
            enPassantTile = -1;
        }

        //promotion
        colorIndex = move.piece.team?0:7;
        if(move.toRow == colorIndex){
            promotionPawn(move);
        }
            move.piece.columns = move.toCol;
            move.piece.rows = move.toRow;
            move.piece.x = move.toCol * titleSize;
            move.piece.y = move.toRow * titleSize;

            move.piece.isFirstStep = false;

            capture(move.capturedPiece);
    }

    private void promotionPawn(Move move) {
        pieces.add(new Queen(this, move.toCol, move.toRow, move.piece.team));
        capture(move.piece);
    }

    public void capture(Piece piece) {
        pieces.remove(piece);
    }

    public boolean sameTeam(Piece piece1, Piece piece2) {
        if(piece1 == null || piece2 == null){
            return false;
        }
        return piece1.team == piece2.team;
    }

    public int getTileNum(int toCol, int toRow) {
        return toRow * rows + toCol;
    }

    Piece findKing(boolean team) {
        for(Piece piece : pieces){
            if(team == piece.team && piece.name.equals("King")){
                return piece;
            }
        }
        return null;
    }

    public void reset() {

        repaint();
    }

    public void addPiece(){
        //black pieces
        pieces.add(new Knight(this, 1, 7, true));
        pieces.add(new Knight(this, 6, 7, true));

        pieces.add(new Queen(this, 4, 7, true));
        pieces.add(new King(this, 3, 7, true));

        pieces.add(new Bishop(this, 2, 7, true));
        pieces.add(new Bishop(this, 5, 7, true));

        pieces.add(new Rook(this, 7, 7, true));
        pieces.add(new Rook(this, 0, 7, true));

        for(int i = 0 ; i < columns ; i++) pieces.add(new Pawn(this, i, 6, true));

        //white pieces
        pieces.add(new Knight(this, 1, 0, false));
        pieces.add(new Knight(this, 6, 0, false));

        pieces.add(new Queen(this, 4, 0, false));
        pieces.add(new King(this, 3, 0, false));

        pieces.add(new Bishop(this, 2, 0, false));
        pieces.add(new Bishop(this, 5, 0, false));

        pieces.add(new Rook(this, 7, 0, false));
        pieces.add(new Rook(this, 0, 0, false));

        for(int i = 0 ; i < columns ; i++) pieces.add(new Pawn(this, i, 1, false));

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                g2d.setColor( (i + j) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 165, 53));
                g2d.fillRect( i *titleSize, j * titleSize, titleSize, titleSize);
            }
        }
        if(selectedPiece != null){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++){
                    if(isValidMove(new Move(this, selectedPiece, i, j))){
                        g2d.setColor(new Color(68, 190, 57, 190));
                        g2d.fillRect( i *titleSize, j * titleSize, titleSize, titleSize);
                    }
                }
            }
        }
        // if(selectedPiece != null){
        //     for (int i = 0; i < rows; i++) {
        //         for (int j = 0; j < columns; j++){
        //             if(scanner.isKingCheck(new Move(this, selectedPiece, i, j))){
        //                 g2d.setColor(new Color(255, 0,0,190));
        //                 g2d.fillRect( i *titleSize, j * titleSize, titleSize, titleSize);
        //             }
        //         }
        //     }
        // }
        for(Piece piece : pieces){
            piece.paint(g2d);
        }
    }
}