import java.util.Vector;

/**
 * HashStorage.GISHashTable.java
 * GISHashTable
 * 
 * @author Dirk N. Hortensius
 * @version Dec 2, 2012
 *
 */
public class HashTable
{
	private HashNode			table[];
	private Vector<Integer>	primes;
	private int 						occupied;
	private int							capacity;
	private int							longestProbe;
	
	private final int			INIT_SIZE = 1019;   //1019
	private final double 	PERC_CAP  = 0.7;
	private final double  PERC_INC  = 1.5;
	
	public HashTable()
	{
		this.longestProbe = 0;
		this.primes 	= new Vector<Integer>();
		this.capacity = this.INIT_SIZE;
		this.table 		= new GISHashNode[this.INIT_SIZE];
		for (int i = 0; i < this.capacity; i++)
		{
			this.table[i] = null;
		}
		
		///*
		int x = 2;
		while(x < INIT_SIZE) 
		{
			x++;
			x = this.nextPrime(2);
		}//*/
	}
	
	private int elfHash(String str)
	{
		int hash = 0;
		for (int i = 0; i < str.length(); i++)
		{
			hash = (hash << 4) + str.charAt(i);
			int nib = hash & 0xF0000000;
			if (nib != 0)
			{
				hash ^= nib >> 24;
			}
			hash &= ~nib;
		}
		
		return hash;
	}
	
	private boolean isPrime(int x)
	{
		if (this.primes.size() == 0)
		{
			return true;
		}
		for (int i : this.primes)
		{
			if (x % i == 0)
			{
				return false;
			}
		}
		return true;
	}
	private int nextPrime(int x)
	{
		while (! isPrime(x))
		{
			x++;
		}
		this.primes.add(x);
		return x;
	}
	
	private void reSize()
	{
		int target = (int)(this.capacity * this.PERC_INC);
		while (this.capacity < target)
		{
			this.capacity = this.nextPrime(this.capacity);
		}
		GISHashNode old[] = this.table;
		this.table 				= new GISHashNode[this.capacity];
		this.occupied     = 0;
		
		for (GISHashNode r : old)
		{
			if (r != null && !r.tombstone)
			{	
				this.insert(r);
			}
		}
	}
	
	public boolean insert(HashNode r)
	{		
		// Size Assertion
		if (this.occupied >= (int)(this.PERC_CAP * this.capacity))
		{
			this.reSize();	
		}
		
		// Initial Hash index
		String tmp 	= r.featureName + r.stateAlphaCode;
		int idx 		= elfHash(tmp) % capacity;
		
		// Quadratic Probe
		///*
		Vector<Integer> visited = new Vector<Integer>();
		int step 	= 1;
		int count = 0;
		int probe = 0;
		while (this.table[idx] != null && !this.table[idx].tombstone)
		{
			probe ++;
			if (visited.contains(idx))
			{
				count ++;
				if (count == 2)
				{
					return false;
				}
			}
			else 
			{
				visited.add(idx);
				count = 0;
			}
			idx = (step + idx) % this.capacity;
			step = ((((int)Math.pow(step, 2)) + step) / 2) % this.capacity;
		}//*/
		
		table[idx] 	=	r;
		this.occupied++;
		
		if (probe > this.longestProbe)
		{
			this.longestProbe = probe;
		}
		
		return true;
	}
	
	public boolean remove(String name, String state)
	{		
		// Initial Hash index
		String tmp 	= name + state;
		int idx 		= elfHash(tmp) % capacity;
		if (this.table[idx] == null)
		{
			return false;
		}
		
		// Quadratic Probe
		Vector<Integer> visited = new Vector<Integer>();
		visited.add(idx);
		int step 	= 1;
		int count = 0;
		while (! (this.table[idx].featureName.equals(name)     && 
						 (this.table[idx].stateAlphaCode.equals(state)) ) )
		{
			if (visited.contains(idx))
			{
				count ++;
				if (count == 2)
				{
					return false;
				}
			}
			else 
			{
				visited.add(idx);
				count = 0;
			}
			idx = (step + idx) % this.capacity;
			step = ((((int)Math.pow(step, 2)) + step) / 2) % this.capacity;
			if (table[idx] == null)
			{
				return false;
			}
		}
		
		table[idx].tombstone 			= true;
		table[idx].offset 				= 0;
		table[idx].featureName 		= null;
		table[idx].stateAlphaCode = null;
		this.occupied--;
		
		return true;
	}
	
	public HashNode find(String name, String state)
	{		
		// Initial Hash index
		String tmp 	= name + state;
		int idx 		= elfHash(tmp) % capacity;
		if (this.table[idx] == null)
		{
			return null;
		}
		
		// Quadratic Probe
		Vector<Integer> visited = new Vector<Integer>();
		visited.add(idx);
		int step 	= 1;
		int count = 0;
		while (! this.table[idx].tombstone                    && 
					!(this.table[idx].featureName.equals(name)      && 
						 (this.table[idx].stateAlphaCode.equals(state)) ) )
		{
			if (visited.contains(idx))
			{
				count ++;
				if (count == 2)
				{
					return null;
				}
			}
			else 
			{
				visited.add(idx);
				count = 0;
			}
			idx = (step + idx) % this.capacity;
			step = ((((int)Math.pow(step, 2)) + step) / 2) % this.capacity;
			if (this.table[idx] == null)
			{
				return null;
			}
		}
		return this.table[idx];
	}
	
	public String printTable()
	{
		String ret = "";
		for (int i = 0; i < this.capacity; i++)
		{
			if (this.table[i] != null)
			{
				ret += i + ":  " + "[" + table[i].featureName + ":" + 
			         table[i].stateAlphaCode + ", [" + table[i].offset + "]]\n";
			}
		}
		return ret;
	}
	
	public int getLongestProbe()
	{
		return this.longestProbe;
	}
	public int getCurrentCapacity()
	{
		return this.capacity;
	}
	public int getCurrentOccupation()
	{
		return this.occupied;
	}
}
