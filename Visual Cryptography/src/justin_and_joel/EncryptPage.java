/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.swing.JTextPane;
import javax.swing.JCheckBox;

public class EncryptPage extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	static final int BLACK = -16777216;  // Constant to represent the RGB binary value of black. In binary - 1111111 00000000 00000000 00000000
	static final int WHITE = -1;  // Constant to represent the RGB binary value of white. In binary - 1111111 1111111 1111111 1111111
	private boolean imageFlag;  // Flag used to track state of image radio button
	private boolean textFlag;  // Flag used to track state of text radio button
	
	// GUI control declarations
	JRadioButton rdbtnImage;
	JRadioButton rdbtnText;
	JButton btnOriginal;
	JButton btnModified;
	JTextArea textArea;
	JButton btnEncrypt;
	JButton btnClear;
	private JPanel contentPane;

	/**
	 * Launch the application .
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptPage frame = new EncryptPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EncryptPage() {
		
		imageFlag = false;
		textFlag = false;
		
		// Create encryption page Jpanel  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Title of encryption page
		JLabel lblTitle = new JLabel("Create Encrypted Images");
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitle.setBorder(loweredetched);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(69, 12, 304, 23);
		contentPane.add(lblTitle);
		
		// Label next to Original Image button that displays selected image path
		final JLabel Original_Path_Name = new JLabel("No Path Selected");
		Original_Path_Name.setBounds(147, 64, 291, 30);
		contentPane.add(Original_Path_Name);
		
		// Label next to Save Image button that displays selected save path
		final JLabel Save_Path_Name = new JLabel("No Save Path Selected");
		Save_Path_Name.setBounds(147, 97, 291, 30);
		contentPane.add(Save_Path_Name);
		
		// Check box to allow output to include printer friendly copies
		final JCheckBox chckbxIncludePrintFriendly = new JCheckBox("Add Print Friendly Copy");
		chckbxIncludePrintFriendly.setBounds(232, 256, 206, 23);
		contentPane.add(chckbxIncludePrintFriendly);
		
		// Radio button that indicates input from an image file
		rdbtnImage = new JRadioButton("Image");
		rdbtnImage.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				imageFlag = true;
				textFlag = false;
				
				//System.out.println("imageFlag is: "+ imageFlag);   // Print debug statement
				rdbtnText.setSelected(false);
			}
		});
		rdbtnImage.setBounds(8, 35, 85, 23);
		contentPane.add(rdbtnImage);
		
		// Radio button that indicates input from an image file
		rdbtnText = new JRadioButton("Text");	
		rdbtnText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				imageFlag = false;
				textFlag = true;
				//System.out.println("imageFlag is: " + imageFlag);  // Print debug statement
				rdbtnImage.setSelected(false);
			}
		});
		
		rdbtnText.setBounds(91, 35, 79, 23);
		contentPane.add(rdbtnText);
		
		btnOriginal = new JButton("Original");
		btnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(imageFlag == false && textFlag == false){
					System.out.println("Select image radio button");
					return;
				}
				else{
					
					Main.path = ImageFunctions.GetPathName();
					
					// Handles case where user cancels file selection
					try{
						Main.file = new File(Main.path);
						Main.originalImage = ImageFunctions.Display(Main.file, "Original");
					} catch (NullPointerException e) {
						System.out.println("An invalid file path was returned");
						return;
					}
		
					//Update label to display selected path
					Original_Path_Name.setText(Main.path);
				}
			}
		});
		
		
		btnOriginal.setBounds(12, 66, 117, 25);
		contentPane.add(btnOriginal);
		
		btnModified = new JButton("Modified");
		btnModified.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(imageFlag == false && Main.path == null && rdbtnText.isSelected() == false){
					System.out.println("Select image radio button/select an original image");
					return;
				}
				else{
					Main.save_path = ImageFunctions.GetPathName();
					Main.save_key_path = Main.save_path + "_key.png";
					Main.key_file = new File(Main.save_path+ "_key.png");
					System.out.println("Save key: " + Main.save_key_path);
										
					Main.save_cipher_path = Main.save_path + "_cipher.png";
					Main.cipher_file = new File(Main.save_cipher_path);
					System.out.println("Save cipher: " + Main.save_cipher_path);
					
					//Update save label to display selected path
					Save_Path_Name.setText(Main.save_path += ".png");
									
					
				}
			}
		});
		btnModified.setBounds(12, 100, 117, 25);
		contentPane.add(btnModified);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(12, 137, 70, 15);
		contentPane.add(lblMessage);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 24));
		textArea.setLineWrap(true);
		textArea.setBounds(12, 164, 426, 79);
		contentPane.add(textArea);
		
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textArea.getText();
				if(imageFlag == false && text.equals("")){
					System.out.println("Enter some text");
					return;
				}
				
				if(imageFlag == false && textFlag == true){
					
					System.out.println("converting textbox to image");
					
					BufferedImage text_image = new BufferedImage(textArea.getWidth(), textArea.getHeight(), BufferedImage.TYPE_BYTE_BINARY );
					Graphics2D graphic = text_image.createGraphics();
					textArea.printAll(graphic);
					graphic.dispose();
					
					//ImageFunctions.Display_Image(text_image, "Text converted to image");
					Main.originalImage = text_image;
				}
				
				//File names and paths for the magnified images
				Main.save_key_magnified_path = Main.save_path + "_key_magnified.png";
				Main.save_cipher_magnified_path = Main.save_path + "_cipher_magnified.png";
				Main.key_magnified_file = new File(Main.save_key_magnified_path);
				Main.cipher_magnified_file = new File(Main.save_cipher_magnified_path);
									
				BufferedImage black_white = new BufferedImage(
				        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
				        BufferedImage.TYPE_BYTE_BINARY);
				    
				Graphics2D graphics = black_white.createGraphics();
				graphics.drawImage(Main.originalImage, 0, 0, null);

				
				Main.bw_file = new File(Main.save_path + ".png");
				ImageFunctions.Save(black_white, Main.bw_file);
				ImageFunctions.Display(Main.bw_file, "Original B/W");
				
				BufferedImage key_image = new BufferedImage(
				        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
				        BufferedImage.TYPE_BYTE_BINARY);
				
				Random rand = new Random();
				try {
					SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");
					
					for(int i = 0; i < key_image.getHeight(); i++){
						for(int j = 0; j < key_image.getWidth(); j++){
							
							int result = secureRandomGenerator.nextInt(100);
							if(result < 50){
								key_image.setRGB(j, i, WHITE);
							}
							else{
								key_image.setRGB(j, i, BLACK);
							}
						}
						
					}
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
									
				ImageFunctions.Display_Image(key_image, "Key");
				ImageFunctions.Save(key_image, Main.key_file);
				
				//BufferedImage magnified_key_image = new BufferedImage(
						//key_image.getWidth() * 2, key_image.getHeight() * 2, BufferedImage.TYPE_BYTE_BINARY);
				
				BufferedImage magnified_key_image = ImageFunctions.Magnify(key_image);
				ImageFunctions.Save(magnified_key_image, Main.key_magnified_file);
				ImageFunctions.Display_Image(magnified_key_image, "Magnified key");
				
				Main.cipher_image = ImageFunctions.Create_Cipher(black_white, key_image);
				BufferedImage magnified_cipher_image = ImageFunctions.Magnify(Main.cipher_image);
				ImageFunctions.Save(magnified_cipher_image, Main.cipher_magnified_file);
				ImageFunctions.Display_Image(magnified_cipher_image, "Magnified Cipher");
				
				if (chckbxIncludePrintFriendly.isSelected()) {
					System.out.println("The printer friendly check box is selected, outputting printer sized pics");
					
					BufferedImage print_ready_test = ImageFunctions.make_print_friendly(black_white);
					ImageFunctions.Display_Image(print_ready_test, "Print Ready");
					
					BufferedImage print_ready_key = ImageFunctions.make_print_friendly(magnified_key_image);
					String print_ready_key_path = Main.save_path + "_key_print_ready.png";
					File print_ready_key_file = new File(print_ready_key_path);
					ImageFunctions.Save(print_ready_key, print_ready_key_file);
					
					BufferedImage print_ready_cipher = ImageFunctions.make_print_friendly(magnified_cipher_image);
					String print_ready_cipher_path = Main.save_path + "_cipher_print_ready.png";
					File print_ready_cipher_file = new File(print_ready_cipher_path);
					ImageFunctions.Save(print_ready_cipher, print_ready_cipher_file);
					}
			}
		});
		btnEncrypt.setBounds(12, 255, 97, 25);
		contentPane.add(btnEncrypt);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(121, 255, 79, 25);
		contentPane.add(btnClear);
		

		

		

		
		
		
	}
}
