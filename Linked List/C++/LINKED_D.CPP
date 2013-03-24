//Progran Linked by Dirk Hortensius
//Creates a linked list w/pointers
//allows for inserting, sorting, and deleting int

#include<iostream.h>
#include<conio.h>
#include<stdlib.h>
#include<ctype.h>

class Node
{
	private:
		int Data;
		Node *Next;

	public:
		Node(int D, Node *N);
		int GetData(){return(Data);};
		Node* GetNext(){return(Next);};
		void SetNext(Node *N){Next=N;};
};

Node::Node(int D, Node *N)
{
	Data=D;
	Next=N;
}


class List
{
	private:
		Node *First;

	public:
		List(){First=NULL;};
		void Print();
		void Insert(int X);
		void Delete(int X);
		void Menu();
};

void List::Print()
{
	Node *Temp=First;

	while(Temp)                          //while Temp!=NULL
	{
		cout<<Temp->GetData()<<" ";        //outputs data
		Temp=Temp->GetNext();              //Temp gets next box
	}
}

void List::Insert(int X)
{
	if(!First)                           //if First=NULL
	{
		First=new Node(X,NULL);            //creates new node
	}
	else
	{
		Node *Temp=First->GetNext();
		Node *Prev=First;
		Node *Swap=First;

		if(X<First->GetData())
		{
			Swap=First;
			First=new Node(X,Swap);
			X=Swap->GetData();
		}
		else
		{
			while(Temp)
			{
				if(X<Temp->GetData())
				{
					Swap=Temp;
					Temp=new Node(X,Swap->GetNext());
					X=Swap->GetData();
					Prev->SetNext(Temp);
				}
				else
				{
					Prev=Temp;
					Temp=Temp->GetNext();
				}
			}
			Temp=new Node(X,NULL);
			Prev->SetNext(Temp);
		}
	}
}

void List::Delete(int X)
{
	Node *Temp=First->GetNext();
	Node *Prev=First;
	int Y=0;

	if(X==First->GetData())
	{
		delete First;
		First=Temp;
	}
	else
	{
		while(Temp)
		{
			if(X==Temp->GetData())
			{
				if(Y==0)
				{
					Prev->SetNext(Temp->GetNext());
					delete Temp;
					Y++;
				}
			}
			Prev=Temp;
			Temp=Temp->GetNext();
		}
	}
}

void List::Menu()
{
	int Q=0;
	int X=0;
	char C='0';

	while(Q==0)
	{
		clrscr();
		cout<<"Main Menu"<<endl;
		cout<<"---------"<<endl;
		cout<<"I: Insert"<<endl;
		cout<<"D: Delete"<<endl;
		cout<<"P: Print"<<endl;
		cout<<"Q: Quit"<<endl<<endl;
		cin>>C;
		cout<<endl;

		if(C=='I'||C=='i')
		{
			while(C!='N'&&C!='n')
			{
				clrscr();
				Print();

				cout<<endl<<endl<<"Enter Number: ";
				cin>>X;

				Insert(X);

				cout<<endl<<"Continue? (Y/N)"<<endl;
				cin>>C;
			}
		}

		if(C=='D'||C=='d')
		{
			while(C!='N'&&C!='n')
			{
				clrscr();
				Print();

				cout<<endl<<endl<<"Enter Number: ";
				cin>>X;

				Delete(X);

				cout<<endl<<"Continue? (Y/N)"<<endl;
				cin>>C;
			}
		}

		if(C=='P'||C=='p')
		{
			Print();
			getch();
		}

		if(C=='Q'||C=='q')
		{
			Q++;
		}
	}
}


void main()
{
	List L;
	L.Menu();
}