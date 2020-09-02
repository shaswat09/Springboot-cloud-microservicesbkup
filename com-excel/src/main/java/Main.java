import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
	private static final String excelFilePath = "C:\\Users\\stalati\\Desktop\\React JS books\\RP-137.xls";
	public static void main(String[] args) throws Exception {
		
		String str="ab?|c12\3";
	//	str=str.replaceAll('[<>/\\|?"*:]', "_");
		str=str.replaceAll("[<>/|?*:]", "_");
		
		str=str.replaceAll("\\\\", "_");
		System.out.println(str);
		
	/*	String report="RP-137";
		int rowNum=4;
		if(report.contains("RP-140") || report.contains("RP-141")|| report.contains("RP-142")){
			rowNum=7;
		}else if (report.contains("RP-143")){
			rowNum=8;
		}
		else if (report.contains("RP-144")){
			rowNum=10;
		}
		else if (report.contains("RP-145")){
			rowNum=5;
		}
		
		if(report.contains("RP-140")){
			System.out.println("LOL");
		}else{
			System.out.println("NOLOL");
		}
		
		
		main1(rowNum);
		/*FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);
		 Iterator<Row> rowIterator = sheet.iterator();
		 HSSFCellStyle backgroundStyle = workbook.createCellStyle();
		 
		 Font font = workbook.createFont();
		 
	        backgroundStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
	        backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 while (rowIterator.hasNext()) {
             Row row = rowIterator.next();
             System.out.println(row.getRowNum());
             if (row.getRowNum()==7){
            	 Iterator<Cell> cellIterator = row.cellIterator();
            	 while (cellIterator.hasNext()) {
                     Cell cell = cellIterator.next();
                     font.setColor(IndexedColors.WHITE.getIndex());
                     font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                     backgroundStyle.setFont(font);
                     cell.setCellStyle(backgroundStyle);
            	 }
             }
         }
		 
		 inputStream.close();

         FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\stalati\\Desktop\\React JS books\\RP140.xls"));
         workbook.write(outFile);
         outFile.close();*/
	}
	
	public static void  main1(int rowNum) throws Exception{
		
		
		ByteArrayInputStream bin=null;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(inputStream); 
		Sheet sheet = workbook.getSheetAt(0);
		 Iterator<Row> rowIterator = sheet.iterator();
		 CellStyle backgroundStyle = workbook.createCellStyle();
		 CellStyle backgroundStyle1 = workbook.createCellStyle();
		 Font font = workbook.createFont();
		 
	        backgroundStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
	        backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	       
		 while (rowIterator.hasNext()) {
             Row row = rowIterator.next();
             System.out.println(row.getRowNum());
             if (row.getRowNum()==rowNum){
            	 Iterator<Cell> cellIterator = row.cellIterator();
            	
            	 while (cellIterator.hasNext()) {
                     Cell cell = cellIterator.next();
                     
                     font.setColor(IndexedColors.WHITE.getIndex());
                     font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                     backgroundStyle.setFont(font);
                    // backgroundStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                     backgroundStyle.setAlignment(CellStyle.ALIGN_CENTER);
                     cell.setCellStyle(backgroundStyle);
            	 }
             }
             if (row.getRowNum()>rowNum){
            	 Iterator<Cell> cellIterator = row.cellIterator();
             	
            	 while (cellIterator.hasNext()) {
                     Cell cell = cellIterator.next();
                     backgroundStyle1.setAlignment(CellStyle.ALIGN_LEFT);
                     cell.setCellStyle(backgroundStyle1);
            	 }
             }
         }
		 sheet.autoSizeColumn(0);
		 sheet.autoSizeColumn(1);
		 sheet.autoSizeColumn(9);
		 ByteArrayOutputStream bout=new ByteArrayOutputStream();
			// writing the modified content from workbook to the ByteArrayOutputStream instance
		 workbook.write(bout);
			bout.close();
		 
		 
		 
		 inputStream.close();

       FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\stalati\\Desktop\\React JS books\\RP-137.xls"));
         workbook.write(outFile);
         outFile.close();
	}

}
