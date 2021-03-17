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

public class Produs {
	
	String nume;
	int stoc;
	int pret;
	
	Produs(String nume,int stoc, int pret)
	{
		this.nume=nume;
		this.stoc=stoc;
		this.pret=pret;
	}
	
	/**
	* adauga o cantitate de produse de un anumit tip	
	* @invariant pret
	* @pre cantitate>0
	* @post stoc>stoc@pre
	* @param cantitate 
	*/
	public void adauga(int cantitate)
	{
		assert cantitate>0 : "Cantitate negativa ";
		if (cantitate>0)
			stoc+=cantitate;	
	}
	
	/**
	* elimina o cantitate de produse de un anumit tip
	* @pre cantitate>0
	* @post stoc<stoc@pre
	* @param cantitate
	* @invariant unitPrice
	*/	
	public boolean scoate(int cantitate)
	{	
		assert cantitate>0 : "Cantitate negativa";				
		
		if (stoc<cantitate)
					return false;
		if (cantitate>0)
						stoc-=cantitate;				
		return (true);		
	}
	
	/**
	* caracterizeaza un produs
	* @post : nochange
	* @invariant stoc,pret
	*/	
	public String toString()
	{
		return "Produsul " + nume + ": "+stoc+" bucati la pretul " + pret+" RON/bucata";
	}
	
	
	
}
