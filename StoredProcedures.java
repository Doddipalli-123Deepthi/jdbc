package demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedures {

    public static void main(String[] args){
        // Database connection details
       // String url = "jdbc:mysql://localhost:3306/deepu";
       // String user = "root";
       // String password = "12345";

        //Connection conn = null;
        //CallableStatement callableStmt = null;
       //ResultSet resultSet = null;
    	try {
            
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu", "root", "12345");

            // Preparing the CallableStatement
            String sql = "{call getUsers()}";
            CallableStatement callableStmt = conn.prepareCall(sql);

            // Executing the CallableStatement
            ResultSet resultSet = callableStmt.executeQuery();

            // Processing the ResultSet
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                System.out.println("Name: " + name + ", City: " + city);
            }		

        }
    	catch (SQLException e) 
        {
            e.printStackTrace();
        }
    	
//        finally
//        {
//            // Closing the resources
//            try 
//            {
//                if (resultSet != null) resultSet.close();
//                if (callableStmt != null) callableStmt.close();
//                if (conn != null) conn.close();
//            } 
//            catch (SQLException ex) 
//            {
//                ex.printStackTrace();
//            }
//        }
    }
}
