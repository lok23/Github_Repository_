
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
        + "abcdefghijklmnopqrstuvwxyz"
        + "abcdefghijklmnopqrstuvwxyz");
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public void testEncrypt() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
        + "abcdefghijklmnopqrstuvwxyz"
        + "abcdefghijklmnopqrstuvwxyz");
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);
            if (index != -1) {
                if (i % 2 == 0) {
                    char newChar = shiftedAlphabet1.charAt(index);
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedAlphabet2.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();    
    }
    public void testEncryptTwoKeys() {
        int key1 = 23;
        int key2 = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(decrypted);
    }
}
