
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                                   " Gender " + rec.get(1) +
                                   " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1) == "M") {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource("data/yob1900.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int count = 0;
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count += 1;
                if (rec.get(0).equals(name)) {
                    rank = count;
                    break;
                }
            } 
        }
        if(rank == 0){
            rank = -1;
        }
        return rank;
    }
    public void testGetRank() {
        System.out.println(getRank(2012, "Ethan", "M"));
    }
    
    public String getName(int year, int rank, String gender) {
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int currRank = 0;
        String name = "No Name";
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currRank += 1;
                if (currRank == rank) {
                    name = rec.get(0);
                    break;
                }
            }
        }
        return name;
    }
    public void testGetName() {
        System.out.println(getName(2012, 3, "M")); 
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be "
        + newName + " if he/she was born in " + newYear);
    }
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int maxRank = -1;
        int maxYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int rank = -1;
            int tempRank = 0;
            String strYear = f.getName().substring(3, 7);
            int intYear = Integer.parseInt(strYear);
            FileResource fr = new FileResource(f);
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    tempRank += 1;
                    if (rec.get(0).equals(name)) {
                        rank = tempRank;
                        break;
                    }
                }
            }
            if (maxRank == -1 && rank != -1) {
                maxRank = rank;
                maxYear = intYear;
            } else if (rank != -1 && rank < maxRank) {
                maxRank = rank;
                maxYear = intYear;
            }
        }
        return maxYear;
    }
    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mason", "M"));
    }
    
    public double getAverageRank(String name, String gender) {
        int maxRank = -1;
        int maxYear = -1;
        int numFiles = 0;
        int sumRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            numFiles += 1;
            int rank = -1;
            int tempRank = 0;
            String strYear = f.getName().substring(3, 7);
            int intYear = Integer.parseInt(strYear);
            FileResource fr = new FileResource(f);
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    tempRank += 1;
                    if (rec.get(0).equals(name)) {
                        rank = tempRank;
                        sumRank += rank;
                        break;
                    }
                }
            }
        }
        double avgRank = Double.valueOf(sumRank / numFiles);
        return avgRank;
    }    
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Mason", "M"));
    }   
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String fileName = "data/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        int tempRank= 0;
        int rank = 0;
        int numBabiesRankedHigher = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                tempRank += 1;
                numBabiesRankedHigher += Integer.parseInt(rec.get(2));
                if (rec.get(0).equals(name)) {
                    rank = tempRank;
                    numBabiesRankedHigher -= Integer.parseInt(rec.get(2));
                    break;
                }
            } 
        }
        if(rank == 0){
            numBabiesRankedHigher = -1;
        } 
        return numBabiesRankedHigher;
    } 
    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }
}
