package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {

	public static void main(String args[]) throws InvalidFormatException, IOException {
		
		String filePath = System.getProperty("user.dir")+"//testData//loginData.xlsx";
		
		Workbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
		Sheet xssfSheet = xssfWorkbook.getSheet("LoginTestData");
			 Iterator <Row> rowIterator = xssfSheet.iterator();
			 rowIterator.next();
			 
			 while(rowIterator.hasNext()) {
				 Row row = rowIterator.next();
				 Cell firstCell = row.getCell(0);
				 Cell secondCell = row.getCell(1);
				 System.out.println(firstCell.toString());
				 System.out.println(secondCell.toString());
				 
			 }
			 
			 
		
		
	}
}
