package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
			public static Connection  getConnection(){
						Connection conn = null;
						String url = "jdbc:mysql://localhost:3306/mydb";
						String id = "root";
						String password = "goqud";
						try {
									Class.forName("com.mysql.jdbc.Driver");
									conn = DriverManager.getConnection(url, id, password);
						} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									System.out.println("class");
									e.printStackTrace();
						} catch (SQLException e) {
									// TODO Auto-generated catch block
									System.out.println("sql");
									e.printStackTrace();
						}
						return conn;
			}
}