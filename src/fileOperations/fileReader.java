package fileOperations;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import resultAndErrorWindows.*;  


public class fileReader {
	 private ArrayList<String> data = new ArrayList<String>();
	 
	 public void setArrayList(File fileIn){
		ArrayList<String> data = new ArrayList<String>();
		try{
			FileInputStream file = new FileInputStream(fileIn);	 
			BufferedReader bReader = new BufferedReader(new InputStreamReader(file, "UTF-8")); 
			String line = null;
			boolean containsLetters = false;
			while ((line = bReader.readLine()) != null) {
				containsLetters = parseLine(line);
				if(line != null && containsLetters == true) 
					data.add(line);	
				else {
					EventQueue.invokeLater(() -> {
		     			errorWindow ex = new errorWindow("A file with answers or questions \n might contain not allowed characters \n or empty fields. \n");
			            ex.setVisible(true);
			    	});
				}
				
				//jesli linijka jest pusta nie dodaje jej do listy, ale sprawdza w tescie z sylab czy listy z odpowiedziami i 
				//pytaniami sa sobie rowne
			}
			bReader.close();
			}  catch (IndexOutOfBoundsException e){
				EventQueue.invokeLater(() -> {
	     			errorWindow ex = new errorWindow("An error occurred while reading a file. \n" + fileIn);
		            ex.setVisible(true);
		    	});
			}catch (IOException e){
				EventQueue.invokeLater(() -> {
	     			errorWindow ex = new errorWindow("The file is unreachable. \n Check the path to the this file: \n " + fileIn);
		            ex.setVisible(true);
		    	});
			} 
		this.data = data;
	}
	 
	 private boolean parseLine (String line){
		 boolean isLetter = true;
		 int i = 0;
		 while (i < line.length() && isLetter == true){
			 if (Character.isLetter(line.charAt(i)) == false){
				 isLetter = false;
			 }
			 i++;
		 }
		 return isLetter;
	 }

	 public ArrayList <String> getArrayList () {
		 return this.data;
	 }
}