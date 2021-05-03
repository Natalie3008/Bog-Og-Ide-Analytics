package databaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* The DBConnection class is a template class for the DAO pattern.
 * It ensures the connection between a java system and a sql database. 
 * It can be reused, with the initial information such as password, 
 * username, database name etc. needing to be changed based on the
 * preferences of the user. 
 */

public class DBConnection {
	private Connection connection = null; // the connection to the database
	private static DBConnection dbConnection = new DBConnection(); // unique instance of the class - singleton pattern

	private static final String DBNAME = "BogOgIdeAnalytics";
	private static final String SERVERNAME = "localhost";
	private static final String PORTNUMBER = "1433";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "secret2021*";

	// constructor - private because of singleton pattern
	private DBConnection() {
		String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s", SERVERNAME, PORTNUMBER, DBNAME);

		String userName = getUserName();
		String password = getPassword();

		try {
			connection = DriverManager.getConnection(urlString, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		return dbConnection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int executeUpdate(String sql) throws SQLException {
		int result = -1;
		try (Statement s = connection.createStatement()) {
			result = s.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int executeSqlInsertWithIdentity(PreparedStatement sqlPreparedStatement) throws SQLException {
		int result = -1;
		try {
			result = sqlPreparedStatement.executeUpdate();
			if (result > 0) {
				ResultSet resultSet = sqlPreparedStatement.getGeneratedKeys();
				resultSet.next();
				result = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	private String getUserName() {
		return USERNAME;
	}

	private String getPassword() {
		return PASSWORD;
	}
}