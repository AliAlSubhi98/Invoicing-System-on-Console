package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CreatingTables {
	public static void createTablesInDataBase() {
	    String url = "jdbc:sqlserver://localhost:1433;" +
	            "databaseName=Invoicing-System-on-Console;" +
	            "encrypt=true;" +
	            "trustServerCertificate=true";
	
	    String user = "sa";
	    String pass = "root";
	

	    Connection con = null;
	    //-----------------------------------------------------------------------------------------------------------------------------------

	    try {
	
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
	
			con = DriverManager.getConnection(url, user, pass);
	
	
			Statement st = con.createStatement();
	
			String sql1 = "IF OBJECT_ID(N'dbo.items', N'U') IS NULL\r\n"
					+ "CREATE TABLE items (\r\n"
					+ "  id INTEGER PRIMARY KEY,\r\n"
					+ "  name VARCHAR(255) NOT NULL,\r\n"
					+ "  unit_price DECIMAL(10, 2) NOT NULL,\r\n"
					+ ");";

			st.executeUpdate(sql1);
			System.out.println("THE items TABLE CREATED SUCCESSFULLY");
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	    //-----------------------------------------------------------------------------------------------------------------------------------
	    try {
	    	
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
	
			con = DriverManager.getConnection(url, user, pass);
	
	
			Statement st = con.createStatement();
	
		    String sql2 = "IF OBJECT_ID(N'dbo.invoices', N'U') IS NULL\r\n"
		    		+ "CREATE TABLE invoices (\r\n"
		    		+ "  id INTEGER PRIMARY KEY,\r\n"
		    		+ "  customer_name VARCHAR(255) NOT NULL,\r\n"
		    		+ "  phone_number VARCHAR(20) NOT NULL,\r\n"
		    		+ "  invoice_date DATE NOT NULL,\r\n"
		    		+ "  total_amount DECIMAL(10, 2) NOT NULL,\r\n"
		    		+ "  paid_amount DECIMAL(10, 2) NOT NULL,\r\n"
		    		+ "  balance DECIMAL(10, 2) NOT NULL,\r\n"
		    		+ ");";

		    st.executeUpdate(sql2);
		    System.out.println("THE invoices TABLE CREATED SUCCESSFULLY");
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	    //-----------------------------------------------------------------------------------------------------------------------------------
	    try {
	    	
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
	
			con = DriverManager.getConnection(url, user, pass);
	
	
			Statement st = con.createStatement();
	
		    String sq3 = "IF OBJECT_ID(N'dbo.shop', N'U') IS NULL\r\n"
		    		+ "CREATE TABLE shop (\r\n"
		    		+ "  id INTEGER IDENTITY PRIMARY KEY,\r\n"
		    		+ "  shop_name VARCHAR(255) NOT NULL,\r\n"
		    		+ "  tel VARCHAR(20) NOT NULL,\r\n"
		    		+ "  fax VARCHAR(20),\r\n"
		    		+ "  email VARCHAR(255),\r\n"
		    		+ "  website VARCHAR(255),\r\n"
		    		+ ");";

		    st.executeUpdate(sq3);
		    System.out.println("THE shop TABLE CREATED SUCCESSFULLY");
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	    //-----------------------------------------------------------------------------------------------------------------------------------

	}
}