package ro.starstorage.datageneratortool.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbUtil {

	private static final Logger LOG = LogManager.getLogger(DbUtil.class.getName());

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("A aparut o eroare la inchiderea conexiunii la baza de date.", e);
			}
		}
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOG.error("A aparut o eroare la inchiderea statementului la baza de date.", e);
			}
		}
	}

	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOG.error("A aparut o eroare la inchiderea setului de rezultat al bazei de date.", e);
			}
		}
	}
}
