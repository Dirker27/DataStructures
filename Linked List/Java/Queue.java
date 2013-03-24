package BufferStorage;

import main.GISRecord;

public class Queue
    extends Linked
{

    @Override
    public final boolean push(GISRecord t)
    {
        Node temp = new Node(t);
        temp.setNext(null);

        if (this.isEmpty())
        {
            this.setHead(temp);
            return true;
        }
        temp.setNext(this.getHead());
        this.setHead(temp);
        return true;
    }
}
