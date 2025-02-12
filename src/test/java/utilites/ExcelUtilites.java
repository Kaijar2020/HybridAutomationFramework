package utilites;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtilites {

    public FileInputStream fileIn;
    public FileOutputStream fileOut;
    public XSSFWorkbook workBook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtilites(String path){
        this.path =path;
    }

    public int getRowCount(String xlsheet) throws IOException, IOException {
        fileIn = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileIn);
        sheet = workBook.getSheet(xlsheet);
        int rowCount = sheet.getLastRowNum();
        workBook.close();
        fileIn.close();
        return rowCount;
    }

    public int getCellCount(String xlsheet, int rowNum) throws IOException {
        fileIn = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileIn);
        sheet = workBook.getSheet(xlsheet);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workBook.close();
        fileIn.close();
        return cellCount;
    }

    public String getCellData(String xlsheet, int rowNum, int colmNum) throws IOException {
        fileIn = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileIn);
        sheet = workBook.getSheet(xlsheet);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colmNum);
        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        }
        catch(Exception e){
            data= "";
        }
        workBook.close();
        fileIn.close();
        return data;
    }

    public void setCellData(String xlsheet, int rowNum, int colmNum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()){  //Create new file if it is not exist.
            workBook = new XSSFWorkbook();
            fileOut = new FileOutputStream(path);
            workBook.write(fileOut);
        }
        fileIn = new FileInputStream(path);
        workBook = new XSSFWorkbook(fileIn);

        if (workBook.getSheetIndex(xlsheet) ==-1){ //Create new sheet if not exist.
            workBook.createSheet(xlsheet);
        }
        sheet = workBook.getSheet(xlsheet);
        if (sheet.getRow(rowNum)==null){ //Create new row whether it not exist.
            sheet.createRow(rowNum);
        }

        row = sheet.getRow(rowNum);
        cell = row.createCell(colmNum);
        cell.setCellValue(data);
        fileOut = new FileOutputStream(path);
        workBook.write(fileOut);
        workBook.close();
        fileIn.close();
    }

}
