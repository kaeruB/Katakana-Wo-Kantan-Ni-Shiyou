import resultAndErrorWindows.*;
import static org.junit.Assert.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.Test;  
import fileOperations.*;
import syllableClasses.*;
import wordClasses.*;

public class JUTest {
																		
	@Test
	public void choosingPresentationCreatingWindowTest() {
		EventQueue.invokeLater(() -> {
            ChoosingPresentation ex = new ChoosingPresentation();
            ex.setVisible(true);
        });
	}
	
	@Test
	public void syllableTestCreatingWindowTest() {
		EventQueue.invokeLater(() -> {
            ChoosingSyllableTest ex = new ChoosingSyllableTest();
            ex.setVisible(true);
        });
		
 		EventQueue.invokeLater(() -> {
 			SyllableGame ex = new SyllableGame(46);
            ex.setVisible(true);
    	});
 		
 		EventQueue.invokeLater(() -> {
 			SyllableGame ex = new SyllableGame(2);
            ex.setVisible(true);
    	});
	}
	
	@Test
	public void readingWordsCreatingWindowTest() {
    	EventQueue.invokeLater(() -> {
            ChoosingWordTest ex = new ChoosingWordTest("readingWordTest");
            ex.setVisible(true);
        });
	}
	
	@Test
	public void wordTestCreatingWindowTest() {
		EventQueue.invokeLater(() -> {
            ChoosingWordTest ex = new ChoosingWordTest("");
            ex.setVisible(true);
        });
		
    	EventQueue.invokeLater(() -> {
    		WordTest ex = new WordTest("src" + File.separator + "KatakanaWordsAnswers.txt");
            ex.setVisible(true);
        });
		
    	EventQueue.invokeLater(() -> {
    		WordTest ex = new WordTest("src" + File.separator + "KatakanaWordsAnswers2.txt");
            ex.setVisible(true);
        });
		
		EventQueue.invokeLater(() -> {
    		WordTest ex = new WordTest("src" + File.separator + "KatakanaWordsQuestions.txt", "src" + File.separator + "KatakanaWordsAnswers.txt");
            ex.setVisible(true);
        });
		
	    EventQueue.invokeLater(() -> {
	    	WordTest ex = new WordTest("src" + File.separator + "KatakanaWordsQuestions2.txt", "src" + File.separator + "KatakanaWordsAnswers2.txt");
	           ex.setVisible(true);
	    });
	    
    	EventQueue.invokeLater(() -> {
    		WordPresentation ex = new WordPresentation();
            ex.setVisible(true);
        });
	}	
	
	@Test
	public void syllableGameWindowTest() {
 	EventQueue.invokeLater(() -> {
        ChoosingSyllableGameSize ex = new ChoosingSyllableGameSize();
        ex.setVisible(true);
    }); 
	}
	
