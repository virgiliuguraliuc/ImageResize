package org.fasttrackit.ImageResize;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImageResize {
    public static void main(String[] args) throws IOException {

        //use your own path, thanks!!
        File folder = new File("/home/virgiliu/Desktop/RESIZED/to_be_resized/");
        File[] listOfFiles = folder.listFiles();
        System.out.println("Total No of Files:"+listOfFiles.length);
        Image image = null;
        BufferedImage tempPNG = null;
        BufferedImage tempJPG = null;
        File newFilePNG = null;
        File newFileJPG = null;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                image = ImageIO.read(new File("/home/virgiliu/Desktop/RESIZED/to_be_resized/"+listOfFiles[i].getName()));
                tempPNG = resize(image, 200, 300);
                tempJPG = resize(image, 200, 300);
                newFilePNG = new File("/home/virgiliu/Desktop/RESIZED/resized/"+listOfFiles[i].getName()+"_200x300.png");
                newFileJPG = new File("/home/virgiliu/Desktop/RESIZED/resized/"+listOfFiles[i].getName()+"_200x300.jpg");
                ImageIO.write(tempPNG, "png", newFilePNG);
                ImageIO.write(tempJPG, "jpg", newFileJPG);
            }
        }
        System.out.println("ALL FILES DONE");
    }
    
    private static BufferedImage resize(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;

    }

    //    Tests
//        File input = new File("/home/virgiliu/Desktop/RESIZED/to_be_resized/oct7a6b.png");
//        BufferedImage image = ImageIO.read(input);
//
//        BufferedImage resized = resize(image, 300, 200);
//
//        File output = new File("/home/virgiliu/Desktop/RESIZED/resized/oct7a6b@200x300.png");
//        ImageIO.write(resized, "png", output);
    
}