package za.co.standardbank.atm.util;

import java.lang.reflect.Field;

import za.co.standardbank.atm.annotations.PrimaryKey;

public class PrimaryKeyField implements FieldFactory
{
	
	Field field;
	public PrimaryKeyField(Field field)
	{
		this.field = field;
		field.setAccessible(true);
	}
	
	public String getName()
	{
		return field.getAnnotation(PrimaryKey.class).name();
	}
	
	public Object getValue(Object t) throws IllegalArgumentException, IllegalAccessException
	{
		return field.get(t);
	}

	@Override
	public String getType() {
		return field.getType().getSimpleName();
	}	
	
	public void set(Object obj, Object value)
	{
		try {
			field.set(obj, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object get(Object obj)
	{
		try {
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
