package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CrudOperations
{

    public static void main(String[] args) 
    {
        Connection conn = null;
        try {
            // Step 1: Load the JDBC driver (Optional for newer versions of Java)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "12345");

            // Call the methods for each operation
           // createTable(conn);
            insertData(conn, "John", "Doe", 30);
            insertData(conn, "Jane", "Smith", 25);
            updateData(conn, "John", 35);
            deleteData(conn, "Jane");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 7: Close the connection
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method to create a table using PreparedStatement
    public static void createTable(Connection conn) {
        PreparedStatement pstmt = null;
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Employees (" 
                    + "ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "FirstName VARCHAR(100), " 
                    + "LastName VARCHAR(100), " 
                    + "Age INT)";
            pstmt = conn.prepareStatement(createTableSQL);
            pstmt.execute();
            System.out.println("Table 'Employees' created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method to insert data into the table
    public static void insertData(Connection conn, String firstName, String lastName, int age) {
        PreparedStatement pstmt = null;
        try {
            String insertSQL = "INSERT INTO Employees (FirstName, LastName, Age) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully: " + firstName + " " + lastName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method to update data in the table
    public static void updateData(Connection conn, String firstName, int newAge) {
        PreparedStatement pstmt = null;
        try {
            String updateSQL = "UPDATE Employees SET Age = ? WHERE FirstName = ?";
			pstmt = conn.prepareStatement(updateSQL);
            pstmt.setInt(1, newAge);
            pstmt.setString(2, firstName);
            pstmt.executeUpdate();
            System.out.println("Data updated successfully for: " + firstName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method to delete data from the table
    public static void deleteData(Connection conn, String firstName) {
        PreparedStatement pstmt = null;
        try {
            String deleteSQL = "DELETE FROM Employees WHERE FirstName = ?";
            pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setString(1, firstName);
            pstmt.executeUpdate();
            System.out.println("Data deleted successfully for: " + firstName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
