import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */ 
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildHashMap();
        printHashMapInfo();
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i=start; i < words.length - myOrder; i++) {
            WordGram word = new WordGram(words, i, myOrder);
            if (word.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram.toString().hashCode());
    }
    

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);  
        sb.append(" ");
        for(int k = 0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size()); 
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    private void buildHashMap() {  
        map = new HashMap<Integer, ArrayList<String>>();
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram word = new WordGram(myText, i, myOrder);
            int code = word.hashCode();
            if (!map.containsKey(code)) {
                map.put(code, new ArrayList<String>());
            }
            if (i + myOrder < myText.length) {
                String follower = myText[i + myOrder];
                ArrayList<String> follows = map.get(code);
                follows.add(follower);
                map.put(code, follows);
            }
        }
    }
    
    private void printHashMapInfo() {
        System.out.println(map.size());
        int largest = 0; 
        for (int code : map.keySet()) {
            int current = map.get(code).size();
            if (current > largest) {
                largest = current;
            }
        }
        System.out.println("Size of largest value in the HashMap: " + largest);
        for (int code : map.keySet()) {
            if (map.get(code).size() == largest) {
                System.out.println("The keys that have the maximum size value and their follow words: ");
                System.out.println(code + " .... " + map.get(code));
            }
        }
    }
}
    

