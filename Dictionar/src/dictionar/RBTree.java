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
import javax.swing.JOptionPane;

public class RBTree 
{
	private RBTree.Node root;
	private int size;
	private Node aux;
	private static final int BLACK=1;
	private static final int RED=0;
	protected static class Node
	{
		private Comparable<String> key;
		private Object value;
		private int color;
		private Node father;
		private Node left;
		private Node right;
		// … constructor and methods
		public Object getKey()
		{
			return key;
		}
		public Object getValue()
		{
			return value;
		}
		public int getColor()
		{
			return color;
		}		
		//returns the old value
		public Comparable setKey(Comparable ckey)
		{
			Comparable aux=key;
			key=(String)ckey;
			return aux;
		}
		//returns the old value
		public Object setValue(Object val)
		{
			Object auxiliar=value;
			value=val;
			return auxiliar;
		}
		//returns the old color
		public int setColor(int color)
		{
			int auxiliar=this.color;
			this.color=color;
			return auxiliar;
		}
	}
	// constructor
	public RBTree()
	{
		// Build an initial empty map
		root = null;
		aux=null;
		size=0;
	}
	
//	methods
	/** 
	 * returns true if and only if the current element has a succesor
	 * @pre true	
	 * @post @nochange
	 */
	public boolean hasNext()
	{		
		if(determineSuccesor(aux)==null) return false;
		return true;
	}
	
	/** 
	 * returns the succesor of the current element
	 * @pre hasNext()
	 * @post @nochange
	 * @post @result == determineSuccesor(aux)		 
	 */
	public Object next()
	{		
		aux=determineSuccesor(aux);
		return aux;
	}
	
	/** 
	 * remove the current element
	 * @pre getNrElem() >0
	 * @post getNrElem() == getNrElem()@pre-1
	 * @post search(aux.product)==null		 
	 */
	public void remove()
	{
		delete_node(aux);
	}
	
	/** 
	 * return the collection's root
	 * @pre true
	 * @post @nochange		 
	 * @post @result==root
	 */
	public Node getRoot()
	{
		return root;
	}
	
	/** 
	 * return the collection's current element
	 * @pre true
	 * @post @nochange		 
	 * @post @result==aux
	 */
	public Node getAux()
	{
		return aux;
	}
	
	public int getSize()
	{
		return size;
	}
	
	
	public void setRoot(Node yourRoot)
	{
		root=yourRoot;
	}
	
	public void setAux(Node yourAux)
	{
		aux=yourAux;
	}
	
	public void setSize(int nr)
	{
		size=nr;
	}
	
	//	my methods
	@SuppressWarnings({ "unchecked"})
	public	RBTree.Node search(Comparable k)
	{
		RBTree.Node p;
		if(root==null) return null;/*arborele este vid */
		p=root;
		while(p != null)
		{
			  if(k.compareTo(p.key)==0) return p;/* The key was finded */
			  else if(k.compareTo(p.key)<0) p=p.left;
	          else p=p.right;
		}
		return null; /* The collection doesn't contain this key */
	}
	//b can contain ? and * 
	public boolean matchWord(Comparable<String> a,Comparable<String> b)
	{
		//if b1 does't contain * , a1 matches b1 <=> a1==b1
		if(b.compareTo("*")==0) return true;
		String a1=((String)a).toLowerCase();
		String b1=((String)b).toLowerCase();
			
		//in case that b1 and a1 don't have the same lenght and b1 contains *
		if(b1.indexOf("*")!=-1)
		{
			if(b1.indexOf("*")+1>a1.length()) return false;
			int i=0;
			while(b1.charAt(i)!='*')
			{
				if(b1.charAt(i)!='?')
					if(b1.charAt(i)!=a1.charAt(i)) return false;
				i++;
			}
			return true;
		}
		if(b1.length()>a1.length()) return false;
		if(b1.length()==a1.length())
		{
			for(int i=0;i<b1.length();i++)
			{
				if(b1.charAt(i)!='?')
					if(b1.charAt(i)!=a1.charAt(i)) return false;
			}
			return true;
		}
		else return false;
	}
	
	@SuppressWarnings("unchecked")
	public String searchMatches(Comparable<String> b)
	{		
		String s="";
		setAux(determineMinim(root));
		if(aux!=null)
		{
			while(hasNext())
			{
				if(matchWord((Comparable<String>)aux.key,b))
					s+=aux.value+"\n";
				next();
			}
			if(matchWord((Comparable<String>)aux.key,b))
				s+=aux.value+"\n";
		}
		return s;
	}
	
