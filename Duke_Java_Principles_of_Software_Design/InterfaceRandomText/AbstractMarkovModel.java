
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();     
        int pos = 0;
        while(true) {
            int index = myText.indexOf(key,pos);
            int nextLetter = index+key.length();
            if(index == -1 || nextLetter >= myText.length()){
                break;
            }  
            String addLetter = myText.substring(nextLetter,nextLetter+1);
            follows.add(addLetter);
            pos = index + 1;
        }
        return follows;
    }
    public String toString() {
        return "MarkovModel of order " + order;
    }
    
}
