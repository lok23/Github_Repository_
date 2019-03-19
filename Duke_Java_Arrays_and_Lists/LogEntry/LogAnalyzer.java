
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
     private ArrayList<LogEntry> records;
     public LogAnalyzer() {
         records = new ArrayList<LogEntry> ();
     }
        
     public void readFile(String filename) {
         FileResource resource = new FileResource(filename);
         for (String line : resource.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPVisits = new ArrayList<String>();
         for (LogEntry le : records) {
             Date dateDate = le.getAccessTime();
             String stringDate = dateDate.toString();
             if (stringDate.contains(someday)) {
                 String s = le.getIpAddress();
                 if (!uniqueIPVisits.contains(s)) {
                     uniqueIPVisits.add(s);
                 }
             }
         }
         return uniqueIPVisits;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         int count = 0;
         ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (high > statusCode && statusCode > low) {
                 String s = le.getIpAddress();
                 if (!uniqueIPsInRange.contains(s)) {
                     uniqueIPsInRange.add(s);
                     count += 1;
                 }
             }
         }   
         return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!counts.containsKey(ip)) {
                 counts.put(ip, 1);
             } else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap <String, Integer> counts) {
         int score = 0;
         for (String s : counts.keySet()) {
             if (counts.get(s) > score) {
                 score = counts.get(s);
             }
         }
         return score;
     }
     
     public ArrayList<String> IPsMostVisits(HashMap <String, Integer> counts) {
         ArrayList<String> uniqueIPVisits = new ArrayList<String>();
         int score = mostNumberVisitsByIP(counts);
         for (String s : counts.keySet()) {
             if (counts.get(s) == score) {
                 uniqueIPVisits.add(s);
             }
         }
         return uniqueIPVisits;
     }
     
     public HashMap<String, ArrayList<String>> IPForDays() {
         HashMap<String, ArrayList<String>> dateMap = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             Date dateDate = le.getAccessTime();
             String stringDate = dateDate.toString();
             String subStringDate = stringDate.substring(4, 10);
             if (!dateMap.containsKey(subStringDate)) {
                 ArrayList<String> dateCounts = new ArrayList<String>();
                 String ip = le.getIpAddress();
                 dateCounts.add(ip);
                 dateMap.put(subStringDate, dateCounts);  
             } else {
                 String ip = le.getIpAddress();
                 dateMap.get(subStringDate).add(ip);
             }
         }
         return dateMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateMap) {
         int score = 0;
         String most = "";
         for (String s : dateMap.keySet()) {
             ArrayList<String> arrayList = dateMap.get(s);
             int sum = arrayList.size();
             if (sum > score) {
                 most = s;
             }
         }
         return most;
     } 
     
     /*public ArrayList<String> test(HashMap<String, ArrayList<String>> dateMap, String day) {
         ArrayList<String> IPsInDay = dateMap.get(day);
         return IPsInDay;
     }*/
     
     public ArrayList<String> IPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateMap, String day) {
         ArrayList<String> IPsInDay = dateMap.get(day);
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (String ip : IPsInDay) {
             if (!counts.containsKey(ip)) {
                 counts.put(ip, 1);
             } else {
                 counts.put(ip, counts.get(ip) + 1);
             }  
         }
         ArrayList<String> maxVisits = IPsMostVisits(counts);  
         return maxVisits;
     }
         
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
