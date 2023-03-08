package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

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
		// -----------------------------------------------------------------------------------------------------------------------------------

	}
	//------------------------------------------------------------------------
	public static void insertIntoItems() {
		String sql = "Truncate table items;";
        String insertQuery = "INSERT INTO items (id, name, unit_price) VALUES (?, ?, ?)";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+	            "databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				//Random rn = new Random();

				for (int i = 0; i < Main.shop.items.size(); i++) {

				stmt.setInt(1, Main.shop.getItems().get(i).getId());
				stmt.setString(2, Main.shop.getItems().get(i).getName());
				stmt.setDouble(3, Main.shop.getItems().get(i).getUnitPrice());
				stmt.addBatch();
				
				}

				int[] results = stmt.executeBatch();
				System.out.println("Inserted " + results.length + " rows into hotels table.");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		
	}
	//------------------------------------------------------------------------
	public static void truncatesItems() {
		String insertQuery = "Truncate table items;";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+"databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				stmt.executeUpdate();
				
			System.out.println("items Table truncated");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	//------------------------------------------------------------------------
	public static void inseartIntoInvoices() {
		String insertQuery = "Truncate table items;";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+"databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				stmt.executeUpdate();
				
			System.out.println("items Table truncated");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}

}