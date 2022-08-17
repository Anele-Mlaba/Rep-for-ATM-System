package za.co.standardbank.atm.orm;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import za.co.standardbank.atm.util.ColumnField;
import za.co.standardbank.atm.util.FieldFactory;
import za.co.standardbank.atm.util.MetaModel;
import za.co.standardbank.atm.util.PrimaryKeyField;

public class EntityManager implements EntityManagerFactory {

	Class<?> clss;
	
	public EntityManager(Class<?> clss) 
	{
		this.clss = clss;
	}

	public void update(Object t)
	{
		MetaModel metaModel = MetaModel.of(t.getClass());
		
		List<FieldFactory> fields = metaModel.getColumns().stream().collect(Collectors.toList());
		fields.add(0, metaModel.getPrimaryKey());
		
		String sql = metaModel.buildUpdateRequest();
		
		for(FieldFactory fld: fields)
		{
			try 
			{
				if(fld.getType().toLowerCase().contains("string"))
					sql = smartReplaceFirst(sql, "'"+fld.getValue(t)+"'");
				else
					sql = smartReplaceFirst(sql, fld.getValue(t)+"");
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		sql = smartReplaceFirst(sql, metaModel.getPrimaryKey().get(t)+"");
		
		String connectionString = "jdbc:mysql://localhost:3306/standardbankatmdb";
		String userName = "root";
		String password = "0633386@NE|_e";
		
		try(	Connection connection = DriverManager.getConnection(connectionString, userName, password);
				Statement statement = connection.createStatement();)
		{
			statement.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void persist(Object t) 
	{
		
		MetaModel metaModel = MetaModel.of(t.getClass());
		String sql;
		
		try
		{
			if((int)metaModel.getPrimaryKey().get(t) == 0)
				sql = metaModel.buildInsertRequest(true);
			else
				sql = metaModel.buildInsertRequest(false);
		}
		catch(ClassCastException clssCastExc)
		{
			sql = metaModel.buildInsertRequest(false);
		}
		
		System.out.println(sql);
		
		String connectionString = "jdbc:mysql://localhost:3306/standardbankatmdb";
		String userName = "root";
		String password = "0633386@NE|_e";
		
		try(	Connection connection = DriverManager.getConnection(connectionString, userName, password);
				Statement statement = connection.createStatement();)
		{
			
			List<FieldFactory> fields =  metaModel.getColumns()
					.stream()
					.map(columnField -> columnField)
					.collect(Collectors.toList());
			
			try
			{
				if(!((int)metaModel.getPrimaryKey().get(t) == 0))
					fields.add(0, metaModel.getPrimaryKey());
			}
			catch(ClassCastException clssCastExc)
			{
				fields.add(0, metaModel.getPrimaryKey());
			}
			
			
			for(FieldFactory fld: fields)
			{
				if(fld.getType().toLowerCase().contains("string"))
					sql = smartReplaceFirst(sql, "'"+fld.getValue(t)+"'");
				else
					sql = smartReplaceFirst(sql, fld.getValue(t)+"");		
			}
			System.out.println(sql);
			statement.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	@Override
	public Object read(Class<?> clss, String primaryKey) 
	{
		MetaModel metaModel = new MetaModel(clss);
		PrimaryKeyField primaryKeyField = metaModel.getPrimaryKey();
		String sql =smartReplaceFirst(metaModel.buildReadRequest(), primaryKeyField.getName() + " = " + "'"+primaryKey+"'");

		String connectionString = "jdbc:mysql://localhost:3306/standardbankatmdb";
		String userName = "root";
		String password = "0633386@NE|_e";
		Object obj =  null;
		
		try(	Connection connection = DriverManager.getConnection(connectionString, userName, password);
				Statement statement = connection.createStatement();)
		{
			ResultSet resultSet= statement.executeQuery(sql);
			
			if(!resultSet.next())
				return null;
			
			try
			{
				obj = clss.getConstructors()[0].newInstance();
			}
			catch(IllegalArgumentException e)
			{
				obj = clss.getConstructors()[1].newInstance();
			}
			
			primaryKeyField.set(obj, resultSet.getObject(primaryKeyField.getName()));
			for(ColumnField clmn: metaModel.getColumns() )
			{
				if(clmn.getType().contains("float"))
				{
					BigDecimal decimal = (BigDecimal) resultSet.getObject(clmn.getName());
					clmn.set(obj,decimal.floatValue());
				}
				else
				{
					clmn.set(obj, resultSet.getObject(clmn.getName()));
				}	
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return obj;
	}
	
	public List<Object> readForeign(Class<?> clss, Object foreignObj)
	{
		MetaModel metaModel = new MetaModel(clss);
		
		PrimaryKeyField primaryKeyField = metaModel.getPrimaryKey();
		
		String sql =smartReplaceFirst(metaModel.buildReadRequest(), 
				MetaModel.of(foreignObj.getClass()).getPrimaryKey().getName() 
				+ " = " + MetaModel.of(foreignObj.getClass()).getPrimaryKey().get(foreignObj));
		
		System.out.println(sql);
		String connectionString = "jdbc:mysql://localhost:3306/standardbankatmdb";
		String userName = "root";
		String password = "0633386@NE|_e";
		List<Object> objects = new ArrayList<>();
		Object obj =  null;
		
		try(	Connection connection = DriverManager.getConnection(connectionString, userName, password);
				Statement statement = connection.createStatement();)
		{
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next())
			{
				try
				{
					obj = clss.getConstructors()[0].newInstance();
				}
				catch(IllegalArgumentException e)
				{
					obj = clss.getConstructors()[1].newInstance();
				}
				
				primaryKeyField.set(obj, resultSet.getObject(primaryKeyField.getName()));
				for(ColumnField clmn: metaModel.getColumns() )
				{
					if(clmn.getType().contains("float"))
					{
						BigDecimal decimal = (BigDecimal) resultSet.getObject(clmn.getName());
						clmn.set(obj,decimal.floatValue());
					}
					else
					{
						clmn.set(obj, resultSet.getObject(clmn.getName()));
					}	
				}
				
				objects.add(obj);
				obj = null;
			}	
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objects;
	}
	
	private String smartReplaceFirst(String sql, String value)
	{
		String rslt = "";
		int count = 0;
		for(int i = 0; i<sql.length();i++)
		{
			
			if((sql.charAt(i)+"").equals("?") && count == 0)
			{
				rslt += value+"";
				count++;
			}
			else
			{
				rslt += sql.charAt(i);
			}
		}
		
		return rslt;
	}
}
