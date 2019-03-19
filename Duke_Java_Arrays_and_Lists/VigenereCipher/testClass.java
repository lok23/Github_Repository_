
/**
 * Write a description of testClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testClass {
    public void sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedString = new StringBuilder();
        int i = 0;
        for (char c : message.toCharArray()) {
            System.out.println(c);
        }
    }
    
    /*public void testSliceString() {
        System.out.println(sliceString("abcdefghijklm", 3, 4));
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            int dkey = cc.getKey(slice);
            key[i] = dkey;
            System.out.println(slice);
        }
        return key;
    }*/
    

}
