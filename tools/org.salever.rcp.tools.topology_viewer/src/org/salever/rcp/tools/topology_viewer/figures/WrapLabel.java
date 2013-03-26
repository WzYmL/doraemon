/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-7 下午03:44:58
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.figures;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

/**
 * Automatically wrapped label.
 *
 * @author salever@126.com
 *
 */
public class WrapLabel extends FlowPage {

	private TextFlow textFlow;
	
	public WrapLabel() {
		this("");
	}
	
	public WrapLabel(String text){	
		textFlow = new TextFlow();
		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow, ParagraphTextLayout.WORD_WRAP_SOFT));
		setHorizontalAligment(PositionConstants.CENTER);
		textFlow.setText(text);
		add(textFlow);
	}
	
	public void setText(String text){
		textFlow.setText(text);
	}
	
	public String getText(){
		return textFlow.getText();
	}
}
