/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinoame;

//clasa Polinom
public class Polinom 
{
	private int coef[];  		//coeficientii polinomului
	private int grad;    		//gradul polinomului

	//constructor
public Polinom(int vect[])
{
	grad=vect.length-1;  		//gradul e cu 1 mai mic decat 
								//numarul de coeficienti
	
	coef=new int[grad+1];		//sunt cu 1 mai multi coef. decat gradul

	for (int i=0; i<=grad; i++)	//se construiesc coeficientii pol.
		{						//pe baza vectoruli primit ca parametru	
		 coef[i]=vect[i];		
		}
}



//afisare polinom
public void afispol()			//afiseaza polinomul
{
if (coef[0]!=0) System.out.print(coef[0]);	//se afiseaza termenul liber
if (grad==1) 
	if (coef[grad]>0) System.out.print("+"+coef[grad]+"*X^" + grad); 
			else System.out.print(coef[grad]+"*X^" + grad); 
if (grad>=2) { 
for (int i=1;i<grad;i++)
{
if (coef[i]==0) continue; //termenii cu coeficient 0 nu se afiseaza
if (i+1==grad) { 
if (coef[i]>0) System.out.print("+"+coef[i]+"*X^"+i);
if (coef[i]<0) System.out.print(coef[i]+"*X^"+i); 
			    }
if ((i+1!=grad)&&(coef[i]==1)) System.out.print("+"+"X^"+i);
if ((i+1!=grad)&&(coef[i]==-1)) System.out.print("-"+"X^"+i);

if ((coef[i]!=1)&&(coef[i]!=-1)&&(i+1!=grad))
if (coef[i]>1) System.out.print("+"+coef[i]+"*X^"+i);
if (coef[i]<-1) System.out.print(coef[i]+"*X^"+i);
}
}
if ((grad>=2)&&(coef[grad]!=0))
{
	if (coef[grad]>0) System.out.print("+"+coef[grad]+"*X^"+grad);
	if (coef[grad]<0) System.out.print(coef[grad]+"*X^"+grad);
}
System.out.println();
}

public void setgrad(int g)  	//fixeaza gradul unui polinom 
	{
	 grad=g;
	}

public int getgrad()			//preia gradul unui polinom
	{
	 return grad;
	}

public void setcoef(int p[])	//fixeaza coeficientii polinomului
	{
	 for (int i=0;i<=grad;i++)
		 coef[i]=p[i];
	}

public int[] getcoef()			//preia toti coef. unui polinom
	{
	 return coef;
	}

}
