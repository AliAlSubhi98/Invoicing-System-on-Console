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
					+ "  name VARCHAR(255) ,\r\n"
					+ "  unit_price DECIMAL(10, 2) ,\r\n"
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
		    		+ "  customer_name VARCHAR(255) ,\r\n"
		    		+ "  phone_number VARCHAR(20)  ,\r\n"
		    		+ "  invoice_date DATE  ,\r\n"
		    		+ "  total_amount DECIMAL(10, 2)  ,\r\n"
		    		+ "  paid_amount DECIMAL(10, 2)  ,\r\n"
		    		+ "  balance DECIMAL(10, 2)  ,\r\n"
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
		    		+ "  shop_name VARCHAR(255) ,\r\n"
		    		+ "  tel VARCHAR(20) ,\r\n"
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
	    try {
	    	
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
	
			con = DriverManager.getConnection(url, user, pass);
	
	
			Statement st = con.createStatement();
	
		    String sq4 = "IF OBJECT_ID(N'dbo.invoice_items', N'U') IS NULL\r\n"
		    		+ "CREATE TABLE invoice_items (\r\n"
		    		+ "  id INTEGER IDENTITY PRIMARY KEY,\r\n"
		    		+ "  invoice_id INTEGER ,\r\n"
		    		+ "  customer_name VARCHAR(255)  ,\r\n"
		    		+ "  phone_number VARCHAR(20)  ,\r\n"
		    		+ "  invoice_date DATE  ,\r\n"
		    		+ "  total_amount DECIMAL(10, 2)  ,\r\n"
		    		+ "  paid_amount DECIMAL(10, 2)  ,\r\n"
		    		+ "  balance DECIMAL(10, 2)  ,\r\n"
		    		+ "  item_id INTEGER ,\r\n"
		    		+ "  item_name VARCHAR(255),\r\n"
		    		+ "  quantity INTEGER ,\r\n"
		    		+ "  unit_price DECIMAL(10, 2),\r\n"
		    		+ "  FOREIGN KEY (invoice_id) REFERENCES invoices(id),\r\n"
		    		+ "  FOREIGN KEY (item_id) REFERENCES items(id)\r\n"
		    		+ ");";

			st.executeUpdate(sq4);
		    
		   
			System.out.println("THE invoice_items TABLE CREATED SUCCESSFULLY");
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	//------------------------------------------------------------------------
	public static void dropAllTables() {
		String insertQuery = "drop table invoice_items;\r\n"
				+ "drop table invoices;\r\n"
				+ "drop table items;\r\n"
				+ "drop table shop;";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+"databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				stmt.executeUpdate();
				
			System.out.println("All Tables dropped");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	//------------------------------------------------------------------------
	public static void insertIntoItems() {
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
				System.out.println("Inserted " + results.length + " rows into items table.");
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
	public static void truncatesInvoices() {
		String insertQuery = "Truncate table invoices;";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+"databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				stmt.executeUpdate();
				
			System.out.println("invoices Table truncated");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	//------------------------------------------------------------------------
	public static void truncatesShop() {
		String insertQuery = "Truncate table shop;";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+"databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				stmt.executeUpdate();
				
			System.out.println("shop Table truncated");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	//------------------------------------------------------------------------
	public static void truncatesInvoiceItems() {
		String insertQuery = "Truncate table invoice_items;";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+"databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				stmt.executeUpdate();
				
			System.out.println("shop Table truncated");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	//------------------------------------------------------------------------
	public static void inseartIntoShop() {
		String insertQuery = "INSERT INTO shop (shop_name, tel, fax, email, website) VALUES (?, ?, ?, ?, ?);";

			try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
					+"databaseName=Invoicing-System-on-Console;" +
					"encrypt=true;" +
					"trustServerCertificate=true", "sa", "root");
					PreparedStatement stmt = conn.prepareStatement(insertQuery)
					) {
				stmt.setString(1, Main.shop.getShopName());
				stmt.setString(2, Main.shop.getTel());
				stmt.setString(3, Main.shop.getFax());
				stmt.setString(4, Main.shop.getFax());
				stmt.setString(5, Main.shop.getWebsite());
				stmt.addBatch();

				stmt.executeUpdate();
				
			System.out.println("shop details inserted");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	}
	//------------------------------------------------------------------------
	public static void inseartIntoInvoices() {
		String insertQuery = "insert into invoices (id , customer_name , phone_number , invoice_date , total_amount , paid_amount, balance) VALUES (?,?,?,?,?,?,?);";

		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
				+"databaseName=Invoicing-System-on-Console;" +
				"encrypt=true;" +
				"trustServerCertificate=true", "sa", "root");
				PreparedStatement stmt = conn.prepareStatement(insertQuery)
				) {
			for( int i = 0 ; i<Main.shop.invoices.size(); i++) {
				
				stmt.setInt(1, Main.shop.invoices.get(i).getId());
				stmt.setString(2, Main.shop.invoices.get(i).getCustomerName());
				stmt.setString(3, Main.shop.invoices.get(i).getPhoneNumber());
				stmt.setString(4, Main.shop.invoices.get(i).getInvoiceDate().toString());
				stmt.setDouble(5, Main.shop.invoices.get(i).getTotalAmount());
				stmt.setDouble(6, Main.shop.invoices.get(i).getPaidAmount());
				stmt.setDouble(7, Main.shop.invoices.get(i).getBalance());
				stmt.addBatch();
			}
			int[] results = stmt.executeBatch();
			System.out.println("Inserted " + results.length + " rows into Invoices table.");		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	//------------------------------------------------------------------------
	public static void inseartIntoInvoiceItems() {//        1            2              3            4             5              6         7          8        9          10       11       
		String insertQuery = "INSERT INTO invoice_items (invoice_id, customer_name, phone_number, invoice_date, total_amount, paid_amount, balance, item_id, item_name, quantity, unit_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
				+"databaseName=Invoicing-System-on-Console;" +
				"encrypt=true;" +
				"trustServerCertificate=true", "sa", "root");
				PreparedStatement stmt = conn.prepareStatement(insertQuery)
				) {
					for (int i = 0; i < Main.shop.invoices.size(); i++) {

						stmt.setInt(1, Main.shop.invoices.get(i).getId());
						stmt.setString(2, Main.shop.invoices.get(i).getCustomerName());
						stmt.setString(3, Main.shop.invoices.get(i).getPhoneNumber());
						stmt.setString(4, Main.shop.invoices.get(i).getInvoiceDate().toString());
						stmt.setDouble(5, Main.shop.invoices.get(i).getTotalAmount());
						stmt.setDouble(6, Main.shop.invoices.get(i).getPaidAmount());
						stmt.setDouble(7, Main.shop.invoices.get(i).getBalance());
						for (int j = 0; j < Main.shop.invoices.get(i).getItems().size() ; j++) {
							stmt.setInt(8, Main.shop.invoices.get(i).getItems().get(j).getId());
							stmt.setString(9, Main.shop.invoices.get(i).getItems().get(j).getName());
							stmt.setInt(10, Main.shop.invoices.get(i).getItems().get(j).getQuantity());
							stmt.setDouble(11, Main.shop.invoices.get(i).getItems().get(j).getUnitPrice());
							stmt.addBatch();
						}
					}
					int[] results = stmt.executeBatch();
					System.out.println("Inserted " + results.length + " rows into invoice_items table.");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
	}

}