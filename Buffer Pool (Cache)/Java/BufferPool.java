public class BufferPool <T>
{
	private Queue 	pool;
	private final int				CAPACITY = 20;
	
	public BufferPool()
	{
		this.pool = new Queue();
	}
	
	public boolean store(T node)
	{
		if (this.pool.contains(node.offset))
		{
			return false;
		}
		
		this.pool.push(node);
		if (pool.size() > CAPACITY)
		{
			pool.pop();
		}
		return true;
	}
	
	public T get(int offset)
	{
		T temp =  pool.remove(offset);
		if (temp != null)
		{
			this.store(temp);
		}
		return temp;
	}
	
	public String toString()
	{
		String ret = "MRU\n";
		for (T r : this.pool)		
		{
			ret += " " + r.toString() + "\n";
		}
		ret += "LRU\n";
		return ret;
	}
	
	public int getSize()
	{
		return this.pool.size();
	}
	
}
