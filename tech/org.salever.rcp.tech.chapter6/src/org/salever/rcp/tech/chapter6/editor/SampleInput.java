package org.salever.rcp.tech.chapter6.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * @author 
 *
 */
public class SampleInput implements IEditorInput{

	/**
	 * ½ÚµãÃû³Æ
	 */
	private String name = "";
	
	public SampleInput(String name) {
		this.name = name;
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;

		}

		if (obj instanceof SampleInput) {
			return name.equals(((SampleInput) obj).getName());

		}
		return false;

	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
