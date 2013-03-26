/** Copyright (c) 2010 salever@126.com. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     salever@126.com - initial API and implementation
 *
 * Create on  上午10:18:53 2011-8-18 ye2011
 *******************************************************************************/
package org.salever.rcp.tools.springxmlgenerator.utils;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.preference.IPreferenceStore;
import org.salever.rcp.tools.springxmlgenerator.Activator;
import org.salever.rcp.tools.springxmlgenerator.preferences.PreferenceConstants;

/**
 * @author LiXP
 *
 */
public class JavaClazzUtils {
	
	private static IPreferenceStore preferenceStore  = Activator.getDefault().getPreferenceStore();
	
	private static final String JAVA_POST_FIX = ".java";
	
	/**
	 * 
	 * @param unit
	 * @return
	 */
	public static String getBeanClass(ICompilationUnit unit) {
		String name = unit.getElementName();
		if(name.endsWith(JAVA_POST_FIX)){
			name = name.substring(0, name.length() - JAVA_POST_FIX.length());
		}
		String clazz = null;
		clazz = unit.getParent().getElementName() + "." +name;
		return clazz;
	}
	
	/**
	 * Get lower case character started id.
	 * @param unit
	 * @return
	 */
	public static String getBeanId(ICompilationUnit unit) {
		
//		boolean useInterfaceName = preferenceStore.getBoolean(PreferenceConstants.P_USE_INTERFACE_NAME);
		
		String name = unit.getElementName();
		if(name.endsWith(JAVA_POST_FIX)){
			name = name.substring(0, name.length() - JAVA_POST_FIX.length());
		}
		char startChar = name.charAt(0);
		if(Character.isUpperCase(startChar)){
			startChar = Character.toLowerCase(startChar);
		}
		name = startChar + name.substring(1);
		return name;
	}
	
	/**
	 * @param dirPath
	 * @return
	 */
	public static IPath getXMLFilePath(IPath dirPath) {
		
		boolean overWrite = preferenceStore.getBoolean(PreferenceConstants.P_OVERWRITE_EXIST_FILE);
		String defaultName = preferenceStore.getString(PreferenceConstants.P_DEFAULT_XML_NAME);
		
		IPath path = dirPath.append(defaultName);
		
		if(path.toFile().exists() && !overWrite){
			int index = 1;
			String newName = defaultName.replace(".", "_" +index + ".");
			path = dirPath.append(newName);
			while(path.toFile().exists()){
				index ++;
				newName = defaultName.replace(".", "_" +index + ".");
				path = dirPath.append(newName);
			}
		}
		
		return path;
	}

	/**
	 * 
	 */
	public JavaClazzUtils() {
	}
}
