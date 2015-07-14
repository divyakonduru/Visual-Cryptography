package justin_and_joel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EncryptPage extends JFrame {
	
	private boolean imageFlag;
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Create Encrypted Images");
		lblTitle.setBounds(138, 12, 192, 15);
		contentPane.add(lblTitle);
		
		rdbtnImage = new JRadioButton("Image");
		rdbtnImage.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				imageFlag = true;
				System.out.println(imageFlag);
				rdbtnText.setSelected(false);
			}
		});
		rdbtnImage.setBounds(8, 35, 85, 23);
		contentPane.add(rdbtnImage);
		
		rdbtnText = new JRadioButton("Text");	
		rdbtnText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				imageFlag = false;
				System.out.println(imageFlag);
				rdbtnImage.setSelected(false);
			}
		});
		
		rdbtnText.setBounds(91, 35, 79, 23);
		contentPane.add(rdbtnText);
		
		btnOriginal = new JButton("Original");
		btnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(imageFlag == false){
					System.out.println("Select image radio button");
					return;
				}
				else{
					
					Main.path = ImageFunctions.GetPathName();
					
					Main.originalImage = ImageFunctions.Display(Main.path);
				}
			}
		});
		btnOriginal.setBounds(12, 66, 117, 25);
		contentPane.add(btnOriginal);
		
		btnModified = new JButton("Modified");
		btnModified.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(imageFlag == false){
					System.out.println("Select image radio button");
					return;
				}
				else{
					Main.save_path = ImageFunctions.GetPathName();
					
					//ImageFunctions.Display(Main.path);
				}
			}
		});
		btnModified.setBounds(12, 100, 117, 25);
		contentPane.add(btnModified);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(12, 137, 70, 15);
		contentPane.add(lblMessage);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(12, 164, 192, 79);
		contentPane.add(textArea);
		
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textArea.getText();
				if(imageFlag == false && text.equals("")){
					System.out.println("Enter some text");
					
				}
				else if(imageFlag == false && !text.equals("")){
					//Add function to convert text to image
					
				}
				else if(imageFlag == true){
					//Add function for encrypting
					ImageFunctions.EncryptImage(Main.save_path, Main.originalImage);
					
				}
			}
		});
		btnEncrypt.setBounds(12, 255, 97, 25);
		contentPane.add(btnEncrypt);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(121, 255, 79, 25);
		contentPane.add(btnClear);
		
		
		
	}
	
	
}
