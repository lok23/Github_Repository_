import edu.duke.FileResource;
import java.util.HashMap;

public class CodonCounter {
    private HashMap<String, Integer> DNAcodons;
    
    public CodonCounter() {
        DNAcodons = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start, String dna) {
        DNAcodons.clear();
        for (int k=start; k<=dna.length()-3; k+=3) {
            String codon = dna.substring(k, k+3);
            if (!DNAcodons.containsKey(codon)) DNAcodons.put(codon,1);
            else DNAcodons.put(codon, DNAcodons.get(codon)+1);
        }
        
    }

    public String getMostCommonCodon() {
        int maxCount = 0;
        String mostCodon = new String();
        for (String codon : DNAcodons.keySet()) {
            int count = DNAcodons.get(codon);
            if (count>maxCount) {
                maxCount = count;
                mostCodon = codon;
            }
        }
        return "Most comon codon: "+mostCodon+"\t"+maxCount;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String codon : DNAcodons.keySet()) {
            int count = DNAcodons.get(codon);
            if (count>=start && count<=end) System.out.println(codon+"\t"+count);
            
        }
        
    }
    
    public int getCodonCount() {
        return DNAcodons.size();
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String DNA = fr.asString();
        
        System.out.println("Codons 0:");
        buildCodonMap(0,DNA);
        System.out.println("Unique codons:\t"+getCodonCount());
        System.out.println(getMostCommonCodon());
        printCodonCounts(1, 5);

        
        System.out.println("Codons 1:");
        buildCodonMap(1,DNA);
        System.out.println(getMostCommonCodon());
        System.out.println("Unique codons:\t"+ getCodonCount());
        printCodonCounts(1, 5);
        
        System.out.println("Codons 2:");
        buildCodonMap(2,DNA);
        System.out.println(getMostCommonCodon());
        System.out.println("Unique codons:\t"+getCodonCount());
        printCodonCounts(1, 5);

    }
}
