/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageFunctions {
	
	static final int BLACK = -16777216; // Constant to represent the RGB binary value of black. In binary - 1111111 00000000 00000000 00000000
	static final int WHITE = -1; // Constant to represent the RGB binary value of white. In binary - 1111111 1111111 1111111 1111111
	
	// Open a JFileChooser and return user selection as a string
	public static String GetPathName(){
		
		JFileChooser openFile = new JFileChooser();
		
		 int ret = openFile.showOpenDialog(null);

		 //System.out.println(ret); // Print Debugging Statement
		 
		 // Return null if no valid path is selected
		 if (ret==0) {

			 File file = openFile.getSelectedFile();

			 String filename = file.getAbsolutePath();

			 //System.out.println(filename);  // Print Debugging Statement
			 
			 return filename;
		 }
		 else{
			 
			 //System.out.println("Error selecting file"); // Print Debugging Statement
			 
			 return null;
		 }
		
	}

	// Displays an image in a new JFrame from a user selected file
	public static BufferedImage Display(File file, String title){
		
		BufferedImage display_image = null;
		
		// Attempt to read file from disk
		try {
			display_image = ImageIO.read(file);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		
		// Upon success display in new JFrame and return display_image
		JLabel output = new JLabel(new ImageIcon(display_image));
		JFrame sample = new JFrame(title);
		sample.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sample.getContentPane().add(output);
		sample.pack();sample.setLocation(600,50);
		sample.setVisible(true);
		
		return display_image;
	}
	
	// Displays an image in a new JFrame from a bufferedImage
	public static void Display_Image(BufferedImage img, String title){
		JLabel test = new JLabel(new ImageIcon(img));
		JFrame sample = new JFrame(title);
		sample.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sample.getContentPane().add(test);
		sample.pack();sample.setLocation(600,50);
		sample.setVisible(true);
	}
	
	// Saves an image to disk at desired path
	public static void Save(BufferedImage img, File path){
		try {
			ImageIO.write( img, "png", path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Converts original image into a cipher image using the key
	public static BufferedImage Create_Cipher(BufferedImage original, BufferedImage key){
		
		BufferedImage cipher_image = new BufferedImage(
		        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
		        BufferedImage.TYPE_BYTE_BINARY);

		// For every pixel in the original image, do the following:
		// 1. If the key pixel is black, flip the color of the original image and store in  cipher at identical location
		// 2. If the key pixel is white, set the cipher pixel to the same color as the original image
		for( int i = 0; i<cipher_image.getHeight(); i++){
			for(int j = 0; j<cipher_image.getWidth(); j++){
				if(key.getRGB(j, i) == BLACK){
					int temp = Get_and_Flip(original, i, j);
					cipher_image.setRGB(j, i, temp);
				}
				else{
					cipher_image.setRGB(j, i, original.getRGB(j, i));
				}
			}
		}
		return cipher_image;
	}
	
	// Convert a regular sized image into an image doubled in size.
	// Every original pixel will be converted into a 2x2 square as follows:
	// 1. If the original pixel was black, the 2x2 sqaure will look like:
	//        
	//                     |X| |
	//                     -----
	//                     | |X|
	//
	// 2. If the original pixel was white, the 2x2 square will look like:
	//        
	//                     | |X|
	//                     -----
	//                     |X| |
	//
	public static BufferedImage Magnify(BufferedImage img){
		
		BufferedImage magnified_image = new BufferedImage(
				img.getWidth()*2, img.getHeight()*2, BufferedImage.TYPE_BYTE_BINARY);
		
		for(int i = 0; i < img.getHeight(); i++){
			for(int j = 0; j < img.getWidth(); j++){
				if(img.getRGB(j, i) == BLACK){
					//        
					//                     |X| |
					//                     -----
					//                     | |X|
					//
					magnified_image.setRGB(j*2, i*2, BLACK);
					magnified_image.setRGB(j*2+1, i*2, WHITE);
					magnified_image.setRGB(j*2, i*2+1, WHITE);
					magnified_image.setRGB(j*2+1, i*2+1, BLACK);
					
				}
				else{
					//        
					//                     | |X|
					//                     -----
					//                     |X| |
					//
					magnified_image.setRGB(j*2, i*2, WHITE);
					magnified_image.setRGB(j*2+1, i*2, BLACK);
					magnified_image.setRGB(j*2, i*2+1, BLACK);
					magnified_image.setRGB(j*2+1, i*2+1, WHITE);
				}
			}
		}
		return magnified_image;
	}
	
	// Map every block of 4 pixels to a single pixel on the scaled image
	public static BufferedImage Shrink(BufferedImage img) {
		BufferedImage shrunk_image = new BufferedImage (
				img.getWidth()/2, img.getHeight()/2, BufferedImage.TYPE_BYTE_BINARY);
		
		for(int i = 0; i < img.getHeight(); i += 2) {
			for (int j = 0; j < img.getWidth(); j += 2) {
				if (img.getRGB(j, i) == BLACK) {
					shrunk_image.setRGB(j/2,  i/2,  BLACK);
				}
				else {
					shrunk_image.setRGB(j/2, i/2, WHITE);
				}
			}
		}
		
		return shrunk_image;
	}
	
	// Get the color of a particular bit and return the inverse of that color
	public static int Get_and_Flip(BufferedImage img, int i, int j){
		
		int initial = img.getRGB(j, i);
		
		if(initial == BLACK){
			return WHITE;
		}
		else{
			return BLACK;
		}
	}

	// Combine the two decrypted images together and return the result as output
	public static BufferedImage Decrypt(BufferedImage image1, BufferedImage image2) {
		
		// Ensure images are the same size 
		if (image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
			System.out.println("The size's of your selected images are mismatched");
			return null;
		}
		
		//Create a new buffered image to hold the decryption
		BufferedImage output = new BufferedImage(
				image1.getWidth(), image1.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		
		for (int i = 0; i < image1.getHeight(); i += 2) {
			for (int j = 0; j < image1.getWidth(); j += 2) {
				if (image1.getRGB(j, i) == BLACK && image2.getRGB(j+1,  i) == BLACK){
					output.setRGB(j, i, BLACK);
					output.setRGB(j+1, i, BLACK);
					output.setRGB(j, i+1, BLACK);
					output.setRGB(j+1, i+1, BLACK);
				}
				else if (image1.getRGB(j, i) == WHITE && image2.getRGB(j+1,  i) == WHITE){
					output.setRGB(j, i, BLACK);
					output.setRGB(j+1, i, BLACK);
					output.setRGB(j, i+1, BLACK);
					output.setRGB(j+1, i+1, BLACK);
				}
				else {
					output.setRGB(j, i, WHITE);
					output.setRGB(j+1, i, WHITE);
					output.setRGB(j, i+1, WHITE);
					output.setRGB(j+1, i+1, WHITE);
				}
												
			}
		}
		return output;
	}
	
	// Scale the encrypted images by a constant number of pixels
	static BufferedImage make_print_friendly(BufferedImage img) {
		
		int scale = 20; // 20 works well for inkjet printers, dependent on dpi
		
		BufferedImage print_image = new BufferedImage(
				img.getWidth()*scale, img.getHeight()*scale, BufferedImage.TYPE_BYTE_BINARY);
		
		// For each pixel in the original image:
		for(int i = 0; i < img.getHeight(); i++){
			for(int j = 0; j < img.getWidth(); j++){
				
				// If the pixel is black:
				if(img.getRGB(j, i) == BLACK){
					
					// make a square of size 'scale' that is black on the output image
					for( int x = 0; x < scale; x++) {
						for( int y = 0; y < scale; y++){
							print_image.setRGB(j*scale+y,i*scale+x, BLACK);
						}
					}				
				}
				
				// If the pixel is white:
				else{
					
					// make a square of size 'scale' that is white on the output image
					for( int x = 0; x < scale; x++) {
						for( int y = 0; y < scale; y++){
							print_image.setRGB(j*scale+y,i*scale+x, WHITE);
						}
					}
				}
			}
		}
		return print_image;
	}
}

