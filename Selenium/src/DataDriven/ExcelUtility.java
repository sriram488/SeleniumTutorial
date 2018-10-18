package DataDriven;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	String xlFilePath;
			
	public ExcelUtility(String xlFilePath) throws Exception
	{
		this.xlFilePath = xlFilePath;
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
	}
	
	public String getCellData(String sheetName, int colNum, int rowNum)
	{
		try {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if (cell.getCellTypeEnum()==CellType.STRING)
				return cell.getStringCellValue();
			else if(cell.getCellTypeEnum()==CellType.NUMERIC || cell.getCellTypeEnum() ==CellType.FORMULA)
			{
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell))
				{
					DateFormat dt = new SimpleDateFormat("dd/MM/yy");
					Date date = cell.getDateCellValue();
					cellValue = dt.format(date);
				}
				return cellValue;
			}
		
			else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else 
				return String.valueOf(cell.getBooleanCellValue());
}
		catch(Exception e)
		{
			e.printStackTrace();
			return " No Matched Value";
		}
}

	public String getCellData( String sheetName, String colName, int rowNum)
	{
		try
		{
			int colNum = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			for( int i = 0; i< row.getLastCellNum(); i++)
			{
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum = i;
			}
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(colNum);
			return cell.getStringCellValue();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "data not found";
		}
	}
	
	public int getRowCount(String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()+1;
		return rowCount;
	}
	
	public int getColumnCount(String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		return columnCount;
	}
}