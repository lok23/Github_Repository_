
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import java.util.ArrayList; 
import java.util.Random;
import edu.duke.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int markovNum;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        markovNum = num;
        order = num;
        map = new HashMap<String, ArrayList<String>>();
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
    
    public HashMap<String, ArrayList<String>> buildMap(String myText) {
        int startIndex = 0;
        while(true) {
            int endIndex = startIndex + order;
            String substring = myText.substring(startIndex, endIndex);
            if (!map.containsKey(substring)) {
                map.put(substring, new ArrayList<String>());
            }            
            if (endIndex < myText.length()) {
                String follower = myText.substring(endIndex, endIndex + 1);
                ArrayList<String> followerArrayList = map.get(substring);
                followerArrayList.add(follower);
                map.put(substring, followerArrayList); 
                startIndex += 1;
            } else {
                break;
            }            
        }
        return map;
    }
    
    public void printHashMapInfo() {  
        buildMap("This is a test, yes this is a test");
        System.out.println("Number of keys: " + map.size());
        int largestSize = 0; 
        for (String key : map.keySet()) {
            int keySize = map.get(key).size();
            if (keySize > largestSize) {
                largestSize = keySize;
            }
        }
        System.out.println("The size of the largest ArrayList of characters: " + largestSize);
        
        System.out.println("The keys that have the maximum size value:");
        for (String key : map.keySet()) {
            if (map.get(key).size() == largestSize) {
                System.out.println(key);
            }
        }

    }    
    
    /*public void testBuildMap() {
        System.out.println(buildMap());
    }*/
        
    public String toString() {
        return "This is the EfficientMarkovModel class";
    }
    
    public ArrayList<String> getFollows(String key) {
        return map.get(key);   
    }
}

