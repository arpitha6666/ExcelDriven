package excelDataProvider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderForExcel {

    @Test(dataProvider="excelTest",dataProviderClass = DataProviderForExcel.class)
    public void test(String greet, String comm, String id) {
        System.out.println(greet + comm + id);
        /*
        hellomessage1
        byetxt147
        solodrive77
         */
    }

    @DataProvider(name = "excelTest")
    public Object[][] getDataExcel() throws IOException {
        FileInputStream fis;
        XSSFWorkbook myWorkbook;
        XSSFSheet mySheet;
        XSSFRow rows;
        int numOfRows;
        int numOfCols;
        File excelFile = new File(System.getProperty("user.dir") + "/src/test/resources/DemoDataForDataProvider.xlsx");
        DataFormatter df = new DataFormatter();
        fis = new FileInputStream(excelFile);
        myWorkbook = new XSSFWorkbook(fis);
        mySheet = myWorkbook.getSheetAt(0);
        //read the sheet and read the rows
        numOfRows = mySheet.getPhysicalNumberOfRows();
        XSSFRow row = mySheet.getRow(0);
        numOfCols = row.getLastCellNum();
        Object[][] data = new Object[numOfRows - 1][numOfCols];
        for (int i = 0; i < numOfRows - 1; i++) {
            row = mySheet.getRow(i + 1);
            for (int j = 0; j < numOfCols; j++) {
                data[i][j] = df.formatCellValue(row.getCell(j));
                System.out.println(data[i][j]);
            }
        }
        return data;
    }
}
