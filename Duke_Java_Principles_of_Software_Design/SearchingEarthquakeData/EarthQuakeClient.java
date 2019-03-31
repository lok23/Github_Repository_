import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int k=0; k < quakeData.size(); k++){
            QuakeEntry quake = quakeData.get(k);            
            Location loc = quake.getLocation();            
            if (loc.distanceTo(from) < distMax) {
                answer.add(quake);
            }
        }
        return answer;
    }
    
     public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (maxDepth > qe.getDepth() && qe.getDepth() > minDepth) {
                answer.add(qe);
            }
        }
        return answer;    
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (where.equals("start")) {
                String info = qe.getInfo(); 
                if (info.startsWith(phrase)) {
                    answer.add(qe);
                }
            }
            if (where.equals("any")) {
                String info = qe.getInfo();
                if (info.contains(phrase)) {
                    answer.add(qe);
                }
            }
            if (where.equals("end")) {
                String info = qe.getInfo(); 
                if (info.endsWith(phrase)) {
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
                
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        //iterate through list return all QuakeEntries with magnitude > 5.0
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
       
       //same as above, except using filterByMagnitude method
       ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
       for (QuakeEntry qe : listBig) {
           System.out.println(qe);
       }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> listClose = filterByDistanceFrom(list, 1000.0*1000.0, city); 
        for (QuakeEntry qe : listClose) {
           System.out.println(qe);
        }
        System.out.println("Found " +listClose.size()+ " quakes that match that criteria."); 
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");    
        
        ArrayList<QuakeEntry> listDepth = filterByDepth(list, -10000.0, -5000.0);
        for (QuakeEntry qe : listDepth) {
           System.out.println(qe);
        }    
        System.out.println("Found " +listDepth.size()+ " quakes that match that criteria."); 
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");  
        
        ArrayList<QuakeEntry> listPhrase = filterByPhrase(list, "end", "California");
        for (QuakeEntry qe : listPhrase) {
           System.out.println(qe);
        }     
        System.out.println("Found " +listPhrase.size()+ " quakes that match that criteria.");         
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public void viewArrayList() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println(list);
    }
}
