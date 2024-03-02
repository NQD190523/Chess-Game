package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton implements ActionListener {

    Board board ;


    public Button(Board board) {
        super("Click me");
        this.board = board;
    }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            board.addPiece();
            board.paintComponent(board.getGraphics());
    }
}