package resultAndErrorWindows;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class EndOfSyllableGame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public EndOfSyllableGame (long length){
		initUI(length);
	}
	
	private void initUI (long length) {
		panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	
        panel.setLayout(new GridLayout(3, 1, 5, 5));
		
	    JButton exitButton = new JButton("EXIT");
	    exitButton.setFont(new Font("Serif", Font.PLAIN, 40));
	    exitButton.addActionListener((ActionEvent e) -> {
	    	dispose();
	    });  
	    exitButton.setMnemonic(KeyEvent.VK_ENTER);
	        
	    JLabel resultText = new JLabel("Your time: " + length / 1000 + " s");
	    resultText.setFont(new Font("Serif", Font.PLAIN, 40));
	    
	    panel.add(resultText);
	    panel.add(exitButton);
	    
	    add(panel);
	    	
	    setTitle("結果");
	    setSize(350, 300);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
}
