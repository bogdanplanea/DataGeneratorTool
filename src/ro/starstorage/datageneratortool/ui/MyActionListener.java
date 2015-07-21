package ro.starstorage.datageneratortool.ui;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MyActionListener implements ListSelectionListener, KeyListener, ActionListener {

	JList<String> listTokens;
	TextArea textArea,textAreaInput;
	JTextField textFieldNrRepetari;
	JButton btnGenereaza;
	
	public MyActionListener(JButton btnGenereaza){
		this.btnGenereaza = btnGenereaza;

	}
	
	public MyActionListener(TextArea textArea, JList<String> listTokens) {
		this.listTokens = listTokens;
		this.textArea = textArea;
		
	}
	
	public MyActionListener(TextArea textAreaInput) {
		this.textAreaInput = textAreaInput;
	}
	public MyActionListener(JTextField textFieldNrRepetari){
		this.textFieldNrRepetari = textFieldNrRepetari;
	}
	
	
		/*public void mousePressed(MouseEvent me) {
			textArea.append("TK_" + (String) listTokens.getSelectedValue());
		}*/

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {

			textArea.append("TK_" + (String) listTokens.getSelectedValue());
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
        if (!(Character.isDigit(c) ||
           (c == KeyEvent.VK_BACK_SPACE) ||
           (c == KeyEvent.VK_DELETE))) {
             e.consume();
           }
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
        try {
	        GenerateFile.generateFile();
        } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		

		
	}

	
}


