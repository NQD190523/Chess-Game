package main;

import pieces.Piece;

public class CheckScanner {
    Board board;

    public CheckScanner(Board board) {
        this.board = board;
    }

    public boolean isKingCheck(Move move) {
        Piece king = board.findKing(move.piece.team);
        assert king != null;

        int kingCol = king.columns;
        int kingRow = king.rows;

        if(board.selectedPiece !=null && board.selectedPiece.name.equals("King")){
            kingCol = move.toCol;
            kingRow = move.toRow;
        }

        // check up
        return hitByRock(move.toCol, move.toRow, king, kingCol, kingRow, 0 , 1) ||
        // check down
        hitByRock(move.toCol, move.toRow, king, kingCol, kingRow, 0 , -1) ||
        // check left
        hitByRock(move.toCol, move.toRow, king, kingCol, kingRow, 1 , 0) ||
        // check down
        hitByRock(move.toCol, move.toRow, king, kingCol, kingRow, -1 , 0) ||
        // check up left
        hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow, -1, -1) ||
        // check up right
        hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow, 1 , -1) ||
        // check down left
        hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow, -1 , 1) ||
        // check down right
        hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow, 1 , 1) ||
        
        hitByKnight(move.toCol, move.toRow, king, kingCol , kingRow) || 
        hitByPawn(move.toCol, move.toRow, king, kingCol, kingRow) ||
        hitByKing(king, kingCol, kingRow);
    }

    private boolean hitByRock(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for(int i = 1; i < 8 ; i++){
            if(kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row){
                break;
            }

            Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));

            if(piece!= null && piece != board.selectedPiece){
                if(!board.sameTeam(piece, king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }
    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for(int i = 1; i < 8 ; i++){
            if(kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row){
                break;
            }

            Piece piece = board.getPiece(kingCol - (i * colVal), kingRow - (i * rowVal));

            if(piece!= null && piece != board.selectedPiece){
                if(!board.sameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
        return checkKight(board.getPiece(kingCol -1, kingRow -2), king, kingCol, kingRow) || 
                    checkKight(board.getPiece(kingCol +1, kingRow -2), king, kingCol, kingRow) || 
                    checkKight(board.getPiece(kingCol + 2, kingRow -1), king, kingCol, kingRow) || 
                    checkKight(board.getPiece(kingCol +2, kingRow +1), king, kingCol, kingRow) || 
                    checkKight(board.getPiece(kingCol +1, kingRow +2), king, kingCol, kingRow) || 
                    checkKight(board.getPiece(kingCol -1, kingRow +2), king, kingCol, kingRow) ||
                    checkKight(board.getPiece(kingCol -2, kingRow +1), king, kingCol, kingRow) ||
                    checkKight(board.getPiece(kingCol -2, kingRow -1), king, kingCol, kingRow)  ;
    }

    private boolean checkKight(Piece p, Piece k, int col, int row){
        return p != null && !board.sameTeam(p, k) && p.name.equals("knight") && !(p.columns == col || p.rows == row);
    }
    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow){
        int colorValue = king.team? -1: 1;
        return checkPawn(board.getPiece(kingCol+1, kingRow + colorValue), king, col, row) ||
                    checkPawn(board.getPiece(kingCol-1, kingRow + colorValue), king, col, row);

    }

    private boolean checkPawn(Piece p, Piece k, int col, int row){
        return p != null && !board.sameTeam(p, k) && p.name.equals("Pawn") && !(p.columns == col || p.rows == row);
    }

    private boolean hitByKing(Piece king, int kingCol, int kingRow){
        return checkKing(board.getPiece(kingCol -1 , kingRow -1 ), king) ||
                    checkKing(board.getPiece(kingCol +1 , kingRow -1 ), king) ||
                    checkKing(board.getPiece(kingCol, kingRow -1 ), king) ||
                    checkKing(board.getPiece(kingCol +1 , kingRow +1 ), king) ||
                    checkKing(board.getPiece(kingCol -1 , kingRow ), king) ||
                    checkKing(board.getPiece(kingCol +1 , kingRow  ), king) ||
                    checkKing(board.getPiece(kingCol -1, kingRow +1 ), king) ||
                    checkKing(board.getPiece(kingCol +1 , kingRow +1  ), king) ||
                    checkKing(board.getPiece(kingCol  , kingRow +1 ), king) ;


    }
    private boolean checkKing(Piece p, Piece k){
        return p != null && !board.sameTeam(p, k) && p.name.equals("King");
    }

}