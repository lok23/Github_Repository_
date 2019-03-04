
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int index){
        int startCodonIndex = dna.indexOf("ATG", index);
        if (startCodonIndex == -1){
            return "";
        } else {
            int indexTAA = findStopCodon(dna, startCodonIndex, "TAA");
            int indexTAG = findStopCodon(dna, startCodonIndex, "TAG");
            int indexTGA = findStopCodon(dna, startCodonIndex, "TGA");
        
            int minIndex = 0;
            if (indexTAA == -1 || (indexTAG != -1 && indexTAG < indexTAA)){
                minIndex = indexTAG;
            } else {
                minIndex = indexTAA;
            }
            if (minIndex == -1 || (indexTGA != -1 && indexTGA < minIndex)){
                minIndex = indexTGA;
            }
            if (minIndex == -1){
                return "";
            }
            return dna.substring(startCodonIndex, minIndex + 3);
        }
    }

    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while(true){
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()){
                break;
            } else {
                count = count + 1;
                startIndex = dna.indexOf(gene, startIndex) + gene.length();
            }
        }
        return count;
    }
    
    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
    
    public static void main(){
        Part3 test = new Part3();
        test.testCountGenes();
    }
}

