package netgloo.controllers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.util.StringTokenizer;

import static java.awt.image.BufferedImage.*;

/**
 * Created by G551 on 06/05/2016.
 */
public class ImageHelper {

    private static String fileDirectory = "D:/image/graysclae_image/";
    private static String testFileDirectory = "D:/image/average_image/";
    private static final int IMG_WIDTH = 64;
    private static final int IMG_HEIGHT = 64;

    public static BufferedImage scale(BufferedImage image) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, image.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

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

    public static int[][] covertImageToGrayMatrix(BufferedImage original) {

        int alpha, red, green, blue;
        int newPixel;

        int[][] result = new int[IMG_WIDTH][IMG_HEIGHT];

        for(int i=0; i < original.getWidth(); i++) {
            for(int j=0; j < original.getHeight(); j++) {
                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                red = (int) (0.299 * red + 0.587 * green + 0.114 * blue);

                result[i][j] = red;
            }
        }

        return result;
    }

    public static void printMatrixToFile(FileOutputStream out, double[][] A) throws IOException{
        int n = A.length;
        int m = A[0].length;

        StringBuffer sb = new StringBuffer(n + " " + m + "\r\n");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                sb.append(A[i][j] + " ");
            }
            sb.append("\r\n");
        }

        out.write(sb.toString().getBytes());
    }

    public static void printMatrixToFile(FileOutputStream out, int[][] A) throws IOException{
        int n = A.length;
        int m = A[0].length;
        StringBuffer sb = new StringBuffer(n + " " + m + "\r\n");

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++)
                sb.append(A[i][j] + " ");
            sb.append("\r\n");
        }

        out.write(sb.toString().getBytes());
    }

    public static double[][] readMatrixFromFile(File file) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(file));

        // read in first matrix, get it's dimensions
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        double[][] result = new double[n][p];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(in.readLine());
            for(int k=0; k<p; k++){
                result[i][k] = Double.parseDouble(st.nextToken());
            }
        }

        return result;
    }

    public static double[][] computeAverageMatrix(double[][] imageUserMatrix, int[][] newImageMatrix, int sum) {

        double[][] eigenFaces = new double[IMG_HEIGHT][IMG_WIDTH];
        int count = sum-1;
        for(int i = 0 ; i < IMG_HEIGHT ; i++) {
            for(int j = 0 ; j < IMG_WIDTH ; j++) {
                eigenFaces[i][j] = (imageUserMatrix[i][j]*count + (double)newImageMatrix[i][j])/sum;
            }
        }

        return eigenFaces;
    }

    public static double computeImageDistanceByEuclid(double[][] imageUserMatrix, int[][] newImageMatrix) {
        double distance = 0;
        for(int i = 0 ; i < IMG_HEIGHT ; i++) {
            for(int j = 0 ; j < IMG_WIDTH ; j++) {
                distance = distance + Math.pow(imageUserMatrix[i][j]- newImageMatrix[i][j], 2);
            }
        }

        return distance;
    }

    public static double computeImageDistanceByManhattan(double[][] imageUserMatrix, int[][] newImageMatrix) {
        double distance = 0;
        for(int i = 0 ; i < IMG_HEIGHT ; i++) {
            for(int j = 0 ; j < IMG_WIDTH ; j++) {
                distance = distance + Math.abs(imageUserMatrix[i][j]- newImageMatrix[i][j]);
            }
        }

        return distance;
    }

    public static void writeImage(String imageName, BufferedImage image) throws IOException {
        File file = new File(fileDirectory + imageName);
        ImageIO.write(image, "jpg", file);
    }

    public static void writeAverageImage(double[][] matrix, String imageName) throws IOException {

        BufferedImage theImage = new BufferedImage(IMG_HEIGHT, IMG_WIDTH, TYPE_INT_RGB);
        for(int x = 0; x < IMG_HEIGHT; x++){
            for(int y = 0; y < IMG_WIDTH; y++){
                int value = (int)matrix[x][y]<<16 | (int)matrix[x][y] << 8 | (int)matrix[x][y];
                theImage.setRGB(x, y, value);
            }
        }

        File file = new File(testFileDirectory + imageName);
        ImageIO.write(theImage, "jpg", file);
    }

    public static void readMatrix(double[][] matrix) {

        int rowCount = matrix.length;
        int columnCount = matrix[0].length;

        for(int i = 0 ; i < rowCount ; i++) {
            System.out.println("row " + i + ": ");
            for(int j = 0 ; j < columnCount ; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

    public static void readMatrix(int[][] matrix) {

        int rowCount = matrix.length;
        int columnCount = matrix[0].length;

        for(int i = 0 ; i < rowCount ; i++) {
            System.out.println("row " + i + ": ");
            for(int j = 0 ; j < columnCount ; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }
}
