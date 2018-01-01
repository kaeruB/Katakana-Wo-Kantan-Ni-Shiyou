package syllableClasses;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChoosingSyllableGameSize extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField sizeGetterTextField;
	private JTextField statement;
	
	public ChoosingSyllableGameSize () {
		initUI();
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	private void initUI() {
		sizeGetterTextField = new JTextField(10);
		sizeGetterTextField.setText("Type here");
		sizeGetterTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	sizeGetterTextField.setText("");
            }
        });
		sizeGetterTextField.addActionListener(this);
		
		statement = new JTextField(10);
		statement.setText("Type a number between 2 and 46");
		statement.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        
        panel.add(statement);
        panel.add(sizeGetterTextField);
        add(panel);
        
        setTitle("サイズ");										
	    setSize(300, 100);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e){
			String size = sizeGetterTextField.getText();
			if (isInteger(size) == true && Integer.parseInt(size) <= 46 && Integer.parseInt(size) >= 2){
				int sizeInt = Integer.parseInt(size);
	     		EventQueue.invokeLater(() -> {
	     			SyllableGame ex = new SyllableGame(sizeInt);
		            ex.setVisible(true);
		            dispose();
		    	});
			}
			else if (isInteger(size) == false){
				statement.setText("Please write ONLY numbers");
				sizeGetterTextField.setText("Number between 2 and 46!");
			}
			else{
				statement.setText("Only numbers between 2 and 46 are accepted");
			}
	}
}
