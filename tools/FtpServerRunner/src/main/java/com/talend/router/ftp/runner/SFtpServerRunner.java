package com.talend.router.ftp.runner;

import java.io.IOException;

public class SFtpServerRunner {

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("msftpsrvr.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
