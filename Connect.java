package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect{
	public static void main(String[] args) throws Exception
	{
//		Class.forName("com.mysql.cj.jdbc.Driver");
		String mysqlHostName="jdbc:mysql://localhost:3306/deepu";
		String userName="root";
		String password="12345";
		Connection con=DriverManager.getConnection(mysqlHostName,userName,password);
		System.out.println("connection craeated "+con);
		Statement stmt=con.createStatement();
		//String sql="create table users(name varchar(20),city varchar(10))";
		
//		String sql="insert into users(name,city)"+"values('deepthi','bangalore')";
//		String sql="insert into users(name,city)"+"values('sandhya','chittoor')";
//		String sql="insert into users(name,city)"+"values('nandini','vkota')";
		
		
//		String sql="DELETE FROM users WHERE name='deepthi'";
//		String sql="update users SET name='heyansh' WHERE city='vkota'";

		String query = "select * from users"; // Modify the query according to your database schema
        ResultSet rs = stmt.executeQuery(query);

        // 5. Process the ResultSet
        while (rs.next()) 
        {
//            String name = rs.getString("name");
//            String city = rs.getString("city");
        	String name = rs.getString(1);
            String city = rs.getString(2);
           

            System.out.println("name: " + name + ",+ city: " + city);
        }
		
		
		
		
	}
}