	@Test
	public void creatingSyllablePresentationWindows() {
		EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("a");
            ex.setVisible(true);
		});
		
		EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("ka");
            ex.setVisible(true);
        });
		
		EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("sa");
            ex.setVisible(true);
        });
	    
		EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("ta");
            ex.setVisible(true);
        });
	    
    	EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("na");
            ex.setVisible(true);
        });
	        
    	EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("ha");
            ex.setVisible(true);
        });
	    
    	EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("ma");
            ex.setVisible(true);
        });
    	
    	EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("ya");
            ex.setVisible(true);
        });

    	EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("ra");
            ex.setVisible(true);
        });

    	EventQueue.invokeLater(() -> {
            Presentation ex = new Presentation("inne");
            ex.setVisible(true);
        });
    }
	
	@Test
	public void creatingOtherWindowsTest() {
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("ka", "kaa");
        ex.setVisible(true);
       });
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("sa", "saa");
        ex.setVisible(true);
		});
		
		EventQueue.invokeLater(() -> {
			SyllableTest ex = new SyllableTest("ta", "taa");
	        ex.setVisible(true);
	    });
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("na", "naa");
        ex.setVisible(true);
		});
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("ha", "haa");
        ex.setVisible(true);
		});
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("ma", "maa");
        ex.setVisible(true);
        });
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("ya", "yaa");
        ex.setVisible(true);
		});
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("ra", "raa");
        ex.setVisible(true);
		});
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("inne", "innea");
        ex.setVisible(true);
		});
		
		EventQueue.invokeLater(() -> {
		SyllableTest ex = new SyllableTest("katakanaq", "katakanaa");
        ex.setVisible(true);
		});
	}
	
	@Test
	public void creatingErrorWindow (){
		EventQueue.invokeLater(() -> {
 			errorWindow ex = new errorWindow("error");
            ex.setVisible(true);
    	});
	}
						

	@Test
	public void creatingButtonTests() {
		ImageIcon exitImage = new ImageIcon(this.getClass().getResource("Exit.png"));
		JButton exitButton = new JButton(exitImage);
		exitButton.setToolTipText("やっと終わりましたかなあ");
		
	    JButton exitButton2 = new JButton("EXIT");
	    exitButton2.setFont(new Font("Serif", Font.PLAIN, 40));
	    exitButton2.setMnemonic(KeyEvent.VK_ENTER);
	}
	
	@Test
	public void creatingJLabel(){
	    JLabel resultText = new JLabel("jlable");
	    resultText.setFont(new Font("Serif", Font.PLAIN, 40));
	}
	
	@Test
	public void creatingJPanel(){
		JPanel panel = new JPanel();
		 panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));	
	     panel.setLayout(new GridLayout(2, 1, 5, 5)); 
	     JButton jb = new JButton ();
	     panel.add(jb);
	}
	
	@Test 
	public void fileReaderTest () {
		ArrayList <String> tmpArrayList;
		fileReader tmp = new fileReader();
		File fileIn = new File("resources" + File.separator + "JUTest" + ".txt");
		tmp.setArrayList(fileIn);
		tmpArrayList = tmp.getArrayList();
		assertTrue(tmpArrayList.get(0).equals("a"));
		assertTrue(tmpArrayList.get(1).equals("ア"));
		assertTrue(tmpArrayList.get(2).equals("あ"));
	}

	@Test
	public void findingImagesMainViewTest () {		
		new ImageIcon("resources" + File.separator + "PresentationOfSyllables.png");
		new ImageIcon("resources" + File.separator + "readingWordTest.png");
		new ImageIcon("resources" + File.separator + "WordTest.png");
		new ImageIcon("resources" + File.separator + "SyllableGame.png");
		new ImageIcon("resources" + File.separator + "Exit.png");
	}
	
	@Test
	public void findingImagesPresentationATest () {
		String [] imageNames = {"a1.png", "i1.png", "u1.png", "e1.png", "o1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}
	
	@Test
	public void findingImagesPresentationKaTest () {
		String [] imageNames = {"ka1.png", "ki1.png", "ku1.png", "ke1.png", "ko1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}
	
	@Test
	public void findingImagesPresentationSaTest () {
		String [] imageNames = {"sa1.png", "shi1.png", "su1.png", "se1.png", "so1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}	
	@Test
	public void findingImagesPresentationTaTest () {
		String [] imageNames = {"ta1.png", "chi1.png", "tsu1.png", "te1.png", "to1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}	
	@Test
	public void findingImagesPresentationNaTest () {
		String [] imageNames = {"na1.png", "ni1.png", "nu1.png", "ne1.png", "no1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}	
	
	@Test
	public void findingImagesPresentationHaTest () {
		String [] imageNames = {"ha1.png", "hi1.png", "fu1.png", "he1.png", "ho1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}	
	
	@Test
	public void findingImagesPresentationMaTest () {
		String [] imageNames = {"ma1.png", "mi1.png", "mu1.png", "me1.png", "mo1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}
	
	@Test
	public void findingImagesPresentationRaTest () {
		String [] imageNames = {"ra1.png", "ri1.png", "ru1.png", "re1.png", "ro1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}	
	@Test
	public void findingImagesPresentationYaTest () {
		String [] imageNames = {"ya1.png", "yu1.png", "yo1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}	
	@Test
	public void findingImagesPresentationInneTest () {
		String [] imageNames = {"n1.png", "wa1.png", "wo1.png"};
		for (int i = 0; i < imageNames.length; i++){
			new ImageIcon("resources" + File.separator + imageNames[i]);
		}
	}
	
	@Test
	public void findingOtherImagesTest () {
		new ImageIcon("resources" + File.separator + "backIcon.png");
	}
}
