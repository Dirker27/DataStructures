package BufferStorage;

import java.util.Iterator;
import java.util.NoSuchElementException;

import main.GISRecord;

public abstract class Linked implements Iterable<GISRecord>
{
	private Node head;

	protected Linked()
	{
		this.setHead(null);
	}

	private class linkedIterator<E> implements Iterator<GISRecord>
	{
		private Node current;
		private Node prev;
		private boolean primed;

		public linkedIterator()
		{
			this.prev = new Node(null);
			this.prev.setNext((Node) getHead());
			this.current = this.prev;
			this.primed = false;
		}

		public boolean hasNext()
		{
			return (this.current.getNext() != null);
		}

		public GISRecord next()
		{
			if (hasNext())
			{
				this.prev = current;
				this.current = this.current.getNext();
				this.primed = true;

				return this.current.getData();
			}

			throw new NoSuchElementException();
		}

		public void remove()
		{
			if (this.primed)
			{
				if (this.current == getHead())
				{
					pop();
					this.prev = new Node(null);
					this.prev.setNext((Node) getHead());
				} else
				{
					Node temp = this.current;
					this.current.setNext(temp.getNext());
					temp.setNext(null);
				}
				this.current = this.prev;
				this.primed = false;
			} else
			{
				throw new IllegalStateException();
			}
		}
	}

	public abstract boolean push(GISRecord t);

	public final boolean pop()
	{
		if (! this.isEmpty())
		{
			Node prev = this.head;
			Node temp = prev.getNext();
			if (temp == null)
			{
				this.head = null;
				return true;
			}
			
			while (temp.getNext() != null)
			{
				prev = temp;
				temp = temp.getNext();
			}
			if (prev == this.head)
			{
				this.head.setNext(null);
				return true;
			}
			
			prev.setNext(null);
			return true;
		} 
		return false;
	}

	public final GISRecord top()
	{
		if (this.head == null)
		{
			return null;
		} else
		{
			return this.head.getData();
		}
	}

	public final GISRecord remove(int o)
	{
		if (!this.isEmpty())
		{
			if (this.head.getData().offset == o)
			{
				GISRecord temp = this.head.getData();
				this.head = this.head.getNext();
				return temp;
			}
			else
			{
				Node temp = this.head;
				Node prev = this.head;
				while (temp != null)
				{
					if (temp.getData().offset == o)
					{
						prev.setNext(temp.getNext());
						temp.setNext(null);
						return temp.getData();
					}
					prev = temp;
					temp = temp.getNext();
				}
				return null;
			}
		} else
		{
			return null;
		}
	}

	public final boolean isEmpty()
	{
		return (this.head == null);
	}

	public final boolean contains(int o)
	{
		Boolean b = false;
		Node temp = this.head;

		while (temp != null)
		{
			if (temp.getData().offset == o)
			{
				b = true;
				break;
			}
			temp = temp.getNext();
		}

		return b;
	}

	public final int size()
	{
		Node temp = this.head;
		int c = 0;
		while (temp != null)
		{
			temp = temp.getNext();
			c++;
		}

		return c;
	}

	public final Iterator<GISRecord> iterator()
	{
		return new linkedIterator<GISRecord>();
	}

	public final Node getHead()
	{
		return head;
	}

	public final void setHead(Node head)
	{
		this.head = head;
	}

}