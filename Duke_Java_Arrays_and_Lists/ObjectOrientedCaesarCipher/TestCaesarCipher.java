import edu.duke.*;
import java.util.Arrays; 
/**
 * Write a description of testCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class TestCaesarCipher {
    public int[] countWordLengths(FileResource resource, int[] counts) {
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
    public int maxIndex(int[] values) {
        int highScore = 0;
        int index = 0;
        for (int k=0; k < values.length; k++) {
            if (values[k] > highScore) {
                highScore = values[k];
                index = k;
            }
        }
        return index;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encryptedMessage = cc.encrypt(message);
        System.out.println(encryptedMessage);
        String decryptedMessage = cc.decrypt(encryptedMessage);
        System.out.println(decryptedMessage);
    }
    
    //public String breakCaesarCipher(String input) {
        
}

