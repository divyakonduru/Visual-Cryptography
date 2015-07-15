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

	public static BufferedImage Display(String path){
		
		BufferedImage test_image = null;
		try {
			test_image = ImageIO.read(new File(path));
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
	
	public static BufferedImage Save(BufferedImage black_white, File path){
		//BufferedImage save_image = null;
		try {
			ImageIO.write((RenderedImage) black_white, "png", path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static BufferedImage EncryptImage(String path, BufferedImage image){
		
		//Transform to B/W, encrypt
		
		return bw;
		
	}
	
}
