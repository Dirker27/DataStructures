package QuadStorage;

import java.util.Vector;

public class QuadTree
{  
  //~ FIELD DECLARATIONS ======================================== ~\\
  //
  private QuadNode root;
  
  private long xMin; 
  private long xMax;
  private long yMin;
  private long yMax;
  
  private Domain region;
   
  //~ INITIALIZATION ============================================ ~\\
  //
  /**
   * CONSTRUCTOR
   * 
   * Initialize quadtree to empty state, representing the specified region.
   *
   * @param xMin - x-axis lower bound
   * @param xMax - x-axis upper bound
   * @param yMin - y axis lower bound
   * @param yMax - y axis upper bound
   */
  public QuadTree(long xMin, long xMax, long yMin, long yMax) 
  {
  	// Set Tree Empty
  	this.root = null;
     
  	// Set Bounds
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
    
    this.region = new Domain(xMin, xMax, yMin, yMax);
  }
  
  //~ PUBLIC ACCESS FUNCIONS ==================================== ~\\
  //
  /** 
   * Pre:   elem != null
   * Post:  If elem lies within the tree's region, and elem is not already 
   *        present in the tree, elem has been inserted into the tree.
   * 
   * Return true iff elem is inserted into the tree. 
   */
  public boolean insert(GISQuadNode elem)
  {
  	if ( (elem.getX() <= this.xMax) && (elem.getX() >= this.xMin)
  			&& (elem.getY() <= this.yMax) && (elem.getY() >= this.yMin) )
  	{
  		if (root == null)
    	{
    		this.root = new QuadLeaf();
    	}
  		this.root = this.root.insert(elem, region);
  		return true;
  	}
  	return false;
  }
    
  /**
   * Pre:  elem != null
   * Post: If elem lies in the tree's region, and a matching element occurs
   *       in the tree, then that element has been removed.
   *
   * Returns true iff a matching element has been removed from the tree.
   */
  public boolean delete(DataNode elem)
  {
  	if ( (elem.getX() <= this.xMax) && (elem.getX() >= this.xMin)
  			&& (elem.getY() <= this.yMax) && (elem.getY() >= this.yMin)
  			&& (this.find(elem) != null) )
  	{
  		this.root   = this.root.delete(elem, region);
  		
  		if (this.root.isEmpty())
  		{
  			this.root = null;
  		}
  		return true;
  	}
  	return false;
  }

  /**
   * Pre:  elem != null
   * 
   * Returns reference to an element x within the tree such that 
   * elem.equals(x)is true, provided such a matching element occurs within
   * the tree; returns null otherwise.
   */
  public DataNode find(DataNode elem)
  {
  	if (this.root == null)
  	{
  		return null;
  	}
  	return this.root.find(elem, region);
  }

  public Vector<Integer> find(long x, long y)
  {
  	if (this.root == null)
  	{
  		return null;
  	}
  	return this.root.find(x, y, region);
  }
 
  /**
   * Pre:  xLo, xHi, yLo and yHi define a rectangular region
   *  
   * Returns a collection of (references to) all elements x such that x is 
   * in the tree and x lies at coordinates within the defined rectangular 
   * region, including the boundary of the region.
   * 
   * @param xLo, xHi, yLo, yHi - region bounds
   */
  public Vector<Integer> find(long xLo, long xHi, long yLo, long yHi)
  {
  	return this.root.find(xLo, xHi, yLo, yHi, this.region);      
  }
  
  public String toString()
  {
  	if (root != null)
  	{
  		return this.root.toString(region);
  	}
  	return this.region.toString();
  }
  
  
  
  //- Output stolen from example slides
  public String printTree()
  {
  	return printTreeHelper(this.root, "");
  }
	public String printTreeHelper(QuadNode sRoot, String Padding)
	{
		String ret = "";
		// Check for empty leaf
		if ( sRoot == null ) {
		ret = Padding + "  *\n";
		return ret;
		}
		
		// Check for and process SW and SE subtrees
		if ( sRoot.type.equals("branch") ) 
		{
			QuadInternal p = (QuadInternal) sRoot;
			ret += printTreeHelper(p.SW, Padding + " ");
			ret += printTreeHelper(p.SE, Padding + " ");
		}
		
		// Display indentation padding for current node
		ret += Padding;
		
		// Determine if at leaf or internal and display accordingly
		if ( sRoot.type.equals("leaf") )
		{
			QuadLeaf p = (QuadLeaf) sRoot;
			//ret += Padding + p.Elements.toString() + "\n";
			ret += Padding + p.toString() + "\n";
		}
		else
		{
			ret += Padding + "@\n";
		}
		
		// Check for and process NE and NW subtrees
		if ( sRoot.type.equals("branch") ) {
  		QuadInternal p = (QuadInternal) sRoot;
  		ret += printTreeHelper(p.NE, Padding + " ");
  		ret += printTreeHelper(p.NW, Padding + " ");
		}
		
		return ret;
	}
 
}