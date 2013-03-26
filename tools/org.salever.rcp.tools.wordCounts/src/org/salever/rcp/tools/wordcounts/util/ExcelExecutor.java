/**
 * @created on 2011-1-7 上午10:12:42
 */
package org.salever.rcp.tools.wordcounts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.salever.rcp.tools.wordcounts.models.CountModel;

/**
 * @author
 */
public class ExcelExecutor {

	/**
	 * 读取excel示例
	 * 
	 * @param path
	 */
	public static void read(String path) {
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			Workbook workbook = Workbook.getWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheet(0);
			Cell cell = sheet.getCell(1, 1); // 参数为：列,行
			String content = cell.getContents();
			System.out.println(content);
			workbook.close();
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入excel
	 * 
	 * @param path
	 * @return
	 */
	public static boolean write(String path) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			WritableWorkbook writableWorkbook = Workbook
					.createWorkbook(fileOutputStream);
			initSheet(writableWorkbook, null);
			writableWorkbook.write();
			writableWorkbook.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void update(String path) {
		try {
			// Excel获得文件
			Workbook wb = Workbook.getWorkbook(new File(path));
			// 打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book = Workbook.createWorkbook(new File(path), wb);
			// 添加一个工作表
			WritableSheet sheet = book.createSheet(Messages.ExcelExecutor_0, 1);
			sheet.addCell(new Label(0, 0, Messages.ExcelExecutor_1));
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 初始化内容，示例
	 * 
	 * @param writableWorkbook
	 * @param models
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private static void initSheet(WritableWorkbook writableWorkbook,
			List<CountModel> models) throws RowsExceededException,
			WriteException {
		WritableSheet writableSheet = null;
		writableSheet = writableWorkbook.createSheet(Messages.ExcelExecutor_2, 0);

		// WritableSheet.setRowView( int i, int height);
		// 作用是指定第i+1行的高度，比如：

		// 将第一行的高度设为200
		// writableSheet.setRowView(0, 100);

		// WritableSheet.setColumnView( int i, int width);
		// 作用是指定第i+1列的宽度，比如：
		// 将第一列的宽度设为30
		// writableSheet.setColumnView(0, 400);

		Label label = null;
		WritableFont font1 = new WritableFont(WritableFont.TIMES, 12,
				WritableFont.BOLD);

		WritableCellFormat format1 = new WritableCellFormat(font1);
		// 把水平对齐方式指定为居中
		format1.setAlignment(jxl.format.Alignment.CENTRE);

		// 把垂直对齐方式指定为居中
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		label = new Label(0, 0, Messages.ExcelExecutor_3);
		writableSheet.addCell(label);
		label = new Label(1, 0, Messages.ExcelExecutor_4);
		writableSheet.addCell(label);
		label = new Label(2, 0, Messages.ExcelExecutor_5);
		writableSheet.addCell(label);
		label = new Label(3, 0, Messages.ExcelExecutor_6);
		writableSheet.addCell(label);

		int row = 1;
		for (CountModel model : models) {

			for (int column = 0; column < 4; column++) {
				switch (column) {
				case 0:
					label = new Label(column, row, model.getWord());
					break;
				case 1:
					label = new Label(column, row, model.getCounts() + ""); //$NON-NLS-1$
					break;
				case 2:
					label = new Label(column, row, model.getTotalCounts() + ""); //$NON-NLS-1$
					break;
				case 3:
					label = new Label(column, row, format(model.getRate()));
					break;
				default:
					break;
				}
				writableSheet.addCell(label);
			}
			row++;
		}
	}

	public static String format(double rate) {
		DecimalFormat df5 = new DecimalFormat("0.000"); //$NON-NLS-1$
		String result = df5.format(rate * 100);
		return result + "%"; //$NON-NLS-1$
	}

	/**
	 * 
	 * @param models
	 * @param path
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void writeModels(List<CountModel> models, String path)
			throws IOException, RowsExceededException, WriteException {
		FileOutputStream fileOutputStream = new FileOutputStream(path);
		WritableWorkbook writableWorkbook = Workbook
				.createWorkbook(fileOutputStream);
		initSheet(writableWorkbook, models);
		writableWorkbook.write();
		writableWorkbook.close();
		fileOutputStream.close();

	}
}
