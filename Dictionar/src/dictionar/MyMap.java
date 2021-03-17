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
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyMap implements Map
{
	private static final int M=26;
	private RBTree[] HT;
	private int mapSize;
	private Collection<Object> collection; //collection of RBTree's values
	//private Set<RBTree.Node> set;
	//private Set<Comparable<String>> keyset;
	public MyMap()
	{	
		HT = new RBTree[M];
		for(int i=0;i<M;i++)
		{
			HT[i]=new RBTree();
		}		
	}
	
	/**
	 * returns  the ASCII code of the first character in String s
	 * @pre s!=null
	 * @param s
	 * @return 
	 */
	//only letters are allowed
	//avoid the others characters, even figures
	public int hashFunction(Comparable s)
	{
		String s1=(String)s;
		return (int)s1.toLowerCase().charAt(0)-97;
	}
	
	public String searchMatchesInMap(Comparable<String> b)
	{
		if(b.compareTo("*")<0  && b.compareTo("?")<0)
		{
			System.out.println("Primul caracter este litera \nb este "+b);
			return HT[hashFunction(b)].searchMatches(b);
		}
		else if (b.compareTo("?")==0)
		{
			String s="";
			for(int i=0;i<M;i++)
				s+=HT[i].searchMatches(b);
			return s;
		}
		else 
		{
			String s="";
			for(int i=0;i<M;i++)
				s+=HT[i].searchMatches(b);
			return s;
		}
				
	}
	
	public int size() {
		//Returns the number of key-value mappings in this map.
		return mapSize;
	}
	
	public int getMapSize()
	{
		return mapSize;
	}
	
	public RBTree getHTi(int i)
	{
		return HT[i];
	}
	public RBTree[] getHT()
	{
		return HT;
	}
	
	public void setMapSize(int mapSize)
	{
		this.mapSize=mapSize;
	}
	
	public Object put(Object key, Object val)
	{
		//add (key, val) eventually replacing
		// the existing Node with the same key
		// Returns the value in that Node or null if such 
		// Node doesn’t exist
		Comparable ckey= (Comparable) key;
		int i=hashFunction((String)ckey);
		RBTree.Node treeNode=HT[i].search(ckey);
		if(treeNode== null)
		{
			RBTree.Node newNode=new RBTree.Node();
			newNode.setKey((Comparable)key);
			newNode.setValue(val);
			HT[i].insertRB(newNode);			
			mapSize++;
			return null;
		}
		else
		{
			Object oldval= treeNode.getValue();
			treeNode.setValue(val);
			return oldval;
		}
	}

	public void clear() {
		// Removes all mappings from this map (optional operation).
		for(int i=0;i<=M-1;i++)
			HT[i]=null;
		mapSize=0;
	}

	public boolean containsKey(Object key) {
		// Returns true if this map contains a mapping for the specified key. 
		Comparable ckey=(Comparable)key;
		return HT[hashFunction(ckey)].search(ckey)!=null;		
	}

	public boolean containsValue(Object val) {
		// Returns true if this map maps one or more keys to the specified value.
		//we use method contains defined by interface Collection
		return collection.contains(val);
	}

	public Set<RBTree.Node> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object get(Object key) {
		//the value to which this map maps the specified key,
		// or null if the map contains no mapping for this key. 
		Comparable ckey=(Comparable)key;
		RBTree.Node p=HT[hashFunction(ckey)].search(ckey);
		return (p==null ? null : p.getValue());
	}

	public boolean isEmpty() {
		// Returns true if this map contains no key-value mappings.
		if(mapSize==0) return true;
		return false;
	}

	public Set<Comparable<String>> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void putAll(Map arg0) {
		// TODO Auto-generated method stub
		
	}

	public Object remove(Object key) {
		//Removes the mapping for this key from this map if it is present
		//returns previous value associated with specified key, 
		//or null if there was no mapping for key.
		Comparable ckey=(Comparable) key;
		int i=hashFunction((String)ckey);
		RBTree.Node treeNode=HT[i].search(ckey);
		if(treeNode!=null)
		{
			Object val=treeNode.getValue();
			HT[i].deleteNodeRB(treeNode);
			HT[i].setSize(HT[i].getSize()-1);
			mapSize--;
			return val;
		}
		return null;
	}
	/*METHOd ADD---interface Collection
	 * Ensures that this collection contains the specified element (optional operation). 
	 * Returns true if this collection changed as a result of the call. 
	 * Returns false if this collection does not permit duplicates and already 
	 * 	contains the specified element.
	 */
	
	public Collection<Object> values() {
		//returns a collection view of the values contained in this map.
		for(int i=0;i<=M-1;i++)
		{
			HT[i].setAux(HT[i].determineMinim(HT[i].getRoot()));
			if(HT[i].getAux()!=null)
			{
				while(HT[i].hasNext())
				{
					collection.add(HT[i].getAux().getValue());
					HT[i].next();
				}
				collection.add(HT[i].getAux().getValue());
			}	
		}
		return collection;
	}

}
