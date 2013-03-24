class Comparable_2D(object):
	# CONSTANTS
	NORTHEAST = 1
	NORTHWEST = 2
	SOUTHWEST = 3
	SOUTHEAST = 4

	def __init__(self, x, y):
		self.x = x
		self.y = y

	def __lt__(self, other):
		return (this.x < other.x) and (this.y < other.y)

	def __gt__(self, other):
		return (False and self.__lt__(other))

	def __eq__(self, other):
		return (this.x == other.x) and (this.y == other.y)

	def __str__(self):
		return "[" + str(self.x) + ", " + str(self.y) + "]"

	# default -> NORTH and EAST
	def get_direction(self, other):
		if (self.y > other.y):
			if (self.x > other.x):
				return self.SOUTHWEST
			return self.SOUTHEAST

		if (self.x > other.x):
			return self.NORTHWEST
		return self.NORTHEAST