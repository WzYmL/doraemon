/*******************************************************************************
 * Copyright (c) 2004, 2005 Elias Volanakis and others.
?* All rights reserved. This program and the accompanying materials
?* are made available under the terms of the Eclipse Public License v1.0
?* which accompanies this distribution, and is available at
?* http://www.eclipse.org/legal/epl-v10.html
?*
?* Contributors:
?*????Elias Volanakis - initial API and implementation
?*******************************************************************************/
package org.salever.gef.examples.layersupport.shapes.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import org.salever.gef.examples.layersupport.shapes.model.Connection;
import org.salever.gef.examples.layersupport.shapes.model.Container;
import org.salever.gef.examples.layersupport.shapes.model.Shape;
import org.salever.gef.examples.layersupport.shapes.model.ShapesDiagram;

/**
 * Factory that maps model elements to edit parts.
 * @author Elias Volanakis
 */
public class ShapesEditPartFactory implements EditPartFactory {

/*
 * (non-Javadoc)
 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
 */
public EditPart createEditPart(EditPart context, Object modelElement) {
	// get EditPart for model element
	EditPart part = getPartForElement(modelElement);
	// store model element in EditPart
	part.setModel(modelElement);
	return part;
}

/**
 * Maps an object to an EditPart. 
 * @throws RuntimeException if no match was found (programming error)
 */
private EditPart getPartForElement(Object modelElement) {
	if (modelElement instanceof ShapesDiagram) {
		return new DiagramEditPart();
	}
	if (modelElement instanceof Container) {
		return new ContainerEditPart();
	}
	if (modelElement instanceof Shape) {
		return new ShapeEditPart();
	}
	if (modelElement instanceof Connection) {
		return new ConnectionEditPart();
	}

	throw new RuntimeException(
			"Can't create part for model element: "
			+ ((modelElement != null) ? modelElement.getClass().getName() : "null"));
}

}