import edu.duke.FileResource;
import edu.duke.DirectoryResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    WordsInFiles(){
        wordMap = new HashMap<String, ArrayList<String>> ();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fname = f.getName();
        for (String word : fr.words()){
            if(!wordMap.containsKey(word)){
                ArrayList<String> al = new ArrayList<String>();
                al.add(fname);
                wordMap.put(word,al);
            } else{
                ArrayList<String> al = wordMap.get(word);
		if (!al.contains(fname)) al.add(fname);
            }
                
        }
    }
    
    private HashMap buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())    addWordsFromFile(f);
        return wordMap;
    }
    
    private int maxNumber() {
        int maxSize = 0;
        for (String key : wordMap.keySet()) {
            int currSize = wordMap.get(key).size();
            if (currSize > maxSize) {
                maxSize = currSize;
            }
        }
        return maxSize;
    }
    
    private ArrayList wordsInNumFiles(int number) {
        ArrayList<String> str = new ArrayList<String>();
        for (String key : wordMap.keySet()) {
            if (number == wordMap.get(key).size()) {
                str.add(key);
            }
        }
        return str;
    }
    
    private ArrayList printFilesIn(String word) {
        ArrayList<String> str = new ArrayList<String>();
        if (wordMap.containsKey(word)) {
            str = wordMap.get(word);
        }
        return str;
    }
    
    public void testMaxNumber() {
        buildWordFileMap();
        System.out.println(wordMap);
        System.out.println(maxNumber());
        System.out.println(wordsInNumFiles(3));
        System.out.println(printFilesIn("cats"));
    }
}
