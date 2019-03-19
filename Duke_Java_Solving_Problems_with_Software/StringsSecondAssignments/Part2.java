
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int index = stringb.indexOf(stringa);
        if (index == -1 && count == 0) {
            return -1;
        } else {
            while (index != -1) {
                count = count + 1;
                stringb = stringb.substring(index + stringa.length());
                index = stringb.indexOf(stringa);
            }
            return count;
        }
    }
    
    public void testHowMany() {
        String stringa1 = "abc";
        String stringb1 = "abcdabcdabc";
        String stringa2 = "aa";
        String stringb2 = "aaaaa";
        String stringa3 = "xyz";
        String stringb3 = "iiiii";
        
        System.out.println(howMany(stringa1, stringb1));
        System.out.println(howMany(stringa2, stringb2));
        System.out.println(howMany(stringa3, stringb3));
    }
    
    public static void main(String args[]) {
        Part2 howMany = new Part2();
        howMany.testHowMany();
    }
}
