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

public class Element {

	Produs valoare;
	Element urm;
	
	/** Constructorul clasei
	 * @param valoare
	 * @post urm==null;
	 */
	 
	Element(Produs valoare)
	{
		this.valoare=valoare;
		urm=null;
	}
	
	
}