package wordClasses;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resultAndErrorWindows.*;  
import fileOperations.*;


public class WordPresentation extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1L;
	private ArrayList <String> japWordsArray;
	private ArrayList <String> angWordsArray;
	private JButton test1;
	private JButton test2;
	private JButton next;
	private JButton previous;
	private JLabel japWordStatement;
	private JLabel angWordStatement;
	private JTextField japWordText;
    private JTextField angWordText;
    private int arrayIndex;
    private JPanel panel;
    private Boolean fileChoosingButtonIsPressed = false;
    
    private void prepareArrays (String japWordFile, String angWordFile){		   	
	    fileReader tmp = new fileReader();
		File fileIn = new File(japWordFile);                      
		tmp.setArrayList(fileIn);
		japWordsArray = tmp.getArrayList();
		
	    fileReader tmp2 = new fileReader();
		File fileIn2 = new File(angWordFile);                      
		tmp2.setArrayList(fileIn2);
		angWordsArray = tmp2.getArrayList();
    }
	
	public WordPresentation (){		
		initUI();
	}
	
	public void initUI() {
		arrayIndex = 0;
		
	    panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(4, 2, 10, 10));         
      
        test1 = new JButton("テスト　１　言葉");
        test1.addActionListener(this);        
        test1.setToolTipText("Test 1 - words");
        
        test2 = new JButton("テスト　２　言葉");
        test2.addActionListener(this);
        test2.setToolTipText("Test 2 - words");
        
        japWordStatement = new JLabel();
        angWordStatement = new JLabel();
        
        japWordStatement.setText("日本語");
        japWordStatement.setToolTipText("Word in Japanese");
        angWordStatement.setText("英語");
        angWordStatement.setToolTipText("Word in English");
        
        japWordText = new JTextField();
        japWordText.setEditable(false);
        angWordText = new JTextField();
        angWordText.setEditable(false);
        
        previous = new JButton("前");
        previous.addActionListener(this);
        next = new JButton("次");
        next.addActionListener(this);
        
        
        panel.add(test1);
        panel.add(test2);
        panel.add(angWordStatement);
        panel.add(angWordText);
        panel.add(japWordStatement);
        panel.add(japWordText);
        panel.add(previous);
        panel.add(next);
        
	    add(panel);
	
	    setTitle("勉強");
	    setSize(500, 300);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e){
		try{
		//wybiera ktore pliki wcytac zaleznie od nacisnietego przycisku		
		String buttonText = e.getActionCommand();
		if (buttonText.equals("テスト　１　言葉") || buttonText.equals("テスト　２　言葉")){
			if (buttonText.equals("テスト　１　言葉")){
				prepareArrays("resources" + File.separator +  "KatakanaWordsQuestions.txt", "resources" + File.separator + "KatakanaWordsAnswers.txt");
				fileChoosingButtonIsPressed = true;
			}
			else if (buttonText.equals("テスト　２　言葉")){
				prepareArrays("resources" + File.separator + "KatakanaWordsQuestions2.txt", "resources" + File.separator + "KatakanaWordsAnswers2.txt");
				fileChoosingButtonIsPressed = true;
			}
			
			if (japWordsArray.size() != angWordsArray.size())
				EventQueue.invokeLater(() -> {
	     			errorWindow ex = new errorWindow("The number of japanese words is \ndifferent than the number \nof english words!");
		            ex.setVisible(true);
		            dispose();
		    	});
			
			japWordText.setText(japWordsArray.get(0));
			angWordText.setText(angWordsArray.get(0));
		}
		if (fileChoosingButtonIsPressed != false){
			if (buttonText.equals("次")){							
				arrayIndex++;
				if(japWordsArray.size() - 1 < arrayIndex)					
					arrayIndex %= japWordsArray.size();
				japWordText.setText(japWordsArray.get(arrayIndex));
				angWordText.setText(angWordsArray.get(arrayIndex));
			}
			else if (buttonText.equals("前")){
				arrayIndex--;
				if (arrayIndex < 0)
					arrayIndex = japWordsArray.size() - 1;
				japWordText.setText(japWordsArray.get(arrayIndex));
				angWordText.setText(angWordsArray.get(arrayIndex));
				}
				panel.validate();													
				panel.repaint();
		}
		else EventQueue.invokeLater(() -> {
 			errorWindow ex = new errorWindow("First, choose the list of words.");
            ex.setVisible(true);
    	});
			
		} catch (IndexOutOfBoundsException exc){
			EventQueue.invokeLater(() -> {
     			errorWindow ex = new errorWindow("Something went wrong.");
	            ex.setVisible(true);
	            dispose();
	    	});
		}
	}
}

