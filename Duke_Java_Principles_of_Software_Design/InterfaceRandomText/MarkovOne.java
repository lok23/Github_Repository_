
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Random;
import edu.duke.*;


public class MarkovOne extends AbstractMarkovModel{
    
    public MarkovOne() {
        myRandom = new Random();
        order = 1;
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
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);        
        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }
    
    
    public void testGF() {
        String st = "Hello this is a test";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        for (int i=0; i<st.length(); i++) {
            String letter = st.substring(i, i+1);
            System.out.println(markov.getFollows(letter));
        }
    }
    
   public void pseudoEfficientMarkovOne() {
       HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
       ArrayList<String> listOne = new ArrayList<String>();
       listOne.add("Blue");
       listOne.add("Black");
       listOne.add("Brown");
       ArrayList<String> listTwo = new ArrayList<String>();
       listTwo.add("Pink");
       listTwo.add("Purple");
       map.put("B colors", listOne);
       map.put("P color", listTwo);
       System.out.println(map);
    }

}
