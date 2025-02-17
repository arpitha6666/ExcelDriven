package excelDataReaderTest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    public ArrayList<String> getData(String testcaseName) throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        //accepts fileinput stream
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/DemoData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int numOfSheets = workbook.getNumberOfSheets();
        //identify the testcases column
        //scan the entire Testcases column to identify 'Purchase'
        //pull the data of 'Purchase'
        for (int i = 0; i < numOfSheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("testData")) {
                XSSFSheet mySheet = workbook.getSheetAt(i);
                Iterator<Row> rows = mySheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cells = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (cells.hasNext()) {
                    Cell cellValue = cells.next();
                    if (cellValue.getStringCellValue().equalsIgnoreCase("Testcases")) {
                        //desired column
                        column = k;
                    }
                    k++;
                }
                //scan the rows for 'Purchase'
                while (rows.hasNext()) {
                    Row row = rows.next();
                    if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
                        Iterator<Cell> rowCells = row.cellIterator();
                        while (rowCells.hasNext()) {
                           Cell rowCellValue = rowCells.next();
                            if(rowCellValue.getCellType()== CellType.STRING) {
                                arr.add(rowCellValue.getStringCellValue());
                            }else{
                                arr.add(NumberToTextConverter.toText(rowCellValue.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return arr;
    }
}
