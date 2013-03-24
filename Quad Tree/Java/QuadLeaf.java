package QuadStorage;

import java.util.Vector;

final class QuadLeaf extends QuadNode
{
	private final int DEFAULT_BUCKET_SIZE = 4;
	
	private int bucketSize;
	Vector<GISQuadNode> Elements;
  
	public QuadLeaf()
	{
		this.type       = "leaf";
		this.bucketSize = this.DEFAULT_BUCKET_SIZE;
		this.Elements   = new Vector<GISQuadNode>(bucketSize);
	}
	
	public boolean isEmpty()
	{
		return (this.Elements.size() == 0);
	}
	
	public GISQuadNode find(GISQuadNode elem, Domain bearings)
	{
		for (GISQuadNode i : this.Elements)
		{
			if ( (i.getX() == elem.getX()) && (i.getY() == elem.getY()) )
			{
				return i;
			}
		}
		return null;
	}
	
	public Vector<Integer> find(long x, long y, Domain bearings)
	{
		for (GISQuadNode n : this.Elements)
		{
			if (n.getX() == x && n.getY() == y)
			{
				return n.getData();
			}
		}
		return null;
	}
	
	public Vector<Integer> find(long xMin, long xMax, long yMin, long yMax,
			                        Domain bearings)
	{
		Vector<Integer> vec = new Vector<Integer>();
		Vector<Integer> t   = new Vector<Integer>();
		if (bearings.hasIntersectRegion(xMin, xMax, yMin, yMax))
		{
			for (GISQuadNode n : this.Elements)
			{
				//if (n.inBox(xMin, xMax, yMin, yMax))
				//{
  				t = n.getData();
  				for (Integer o : t)
  				{
  					vec.add(o);
  				}
  				return vec;
				//}
			}
		}
		
		return new Vector<Integer>();
	}
	
	public QuadNode insert(GISQuadNode elem, Domain bearings)
	{
		if (this.Elements.size() == this.bucketSize)
		{
			QuadInternal branch = new QuadInternal();
			
			for (GISQuadNode i : this.Elements)
			{
				branch.insert(i, bearings);
			}
			branch.insert(elem, bearings);
			return branch;
		}
		
		boolean b = true;
		for (GISQuadNode i : this.Elements)
		{
			if (b && i.getX() == elem.getX() && i.getY() == elem.getY())
			{
				i.addNeighbor(elem.getData().get(0));
				b = false;
			}
		}
		if (b){
			this.Elements.add(elem);
		}
		
		return this;
	}
	
	public QuadNode delete(GISQuadNode elem, Domain bearings)
	{
		int i = 0;
		for (GISQuadNode e : this.Elements)
		{
			if ( (e.getX() == elem.getX()) && (e.getY() == elem.getY()) )
			{
				this.Elements.remove(i);
				break;
			}
			i++;
		}
		return this;
	}
	
	public String toString(Domain bearings)
	{
		String ret = "";
		for (GISQuadNode n : this.Elements)
		{
			ret += n.toString();
		}
		return ret;
	}
	public String toString()
	{
		String ret = "";
		for (GISQuadNode n : this.Elements)
		{
			ret += n.toString();
		}
		return ret;
	}
	
}  // </QuadLeaf>
