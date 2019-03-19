import edu.duke.*;
import java.util.Arrays; 
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int indexOfWords = word.length();
            if (Character.isLetter(word.charAt(0)) == false || Character.isLetter(word.charAt(indexOfWords-1)) == false) {
                indexOfWords -= 1;
            }
            if (indexOfWords > 31) {
                counts[30] += 1;
            } else {
                counts[indexOfWords] += 1;
            }
        }
        System.out.println(Arrays.toString(counts));
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource("smallHamlet.txt");
        int[] counts = new int[31];
        countWordLengths(fr, counts);
    }
    
    public int indexOfMax(int[] values) {
        int highScore = 0;
        for (int k=0; k < values.length; k++) {
            if (values[k] > highScore) {
                highScore = values[k];
            }
        }
        return highScore;
    }
    
    public int[] reusedCountWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int indexOfWords = word.length();
            if (Character.isLetter(word.charAt(0)) == false || Character.isLetter(word.charAt(indexOfWords-1)) == false) {
                indexOfWords -= 1;
            }
            if (indexOfWords > 31) {
                counts[30] += 1;
            } else {
                counts[indexOfWords] += 1;
            }
        }
        return counts;
    }
    
    public void testIndexOfMax() {
        FileResource fr = new FileResource("smallHamlet.txt");
        int[] counts = new int[31];
        int[] values = reusedCountWordLengths(fr, counts);
        int highScore = indexOfMax(values);
        System.out.println(highScore);
    }
    
    
}
    

