from sector_tree import Sector_Tree

def test_insert():
	s = Sector_Tree([(-100, -100), (100, 100)], 0)

	s.insert(50, 50)
	s.insert(25, 25)
	s.insert(75, 75)
	s.insert(25, 75)
	s.insert(75, 25)
	#s.insert(12.5,12.5)

	s.insert(-50, 50)
	s.insert(-50, -50)
	s.insert(50, -50)

def test_print():
	s = Sector_Tree([(-1024, -1024), (1024, 1024)], 1)

	s.insert( 512,  512)
	s.insert(-512,  512)
	s.insert( 512, -512)
	s.insert(-512, -512)

	s.insert(-256, -256)
	s.insert(-128, -128)
	s.insert(-64 , -64 )
	s.insert(-32 , -32 )

	s.insert(256, 256)
	s.insert(128, 128)
	s.insert(64 , 64 )
	s.insert(32 , 32 )

	print (str(s) + "\n\n" + ("=" * 80) + "\n")

	s = Sector_Tree([(-1024, -1024), (1024, 1024)], 2)

	s.insert( 512,  512)
	s.insert(-512,  512)
	s.insert( 512, -512)
	s.insert(-512, -512)

	s.insert(-256, -256)
	s.insert(-128, -128)
	s.insert(-64 , -64 )
	s.insert(-32 , -32 )

	s.insert(256, 256)
	s.insert(128, 128)
	s.insert(64 , 64 )
	s.insert(32 , 32 )

	print (s)

test_insert()
test_print()