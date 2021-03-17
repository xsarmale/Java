/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depozit;

public class Lista {

	Element prim,ultim;
	int nrelem;
	
	Lista()
	{
		prim=ultim=null;
		nrelem=0;
	}
	
	/** adauga un numar de produse la stoc
	* @param nume,st
	* @pre nume!=null;
	* @pre st>0
	* @invariant nrelem
	*/
	void adaugaLaStoc(String nume, int st)
	{
		assert (nume!=null) && (st>0) : "Parametrii gresiti";
		Element temp=prim;
		while(temp!=null)
		{
			if (temp.valoare.nume.equals(nume))
			{
				temp.valoare.adauga(st);				
			}
			temp=temp.urm;
		}
		assert (st>0);
	}
	
	/** adauga un nou produs in depozit
	* @param prod
	* @pre prod.stoc>0
	* @pre prod.pret>0
	* @post nrelem=nrelem@pre + 1
	*/
	void adaugaProdusNou(Produs prod)
	{
		assert (prod.stoc>0)&&(prod.pret>0) : "Parametrii gresiti";
		Element e=new Element(prod);
		e.urm=null;
				
		if (prim==null)	{
						 prim=e;
						}
		else if (ultim==null) {
						  prim.urm=e;
						  ultim=e;
						 }
		if (ultim!=null) {
			ultim.urm=e;
			ultim=e;
			ultim.urm=null;
		}
		nrelem++;
		}
	
	/** verifica daca un produs identificat prin nume exista in depozit
	* @param nume
	* @pre nume!=null;
	* @post @nochange
	* @invariant nrelem	
	*/
	
	public boolean exista(String nume)
	{
		assert nume!=null;
		
		Element temp=prim;
		boolean da=false;		
		while (temp!=null)
			{
			if (temp.valoare.nume.equals(nume))
							da=true;			
			temp=temp.urm;
			}		
		return da;		
	}
	
	/** returneaza valoare tuturor produselor 
	* de un anumit tip din depozit
	* @param nume
	* @pre nume!=null;	
	* @post @nochange
	* @post @result=(search(nume)==true) ? valoare : 0
	*/
	int valoareTotProdus(String nume)
	{
		assert nume!=null;
		Element temp=prim;
		int valoare=0;
		if (exista(nume)==true)		
			while ((temp!=null)&&(!temp.valoare.nume.equals(nume)))
														temp=temp.urm;			
		else
			return 0;
		valoare=valoare+temp.valoare.stoc*temp.valoare.pret;	
		return valoare;
	}
	
	/** livreaza un produs eliminandu-l din stoc
	* returneaza true daca livrarea s-a produs, 
	* adica au fost suficiente produse de tipul cerut 
	* pentru a putea efectua livrarea
	* @param nume,numar
	* @pre search(nume)==true && nume!=null
	* @post @result=(temp.valoare.remove(numar))
	*/
	boolean livreazaProdus(String nume,int numar)
	{
		assert nume!=null;
		Element temp=prim;
		while(temp!=null)
		{
			if (temp.valoare.nume.equals(nume))
			{
				return temp.valoare.scoate(numar);				
			}
			temp=temp.urm;
		}	
		return true;
	}
	
	/** scoate un produs din depozit
	* returneaza true daca produsul cu numele cerut exista in depozit
	* @param nume
	* @pre nume!=null;
	* @post search(nume) ? nrelem==nrelem@pre-1 : @nochange
	* @post @result=(search(nume))	
	*/
	boolean scoateProdus(String nume)
	{
		assert nume!=null;
		Element temp=prim;
		Element aux=null;
		boolean b;
		while (temp!=null)
		{			
			if (temp.valoare.nume.equals(nume)) 
				{
				if (temp==prim)														
					if (prim.urm!=null)
						prim=prim.urm;
					else											
						prim=ultim=null;			
				if (temp==ultim)
								{
								aux.urm=null;
								ultim=aux;	
								ultim.urm=null;
								}		
				if ((prim!=temp)&&(ultim!=temp))
				{									
					aux.urm=temp.urm;										
				}			
					
				nrelem--;
				return true;
				}
			aux=temp;
			temp=temp.urm;
		}
		return false;		
	}
	
	/** returneaza true daca exista o depasire de stoc in depozit pentru un anumit produs
	* @param nume
	* @pre prim!=null && nume!=null;
	* @post @nochange	
	* @invariant nrelem
	*/
	boolean depasire(String nume)
	{
		assert ((prim!=null)&&(nume!=null)) : "Parametrii gresiti";
		Element temp=prim;
		while(temp!=null)
				{
				if (temp.valoare.nume.equals(nume)) 
						if (50<temp.valoare.stoc) return true;
				temp=temp.urm;
				}		
		return false;
	}
	
	/** returneaza true daca sunt putine produse de un anumit tip
	* @param nume
	* @pre prim!=null && nume!=null;
	* @post @nochange	
	* @invariant nrelem
	*/
	
	boolean prea_putine(String nume)
	{
		assert ((prim!=null)&&(nume!=null)) : "Parametrii gresiti";
		Element temp=prim;
		while(temp!=null)
				{
				if (temp.valoare.nume.equals(nume)) 
								if (5>temp.valoare.stoc) return true;
				temp=temp.urm;
				}		
		return false;
	}
	
	/** returneaza cel mai scump produs din depozit
	* @pre prim!=null;
	* @post @nochange	
	*/
	
	String celMaiScump()
	{
		assert prim!=null;
		Element temp=prim;
		int max=0;
		String t="";
		while (temp!=null)
				{	
				int x=temp.valoare.stoc*temp.valoare.pret;
				if (max < x)
						{		
						max=x;
						t=temp.valoare.nume;
						}
 				temp=temp.urm;
				}
		return "Cel mai scump produs este " + t;
	}
	
	/**returneaza cel mai ieftin produs din depozit
	* @pre prim!=null;
	* @post @nochange	
	*/
	
	String celMaiIeftin()
	{
		assert prim!=null;
		Element temp=prim;
		int min=0;
		String t="";
		while (temp!=null)
				{	
				int x=temp.valoare.stoc*temp.valoare.pret;
				if (min==0)
						{
						min=x;
						t=temp.valoare.nume;
						}
				else
					if (min > x)
						{		
						min=x;
						t=temp.valoare.nume;
						}
 				temp=temp.urm;
				}
		return "Cel mai ieftin produs este " + t;
	}	
	
	/**verifica daca un produs este suficient de scump 
	* @pre nume!=null;
	* @post @nochange	
	*/
	
	boolean verif_stoc(String nume)
	{
		Element temp=prim;
		while (temp!=null)
			{			
			if (temp.valoare.nume.equals(nume))						
					if (temp.valoare.stoc*temp.valoare.pret<100)
								return true;
			temp=temp.urm;
			}
		return false;
	}
	
	/** returneaza pretul total al produselor din depozit
	* @pre prim!=null;	
	* @post @nochange
	* @invariant nrelem
	*/
	
	int pretTotal()
	{
		Element temp=prim;
		int valoare=0;
		while (temp!=null)
			{
			valoare=valoare+temp.valoare.stoc*temp.valoare.pret;
			temp=temp.urm;
			}
		return valoare;
	}
	
	/** metode de afisare a unui mesaj care sa 
	* afiseze toate produsele existente in depozit
	* cu informatii specifice pentru fiecare
	* @pre temp!=null
	* @post @nochange
	*/
	
	void afisare()
	{
		Element temp=prim;
		while(temp!=null)
				{
			    Main.mesaje.append(temp.valoare.toString()+"\n");
				temp=temp.urm;
				}		
	}
}