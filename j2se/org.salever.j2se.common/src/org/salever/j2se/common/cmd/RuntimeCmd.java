/**
 * 
 */
package org.salever.j2se.common.cmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author LiXP
 * 
 */
public class RuntimeCmd {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {

		String address = "localhost";
		String username = "root";
		String password = "root";
		String sqlpath = "c:/data/";
		String filename = "new.sql";
		String databaseName = "test";
		StringBuffer sb = new StringBuffer();
		// 拼接命令
		sb.append("mysqldump ");
		sb.append("--opt ");
		sb.append("-h ");

		sb.append(address);
		sb.append(" ");
		sb.append("--user=");

		sb.append(username);
		sb.append(" ");
		sb.append("--password=");

		sb.append(password);
		sb.append(" ");
		sb.append("--lock-all-tables=true ");
		sb.append("--result-file=");

		sb.append(sqlpath);

		sb.append(filename);
		sb.append(" ");
		sb.append("--default-character-set=utf8 ");

		sb.append(databaseName);

		Runtime cmd = Runtime.getRuntime();
		try {
			Process p = cmd
					.exec("cmd /c start " + sb.toString(),
							new String[] { "path=$path;C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin" },
							new File("c:/data"));
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			FileOutputStream fos = new FileOutputStream(sqlpath + filename);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write(line);
				bw.flush();
			}
			br.close();
			bw.close();
			System.out.println("调用命令：" + sb.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
