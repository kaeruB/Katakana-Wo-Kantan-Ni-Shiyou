package wordClasses;

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import resultAndErrorWindows.*;  
import fileOperations.*;



public class WordTest extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private int i;       												//do warunku koncowego,liczy ile razy wybrano odpowiedz
	private String text;
	private ArrayList <String> questions;
	private ArrayList <String> answers;
	private int goodAnswersCounter;
	private int badAnswersCounter;	
	private JTextField textFieldAnswer;
	private JTextField textFieldWord;
	private JTextField textIsCorrect;

	public WordTest (String plik1, String ... v) {			//jeden parametr - "reading word test"
		try{
			questions = new ArrayList <String> ();				//swa parametry - test wyswietlajacy test po angielsu do wpisania po japonsku
			fileReader tmp = new fileReader();
			File fileIn = new File(plik1);                      
			tmp.setArrayList(fileIn);
			questions = tmp.getArrayList();
			
			if (v.length != 0){
			answers = new ArrayList <String> ();		
			fileReader tmp2 = new fileReader();
			File fileIn2 = new File(v[0]);                      
			tmp2.setArrayList(fileIn2);
			answers = tmp2.getArrayList();
			
			if (questions.size() != answers.size()){
				EventQueue.invokeLater(() -> {
		    		errorWindow ex = new errorWindow("The number of answers is different \nthan the number of questions!");
		            ex.setVisible(true);
		            dispose();
		    	});
			}
		}
		else if (v.length == 0)
				answers = questions;
		
		initUI();
		} catch (IndexOutOfBoundsException e) {
			EventQueue.invokeLater(() -> {
     			errorWindow ex = new errorWindow("Something went wrong.");
	            ex.setVisible(true);
	            dispose();
	    	});
		}
	}
	
	private void initUI() {		
		i = 0;
		goodAnswersCounter = 0;
		badAnswersCounter = 0;
	
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	
        panel.setLayout(new GridLayout(4, 1, 5, 5));                  
	
	    JButton exitButton = new JButton("EXIT");
	    exitButton.setFont(new Font("Serif", Font.PLAIN, 40));
	    exitButton.addActionListener((ActionEvent e) -> {
	    	dispose();
	    }); 
	    
	    textFieldAnswer = new JTextField(15);
	    textFieldAnswer.setToolTipText("上の言葉をカタカナで書いてください。");
	    textFieldAnswer.setText("Please type the word you see in katakana.");
        textFieldAnswer.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textFieldAnswer.setText("");
            }
        });
	    textFieldAnswer.addActionListener(this);
	    
	    textFieldWord = new JTextField(15);
	    textFieldWord.setToolTipText("この言葉を書いてください。");
	    textFieldWord.setEditable(false);
	    textFieldWord.setFont(new Font("Serif", Font.PLAIN, 40));
		textFieldWord.setText(questions.get(i));
	
	    textIsCorrect = new JTextField(15);
	    textIsCorrect.setFont(new Font("Serif", Font.PLAIN, 40));
	    textIsCorrect.setEditable(false);
		textIsCorrect.setText("");
	    
	    panel.add(textFieldWord);        
	    panel.add(textFieldAnswer);  		 
	    panel.add(textIsCorrect);
	    panel.add(exitButton);
		
	    
	    add(panel);
	    	
	    setTitle("言葉 - テスト");
	    setSize(350, 300);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		 try {
			 if (i < questions.size()){
			 }
				 text = textFieldAnswer.getText();
				 if (text.equals(answers.get(i).toString())){
					 goodAnswersCounter++;
			    	 textIsCorrect.setText("いい答え！　：)　");
				 }
				 else{
					 badAnswersCounter++;
			    	 textIsCorrect.setText("悪い答え！　:(　");
				 }
				 i++;
				 
				 if (i < questions.size()){
					 textFieldWord.setText(questions.get(i));
					 textFieldAnswer.setText("");
				 }
				 else {
			     	EventQueue.invokeLater(() -> {
			     		Results ex = new Results(goodAnswersCounter, badAnswersCounter);
				        ex.setVisible(true);
				        dispose();
				    });
				 }	
		 	}catch (ArrayIndexOutOfBoundsException exc){
				EventQueue.invokeLater(() -> {
	     			errorWindow ex = new errorWindow("Something went wrong.");
		            ex.setVisible(true);
		            dispose();
		    	});
		 	}
	} 
}