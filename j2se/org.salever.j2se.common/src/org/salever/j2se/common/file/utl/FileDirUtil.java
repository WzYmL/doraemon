/**
 * 
 */
package org.salever.j2se.common.file.utl;

import java.io.File;
import java.net.URL;

/**
 * @author LiXP
 * 
 */
public class FileDirUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String path = FileDirUtil.class.getClassLoader().getResource(".")
		// .getPath();
		// System.out.println(path);

		System.out.println("user.dir: " + System.getProperty("user.dir"));
		File file = new File("folder/test.txt");
		System.out.println("File folder/test.txt exist?" + file.exists());
		String absolutePath = file.getAbsolutePath();
		System.out
				.println("File folder/test.txt absolutePath: " + absolutePath);

		URL dotURL = FileDirUtil.class.getClassLoader().getResource(".");
		System.out
				.println("FileDirUtil.class.getClassLoader().getResource(\".\"):"
						+ dotURL);
		URL slashURL = FileDirUtil.class.getClassLoader().getResource("/");
		System.out
				.println("FileDirUtil.class.getClassLoader().getResource(\"/\"):"
						+ slashURL);
		URL url = FileDirUtil.class.getClassLoader().getResource("");
		System.out
				.println("FileDirUtil.class.getClassLoader().getResource(\"\"):"
						+ url);

		URL classLoaderURL = FileDirUtil.class.getClassLoader().getResource(
				"test.txt");
		System.out
				.println("FileDirUtil.class.getClassLoader().getResource(\"test.txt\"):"
						+ classLoaderURL);

		URL classLoaderURL2 = FileDirUtil.class.getClassLoader().getResource(
				"org/salever/j2se/common/file/utl/test.txt");
		System.out
				.println("FileDirUtil.class.getClassLoader().getResource(\"org/salever/j2se/common/file/utl/test.txt\"):"
						+ classLoaderURL2);

		URL classURL = FileDirUtil.class.getResource("test.txt");
		System.out.println("FileDirUtil.class.getResource(\"test.txt\"):"
				+ classURL);

	}
}
