package QuadStorage;

/**
 * Minor.P3.DS.Coordinate.java Coordinate
 * 
 * @author Dirk N. Hortensius
 * @version Oct 16, 2012
 * 
 */
public final class Coordinate implements Compare2D<Long>
{
	Long x;
	Long y;

	public Coordinate(long x, long y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public long getX()
	{
		return this.x;
	}

	@Override
	public long getY()
	{
		return this.y;
	}

	@Override
	public Direction directionFrom(long X, long Y)
	{
		int i = this.x.compareTo(X);
		int j = this.y.compareTo(Y);

		// x > X
		if (i > 0)
		{
			if (j >= 0)
			{
				return Direction.NE;
			}
			return Direction.SE;
		}

		// x <= X
		if (j > 0)
		{
			return Direction.NW;
		} else
		{
			return Direction.SW;
		}
	}

	@Override
	public Direction inQuadrant(long xLo, long xHi, long yLo, long yHi)
	{
		if (this.inBox(xLo, xHi, yLo, yHi))
		{
			long X = (long) ((xLo + xHi) / 2.0);
			long Y = (long) ((yLo + yHi) / 2.0);

			return this.directionFrom(X, Y);
		}

		return Direction.NOQUADRANT;
	}

	@Override
	public boolean inBox(long xLo, long xHi, long yLo, long yHi)
	{	
		return true;/*
		return ( (this.x >= xLo) &&
				     (this.x <= xHi) && 
				     (this.y >= yLo) && 
				     (this.y <= yHi) );//*/
	}

	public String toString()
	{
		return ("(" + this.getX() + ", " + this.getY() + ")");
	}

}