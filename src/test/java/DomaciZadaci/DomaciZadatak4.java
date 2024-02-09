package DomaciZadaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class DomaciZadatak4 {
//Prvo mora da se uloguje sa mojim kredencijalima(validUsername, validPassword) i onda da se nadju novi cookies za token i expires.
    //Kad promeni expires i token vrednost, pokrenuti program radi
    //Dodala sam thread.sleep u nekim mestima jer drugi waitovi nisu mi radili

    WebDriver driver;
    WebDriverWait wait;
    String url, validUsername, validPassword;
    WebElement loginBtnInBookStorePage, loginBtnInLoginPage, bookStoreLink, addBookButton, backToStoreButton;
    WebElement usernameField, passwordField;
    WebElement profileLink;
    Cookie token, userId, user, expiration;
    JavascriptExecutor js;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        url = "https://demoqa.com/";
        validUsername = "lauant";
        validPassword = "Lauant123*";
    }

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);

        List<WebElement> homePageElements = driver.findElements(By.cssSelector(".card.mt-4.top-card"));
        for (WebElement el : homePageElements) {
            if (el.getText().equals("Book Store Application")) {
                js.executeScript("window.scrollBy(0, 600)");
                el.click();
            }
        }
        token = new Cookie("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImxhdWFudCIsInBhc3N3b3JkIjoiTGF1YW50MTIzKiIsImlhdCI6MTcwNTg0NTExMX0.HiZdU7S-2iahRzThF7ceiXcOJns34r1XmngaYMbrSu8");
        expiration = new Cookie("expires", "2024-01-28T13%3A51%3A51.383Z");
        user = new Cookie("userName", "lauant");
        userId = new Cookie("userID", "6e105aee-1719-474f-8697-8877643ce314");
    }

    @Test(priority = 10)
    public void userCanAddBooks() throws InterruptedException {
        loginBtnInBookStorePage = driver.findElement(By.id("login"));
        wait.until(ExpectedConditions.visibilityOf(loginBtnInBookStorePage));
        loginBtnInBookStorePage.click();
        driver.manage().addCookie(token);
        driver.manage().addCookie(userId);
        driver.manage().addCookie(user);
        driver.manage().addCookie(expiration);
        driver.navigate().refresh();

        List<WebElement> elementsItems = driver.findElements(By.id("item-3"));
        for (WebElement el : elementsItems) {
            if (el.getText().equals("Profile")) {
                profileLink = el;
            }
        }
        js.executeScript("window.scrollBy(0, 400)");
        profileLink.click();
        WebElement emptyTable = driver.findElement(By.className("rt-noData"));
        Assert.assertNotNull(emptyTable);
        List<WebElement> bookStoreItems = driver.findElements(By.id("item-2"));
        for (WebElement el : bookStoreItems) {
            if (el.getText().equals("Book Store")) {
                bookStoreLink = el;
            }
        }
        js.executeScript("window.scrollBy(0, 300)");
        bookStoreLink.click();
        WebElement gitPocketGuideBook = driver.findElement(By.id("see-book-Git Pocket Guide"));
        Thread.sleep(500);
        gitPocketGuideBook.click();
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/books?book=9781449325862"));
        WebElement buttonBox = driver.findElement(By.cssSelector(".mt-2.fullButtonWrap.row"));
        Thread.sleep(500);
        js.executeScript("arguments[0].scrollIntoView(true);", buttonBox);
        List<WebElement> addNewRecordButtons = driver.findElements(By.id("addNewRecordButton"));
        addBookButton = addNewRecordButtons.get(1);
        addBookButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        backToStoreButton = addNewRecordButtons.get(0);
        Thread.sleep(1000);
        backToStoreButton.click();
        js.executeScript("window.scrollBy(0, 200)");
        WebElement jsBook = driver.findElement(By.id("see-book-You Don't Know JS"));
        jsBook.click();
        js.executeScript("window.scrollBy(0, 200)");

        List<WebElement> addNewRecordButtons2 = driver.findElements(By.id("addNewRecordButton"));
        addBookButton = addNewRecordButtons2.get(1);
        addBookButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        js.executeScript("window.scrollBy(0, 400)");
        List<WebElement> elementsItems2 = driver.findElements(By.id("item-3"));
        for (WebElement el : elementsItems2) {
            if (el.getText().equals("Profile")) {
                profileLink = el;
            }
        }
        profileLink.click();
        WebElement gitBookTitle = driver.findElement(By.id("see-book-Git Pocket Guide"));
        Assert.assertEquals(gitBookTitle.getText(), "Git Pocket Guide");
        WebElement jsBookTitle = driver.findElement(By.id("see-book-You Don't Know JS"));
        Assert.assertEquals(jsBookTitle.getText(), "You Don't Know JS");

}

    @Test (priority = 20)
    public void userBooksAreSaved(){
        loginBtnInBookStorePage = driver.findElement(By.id("login"));
        loginBtnInBookStorePage.click();
        usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys(validUsername);
        passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(validPassword);
        loginBtnInLoginPage = driver.findElement(By.id("login"));
        loginBtnInLoginPage.click();
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/books"));
        List<WebElement> elementsItems = driver.findElements(By.id("item-3"));
        for (WebElement el : elementsItems) {
            if (el.getText().equals("Profile")) {
                profileLink = el;
            }
        }
        js.executeScript("window.scrollBy(0, 400)");
        wait.until(ExpectedConditions.visibilityOf(profileLink));
        profileLink.click();
        WebElement gitBookTitle = driver.findElement(By.id("see-book-Git Pocket Guide"));
        Assert.assertEquals(gitBookTitle.getText(), "Git Pocket Guide");
        WebElement jsBookTitle = driver.findElement(By.id("see-book-You Don't Know JS"));
        Assert.assertEquals(jsBookTitle.getText(), "You Don't Know JS");
    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @AfterClass
    public void quitDriver(){
        driver.quit();
    }
}
