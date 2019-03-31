
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    public String givenWhere;
    public String givenPhrase;
    public String name;
    public PhraseFilter(String where, String phrase) {
        givenWhere = where;
        givenPhrase = phrase;
        name = "PhraseFilter";
    }
    public boolean satisfies(QuakeEntry qe) {
        String info = qe.getInfo();
        if (givenWhere.equals("start")&& info.startsWith(givenPhrase)) {
            return true;
        }
        if (givenWhere.equals("any")&& info.contains(givenPhrase)) {
            return true;
        }        
        if (givenWhere.equals("end")&& info.endsWith(givenPhrase)) {
            return true;
        } else {
            return false;    
        }
    }

    public String getName() {
        return name;
    }
}
