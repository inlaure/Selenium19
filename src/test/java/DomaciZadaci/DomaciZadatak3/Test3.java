package DomaciZadaci.DomaciZadatak3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Test3 {
    public static void main(String[] args){

        //Testing with validUsername and empty password field

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");

        String validUsername = "student";

        WebElement practiceEl = driver.findElement(By.id("menu-item-20"));
        practiceEl.click();

        WebElement testLoginLink = driver.findElement(By.linkText("Test Login Page"));
        testLoginLink.click();

        //Valid username
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(validUsername);

        WebElement loginBtn = driver.findElement(By.id("submit"));
        loginBtn.click();

        wait.until(ExpectedConditions.urlToBe("https://practicetestautomation.com/practice-test-login/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");

        WebElement errorMsg = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMsg.isDisplayed());

        Assert.assertEquals(errorMsg.getText(), "Your password is invalid!");
    }
}
