package com.text;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.text.html.StyleSheet;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javafx.scene.paint.Color;



public class POIDemo {

	public static void main(String[] args) {
		createHSSFWorkboot("D:/測試2.xls");

	}

	
	public static void createHSSFWorkboot(String fileName){
		Workbook wb = new HSSFWorkbook();
		FileOutputStream fout = null;
		
		try {
			Sheet sheet =wb.createSheet("測試1");
			
			writeContent(wb, sheet);
			fout = new FileOutputStream(fileName);
			wb.write(fout);
			
		} catch (IOException e) {

		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}
	
	public static void writeContent(Workbook wb, Sheet sheet){
		 CellStyle yellow = wb.createCellStyle();
		
		 yellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 yellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		 
		 CellStyle blue = wb.createCellStyle();
			
		 blue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 blue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		 Row headRow = sheet.createRow(0);
         String[] headerStr = { "股號", "年", "月", "開立發票總金額", "營業收入淨額", "合併營業收入淨額"};
         for( int i = 0 ; i < headerStr.length ; i++ ){
             Cell cell = headRow.createCell(i);
             cell.setCellValue(headerStr[i]);
             if(i%2 == 0){
            	 cell.setCellStyle(yellow);
             }
             else{
            	 cell.setCellStyle(blue);
             }
         }

         Row dataRow = sheet.createRow(1);
         String[] DataStr = { "1012", "5", "2", "111,111", "222,222", "333,333"};
         for( int i = 0 ; i < DataStr.length ; i++ ){
             Cell cell = dataRow.createCell(i);
             cell.setCellValue(DataStr[i]);

         }
	}
}
