/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinoame;

//clasa Operatie va contine toate operatiile pe polinoame
public class Operatie 
{
	
//metoda adunare va returna coeficentii sumei a doua polinoame
public int[] adunare(Polinom P, Polinom Q)
{
int p=P.getgrad();						//in p va fi gradul polinomului P
int q=Q.getgrad();						//in q va fi gradul polinomului Q
int coefp[]=P.getcoef();				//vectorul coefp va retine coeficientii lui P
int coefq[]=Q.getcoef();				//vectorul coefq va retine coeficientii lui Q

if (p>=q) 								//daca P are grad mai mare
	{
	 int[] rezultat=new int[p+1];	
	 for (int i=0;i<=q;i++)				
		rezultat[i]=coefp[i]+coefq[i];  //se aduna coeficientii gradelor egale
	 for (int i=q+1;i<=p;i++)			
		rezultat[i]=coefp[i];			//in rest se retin coef. lui P 
	 return rezultat;
	}
else 									//daca Q are grad mai mare
	{									//se procedeaza la fel, doar ca in rest 
	int[] rezultat=new int[q+1];	 
	for (int i=0;i<=p;i++)				//se vor retine coef. lui Q
		rezultat[i]=coefq[i]+coefp[i];
	 for (int i=p+1;i<=q;i++)
		rezultat[i]=coefq[i];
	 return rezultat;
	}
}

//metoda scadere va returna coeficientii diferentei a doua polinoame
public int[] scadere(Polinom P, Polinom Q)
{
int p=P.getgrad();						//in p va fi gradul polinomului P
int q=Q.getgrad();						//in q va fi gradul polinomului Q
int coefp[]=P.getcoef();				//vectorul coefp va retine coeficientii lui P
int coefq[]=Q.getcoef();				//vectorul coefq va retine coeficientii lui Q
			
if (p>=q) 								//daca P are grad mai mare
 	{ 
	 int[] rezultat=new int[p+1];	
	 for (int i=0;i<=q;i++)
		rezultat[i]=coefp[i]-coefq[i];  //se scad coeficientii gradelor egale
	 for (int i=q+1;i<=p;i++)
		rezultat[i]=coefp[i];			//in rest se retin coef. lui P 
	 return rezultat;
	}
else 									//daca Q are grad mai mare
 	{									//se procedeaza la fel, doar ca in rest 
	 int[] rezultat=new int[q+1];	 
	 for (int i=0;i<=p;i++)				//se vor retine coef. lui Q
		 rezultat[i]=coefq[i]-coefp[i];
	 for (int i=p+1;i<=q;i++)
		 rezultat[i]=coefq[i];
	 return rezultat;
	}
}

//metoda inmultire va returna coeficientii produsului celor 2 polinoame
public int[] inmultire(Polinom P, Polinom Q)
{
int p=P.getgrad();	//p e gradul primului polinom
int q=Q.getgrad();  //q e gradul celui de-al doi-lea polinom
int coefp[]=P.getcoef();
int coefq[]=Q.getcoef();
int[] rezultat=new int[p+q+1];//polinomul rezultat are grad p+q+1
for (int i=0;i<=p;i++)
	for (int j=0;j<=q;j++)
	 rezultat[i+j]=rezultat[i+j]+coefp[i]*coefq[j]; //se contruiesc coeficientii
return rezultat;	
}

//metoda valoarepol va returna val. polinomului P in punctul k
public int valoarepol(Polinom P, int k)
{
int p=P.getgrad();						//in p va fi gradul polinomului P
int coefp[]=P.getcoef();				//vectorul coefp va retine coeficientii lui P
int putere=1;
int valoare=0;
for (int i=0;i<=p;i++)
{
	valoare=valoare+putere*coefp[i];//creste valoarea totala
	putere=putere*k;//creste puterea
}
return valoare;//returneaza valoarea
}
//metoda radacini va returna un vector ce contine divizorii termenului liber,
//deoarece va returna doar radacinile intregi 
public int[] radacini(Polinom P)
{
	int k=0;//folosit pentru indexare
	int p=P.getgrad();//in p se retine gradul polinomului P
	int posib[]=new int[2*p];//polinomul are cel mult p radacini, deci cel mult
							 //2*p posibilitati, pozitive si negative
	int coefp[]=P.getcoef(); //se retin coeficientii polinomului
	if (coefp[0]<0) coefp[0]=-coefp[0]; //se retine modulul termenului liber
	if (coefp[0]==0) System.out.print(" 0");//daca nu exista termen liber 0 e radacina
	else if (coefp[0]==1) //daca termenul liber e 1 se pastreaza div. lui 1
	{
		posib[0]=1;
		posib[1]=-1;
	}
	else if (coefp[0]==2)//daca termenul liber e 2 se pastreaza div. lui 2 
	{
		posib[0]=1;
		posib[1]=-1;
		posib[2]=2;
		posib[3]=-2;
	}
	else //altfel se cauta si se pastreaza divizorii termenului liber
	for (int i=2;i<Math.sqrt(coefp[0]);i++)
		if (coefp[i]%i==0) 
		{
			posib[k]=i;
			k++;
			posib[k]=-i;
			k++;
		}
	return posib;//se returneaza vectorul cu posibilitati de radacini
}

//metoda derivare returneaza coeficientii rezultatului derivarii unui polinom
public int[] derivare(Polinom P)
{
int p=P.getgrad();//p retine gradul polinomului
int coefp[]=P.getcoef();//coefp retine coeficientii polinomului
int[] rezultat = new int[p];
for (int i=0;i<=(p-1);i++)
	rezultat[i]=coefp[i+1]*(i+1); //se construieste vectorul coeficientilor derivatului
return rezultat;//rezultatul se returneaza
}
}