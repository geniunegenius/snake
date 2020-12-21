import java.awt.Component;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        
        Gameplay gameplay = new Gameplay();
        
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        frame.setBounds(10, 10, 910, 700);
        frame.setVisible(true);
        frame.add(gameplay);
        
	}

}
