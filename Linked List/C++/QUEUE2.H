template<class gtype>

struct Queue
{
	private:
		struct Node
		{
			gtype Data;
			//int Data;
			Node*Next;
		};

		Node*Top;
		Node*Bottom;

	public:
		Queue();
		Node*GetTop(){return(Top);}
		Node*GetBottom(){return(Bottom);}
		void Clear(){Bottom=NULL;Top=NULL;Top->Next=Bottom;}
		int EmptyQueue(){return(Bottom==NULL);}
		void Print(Node*Temp);
		void Push(gtype X);
	 //	int Pop();
		gtype Pop();
};

Queue::Queue()
{
	Bottom=NULL;
	Top=Bottom;
	Top->Next=Bottom;
}

void Queue::Print(Node*Temp)
{
	if(Temp)
	{
		Print(Temp->Next);
		cout<<Temp->Data<<" ";
	}
}

void Queue::Push(gtype X)
{
	if(Bottom==NULL)
	{
		Bottom=new Node;
		Bottom->Data=X;
		Bottom->Next=NULL;
		Top=Bottom;
	}
	else
	{
		Node*Temp=new Node;
		Temp->Data=X;
		Temp->Next=Top;
		Top=Temp;
	}
}

int Queue::Pop()
{
	gtype X=Bottom->Data;

	if(Top->Next)
	{
		Node*Temp=Top;
		Node*T=Bottom;

		while(Temp->Next!=Bottom)
		{
			Temp=Temp->Next;
		}

		Bottom=Temp;
		Bottom->Next=NULL;
		delete T;
	}
	else if(Top==Bottom)
	{
		Bottom=NULL;
		Top=Bottom;
		Top->Next=Bottom;
	}
	return(X);
}