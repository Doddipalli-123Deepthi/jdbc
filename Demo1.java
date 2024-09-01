package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Demo1
{
public static void main(String[] args) throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu", "root", "12345");
	System.out.println("con"+conn);
	Statement stmt = conn.createStatement();
	String sql = "create table emp";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, "value");
}	
	

	

	



}
