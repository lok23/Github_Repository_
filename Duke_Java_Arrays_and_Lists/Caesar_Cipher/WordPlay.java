
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        boolean found = false;
        String vowel = "AEIOUaeiou";
        for (int i = 0; i < vowel.length(); i++) {
            char letter = vowel.charAt(i);
            if (letter == ch) {
                found = true;
                break;
            }
        }
        return found;
    }
    public void testIsVowel() {
        boolean chars = isVowel('f');
        System.out.println(chars);
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder text = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char letter = text.charAt(i);
            if (isVowel(letter) == true) {
                text.setCharAt(i, ch);
            }
        }
        return text.toString();
    }
    public void testReplaceVowels() {
        System.out.println(replaceVowels("ABCDE", '*'));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder text = new StringBuilder(phrase);
        char charUpper = Character.toUpperCase(ch);
        char charLower = Character.toLowerCase(ch);
        for (int i = 0; i < phrase.length(); i++) {
            char letter = text.charAt(i);
            if ((letter == charUpper || letter == charLower) && i % 2 == 1) {
                text.setCharAt(i, '*');
            }
            if ((letter == charUpper || letter == charLower) && i % 2 == 0) {
                text.setCharAt(i, '+');
            }
        }
        return text.toString();
    }
    public void testEmphasize() {
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
