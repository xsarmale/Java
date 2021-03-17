/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionar;

/**
 *
 * @author boral
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollBar;

public class Main {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="12,-12"
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JTextArea jTextArea = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton = null;
	private Load load;
	private MyMap myMap;
	private JButton jButton3 = null;
	private JTextArea jTextArea1 = null;
	private JScrollBar jScrollBar = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	public Main()
	{
		load=new Load();
		myMap=new MyMap();
		load.myMap=myMap;
	}
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(673, 534));
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setLocation(new Point(200, 200));
			jFrame.setBackground(new Color(255, 255, 102));
			jFrame.setFont(new Font("Arial", Font.PLAIN, 12));
			jFrame.setContentPane(getJPanel());
			jFrame.setTitle("DICTIONARY");
			jFrame.setVisible(true);						
		}
		return jFrame;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(40, 68, 105, 29));
			jLabel.setText("  Enter your word");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBackground(new Color(0, 255, 51));
			jPanel.setPreferredSize(new Dimension(100, 100));
			jPanel.add(jLabel, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJTextArea(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(getJButton2(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton3(), null);
			jPanel.add(getJTextArea1(), null);
			jPanel.add(getJScrollBar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(172, 63, 240, 31));
			jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {					
					if(e.getKeyChar()=='\n')  //or e.getKeyCode()==10
					{
						jTextArea.setText("");
						String s=jTextField.getText();
						boolean ok=validate(s);
						if(!ok) jTextArea.setText("Sorry. No records for '"+s+"'.");
						if(s.length()>0 && ok)
						{
							String text;
							text=(String)load.myMap.searchMatchesInMap(s);
							jTextArea.setEditable(true);
							if(text.compareTo("")!=0)					
								jTextArea.setText(text);
							else 
								jTextArea.setText("Sorry. No records for '"+s+"'.");
							jTextArea.setEditable(false);
							jTextField.setText("");
						}
					}
				}
			});
			
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(227, 123, 395, 345));
			jTextArea.setEditable(false);
		}
		return jTextArea;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(38, 176, 124, 30));
			jButton1.setText("Add");
			jButton1.setBackground(new Color(0, 204, 0));
			jButton1.setVisible(false);
			jButton1.setEnabled(false);
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String s=jTextField.getText();
					if(!validate1(s)) jTextArea.setText("Invalide word");
					if(s.length()>0 && validate1(s))
					{
						if(jTextArea.getText().length()>0)
						{
							load.myMap.put(s,s+" ="+jTextArea.getText());
							//Note. we put in the file whith space followed by "="
							load.writeInFile("Dictionary.txt",s+" ="+jTextArea.getText());
							jTextArea.setText("");
						}
						else jTextArea.setText("Invalide explanations");
					}
					else 
						jTextArea.setText("Invalide word");
					jTextField.setText("");
					//jTextArea.setText("");
					jTextArea.setEditable(false);
					jButton.setEnabled(true);
					jButton2.setEnabled(true);
					jButton1.setEnabled(false);
					jButton1.setVisible(false);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(36, 268, 128, 29));
			jButton2.setBackground(new Color(0, 204, 0));
			jButton2.setText("Remove");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextArea.setText("");
					String s=jTextField.getText();
					boolean ok=validate(s);
					if(!ok) jTextArea.setText("Sorry. No records for '"+s+"'.");
					if(s.length()>0 && ok)
					{						

						if(load.myMap.remove(s)!=null)
						{
							load.writeLoadInFile("Dictionary.txt");
							jTextArea.setText("Remove succesfully");
						}
						else 
							jTextArea.setText("Sorry. No records for '"+s+"'.");						
						jTextField.setText("");
					}
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(37, 122, 124, 32));
			jButton.setBackground(new Color(0, 204, 51));
			jButton.setText("Search");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e)
				{										
					jTextArea.setText("");
					String s=jTextField.getText();
					boolean ok=validate(s);
					if(!ok) jTextArea.setText("Sorry. No records for '"+s+"'.");
					if(s.length()>0 && ok)
					{
						String text;
						text=(String)load.myMap.searchMatchesInMap(s);
						jTextArea.setEditable(true);
						if(text.compareTo("")!=0)					
							jTextArea.setText(text);
						else 
							jTextArea.setText("Sorry. No records for '"+s+"'.");
						jTextArea.setEditable(false);
						jTextField.setText("");
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(38, 224, 124, 28));
			jButton3.setBackground(new Color(0, 204, 51));
			jButton3.setText("New word");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jButton1.setEnabled(true);
					jButton1.setVisible(true);
					jTextArea.setText("");
					jTextArea.setEditable(true);
					jButton.setEnabled(false);
					jButton2.setEnabled(false);
				}
			});
		}
		return jButton3;
	}
	//my methods
	
	//after * any char is valide
	private boolean validate(String s)
	{
		int i=0;
		char[] s1=s.toLowerCase().toCharArray();
		while(i<s1.length)
		{
			if(s1[i]=='*' || s1[i]=='?')
				break;
			if(s1[i]<'a' || s1[i]>'z') return false;
			i++;
		}
		return true;
	}
	
	private boolean validate1(String s)
	{
		int i=0;
		char[] s1=s.toLowerCase().toCharArray();
		while(i<s1.length)
		{			
			if(s1[i]<'a' || s1[i]>'z') return false;
			i++;
		}
		return true;
	}
	
	/**
	 * This method initializes jTextArea1	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setBounds(new Rectangle(21, 331, 185, 94));
			jTextArea1.setText("You can use '*' or '?'.\n     If you type 'ma*' ," +
					" will appear\n all the words wich start with 'ma'." +
					"\n     If you type 'm?' ," +
					" will appear\n all the words with two letters\nwich start with 'm'.");
			jTextArea1.setBackground(Color.green);
			jTextArea1.setEditable(false);
		}
		return jTextArea1;
	}
	/**
	 * This method initializes jScrollBar	
	 * 	
	 * @return javax.swing.JScrollBar	
	 */
	private JScrollBar getJScrollBar() {
		if (jScrollBar == null) {
			jScrollBar = new JScrollBar();
			jScrollBar.setBounds(new Rectangle(591, 127, 17, 333));
		}
		return jScrollBar;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();	
		main.load.readLoadUsingScanner("Dictionary.txt");
		main.jFrame=main.getJFrame();
	}

}
