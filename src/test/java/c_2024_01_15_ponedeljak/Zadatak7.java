package c_2024_01_15_ponedeljak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak7 {
    public static void main(String[] args) {
        // Zadatak 7
        // Testirati neuspesan log in
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/");
        WebElement practiceLink = driver.findElement(By.id("menu-item-20"));
        practiceLink.click();

        WebElement testLinkPage = driver.findElement(By.linkText("Test Login Page"));
        testLinkPage.click();

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("incorrectUser");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Password123");

        WebElement loginBtn = driver.findElement(By.id("submit"));
        loginBtn.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");

        WebElement error = driver.findElement(By.id("error"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(error.getText(), "Your username is invalid!");
        Assert.assertTrue(username.getText().isEmpty());
        Assert.assertTrue(password.getText().isEmpty());
        Assert.assertTrue(loginBtn.isDisplayed());
    }
}
