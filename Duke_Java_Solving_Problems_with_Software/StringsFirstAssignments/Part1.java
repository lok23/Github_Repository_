package StringsFirstAssignments;


/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1){
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0){
            return dna.substring(startIndex, stopIndex + 3);
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String dna1 = "TTGAACCAGACTAAGA";
        String dna2 = "GCATGAACAGGCATCC";
        String dna3 = "CTATCCGGATCCG";
        String dna4 = "ATGCCAGTCAGCTAACA";
        String dna5 = "ATGCCAGTAGCTAACA";
        
        System.out.println("The string is " + dna1 + ". The Gene is " + findSimpleGene(dna1));
        System.out.println("The string is " + dna2 + ". The Gene is " + findSimpleGene(dna2));
        System.out.println("The string is " + dna3 + ". The Gene is " + findSimpleGene(dna3));
        System.out.println("The string is " + dna4 + ". The Gene is " + findSimpleGene(dna4));
        System.out.println("The string is " + dna5 + ". The Gene is " + findSimpleGene(dna5));
    }
    
    public static void main (String[] args) {
        Part1 gene1 = new Part1();
        gene1.testSimpleGene();
    }
}
