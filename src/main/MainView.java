package main;

import java.awt.event.*;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import syllableClasses.*;
import wordClasses.*;

public class MainView extends JFrame{ 
	private static final long serialVersionUID = 1L;
	private JPanel panel;	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
 			MainView ex = new MainView();
            ex.setVisible(true);
    	});
    }
	
    public MainView() {
    	panel = new JPanel();		
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	//createEmptyBorder(top, left, bottom, right)
        panel.setLayout(new GridLayout(3, 2, 10, 10));  

        JButton syllablePresentationButton = new JButton(new ImageIcon("resources" + File.separator + "PresentationOfSyllables.png"));
        panel.add(syllablePresentationButton);
        syllablePresentationButton.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {													//umieszczenie w swingowej kolejce zdarzen
	            ChoosingPresentation ex = new ChoosingPresentation();						//utworzenie instancji kodu
	            ex.setVisible(true);														//ustawiene zeby bylo widoczne
	        });
        });
        
        JButton syllableTestButton = new JButton(new ImageIcon("resources" + File.separator + "SyllableTest.png"));
        panel.add(syllableTestButton);
        syllableTestButton.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {
	            ChoosingSyllableTest ex = new ChoosingSyllableTest();
	            ex.setVisible(true);
	        });
        });
        
       
        JButton readingWordTestButton = new JButton(new ImageIcon("resources" + File.separator + "readingWordTest.png"));
        panel.add(readingWordTestButton);
        readingWordTestButton.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {
	            ChoosingWordTest ex = new ChoosingWordTest("readingWordTest");
	            ex.setVisible(true);
	        });
        });
        
        JButton wordTestButton = new JButton(new ImageIcon("resources" + File.separator + "WordTest.png"));
        panel.add(wordTestButton);
        wordTestButton.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {
	            ChoosingWordTest ex = new ChoosingWordTest("");
	            ex.setVisible(true);
	        });
        });
        
        JButton syllableGameButton = new JButton(new ImageIcon("resources" + File.separator + "SyllableGame.png"));			
        panel.add(syllableGameButton);
        syllableGameButton.addActionListener((ActionEvent e) -> {
	    	EventQueue.invokeLater(() -> {
	            ChoosingSyllableGameSize ex = new ChoosingSyllableGameSize();
	            ex.setVisible(true);
	        });
        });     
        
        JButton exitButton = new JButton(new ImageIcon("resources" + File.separator + "Exit.png"));
        panel.add(exitButton);
        exitButton.addActionListener((ActionEvent e) -> {
         		dispose();
        });
        
	    add(panel);
    	
	    setTitle("カタカナ　-　何をしたいですか？　選んでください！");
	    setSize(420, 350);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
    }
}