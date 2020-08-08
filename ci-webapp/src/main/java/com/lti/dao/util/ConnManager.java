package com.lti.dao.util;
//import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnManager {
	public static Connection connect() throws IOException {
		try {
			Properties dbProps= new Properties();
			//dbProps.load(new FileReader("dev-db.properties"));
			dbProps.load(ConnManager.class.getClassLoader().getResourceAsStream("dev-db.properties"));
			
			Class.forName(dbProps.getProperty("driverName"));
			return DriverManager.getConnection(dbProps.getProperty("url"),dbProps.getProperty("user"),dbProps.getProperty("pass"));
		}
		
			catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace(); //we should rather throw an user defined exception
				return null;
			
		}
		
	}

}
