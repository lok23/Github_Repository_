package StringsFirstAssignments;


/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        if(Character.isUpperCase(dna.charAt(0))){
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
         
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
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
        String dna4 = "atgccagtcagctaaca";
        String dna5 = "atgccagtagctaaca";
        
        System.out.println("The string is " + dna1 + ". The Gene is " + findSimpleGene(dna1, "ATG", "TAA"));
        System.out.println("The string is " + dna2 + ". The Gene is " + findSimpleGene(dna2, "ATG", "TAA"));
        System.out.println("The string is " + dna3 + ". The Gene is " + findSimpleGene(dna3, "ATG", "TAA"));
        System.out.println("The string is " + dna4 + ". The Gene is " + findSimpleGene(dna4, "ATG", "TAA"));
        System.out.println("The string is " + dna5 + ". The Gene is " + findSimpleGene(dna5, "ATG", "TAA"));
    }    
    
    public static void main (String[] args) {
        Part2 gene2 = new Part2();
        gene2.testSimpleGene();
    }
}


