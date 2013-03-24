package QuadStorage;

import java.util.Vector;

abstract class QuadNode
{
	String type;
	
	public abstract boolean   		 			isEmpty();
	public abstract QuadNode 						insert(GISQuadNode elem, Domain bearings);
	public abstract QuadNode 						delete(GISQuadNode elem, Domain bearings);
	public abstract GISQuadNode 				find(GISQuadNode elem, Domain bearings);
	public abstract Vector<Integer>     find(long x, long y, Domain bearings);
	public abstract Vector<Integer>     find(long xMin, long xMax, long yMin, long yMax, 
																					 Domain bearings);
	public abstract String     					toString(Domain bearings);
}  // </prQuadNode>

