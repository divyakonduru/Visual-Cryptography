/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DecryptPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecryptPage frame = new DecryptPage();
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
	public DecryptPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{49, 56, 56, 56, 56, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JLabel lblDecrypt = new JLabel("Decryption");
		lblDecrypt.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDecrypt.setBorder(loweredetched);
		lblDecrypt.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDecrypt = new GridBagConstraints();
		gbc_lblDecrypt.fill = GridBagConstraints.BOTH;
		gbc_lblDecrypt.insets = new Insets(0, 0, 5, 0);
		gbc_lblDecrypt.gridx = 0;
		gbc_lblDecrypt.gridy = 0;
		contentPane.add(lblDecrypt, gbc_lblDecrypt);
		
		JButton btnImage1 = new JButton("Image 1");
		GridBagConstraints gbc_btnImage1 = new GridBagConstraints();
		gbc_btnImage1.fill = GridBagConstraints.BOTH;
		gbc_btnImage1.insets = new Insets(0, 50, 5, 50);
		gbc_btnImage1.gridx = 0;
		gbc_btnImage1.gridy = 1;
		contentPane.add(btnImage1, gbc_btnImage1);
		btnImage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser();

				 int ret = openFile.showOpenDialog(null);

				 System.out.println(ret);

				 if (ret==0) {

					 File file = openFile.getSelectedFile();

					 String filename = file.getName();

					 Main.image1_path = filename;

					 System.out.println(filename);
				 }
				 else{
					 System.out.println("Error selecting file");
				 }
			}
		});
		
		
		JButton btnImage2 = new JButton("Image 2");
		GridBagConstraints gbc_btnImage2 = new GridBagConstraints();
		gbc_btnImage2.fill = GridBagConstraints.BOTH;
		gbc_btnImage2.insets = new Insets(0, 50, 5, 50);
		gbc_btnImage2.gridx = 0;
		gbc_btnImage2.gridy = 2;
		contentPane.add(btnImage2, gbc_btnImage2);
		btnImage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser();

				 int ret = openFile.showOpenDialog(null);

				 System.out.println(ret);

				 if (ret==0) {

					 File file = openFile.getSelectedFile();

					 String filename = file.getName();

					 Main.image2_path = filename;

					 System.out.println(filename);
				 }
				 else{
					 System.out.println("Error selecting file");
				 }
			}
		});
		
		
		JButton btnSaveImage = new JButton("Save Image");
		GridBagConstraints gbc_btnSaveImage = new GridBagConstraints();
		gbc_btnSaveImage.fill = GridBagConstraints.BOTH;
		gbc_btnSaveImage.insets = new Insets(0, 50, 5, 50);
		gbc_btnSaveImage.gridx = 0;
		gbc_btnSaveImage.gridy = 3;
		contentPane.add(btnSaveImage, gbc_btnSaveImage);
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser();

				 int ret = openFile.showOpenDialog(null);

				 System.out.println(ret);

				 if (ret==0) {

					 File file = openFile.getSelectedFile();

					 String filename = file.getName();

					 Main.save_path = filename;

					 System.out.println(filename);
				 }
				 else{
					 System.out.println("Error selecting save file");
				 }
			}
		});
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Add decryption function
			}
		});
		GridBagConstraints gbc_btnDecrypt = new GridBagConstraints();
		gbc_btnDecrypt.fill = GridBagConstraints.BOTH;
		gbc_btnDecrypt.gridx = 0;
		gbc_btnDecrypt.gridy = 4;
		contentPane.add(btnDecrypt, gbc_btnDecrypt);
	}
}
