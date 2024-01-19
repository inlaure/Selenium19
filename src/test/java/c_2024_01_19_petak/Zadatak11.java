package c_2024_01_19_petak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Zadatak11 {
//    Za pocetak nastavite pisanje testova za login sto smo poceli juce.
//    Ideja je da u jednoj klasi imate sve metode (test kejseve) za testiranje ove funkcionalnosti. To bi bio log in, log out i neuspeli pokusaji
//    Mozete da radite sve testove u jednom browseru ili svaki test u posebnom
//    https://practicetestautomation.com/
    WebDriver driver;
    WebDriverWait wait;
    String validUsername, invalidUsername, validPassword, invalidPassword, loginURL;
    WebElement usernameInput, passwordInput, submitBtn;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
         validUsername = "student";
         invalidUsername = "student1";
         validPassword = "Password123";
         invalidPassword = "password";
        loginURL = "https://practicetestautomation.com/logged-in-successfully/";

    }

    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://practicetestautomation.com/");
        WebElement practiceLink = driver.findElement(By.id("menu-item-20"));
        practiceLink.click();
        WebElement testLoginPageLink = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageLink.click();
        usernameInput = driver.findElement(By.id("username"));
        passwordInput = driver.findElement(By.id("password"));
        submitBtn = driver.findElement(By.id("submit"));
    }

    @Test(priority = 10)
    public void userCanLogIn(){
        usernameInput.sendKeys(validUsername);
        passwordInput.sendKeys(validPassword);
        submitBtn.click();
//        Assert.assertTrue(logoutBtn.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
    }

    @Test(priority = 20)
    public void userCanLogOut(){
        usernameInput.sendKeys(validUsername);
        passwordInput.sendKeys(validPassword);
        submitBtn.click();
        WebElement logoutBtn = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logoutBtn.isDisplayed());
        logoutBtn.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(loginURL)));
        Assert.assertNotEquals(driver.getCurrentUrl(), loginURL);

        boolean isButtonShown = false;
        try{
            WebElement logoutBtnAfterLoggingOut=driver.findElement(By.linkText("Log out"));
            isButtonShown = logoutBtnAfterLoggingOut.isDisplayed();
        }catch(Exception e){
            System.out.println(e);
        }
        Assert.assertFalse(isButtonShown);

    }
}
