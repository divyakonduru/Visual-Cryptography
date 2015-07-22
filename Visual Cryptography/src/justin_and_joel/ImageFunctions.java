/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageFunctions {
	
	public static String GetPathName(){
		JFileChooser openFile = new JFileChooser();
		
		 int ret = openFile.showOpenDialog(null);

		 System.out.println(ret);

		 if (ret==0) {

			 File file = openFile.getSelectedFile();

			 String filename = file.getAbsolutePath();

			 System.out.println(filename);
			 
			 
			 return filename;
		 }
		 else{
			 
			 System.out.println("Error selecting file");
			 
			 return null;
		 }
		
	}

	public static BufferedImage Display(File file){
		
		BufferedImage test_image = null;
		try {
			test_image = ImageIO.read(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel test = new JLabel(new ImageIcon(test_image));
		JFrame sample = new JFrame();
		sample.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sample.getContentPane().add(test);
		sample.pack();sample.setLocation(600,50);
		sample.setVisible(true);
		
		return test_image;
	}
	
	public static void Display_Image(BufferedImage img){
		JLabel test = new JLabel(new ImageIcon(img));
		JFrame sample = new JFrame();
		sample.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sample.getContentPane().add(test);
		sample.pack();sample.setLocation(600,50);
		sample.setVisible(true);
		
	}
	
	public static BufferedImage Save(BufferedImage img, File path){
		//BufferedImage save_image = null;
		try {
			ImageIO.write( img, "png", path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static BufferedImage Magnify(BufferedImage img){
		
		BufferedImage magnified_image = new BufferedImage(
				img.getWidth()*2, img.getHeight()*2, BufferedImage.TYPE_BYTE_BINARY);
		
		for(int i = 0; i < img.getHeight(); i++){
			for(int j = 0; j < img.getWidth(); j++){
				if(img.getRGB(j, i) == -16777216){
					magnified_image.setRGB(j*2, i*2, 0);
					magnified_image.setRGB(j*2+1, i*2, -1);
					magnified_image.setRGB(j*2, i*2+1, -1);
					magnified_image.setRGB(j*2+1, i*2+1, 0);
					
				}
				else{
					magnified_image.setRGB(j*2, i*2, -1);
					magnified_image.setRGB(j*2+1, i*2, 0);
					magnified_image.setRGB(j*2, i*2+1, 0);
					magnified_image.setRGB(j*2+1, i*2+1, -1);
				}
			}
		}
		return magnified_image;
	}
	
	//public static BufferedImage EncryptImage(String path, BufferedImage image){
		
		//Transform to B/W, encrypt
		
		//return bw;
		
	//}
	
}
