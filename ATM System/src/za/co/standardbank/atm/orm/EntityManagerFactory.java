package za.co.standardbank.atm.orm;

public interface EntityManagerFactory 
{
	
	public static EntityManager of(Class<?> clss)
	{
		return new EntityManager(clss);
	}
	
	void persist(Object t);
	Object read (Class<?> clss, String PrimaryKey);
	
}
