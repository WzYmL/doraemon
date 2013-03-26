/**
 * @created on 2010-12-9 上午09:33:30
 */
package org.salever.common.swtjface.richhtml4eclipse.widgets;

import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.salever.common.swtjface.richhtml4eclipse.Activator;

/**
 * @author
 */
public class TinyMCEPath {
	/** The real path to the tinyMce HTML Page */
	public static final String BASE_PATH = "tiny_mce/base/base.htm"; //$NON-NLS-1$

	private static TinyMCEPath currentPath = new TinyMCEPath();

	private TinyMCEPath() {
		path = "";
	}

	public static TinyMCEPath getCurrentPath() {
		return currentPath;
	}

	private String path;

	public String getUrl() {
		return path;
	}

	public void setUrl(String url) {
		this.path = url;
	}

	/**
	 * 插件环境下获取tiny MCE 路径
	 * @return
	 */
	public static String resolveBaseHtmlUnderPlugin() {

		// 插件环境
		if (Activator.getDefault() != null) {
			try {
				return FileLocator.resolve(
						Activator.getDefault().getBundle().getEntry(BASE_PATH))
						.toString();
			} catch (IOException e) {
				throw new IllegalStateException("Invalid state", e); //$NON-NLS-1$
			}
		}
		return "";
	}

}
