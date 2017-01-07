package justin_and_joel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import static justin_and_joel.ImageFunctions.getAndFlip;

/**
 * Created by Blackmon on 1/4/2017.
 */
public class Encrypt {
    private BufferedImage originalImage;
    private BufferedImage bwImage;
    private BufferedImage keyImage;
    static final int BLACK = -16777216;  // Constant to represent the RGB binary value of black. In binary - 1111111 00000000 00000000 00000000
    static final int WHITE = -1;  // Constant to represent the RGB binary value of white. In binary - 1111111 1111111 1111111 1111111

    public Encrypt(BufferedImage originalImage) {
        this.originalImage = originalImage;
        this.init();
    }

    private void init() {
        createBlackWhite();
        createKey();
    }
    public BufferedImage getBwImage() {
        return bwImage;
    }
    public BufferedImage getKeyImage() {
        return keyImage;
    }
    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    // Create Black and White image from original image
    private void createBlackWhite() {
        bwImage = new BufferedImage(
                originalImage.getWidth(), originalImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = bwImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
    }
    /* TODO: Do we even want to save or display this image?
    // Save and display black and white image file
    Main.bw_file = new File(Main.save_path + ".png");
				ImageFunctions.Save(bwImage, Main.bw_file);
				ImageFunctions.Display(Main.bw_file, "Original B/W");*/

    // Create image key
    private void createKey() {
        BufferedImage keyImage = new BufferedImage(
                originalImage.getWidth(), originalImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        // Generate a random key
        Random rand = new Random();
        try {
            SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");

            for(int i = 0; i < keyImage.getHeight(); i++){
                for(int j = 0; j < keyImage.getWidth(); j++){
                    int result = secureRandomGenerator.nextInt(100);
                    if(result < 50){
                        keyImage.setRGB(j, i, WHITE);
                    }
                    else{
                        keyImage.setRGB(j, i, BLACK);
                    }
                }
            }
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    // Converts original image into a cipher image using the key
    public BufferedImage createCipher(){

        BufferedImage cipherImage = new BufferedImage(
                originalImage.getWidth(), originalImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        // For every pixel in the original image, do the following:
        // 1. If the key pixel is black, flip the color of the original image and store in  cipher at identical location
        // 2. If the key pixel is white, set the cipher pixel to the same color as the original image
        for( int i = 0; i<cipherImage.getHeight(); i++){
            for(int j = 0; j<cipherImage.getWidth(); j++){
                if(keyImage.getRGB(j, i) == BLACK){
                    int temp = getAndFlip(originalImage, i, j);
                    cipherImage.setRGB(j, i, temp);
                }
                else{
                    cipherImage.setRGB(j, i, originalImage.getRGB(j, i));
                }
            }
        }
        return cipherImage;
    }
}
