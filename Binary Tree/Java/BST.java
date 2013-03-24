//    On my honor:
//   
//    - I have not discussed the Java language code in my program with
//      anyone other than my instructor or the teaching assistants
//      assigned to this course.
//   
//    - I have not used Java language code obtained from another student,
//      or any other unauthorized source, either modified or unmodified. 
//   
//    - If any Java language code or documentation used in my program
//      was obtained from another source, such as a text book or course
//      notes, that has been clearly noted with a proper citation in
//      the comments of my program.
//   
//    - I have not designed this program in such a way as to defeat or
//      interfere with the normal operation of the Curator System.
//
//    Dirk Hortensius

package Minor.P2.DS;

/**
 * Minor.P2.DS.BST.java
 * BST (BinarySearchTree)
 * 
 * @author Dirk N. Hortensius [dirker27]
 * @version Sep 25, 2012
 *
 * @param <T> - Comparable datatype of tree contents
 */
public class BST<T extends Comparable<? super T>> 
{
	  /**
	   * Minor.P2.DS.BST.java
	   * BinaryNode
	   * 
	   * Nodes of Binary Tree
	   * 
	   * @author Dirk N. Hortensius
	   * @version Sep 25, 2012
	   */
		class BinaryNode
    {
      T          element;  // the data in the node
      BinaryNode left;     // pointer to the left child
      BinaryNode right;    // pointer to the right child
      
      /**
       * IMPLICIT CONSTRUCTOR
       * 
       * Initializes node pointers to null
       *
       * @param elem - Initial Data of node
       * 							(Only changed in "recycling" process of BST.insert())
       */
      public BinaryNode( T elem )
      {
        this.element = elem;
        this.left    = null;
        this.right   = null;
      }
      
      /**
       * EXPLICIT CONSTRUCTOR
       *
       * @param elem - Initial Data
       * @param lt   - Initial Lesser Node
       * @param rt   - Initial Greater Node
       */
      public BinaryNode( T elem, BinaryNode lt, BinaryNode rt ) 
      {
        this.element  = elem;
        this.left 		= lt;
        this.right  	= rt;
      }
      
      /**
       * True if subtree of node is identical in formation + contents
       * 
       * Recurses to pointer nodes
       * Returns master Boolean statement for subtree
       */
      public boolean equals(BinaryNode other)
      {
      	// if other node is null, automatically inequivalent
      	if (other == null)
      	{
      		return false;
      	}
      	
      	boolean b = true;			// recursive truth tracker
      	
      	//- check left ------------------------------------
      	if (this.left != null)
      	{
      		if (other.left != null)
      		{
      			b = (b && this.left.equals(other.left));
      		}
      		else
      		{
      			b = false;
      		}      		
      	}
      	else
      	{
      		b = (b && other.left == null);
      	}
      	
      	//- check right -----------------------------------
      	if (this.right != null)
      	{
      		if (other.right != null)
      		{
      			b = (b && this.right.equals(other.right));
      		}
      		else
      		{
      			b = false;
      		}      		
      	}
      	else
      	{
      		b = (b && other.right == null);
      	}
      	
      	// final check
      	return ( b && this.element.equals(other.element) );
      }
      
    }

    BinaryNode root;        // pointer to root node, if present
    BinaryNode pool;        // pointer to first node in the pool
    int        pSize;       // size limit for node pool

    /**
     * IMPLICIT CONSTRUCTOR
     * 
     * Empty Tree
     * 
     * For trees w/o pools
     */
    public BST( ) 
    {
    	this.root  = null;
    	this.pool  = null;
    	this.pSize = 0;
    }

    /**
     * EXPLICIT CONSTRUCTOR
     *
     * Empty Tree
     *
     * @param Sz - Permanent pool size
     */
    public BST( int Sz )
    {
    	this.root  = null;
    	this.pool  = null;
    	this.pSize = Sz;
    }

    /**
     * True if empty tree 
     * 
     * (root is null)
     */
    public boolean isEmpty( )
    {
    	return (this.root == null);
    }

    /**
     * Returns pointer to data element if element in tree
     * 
     * Returns null otherwise
     * 
     * Calls Private helper function
     */
    public T find( T x )
    {
    	return this.find(x, this.root);
    }  

    /**
     * Inserts element into tree
     * 
     * Returns false for attempted duplicate entries
     * 
     * Recycles from node pool if items exist
     */
    public boolean insert( T x ) 
    {   
    	// if no duplicate
    	if ( this.find(x) == null)
    	{
    		// Recycles pool
      	BinaryNode temp;
      	if (this.pool != null)
      	{
      		temp = this.clipPool(this.pool);
      		temp.element = x;
      	}
      	else
      	{
      		temp = new BinaryNode(x);
      	}
      	
      	this.root = this.insert(temp, this.root);
      	return true;
    	}
    	return false;
    }

    /**
     * removes equivalent data element from tree
     * 
     * returns false if no data element in tree
     */
    public boolean remove( T x )
    {
    	// returns if item in tree, then removes
    	if ( this.find(x) != null )
    	{
    		this.root = this.remove(x, this.root);
    		return true;
    	}
    	return false;
    }

    /**
     * removes subtree starting at node of equivalent value
     * 
     * returns false if x is null or not in tree
     */
    public boolean prune( T x) 
    {
    	if ( this.find(x) != null )
    	{
    		this.root = this.prune(x, this.root);
    		return true;
    	}
    	return false;
    } 

