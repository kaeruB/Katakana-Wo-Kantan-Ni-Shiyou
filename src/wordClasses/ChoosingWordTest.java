package wordClasses;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChoosingWordTest extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public ChoosingWordTest (String type){							//rodzaj - czy czytanie czy znaczenie
		initUI(type);
	}
	
	private void addActionListenerToButton (JButton b, String fileName, String ... v){
		if (v.length == 0)
		b.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {
	    		WordTest ex = new WordTest(fileName);
	            ex.setVisible(true);
	            dispose();
	        });
	    });
		else
			b.addActionListener((ActionEvent e) -> {
		    	EventQueue.invokeLater(() -> {
		    		WordTest ex = new WordTest(fileName, v[0]);
		            ex.setVisible(true);
		            dispose();
		        });
		    });
	}
	
	private void initUI (String type){
        panel = new JPanel();        
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(3, 1, 5, 5)); 
        
        
        JButton test1 = new JButton("テスト　１");
        test1.setToolTipText("Test 1");
        JButton test2 = new JButton("テスト　２");
        test2.setToolTipText("Test 2");
        JButton wordListButton = new JButton ("テスト  -  言葉。");
        wordListButton.setToolTipText("Learn the words for tests.");

        if (type == "readingWordTest"){
        	addActionListenerToButton (test1, "resources" + File.separator +  "KatakanaWordsAnswers.txt");
        	addActionListenerToButton (test2, "resources" + File.separator +  "KatakanaWordsAnswers2.txt");
        }
       else{
    	   addActionListenerToButton (test1, "resources" + File.separator +  "KatakanaWordsQuestions.txt", "resources" + File.separator +  "KatakanaWordsAnswers.txt");
       	   addActionListenerToButton (test2, "resources" + File.separator + "KatakanaWordsQuestions2.txt", "resources" + File.separator + "KatakanaWordsAnswers2.txt");
        }
        
        wordListButton.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {
	    		WordPresentation ex = new WordPresentation();
	            ex.setVisible(true);
	            dispose(); 
	        });
	    });
        
        panel.add(test1);
        panel.add(test2);
        panel.add(wordListButton);
        
        add(panel);

        setTitle("");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}
}
