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

    @Test(dataProvider = "excelTest", dataProviderClass = DataProviderForExcel.class)
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
        File excelFile = new File(System.getProperty("user.dir") + "/src/test/resources/DemoDataForDataProvider.xlsx");
        ExcelUtil util = new ExcelUtil();
        Object[][] data = util.readExcelFile(excelFile, "testData");
        return data;
    }
}
