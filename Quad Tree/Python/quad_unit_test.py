from quad_tree import Quad_Tree

def test_insert():
	q = Quad_Tree([(-100, -100), (100, 100)], 0)

	q.insert(50, 50)
	q.insert(25, 25)
	q.insert(75, 75)
	q.insert(25, 75)
	q.insert(75, 25)
	#q.insert(12.5,12.5)

	q.insert(-50, 50)
	q.insert(-50, -50)
	q.insert(50, -50)

def test_print():
	q = Quad_Tree([(-1024, -1024), (1024, 1024)], 1)

	q.insert( 512,  512)
	q.insert(-512,  512)
	q.insert( 512, -512)
	q.insert(-512, -512)

	q.insert(-256, -256)
	q.insert(-128, -128)
	q.insert(-64 , -64 )
	q.insert(-32 , -32 )

	q.insert(256, 256)
	q.insert(128, 128)
	q.insert(64 , 64 )
	q.insert(32 , 32 )

	print (str(q) + "\n\n" + ("=" * 80) + "\n")

	q = Quad_Tree([(-1024, -1024), (1024, 1024)], 2)

	q.insert( 512,  512)
	q.insert(-512,  512)
	q.insert( 512, -512)
	q.insert(-512, -512)

	q.insert(-256, -256)
	q.insert(-128, -128)
	q.insert(-64 , -64 )
	q.insert(-32 , -32 )

	q.insert(256, 256)
	q.insert(128, 128)
	q.insert(64 , 64 )
	q.insert(32 , 32 )

	print (q)

test_insert()
test_print()