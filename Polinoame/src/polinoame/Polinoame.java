/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinoame;

	import javax.swing.JTextField;//pentru TextField-uri
	import javax.swing.JFrame;	  //pentru Fereastra principala			
	import javax.swing.JButton;	  //butoane 		
	import java.awt.Color;		  //pentru background-uri	
        import java.awt.event.*;	  //pentru ActioListener	
	import java.util.*;			  //pentru citire si scriere in consola	
	import javax.swing.JPanel;	  //pentru panoul pus pe fereastra 
	
/**
 *
 * @author boral
 */
public class Polinoame {

   
//Se initialieaza coeficientii celor 2 polinoame 
	public static final int c1[]={0,0,0,0,0,0,0};
	public static final int c2[]={0,0,0,0,0,0,0};
	
//se declara constantele folosite	
	public static final Operatie op=new Operatie();
	public static final Polinom P=new Polinom(c1);
	public static final Polinom Q=new Polinom(c2);
	public static final JTextField valx1 = new JTextField(3);

///////////////////////////////////////////////////////////////////////////	

	//metoda main	
	public static void main (String[] args)
		{
//se citesc din consola pe rand gradele si coeficientii celor 2 polinoame
//si se creeaza vectorii c1 si c2 care contin coeficientii lor
		String gradpol1,gradpol2,elem;//gradele polinoamelor si elementele citite
		Scanner in = new Scanner(System.in);
		System.out.println("INTRODUCETI GRADUL PRIMULUI POLINOM(<=6)");
		gradpol1 = in.nextLine();//se retine caracterul introdus de la tastatura
		int g1 = Integer.parseInt(gradpol1);//se converteste la intreg si e recunoscut
											//ca gradul primului polinom
		for (int i=0;i<=g1;i++){ 
			System.out.println("dati coeficientul termenului de grad "+i);
			elem = in.nextLine();//se retine ca string coeficientul introdus
			c1[i]= Integer.parseInt(elem);//se converteste si se pastreaza in 
										  //in vectorul coeficientilor pol. 1
		}
		System.out.println("INTRODUCETI GRADUL POLINOMULUI 2(<=6)");
		gradpol2 = in.nextLine();//se retine caracterul introdus de la tastatura
		int g2 = Integer.parseInt(gradpol2);//se converteste la intreg si e recunoscut
											//ca gradul primului polinom
		for (int i=0;i<=g2;i++){ 
			System.out.println("dati coeficientul termenului de grad "+i);
			elem = in.nextLine();//se retine ca string coeficientul introdus
			c2[i]= Integer.parseInt(elem);//se converteste si se pastreaza in 
										  //in vectorul coeficientilor pol. 2
		}
		
		 //se creaza frame-ul	
		 JFrame frame=new JFrame("Operatii cu polinoame- Tema 1");
		 frame.setSize(300,170);
		 frame.setResizable(false);
		 frame.setVisible(true);
		 frame.setLocation(300,250);
		 
		 //se creeaza panel-ul in care vor fi puse butoanele
		 JPanel f = new JPanel();
		 f.setBackground(Color.BLACK);
		 
		 //se creaza butoanele
		 JButton afispol1=new JButton("Afisati pol. 1");
		 JButton afispol2=new JButton("Afisati pol. 2");
		 JButton adunare=new JButton("Adunare");
		 JButton scadere=new JButton("Scadere");
		 JButton inmultire=new JButton("Inmultire");
		 JButton derivare=new JButton("Derivare pol. 1");
		 JButton radac=new JButton("Radacini pol. 1");
		 JButton valoare1=new JButton("Valoare pol.1 in X= ");	
		 
		 //un textfield in care va fi introdusa o valoare 
	
		 
		
///////////////////////////////////////////////////////////////////////////

		 //se adauga butoanele, se pozitioneaza fiecare si li se dau culori 		 
		 f.add(afispol1);
		 afispol1.setBackground(Color.RED);
		 
		 f.add(afispol2);
		 afispol2.setBackground(Color.RED);
		 
		 f.add(adunare);
		 adunare.setBackground(Color.GREEN);
		
		 f.add(scadere);
		 scadere.setBackground(Color.GREEN);
		 
		 f.add(inmultire);
		 inmultire.setBackground(Color.GREEN);
		 		 
		 f.add(derivare);
		 derivare.setBackground(Color.lightGray);	 
		 
		 f.add(radac);
		 radac.setBackground(Color.lightGray);
		 
		 f.add(valoare1);
		 valoare1.setBackground(Color.YELLOW);
		 
		 f.add(valx1);
		 valx1.setForeground(Color.BLUE);
		 valx1.setEditable(true);
		 
		 frame.add(f);

///////////////////////////////////////////////////////////////////////////		 
		 
		 //clasele ActionListener creeaza Listeneri pe butoane
		 class Af1BtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 P.setcoef(c1);
				 System.out.print("Primul pol. este: ");
				 P.afispol(); 			
			 }
		 }
		 ActionListener afi1 = new Af1BtnListener();
		 afispol1.addActionListener(afi1);//pentru afisare polinom 1
		 
		 class Af2BtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 Q.setcoef(c2);
				 System.out.print("Al 2-lea pol. este: ");
				 Q.afispol(); 			
			 }
		 }
		 ActionListener afi2 = new Af2BtnListener();
		 afispol2.addActionListener(afi2);//pentru afisare polinom 2
		 
		 class AdBtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 int[] pol;
				 pol=op.adunare(P,Q);
				 Polinom A=new Polinom(pol);
				 A.setcoef(pol);
				 System.out.print("Suma este: ");
				 A.afispol(); 
					
			 }
		 }
		 ActionListener adun = new AdBtnListener();
		 adunare.addActionListener(adun);//pentru adunare polinoame
		 
		 class ScBtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 int[] pol;
				 pol=op.scadere(P,Q);
				 Polinom S=new Polinom(pol);
				 S.setcoef(pol);
				 System.out.print("Diferenta este: ");
				 S.afispol();
			 }
		 }
		 ActionListener scad = new ScBtnListener();
		 scadere.addActionListener(scad);//pentru scadere polinoame
		 
		 class DeBtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 int[] pol;
				 pol=op.derivare(P);
				 Polinom D=new Polinom(pol);
				 D.setcoef(pol);
				 System.out.print("Derivatul pol. 1 este: ");
				 D.afispol();
			 }
		 }
		 ActionListener deri = new DeBtnListener();
		 derivare.addActionListener(deri);//pentru derivare polinom 1
		 
		 class InBtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 int[] pol;
				 pol=op.inmultire(P,Q);
				 Polinom I=new Polinom(pol);
				 I.setcoef(pol);
				 System.out.print("Produsul polinoamelor este: ");
				 I.afispol();
			 }
		 }
		 ActionListener inmu = new InBtnListener();
		 inmultire.addActionListener(inmu);//pentru inmultire polinoame
		 
		 class RaBtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 int[] pol;
				 int val=0;
				 pol=op.radacini(P);
				 System.out.print("Radacinile intregi sunt: ");
				 int t=0;
				 int radacini[]=new int[pol.length];
				 for (int i=0;i<=pol.length-1;i++)
					 {
					 val=op.valoarepol(P,pol[i]);
					 if (val==0) {radacini[t]=pol[i];
					 			  t++;
								 }
					 val=op.valoarepol(P,-pol[i]);
					 if (val==0) {radacini[t]=-pol[i];
					 			  t++;
					 			 } 
					 }
				 System.out.println();
				 for (int i=0;i<=t;i++)
					 if ((i%2!=0)&&(radacini[i]!=0)) System.out.println((i+1)/2+ ". "+radacini[i]);  
			 }
		 }
		 ActionListener rada = new RaBtnListener();
		 radac.addActionListener(rada);//pentru radacini polinom 1
		 
		 class VaBtnListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 int[] pol;
				 String text;
				 int valox=0;
				 int val=0;
				 text=valx1.getText();
				 valox = Integer.parseInt(text);
				 val=op.valoarepol(P,valox);
				 System.out.println("Valoarea pol.1 in "+ valox+ " este "+val+" ");
				 String valafis = Integer.toString(val);
			 }
		 }
		 ActionListener valo = new VaBtnListener();
		 valoare1.addActionListener(valo);//pentru val. pol. 1 intr-un punct x
		 
		 
    }//terminare main
}//terminare clasa executie