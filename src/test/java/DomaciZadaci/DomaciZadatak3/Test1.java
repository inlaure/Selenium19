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

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
//        Domaci Zadatak:
//        Napisati 5 negativnih log-in test case-eva za dati sajt:
//            https://practicetestautomation.com/

        //Testing with validUsername and invalidPassword
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");

        String validUsername = "student";
        String invalidPassword = "password123";

        WebElement practiceEl = driver.findElement(By.id("menu-item-20"));
        practiceEl.click();

        WebElement testLoginLink = driver.findElement(By.linkText("Test Login Page"));
        testLoginLink.click();

//        Thread.sleep(2000);
        //Valid username
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(validUsername);

//        Thread.sleep(2000);
        //Invalid password
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(invalidPassword);

//        Thread.sleep(2000);
        WebElement loginBtn = driver.findElement(By.id("submit"));
        loginBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://practicetestautomation.com/practice-test-login/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");

        WebElement errorMsg = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), "Your password is invalid!");
    }
}
