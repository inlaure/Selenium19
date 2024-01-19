package c_2024_01_15_ponedeljak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak6 {
    public static void main(String[] args) {
// Zadatak 6
// Testirati log out funkcionalnost

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/");

        String validUsername = "student";
        String validPassword = "Password123";

        WebElement practiceLink = driver.findElement(By.id("menu-item-20"));
        practiceLink.click();

        WebElement testLoginPage = driver.findElement(By.linkText("Test Login Page"));
        testLoginPage.click();

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(validUsername);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(validPassword);

        WebElement loginBtn = driver.findElement(By.id("submit"));
        loginBtn.click();

        WebElement logoutBtn = driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color"));
        logoutBtn.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");

        WebElement loginBtn2 = driver.findElement(By.id("submit"));
        Assert.assertTrue(loginBtn2.isDisplayed());

    }
}
