package org.fasttrackit.ImageResize;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;



public class ImageResize {
    public static void main(String[] args) throws IOException {
        System.out.println("Virgiliu's bulk image Resizer");
        //use your own path, thanks!!
        File folder = new File("/home/virgiliu/Desktop/RESIZED/to_be_resized/");
        File[] listOfFiles = folder.listFiles();
        System.out.println("Total No of Files:" + listOfFiles.length);
        Image image = null;
        BufferedImage tempPNG = null;
        BufferedImage tempJPG = null;
        File newFilePNG = null;
        File newFileJPG = null;

        String path1 = "/home/virgiliu/Desktop/RESIZED/to_be_resized/";
        String path2 = "/home/virgiliu/Desktop/RESIZED/resized/";

        System.out.println("type Width");
        int width = userIntSelect();
        System.out.println("type Height");
        int height = userIntSelect();
        System.out.println("YOU HAVE SELECTED " + width + " X " + height);

        System.out.println("\n What do you need masta?" +
                "\n type 1 for PNG" +
                "\n type 2 for JPG" +
                "\n type 3 for PNG and JPG");
        int selector = userIntSelect();

        switch (selector) {

            case 1:
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        System.out.println("File " + listOfFiles[i].getName());
                        image = ImageIO.read(new File(path1 + listOfFiles[i].getName()));
                        tempPNG = resize(image, width, height);
                        newFilePNG = new File(path2 + listOfFiles[i].getName() + "_" + width + "X" + height + ".png");
                        ImageIO.write(tempPNG, "png", newFilePNG);

                    }
                } break;
            case 2:
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        System.out.println("File " + listOfFiles[i].getName());
                        image = ImageIO.read(new File(path1 + listOfFiles[i].getName()));
                        tempJPG = resize(image, width, height);
                        newFileJPG = new File(path2 + listOfFiles[i].getName()+ "_" + width + "X" + height + ".jpg");
                        ImageIO.write(tempJPG, "jpg", newFileJPG);
                    }
                } break;
            case 3:
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        System.out.println("File " + listOfFiles[i].getName());
                        image = ImageIO.read(new File(path1 + listOfFiles[i].getName()));
                        tempPNG = resize(image, width, height);
                        tempJPG = resize(image, width, height);
                        newFilePNG = new File(path2 + listOfFiles[i].getName() + "_" + width + "X" + height + ".png");
                        newFileJPG = new File(path2 + listOfFiles[i].getName()+ "_" + width + "X" + height + ".jpg");
                        ImageIO.write(tempPNG, "png", newFilePNG);
                        ImageIO.write(tempJPG, "jpg", newFileJPG);
                    }
                } break;
            default:
                System.out.println("ALL FILES DONE");
        }


    }

    private static int userIntSelect() {
        Scanner input = new Scanner(System.in);
        int number = 0;
        try {
            number = input.nextInt();

        } catch (InputMismatchException e) {

            System.out.println("Int only, please.");
            userIntSelect();
        }
        return  number;
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