/**
 * 
 */
package org.salever.j2se.common.file.utl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author LiXP
 * 
 */
public class FileReadWriter {

	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void readStream(String file) throws IOException {

		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(file), "ISO-8859-1");
		char[] cbuf = new char[256];
		int read = inputStreamReader.read(cbuf);
		while (read != -1) {
			System.out.println(cbuf);
			read = inputStreamReader.read(cbuf);
		}
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void readReader(String file) throws IOException {

		InputStreamReader inputStreamReader = new FileReader(file);
		char[] cbuf = new char[256];
		int read = inputStreamReader.read(cbuf);
		while (read != -1) {
			System.out.println(cbuf);
			read = inputStreamReader.read(cbuf);
		}
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void bufferRead(String file) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), "utf-8"));
		String line = bufferedReader.readLine();
		while (line != null) {
			System.out.println(line);
			line = bufferedReader.readLine();
		}
	}

	/**
	 * FileWriter 用于写入字符流
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void write(String file) throws IOException {
		FileWriter wirter = new FileWriter(file);
		wirter.write("你好");
		wirter.flush();
	}

	/**
	 * OutputStreamWriter 是字符流通向字节流的桥梁：可使用指定的 charset
	 * 将要写入流中的字符编码成字节。它使用的字符集可以由名称指定或显式给定， 否则将接受平台默认的字符集。
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void writeStream(String file) throws IOException {
		OutputStreamWriter wirter = new OutputStreamWriter(
				new FileOutputStream(file), "uft-8");
		wirter.write("你好");
		wirter.flush();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileReadWriter.writeStream("test2.txt");
		FileReadWriter.write("test1.txt");
	}

}
