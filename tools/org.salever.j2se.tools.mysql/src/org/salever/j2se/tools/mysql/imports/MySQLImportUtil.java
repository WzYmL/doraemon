/**
 * 
 */
package org.salever.j2se.tools.mysql.imports;

import java.io.File;
import java.io.IOException;

/**
 * @author LiXP
 * 
 */
public class MySQLImportUtil {

	/**
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @param database
	 * @param table
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static int importMySQL(String host, String username,
			String password, String database, String table, String filePath)
			throws IOException, InterruptedException {

		String cmd = buildCMD(host, username, password, database, table,
				filePath);

		System.out.println(cmd);

		Process process = Runtime.getRuntime().exec(cmd);

		process.waitFor();

		int exitValue = process.exitValue();

		System.out.println(exitValue + " " + new File(filePath).exists());

		return exitValue;
	}

	/**
	 * 
	 * @param cmd
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static int importMySQL(String cmd) throws IOException,
			InterruptedException {

		System.out.println(cmd);

		Process process = Runtime.getRuntime().exec(cmd);

		process.waitFor();

		int exitValue = process.exitValue();

		return exitValue;
	}

	/**
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @param database
	 * @param table
	 * @param filePath
	 * @return
	 */
	public static String buildCMD(String host, String username,
			String password, String database, String table, String filePath) {

		StringBuffer cmds = new StringBuffer();
		cmds.append("cmd /c ");
		cmds.append("mysql ");
		cmds.append(database + " ");
		cmds.append(table + " ");
		cmds.append("--host=" + host + " ");
		cmds.append("--user=" + username + " ");
		cmds.append("--password=" + password + " ");
		cmds.append("< " + filePath);
		return cmds.toString();
	}
}
