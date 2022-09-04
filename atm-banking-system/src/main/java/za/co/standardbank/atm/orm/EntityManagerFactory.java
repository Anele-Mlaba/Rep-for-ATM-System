package za.co.standardbank.atm.orm;

import java.util.List;

public interface EntityManagerFactory 
{
	
	public static EntityManager of(Class<?> clss)
	{
		return new EntityManager(clss);
	}
	
	void persist(Object t);
	Object read (String PrimaryKey);
	void update(Object t);
	public List<Object> readForeign(Object foreignObj);
}
