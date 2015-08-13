/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	
	//For Encryption
	static String path; // Currently opened image file path
	static String save_path; // Desired save file path
	static String save_key_path; // Save path for key
	static String save_cipher_path; // Save path for cipher
	static String save_key_magnified_path;  // Save path for magnified key
	static String save_cipher_magnified_path; // Save path for magnified cipher
	static File file; // Currently opened file
	static File bw_file; // Black and white version of original
	static File key_file; // Key image file
	static File cipher_file; // Cipher image file
	static File key_magnified_file; // Magnified key file
	static File cipher_magnified_file; // Magnified cipher file
	static File key_print_ready_file; // Print ready version of key (each pixel in original image is represented by a larger N pixel by N pixel square.  Allows printing to transparencies that can be seen clearly.
	static File cipher_print_ready_file; // Print ready version of cipher (each pixel in original image is represented by a larger N pixel by N pixel square.  Allows printing to transparencies that can be seen clearly.
	static BufferedImage originalImage; // Stores BufferedImage of original image
	static BufferedImage cipher_image; // Stores BufferedImage of cipher _image
	
	//For Decryption
	static String image1_path; // Path for first encrypted image
	static String image2_path; // Path for second encrypted image
	static String image_decrypt_path; // Path for decrypted image
	static String normal_size_decrypted_path; // Path for scaled down decrypted image
	static File image1_file; // File to hold first encrypted image
	static File image2_file; // File to hold second encrypted image
	static File image_decrypt_file; // File to hold decrypted image
	static File normal_size_decrypted_file; //File to hold normal sized image
	static BufferedImage image1; // BufferedImage to hold first encrypted image
	static BufferedImage image2; // BufferedImage to hold second encrypted image
	static BufferedImage decrypt_image; // BufferedImage to hold decrypted image
	static BufferedImage normal_size_decrypted_image; // // BufferedImage to hold scaled decrypted image
	
	public static void main(String[] args) {
		
		// Begin program and GUI
		FirstPage.main(null);
		
	}

}
