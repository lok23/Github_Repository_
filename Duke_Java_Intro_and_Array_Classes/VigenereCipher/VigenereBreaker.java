import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedString = new StringBuilder();
        int i = 0;
        for (char c : message.toCharArray()) {
            if (i % totalSlices == whichSlice) 
            slicedString.append(c);
            i++;
        }
        return slicedString.toString();
    }

    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0; i < klength; i++) {
            String slicedString = sliceString(encrypted, i, klength);
            int elementKey = cc.getKey(slicedString);
            key[i] = elementKey;
        }
        return key;
    }
    
    public int[] testTryKeyLength() {
        FileResource fr = new FileResource();
        String s = fr.asString();
        int[] key = tryKeyLength(s, 5, 'e');
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hashSetString = new HashSet<String>();
        for (String s: fr.lines()){
            hashSetString.add(s.toLowerCase());
        }
        return hashSetString;
    }
    
    public void testReadDictionary() {
        FileResource fr = new FileResource();
        System.out.println(readDictionary(fr));
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] stringArray = message.split("\\W+");
        int count = 0;
        for (String s: stringArray){
            String sLower = s.toLowerCase();
            if (dictionary.contains(sLower)){
                count += 1;
            }
        }
        return count;
    }
    
    public void testCountWords() {
        FileResource fr = new FileResource();
        String s = fr.asString();
        FileResource fr2 = new FileResource();
        System.out.println(countWords(s, readDictionary(fr2)));
        //How to test testCountWords: Choose "athens.txt" as the first FileResource and "English" as second
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        char c = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++){
            int[] key = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            if (a > max){
                max = a;
            }
        }
        for (int j = 1; j <= 100; j++){
            int[] key = tryKeyLength(encrypted, j, c);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            if (a == max){
                return s;
            }
        }
        return null;
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String s = fr.asString();
        FileResource fr2 = new FileResource();
        HashSet<String> hashSetString = readDictionary(fr2);
        String message = breakForLanguage(s, hashSetString);
        System.out.println(message);
        /*DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> hm = new HashMap<String, HashSet<String>>();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            hm.put(f.getName(), readDictionary(fr));
            System.out.println(f.getName() + " read.");
        }
        System.out.println();
        breakForAllLanguages(s, hm);*/
    }  

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (String s: dictionary){
            String sLower = s.toLowerCase();
            for (char c: sLower.toCharArray()){
                if (map.containsKey(c)){
                    map.put(c, map.get(c) + 1);
                }
                else{
                    map.put(c, 1);
                }
            }
        }
        int max = 0;
        for (char c: map.keySet()){
            if (map.get(c) > max){
                max = map.get(c);
            }
        }
        for (char c: map.keySet()){
            if (map.get(c) == max){
                return c;
            }
        }
        return '!';
    }   
    
    public void testMostCommonCharIn() {
        FileResource fr2 = new FileResource();
        HashSet<String> dictionary = readDictionary(fr2);
        char maxLetter = mostCommonCharIn(dictionary);
        System.out.println(maxLetter); 
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        int max = 0;
        for (String language: languages.keySet()){
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            if (i > max){
                max = i;
            }
        }
        for (String language: languages.keySet()){
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            if (i == max){
                System.out.println(s + " " + language);
            }
        }
    }
}
