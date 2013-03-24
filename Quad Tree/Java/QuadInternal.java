package QuadStorage;

import java.util.Vector;

final class QuadInternal extends QuadNode
{
	QuadNode NW, NE, SE, SW;
	
	int filled;
   
	public QuadInternal()
	{
		this.type = "branch";
		this.NW = null;
		this.NE = null;
		this.SE = null;
		this.SW = null;
		
		filled = 0;
	}
	
	public boolean isEmpty()
	{
		boolean b = true;
		if (this.NW != null)
		{
			b = (b && this.NW.isEmpty());
		}
		if (this.NE != null)
		{
			b = (b && this.NE.isEmpty());
		}
		if (this.SW != null)
		{
			b = (b && this.SW.isEmpty());
		}
		if (this.SE != null)
		{
			b = (b && this.SE.isEmpty());
		}
		return b;
	}
	
	public GISQuadNode find(GISQuadNode elem, Domain bearings)
	{
		Coordinate core = bearings.getCore();
		Coordinate c    = new Coordinate(elem.getX(), elem.getY());
		Direction d     = c.directionFrom(core.getX(), core.getY());
		
		if (d == Direction.NW && (this.NW != null) )
		{
			return this.NW.find(elem, bearings.getNW());
		}
		else if (d == Direction.NE && (this.NE != null) )
		{
			return this.NE.find(elem, bearings.getNE());
		}
		else if (d == Direction.SW && (this.SW != null) )
		{
			return this.SW.find(elem, bearings.getSW());
		}
		else if (d == Direction.SE && (this.SE != null) )
		{
			return this.SE.find(elem, bearings.getSE());
		}
		
		return null;
	}
	
	public Vector<Integer> find(long x, long y, Domain bearings)
	{
		Coordinate core = bearings.getCore();
		Coordinate c    = new Coordinate(x, y);
		Direction d     = c.directionFrom(core.getX(), core.getY());
		
		if (d == Direction.NW && (this.NW != null) )
		{
			return this.NW.find(x, y, bearings.getNW());
		}
		else if (d == Direction.NE && (this.NE != null) )
		{
			return this.NE.find(x, y, bearings.getNE());
		}
		else if (d == Direction.SW && (this.SW != null) )
		{
			return this.SW.find(x, y, bearings.getSW());
		}
		else if (d == Direction.SE && (this.SE != null) )
		{
			return this.SE.find(x, y, bearings.getSE());
		}
		
		return null;
	}
	
	public Vector<Integer> find(long xMin, long xMax, long yMin, long yMax,
			                            Domain bearings)
	{		
		Vector<Integer> vec = new Vector<Integer>();
		Vector<Integer> t   = new Vector<Integer>();
		
		Domain d = bearings.getNW();
		if (this.NW != null && d.hasIntersectRegion(xMin, xMax, yMin, yMax))
		{
			t = (this.NW.find(xMin, xMax, yMin, yMax, d));
			for (Integer o : t)
			{
				vec.add(o);
			}
			return vec;
		}
		d = bearings.getNE();
		if (this.NE != null && d.hasIntersectRegion(xMin, xMax, yMin, yMax))
		{
			t = this.NE.find(xMin, xMax, yMin, yMax, d);
			for (Integer o : t)
			{
				vec.add(o);
			}
			return vec;
		}
		d = bearings.getSW();
		if (this.SW != null && d.hasIntersectRegion(xMin, xMax, yMin, yMax))
		{
			t = this.SW.find(xMin, xMax, yMin, yMax, d);
			for (Integer o : t)
			{
				vec.add(o);
			}
		}
		d = bearings.getSE();
		if (this.SE != null && d.hasIntersectRegion(xMin, xMax, yMin, yMax))
		{
			t = this.SE.find(xMin, xMax, yMin, yMax, d);
			for (Integer o : t)
			{
				vec.add(o);
			}
		}
		return vec;
	}
	
	public QuadNode insert(GISQuadNode elem, Domain bearings)
	{
		Coordinate core = bearings.getCore();
		Coordinate c    = new Coordinate(elem.getX(), elem.getY());
		Direction d     = c.directionFrom(core.getX(), core.getY());
		
		if (d == Direction.NW)
		{
			if (this.NW == null)
			{
				this.NW = new QuadLeaf();
				this.filled++;
			}
			this.NW = this.NW.insert(elem, bearings.getNW());
		}
		else if (d == Direction.NE)
		{
			if (this.NE == null)
			{
				this.NE = new QuadLeaf();
				this.filled++;
			}
			this.NE = this.NE.insert(elem, bearings.getNE());
		}
		else if (d == Direction.SW)
		{
			if (this.SW == null)
			{
				this.SW = new QuadLeaf();
				this.filled++;
			}
			this.SW = this.SW.insert(elem, bearings.getSW());
		}
		else if (d == Direction.SE)
		{
			if (this.SE == null)
			{
				this.SE = new QuadLeaf();
				this.filled++;
			}
			this.SE = this.SE.insert(elem, bearings.getSE());
		}
		
		return this;
	}
	
	public QuadNode delete(GISQuadNode elem, Domain bearings)
	{
		Coordinate core = bearings.getCore();
		Coordinate c    = new Coordinate(elem.getX(), elem.getY());
		Direction d     = c.directionFrom(core.getX(), core.getY());
		
		if (d == Direction.NW)
		{
			this.NW = this.NW.delete(elem, bearings.getNW());
			if (this.NW.isEmpty())
			{
				this.NW = null;
				this.filled--;
			}
		}
		else if (d == Direction.NE)
		{
			this.NE = this.NE.delete(elem, bearings.getNE());
			if (this.NE.isEmpty())
			{
				this.NE = null;
				this.filled--;
			}
		}
		else if (d == Direction.SW)
		{
			this.SW = this.SW.delete(elem, bearings.getSW());
			if (this.SW.isEmpty())
			{
				this.SW = null;
				this.filled--;
			}
		}
		else if (d == Direction.SE)
		{
			this.SE = this.SE.delete(elem, bearings.getSE());
			if (this.SE.isEmpty())
			{
				this.SE = null;
				this.filled--;
			}
		}
		
		if (this.filled <= 1)
		{
			return this.trim();
		}
		
		return this;
	}
	
	private QuadNode trim()
	{
		if ( (this.NW != null) && (this.NW.type.equals("leaf")) )
		{
			return this.NW;
		}
		else if ( (this.NE != null) && (this.NE.type.equals("leaf")) )
		{
			return this.NE;
		}
		else if ( (this.SW != null) && (this.SW.type.equals("leaf")) )
		{
			return this.SW;
		}
		if ( (this.SE != null) && (this.SE.type.equals("leaf")) )
		{
			return this.SE;
		}
		return this;
	}
	
	public String toString(Domain bearings)
	{
		String ret = bearings.toString();
		//String ret = "";
		if (this.NW != null)
		{
			ret += "\nNW: " + NW.toString(bearings.getNW());
		}
		if (this.NE != null)
		{
			ret += "\nNE: " + NE.toString(bearings.getNE());
		}
		if (this.SW != null)
		{
			ret += "\nSW: " + SW.toString(bearings.getSW());
		}
		if (this.SE != null)
		{
			ret += "\nSE: " + SE.toString(bearings.getSE());
		}
		return ret;
	}
}  // </GISQuadInternal>
