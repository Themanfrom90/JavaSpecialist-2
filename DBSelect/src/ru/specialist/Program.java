package ru.specialist;

import java.sql.*;
import java.util.Scanner;

public class Program {
	
	public static final String DRIVER_NAME = 
			"com.mysql.jdbc.Driver";
	public static final String CONNECTION_STRING = 
			"jdbc:mysql://localhost:3306/web?user=root&password=demo";
	public static void main(String[] args)
			throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		
		
		try(Connection conn = DriverManager.getConnection(CONNECTION_STRING))
		{
			CallableStatement sp = conn.prepareCall("call countCourse(?)");
			// IN INOUT sp.set...
			sp.execute();
			
			// INOUT, OUT sp.get...
			int count = sp.getInt(1);
			
			System.out.printf("Всего курсов в базе: %d\n", count);
			
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Поиск: ");
			String search = sc.nextLine().trim();
			// bad : sql injection
			/*
			Statement cmd = conn.createStatement();
			String sql = "SELECT title, length FROM Courses "
					+ "WHERE title LIKE '%"+search+"%' "
					+ "ORDER BY title";
			ResultSet result = cmd.executeQuery(sql);*/
			// INSERT INTO Courses (title, length, description) VALUES(?, ?, ?)
			// executeUpdate
			
			String sql = "SELECT title, length FROM Courses "
					+ "WHERE title LIKE ? "
					+ "ORDER BY title";
			PreparedStatement cmd = conn.prepareStatement(sql);
			cmd.setString(1, "%"+search+"%");
			ResultSet result = cmd.executeQuery();
			
			while(result.next()) {
				String title = result.getString("title");
				int length = result.getInt("length");
				System.out.printf("%-30s : %d\n", title, length);
			}
			result.close();
		}//conn.close();

	}

}
