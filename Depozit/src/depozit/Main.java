/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depozit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.*;         

public class Main extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static Depozit dep=new Depozit();
    private static JFrame f=new JFrame("Tema 4 - Depozit");
	
    private static JButton adaugare=new JButton("Adauga produs nou");
    private static JButton crestestoc=new JButton("Mareste stoc");
    private static JButton scoateprodus=new JButton("Elimina produs");
    private static JButton livreaza=new JButton("Livreaza produs");
    private static JButton vezi=new JButton("Vezi situatie depozit");
    private static JButton filtre=new JButton("Filtre");
    private static JButton inchide=new JButton("Inchide");  
	public static JTextArea mesaje=new JTextArea();
	private static int var1=0,var2=0;

/** Constructorul clasei Main
*/
public Main()
{
setLayout(null);
adaugare.setBackground(Color.GREEN);
adaugare.setBounds(5,5,170,30);
add(adaugare);

crestestoc.setBackground(Color.GREEN);
crestestoc.setBounds(5,40,170,30);
add(crestestoc);

scoateprodus.setBackground(Color.GREEN);
scoateprodus.setBounds(5,75,170,30);
add(scoateprodus);

livreaza.setBackground(Color.GREEN);
livreaza.setBounds(5,110,170,30);
add(livreaza);

vezi.setBackground(Color.GREEN);
vezi.setBounds(5,145,170,30);
add(vezi);

filtre.setBackground(Color.GREEN);
filtre.setBounds(5,180,170,30);
add(filtre);

inchide.setBackground(Color.RED);
inchide.setBounds(5,235,170,30);
add(inchide);

mesaje.setBackground(Color.BLACK);
mesaje.setForeground(Color.RED);
mesaje.setBounds(180,5,500,270);
mesaje.setVisible(true);
add(mesaje);
}	

