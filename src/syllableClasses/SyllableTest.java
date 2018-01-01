package syllableClasses;
import java.awt.Color;
import java.awt.EventQueue;
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


public class SyllableTest  extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private ArrayList <String> questionArray; 
	private ArrayList <String> answerArray;
	private ArrayList <JButton> buttonArray;
	private JTextField question;
	private JTextField answer;
	private int goodAnswersCounter;				//liczy ile dobrych
	private int badAnswersCounter;				//liczy ile bylo zlych odpowiedzi
	private JPanel panel;
	private int currentQuestionIndex;
	      
	
	public SyllableTest(String plikPytania, String plikOdpowiedzi) {
		try {
			questionArray = new ArrayList <String>();
			answerArray = new ArrayList <String>();
			
	    	makeArraysReady(plikPytania, plikOdpowiedzi);	    		
	    	RandomizeArrayLists();
	    	initUI();
	    	
		} catch (IndexOutOfBoundsException e){
			EventQueue.invokeLater(() -> {
     			errorWindow ex = new errorWindow("Something went wrong.");
	            ex.setVisible(true);
	            dispose();
	    	});
		}
    }
    
    private void makeArraysReady (String plikPytania, String plikOdpowiedzi){
		//wczytanie dwoch list - jedna z pytaniami, druga z odpowiedziami 
		fileReader tmp = new fileReader();

		File fileIn = new File("resources" + File.separator + plikPytania + ".txt");
		tmp.setArrayList(fileIn);
		questionArray = tmp.getArrayList();
		
		fileIn = new File("resources" + File.separator + plikOdpowiedzi + ".txt");
		tmp.setArrayList(fileIn);
		answerArray = tmp.getArrayList();
		
		if (questionArray.size() != answerArray.size()){
			EventQueue.invokeLater(() -> {
     			errorWindow ex = new errorWindow("The number of answers is different \nthan the number of questions!");
	            ex.setVisible(true);
	            dispose();
	    	});
		}
    }
    
	private void RandomizeArrayLists(){
		Random rgen = new Random();
		for (int i = 0; i < questionArray.size(); i++){
			int randomPosition = rgen.nextInt(questionArray.size());
			Collections.swap(questionArray, 0, randomPosition);
			Collections.swap(answerArray, 0, randomPosition);
		}
	}

    private int randGenerator (int ... v){							//jeśli jeden  argument - oznacza przedział od 0 do argumentu losowanych liczb
    																//jeśli dwa - drugi parametr to wartosc ktore nie wygeneruje
    	if (v.length == 0)
    		return 0;
    	Random randomGenerator = new Random();						
    	int randInt = randomGenerator.nextInt(v[0]);;
    	if (v.length == 2)
    		while (randInt == v[1])
    			randInt = randomGenerator.nextInt(questionArray.size());
	    return randInt;
    }

    
    private void setButtonsOnPanel (int position){
    	try {
		    //buttony ze złymi odpowiedziami
			for (int i = 0; i < 3; i++)
				buttonArray.add(new JButton(answerArray.get(randGenerator(questionArray.size(),currentQuestionIndex))));
			
			//miejsce na dobra odpowiedz:
			if (position == 0)
				buttonArray.add(0, new JButton(answerArray.get(currentQuestionIndex)));
			else if (position == 1)
				buttonArray.add(1, new JButton(answerArray.get(currentQuestionIndex)));
			else if (position == 2)
				buttonArray.add(2, new JButton(answerArray.get(currentQuestionIndex)));
			else if (position == 3)
				buttonArray.add(3, new JButton(answerArray.get(currentQuestionIndex)));
			
	
			for (int i = 0; i < 4; i++){
				panel.add(buttonArray.get(i));
				buttonArray.get(i).addActionListener(this);
			}
    	} catch (ArrayIndexOutOfBoundsException exc){
			EventQueue.invokeLater(() -> {
     			errorWindow ex = new errorWindow("Something went wrong.");
	            ex.setVisible(true);
	    	});
    	}
    }
    
    
	private void initUI() {	
		currentQuestionIndex = 0;
		goodAnswersCounter = 0;
		badAnswersCounter = 0;
		buttonArray = new ArrayList <JButton>();

		panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	//createEmptyBorder(top, left, bottom, right)
        panel.setLayout(new GridLayout(3, 2, 10, 10));                  //setLayout(new GridLayout(rows,columns,x_spacing,y_spacing));
	 
	    question = new JTextField(15);    
	    question.setEditable(false);
		question.setText("Which picture shows " + questionArray.get(currentQuestionIndex) + "?");
		
	    answer = new JTextField(15);    
	    answer.setEditable(false);
		answer.setText("No answer yet");
 
        panel.add(question);
		panel.add(answer);	
		
		setButtonsOnPanel(randGenerator(3));
	
	    add(panel);
    	
	    setTitle("テスト");
	    setSize(350, 300);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals(answerArray.get(currentQuestionIndex)) && currentQuestionIndex < answerArray.size()){
				goodAnswersCounter++;
				currentQuestionIndex++;
				while (!buttonArray.isEmpty()){
					panel.remove(buttonArray.get(0));
					buttonArray.remove(0);
				}
				if (currentQuestionIndex < answerArray.size()){
					question.setText("Which picture shows " + questionArray.get(currentQuestionIndex) + "?");
					setButtonsOnPanel(randGenerator(3));
					panel.validate();													//przy kolejnym pytaniu powroca do pierwotnego rozmiaru
					panel.repaint();
				}
			}
			else if (!e.getActionCommand().equals(answerArray.get(currentQuestionIndex))){
				badAnswersCounter++;
				answer.setText("Wrong answer");
				JButton tmpButton = (JButton)e.getSource();								
				tmpButton.setBackground(Color.RED.brighter());
			}
			
			if (currentQuestionIndex == answerArray.size()) {
				EventQueue.invokeLater(() -> {
					ChoosingSyllableTest ex1 = new ChoosingSyllableTest();
		            ex1.setVisible(true);
	     			Results ex = new Results(goodAnswersCounter, badAnswersCounter);
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