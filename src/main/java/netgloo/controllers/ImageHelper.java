package netgloo.controllers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * Created by G551 on 06/05/2016.
 */
public class ImageHelper {

    private static String fileDirectory = "D:/image/gray/";
    private static final int IMG_WIDTH = 64;
    private static final int IMG_HEIGHT = 64;

    // Convert R, G, B, Alpha to standard 8 bit
    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    public static BufferedImage covertImageToGray(BufferedImage original) {

        int alpha, red, green, blue;
        int newPixel;

        BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {
                // Get pixels by R, G, B
                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                red = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                // Return back to original format
                newPixel = colorToRGB(alpha, red, red, red);
                // Write pixels into image
                lum.setRGB(i, j, newPixel);
            }
        }

        return lum;
    }

    public static BufferedImage scale(BufferedImage image) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, image.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public static void writeGrayScaleImage(String imageName, BufferedImage image) throws IOException {
        File file = new File(fileDirectory + imageName);
        ImageIO.write(scale(covertImageToGray(image)), "jpg", file);
    }

    private static double[][] computeEigenFaces(double[][] imageUserMatrix, int[][] newImageMatrix, int count) {

        double[][] eigenFaces = new double[IMG_HEIGHT][IMG_WIDTH];
        int sum = count+1;
        for(int i = 0 ; i < IMG_HEIGHT ; i++) {
            for(int j = 0 ; j < IMG_WIDTH ; j++) {
                eigenFaces[i][j] = (imageUserMatrix[i][j]*count + newImageMatrix[i][j])/sum;
            }
        }
        return eigenFaces;
    }

    public static double computeImageDistance(double[][] imageUserMatrix, int[][] newImageMatrix) {
        double distance = 0;
        for(int i = 0 ; i < IMG_HEIGHT ; i++) {
            for(int j = 0 ; j < IMG_WIDTH ; j++) {
                distance = distance + Math.pow(imageUserMatrix[i][j]- newImageMatrix[i][j], 2);
            }
        }
        return distance;
    }

    private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {
        System.out.println(" convertTo2DWithoutUsingGetRGB: ");

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

    public static void readImageMatrix(BufferedImage image) {
        System.out.println(" readImageMatrix: ");
        int[][] imageMatrix = convertTo2DWithoutUsingGetRGB(scale(image));

        int rowCount = imageMatrix.length;
        int columnCount = imageMatrix[0].length;

        for(int i = 0 ; i < rowCount ; i++) {
            System.out.println("row " + i + ": ");
            for(int j = 0 ; j < columnCount ; j++) {
                System.out.print(imageMatrix[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

    public static void readRGBImageMatrix(BufferedImage inputImage) {
        BufferedImage image = scale(inputImage);
        int alpha, red, green, blue;
        for(int i=0; i<image.getWidth(); i++) {
            System.out.println("row " + i + ": ");
            for(int j=0; j<image.getHeight(); j++) {

                // Get pixels by R, G, B
                alpha = new Color(image.getRGB(i, j)).getAlpha();
                red = new Color(image.getRGB(i, j)).getRed();
                green = new Color(image.getRGB(i, j)).getGreen();
                blue = new Color(image.getRGB(i, j)).getBlue();

                System.out.print(red + "." + green + "." + blue + "   ");
            }
            System.out.println(" ");
        }
    }
}
