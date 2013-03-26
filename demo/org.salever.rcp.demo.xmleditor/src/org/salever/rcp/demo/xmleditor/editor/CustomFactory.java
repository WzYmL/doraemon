/**
 * 
 */
package org.salever.rcp.demo.xmleditor.editor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;

/**
 * @author salever@126.com
 * 
 */
public class CustomFactory {

	public static String IconSize = CustomConstants.LARGE_ICON;

	private static int currentNewCount = 0;

	public static boolean hasNewConfig = false;

	private static String cuttentFilePath = "";

	private static String mappingFilePath = "";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static String Date2String(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * @param startOffset
	 * @param length
	 * @return StyleRange
	 */
	private static StyleRange getBlueHighlightStyle(int startOffset, int length) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = startOffset;
		styleRange.length = length;

		styleRange.foreground = Display.getCurrent().getSystemColor(
				SWT.COLOR_BLUE);
		return styleRange;
	}

	private static int getCharType(char ch) {

		if (ch == '<') {
			return CustomConstants.TAG_BEGIN;
		} else if (ch == '>') {
			return CustomConstants.TAG_END;
		} else if (ch == '?') {
			return CustomConstants.TAG_HEAD;
		} else if (ch == '/') {
			return CustomConstants.TAG_NAIL;
		} else if (ch == '=') {
			return CustomConstants.TAG_EQUAL;
		} else if (ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t') {
			return CustomConstants.SPACE_OR_LINE;
		} else if (Character.isDigit(ch) || Character.isLetter(ch) || ch == '_'
				|| ch == '-') {
			return CustomConstants.XML_VAR;
		}
		return -1;
	}

	public static String getCurrentFilePath() {
		return cuttentFilePath;
	}

	/**
	 * @return the currentNewCount
	 */
	public static int getCurrentNewCount() {
		return currentNewCount;
	}

	/**
	 * @return the cuttentFilePath
	 */
	public static String getCuttentFilePath() {
		return cuttentFilePath;
	}

	/**
	 * 设置默认ID
	 * 
	 * @return
	 */
	public static String getDefaultID() {

		String id = "" + currentNewCount;
		currentNewCount++;
		return id;
	}

	/**
	 * 设置默认ID
	 * 
	 * @return
	 */
	public static int getID() {
		return currentNewCount++;
	}

	/**
	 * @return the mappingFilePath
	 */
	public static String getMappingFilePath() {
		return mappingFilePath;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String getNameFromPath(String path) {
		String name;
		if (path.contains("\\")) {
			name = path.substring(path.lastIndexOf("\\") + 1);
		} else if (path.contains("/")) {
			name = path.substring(path.lastIndexOf("/") + 1);
		} else {
			name = "";
		}
		return name;
	}

	// @SuppressWarnings("deprecation")
	// public static XMLParser getParser() {
	// return XMLParser.newInstance();
	// }

	/**
	 * @param startOffset
	 * @param length
	 * @return StyleRange
	 */
	private static StyleRange getRedHighlightStyle(int startOffset, int length) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = startOffset;
		styleRange.length = length;

		styleRange.foreground = Display.getCurrent().getSystemColor(
				SWT.COLOR_RED);
		return styleRange;
	}

	/**
	 * 分析字符串，准备高亮
	 * 
	 * @param text
	 * @return
	 */
	public static ArrayList<StyleRange> getXMLHightlightRange(String text) {
		ArrayList<StyleRange> rangeList = new ArrayList<StyleRange>();
		char ch = '0';
		StyleRange range;
		int state = CustomConstants.STATE_TAG;
		int index = 0;
		int startOffSet = 0;
		int lengthOffSet = 0;

		while (index < text.length()) {

			ch = text.charAt(index);

			switch (state) {
			case CustomConstants.STATE_TAG: // 标签
				if (ch == '/' || ch == '>'
						|| getCharType(ch) == CustomConstants.SPACE_OR_LINE) {
					index++;
					break;
				} else if (ch == '<' || ch == '?') {
					state = CustomConstants.STATE_ELEMENT;
					index++;
					break;
				}

			case CustomConstants.STATE_ELEMENT: // 节点
				if (getCharType(ch) == CustomConstants.XML_VAR || ch == '/') {
					index++;
					break;
				} else if (getCharType(ch) == CustomConstants.SPACE_OR_LINE) {
					index++;
					state = CustomConstants.STATE_ATTRIBUTE;
					break;
				} else if (ch == '>') {
					index++;
					state = CustomConstants.STATE_TAG;
					break;
				} else if (ch == '?') {
					index++;
					break;
				}

			case CustomConstants.STATE_ATTRIBUTE: // 属性
				if (getCharType(ch) == CustomConstants.XML_VAR) {
					if (startOffSet == 0) {
						startOffSet = index;
					}
					index++;
					break;
				} else if (getCharType(ch) == CustomConstants.SPACE_OR_LINE
						|| ch == '=') {
					if (startOffSet == 0) {
						index++;
						break;
					}
					lengthOffSet = index - startOffSet;
					range = getRedHighlightStyle(startOffSet, lengthOffSet);
					rangeList.add(range);
					startOffSet = 0;
					lengthOffSet = 0;
					index++;
					state = CustomConstants.STATE_VALUE;
					break;
				}

			case CustomConstants.STATE_VALUE: // 值
				/*
				 * if(getCharType(ch) == CustomConstants.SPACE_OR_LINE || ch ==
				 * '='){ index ++; break; }else
				 */
				if (ch == '\'' || ch == '"') {
					if (startOffSet == 0) {
						index++;
						startOffSet = index;
						break;
					} else {
						lengthOffSet = index - startOffSet;
						range = getBlueHighlightStyle(startOffSet, lengthOffSet);
						rangeList.add(range);
						startOffSet = 0;
						lengthOffSet = 0;
						index++;
						state = CustomConstants.STATE_AFTER_VALUE;
						break;
					}
				} else {
					index++;
					break;
				}

			case CustomConstants.STATE_AFTER_VALUE: // 值之后
				if (getCharType(ch) == CustomConstants.SPACE_OR_LINE) {
					index++;
					break;
				} else if (ch == '/' || ch == '>' || ch == '?') {
					index++;
					state = CustomConstants.STATE_TAG;
					break;
				} else if (getCharType(ch) == CustomConstants.XML_VAR) {
					state = CustomConstants.STATE_ATTRIBUTE;
					break;
				} else {
					index++;
				}

			case CustomConstants.STATE_FINISH: // 完成

			}
		}

		return rangeList;
	}

	public static void setCurrentFilePath(String path) {
		cuttentFilePath = path;
	}

	/**
	 * @param currentNewCount
	 *            the currentNewCount to set
	 */
	public static void setCurrentNewCount(int currentNewCount) {
		CustomFactory.currentNewCount = currentNewCount;
	}

	/**
	 * @param cuttentFilePath
	 *            the cuttentFilePath to set
	 */
	public static void setCuttentFilePath(String cuttentFilePath) {
		CustomFactory.cuttentFilePath = cuttentFilePath;
	}

	/**
	 * @param mappingFilePath
	 *            the mappingFilePath to set
	 */
	public static void setMappingFilePath(String mappingFilePath) {
		CustomFactory.mappingFilePath = mappingFilePath;
	}

}
