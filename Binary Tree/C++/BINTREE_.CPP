//Program BINTREE_DNH by Dirk Hortensius

#include<iostream.h>
#include<conio.h>
#include<iomanip.h>
#include<stdlib.h>


class Binary
{
	private:
		//individual node
		struct node
		{
			int data;
			node*left;
			node*right;
			node(){data=0;left=NULL;right=NULL;}
		};

		int array[10];

		node*root;     //base node

		void input();
		void output();
		void print1(node*temp);
		void print2(node*temp,int x,int y);


	public:
		Binary(){root=NULL;}
		void execute();
};


void Binary::input()
{
	node*temp;
	node*prev;

	int c;

	clrscr();

	cout<<"1:Automatic\n2:Manual\n\n";
	cin>>c;

	if(c==1)
	{
		for(int x=0;x<10;x++)
		{
			array[x]=random(10);
		}
	}
	else
	{
		clrscr();
		for(int x=0;x<10;x++)
		{
			cin>>array[x];
		}
	}

	for(int x=0;x<10;x++)
	{
		temp=root;
		prev=temp;

		if(!root)
		{
			root=new node;
			root->data=array[x];

			temp=root;
			prev=temp;
		}
		else
		{
			while(temp)
			{
				if(array[x]<=temp->data)
				{
					prev=temp;
					temp=temp->left;
				}
				else if(array[x]>temp->data)
				{
					prev=temp;
					temp=temp->right;
				}
			}

			temp=new node;
			temp->data=array[x];

			if(temp->data<=prev->data)
			{
				prev->left=temp;
			}
			else
			{
				prev->right=temp;
			}

		}
	}
}

void Binary::output()
{
	clrscr();

	for(int x=0;x<10;x++)
	{
		cout<<array[x]<<" ";
	}
	cout<<endl<<endl;


	gotoxy(1,3);
	print1(root);

	print2(root,40,1);

	getch();
}

void Binary::print1(node*temp)
{
	if(temp)
	{
		print1(temp->left);

		cout<<temp->data<<" ";

		print1(temp->right);
	}
}

void Binary::print2(node*temp,int x,int y)
{
	if(temp)
	{
		if(y==1)
		{
			print2(temp->left,25,y+1);

			gotoxy(x,(y*2)+5);
			cout<<temp->data;

			print2(temp->right,55,y+1);
		}

		else
		{
		print2(temp->left,x-(10-(1.7*y)),y+1);

		gotoxy(x,(y*2)+5);
		cout<<temp->data;

		print2(temp->right,x+(10-(1.7*y)),y+1);
		}
	}
}

void Binary::execute()
{
	input();

	output();
}

void main()
{
	randomize();

	Binary b;
	b.execute();
}