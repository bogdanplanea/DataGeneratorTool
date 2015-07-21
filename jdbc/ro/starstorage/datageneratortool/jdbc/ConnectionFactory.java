package ro.starstorage.datageneratortool.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {

	private static final Logger LOG = LogManager.getLogger(ConnectionFactory.class.getName());
	 Properties configFile = new Properties();
	 {
		try {
			configFile.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("token.properties"));
		} catch (IOException e) {
			LOG.error("Nu am gasit fisierul de proprietati.", e);
		}
	}

	// static reference to itself
	private static ConnectionFactory instance = new ConnectionFactory();
	public  final String URL = configFile.getProperty("db_url");
	public  final String USER = configFile.getProperty("db_username");
	public  final String PASSWORD = configFile.getProperty("db_password");
	public  final String DRIVER_CLASS = configFile.getProperty("db_driver");

	// private constructor
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			LOG.error("Nu gasesc driverul pentru JDBC.", e);
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			LOG.error("ERROR: Unable to Connect to Database.", e);
		}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}
	
	

}
