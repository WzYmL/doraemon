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
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.salever.rcp.tools.topology_viewer.Activator;
import org.salever.rcp.tools.topology_viewer.editors.DiagramEditor;
import org.salever.rcp.tools.topology_viewer.editors.DiagramEditorInput;
import org.salever.rcp.tools.topology_viewer.figures.SubnetFigure;
import org.salever.rcp.tools.topology_viewer.figures.TopoFigure;
import org.salever.rcp.tools.topology_viewer.models.ModelUtil;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.Subnet;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;
import org.salever.rcp.tools.topology_viewer.policies.CustomComponentEditPolicy;
import org.salever.rcp.tools.topology_viewer.policies.CustomDirectEditPolicy;
import org.salever.rcp.tools.topology_viewer.policies.CustomGraphicalNodeEditPolicy;


/**
 * @author salever@126.com
 * 
 * @name HelloEditPart
 * 
 */
public class SubnetEditPart extends EditPartWithListener implements
		NodeEditPart {

	private CustomDirectEditManager manager = null;

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
		Subnet model = (Subnet) getModel();
		SubnetFigure figure = new SubnetFigure(model);
		model.setConstraint(figure.getBounds());
		return figure;
	}

	/**	
	 * 
	 */
	@Override
	public List<Object> getModelSourceConnections() {
		return ((Subnet) getModel()).getSourceConnection();
	}

	/**
	 * 
	 */
	@Override
	public List<Object> getModelTargetConnections() {
		return ((Subnet) getModel()).getTargetConnection();
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
		return new LabelAnchor(((TopoFigure) getFigure()).getImage());
	}

	private void performDirectEdit() {
		if (manager == null) {
			manager = new CustomDirectEditManager(this, TextCellEditor.class,
					new CustomCellEditorLocator(getFigure()));
		}
		manager.show();
	}

	private void performDoubleClick() {
		Subnet subnet = (Subnet) getModel();
		Network network = ModelUtil.transform(subnet);
		DiagramEditorInput input = new DiagramEditorInput();
		input.setNetwork(network);
		IWorkbenchPage page = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = page.findEditor(input);
		if (part != null) {
			page.bringToTop(part);
		} else {
			try {
				page.openEditor(input, DiagramEditor.ID);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void performRequest(Request req) {

		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			performDirectEdit();
			return;
		}

		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			performDoubleClick();
			return;
		}

		super.performRequest(req);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals(TopoModel.P_CONSTRAINT)) {
			refreshVisuals();
		} else if (arg0.getPropertyName().equals(TopoModel.P_NAME)) {
			TopoFigure figure = (TopoFigure) getFigure();
			figure.setName((String) arg0.getNewValue());
		} else if (arg0.getPropertyName().equals(TopoModel.P_SOURCE_CONNECTION)) {
			refreshSourceConnections();
		} else if (arg0.getPropertyName().equals(TopoModel.P_TARGET_CONNECTION)) {
			refreshTargetConnections();
		}
	}

	@Override
	protected void refreshVisuals() {
		Subnet subnet = (Subnet) getModel();
		Rectangle constraint = subnet.getConstraint();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), constraint);

	}

	/**
	 * 
	 * @return
	 */
	public Subnet getCastedModel() {
		return (Subnet) getModel();
	}

}