	public int insert(RBTree.Node z)
	{
		RBTree.Node y=null,x;		
		x=root;
		while(x!=null)
		{
			y=x;			
			if(z.key.compareTo((String)x.key)<0)
				x=x.left;
			else if(z.key.compareTo((String)x.key)>0)
				x=x.right;
			else return 0;//this node already exists in collection
		}
		z.father=y;
		if(y==null)
			root=z;
		else if(z.key.compareTo((String)y.key)<0)
			y.left=z;
		else y.right=z;
		return 1;
	}
	
	public int insertRB(RBTree.Node x)
	{
		if(insert(x)==0) return 0;
		fixAfterInsertion(x);
		size++;
		return 1;
	}
	
	
	/** 
	 * remove a specific node from collection
	 * @pre getNrElem() > 0
	 * @post getNrElem() == getNrElem()@pre-1
	 * @post search(aux.product)==null	 
	 */
	public void delete_node(RBTree.Node z)
	{
		RBTree.Node y,x;
		if((z.left==null)||(z.right==null))
			y=z;
		else
			y=determineSuccesor(z);
		if(y.left!=null)
			x=y.left;
		else
			x=y.right;
		if(x!=null)
			x.father=y.father;
		if(y.father==null)
			root=x;
		else if(y==y.father.left)
			y.father.left=x;
		else
			y.father.right=x;
		if(!y.equals(z))
		{
			z.key=y.key;
			z.value=y.value;
		}
	}
	
	public void deleteNodeRB(Node p) {
        size--;

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (p.left != null && p.right != null) {
            Node s = determineSuccesor(p);
            p.key = s.key;       
            p.value = s.value;  
            p = s;
        } // p has 2 children

        // Start fixup at replacement node, if it exists.
        Node replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            // Link replacement to father
            replacement.father = p.father;
            if (p.father == null)
                root = replacement;
            else if (p == p.father.left)
                p.father.left  = replacement;
            else
                p.father.right = replacement;

            // Null out links so they are OK to use by fixAfterDeletion.
            p.left = p.right = p.father = null;

            // Fix replacement
            if (p.color ==BLACK)
                fixAfterDeletion(replacement);
        } else if (p.father == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.color == BLACK)
                fixAfterDeletion(p);

