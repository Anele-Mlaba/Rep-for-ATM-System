package za.co.standardbank.atm.util;

public interface FieldFactory {
	public String getType();
	Object getValue(Object t) throws IllegalArgumentException, IllegalAccessException;
}
