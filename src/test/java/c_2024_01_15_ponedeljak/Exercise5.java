package c_2024_01_15_ponedeljak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Exercise5 {
    public static void main(String[] args) {
        // Zadatak 5
        // Testirati log in stranice: https://practicetestautomation.com/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/");

        String validUsername = "student";
        String validPassword = "Password123";
        WebElement practiceLink = driver.findElement(By.id("menu-item-20"));
        practiceLink.click();

        WebElement loginLink = driver.findElement(By.xpath("/html/body/div/div/section/div/div/article/div[2]/div[1]/div[1]/p/a"));
        loginLink.click();

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(validUsername);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(validPassword);

        WebElement submitBtn = driver.findElement(By.id("submit"));
        submitBtn.click();

        //-----------------Assertions

        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement loginTitle = driver.findElement(By.className("post-title"));
        Assert.assertEquals(loginTitle.getText(), "Logged In Successfully");

        WebElement pageImg = driver.findElement(By.className("custom-logo"));
        Assert.assertTrue(pageImg.isDisplayed());

        WebElement logoutBtn = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logoutBtn.isDisplayed());

        WebElement welcomeText = driver.findElement(By.className("has-text-align-center"));
        String expectedWelcome = "Congratulations " + validUsername+ ". You successfully logged in!";
        Assert.assertEquals(welcomeText.getText(), expectedWelcome);
    }
}
