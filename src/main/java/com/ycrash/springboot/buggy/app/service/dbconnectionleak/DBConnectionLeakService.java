package com.ycrash.springboot.buggy.app.service.dbconnectionleak;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DBConnectionLeakService {


	@Value(value = "${datasource.url}")
	private String datasourceUrl;

	@Value(value = "${datasource.username}")
	private String dbUsername;

	@Value(value = "${datasource.password}")
	private String dbPassword;

	@Value(value = "${datasource.table.name}")
	private String tableName;

	private static final Logger log = LoggerFactory.getLogger(DBConnectionLeakService.class);

	public Connection getConnection(String datasourceUrl,String dbUsername,String dbPassword) throws SQLException {
		return DriverManager.getConnection(datasourceUrl, dbUsername, dbPassword);
	}

	public void closeConnection(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * Opens a DB connection but never closes it
	 * 
	 */
	public void leakConnection(String datasourceUrl,String dbUsername,String dbPassword,String tableName) {
		Connection connection = null;
		try {
			connection = getConnection(datasourceUrl, dbUsername,dbPassword);
			// Perform database operations using the connection
			log.debug("Connecting to DB and querying " + tableName);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM  " + tableName);
			ResultSet resultSet = statement.executeQuery();
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			printLog(e);
			e.printStackTrace();
		} finally {
			try {
				// closeConnection(connection);
			} catch (Exception e) {
				printLog(e);
				e.printStackTrace();
			}
		}

	}
	
	
	/**
	 * Continuously leaks DB connections."
	 */
	public void start(String datasourceUrl,String dbUsername,String dbPassword,String tableName) {
		while(true) {
			leakConnection(datasourceUrl,dbUsername,dbPassword,tableName);
		}
	}

	/**
	 * Print stack trace in log file
	 */
	private void printLog(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		log.error(sw.toString());
	}
}
