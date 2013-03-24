public class HashNode<T>
{
	public	int			offset;
	
	public	String 	featureName;
	public	String	stateAlphaCode;
	
	public boolean tombstone;
	
	/**
	 * TESTING USE ONLY
	 *
	 */
	public HashNode(String fName, String state)
	{
		this.tombstone 			= false;
		this.featureName 		= fName;
		this.stateAlphaCode = state;
		this.offset         = 0;
	}
	public HashNode(T r)
	{
		this.tombstone 			= false;
		this.featureName 		= r.featureName;
		this.stateAlphaCode = r.stateAlphaCode;
		this.offset         = r.offset;
	}
}
