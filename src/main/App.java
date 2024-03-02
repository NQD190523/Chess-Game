package main;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
// import javax.swing.JPanel;
import javax.swing.JPanel;


public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout( new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000) );
        frame.setLocationRelativeTo(null);

        Board board = new Board();

        JButton button = new JButton("Click me");
       button.addActionListener(new ActionListener() {
        @Override
           public void actionPerformed(java.awt.event.ActionEvent e) {
            board.reset();
           }
       });

       JPanel buttoPanel = new JPanel();
       buttoPanel.add(button);
        
        // Button button = new Button(board);
        frame.add(board);
        frame.add(buttoPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
