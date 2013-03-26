package org.eclipse.update.example;

import java.io.IOException;
import java.util.Arrays;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */
	public static void main(String args) throws IOException {
		int[] x = new int[6];
		Arrays.fill(x, 1);
		
		for(int i =0; i < x.length; i ++){
			System.in.read();
			System.out.print(x[i]);
		}

	}

	private static void foo() {   
	    try {   
	        System.out.println("try");   
	        foo();   
	    } catch (Throwable e) {   
	        System.out.println("catch");   
	        foo();   
	    } finally {   
	        System.out.println("finally");   
	        foo();   
	    }   
	}   
	  
	public static void main(String[] args) {   
	    foo();   
	}
}
