/**
 * 
 */
package org.salever.common.swtjface.extend.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

/**
 * @author oracle
 *
 */
public final class GUIConstants {

	private GUIConstants(){
		
	}
	
	public static final String CONSTITUTION_MAIN_TYPE = "体质类型";
	
	public static final String CONSTITUTION_SUB_TYPE = "体质倾向";
	
	public static final String CROSS_CONSTITUION = "交叉体质";
	
	public static final String SUBHEALTH_MAIN_TYPE = "亚健康主类型";
	
	public static final String SUBHEALTH_SUB_TYPE = "亚健康子类型";
	
	public static final Font QUESTION_FONT = new Font(null,"宋体", 10, SWT.NORMAL);

	public static final int FIELD_DESCEPTION_LABEL_WIDTH = 100;

	public static final int EXAM_FIELD_DESCEPTION_LABEL_WIDTH = 130;
}
