import edu.duke.*;
import java.io.*;
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int invertedRed = (255 - inPixel.getRed());
            int invertedBlue = (255 - inPixel.getBlue());
            int invertedGreen = (255 - inPixel.getGreen());
            pixel.setRed(invertedRed);
            pixel.setGreen(invertedBlue);
            pixel.setBlue(invertedGreen);
        }
        return outImage;
    }  
    public void testInversions() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeInversion(ir);
        gray.draw();
    } 
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource invert = makeInversion(image);
            String iname = image.getFileName();
            String iNewName = "invert" + iname;
            invert.setFileName("Users/Cheng/bluej_projects/Grayscale/images" + iNewName);
            invert.setFileName(f.getParentFile() + "/" + iNewName);
            invert.draw();
            invert.save();
        }
    }
}    

