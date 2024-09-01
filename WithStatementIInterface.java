package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class WithStatementIInterface {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// Step 1: Load the JDBC driver (Optional for newer versions of Java)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2: Establish a connection to the database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WithStatement", "root", "12345");

			// Call the methods for each operation
			//createTable(conn);
			insertData(conn, "Prabha", "Kiran", 28);
			//insertData(conn, "Jane", "Smith", 25);
			//updateData(conn, "John", 35);
			//deleteData(conn, "Jane");

		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			// Step 7: Close the connection
			try
			{
				if (conn != null)
					conn.close();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	// Method to create a table
	public static void createTable(Connection conn) 
	{
		Statement stmt = null;
		try
		{
			String createTableSQL = "CREATE TABLE Student (" + "ID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "FirstName VARCHAR(100), " + "LastName VARCHAR(100), " + "Age INT)";
			stmt = conn.createStatement();
			stmt.execute(createTableSQL);
			System.out.println("Table 'Student' created successfully.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				if (stmt != null)
					stmt.close();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	// Method to insert data into the table
	public static void insertData(Connection conn, String firstName, String lastName, int age) {
		Statement stmt = null;
		try {
			String insertSQL = "INSERT INTO Student (FirstName, LastName, Age) VALUES ('" + firstName + "', '"
					+ lastName + "', " + age + ")";
			stmt = conn.createStatement();
			stmt.executeUpdate(insertSQL);
			System.out.println("Data inserted successfully: " + firstName + " " + lastName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Method to update data in the table
	public static void updateData(Connection conn, String firstName, int newAge) {
		Statement stmt = null;
		try {
			String updateSQL = "UPDATE Employees SET Age = " + newAge + " WHERE FirstName = '" + firstName + "'";
			stmt = conn.createStatement();
			stmt.executeUpdate(updateSQL);
			System.out.println("Data updated successfully for: " + firstName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Method to delete data from the table
	public static void deleteData(Connection conn, String firstName) {
		Statement stmt = null;
		try {
			String deleteSQL = "DELETE FROM Employees WHERE FirstName = '" + firstName + "'";
			stmt = conn.createStatement();
			stmt.executeUpdate(deleteSQL);
			System.out.println("Data deleted successfully for: " + firstName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
