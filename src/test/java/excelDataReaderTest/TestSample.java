package excelDataReaderTest;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

    public static void main(String[] args) throws IOException {
        DataDriven dataObj = new DataDriven();
        ArrayList<String> arrObj =dataObj.getData("Add Profile");
        arrObj.stream().forEach(System.out::println);
    }
}
