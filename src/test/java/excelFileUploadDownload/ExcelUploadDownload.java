package excelFileUploadDownload;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

public class ExcelUploadDownload {
    public static void main(String[] args) {
        String fruitName = "Apple";
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
        driver.findElement(By.id("downloadButton")).click();

        //upload file
        WebElement upload =driver.findElement(By.cssSelector("input[type='file']"));
        String path = new File("C://Users//arpit//Downloads//download.xlsx").getAbsolutePath();
        upload.sendKeys(path);


        //download

        //Edit excel
        //get column of price


        //Upload

        //Wait for success message to show up and disapper
        By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
        Assert.assertEquals(driver.findElement(toastLocator).getText(),"Updated Excel Data Successfully.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
        //verify updated excel is showing in the table
        String priceColum = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
        String actualPrice = driver.findElement(By.xpath("//div[text()='"+fruitName+"']/parent::div/parent::div/div[@id='cell-"+priceColum+"-undefined']")).getText();
        System.out.println("actualPrice "+actualPrice);
        driver.close();






    }


}
