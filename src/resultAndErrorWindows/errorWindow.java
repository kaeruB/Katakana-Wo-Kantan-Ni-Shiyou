package resultAndErrorWindows;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class errorWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	
	public errorWindow (String statement) {
		initUI(statement);
	}
	
	private void initUI(String statement) {		
		panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	
        panel.setLayout(new GridLayout(2, 1, 5, 5));                  
	
	    JButton exitButton = new JButton("EXIT");
	    exitButton.setFont(new Font("Serif", Font.PLAIN, 40));
	    exitButton.addActionListener((ActionEvent e) -> {
	    	dispose();
	    });  
	    
	    JTextArea error = new JTextArea();
	    error.setEditable(false);
		error.setText(statement);
  	    error.setFont(new Font("Serif", Font.PLAIN, 21));
	     
	    panel.add(error);  
	    panel.add(exitButton);

	    add(panel);
	    	
	    setTitle("ERROR");
	    setSize(350, 300);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
}
