/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depozit;

/**
 *
 * @author boral
 */
public class Depozit {

	Lista produse;	
	int total;
	
	Depozit()
	{
		produse=new Lista();
	}
	
	/**
	* adauga un produs in depozit si returneaza un mesaj
	* @param nume,stoc,pret;
	* @pre stoc>0 && pret>0 ;
	* @post produse.nrelem=produse.nrelem@pre + 1
	*/
	
	public String adaugaProdus(String nume,int stoc,int pret) 
	{ 			
		assert stoc>0 && pret>0 : "Parametrii gresiti" ;
		if (!produse.exista(nume))
							{
							produse.adaugaProdusNou(new Produs(nume,stoc,pret));
							return "Produsul " + nume + " adaugat";
							}
		else
			{
			produse.adaugaLaStoc(nume,stoc);		
			return "Produsul " + nume + " deja exista. Stocul curent creste cu valoarea precizata";
			}	
	}
	
	/**
	* adauga produse in stoc si returneaza un mesaj
	* @param nume,stoc
	* @pre nume!=null && stoc>0 ;
	* @invariant produse.nrelem==produse.nrelem@pre
	* @post produse.nrelem=produse.nrelem@pre + 1
	*/
	
	public String adaugaInStoc(String nume,int stoc) { 		
		
		assert stoc>0 : "Parametru stoc negativ";
		
		if (produse.exista(nume))
							{
							produse.adaugaLaStoc(nume,stoc);
							return "Stocul produsului " + nume + " updatat";
							}
		else
			{				
			return "Produsul " + nume + " nu exista in depozit";
			}	
	}	
	
	/**
	* scoate un produs din depozit si returneaza un mesaj
	* @param nume
	* @pre nume!=null ;
	* @post (produse.exista ) ? produse.nrelem=produse.nrelem@pre - 1
	*/
	
	public String scoateProdus(String nume) 
	{
		
		if (produse.exista(nume))
				{
				produse.scoateProdus(nume);			
				return "Produsul " + nume + " a fost scos din depozit";
				}
		else return "Produsul " + nume + " nu exista in depozit";
	}
	
	/**
	* livreaza produse si returneaza un mesaj
	* @param nume,numar
	* @pre produse.nrelem >0
	* @pre nume!=null && numar>0
	* @post produse.nrelem==produse.nrelem@pre
	*/
	
	public String livreazaProdus(String nume,int numar) {
		
		assert numar>0 : "Parametru numar negativ";
		if (produse.exista(nume)==true)
				if (produse.livreazaProdus(nume,numar))				
					return "Au fost livrate "+numar+" produse " + nume + " cu succes";
				else
					return "Prea putine produse in stoc pentru a face livrarea";
				
		else return "Produsul " + nume + " nu exista in depozit";
	}
	
	/**
	* verifica daca e suprastoc pentru un anumit produs
	* @param nume
	* @pre nume!=null 
	* @post @nochange
	*/
	
	public String suprastoc(String nume)
	{
		if (!produse.exista(nume))			
				return "Produsul " + nume + " nu exista in depozit";
		else
			if (produse.depasire(nume)==true)
				return "Suprastoc de produs " + nume;
			else
				return "Nu exista suprastocuri";
		
	}
	
	/**
	* verifica daca un anumit sunt prea putine produse de un anumit tip
	* @param nume
	* @pre nume!=null 
	* @post @nochange
	*/
	
	public String substoc(String nume)
	{
		if (produse.exista(nume)==false)			
				return "Produsul " + nume + " nu exista in depozit";
		else
			if (produse.prea_putine(nume)==true)
				return "Substoc pentru produsul " + nume;
			else
				return "Nu exista substocuri";
	}
	
	/**
	* verifica daca depozitul are un numar echilibrat de produse
	* adica nu mai putine de 5 si mai multe de 10 de fiecare tip
	* @pre nume!=null 
	* @post @nochange
	*/
	
	public String echilibru()
	{
		if (produse.nrelem<5)
				return "Prea putine produse ";
		else
			if (produse.nrelem>10)
				return "Prea multe produse";
			else
				return "Depozitul are un numar echilibrat de produse";
	}
	
	/**
	* calculeaza valoarea totala a produselor din depozit
	* @pre produse.prim!=null;
	* @post @result=(total>100) ? "Valoare totala depaseste 1000 RON" : "Valoare totala e sub 1000 RON"
	*/
	public String valTotala()
	{
		total=produse.pretTotal();
		return "Valoare totala "+total+" ";

	}
	
	/**
	* verifica daca depozitul contine seficiente 
	* produse de un anumit tip
	* @param nume
	* @pre nume!=null;
	* @post @nochange
	*/	
	
	public String elemSuf(String nume)
	{
		if (produse.verif_stoc(nume))
				return "Se termina produsul " + nume  ;
		else
				return "Exista suficiente produse " + nume;
	}

}