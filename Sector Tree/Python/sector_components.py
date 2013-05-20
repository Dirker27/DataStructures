from comparable_2D import Comparable_2D
from domain        import Domain

class Sector_Object(Comparable_2D):

	def __init__(self, x, y, data):
		Comparable_2D.__init__(self, x, y)
		self.data = data

	def __str__(self):
		s = Comparable_2D.__str__(self) + " - " + str(self.data)
		return s

	def __eq__(self, other):
		return Compare_2D.__eq__(self, other) and self.data.__eq__(other.data)


class Sector_Leaf(object):

	def __init__(self):
		self.bucket = []
		self.usage  = 0

		#self.insert( obj, Domain((0,0),(0,0)), self.BUCKET_SIZE )

	def __str__(self):
		s = "SECTOR:{ "
		for obj in self.bucket:
			s += str(obj)

		return s + " }"

	def print_tree(self, padding):
		return str(self)

	def insert(self, obj, bearings, bucket_size = 1):
		self.bucket.append(obj)
		self.usage += 1
		
		if (self.usage > bucket_size):
			branch = Sector_Branch()

			for i in range(0, self.usage):
				branch = branch.insert( self.bucket[i] ,
				                        bearings       ,
				                        bucket_size    )
			return branch

		return self


class Sector_Branch(object):

	def __init__(self):
		self.dir_child = { Comparable_2D.NORTHEAST : None ,
		                   Comparable_2D.NORTHWEST : None ,
		                   Comparable_2D.SOUTHWEST : None ,
		                   Comparable_2D.SOUTHEAST : None }

	def __str__(self):
		s = "BRANCH:"
		s += "\n\t--------------------------------------\ "
		s += "\n\t - <NE> " + str(self.dir_child[Comparable_2D.NORTHEAST])
		s += "\n\t - <NW> " + str(self.dir_child[Comparable_2D.NORTHWEST])
		s += "\n\t - <SW> " + str(self.dir_child[Comparable_2D.SOUTHWEST])
		s += "\n\t - <SE> " + str(self.dir_child[Comparable_2D.SOUTHEAST])
		s += "\n\t--------------------------------------/ "
		return s

	def print_tree(self, padding):
		padding += "\t|"

		s = "BRANCH:\n"
		s += padding + "--------------------------------------\ \n"

		s += padding + " - <NE> "
		if (self.dir_child[Comparable_2D.NORTHEAST] != None):
			s += self.dir_child[Comparable_2D.NORTHEAST].print_tree(padding)
		s += "\n"

		s += padding + " - <NW> " 
		if (self.dir_child[Comparable_2D.NORTHWEST] != None):
			s += self.dir_child[Comparable_2D.NORTHWEST].print_tree(padding)
		s += "\n"

		s += padding + " - <SW> " 
		if (self.dir_child[Comparable_2D.SOUTHWEST] != None):
			s += self.dir_child[Comparable_2D.SOUTHWEST].print_tree(padding)
		s += "\n"

		s += padding + " - <SE> " 
		if (self.dir_child[Comparable_2D.SOUTHEAST] != None):
			s += self.dir_child[Comparable_2D.SOUTHEAST].print_tree(padding)
		s += "\n"

		s += padding + "--------------------------------------/ "
		
		return s

	def insert(self, obj, bearings, bucket_size = 1):
		d = bearings.get_direction(obj)
		q = bearings.get_quadrant(d)

		if (self.dir_child[d] == None):
			self.dir_child[d] = Sector_Leaf()

		self.dir_child[d] = self.dir_child[d].insert(obj, q)

		return self
