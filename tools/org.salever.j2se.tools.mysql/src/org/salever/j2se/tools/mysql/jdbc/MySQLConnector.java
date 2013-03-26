/**
 * 
 */
package org.salever.j2se.tools.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author LiXP
 * 
 */
public class MySQLConnector {

	/**
	 * 创建数据库的连接
	 * 
	 * @return
	 */
	public Connection ConnectionDBT() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String user = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/test";

			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
