package syllableClasses;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resultAndErrorWindows.*;  
import fileOperations.*;


public class SyllableGame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private ArrayList<String> answerQuestionArray;   
	private ArrayList<JButton> wrongAnswerPressedButtonArray;
	private int questionCounter;    								//jak dojdzie do numberOfSyllables to koniec 
	private int numberOfSyllables;
	private ArrayList<JButton> buttons;
	private int currentQuestionIndex;
	private JTextField textFieldQuestion;
	private JPanel panel;
	private long startTime;
	private long endTime;
	private int windowWidth;
	private int windowHeight;
	
	public SyllableGame(int numberOfSyllables){
		try {
			this.numberOfSyllables = numberOfSyllables;
			if (numberOfSyllables < 15){
				windowWidth = 300;
				windowHeight = 300;
			}
			else if (numberOfSyllables < 30){
				windowWidth = 400;
				windowHeight = 400;
			}
			else{
				windowWidth = 500;
				windowHeight = 500;
			}
			
			prepareArray("resources" + File.separator + "syllableGameF.txt");
			RandomizeArray(answerQuestionArray);
			buttons = new ArrayList<JButton>();
			questionCounter = 0;
			currentQuestionIndex = 0;
			
			startTime = System.currentTimeMillis();
			
			initUI();
		} catch (IndexOutOfBoundsException e){
			EventQueue.invokeLater(() -> {
     			errorWindow ex = new errorWindow("Something went wrong.");
	            ex.setVisible(true);
	            dispose();
	    	});
		}
	}
	
	private void prepareArray (String file){				//wczytanie pliku
			fileReader tmp = new fileReader();				//w pliku sylaby ułożone są naprzemiennie - po japońsku- po polsku -..
			File fileIn = new File(file);
			tmp.setArrayList(fileIn);
			answerQuestionArray = tmp.getArrayList();
	}
	
	private void RandomizeArray(int[] array){				//wymieszanie tablicy indeksow przyciskow - aby umiescic przyciski losowo na planszy
		Random rgen = new Random();   
		for (int i = 0; i < array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int tmp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = tmp;
		} 
	}
	
	private void RandomizeArray(ArrayList <String> array){			//wymieszanie listy z odpowiedziami i pytaniami tak, zeby tworzyly dalej naprzemienne pary
		Random rgen = new Random();
		for (int i = 0; i < array.size(); i++){
			int randomPosition = rgen.nextInt(array.size()/2);
			Collections.swap(array, 0, randomPosition * 2);
			Collections.swap(array, 1, randomPosition * 2 + 1);
		}
	}
	
	private void initUI (){
			panel = new JPanel();
			panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	
	        panel.setLayout(new GridLayout(6, 2, 10, 10));
	        
	       
	        textFieldQuestion = new JTextField(2);
	        textFieldQuestion.setText(answerQuestionArray.get(currentQuestionIndex));
	        textFieldQuestion.setToolTipText("Find the syllable and press on it.");
	        textFieldQuestion.setEditable(false);
	        textFieldQuestion.setFont(new Font("Serif", Font.PLAIN, 15));
	      
			wrongAnswerPressedButtonArray = new ArrayList<JButton>();       
	        
	        int j = 1;													
	        for (int i = 0; i < numberOfSyllables; i++){						//zaladowanie do listy przyciskow znakow japonskich			
	        	buttons.add(new JButton(answerQuestionArray.get(j)));            //j - bo co druga wartosc w liscie to znak japonski
	        	buttons.get(i).setFont(new Font("Serif", Font.PLAIN, 15));
	        	buttons.get(i).addActionListener(this);
	        	buttons.get(i).setBackground(Color.GREEN.brighter());
	        	j += 2;
	        }
			
	        int buttonOrder[] = new int[numberOfSyllables]; 
	        for (int i = 0; i < numberOfSyllables; i++)
	        	buttonOrder[i] = i;
	        RandomizeArray(buttonOrder);   								//losowa kolejnosc dodawania przyciskow
	        															//bez tego odpowiedz jest bezposrednio pod pytaniem
	        panel.add(textFieldQuestion);        
	        for (int i = 0; i < numberOfSyllables; i++){
	        	panel.add(buttons.get(buttonOrder[i]));
	        }        
	       
	        add(panel);
	    
		    setTitle("ゲーム");										
		    setSize(windowWidth, windowHeight);
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e){	
		try {														//crrentQuestionIndex + 1 to indeks odpowiedzi
			if (e.getActionCommand().equals(answerQuestionArray.get(currentQuestionIndex + 1)) && questionCounter < numberOfSyllables){
				//przywracanie pierwotnych rozmiarow zmienionych przyciskow przy wczesniejszym pytaniu 
				while(! wrongAnswerPressedButtonArray.isEmpty()){
					wrongAnswerPressedButtonArray.get(0).setFont(new Font("Serif", Font.PLAIN, 15));
					wrongAnswerPressedButtonArray.get(0).setBackground(Color.GREEN.brighter());
					wrongAnswerPressedButtonArray.remove(0);
				}
				
				panel.remove(buttons.get(0));
				panel.validate();
				panel.repaint();
				currentQuestionIndex += 2;
				questionCounter++;
				textFieldQuestion.setText(answerQuestionArray.get(currentQuestionIndex));
				buttons.remove(0);														//usuwa przycisk z dobra odpowiedzia, juz niepotrzebny			
			}
			else {
				JButton tmpButton = (JButton)e.getSource();								
				tmpButton.setFont(new Font("Serif", Font.PLAIN, 10));				//zmniejsza i koloruje na czerwono przycisk ze zla odpowiedzia
				tmpButton.setBackground(Color.RED.brighter());
				wrongAnswerPressedButtonArray.add(tmpButton);						//zapazmietuje przyciski, ktore zostaly zmienione					
				panel.validate();													//przy kolejnym pytaniu powroca do pierwotnego rozmiaru
				panel.repaint();
			}
			
			if (questionCounter == numberOfSyllables){
				endTime = System.currentTimeMillis();			
	     		EventQueue.invokeLater(() -> {
	     			EndOfSyllableGame ex = new EndOfSyllableGame(endTime - startTime);
		            ex.setVisible(true);
		            dispose();
		    	});
			}
		} catch (ArrayIndexOutOfBoundsException exc){
			EventQueue.invokeLater(() -> {
     			errorWindow ex = new errorWindow("Something went wrong.");
	            ex.setVisible(true);
	            dispose();
	    	});
    	}
	}
}