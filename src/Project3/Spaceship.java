	
package Project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Spaceship extends Game {

	private String input;
	private ArrayList<String> spaceshipPic;
	private int remainingSteps;
	private ArrayList<String> spaceWords;
	private String theWord;
	private ArrayList<String> wordLine;
	private String guessedLetters;

    public Spaceship() {
        this.remainingSteps = 10;
        this.spaceshipPic = new ArrayList<String>();
        this.spaceWords = new ArrayList<String>();
        this.wordLine=new ArrayList<String>();
        for(int j=0;j<6;j++) {
        	wordLine.add("_");
        }
        spaceWords.add("Galaxy");
    	spaceWords.add("Cosmos");
    	spaceWords.add("Meteor");
    	spaceWords.add("Nebula");
    	spaceWords.add("Planet");
    	Random random = new Random();
        theWord = spaceWords.get(random.nextInt(spaceWords.size()));
        theWord = theWord.toLowerCase();
        this.guessedLetters = "";
        
        try {
            Scanner scanner = new Scanner(new File("rockets.txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
            	spaceshipPic.add(scanner.next());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        	System.out.println("Error loading spaceship "+e.getMessage());
        }
    }

    @Override
    public String explainRules() {
        return "Welcome to the Spaceship Game! Try to guess the given word in " +
        		remainingSteps + " steps.";
    }

    @Override
    public String setup() {
    	String wordLineResponse = "";
    	for(int i=0;i<wordLine.size();i++) {
    		wordLineResponse+=wordLine.get(i)+" ";
    	}
    	return (spaceshipPic.get((spaceshipPic.size())-remainingSteps) + "\n"+"\n" + wordLineResponse);		
    }

    @Override
    public boolean goodPlayerInput(String input) {
    	input=input.toLowerCase();
    	if(input.length()==1 && Character.isLetter(input.charAt(0)) && !guessedLetters.contains(input)) {
    		guessedLetters+= input;
    		this.input=input;
    		int index = theWord.indexOf(input);
    		while(index >= 0) {
    		   wordLine.set(index, String.valueOf(input.charAt(0))); 
    		   index = theWord.indexOf(input, index+1);
    		}
    		return true;
    	}else {
    		return false;
    	}
    	
    }

    @Override
    public String checkWinOrLose() {
    	if(!theWord.contains(this.input)) {
    		remainingSteps--;
    	}
    	if (!wordLine.contains("_")) {
    		return "Congratulations! You won!";
    	}
		if(remainingSteps==0) {
    		return "";
		}else {
			return "You have "+remainingSteps+" remaining steps.";
		}
    }

    @Override
    public boolean canPlayAgain() {
    	if(remainingSteps > 0) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
