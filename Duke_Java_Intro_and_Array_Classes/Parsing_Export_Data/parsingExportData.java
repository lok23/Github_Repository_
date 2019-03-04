
/**
 * Write a description of parsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class parsingExportData {
    public CSVParser tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }

    public String countryInfo(CSVParser parser, String country) {
        String result = "NOT FOUND";
        for (CSVRecord record: parser) {
            String country_search = record.get("Country");
            if (country_search.contains(country)) {
                String country_name = record.get("Country");
                String country_exports = record.get("Exports");
                String country_value = record.get("Value (dollars)");
                
                result = (country_name + " : " + country_exports 
                + " : " + country_value);
            }
        }
        return result;
    }
    
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2) {
        for (CSVRecord record: parser) {
            String country_exports = record.get("Exports");
            if (country_exports.contains(exportItem1) && country_exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser,String exportItem) {
        int count = 0;
        for (CSVRecord record: parser) {
            String country_exports = record.get("Exports");
            if (country_exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            String country_value = record.get("Value (dollars)");
            String country_name = record.get("Country");
            if (country_value.length() > amount.length()) {
                System.out.println(country_name + ", "
                + country_value);
            }
        }
    }
                
    public void test(){
        CSVParser parser4=tester();
        bigExporters(parser4, "$999,999,999,999");
    }    
}
