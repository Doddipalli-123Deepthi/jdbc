package demo;

import java.sql.*;
import java.util.Scanner;

public class Sample{

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/deepu";
    private static final String USER = "root";
    private static final String PASS = "12345";

    // Method to insert a record
    public void insertRecord(String name, int age) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             CallableStatement stmt = conn.prepareCall("{call insertRecord(?, ?)}")) {

            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.execute();

            System.out.println("Record inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a record
    public void updateRecord(int id, String name, int age) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             CallableStatement stmt = conn.prepareCall("{call updateRecord(?, ?, ?)}")) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.executeUpdate();

            System.out.println("Record updated successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a record
    public void deleteRecord(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             CallableStatement stmt = conn.prepareCall("{call deleteRecord(?)}")) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Record deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch a record
    public void fetchRecord(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             CallableStatement stmt = conn.prepareCall("{call fetchRecord(?)}")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int empId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + empId + ", Name: " + name + ", Age: " + age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sample manager = new Sample();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Insert Record");
            System.out.println("2. Update Record");
            System.out.println("3. Delete Record");
            System.out.println("4. Fetch Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    manager.insertRecord(name, age);
                    break;
                case 2:
                    System.out.print("Enter ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    manager.updateRecord(updateId, newName, newAge);
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteRecord(deleteId);
                    break;
                case 4:
                    System.out.print("Enter ID to fetch: ");
                    int fetchId = scanner.nextInt();
                    manager.fetchRecord(fetchId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
