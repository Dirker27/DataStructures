from comparable_2D import Comparable_2D

class Domain(Comparable_2D):

	def __init__(self, low_bound = (0, 0), high_bound = (0, 0)):
		Comparable_2D.__init__( self, 
			                    (low_bound[0] + high_bound[0]) / 2 ,
			                    (low_bound[1] + high_bound[1]) / 2 )

		self.t_l  = Comparable_2D( low_bound[0], high_bound[1])
		self.t_r  = Comparable_2D(high_bound[0], high_bound[1])
		self.b_l  = Comparable_2D( low_bound[0],  low_bound[1])
		self.b_r  = Comparable_2D(high_bound[1],  low_bound[1])

		self.core = Comparable_2D( (low_bound[0] + high_bound[0]) / 2 ,
			                       (low_bound[1] + high_bound[1]) / 2 )

		# Switch-statement emulation
		def ne():
			return Domain( (self.core.x, self.core.y) , 
				           (self.t_r.x , self.t_r.y ) )
		def nw():
			return Domain( (self.core.x, self.core.y) , 
				           (self.t_l.x , self.t_l.y ) )
		def sw():
			return Domain( (self.b_l.x , self.b_l.y)  , 
				           (self.core.x, self.core.y) )
		def se():
			return Domain( (self.b_r.x , self.b_r.y)  , 
				           (self.core.x, self.core.y) )

		self.quad_switch = { Comparable_2D.NORTHEAST : ne ,
			                 Comparable_2D.NORTHWEST : nw ,
			                 Comparable_2D.SOUTHWEST : sw ,
			                 Comparable_2D.SOUTHEAST : se }



	def __str__(self):
		return "DOMAIN:(" + str(self.b_l) + ", " + str(self.t_r) + ")"

	def get_quadrant(self, quadrant):
		return self.quad_switch[quadrant]()

	'''def ne(self):
		return Domain( (self.core.x, self.core.y) , 
			           (self.t_r.x , self.t_r.y ) )
	def nw(self):
		return Domain( (self.core.x, self.core.y) , 
			           (self.t_l.x , self.t_l.y ) )
	def sw(self):
		return Domain( (self.b_l.x , self.b_l.y)  , 
			           (self.core.x, self.core.y) )
	def se(self):
		return Domain( (self.b_r.x , self.b_r.y)  , 
			           (self.core.x, self.core.y) )'''