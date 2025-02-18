package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class ExcelUtil {
    int numOfSheets = 0;
    FileInputStream fis;
    XSSFWorkbook myWorkbook;
    XSSFSheet mySheet;
    XSSFRow rows;
    int numOfRows;
    int numOfCols;


    DataFormatter df = new DataFormatter();
    public Object[][] readExcelFile(File excelFile, String sheetName) throws IOException {
        fis = new FileInputStream(excelFile);
        myWorkbook = new XSSFWorkbook(fis);
        numOfSheets = myWorkbook.getNumberOfSheets();
        for (int i = 0; i < numOfSheets; i++) {
            if (myWorkbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                mySheet = myWorkbook.getSheetAt(i);
                break;
            } else {
                mySheet = myWorkbook.getSheetAt(0);
            }
        }
        //read the sheet and read the rows
        numOfRows = mySheet.getPhysicalNumberOfRows();
        XSSFRow row = mySheet.getRow(0);
        numOfCols = row.getLastCellNum();
        Object[][] data = new Object[numOfRows - 1][numOfCols];
        for (int i = 0; i < numOfRows-1; i++) {
            row = mySheet.getRow(i+1);
            for (int j = 0; j < numOfCols; j++) {
                    data[i][j] =df.formatCellValue(row.getCell(j));
                    System.out.println(data[i][j]);
            }
        }
        return data;

    }

    public void getColumnNumber(String excelFilePath, String findValue) throws IOException {
        fis = new FileInputStream(excelFilePath);
        myWorkbook = new XSSFWorkbook(fis);
        mySheet = myWorkbook.getSheetAt(0);
        numOfRows = mySheet.getPhysicalNumberOfRows();
        XSSFRow row = mySheet.getRow(0);
        numOfCols = row.getLastCellNum();
        Iterator<Row> rows = mySheet.iterator();
        Row firstRow = rows.next();
        Iterator<Cell> cells = firstRow.cellIterator();
        int k = 0;
        int column = 0;
        while (cells.hasNext()) {
            Cell cellValue = cells.next();
            if (cellValue.getStringCellValue().equalsIgnoreCase(findValue)) {
                //desired column
                column = k;
            }
            k++;
        }
    }

    public void getRowNumber(String excelFilePath, String findValue) throws IOException {
        fis = new FileInputStream(excelFilePath);
        myWorkbook = new XSSFWorkbook(fis);
        mySheet = myWorkbook.getSheetAt(0);
        numOfRows = mySheet.getPhysicalNumberOfRows();
        XSSFRow row = mySheet.getRow(0);
        numOfCols = row.getLastCellNum();
        Iterator<Row> rows = mySheet.iterator();
        Row firstRow = rows.next();
        Iterator<Cell> cells = firstRow.cellIterator();
        int k = 0;
        int column = 0;
        while (cells.hasNext()) {
            Cell cellValue = cells.next();
            if (cellValue.getStringCellValue().equalsIgnoreCase(findValue)) {
                //desired column
                column = k;
            }
            k++;
        }
    }



}
