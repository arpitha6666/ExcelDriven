package excelDataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSampleTest {

    //multiple sets of data to our tests
    //array public

    @Test(dataProvider = "driveTest")
    public void dataProviderTest(String greet, String comm, int id){
        System.out.println(greet+comm+id);
        /*
        hellomessage1
        byetxt147
        solodrive77
         */
    }
    @DataProvider(name = "driveTest")
    public Object[][] getData(){
        Object[][] dataObj ={
                {"hello","message",1},{"bye","txt",147},{"solo","drive",77}
        };

        return dataObj;
    }
}
