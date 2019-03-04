
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testPrintAll() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log");
        log.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println(uniqueIPs);
    }
    
    public void testPrintAllNumberThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAllHigherThanNum(200);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        ArrayList<String> uniqueIPsDay = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println(uniqueIPsDay);
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println(la.countUniqueIPsInRange(300,399));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap map = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(map));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap map = la.countVisitsPerIP();
        System.out.println(la.IPsMostVisits(map));
    }
    
    public void testIPForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.IPForDays());
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap map = la.IPForDays();
        System.out.println(la.dayWithMostIPVisits(map));
    }
    
    /*public void testtest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap map = la.IPForDays();
        System.out.println(la.test(map, "Sep 30"));
    }*/
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap map = la.IPForDays();
        System.out.println(la.IPsWithMostVisitsOnDay(map, "Sep 30")); 
    }
}
