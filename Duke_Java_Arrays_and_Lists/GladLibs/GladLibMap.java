
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private HashMap <String, ArrayList<String>> wordMap = new HashMap<String, ArrayList<String>>();
    private ArrayList<String> usedList = new ArrayList<String>();
    private ArrayList<String> usedLabels = new ArrayList<String>();
    private Random myRandom;
    int replacedWords = 0;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal",
                           "adjective", "name", "color",
                           "timeframe", "verb", "fruit"};
        for (String s : labels) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            wordMap.put(s, list);
        }
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        FileResource resource = new FileResource(source);
        for(String line : resource.lines()){
             list.add(line);
        }
        return list;
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (usedLabels.indexOf(label) == -1 && !label.equals("number")) {
            usedLabels.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return randomFrom(wordMap.get(label));
    }

    public void test() {
        System.out.println(wordMap);
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if (usedList.contains(sub) == true) {
            while (usedList.contains(sub) == true) {
                sub = getSubstitute(w.substring(first+1,last));
                replacedWords += 1;
            }
        }
        usedList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        FileResource resource = new FileResource(source);
        for(String word : resource.words()){
            story = story + processWord(word) + " ";
        }
        
        return story;
    }
    
    public int totalWordsInMap() {
        int total = 0;
        for (String key : wordMap.keySet()) {
            for (String subKey : wordMap.get(key)) {
                total += 1;
            }
        }
        return total;
    }
    
    public int totalWordsConsidered() {
        int total = 0;
        for (String key : usedLabels) {
            for (String subKey : wordMap.get(key)) {
                total += 1;
            }
        }
        return total;
    }
    
    public ArrayList testWordsConsidered() {
        return usedLabels;
    }
        
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("\nNumber of words replaced: " + replacedWords);
        System.out.println(totalWordsInMap());
        System.out.println(totalWordsConsidered());
    }
}
