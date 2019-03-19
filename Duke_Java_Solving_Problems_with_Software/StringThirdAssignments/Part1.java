
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import edu.duke.FileResource;

import edu.duke.StorageResource;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){                                 
            int currIndex = dna.indexOf(stopCodon,startIndex+3);
            while (currIndex != -1 ) {
               int diff = currIndex - startIndex;
               if (diff % 3  == 0) {
                   return currIndex;
               }
               else {
                   currIndex = dna.indexOf(stopCodon, currIndex + 1);
               }
            }
            return -1;
        
    }
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex + 3);
    }
    public StorageResource getAllGenes(String dna) {
      StorageResource geneList = new StorageResource();
      int startIndex = 0;
      while (true) {
          String currentGene = findGene(dna, startIndex);
          if (currentGene.isEmpty()) {
              break;
          } else {
          geneList.add(currentGene);
          startIndex = dna.indexOf(currentGene, startIndex) +
                       currentGene.length();
          }
        }
        return geneList;
    }
    public int countChar(String input, char character) {
        int count = 0;
        int i = 0;
        while (i < input.length()) {
            char output = input.charAt(i);
            if (output == character) {
                //System.out.println(character);
                count++;    
            }
            i++;
        }
        return count;
    }
    public float cgRatio(String dna) {
        int CCount = countChar(dna, 'C');
        int GCount = countChar(dna, 'G');
        int total = CCount + GCount;
        float result = (float)total/(float)dna.length();
        return result;
    }
    public int countCTG(String dna) {
        String stringa = "CTG";
        int count = 0;
        int index = dna.indexOf(stringa);
        if (index == -1 && count == 0) {
            return count;
        } else {
            while (index != -1) {
                count = count + 1;
                dna = dna.substring(index + stringa.length());
                index = dna.indexOf(stringa);
            }
        return count;
        }
    }
    public void processGenes(){
        FileResource fr = new FileResource("brca1line.fa");
  1HÃ¹fÂ³ WÃ´6â€ Â§ !SFÂ¢PÃ¬â€”ÃŒ ;cÃ‡'+Ã´, qK )WÂ¤Ã“ yÃ­F%Âª3~â€“ÃµsÃ‡ ,Ã¶xwÂ£YfC Â¹
kÂ¡Â±^q/Â©
        
