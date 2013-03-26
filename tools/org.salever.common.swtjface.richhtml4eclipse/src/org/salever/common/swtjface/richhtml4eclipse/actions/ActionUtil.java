/**
 * 
 */
package org.salever.common.swtjface.richhtml4eclipse.actions;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author LiXiaopeng
 * 
 */
public class ActionUtil {

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return ImageDescriptor.createFromFile(ActionUtil.class, path);
	}
}
