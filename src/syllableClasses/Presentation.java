package syllableClasses;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Presentation extends JFrame{
	private static final long serialVersionUID = 1L;	
	private JPanel mainPanel;
    private CardLayout layout;

    public Presentation(String group) {
        initUI(group);
    }
    
    private void setLabels (String ... paths){
	    for (int i = 0; i < paths.length; i++) {
	    	ImageIcon icon = new ImageIcon("resources" + File.separator + "katakana" + File.separator + paths[i]);
	    	JLabel label = new JLabel(icon);
	    	mainPanel.add(label); 
	    }
    }
    
    
    private void initUI(String group) {
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(150, 150, 150));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        
        layout = new CardLayout();
        mainPanel.setLayout(layout); 
        
        if (group.equals("a")) {  
        	setLabels("a1.png", "i1.png", "u1.png", "e1.png", "o1.png");
        }
        else if (group.equals("ka"))
    	    setLabels ("ka1.png", "ki1.png", "ku1.png", "ke1.png", "ko1.png");
        else if (group.equals("sa"))
        	setLabels("sa1.png", "shi1.png", "su1.png", "se1.png", "so1.png");
        else if (group.equals("ta"))
        	setLabels("ta1.png", "chi1.png", "tsu1.png", "te1.png", "to1.png");
        else if (group.equals("na"))
        	setLabels("na1.png", "ni1.png", "nu1.png", "ne1.png", "no1.png");
        else if (group.equals("ha"))
        	setLabels("ha1.png", "hi1.png", "fu1.png", "he1.png", "ho1.png");
 	    else if (group.equals("ma"))
        	setLabels("ma1.png", "mi1.png", "mu1.png", "me1.png", "mo1.png");
 	    else if (group.equals("ra"))
        	setLabels("ra1.png", "ri1.png", "ru1.png", "re1.png", "ro1.png");
 	    else if (group.equals("ya"))
        	setLabels("ya1.png", "yu1.png", "yo1.png");
       	else if (group.equals("inne"))
        	setLabels("n1.png", "wa1.png", "wo1.png");
      
        add(mainPanel);     
 
        JButton prevButton = new JButton(new ImageIcon("resources" + File.separator + "previous.png"));
        prevButton.addActionListener((ActionEvent e) -> {
            layout.previous(mainPanel);
        });

        JButton nextButton = new JButton(new ImageIcon("resources" + File.separator + "next.png"));
        nextButton.addActionListener((ActionEvent e) -> {
            layout.next(mainPanel);
        });
        
        JButton closeButton = new JButton(new ImageIcon("resources" + File.separator + "close.png"));
        closeButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        JButton backButton = new JButton(new ImageIcon("resources" + File.separator + "backIcon.png"));
        backButton.addActionListener((ActionEvent e) -> {
        	EventQueue.invokeLater(() -> {
	            ChoosingPresentation ex = new ChoosingPresentation();
	            ex.setVisible(true);
	            dispose();
	        });
        });
       
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(50, 50, 50));
        btnPanel.add(prevButton);
        btnPanel.add(nextButton);
        btnPanel.add(closeButton);
        btnPanel.add(backButton);

        add(btnPanel, BorderLayout.SOUTH);

        pack();														//automatically sizes JFrame based on the size of its components

        setTitle("カタカナ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }  
}