package com.fainberg.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fainberg.usermanagement.model.User;

public class UserDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/userslistdb?useSSL=false&useUnicode=true&serverTimezone=UTC";
	private String jdbcUser = "root";
	private String jdbcPassword = "trEv0r11";
	
	
	private static final String SELECT_ALL_USERS = "select * from users;";
	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id=?;";
	private static final String DELETE_USER_BY_ID = "delete from users where id=?;";
    private static final String UPDATE_USER_BY_ID = "update users set name = ?,email= ?, country =? where id = ?;";
    private static final String INSERT_USER_TO_USERS = "INSERT INTO users" + "  (name, email, country) VALUES " + " (?, ?, ?);";
	
    
	public UserDAO() {}
	
	
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Connection established to SQL;");
		return connection;
	}

	
	
	public List<User> selectAllUsers() {
        // using try-with-resources to avoid closing resources (boiler plate code)
		List <User> users = new ArrayList<User>();
		
		
        // Step 1: Establishing a Connection
		try (Connection connection = getConnection();
	         // Step 2:Create a statement using connection object
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
			
			
			System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
            
            
         // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
            
			
			
		} catch (SQLException e) {
            printSQLException(e);
		}
		
		
		return users;
	}



	private void printSQLException(SQLException ex) {
		 for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	}
	
	
	
}