            if (p.father != null) {
                if (p == p.father.left)
                    p.father.left = null;
                else if (p == p.father.right)
                    p.father.right = null;
                p.father = null;
            }
        }
    }
	
	 private void fixAfterInsertion(RBTree.Node x) {
	        x.color = RED;

	        while (x != null && x != root && x.father.color == RED) {
	            if (fatherOf(x) == leftOf(fatherOf(fatherOf(x)))) {
	                RBTree.Node y = rightOf(fatherOf(fatherOf(x)));
	                if (colorOf(y) == RED) {
	                    setColor(fatherOf(x), BLACK);
	                    setColor(y, BLACK);
	                    setColor(fatherOf(fatherOf(x)), RED);
	                    x = fatherOf(fatherOf(x));
	                } else {
	                    if (x == rightOf(fatherOf(x))) {
	                        x = fatherOf(x);
	                        rotateLeft(x);
	                    }
	                    setColor(fatherOf(x), BLACK);
	                    setColor(fatherOf(fatherOf(x)), RED);
	                    if (fatherOf(fatherOf(x)) != null) 
	                        rotateRight(fatherOf(fatherOf(x)));
	                }
	            } else {
	                RBTree.Node y = leftOf(fatherOf(fatherOf(x)));
	                if (colorOf(y) == RED) {
	                    setColor(fatherOf(x), BLACK);
	                    setColor(y, BLACK);
	                    setColor(fatherOf(fatherOf(x)), RED);
	                    x = fatherOf(fatherOf(x));
	                } else {
	                    if (x == leftOf(fatherOf(x))) {
	                        x = fatherOf(x);
	                        rotateRight(x);
	                    }
	                    setColor(fatherOf(x),  BLACK);
	                    setColor(fatherOf(fatherOf(x)), RED);
	                    if (fatherOf(fatherOf(x)) != null) 
	                        rotateLeft(fatherOf(fatherOf(x)));
	                }
	            }
	        }
	        root.color = BLACK;
	    }
	 
	 /** From CLR **/
	    private void fixAfterDeletion(RBTree.Node x) {
	        while (x != root && colorOf(x) == BLACK) {
	            if (x == leftOf(fatherOf(x))) {
	                RBTree.Node sib = rightOf(fatherOf(x));

	                if (colorOf(sib) == RED) {
	                    setColor(sib, BLACK);
	                    setColor(fatherOf(x), RED);
	                    rotateLeft(fatherOf(x));
	                    sib = rightOf(fatherOf(x));
	                }

	                if (colorOf(leftOf(sib))  == BLACK && 
	                    colorOf(rightOf(sib)) == BLACK) {
	                    setColor(sib,  RED);
	                    x = fatherOf(x);
	                } else {
	                    if (colorOf(rightOf(sib)) == BLACK) {
	                        setColor(leftOf(sib), BLACK);
	                        setColor(sib, RED);
	                        rotateRight(sib);
	                        sib = rightOf(fatherOf(x));
	                    }
	                    setColor(sib, colorOf(fatherOf(x)));
	                    setColor(fatherOf(x), BLACK);
	                    setColor(rightOf(sib), BLACK);
	                    rotateLeft(fatherOf(x));
	                    x = root;
	                }
	            } else { // symmetric
	                RBTree.Node sib = leftOf(fatherOf(x));

	                if (colorOf(sib) == RED) {
	                    setColor(sib, BLACK);
	                    setColor(fatherOf(x), RED);
	                    rotateRight(fatherOf(x));
	                    sib = leftOf(fatherOf(x));
	                }

	                if (colorOf(rightOf(sib)) == BLACK && 
	                    colorOf(leftOf(sib)) == BLACK) {
	                    setColor(sib,  RED);
	                    x = fatherOf(x);
	                } else {
	                    if (colorOf(leftOf(sib)) == BLACK) {
	                        setColor(rightOf(sib), BLACK);
	                        setColor(sib, RED);
	                        rotateLeft(sib);
	                        sib = leftOf(fatherOf(x));
	                    }
	                    setColor(sib, colorOf(fatherOf(x)));
	                    setColor(fatherOf(x), BLACK);
	                    setColor(leftOf(sib), BLACK);
	                    rotateRight(fatherOf(x));
	                    x = root;
	                }
	            }
	        }

	        setColor(x, BLACK); 
	    }




    /** From CLR **/
    
    private static int colorOf(RBTree.Node p) {
        return (p == null ? BLACK : p.color);
    }

    private static RBTree.Node  fatherOf(RBTree.Node p) { 
        return (p == null ? null: p.father);
    }

    private static void setColor(RBTree.Node p, int c) { 
        if (p != null)  p.color = c; 
    }

    private static RBTree.Node  leftOf(RBTree.Node p) { 
        return (p == null)? null: p.left; 
    }

    private static RBTree.Node  rightOf(RBTree.Node p) { 
        return (p == null)? null: p.right; 
    }


	
	private void rotateLeft(Node p) {
        Node r = p.right;
        p.right = r.left;
        if (r.left != null)
            r.left.father = p;
        r.father = p.father;
        if (p.father == null)
            root = r;
        else if (p.father.left == p)
            p.father.left = r;
        else
            p.father.right = r;
        r.left = p;
        p.father = r;
    }

    /** From CLR **/
    private void rotateRight(Node p) {
        Node l = p.left;
        p.left = l.right;
        if (l.right != null) l.right.father = p;
        l.father = p.father;
        if (p.father == null)
            root = l;
        else if (p.father.right == p)
            p.father.right = l;
        else p.father.left = l;
        l.right = p;
        p.father = l;
    }

	
	public RBTree.Node determineMinim(RBTree.Node r)
	{
		assert root!=null;
		RBTree.Node p;
		p=r;
		if(p==null) return null;
		while(p.left!=null)
		{
			p=p.left;			
		}
		return p;
	}
	
	public RBTree.Node determineSuccesor(RBTree.Node q)
	{
		RBTree.Node p=q,y;
		if(p.right!=null)
			return determineMinim(p.right);			
		y=p.father;
		while((y!=null)&&(p==y.right))
		{
			p=y;
			y=y.father;
		}
		return y;		
	}
	void preordine(Node p,int nivel)
	{
	if (p!=null)
	         {
	          for(int i=1;i<=nivel;i++) System.out.print(" ");
	          System.out.println(p.key);
	          preordine(p.left,nivel+1);
	          preordine(p.right,nivel+1);          
	         }
	}

	void inordine(Node p,int nivel)
	{
	if (p!=null)
	         {
	         inordine(p.left,nivel+1);         
	         for(int i=1;i<=nivel;i++) System.out.print("    ");             
	         System.out.println(p.key+(p.color==BLACK ? " n":" r"));
	         inordine(p.right,nivel+1);
	         }     
	}
}