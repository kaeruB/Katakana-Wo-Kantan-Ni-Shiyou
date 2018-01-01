package syllableClasses;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class ChoosingPresentation extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private String [] buttonTitles = {"ｱ ｰ ｵ", "a", "ｶ －ｺ", "ka", "ｻ －ｿ", "sa", "ﾀ －ﾄ", "ta", "ﾅ －ﾉ", "na", "ﾊ －ﾎ", "ha", "ﾏ －ﾓ", "ma", "ﾔ －ﾖ", "ya", "ﾗ －ﾛ", "ra", "ﾝ､ﾜ､ｦ", "inne"};
	private String presentationName;
	private ArrayList <JButton> buttons = new ArrayList <JButton> ();
	
	public ChoosingPresentation () {
		
		initUI();
	}
	
	private void initUI() {
        panel = new JPanel();  
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        
        int j = 0;
        for (int i = 0; i < buttonTitles.length; i += 2){
        	buttons.add(new JButton(buttonTitles[i]));
        	buttons.get(j).addActionListener(this);
        	panel.add(buttons.get(j));
        	j++;       	
        }
        
         
	    JButton goToSyllableTest = new JButton("Test");
	    panel.add(goToSyllableTest);
	    goToSyllableTest.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {
	            ChoosingSyllableTest ex = new ChoosingSyllableTest();
	            ex.setVisible(true);
	            dispose();
	        });
	    });
	    

	    JButton exitButton = new JButton("EXIT");
	    panel.add(exitButton);
	    exitButton.addActionListener((ActionEvent e) -> {
	     		dispose();
	    });


        add(panel);

        setTitle("カタカナ　-　勉強しましょ！");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	
	public void actionPerformed (ActionEvent e){
		int i = 0;
    	while (e.getActionCommand() != buttonTitles[i])
    		i = i + 2;
    	presentationName = buttonTitles[i+1];						//kolejny indeks od napisu na przycisku w tablicy buttonTitles to nazwa prezentacji
    	
        EventQueue.invokeLater(() -> {
	           Presentation ex = new Presentation(presentationName);
	           ex.setVisible(true);
	           dispose();
    	});
	}
}