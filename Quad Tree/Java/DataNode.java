package QuadStorage;

import java.util.Vector;

public class DataNode implements Compare2D<Long>
{
	private Vector<Integer>   data;
	private Coordinate				loc;
	
	public DataNode(GISRecord record)
	{
		this.data = new Vector<Integer>();
		this.data.add(record.offset);
		
		this.loc = new Coordinate(record.primaryLong.getSec() ,
														  record.primaryLat.getSec()  );
	}
	
	/**
	 * TESTING ONLY
	 */
	public DataNode(long x, long y)
	{
		this.data = new Vector<Integer>();
		this.loc = new Coordinate(x, y);
	}
	
	/**
	 * for Cases where two objects have the same physical location
	 * @param r - neighbor
	 */
	public void addNeighbor(Integer o)
	{
		this.data.add(o);
	}
	

	@Override
	public long getX()
	{
		return loc.getX();
	}

	@Override
	public long getY()
	{
		return loc.getY();
	}

	@Override
	public Direction directionFrom(long X, long Y)
	{
		return loc.directionFrom(X, Y);
	}

	@Override
	public Direction inQuadrant(long xLo, long xHi, long yLo, long yHi)
	{
		return loc.inQuadrant(xLo, xHi, yLo, yHi);
	}

	@Override
	public boolean inBox(long xLo, long xHi, long yLo, long yHi)
	{
		return loc.inBox(xLo, xHi, yLo, yHi);
	}

	public Vector<Integer> getData()
	{
		return this.data;
	}
	
	public String toString()
	{
		String ret = "[" + this.loc.toString() + ",";
		for (Integer i : this.data)
		{
			ret += " " + i;
		}
		return ret + "] ";
	}
	
}
