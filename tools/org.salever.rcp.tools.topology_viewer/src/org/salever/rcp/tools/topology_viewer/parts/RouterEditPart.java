/**
 * @file HelloEditPart.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����02:21:48
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LabelAnchor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tools.topology_viewer.Activator;
import org.salever.rcp.tools.topology_viewer.editors.TextEditor;
import org.salever.rcp.tools.topology_viewer.editors.TextEditorInput;
import org.salever.rcp.tools.topology_viewer.figures.RouterFigure;
import org.salever.rcp.tools.topology_viewer.figures.TopoFigure;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;
import org.salever.rcp.tools.topology_viewer.policies.CustomComponentEditPolicy;
import org.salever.rcp.tools.topology_viewer.policies.CustomDirectEditPolicy;
import org.salever.rcp.tools.topology_viewer.policies.CustomGraphicalNodeEditPolicy;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 * @name HelloEditPart
 * 
 */
public class RouterEditPart extends EditPartWithListener implements
		NodeEditPart {

	private CustomDirectEditManager manager = null;

	private IWorkbenchPage page = Activator.getDefault().getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new CustomComponentEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new CustomDirectEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new CustomGraphicalNodeEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		Router model = (Router) getModel();
		RouterFigure figure = new RouterFigure(model);
		model.setConstraint(figure.getBounds());
		return figure;

	}

	/**
	 * 
	 */
	@Override
	public List<Object> getModelSourceConnections() {
		return ((Router) getModel()).getSourceConnection();
	}

	/**
	 * 
	 */
	@Override
	public List<Object> getModelTargetConnections() {
		return ((Router) getModel()).getTargetConnection();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		/* return new LabelAnchor((Label) getFigure()); */
		return new LabelAnchor(((TopoFigure) getFigure()).getImage());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		/* return new LabelAnchor((Label) getFigure()); */
		return new LabelAnchor(((TopoFigure) getFigure()).getImage());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		/* return new LabelAnchor((Label) getFigure()); */
		return new LabelAnchor(((TopoFigure) getFigure()).getImage());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		/* return new LabelAnchor((Label) getFigure()); */
		return new LabelAnchor(((TopoFigure) getFigure()).getImage());
	}

	private void performDirectEdit() {
		if (manager == null) {
			manager = new CustomDirectEditManager(this, TextCellEditor.class,
					new CustomCellEditorLocator(getFigure()));
		}
		manager.show();
	}

	@SuppressWarnings("unused")
	private void performDoubleClick() {
		Router model = (Router) getModel();
		String path = model.getConfigFile();

		if (path.equals("")) {
			if (!MessageDialog.openConfirm(Display.getCurrent()
					.getActiveShell(), Messages
					.getString("Message.Confirm_Title"), Messages
					.getString("Message.Confirm_Text"))) {
				return;
			}
			FileDialog dlg = new FileDialog(Display.getCurrent()
					.getActiveShell(), SWT.OPEN);
			dlg.setText(Messages.getString("Dialog.Select_Config_File"));
			dlg.setFilterExtensions(new String[] { "*.cfg", "*.*" });
			path = dlg.open();
			if (path != null) {
				model.setConfigFile(path);
				model.setInput(new TextEditorInput(path));
				CustomFactory.hasNewConfig = true;
			} else {
				return;
			}
		}

		IEditorInput input = model.getInput();
		try {
			IEditorPart editor = page.findEditor(input);
			if (editor != null) {
				page.bringToTop(editor);
			} else {
				page.openEditor(input, TextEditor.ID);
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void performRequest(Request req) {
		super.performRequest(req);

		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			performDirectEdit();
			return;
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals(TopoModel.P_CONSTRAINT)) {
			refreshVisuals();
		} else if (arg0.getPropertyName().equals(TopoModel.P_NAME)) {
			RouterFigure label = (RouterFigure) getFigure();
			label.setName((String) arg0.getNewValue());
		} else if (arg0.getPropertyName().equals(TopoModel.P_SOURCE_CONNECTION)) {
			refreshSourceConnections();
		} else if (arg0.getPropertyName().equals(TopoModel.P_TARGET_CONNECTION)) {
			refreshTargetConnections();
		}
	}

	@Override
	protected void refreshVisuals() {
		Router model = (Router) getModel();
		Rectangle constraint = model.getConstraint();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), constraint);
	}
	
	/**
	 * 
	 * @return
	 */
	public Router getCastedModel(){
		return (Router)getModel();
	}

}
