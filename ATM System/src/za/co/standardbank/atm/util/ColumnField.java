package za.co.standardbank.atm.util;

import java.lang.reflect.Field;

import za.co.standardbank.atm.annotations.Column;

public class ColumnField implements FieldFactory {
	Field field;
	public ColumnField(Field field)
	{
		this.field  = field;
		field.setAccessible(true);
	}
	
	public String getName()
	{
		return field.getAnnotation(Column.class).name();
	}
	
	public Object getValue(Object o) throws IllegalArgumentException, IllegalAccessException
	{
		return field.get(o);
	}	
	
	public String getType()
	{
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
}
