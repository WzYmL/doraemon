/**
 * 
 */
package org.salever.j2se.common.velocity;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * Need velocity-1.6.4.jar
 * 
 * @author LiXiaopeng
 * 
 */
public class VelocityHelloWorld {

	/**
	 * @param args
	 * @throws Exception
	 * @throws ParseErrorException
	 * @throws ResourceNotFoundException
	 */
	public static void main(String[] args) throws ResourceNotFoundException,
			ParseErrorException, Exception {

		List<PersonBean> list = new ArrayList<PersonBean>();
		list.add(new PersonBean());
		list.add(new PersonBean());
		list.add(new PersonBean());

		Velocity.init();
		VelocityContext context = new VelocityContext();
		context.put("name", "Velocity");
		context.put("array", new String[] { "a", "b", "c" });
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("c:\\1.txt"), "UTF-8"));
		Template template = Velocity.getTemplate("velocity/hello.vm");
		template.merge(context, writer);
		writer.flush();
		writer.close();
	}

}
