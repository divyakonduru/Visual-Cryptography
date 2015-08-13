/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FirstPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application .
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstPage frame = new FirstPage();
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
		public FirstPage() {
			
			// Create main Jpanel
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			// Create label
			JLabel Title = new JLabel("VisualCryptography");
			Title.setBounds(37, 12, 378, 28);
			contentPane.add(Title);
			
			// Button to access encryption page
			JButton btnEncrypt = new JButton("Encrypt");
			btnEncrypt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					EncryptPage.main(null);
				}
			});
			btnEncrypt.setBounds(100, 110, 240, 25);
			contentPane.add(btnEncrypt);
			
			// Button to access decryption page
			JButton btnDecrypt = new JButton("Decrypt");
			btnDecrypt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DecryptPage.main(null);
				}
			});
			btnDecrypt.setBounds(100, 140, 240, 25);
			contentPane.add(btnDecrypt);
		}
}
