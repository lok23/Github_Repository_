
/**
 * Write a description of parsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class parsingWeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if (currentTemp < lowestTemp) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println(coldest.get("TemperatureF") + ", " + coldest.get("TimeEDT"));
    }
    
    public String fileWithColdestTemperature() {
        String coldestFileName = null;
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if (currentTemp < lowestTemp) {
                    lowestSoFar = currentRow;
                    coldestFileName = f.getName();
                }
            }
        }
        return coldestFileName;
    }
    public void dateTimeTemp(CSVParser parser) {
        for (CSVRecord currentRow : parser) {
            System.out.println(currentRow.get("DateUTC") 
            + " " + currentRow.get("TemperatureF"));
        }
    }
    
    public void testFileWithColdestTemperature() {
        String coldestName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestName);
        
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        dateTimeTemp(fr.getCSVParser());

    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord driestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (driestSoFar == null) {
                driestSoFar = currentRow;
            } else {
                String currHumidity = driestSoFar.get("Humidity");
                if (currHumidity != ("N/A")) {
                    double doubCurrHumidity = Double.parseDouble(currentRow.get("Humidity"));
                    double doubDriestHumidity = Double.parseDouble(driestSoFar.get("Humidity"));
                    if (doubCurrHumidity < doubDriestHumidity) {
                        driestSoFar = currentRow;
                    }
                }
            }
        }
        return driestSoFar;
    }  
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
	CSVParser parser = fr.getCSVParser();
	CSVRecord driest = lowestHumidityInFile(parser);
	System.out.println("Lowest Humidity was " + driest.get("Humidity") 
	+" at " + driest.get("DateUTC") );
    }    
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord driestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (driestSoFar == null) {
                driestSoFar = currentRow;
            } else {
                String currHumidity = driestSoFar.get("Humidity");
                if (currHumidity != "N/A") {
                    double doubCurrHumidity = Double.parseDouble(currentRow.get("Humidity"));
                    double doubDriestHumidity = Double.parseDouble(driestSoFar.get("Humidity"));
                    if (doubCurrHumidity < doubDriestHumidity) {
                        driestSoFar = currentRow;
                    }
                }
            }
        }
        return driestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
	CSVRecord driest = lowestHumidityInManyFiles();	
	System.out.println("Lowest Humidity was: " + driest.get("Humidity")
	+" at " + driest.get("DateUTC") );
    } 
        
    public double averageTemperatureInFile(CSVParser parser) {
        int count = 0;
        double temperTotal = 0.0;
        for (CSVRecord currentRow : parser) {
            temperTotal += Double.parseDouble(currentRow.get("TemperatureF"));
            count += 1;
        }
        double average = (temperTotal / count);
        return (average);
    }   
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser file = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(file));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double temperTotal = 0;
        int count = 0;
        double average = 0;
        for (CSVRecord currentRow : parser) {
            double humidity = Double.parseDouble(currentRow.get("Humidity"));
            if (humidity >= value) {
                temperTotal += Double.parseDouble(currentRow.get("TemperatureF"));
                count += 1;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            average = (temperTotal / count);
            return average;
        }
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser,80);
        if (average == 0) {
            System.out.println("No temperatures with that humidity");  
        } else {
            System.out.println("Average temperature when high Humidity is " + average);
        }
    }
        
}