    /**
     * wipes tree
     * 
     * (prunes at root)
     */
    public void clear( ) 
    {
    	this.root = this.purge(this.root);
    }

    /**
     * Compares to other object
     * 
     * True only if other object is a BST and has an equivalent root hierarchy
     */
    public boolean equals(Object other) 
    {
    	// if Class-Caste fails, object is not a BST
    	try {
      	@SuppressWarnings("unchecked")
				BST<T> tree = (BST<T>)other;
      	// if both roots are null, they are equal
      	if (this.root == null)
      	{
      		return (tree.root == null);
      	}
      	return this.root.equals(tree.root);
    	}
    	catch (ClassCastException e){
    		return false;
    	}
    }
    
    //~ PRIVATE IMPLEMENTATION ===================================== \\

    /**
     * Recursively searches for a node with value x.
     */
    private T find( T x, BinaryNode n )
    {
    	if (n == null || x == null)
    	{
    		return null;
    	}
    	
    	int c = x.compareTo(n.element);
    	if (c < 0)
    	{
    		return this.find(x, n.left);
    	}
    	else if( c > 0)
    	{
    		return this.find(x, n.right);
    	}
    	return n.element;
    }
    
    /**
     * recursively inserts new node element (if not already in tree)
     */
    private BinaryNode insert( BinaryNode x, BinaryNode n)
    {
    	if (n == null)
    	{
    		n = x;
    	}
    	
    	int c = x.element.compareTo(n.element);
    	if (c < 0)
    	{
    		n.left = insert(x, n.left);
    		return n;
    	}
    	else if (c > 0)
    	{
   			n.right = insert(x, n.right);
    		return n;
    	}
    	return n;
    }
    
    /**
     * recursively scans for and removes element from tree
     */
    private BinaryNode remove( T x, BinaryNode n)
    {
    	int c = x.compareTo(n.element);
    	if (c < 0)
    	{
    		n.left = this.remove(x, n.left);
    	}
    	else if(c > 0)
    	{
    		n.right = this.remove(x, n.right);
    	}
    	else
    	{
    		n = this.delete(n);
    	}
    	return n;
    }
    
    /**
     * Deletes item from tree, places into pool
     * 
     * Handles all 4 node cases (L, R, LR, None)
     * 
     * Severs node pointers of recycled node
     */
    private BinaryNode delete(BinaryNode n)
    {
    	BinaryNode t = null;						// return node/subtree
    	
    	if ( this.nodeHasChildren(n) )
    	{
    		// if no left child, replaced with right
    		if (n.left == null)
    		{
    			t = n.right;
    		}
    		// if no right child, replaced with left
    		else if (n.right == null)
    		{
    			t = n.left;
    		}
    		// if has 2 children (HARD MODE!!!)
    		else
    		{
    			BinaryNode u = n.right;
    			
    			// if has a leftmost element of right side
    			if (u.left != null)
    			{
    				t = u.left;
    				while (t.left != null)
    				{
    					u = u.left;
    					t = u.left;
    				}
    				u.left = t.right;
    				t.right = n.right;
    				t.left = n.left;
    			}
    			// if right node has no left, shift subtree
    			else
    			{
    				t = n.right;
    				t.left = n.left;
    			}
    		}
    	}
    	//neuter node
    	n.left = null;
    	n.right = null;
    	addToPool(n);
    	
    	return t;
    }
    
    /**
     * Inserts to pool
     * @param n
     */
    private void addToPool(BinaryNode n)
    {
    	//insert to pool if under capacity
    	if ( this.pSize > this.countTree(this.pool) )
    	{
    		this.pool = this.insert(n, this.pool);
    	}
    }
    
    private BinaryNode prune(T x, BinaryNode n)
    {
     	int c = x.compareTo(n.element);
     	if (c > 0)
     	{
     		n.right = this.prune(x, n.right);
     	}
     	else if (c < 0)
     	{
     		n.left = this.prune(x, n.left);
     	}
     	else
     	{
     		n = this.purge(n);
     	}
     	return n;
     		
    }
    
    /**
     * sets all pointers/root of subtree to null (postorder)
     */
    private BinaryNode purge(BinaryNode n)
    {
    	if (n.left != null)
    	{
    		n.left = this.purge(n.left);
    	}
    	if (n.right != null)
    	{
    		n.right = this.purge(n.right);
    	}
    	return null;
    }
    
    /**
     * Recursively counts (sub)tree generated by given BinaryNode
     * 
     * @param  n - root of subtree
     * @return size of tree / subtree starting at n
     */
    private int countTree( BinaryNode n )
    {
    	if (n == null)
    	{
    		return 0;
    	}
    	return (this.countTree(n.left) + this.countTree(n.right) + 1);
    }
    
    /**
     * pulls end node from the pool (postorder)
     */
    private BinaryNode clipPool(BinaryNode n)
    {
    	if (n.left != null)
    	{
    		return this.clipPool(n.left);
    	}
    	if (n.right != null)
    	{
    		return this.clipPool(n.right);
    	}
    	
    	this.pool = this.remove(n.element, this.pool);
    	return n;
    }  
        
    /**
     * determines if given node has children
     */
    private boolean nodeHasChildren(BinaryNode n)
    {
    	return ( (n.left != null) || (n.right != null) );
    }
    
}
