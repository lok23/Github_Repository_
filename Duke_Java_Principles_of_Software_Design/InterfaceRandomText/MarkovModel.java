
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import java.util.ArrayList; 
import java.util.Random;
import edu.duke.*;

public class MarkovModel extends AbstractMarkovModel{
    private int markovNum;
    
    public MarkovModel(int num) {
        myRandom = new Random();
        markovNum = num;
        order = num;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-markovNum);
        String key = myText.substring(index, index+markovNum);
        sb.append(key);        
        for(int k=0; k < numChars - markovNum; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(key.length()-(markovNum-1)) + next;
        }
        
        return sb.toString();
    }

}
