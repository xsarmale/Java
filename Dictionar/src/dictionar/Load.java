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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class Load 
{
	MyMap myMap;
	public void readFile(String f)
	{
		String s;
		try
		{
			BufferedReader in=new BufferedReader(new FileReader(f));		
			while((s=in.readLine())!=null)
			{
				System.out.println(s);
			}
			in.close();
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}	
	}
	
	public void readLoadUsingScanner(String f)
	{
		String s;
		String[] aux;
		try
		{
			Scanner scanner=new Scanner(new File(f));			
			while(scanner.hasNextLine())
			{
				s=scanner.nextLine();				
				aux=s.split("\\s");
				myMap.put(aux[0],s);
			}
			scanner.close();
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
	}
	
	public String findUsingScanner(String f,String text)
	{		
		try
		{
			String s=null;
			Scanner scanner=new Scanner(new File(f));
			while(scanner.hasNextLine() && s==null)
			{				
				scanner.nextLine();
				s=scanner.findInLine(text);				
			}
			scanner.close();
			return s;
		}
		catch(IOException e)
		{
			return "File not found";
		}
	}
	
	public void writeLoadInFile(String f)
	{		
		try
		{	
			//obs. punem false ca sa scrie peste; prima data sterge si apoi scrie!!!!!!!!!!!!!!!!!
			BufferedWriter buf=new BufferedWriter(new FileWriter(f,false));
			for(int i=0;i<=25;i++)
			{
				myMap.getHTi(i).setAux(myMap.getHTi(i).determineMinim(myMap.getHTi(i).getRoot()));
				if(myMap.getHTi(i).getAux()!=null)
				{
					while(myMap.getHTi(i).hasNext())
					{
						buf.write((String)(myMap.getHTi(i).getAux().getValue()));
						buf.newLine();
						myMap.getHTi(i).next();
					}					
					buf.write((String)(myMap.getHTi(i).getAux().getValue()));
					buf.newLine();
				}	
			}								
			buf.close();
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
	}
	
	public void writeInFile(String f,String text)
	{		
		try
		{
			BufferedWriter buf=new BufferedWriter(new FileWriter(f,true));							
			buf.newLine();		
			buf.write(text);					
			buf.close();
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
	}
	public void writeRandomFile(String f,String s)
	{
		try 
		{
	        File file = new File(f);
	        RandomAccessFile raf = new RandomAccessFile(file, "rw");	        	    
	        // Seek to end of file
	        raf.seek(f.length());
	    
	        // Append to the end	        
	        raf.writeBytes(s);
	        raf.close();
	    }
		catch (IOException e) 
		{
			System.out.println("Eroare in cautarea fisierului");
		}
    }
	
	public void mapedByteBuffer(String f) 
    {
        // check command-line argument
        // get channel
        try
        {
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
	        FileChannel fc = raf.getChannel();
	
	        // map file to buffer
	        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
	
	        // reverse bytes of file
	 	        
	        int len = (int)fc.size();
	        for (int i = 0, j = len - 1; i < j; i++, j--) 
	        {
	          byte b = mbb.get(i);
	          mbb.put(i, mbb.get(j));
	          mbb.put(j, b);
	        }
	        // finish up
	        fc.close();
	        raf.close();
        }
        catch(IOException e)
		{
			System.out.println("File not found");
		}
    }		
}
