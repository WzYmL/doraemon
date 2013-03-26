package org.salever.j2se.common.java6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptFlipBindings {
	public static void main(String args[]) throws IOException {
		ScriptEngineManager manager = new ScriptEngineManager();

		System.out.println("Please input a string:");

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		String readLine = bufferedReader.readLine();

		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			engine.put("name", readLine);
			engine.eval("var output = '';"
					+ "for (i = 0; i <= name.length; i++) {"
					+ " output = name.charAt(i) + output" + "}");
			String name = (String) engine.get("output");
			PrintStream console = System.out;
			console.printf("Reversed: %s%n", name);
		} catch (ScriptException e) {
			System.err.println(e);
		}
	}
}