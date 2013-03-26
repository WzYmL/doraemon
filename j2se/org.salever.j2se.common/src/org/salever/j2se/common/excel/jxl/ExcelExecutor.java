/**
 * @created on 2011-1-7 上午10:12:42
 */
package org.salever.j2se.common.excel.jxl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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
			initSheet(writableWorkbook);
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
			WritableSheet sheet = book.createSheet(" 第二页 ", 1);
			sheet.addCell(new Label(0, 0, " 第二页的测试数据 "));
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
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private static void initSheet(WritableWorkbook writableWorkbook)
			throws RowsExceededException, WriteException {
		WritableSheet writableSheet = null;
		for (int i = 0; i < 3; i++) {
			String sheet = "";
			if (i == 0) {
				sheet = "一季度";
			} else if (i == 1) {
				sheet = "二季度";
			} else if (i == 2) {
				sheet = "三季度";
			}
			writableSheet = writableWorkbook.createSheet(sheet, i);
			
			  //  WritableSheet.setRowView( int  i, int  height);
			 // 作用是指定第i+1行的高度，比如：

			  // 将第一行的高度设为200
			writableSheet.setRowView( 0 , 100 );

			// WritableSheet.setColumnView( int  i, int  width);
			  // 作用是指定第i+1列的宽度，比如：
			  // 将第一列的宽度设为30
			writableSheet.setColumnView( 0 , 400 );
			
			Label label = null;
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
					WritableFont.BOLD);

			WritableCellFormat format1 = new WritableCellFormat(font1);
			// 把水平对齐方式指定为居中
			format1.setAlignment(jxl.format.Alignment.CENTRE);

			// 把垂直对齐方式指定为居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
			
			for (int row = 0; row < 4; row++) {
				for (int column = 0; column < 4; column++) {
					if (row == 0) {
						label = new Label(column, row, column + " 周 ", format1);
					} else if (column == 0) {
						label = new Label(column, row, row + " 月 ", format1);
					}else if(row == 0 && column == 0) {
						label = new Label(column, row, row + " ", format1);
					}
					else {
						label = new Label(column, row, "" + row + " 月 "
								+ column + " 周 ", format1);
					}

					writableSheet.addCell(label);
				}

			}

		}
	}
}