/** metoda principala a clasei Main 
* @param args
*/    
public static void main(String[] args) {
        //se gestioneaza frame-ul        
        f.getContentPane().add(new Main());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(695, 313);
		f.setBackground(Color.darkGray);
		f.setForeground(Color.BLUE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);     
  
/** Clasa AdaugareListener se ocupa cu raspunsul la apasarea butonului
* de adaugare a unui nou produs in depozit 
*/       
class AdaugareListener implements ActionListener         
{   
    JPanel pan2=new JPanel();
    JLabel lab1=new JLabel("Nume       :");          
    JLabel lab2=new JLabel("Stoc :");               
    JLabel lab3=new JLabel("Pret/bucata :");
    JTextField tfnume=new JTextField(35);       
    JTextField tfstoc=new JTextField(10); 
    JTextField tfpret=new JTextField(10);
    JButton but=new JButton("Adauga");
    JFrame int_frame=new JFrame("Dati info: ");         
                                               
    String s[]=new String[3];                       
    class AButtonListener implements ActionListener 
    {   
        public void actionPerformed(ActionEvent event)
        {
            s[0]=tfnume.getText();      
            s[1]=tfstoc.getText();
            s[2]=tfpret.getText();
            int x=Integer.parseInt(s[1]);//stocul
            int y=Integer.parseInt(s[2]);//pretul  
			if (var1==0)
				try{
				mesaje.setText(dep.adaugaProdus(s[0],x,y));
				}
			catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
            int_frame.dispose(); 
            var1=1;
        }        
      }
	
      public void actionPerformed(ActionEvent event)
            {                          
            but.setBackground(Color.green);
            but.setSize(40,40);
            ActionListener listener1 = new AButtonListener();
            but.addActionListener(listener1);                      
            int_frame.setSize(500,100);
            int_frame.setLocationRelativeTo(null);
            pan2.add(lab1);
            pan2.add(tfnume);   
            pan2.add(lab2);             
            pan2.add(tfstoc);    
            pan2.add(lab3);
            pan2.add(tfpret);
            pan2.add(but);
            int_frame.add(pan2);                           
            int_frame.setVisible(true);    
            var1=0;
            }
}
ActionListener listener2 = new AdaugareListener();
adaugare.addActionListener(listener2);

/** Clasa Adaugare2Listener se ocupa cu raspunsul la apasarea
* butonului de adaugare in stoc de elemente dintr-un produs existent in depozit
*/

class Adaugare2Listener implements ActionListener         
{   
    JPanel pan2=new JPanel();
    JLabel lab1=new JLabel("Nume       :");          
    JLabel lab2=new JLabel("Stoc :");               
    JTextField tfnume=new JTextField(35);       
    JTextField tfstoc=new JTextField(10); 
    JButton but=new JButton("Adauga");
    JFrame int_frame=new JFrame("Dati info: ");         
                                               
    String s[]=new String[2];                       
    class A2ButtonListener implements ActionListener 
    {   
        public void actionPerformed(ActionEvent event)
        {
            s[0]=tfnume.getText();      
            s[1]=tfstoc.getText();
            int x=Integer.parseInt(s[1]);//stocul
            if (var2==0)
			try{	
				mesaje.setText(dep.adaugaInStoc(s[0],x));
			}
			catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
            int_frame.dispose(); 
            var2=1;
        }        
      }
	
      public void actionPerformed(ActionEvent event)
            {                          
            but.setBackground(Color.green);
            but.setSize(40,40);
            ActionListener listener3 = new A2ButtonListener();
            but.addActionListener(listener3);                      
            int_frame.setSize(500,100);
            int_frame.setLocationRelativeTo(null);
            pan2.add(lab1);
            pan2.add(tfnume);   
            pan2.add(lab2);             
            pan2.add(tfstoc);    
            pan2.add(but);
            int_frame.add(pan2);                           
            int_frame.setVisible(true);    
            var2=0;
            }
}
ActionListener listener4 = new Adaugare2Listener();
crestestoc.addActionListener(listener4);

/** Clasa ScoateProdusListener se ocupa cu raspunsul la apasarea
* butonului de eliminare a unui produs din depozit	
*/

class ScoateProdusListener implements ActionListener 		
{	 
    JTextField tf1=new JTextField(15);				
	JLabel l1=new JLabel("Nume   :");	
	JFrame scoatereFrame=new JFrame("Elimina produs");			
    public void actionPerformed(ActionEvent event)			
        {
	 	tf1.setText("");
		JPanel scoaterePanel=new JPanel();
		JButton scoatereButon=new JButton("Elimina");
		scoatereButon.setBackground(Color.red);							
		scoaterePanel.add(l1);
		scoaterePanel.add(tf1);				
		class DButtonListener implements ActionListener 		
			{	 
			 public void actionPerformed(ActionEvent event)			
				  {
					String s=tf1.getText();	
					try{
					JOptionPane.showMessageDialog(null,dep.scoateProdus(s));
					}
					catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
					scoatereFrame.dispose();
				  }
			}	
				ActionListener listener5= new DButtonListener();				
				scoatereButon.addActionListener(listener5);
				scoaterePanel.add(scoatereButon);
				scoatereFrame.add(scoaterePanel);
				scoatereFrame.setSize(300,200);
				scoatereFrame.setVisible(true);
				scoatereFrame.setLocationRelativeTo(null);						
		        }
	     }
ActionListener listener6=new ScoateProdusListener();
scoateprodus.addActionListener(listener6);

/** Clasa LivreazaListener se ocupa cu raspusul la apasarea
* pe butonul de livrare a unui produs		 
*/

class LivreazaListener implements ActionListener 		
{	 
	JTextField tf1=new JTextField(15);
	JTextField tf2=new JTextField(14);		
	JLabel l1=new JLabel("Nume    :");
	JLabel l2=new JLabel("Cantitate livrata   :");			
	JFrame livrareFrame;
			
    public void actionPerformed(ActionEvent event)			
        {
	 	livrareFrame=new JFrame("Frame Livrare");
		tf1.setText("");
		tf2.setText("");				
		JButton livrareButon=new JButton("Livreaza ");				
		livrareButon.setBackground(Color.green);				
		JPanel livrarePanel=new JPanel();				
		livrarePanel.add(l1);
		livrarePanel.add(tf1);
		livrarePanel.add(l2);
		livrarePanel.add(tf2);
				
		class DDButtonListener implements ActionListener 		
	    {	 
		public void actionPerformed(ActionEvent event)			
		    {
			String s=tf1.getText();						
			int i=Integer.parseInt(tf2.getText());
			try{
			JOptionPane.showMessageDialog(null,dep.livreazaProdus(s,i));	
			}
			catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			livrareFrame.dispose();
		    }
		}			     
				
		ActionListener listener7= new DDButtonListener();				
		livrareButon.addActionListener(listener7);
		livrarePanel.add(livrareButon);
		livrareFrame.add(livrarePanel);
		livrareFrame.setSize(300,200);
		livrareFrame.setVisible(true);
		livrareFrame.setLocationRelativeTo(null);	
										
		}
}
ActionListener listener8 = new LivreazaListener();  
livreaza.addActionListener(listener8);

/**Clasa VeziSituatieListener se ocupa cu raspunsul la apasarea 
* butonului de vizualizare a situatiei depozitului
*/

class VeziSituatiaListener implements ActionListener     
{    
    public void actionPerformed(ActionEvent event)
    {
		mesaje.setText("Situatia depozitului este urmatoarea: \n");
        dep.produse.afisare();
    }
}
ActionListener listener9 = new VeziSituatiaListener(); 
vezi.addActionListener(listener9);

/** Clasa FiltreListener se ocupa cu raspunsul la apasarea 		
* butonului de utilizare a filtrelor
*/
		
class FiltreListener implements ActionListener 		
{	 
JFrame filtreFrame;
JPanel filtrePanel;
JLabel l=new JLabel("Produsul inspectat           :");
JTextField tf=new JTextField(5);			

	public void actionPerformed(ActionEvent event)			
	{		 
		filtreFrame=new JFrame("Frame filtre");
		filtrePanel=new JPanel();
		
		JButton b1=new JButton("Substoc");
		JButton b2=new JButton("Suprastoc");
		JButton b3=new JButton("Produse suficiente");
		JButton b4=new JButton("Valoare totala");
		JButton b5=new JButton("Elemente suficente");
		JButton b6=new JButton("Cel mai valoros");
		JButton b7=new JButton("Cel mai putin valoros");
		JButton b8=new JButton("Paraseste filtrele");
				
		tf.setText("");	
			 
		class SubButtonListener implements ActionListener 		
		{	 
		     public void actionPerformed(ActionEvent event)		
			 {			
				 try{
			  JOptionPane.showMessageDialog(null,dep.substoc(tf.getText()));	
				 }
				 catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			 }				 
		}	
		ActionListener list1= new SubButtonListener();
		b1.addActionListener(list1);
		filtrePanel.add(b1);
		
		class SupraButtonListener implements ActionListener 		
		{	 
			 public void actionPerformed(ActionEvent event)			
			 {
				 try{
			  JOptionPane.showMessageDialog(null,dep.suprastoc(tf.getText()));	
				 }
				 catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			 }
		}	
		ActionListener list2= new SupraButtonListener();
		b2.addActionListener(list2);
		filtrePanel.add(b2);
		
		class ProdSufButtonListener implements ActionListener 		
		{	 
			public void actionPerformed(ActionEvent event)			
			{
				try{
			JOptionPane.showMessageDialog(null,dep.echilibru());	
				}
				catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			}
		}	
		ActionListener list3= new ProdSufButtonListener();
		b3.addActionListener(list3);
		filtrePanel.add(b3);
		
		class ValSufButtonListener implements ActionListener 		
		{	 
			public void actionPerformed(ActionEvent event)			
			{
				try{
			JOptionPane.showMessageDialog(null,dep.valTotala());	
				}
				catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			}
		}	
		ActionListener list4= new ValSufButtonListener();
		b4.addActionListener(list4);
		filtrePanel.add(b4);
		
		class ElemSufButtonListener implements ActionListener 		
		{	 
			public void actionPerformed(ActionEvent event)			
			{	
			if (tf.getText()!="")
				try{
			JOptionPane.showMessageDialog(null,dep.elemSuf(tf.getText()));
				}
			catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			}
		}	
		ActionListener list5= new ElemSufButtonListener();
		b5.addActionListener(list5);
		filtrePanel.add(b5);
		
		class CelMaiValButtonListener implements ActionListener 		
		{	 
			public void actionPerformed(ActionEvent event)			
			{
			if (dep.produse.nrelem>0)
				try{
			JOptionPane.showMessageDialog(null,dep.produse.celMaiScump());
				}
			catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			}
		}	
		ActionListener list6= new CelMaiValButtonListener();
		b6.addActionListener(list6);
		filtrePanel.add(b6);
		
		class CelMaiIefButtonListener implements ActionListener 		
		{	 
			 public void actionPerformed(ActionEvent event)			
			 {
			 if (dep.produse.nrelem>0)
				 try{
			 JOptionPane.showMessageDialog(null,dep.produse.celMaiIeftin());
				 }
			 catch(AssertionError e){JOptionPane.showMessageDialog(null,e);}
			 }
		}
		ActionListener list7= new CelMaiIefButtonListener();
		b7.addActionListener(list7);
		filtrePanel.add(b7);
		
		class ParasesteButtonListener implements ActionListener 		
		{	 
			 public void actionPerformed(ActionEvent event)			
			 {
			 filtreFrame.dispose();					
			 }
		}					
		ActionListener list8= new ParasesteButtonListener();
		b8.addActionListener(list8);
		b8.setBackground(Color.RED);	
filtrePanel.add(l);
filtrePanel.add(tf);
filtrePanel.add(b8);
filtrePanel.setBackground(Color.YELLOW);				
filtreFrame.add(filtrePanel);
filtreFrame.setSize(350,200);
filtreFrame.setVisible(true);
filtreFrame.setLocationRelativeTo(null);	
	}
}	
ActionListener listener11 = new FiltreListener();
filtre.addActionListener(listener11);

/**Clasa ExitListenr se ocupa cu raspunsul la apasarea butonului
* de inchidere a ferestrei 
*/

class ExitListener implements ActionListener       
{   
public void actionPerformed(ActionEvent event)         
     {
      System.exit(0);                         
     }
}     
ActionListener listener10 = new ExitListener();
inchide.addActionListener(listener10);         
    }

}
