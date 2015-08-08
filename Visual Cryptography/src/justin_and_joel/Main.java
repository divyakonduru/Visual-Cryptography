/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.stream.ImageOutputStream;

public class Main {

	/**
	 * @param args
	 */
	
	//For Encryption
	static String path; //Holds current file path
	static String save_path; // Saved file path
	static String save_key_path;
	static String save_cipher_path;
	static String save_key_magnified_path;
	static String save_cipher_magnified_path;
	static File file; //Holds current open file
	static File bw_file; //Holds black and white original
	static File key_file;//Holds key image
	static File cipher_file;//Holds cipher image
	static File key_magnified_file;
	static File cipher_magnified_file;
	static BufferedImage originalImage;
	static BufferedImage cipher_image;
	
	//For Decryption
	static String image1_path;
	static String image2_path;
	static String image_decrypt_path;
	static String normal_size_decrypted_path;
	static File image1_file;
	static File image2_file;
	static File image_decrypt_file;
	static File normal_size_decrypted_file;
	static BufferedImage image1;
	static BufferedImage image2;
	static BufferedImage decrypt_image;  //Holds the decrypted image
	static BufferedImage normal_size_decrypted_image;
	
	public static void main(String[] args) {
		
		FirstPage.main(null);
		
	}

}
