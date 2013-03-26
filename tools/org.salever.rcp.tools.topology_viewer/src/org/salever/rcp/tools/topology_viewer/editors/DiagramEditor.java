/**
 * @file DiagramEditor.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  涓嬪崍12:16:28
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.salever.rcp.tools.topology_viewer.actions.OpenConfigureAction;
import org.salever.rcp.tools.topology_viewer.menu.LabelContextMenuProvider;
import org.salever.rcp.tools.topology_viewer.models.DoubleArrowConnection;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.models.Subnet;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;
import org.salever.rcp.tools.topology_viewer.parts.CustomEditPartFactory;
import org.salever.rcp.tools.topology_viewer.parts.RootEditPart;
import org.salever.rcp.tools.topology_viewer.parts.tree.TreeEditPartFactory;
import org.salever.rcp.tools.topology_viewer.splitter.MySplitter;
import org.salever.rcp.tools.topology_viewer.system.CustomConstants;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlUtil;

/**
 * @author salever@126.com
 * 
 * @name DiagramEditor
 * 
 */
public class DiagramEditor extends GraphicalEditorWithPalette {

	class MyContentOutlinePage extends ContentOutlinePage {

		private SashForm sashForm;

		// 实现鹰眼的图形
		private ScrollableThumbnail thumbnail;

		private DisposeListener disposeListener;

		public MyContentOutlinePage() {
			super(new TreeViewer());
		}

		@Override
		public void createControl(Composite parent) {

			sashForm = new SashForm(parent, SWT.VERTICAL);
			getViewer().createControl(sashForm);
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new TreeEditPartFactory());
			getViewer().setContents(network);
			getSelectionSynchronizer().addViewer(getViewer());

			Canvas canvas = new Canvas(sashForm, SWT.NONE);
			LightweightSystem ls = new LightweightSystem(canvas);

			thumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer()
							.getRootEditPart()).getFigure());
			thumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));

			ls.setContents(thumbnail);

			disposeListener = new DisposeListener() {
				public void widgetDisposed(DisposeEvent e) {
					// 删除绘制的图形
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(
					disposeListener);
		}

		@Override
		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());

			if (getGraphicalViewer().getControl() != null
					&& !getGraphicalViewer().getControl().isDisposed()) {
				getGraphicalViewer().getControl().removeDisposeListener(
						disposeListener);
			}
			super.dispose();
		}

		@Override
		public Control getControl() {
			return sashForm;
		}

		@Override
		public void init(IPageSite pageSite) {
			super.init(pageSite);

			String id;
			IActionBars bars = pageSite.getActionBars();
			ActionRegistry registry = getActionRegistry();

			id = ActionFactory.UNDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));

			id = ActionFactory.REDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));

			id = ActionFactory.DELETE.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));

			bars.updateActionBars();
		}
	}

	public static final String ID = "org.salever.rcp.tools.topology_viewer.editors.DiagramEditor";

	private GraphicalViewer viewer;

	private Network network = new Network();

	private boolean dirty = false;

	private CommandStackListener listener = new CommandStackListener() {

		@Override
		public void commandStackChanged(EventObject event) {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}

	};

	public DiagramEditor() {
		this.setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		viewer = getGraphicalViewer();

		ScalableRootEditPart rootPart = new RootEditPart();
		// viewer.getControl().setBackground(new Color(null,250,250,245));

		viewer.setRootEditPart(rootPart);
		viewer.setEditPartFactory(new CustomEditPartFactory());
		viewer.setContextMenu(new LabelContextMenuProvider(viewer,
				getActionRegistry()));

		ZoomManager manager = rootPart.getZoomManager();

		IAction zoomInAction = new ZoomInAction(manager);
		IAction zoomOutAction = new ZoomOutAction(manager);
		getActionRegistry().registerAction(zoomInAction);
		getActionRegistry().registerAction(zoomOutAction);

		KeyHandler handler = new KeyHandler();
		handler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry()
				.getAction(ActionFactory.DELETE.getId()));
		handler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry()
				.getAction(GEFActionConstants.DIRECT_EDIT));

		getGraphicalViewer().setKeyHandler(handler);
		getCommandStack().addCommandStackListener(listener);

	}

	@Override
	@SuppressWarnings("unchecked")
	protected void createActions() {
		super.createActions();

		ActionRegistry registry = getActionRegistry();
		IAction action = new DirectEditAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.CENTER);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.TOP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.MIDDLE);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this,
				PositionConstants.BOTTOM);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new OpenConfigureAction(this, 1);
		action.setId(CustomConstants.ACTION_OPEN_CONFIG_TARGET);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	public void createPartControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		MySplitter splitter = new MySplitter(composite, SWT.HORIZONTAL);
		createPaletteViewer(splitter);
		createGraphicalViewer(splitter);
		splitter.maintainSize(getPaletteViewer().getControl());
		splitter.setFixedSize(getInitialPaletteSize());
		splitter.addFixedSizeChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				handlePaletteResized(((MySplitter) evt.getSource())
						.getFixedSize());
			}
		});

	}

	@Override
	public void doSave(IProgressMonitor monitor) {

		synchronize();
		getCommandStack().markSaveLocation();
		dirty = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
		writeToXmlFile(network);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class) {
			return new MyContentOutlinePage();
		}
		return super.getAdapter(type);
	}

	@Override
	protected PaletteRoot getPaletteRoot() {

		PaletteRoot root = new PaletteRoot();

		PaletteGroup toolGroup = new PaletteGroup(Messages.getString("Tool"));
		ToolEntry tool = new SelectionToolEntry();
		toolGroup.add(tool);
		root.setDefaultEntry(tool);

		tool = new MarqueeToolEntry();
		toolGroup.add(tool);

		PaletteDrawer drawer = new PaletteDrawer(Messages.getString("Draw"));
		ImageDescriptor descriptor = ImagesContext
				.getImageDescriptor(ImagesContext.PALETTE_ROUTER);

		CreationToolEntry roueterEntry = new CreationToolEntry(
				Messages.getString("Router"), "Router", new SimpleFactory(
						Router.class), descriptor, descriptor);
		drawer.add(roueterEntry);

		descriptor = ImagesContext
				.getImageDescriptor(ImagesContext.PALETTE_SUBNET);
		CreationToolEntry subnetEntry = new CreationToolEntry("Subnet",
				"Router", new SimpleFactory(Subnet.class), descriptor,
				descriptor);
		drawer.add(subnetEntry);

		PaletteDrawer connectionDrawer = new PaletteDrawer(
				Messages.getString("Connection"));
		ImageDescriptor SimpleConnectionDescriptor = ImagesContext
				.getImageDescriptor(ImagesContext.DOUBLE_ARROW);
		ConnectionCreationToolEntry connectionCreationEntry = new ConnectionCreationToolEntry(
				Messages.getString("Double_Connection"), "Connection",
				new SimpleFactory(DoubleArrowConnection.class),
				SimpleConnectionDescriptor, SimpleConnectionDescriptor);

		connectionDrawer.add(connectionCreationEntry);
		root.add(toolGroup);
		root.add(drawer);
		root.add(connectionDrawer);

		return root;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
		this.network = ((DiagramEditorInput) input).getNetwork();
		super.init(site, input);
	}

	@Override
	protected void initializeGraphicalViewer() {

		viewer.setContents(network);
		viewer.flush();
		dirty = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
		setPartName(network.getName());

	}

	@Override
	public boolean isDirty() {
		return (dirty || getCommandStack().isDirty() || CustomFactory.hasNewConfig);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return network.getLevel() == Network.TOP_LEVEL;
	}

	@Deprecated
	public void switchIconSize(String iconType) {
		List<TopoModel> routerList = network.getChildren();

		for (Iterator<TopoModel> it = routerList.iterator(); it.hasNext();) {
			if (iconType.equals(CustomConstants.SMALL_ICON)) {
				// /router.setIconType(CustomConstants.LARGE_ICON);
				CustomFactory.IconSize = CustomConstants.LARGE_ICON;
			} else {
				// router.setIconType(CustomConstants.SMALL_ICON);
				CustomFactory.IconSize = CustomConstants.SMALL_ICON;
			}

		}

		viewer.setContents(network);
		viewer.flush();
	}

	private void synchronize() {
		if (network.getLevel() == Network.TOP_LEVEL) {
			return;
		}
		Subnet subnet = network.getContent();
		for (Iterator<TopoModel> it = network.getChildren().iterator(); it
				.hasNext();) {
			TopoModel model = it.next();
			if (!subnet.hasChild(model)) {
				if (model instanceof Router) {
					subnet.addRouter((Router) model);
				} else {
					subnet.addSubnet((Subnet) model);
				}
			}
		}
	}

	private void writeToXmlFile(Network network) {
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.xml", "*.X" });
		dlg.setText("Please choose a file to save mapping file:");
		String path = dlg.open();
		if (path != null) {
			XmlUtil.generateMappingFile(network, path);
		}
	}
}
