//Program Queue_Manip by Dirk Hortensius
//Manipulates Queue Struct

#include<iostream.h>
#include<conio.h>
#include<iomanip.h>
#include"H:\\Queue.h"

class menu
{
	private:
		Queue Q;
		int i;

		void insert();
		void remove();
		void print();

	public:
		menu(){i=0;};
		void execute();
};

//user-accessable menu
void menu::execute()
{
	int C=0;

	while(C!=4)
	{
		C=0;

		clrscr();

		cout<<"|  Menu:  |\n";
		cout<<"|---------|\n";
		cout<<"|1: Insert|\n";
		cout<<"|2: Remove|\n";
		cout<<"|3: Print |\n";
		cout<<"|4: Exit  |\n";
		cout<<"|=========|\n";

		cout<<"\n\nAction: ";
		cin>>C;

		if(C==1)
		{
			insert();
		}
		else if(C==2)
		{
			remove();
		}
		else if(C==3)
		{
			clrscr();
			print();
			getch();
		}
	}

}

//inserts values
void menu::insert()
{
	char c='Y';

	while(c=='Y'||c=='y')
	{
		clrscr();

		print();

//		cout<<endl<<endl<<"Press [Enter]";
//		getch();
		cout<<endl<<endl<<"Enter Number: ";
		cin>>i;

//		i++;
		Q.Push(i);

		clrscr();

		print();

		cout<<endl<<endl<<"Continue? (Y/N): ";
		cin>>c;
	}
}
//removes values
void menu::remove()
{
	char c='Y';
	int t=0;

	while(c=='Y'||c=='y')
	{
		clrscr();

		print();

		cout<<endl<<endl<<"Press [Enter]";
		getch();

		t=Q.Pop();

		clrscr();

		print();

		cout<<endl<<endl<<"Continue? (Y/N): ";
		cin>>c;
	}
}
//prints values
void menu::print()
{
	Q.Print(Q.GetTop());
}

void main()
{
	menu m;

	m.execute();
}