package c_2024_01_15_ponedeljak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Exercise4 {
    public static void main(String[] args) {

        //Zadatak 4
        //Otici na Google
        //Zatim ukucati "Wikipedia" u polje za pretragu
        //Odraditi pretragu i otvoriti stranicu
        //Na stranici Wikipedia pretraziti "Nikola Tesla"

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Wikipedia");

        List<WebElement> searchButton = driver.findElements(By.name("btnK"));
        searchButton.get(1).click();

        //Example of incorrect use of locator
        //WebElement wikipediaLink = driver.findElement(By.className("LC20lb MBeuO DKV0Md"));

        WebElement wikipediaLink = driver.findElement(By.cssSelector(".LC20lb.MBeuO.DKV0Md"));

        wikipediaLink.click();

        WebElement wikipediaSearch = driver.findElement(By.id("searchInput"));

        wikipediaSearch.sendKeys("Nikola Tesla");

        WebElement wikipediaButton = driver.findElement(By.cssSelector("button[type='submit']"));
        wikipediaButton.click();

//--------------------------------

        //Tests have no use if assertion is not used
        //The difference between tests and programmes are assertions
        //In order to execute assertion, TestNG file needs to be added to POM

        //Defining the correct URL
        String expectedURL = "https://en.wikipedia.org/wiki/Nikola_Tesla";

        //When assertEquals is used, on the left side we write the actual result, on the right one - the expected
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        //Verifying that the title is "Nikola Tesla"
        WebElement pageTitle = driver.findElement(By.className("mw-page-title-main"));
        String pageTitleText = pageTitle.getText();

        String expectedTitle = "Nikola Tesla";

        Assert.assertEquals(pageTitleText, expectedTitle);

        //Verifying that there's a picture on website
        WebElement pageImage = driver.findElement(By.className("infobox-image"));

        Assert.assertTrue(pageImage.isDisplayed());

        driver.quit();

    }
}