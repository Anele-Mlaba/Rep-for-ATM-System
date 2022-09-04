package za.co.standardbank.atm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import za.co.standardbank.atm.annotations.Column;
import za.co.standardbank.atm.annotations.PrimaryKey;
import za.co.standardbank.atm.annotations.TableName;

public class MetaModel {
	
	private Class<?> clss;
	
	public MetaModel(Class<?> clss)
	{
		this.clss = clss;
	}
	
	public static MetaModel of(Class<?> clss)
	{
		return new MetaModel(clss);
	}
	
	public PrimaryKeyField getPrimaryKey()
	{
		Field[] fields = clss.getDeclaredFields();
		PrimaryKeyField primaryKeyField = null;
		
		for (Field field: fields)
		{
			PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
			
			if(primaryKey != null)
			{
				primaryKeyField = new PrimaryKeyField(field);
				break;
			}
		}
		
		return primaryKeyField;
	}
	
	public List<ColumnField> getColumns()
	{
		List<ColumnField> columnFields = new ArrayList<>();
		Field[] fields = clss.getDeclaredFields();
		
		for (Field field: fields)
		{
			Column column = field.getAnnotation(Column.class);
			if(column != null)
			{
				columnFields.add(new ColumnField(field));
			}
		}
		
		
		return columnFields;
	}
	
	 public String buildInsertRequest(boolean isAutoIncr)
	 {
		 //INSERT INTO tableName(column, column...) VALUES(value1, value2...)
		  
		 String tableName = clss.getAnnotation(TableName.class).name();
		 String columnNames;
		 String questionMarks;
		 if(!isAutoIncr)
		 {
			 columnNames = this.getPrimaryKey().getName() +","+
					 this.getColumns()
			 				.stream()
			 				.map(columnField->columnField.getName())
			 				.collect(Collectors.joining(","));
			 
			 questionMarks = IntStream
					 .range(0, this.getColumns().size()+1)
					 .mapToObj(number->"?")
					 .collect(Collectors.joining(","));
			 
		 }
		 
		 else
		 {
			 columnNames = this.getColumns()
			 				.stream()
			 				.map(columnField->columnField.getName())
			 				.collect(Collectors.joining(","));
			 
			 questionMarks = IntStream
					 .range(0, this.getColumns().size())
					 .mapToObj(number->"?")
					 .collect(Collectors.joining(","));	 
		 }
		 
		 
		 
		 return "INSERT INTO "+tableName+"("+columnNames+")"+" VALUES("+questionMarks+")";
	 }
	 
	 public String buildReadRequest()
	 {
		 return "SELECT * FROM " + clss.getAnnotation(TableName.class).name() + 
				" WHERE ?";
	 }
	 
	 public String buildUpdateRequest()
	 {
		 String columnNames = this.getPrimaryKey().getName() +" = ? ,"+
				 this.getColumns()
		 				.stream()
		 				.map(columnField->columnField.getName())
		 				.collect(Collectors.joining(" = ? ,")) + " = ?";
		 
		 return  "UPDATE " + clss.getAnnotation(TableName.class).name() + 
				" SET " + columnNames +
				" WHERE " + this.getPrimaryKey().getName() + " = ?;";
	 }
	 
}
