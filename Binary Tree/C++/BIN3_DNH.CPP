//Program BinTree3_DNH by Dirk Hortensius

#include<iostream.h>
#include<conio.h>
#include<iomanip.h>

class Binary
{
	private:
		//individual node
		struct node
		{
			int data;                        //number stored
			node*left;                       //accesses smaller nodes
			node*right;                      //accesses larger nodes
			node(){data=0;left=NULL;right=NULL;}  //sets all vals to NULL
		};

		int array[9];  //unsorted vals

		node*root;     //base node

		void insert(int x);                //inserts values in tree
		void remove(int x);                //removes values from tree
		void output();                     //outputs to user
		void print1(node*temp);            //outputs string of values
		void print2(node*temp,int x,int y);//outputs organized tree


	public:
		Binary(){root=NULL;}               //constructor
		void execute();                    //master function
};

//inserts value into tree
void Binary::insert(int x)
{
	//if root is NULL (blank tree)
	if(!root)
	{
		//sets root
		root=new node;
		root->data=x;
	}
	else
	{
		node*temp=root;
		node*prev=temp;

		//cycles through tree searching for location
		while(temp)
		{
			if(x<=temp->data)
			{
				prev=temp;
				temp=temp->left;
			}
			else if(x>temp->data)
			{
				prev=temp;
				temp=temp->right;
			}
		}

		//sets node
		temp=new node;
		temp->data=x;

		//connects to tree
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

//removes nodes from tree
void Binary::remove(int x)
{
	int l=0;                             //tracks if number in tree
	node*t;                              //temporary node for deletion

	//if root to be deleted
	if(x==root->data)
	{
		t=root;                            //deletes soon to be null value

		//no children
		if(!root->left&&!root->right)
		{
			root=NULL;
		}
		//one child (left side)
		else if(root->left&&!root->right)
		{
			root=root->left;
		}
		//one child (right side)
		else if(!root->left&&root->right)
		{
			root=root->right;
		}
		//two children
		else if(root->left&&root->right)
		{
			node*t2=root->right;             //temp variable for finding node

			//if right node has left
			if(t2->left)
			{
				//finds leftmost
				while(t2->left->left)
				{
					t2=t2->left;
				}

				node*t3=t2->left;              //temp var for moving node

				t2->left=t2->left->right;      //sets as NULL if no right

				//moves node
				t3->left=root->left;
				t3->right=root->right;
				root=t3;
			}
			else
			{
				//sets right as root
				t2=root->left;
				root=root->right;
				root->left=t2;
			}
		}

		delete(t);                         //deletes stray node
		l++;                               //tracks deleted val
	}

	//same concept throughout tree, finds if val exists in tree
	else
	{
		node*temp=root;

		while(temp)
		{
			if(x<temp->data)
			{
				if(temp->left->data==x)
				{
					t=temp->left;

					if(!temp->left->left&&!temp->left->right)
					{
						temp->left=NULL;
					}
					else if(!temp->left->left&&temp->left->right)
					{
						temp->left=temp->left->right;
					}
					else if(temp->left->left&&!temp->left->right)
					{
						temp->left=temp->left->left;
					}
					else if(temp->left->left&&temp->left->right)
					{
						node*t2=temp->left->right;

						if(t2->left)
						{
							while(t2->left->left)
							{
								t2=t2->left;
							}
							node*t3=t2->left;

							t2->left=t2->left->right;

							t3->left=temp->left->left;
							t3->right=temp->left->right;
							temp->left=t3;
						}
						else
						{
							t2=temp->left->left;
							temp->left=temp->left->right;
							temp->left->left=t2;
						}
					}

					delete(t);
					l++;
					temp=NULL;
				}
				else
				{
					temp=temp->left;
				}
			}
			else if(x>temp->data)
			{
				if(temp->right->data==x)
				{
					t=temp->right;

					if(!temp->right->left&&!temp->right->right)
					{
						temp->right=NULL;
					}
					else if(!temp->right->left&&temp->right->right)
					{
						temp->right=temp->right->right;
					}
					else if(temp->right->left&&!temp->right->right)
					{
						temp->right=temp->right->left;
					}
					else if(temp->right->left&&temp->right->right)
					{
						node*t2=temp->right->right;

						if(t2->left)
						{
							while(t2->left->left)
							{
								t2=t2->left;
							}
							node*t3=t2->left;

							t2->left=t2->left->right;

							t3->left=temp->right->left;
							t3->right=temp->right->right;
							temp->right=t3;
						}
						else
						{
							t2=temp->right->left;
							temp->right=temp->right->right;
							temp->right->left=t2;
						}
					}

					delete(t);
					l++;
					temp=NULL;
				}
				else
				{
					temp=temp->right;
				}
			}
		}
	}

	if(l==0)
	{
		clrscr();
		cout<<"Not in List\n";
		getch();
	}
}

void Binary::output()
{
	clrscr();

	gotoxy(52,3);
	cout<<"Sorted: ";
	print1(root);

	print2(root,40,1);
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
	clrscr();

	int d;

	array[0]=5;
	array[1]=8;
	array[2]=6;

	array[3]=7;

	array[4]=4;
	array[5]=8;
	array[6]=2;
	array[7]=9;
	array[8]=1;

	for(int x=0;x<4;x++)
	{
		insert(array[x]);
	}

	output();

	gotoxy(50,1);
	cout<<"Unsorted: ";
	for(x=0;x<4;x++)
	{
		cout<<array[x]<<" ";
	}

	gotoxy(1,1);
	cout<<"Enter Number: ";
	cin>>d;

	remove(d);

	output();

	gotoxy(50,1);
	cout<<"Unsorted: ";
	for(x=0;x<4;x++)
	{
		cout<<array[x]<<" ";
	}

	getch();
}



void main()
{
	Binary B;
	B.execute();
}

