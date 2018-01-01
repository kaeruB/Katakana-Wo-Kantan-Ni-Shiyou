package syllableClasses;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;

public class ChoosingSyllableTest extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private String [] buttonTitles = {"ｱ ｰ ｵ", "a", "ｶ －ｺ", "ka", "ｻ －ｿ", "sa", "ﾀ －ﾄ", "ta", "ﾅ －ﾉ", "na", "ﾊ －ﾎ", "ha", "ﾏ －ﾓ", "ma", "ﾔ －ﾖ", "ya", "ﾗ －ﾛ", "ra", "ﾝ､ﾜ､ｦ", "inne", "ｶﾀｶﾅ", "katakanaq"};
	private String testName;
	private ArrayList <JButton> buttons = new ArrayList <JButton> ();
	
	public ChoosingSyllableTest () {
		initUI();
	}
	
	private void initUI() {
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(4, 4, 5, 5));    
       
        int j = 0;
        for (int i = 0; i < buttonTitles.length; i += 2){
        	buttons.add(new JButton(buttonTitles[i]));
        	buttons.get(j).addActionListener(this);
        	panel.add(buttons.get(j));
        	j++;       	
        }
           
	    JButton exitButton = new JButton(new ImageIcon("resources" + File.separator + "Exit.png"));
	    panel.add(exitButton);
	    exitButton.addActionListener((ActionEvent e) -> {
	     		dispose();
	    });

        add(panel);

        setTitle("テストを選んでください");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	
	public void actionPerformed (ActionEvent e){
		int i = 0;
    	while (e.getActionCommand() != buttonTitles[i])
    		i = i + 2;
    	testName = buttonTitles[i+1];						//kolejny indeks od napisu na przycisku w tablicy buttonTitles to nazwa prezentacji
    	
        EventQueue.invokeLater(() -> {
	           SyllableTest ex = new SyllableTest(testName, testName + "a");
	           ex.setVisible(true);
	           dispose();
    	});
	}
}