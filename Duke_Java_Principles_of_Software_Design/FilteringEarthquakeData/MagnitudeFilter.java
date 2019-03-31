
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMag = 0.0;
    private double maxMag = 0.0;
    private String name;
    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
        name = "MagnitudeFilter";
    }
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag);
    }

    public String getName() {
        return name;
    }
}
