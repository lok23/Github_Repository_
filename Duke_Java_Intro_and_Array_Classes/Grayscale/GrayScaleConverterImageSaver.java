import edu.duke.*;
import java.io.*;
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrayScaleConverterImageSaver {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGray(image);
            String gname = image.getFileName();
            String gNewName = "grayscale" + gname;
            gray.setFileName("Users/Cheng/bluej_projects/Grayscale/images" + gNewName);
            gray.setFileName(f.getParentFile() + "/" + gNewName);
            gray.draw();
            gray.save();
        }
    }
}

