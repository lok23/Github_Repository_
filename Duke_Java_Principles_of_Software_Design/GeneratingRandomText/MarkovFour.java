
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import java.util.ArrayList; 
import java.util.Random;
import edu.duke.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);        
        for(int k=0; k < numChars -4; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(key.length()-3) + next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
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
    
    public void testGRT() {
        String st = "Hello there, how are you doing";
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        System.out.println(markov.getRandomText(1000));
    }
}
