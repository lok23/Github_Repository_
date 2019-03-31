import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*
        System.out.println(list);
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
        //System.out.println(indexOfLargest(list));
        ArrayList<QuakeEntry> listLargest = getLargest(list, 5);
        for(QuakeEntry quake : listLargest){
            System.out.println(quake);
        }
        System.out.println("Total number of earthquakes read in: " + list.size());
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = 0;
        double maxMagnitude = 0.0;
        for (int k=0; k < data.size(); k++){
            QuakeEntry quake = data.get(k);            
            double currMagnitude = quake.getMagnitude();            
            if (currMagnitude > maxMagnitude) {
                index = k;
                maxMagnitude = currMagnitude;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, Integer howMany) {
        ArrayList<QuakeEntry> tempData = data; 
        ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
        while(largest.size() < howMany){
            int index = indexOfLargest(tempData);
            largest.add(tempData.get(index));
            tempData.remove(index);
        }
        
        return largest;
    }
}
    
    /*public ArrayList<QuakeEntry> getLargestV2(ArrayList<QuakeEntry> data, Integer howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();        
        for (int j=0; j <= howMany; j++) {
            indexOfLargest(ArrayList<QuakeEntry> data)
            */

