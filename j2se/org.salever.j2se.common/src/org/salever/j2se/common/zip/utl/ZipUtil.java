package org.salever.j2se.common.zip.utl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {

	public static void unzip(InputStream iszip, String toFolder) {
		ZipInputStream in = null;
		try {
			in = new ZipInputStream(iszip);
			for (ZipEntry entry = in.getNextEntry(); entry != null; entry = in.getNextEntry()) {
				if (entry.isDirectory()) {
					// If the entry is a directory, it should be created
					(new File(toFolder, entry.getName())).mkdirs();
				} else {
					// If the entry is a file, it should be created and
					// written in the dir
					// Creates the destination file
					File f = new File(toFolder, entry.getName());
					// Create the directory structure in the destination
					if (!f.getParentFile().exists()) {
						f.getParentFile().mkdirs();
					}
					// Writes the contents into the created file
					FileOutputStream out = new FileOutputStream(f);
					try {
						byte[] buf = new byte[1024];
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
					} finally {
						out.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
