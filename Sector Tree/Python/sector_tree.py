from sector_components import *
from domain            import Domain

class Sector_Tree(object):
	DEFAULT_BUCKET_SIZE = 1

	# CONSTRUCTOR
	# - bounds:       domain/range of 2D world
	# - bucket_size:  capacity of quad tree leaves (determines relative depth)
	# - store_domain: do the internal nodes store their coordinates?
	#                   (storage increases memory overhead, but faster navigation)
	# - collapse:     whether to collapse branches with empty content
	def __init__( self                                    , 
		          bounds = [(-1024, -1024), (1024, 1024)] ,
		          bucket_size  = 1                        ,
		          store_domain = True                     ):
		self.bounds = Domain(bounds[0], bounds[1])

		self.BUCKET_SIZE = self.DEFAULT_BUCKET_SIZE
		if (bucket_size > 0):
			self.BUCKET_SIZE = bucket_size

		self.root = None

	def __str__(self):
		s = "TREE:{ " + str(self.bounds) + " }\n"
		s += "root -> "

		if self.root != None:
			s += self.root.print_tree("")

		return s

	def insert(self, x = 0, y = 0, data = None):
		obj = Sector_Object(x, y, data)

		if (self.root == None):
			self.root = Sector_Leaf()

		self.root = self.root.insert(obj, self.bounds, self.BUCKET_SIZE)