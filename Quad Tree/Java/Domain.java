package QuadStorage;

final class Domain
	{
		Coordinate topL;
		Coordinate topR;
		Coordinate botL;
		Coordinate botR;
		
		long xMax;
		long xMin;
		long yMax;
		long yMin;
		
		private Coordinate core;
		
		public Domain(long xMin, long xMax, long yMin, long yMax)
		{
			this.topL = new Coordinate(xMin, yMax);
			this.topR = new Coordinate(xMax, yMax);
			this.botL = new Coordinate(xMin, yMin);
			this.botR = new Coordinate(xMax, yMin);
			
			this.core = new Coordinate( ((xMin + xMax) / (long)2.0),
																		 ((yMin + yMax) / (long)2.0) );
			this.xMin = xMin;
			this.xMax = xMax;
			this.yMin = yMin;
			this.yMax = yMax;
		}
		
		public Domain getNE()
		{
			long xMin = this.core.getX();
			long xMax = this.topR.getX();
			long yMin = this.core.getY();
			long yMax = this.topR.getY();
			
			return new Domain(xMin, xMax, yMin, yMax);
		}
		
		public Domain getNW()
		{
			long xMax = this.core.getX();
			long xMin = this.topL.getX();
			long yMin = this.core.getY();
			long yMax = this.topR.getY();
			
			return new Domain(xMin, xMax, yMin, yMax);
		}
		
		public Domain getSE()
		{
			long xMin = this.core.getX();
			long xMax = this.topR.getX();
			long yMax = this.core.getY();
			long yMin = this.botR.getY();
			
			return new Domain(xMin, xMax, yMin, yMax);
		}
		
		public Domain getSW()
		{
			long xMax = this.core.getX();
			long xMin = this.botL.getX();
			long yMax = this.core.getY();
			long yMin = this.botR.getY();
			
			return new Domain(xMin, xMax, yMin, yMax);
		}
		
		public boolean hasIntersectRegion(long xMin, long xMax, 
				                              long yMin, long yMax) 
		{
			long xLo = this.xMin;
			long xHi = this.xMax;
			long yLo = this.yMin;
			long yHi = this.yMin;
			if (xMin > xLo)
			{
				xLo = xMin;
			}
			if (xMax < xHi)
			{
				xHi = xMax;
			}
			if (yMin > yLo)
			{
				xLo = xMin;
			}
			if (yMax < yHi)
			{
				xHi = xMax;
			}
			
			if( (yMax - yMin < 0) || (xMax - xMin < 0))
			{
				return false;
			}
			return true;
		}
		
		public String toString()
		{
			return ('{' + botL.toString() + "-" + topR.toString() + '}');
		}
		
		public Coordinate getCore()
		{
			return this.core;
		}

	}