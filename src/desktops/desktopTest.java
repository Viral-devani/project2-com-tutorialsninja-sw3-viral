package desktops;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class desktopTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setup() {
        openBrowser(baseUrl);//navigating to the webpage
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        //Mouse hover on Desktops Tab and click
        Thread.sleep(2000);
        mouseHoverAndClick(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]"));
        //Click on "Show All Desktops"
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[contains(text(),'Show All Desktops')]"));
        //Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (Z - A)");
        verifyElements("Name (Z - A)", By.xpath("//option[@value='http://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC']\n"), "Correct text");
    }

    @Test

    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //Mouse hover on Desktops Tab and click
        Thread.sleep(2000);
        mouseHoverAndClick(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]"));
        Thread.sleep(5000);

        //Click on "Show All Desktops"
        clickOnElement(By.linkText("Show All Desktops"));
        // clickOnElement(By.xpath("//a[contains(text(),'Show All Desktops')]"));
        //clickOnElement(By.xpath("//a[contains(text(),'Show All Desktops')]"));


        //Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");

        //Select product "HP LP3065"
        Thread.sleep(2000);
        clickOnElement(By.xpath("//img[@title='HP LP3065']"));
        //Verify the text "HP LP3065"
        verifyElements("HP LP3065", By.xpath("//h1[contains(text(),'HP LP3065')]"), "Correct Text");


        //Select date picker

        String year = "2022";
        String month = "November";
        String date = "30";
        clickOnElement(By.xpath("//div[@class='input-group date']//button[@type='button']")); // Opens the date picker
        while (true) {
            String monthYear = driver.findElement(By.xpath("//th[@class='picker-switch']")).getText();
            String arr[] = monthYear.split(" ");
            String mon = arr[0];
            String yr = arr[1];
            if (mon.equalsIgnoreCase(month) && yr.equalsIgnoreCase(year)) {
                Thread.sleep(2000);
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
                break;
            } else
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
        }
        //Select date
        List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='table-condensed']//td"));

        for (WebElement ele : allDates) {
            String dt = ele.getText();
            //System.out.println(dt);
            if (dt.equalsIgnoreCase(date)) {
                Thread.sleep(2000);
                ele.click();
                break;
            }

        }
        //2.7.Enter Qty "1” using Select class.
        //driver.findElements(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/input[1]")).clear();
        WebElement toClear = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/input[1]"));
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);//delete '1'
        Thread.sleep(3000);
        sendTextToElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/input[1]"), "1");

        //2.8 Click on “Add to Cart” button
        clickOnElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/button"));

        //2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        verifyElements("Success: You have added HP LP3065 to your shopping cart!\n" +
                "×", By.xpath("//div[@class='alert alert-success alert-dismissible']"), " You have added HP LP3065 to your shopping cart!");

        //2.10 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("/html/body/div[2]/div[1]/a[2]"));

        //2.11 Verify the text "Shopping Cart"
        verifyElements("Shopping Cart  (1.00kg)",By.xpath("/html/body/div[2]/div/div/h1"),"Shopping cart page not clicked");

        //2.12 Verify the Product name "HP LP3065"
        verifyElements("HP LP3065",By.xpath("/html/body/div[2]/div/div/form/div/table/tbody/tr/td[2]/a"),"Product is not displyed");
        //2.13 Verify the Delivery Date "2022-11-30"
        verifyElements("Delivery Date: 2022-11-30",By.xpath("/html/body/div[2]/div/div/form/div/table/tbody/tr/td[2]/small[1]"),"Delivery date is not correct");
        //2.14 Verify the Model "Product21
        verifyElements("Product 21",By.xpath("/html/body/div[2]/div/div/form/div/table/tbody/tr/td[3]"),"product is not correct");
        //2.15 Verify the Todat "$122.00"
        verifyElements("$122.00",By.xpath("/html/body/div[2]/div/div/form/div/table/tbody/tr/td[6]"),"Total is incorrect");


    }

    @After
    public void testDown() {
        closeBrowser();
    }
}

