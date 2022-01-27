package com.qa.opencart.util;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

// This Class is to fetch values from Excel Sheet
public class ExcelUtil
{
	private static final String EXCEL_SHEET_DATA_PATH = "./src/test/resources/ExcelSheetData/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName)
	{
		Object data[][] = null;
		try {
				FileInputStream ip = new FileInputStream(EXCEL_SHEET_DATA_PATH);//ApachePoI Method for Path
				book = WorkbookFactory.create(ip);	//ApachePoI method create memory array and read Excel Value for WorkBook
				sheet = book.getSheet(sheetName); //To fetch Data from Particular Sheet of Excel Sheet File
			}
		catch (FileNotFoundException e)
		{e.printStackTrace();}
		catch (InvalidFormatException e)
		{e.printStackTrace();}
		catch (IOException e)
		{e.printStackTrace();}
// Now We have Access to Excel Data Sheet So fill the Values
		
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
// Now iterate Data and fill it in any Page that is being tested
		for (int r = 0; r < sheet.getLastRowNum(); r++)
		{
			for (int c = 0; c < sheet.getRow(0).getLastCellNum(); c++)
			{
				data[r][c] = sheet.getRow(r + 1).getCell(c).toString();
			}
		}
		return data;
	}	
}
