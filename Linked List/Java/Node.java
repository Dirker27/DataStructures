package BufferStorage;

import main.GISRecord;

public final class Node
{
	private GISRecord data;
	private Node next;
	
	public Node(GISRecord data)
	{
		this.setData(data);
		this.setNext(null);
	}
	
	public GISRecord getData()
	{
		return data;
	}
	public void setData(GISRecord data)
	{
		this.data = data;
	}
	public Node getNext()
	{
		return next;
	}
	public void setNext(Node next)
	{
		this.next = next;
	}

}