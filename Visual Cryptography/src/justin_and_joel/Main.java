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
	
	static String path; //Holds current file path
	static String save_path; // Saved file path
	static String save_key_path;
	static String save_cipher_path;
	static File file; //Holds current open file
	static File bw_file; //Holds black and white original
	static File key_file;//Holds key image
	static File cipher_file;//Holds cipher image
	static BufferedImage originalImage;
	static BufferedImage cipher_image;
	
	public static void main(String[] args) {
		
		FirstPage.main(null);
		
	}

}
