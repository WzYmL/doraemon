/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on Apr 11, 2012 4:56:54 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author LiXP
 * 
 */
public class SecondCompile {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<JavaFileObject>();

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnosticCollector, Locale.CHINA, Charset.defaultCharset());

		// StringWriter writer = new StringWriter();
		// PrintWriter out = new PrintWriter(writer);
		// out.println("public class HelloWorld {");
		// out.println(" public static void main(String args[]) {");
		// out.println(" System.out.println(\"Hello, World\");");
		// out.println(" }");
		// out.println("}");

		Iterable<? extends JavaFileObject> javaFileObjectsFromStrings = fileManager
				.getJavaFileObjectsFromStrings(Arrays.asList("Hello.java"));

		CompilationTask task = compiler.getTask(null, fileManager,
				new DiagnosticListener<JavaFileObject>() {
					@Override
					public void report(
							Diagnostic<? extends JavaFileObject> diagnostic) {
						System.out.printf("Code: %s%n" + "Kind: %s%n"
								+ "Position: %s%n" + "Start Position: %s%n"
								+ "End Position: %s%n" + "Source: %s%n"
								+ "Message: %s%n", diagnostic.getCode(),
								diagnostic.getKind(), diagnostic.getPosition(),
								diagnostic.getStartPosition(),
								diagnostic.getEndPosition(),
								diagnostic.getSource(),
								diagnostic.getMessage(null));

					}

				}, null, null, javaFileObjectsFromStrings);
		Boolean result = task.call();
		fileManager.close();
		System.out.println("Result: " + result);
	}
}
