package StringsFirstAssignments;
import edu.duke.*;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void testUrl(String url){
        URLResource ur = new URLResource(url);
        for (String word : ur.words()) {
            String wordLower = word.toLowerCase();
            if (wordLower.indexOf("youtube.com") != -1){
                int quotationStartIndex = word.indexOf("\"");
                int quotationEndIndex = word.lastIndexOf("\"");
                word = word.substring(quotationStartIndex + 1, quotationEndIndex);
                System.out.println(word);
            }
        }  
    }
    
    public static void main(String[] args) {
        Part4 urltest = new Part4();
        urltest.testUrl("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}
