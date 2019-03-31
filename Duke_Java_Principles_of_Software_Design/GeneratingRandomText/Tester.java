
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test");
        System.out.println(markov.getFollows("es"));
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
	FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	markov.setTraining(st);
	System.out.println(markov.getFollows("t"));
	System.out.println(markov.getFollows("t").size());
    }
}
