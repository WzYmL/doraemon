package org.salever.common.swtjface.htmleditor.actions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.RGB;

/**
 * @author Carlos M�ndez P�rez, Javier S�ez Gasc�n
 * 
 */
public class jsCommands {

	public jsCommands() {
		super();
	}

	public static boolean selectedText(Browser b, String text) {
		String cad = "";

		cad = cad + "var txt = '';";
		cad = cad + "if (window.getSelection)";
		cad = cad + "{";
		cad = cad + "	txt = window.getSelection();";
		cad = cad + "}";
		cad = cad + "else if (document.getSelection)";
		cad = cad + "{";
		cad = cad + "	txt = document.getSelection();";
		cad = cad + "}";
		cad = cad + "else if (document.selection)";
		cad = cad + "{";
		cad = cad + "	txt = document.selection.createRange().text;";
		cad = cad + "}";
		cad = cad + "if (txt == '') txt = '" + text + "'";
		return b.execute(cad);
	}

	public static boolean Write(Browser b, String text) {
		String cad = "";
		// b.execute("sel=frmEditor.document.getSelection();");
		/*
		 * cad = "frmEditor.document.value += '" + text + "';";
		 * System.out.println(cad); return b.execute(cad);
		 */
		cad = cad + "frmEditor.document.write('" + text + "');";
		return b.execute(cad);
	}

	public static String table(Integer rows, Integer columns, String width,
			String border) {
		int i, j;
		String result;
		if (border == "" || border == null)
			border = "0";
		if (width == "" || width == null)
			width = "100%";
		result = "<table border=\"" + border + "\" width=\"" + width + "\">";
		for (i = 0; i < rows.intValue(); i++) {
			result = result + "<tr>";
			for (j = 0; j < columns.intValue(); j++) {
				result = result + "<td>&nbsp;</td>";
			}
			result = result + "</tr>";
		}
		result = result + "</table>";
		return result;
	}

	public static String color(RGB color, String text) {
		String result = null;
		String aux = null;
		String r, g, b = null;
		r = Integer.toHexString(color.red);
		g = Integer.toHexString(color.green);
		b = Integer.toHexString(color.blue);
		if (r == null)
			r = "00";
		if (g == null)
			g = "00";
		if (b == null)
			b = "00";
		aux = "#" + r + g + b;
		aux = aux.toUpperCase();
		result = aux;
		// result = "<font color=\"" + aux +"\">"+ text + "</font>";
		return result;
	}

	public static boolean executeCommand(Browser b, String command,
			String ifaz, String add) {
		boolean result;
		result = b.execute("frmEditor.document.execCommand('" + command
				+ "', '" + ifaz + "', '" + add + "');");
		return result;
	}

	public static String hiperLink(String text, String title, String url,
			String target) {
		String link = null;
		if (text.length() == 0)
			text = url;
		link = "<a ";
		if (target.length() != 0) {
			link = link + " target=\"" + target + "\"";
		}
		if (title.length() != 0) {
			link = link + " title=\"" + title + "\"";
		}
		if (url.length() != 0) {
			link = link + " href=\"" + url + "\"";
		}
		link = link + ">";
		if (text.length() != 0) {
			link = link + text;
		}
		link = link + "</a>";
		return link;
	}

	public static String createFrame(String url, String textArea) {
		String aux = null;
		if (url == null)
			url = "about:blank";
		if (textArea == null)
			textArea = "";
		aux = "<html>"
				+ "<title>"
				+ "</title>"
				+ "<body topmargin=0 leftmargin=0>"
				+ "<textarea style=\"visibility: hidden;\" rows=\"20\" cols=\"80\" id=\"txtArea\" name=\"txtArea\">"
				+ textArea
				+ "</textarea>"
				+ "<iframe bgcolor=\"#FFFFFF\" height=\"100%\" width=\"100%\" name=\"frmEditor\" "
				+ "id=\"frmEditor\" "
				+ "style=\"position: absolute; top: 0px; left: 0px;\" "
				+ "src=\"" + url + "\">" + "</iframe>" + "</body>" + "</html>";
		return aux;

	}

	public static boolean editMode(Browser browser) {
		boolean result;
		final String frameEditor = "document.getElementById('frmEditor')"
				+ ".contentWindow.document.body.innerHTML"
				+ "="
				+ "document.getElementById('txtArea').value;"
				+ "document.frmEditor.document.designMode=\"on\";document.frmEditor.document.execCommand(\"EditMode\");document.frmEditor.document.execCommand(\"LiveResize\", false, true);document.frmEditor.document.execCommand(\"2D-Position\", false, true);document.frmEditor.document.execCommand(\"MultipleSelection\", false, true);";

		System.out.print("EditMode ");
		if ((result = browser.execute(frameEditor)) == false) {
			System.out.println("Error in edit Mode command..." + result);
		} else {
			System.out.println("Enabled " + result);
		}

		final String frameEditor1 = "document.getElementById('frmEditor').contentWindow.document.designMode = \"on\";"
				+ "try {"
				+ "  document.getElementById('frmEditor').contentWindow.document.execCommand(\"undo\", false, null);"
				+ "}  catch (e) {"
				+ "  alert(\"This editor is not supported on your OS.\");"
				+ "}";

		System.out.print("EditMode ");
		if ((result = browser.execute(frameEditor1)) == false) {
			System.out.println("Error in edit Mode command..." + result);
		} else {
			System.out.println("Enabled " + result);

		}

		// copyTextArea2iFrame (browser);
		return result;
	}

	public static boolean copyiFrame2TextArea_bk(Browser browser) {
		boolean result;
		String cad = "txtArea.value=frmEditor.document.body.innerHTML;"
				+ "parent.document.getElementById('frmEditor').style.display = 'none';";
		result = browser.execute(cad);
		System.out.println(browser.getText());

		return result;
	}
	
	public static boolean copyiFrame2TextArea(Browser browser) {
		boolean result = false;
		String cad = "txtArea.value=frmEditor.document.body.innerHTML;"
				+ "parent.document.getElementById('frmEditor').style.display = 'none';";
		result = browser.execute(cad);
		System.out.println(browser.getText());
		return result;
	}

	public static boolean copyTextArea2iFrame(Browser browser) {
		boolean result;
		String cad = "frmEditor.document.body.innerHTML=txtArea.value;"
				+ "parent.document.getElementById('frmEditor').style.display = '';";
		result = browser.execute(cad);
		System.out.println(browser.getText());
		return result;
	}

	public static String setTextAreaValue(String value) {
		String aux;
		if (value != null) {
			/*
			 * Pattern p = Pattern.compile("\""); Matcher m = p.matcher(value);
			 * value = m.replaceAll("\\\"");
			 */
			// value = value.replaceAll("\"","\\\\\"");

		}

		// value = value.replaceAll("\"", "\\\"");
		aux = "document.getElementById('txtArea').value ='" + value + "';";
		return value;
	}

	public static String image(String border, String uri, String width,
			String height, String title) {
		String cad;
		if (border == "")
			border = "0";
		cad = "<img border=" + border + " src=\"" + uri + "\" ";
		if (width != "")
			cad = cad + "width=" + width + " ";
		if (height != "")
			cad = cad + "height=" + height + " ";
		cad = cad + "title=\"" + title + "\"  alt=\"" + title + "\" />";
		return cad;
	}

}
