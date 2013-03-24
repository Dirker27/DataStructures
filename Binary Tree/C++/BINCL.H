//Binary_Class.H by Dirk Hortensius

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
			void set(int d){data=d;}
		};

		node*root;     //base node
		node*getRoot(){return(root);}
		node*getLeft(node*n){return(n->left);}
		node*getRight(node*n){return(n->right);}

		void input();
		void output();
		void setLeft(node*n,int d){n->left=new node();}
		void setRight(node*n,int d){n->right=new node();}


	public:
		Binary(){root=NULL;}
		void execute();

}