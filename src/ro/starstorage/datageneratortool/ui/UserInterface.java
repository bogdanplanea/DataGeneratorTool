package ro.starstorage.datageneratortool.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserInterface {
	private static final Logger LOG = LogManager.getLogger(UserInterface.class.getName());
	private JFrame frmDataGenerator;
	private static TextArea textAreaGeneral;
	private static TextArea textAreaInput;
	private static JTextField textFieldNrRepetari;
	private static JComboBox<String> comboBoxTipFisier;
	private static JComboBox<String> comboBoxFisierContinut;
	private JTextPane txtpnNrRepetari;
	private JButton btnGenereaza;
	private JScrollPane scrollPaneTokens;
	private JTextPane txtpnGeneral;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frmDataGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDataGenerator = new JFrame();
		frmDataGenerator.getContentPane().setFont(new Font("Cooper Black", Font.PLAIN, 14));
		frmDataGenerator.setResizable(false);
		frmDataGenerator.setTitle("Data Generator");
		frmDataGenerator.setBounds(100, 100, 768, 493);
		frmDataGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDataGenerator.getContentPane().setLayout(null);
		frmDataGenerator.getContentPane().setBackground(new Color(211, 211, 211));

		comboBoxTipFisier = new JComboBox<String>();
		comboBoxTipFisier.setBounds(164, 58, 139, 23);
		frmDataGenerator.getContentPane().add(comboBoxTipFisier);

		comboBoxTipFisier.addItem("Fisier .CSV");
		comboBoxTipFisier.addItem("Script .SQL");
		comboBoxTipFisier.addItem("Script .SQL si executie in BD");
		
		JTextPane txtpnTipExoprt = new JTextPane();
		txtpnTipExoprt.setForeground(new Color(0, 0, 51));
		txtpnTipExoprt.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		txtpnTipExoprt.setText("Tip export");
		txtpnTipExoprt.setEditable(false);
		txtpnTipExoprt.setBackground(UIManager.getColor("ArrowButton.disabled"));
		txtpnTipExoprt.setBounds(36, 58, 102, 23);
		frmDataGenerator.getContentPane().add(txtpnTipExoprt);

		txtpnNrRepetari = new JTextPane();
		txtpnNrRepetari.setForeground(new Color(0, 0, 51));
		txtpnNrRepetari.setText("Nr. repetari");
		txtpnNrRepetari.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		txtpnNrRepetari.setEditable(false);
		txtpnNrRepetari.setBackground((Color) null);
		txtpnNrRepetari.setBounds(36, 89, 102, 23);
		frmDataGenerator.getContentPane().add(txtpnNrRepetari);
		

		textFieldNrRepetari = new JTextField();
		textFieldNrRepetari.setBounds(164, 92, 139, 20);
		frmDataGenerator.getContentPane().add(textFieldNrRepetari);
		textFieldNrRepetari.setColumns(10);
		textFieldNrRepetari.addKeyListener(new MyActionListener(textFieldNrRepetari)); 
	        

		JTextPane txtpnFisierContinut = new JTextPane();
		txtpnFisierContinut.setForeground(new Color(0, 0, 51));
		txtpnFisierContinut.setText("Fisier continut");
		txtpnFisierContinut.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		txtpnFisierContinut.setEditable(false);
		txtpnFisierContinut.setBackground((Color) null);
		txtpnFisierContinut.setBounds(36, 119, 119, 20);
		frmDataGenerator.getContentPane().add(txtpnFisierContinut);

		JComboBox<String> comboBoxFisierContinut = new JComboBox<String>();
		comboBoxFisierContinut.setBounds(164, 119, 139, 23);
		frmDataGenerator.getContentPane().add(comboBoxFisierContinut);

		comboBoxFisierContinut.addItem("Nu");
		comboBoxFisierContinut.addItem("Da");

		textAreaInput = new TextArea();
		textAreaInput.setBounds(36, 200, 403, 133);
		frmDataGenerator.getContentPane().add(textAreaInput);

		JTextPane txtpnInput = new JTextPane();
		txtpnInput.setForeground(new Color(0, 0, 0));
		txtpnInput.setText("Input:");
		txtpnInput.setFont(new Font("Courier New", Font.BOLD, 15));
		txtpnInput.setEditable(false);
		txtpnInput.setBackground((Color) null);
		txtpnInput.setBounds(36, 177, 102, 23);
		frmDataGenerator.getContentPane().add(txtpnInput);

		JTextPane txtpnTokens = new JTextPane();
		txtpnTokens.setText("Tokens:");
		txtpnTokens.setFont(new Font("Courier New", Font.BOLD, 15));
		txtpnTokens.setEditable(false);
		txtpnTokens.setBackground((Color) null);
		txtpnTokens.setBounds(591, 177, 102, 23);
		frmDataGenerator.getContentPane().add(txtpnTokens);

		JLabel lblSageata = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/image1.png")).getImage();
		lblSageata.setIcon(new ImageIcon(img));
		lblSageata.setBounds(487, 240, 90, 53);
		frmDataGenerator.getContentPane().add(lblSageata);

		DefaultListModel<String> modelTokens = new DefaultListModel<String>();
		modelTokens.addElement("CNP");
		modelTokens.addElement("Nume");
		modelTokens.addElement("Numar Intreg");
		modelTokens.addElement("Numar Intreg Pozitiv");
		modelTokens.addElement("Numar Real");
		modelTokens.addElement("Numar Real Pozitiv");
		modelTokens.addElement("Sex");
		modelTokens.addElement("Valori Bool");
		modelTokens.addElement("ID");
		modelTokens.addElement("E-mail");
		modelTokens.addElement("Caractere speciale");
		modelTokens.addElement("Set Lista");
		modelTokens.addElement("Data");

	    scrollPaneTokens = new JScrollPane();
		scrollPaneTokens.setBounds(591, 200, 150, 133);
		frmDataGenerator.getContentPane().add(scrollPaneTokens);

		JList<String> listTokens = new JList<String>();
		
		scrollPaneTokens.setViewportView(listTokens);
		listTokens.setModel(modelTokens);
		
		MyActionListener mal = new MyActionListener(textAreaInput, listTokens);
		listTokens.addListSelectionListener(mal);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image imge = new ImageIcon(this.getClass().getResource("/image3.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(imge));
		lblNewLabel_2.setBounds(10, 51, 22, 39);
		frmDataGenerator.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		Image imgr = new ImageIcon(this.getClass().getResource("/image4.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(imgr));
		lblNewLabel_3.setBounds(10, 92, 22, 23);
		frmDataGenerator.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		Image imgc = new ImageIcon(this.getClass().getResource("/image5.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(imgc));
		lblNewLabel_4.setBounds(10, 119, 28, 23);
		frmDataGenerator.getContentPane().add(lblNewLabel_4);

		btnGenereaza = new JButton("Genereaza");
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image imgt = new ImageIcon(this.getClass().getResource("/image2.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(imgt));
		lblNewLabel_1.setBounds(297, 383, 46, 32);
		frmDataGenerator.getContentPane().add(lblNewLabel_1);

		btnGenereaza.setBounds(335, 371, 187, 40);
		btnGenereaza.setBackground(Color.red);
		btnGenereaza.setForeground(new Color(5, 11, 54));
		btnGenereaza.setFont(new Font("Cooper Black", Font.PLAIN, 16));
		btnGenereaza.setOpaque(false);
		btnGenereaza.setBounds(283, 377, 201, 45);
		frmDataGenerator.getContentPane().add(btnGenereaza);
		btnGenereaza.addActionListener(new MyActionListener(btnGenereaza));
		
		textAreaGeneral = new TextArea();
		textAreaGeneral.setBounds(341, 56, 400, 82);
		frmDataGenerator.getContentPane().add(textAreaGeneral);
		
		txtpnGeneral = new JTextPane();
		txtpnGeneral.setText("General:");
		txtpnGeneral.setForeground(Color.BLACK);
		txtpnGeneral.setFont(new Font("Courier New", Font.BOLD, 15));
		txtpnGeneral.setEditable(false);
		txtpnGeneral.setBackground((Color) null);
		txtpnGeneral.setBounds(341, 32, 102, 23);
		frmDataGenerator.getContentPane().add(txtpnGeneral);

	}

	public static String getComboBoxTipExport() {

		final String tipExport = comboBoxTipFisier.getSelectedItem().toString();
		return tipExport;
	}
	
//	public static String getComboBoxFisierContinut() {
//		
//		final String fisierContinut = comboBoxFisierContinut.getSelectedItem().toString();
//		return fisierContinut;
//	}
	
	public static String getTextAreaGeneral() {
		
		final String general = textAreaGeneral.getText();
		return general;
	}
	
	public static String getTextAreaInput() {
		LOG.debug("Am intrat in getTextAreaInput method..");
	    final String input = textAreaInput.getText();
//	    final String input = "INSERT INTO valoare (val_bool) VALUES ('TK_Valori Bool');";
	    LOG.debug("Returnez textul");
		return input;
	}
	
	public static int getTextFieldNrRepetari() {
		final String nrRepetari = textFieldNrRepetari.getText();
		final int textFieldNrRepetari = Integer.valueOf(nrRepetari);
		return textFieldNrRepetari;
	}
}
