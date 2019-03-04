import edu.duke.*;
import java.util.ArrayList;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> myNames;
    private ArrayList<Integer> myCounts;
    public CharactersInPlay() {
        myNames = new ArrayList<String>();
        myCounts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        String s = person.toLowerCase();
        int index = myNames.indexOf(s);
        if (index == -1) {
            myNames.add(s);
            myCounts.add(1);
        } else {
            int value = myCounts.get(index);
            myCounts.set(index, value+1);
        }
    }
    
    public void findAllCharacters() {
        myNames.clear();
        myCounts.clear();
        int end = 0;
        FileResource resource = new FileResource();
        for (String s : resource.lines()) {
            end = s.indexOf(".");
            if (end != -1) {
                update(s.substring(0, end));
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int k=0; k < myNames.size(); k++) {
            if (myCounts.get(k) >= num1 && myCounts.get(k) <= num2) {
                System.out.println(myNames.get(k) + "\t" + myCounts.get(k));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        /*for (int k=0; k < myNames.size(); k++) {
            System.out.println(myNames.get(k) + "\t" + myCounts.get(k));
        }*/
        charactersWithNumParts(2, 1200);
    }
}
