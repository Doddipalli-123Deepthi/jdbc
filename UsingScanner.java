package demo;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.util.Scanner;

	public class UsingScanner
	{

	    public static void main(String[] args)
	    {
	        Connection conn = null;
	        Scanner scanner = new Scanner(System.in);

	        try 
	        {
	            // Load the JDBC driver (Optional for newer versions of Java)
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish a connection to the database
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "12345");

	            // User menu for operations
	            while (true)
	            {
	                System.out.println("\nSelect an operation:");
	                System.out.println("1. Insert Data");
	                System.out.println("2. Update Data");
	                System.out.println("3. Delete Data");
	                System.out.println("4. Exit");
	                System.out.print("Enter your choice: ");
	                int choice = scanner.nextInt();
	                scanner.nextLine(); // Consume newline

	                switch (choice) 
	                {
	                    case 1:
	                        System.out.print("Enter first name: ");
	                        String firstName = scanner.nextLine();
	                        System.out.print("Enter last name: ");
	                        String lastName = scanner.nextLine();
	                        System.out.print("Enter age: ");
	                        int age = scanner.nextInt();
	                        insertData(conn, firstName, lastName, age);
	                        break;
	                    case 2:
	                        System.out.print("Enter first name to update: ");
	                        String updateFirstName = scanner.nextLine();
	                        System.out.print("Enter new age: ");
	                        int newAge = scanner.nextInt();
	                        updateData(conn, updateFirstName, newAge);
	                        break;
	                    case 3:
	                        System.out.print("Enter first name to delete: ");
	                        String deleteFirstName = scanner.nextLine();
	                        deleteData(conn, deleteFirstName);
	                        break;
	                    case 4:
	                        System.out.println("Exiting...");
	                        return;
	                    default:
	                        System.out.println("Invalid choice, please try again.");
	                        break;
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the connection
	            try {
	                if (conn != null)
	                    conn.close();
	                scanner.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void insertData(Connection conn, String firstName, String lastName, int age) 
	    {
	        PreparedStatement pstmt = null;
	        try
	        {
	            String insertSQL = "INSERT INTO Employees (FirstName, LastName, Age) VALUES (?, ?, ?)";
	            pstmt = conn.prepareStatement(insertSQL);
	            pstmt.setString(1, firstName);
	            pstmt.setString(2, lastName);
	            pstmt.setInt(3, age);
	            pstmt.executeUpdate();
	            System.out.println("Data inserted successfully: " + firstName + " " + lastName);
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	            try 
	            {
	                if (pstmt != null)
	                    pstmt.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void updateData(Connection conn, String firstName, int newAge) {
	        PreparedStatement pstmt = null;
	        try
	        {
	            String updateSQL = "UPDATE Employees SET Age = ? WHERE FirstName = ?";
	            pstmt = conn.prepareStatement(updateSQL);
	            pstmt.setInt(1, newAge);
	            pstmt.setString(2, firstName);
	            pstmt.executeUpdate();
	            System.out.println("Data updated successfully for: " + firstName);
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        } finally 
	        {
	            try {
	                if (pstmt != null)
	                    pstmt.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void deleteData(Connection conn, String firstName) {
	        PreparedStatement pstmt = null;
	        try 
	        {
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



