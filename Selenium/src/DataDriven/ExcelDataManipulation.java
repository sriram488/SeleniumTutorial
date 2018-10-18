package DataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelDataManipulation 
{
	private static XSSFWorkbook workBook;

	public static void main(String[] args) throws IOException, FileNotFoundException, Exception
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\sriram\\Desktop\\Selenium\\Book1.xlsx");
		workBook = new XSSFWorkbook(fis);
		int sheets = workBook.getNumberOfSheets();
		
		for(int i= 0; i < sheets; i ++)
		{
			if(workBook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				//Workbook has sheets example-sheet1,sheet2,sheet3 
				XSSFSheet sheet = workBook.getSheetAt(i);
				//Identify by scanning the entire 1st row
				//Sheet is collection of rows
				Iterator<Row> rows = sheet.iterator();
				Row firstRow= rows.next();
				// row is basically collection of cells
				Iterator<Cell> cell = firstRow.cellIterator();
				int k = 0; 
				int column = 0;
				while(cell.hasNext())
				{
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("Data3"))
					{
						column = k;
					}
					k++;
				}
				System.out.println(column);
			}
			
		}
		ExcelUtility excel = new ExcelUtility("C:\\Users\\sriram\\Desktop\\Selenium\\Book1.xlsx");
		int columns = excel.getColumnCount("Sheet1");
		int rows = excel.getRowCount("Sheet1");
		String cellData = excel.getCellData("Sheet1", 3, 4);
		System.out.println(columns);
		System.out.println(rows);
		System.out.println(cellData);

	}
	
}
