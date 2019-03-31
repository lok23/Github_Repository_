
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location givenLoc;
    private double maxDist;
    private String name;
    public DistanceFilter(Location loc, double max) {
        givenLoc = loc;
        maxDist = max;
        name = "DistanceFilter";
    }
    public boolean satisfies(QuakeEntry qe) {
        Location quakeLoc = qe.getLocation();
        if (givenLoc.distanceTo(quakeLoc) < maxDist) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
}
