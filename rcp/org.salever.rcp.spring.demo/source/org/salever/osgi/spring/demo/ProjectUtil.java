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
 * Create on  上午11:09:46 2011-10-9 ye2011
 *******************************************************************************/
package org.salever.osgi.spring.demo;

/**
 * @author LiXP
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
 
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.plugin.AbstractUIPlugin;
 
 
/**
 * 用于插件项目和非插件项目，提供两者通用的方法接口
 * @author chengang 2006-3-30
 */
public class ProjectUtil {
 
    private static AbstractUIPlugin plugin = Activator.getDefault();
 
    private ProjectUtil() {}
 
    /**
     * 判断当前的运行状态是否为插件方式
     * @return true=插件方式运行
     */
    private static boolean isPlugin() {
        return plugin != null;
    }
 
    public static URL getURL(String path) {
        if (isPlugin())//如果是插件
            return FileLocator.find(plugin.getBundle(), new Path(path), null);
        else
            try {
                return new URL("file:" + path);
            } catch (MalformedURLException e) {
                throw new RuntimeException(path + " is error", e);
            }
    }
 
    public static InputStream getInputStream(String path) {
        URL url = getURL(path);
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static String toFullPath(String path) {
        if (isPlugin()) {
            try {
                return FileLocator.toFileURL(ProjectUtil.getURL(path)).getPath();
            } catch (IOException e) {
                throw new RuntimeException(path + " toFullPath is fault", e);
            }
        } else {
            return path;
        }
    }
 
}