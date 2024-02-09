package DomaciZadaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class DomaciZadatak5 {
//    Domaci Zadatak:
//    Otici na sajt Herkouapp(https://the-internet.herokuapp.com/)
//    // i napisati sto vise test case-eva (Vas izbor sta cete testirati).
    WebDriver driver;
    WebDriverWait wait;
    String url;
    JavascriptExecutor js;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        url = "https://the-internet.herokuapp.com/";
    }

    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test(priority = 10)
    public void userCanCheckAllBoxes(){
        WebElement checkboxesLink = driver.findElement(By.linkText("Checkboxes"));
        checkboxesLink.click();
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/checkboxes"));
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        WebElement checkbox1 = checkboxes.get(0);
        WebElement checkbox2 = checkboxes.get(1);
        Assert.assertTrue(checkbox2.isSelected());
        checkbox1.click();
        Assert.assertEquals(checkbox1.getAttribute("checked"), checkbox2.getAttribute("checked"));
    }

    @Test(priority = 20)
    public void contentIsDynamic(){
        WebElement dynamicContent = driver.findElement(By.linkText("Dynamic Content"));
        dynamicContent.click();
        WebElement contentText1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]"));
        String textBeforeRefresh1 = contentText1.getText();
        WebElement contentText2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]"));
        String textBeforeRefresh2 = contentText2.getText();
        WebElement contentText3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]"));
        String textBeforeRefresh3 = contentText3.getText();
        driver.navigate().refresh();
        contentText1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]"));
        String textAfterRefresh1 = contentText1.getText();
        contentText2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]"));
        String textAfterRefresh2 = contentText2.getText();
        contentText3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]"));
        String textAfterRefresh3 = contentText3.getText();
        Assert.assertNotEquals(textBeforeRefresh1, textAfterRefresh1);
        Assert.assertNotEquals(textBeforeRefresh2, textAfterRefresh2);
        Assert.assertNotEquals(textBeforeRefresh3, textAfterRefresh3);
    }

    @Test(priority = 30)
    public void userCanAddRemovedCheckbox(){
        WebElement dynamicControlsLink = driver.findElement(By.linkText("Dynamic Controls"));
        dynamicControlsLink.click();
        WebElement addRemoveBtn = driver.findElement(By.cssSelector("button[onclick='swapCheckbox()']"));
        addRemoveBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(addRemoveBtn));
        Assert.assertEquals(addRemoveBtn.getText(), "Add");
        addRemoveBtn = driver.findElement(By.cssSelector("button[onclick='swapCheckbox()']"));
        addRemoveBtn.click();
        WebElement message = driver.findElement(By.id("message"));
        WebElement checkbox = driver.findElement(By.id("checkbox"));
        Assert.assertEquals(message.getText(), "It's back!");
        Assert.assertTrue(checkbox.isDisplayed());
    }

    @Test(priority = 40)
    public void userCanEnableInput(){
        WebElement dynamicControlsLink = driver.findElement(By.linkText("Dynamic Controls"));
        dynamicControlsLink.click();
        WebElement enableBtn = driver.findElement(By.cssSelector("button[onclick='swapInput()']"));
        enableBtn.click();
        WebElement inputField = driver.findElement(By.cssSelector("input[disabled]"));
        wait.until(ExpectedConditions.elementToBeClickable(enableBtn));
        WebElement inputMessage = driver.findElement(By.id("message"));
        Assert.assertTrue(inputField.isEnabled());
        Assert.assertEquals(inputMessage.getText(), "It's enabled!");
        Assert.assertEquals(enableBtn.getText(), "Disable");
    }

    @Test(priority = 50)
    public void userCanUploadFiles(){
        WebElement fileUploadLink = driver.findElement(By.linkText("File Upload"));
        fileUploadLink.click();
        WebElement fileUploadBtn = driver.findElement(By.id("file-upload"));
        String imagePath = "C:\\Users\\PC\\Desktop\\java.jpg";
        fileUploadBtn.sendKeys(imagePath);
        WebElement uploadBtn = driver.findElement(By.id("file-submit"));
        uploadBtn.click();
        WebElement uploadMessage = driver.findElement(By.tagName("h3"));
        WebElement uploadedFile = driver.findElement(By.id("uploaded-files"));
        Assert.assertEquals(uploadMessage.getText(), "File Uploaded!");
        Assert.assertEquals(uploadedFile.getText(), "java.jpg");
    }

    @Test(priority=60)
    public void userCanLogIn(){
        WebElement authLink = driver.findElement(By.linkText("Form Authentication"));
        authLink.click();
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        loginBtn.click();
        WebElement logoutBtn = driver.findElement(By.cssSelector(".button.secondary.radius"));
        WebElement succesMsg = driver.findElement(By.className("subheader"));
        String message = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
        Assert.assertEquals(succesMsg.getText(), message);
        Assert.assertTrue(logoutBtn.isDisplayed());
    }

    @Test(priority = 70)
    public void userCanClosePromptWindows(){
        WebElement alertWindowLink = driver.findElement(By.linkText("JavaScript Alerts"));
        alertWindowLink.click();
        WebElement resultMsg = driver.findElement(By.id("result"));
        WebElement alertBtn = driver.findElement(By.cssSelector("button[onclick='jsAlert()']"));
        alertBtn.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        String alertResult = "You successfully clicked an alert";
        Assert.assertTrue(resultMsg.isDisplayed());
        Assert.assertEquals(resultMsg.getText(), alertResult);
        WebElement confirmBtn = driver.findElement(By.cssSelector("button[onclick='jsConfirm()']"));
        confirmBtn.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        String confirmResult = "You clicked: Ok";
        Assert.assertTrue(resultMsg.isDisplayed());
        Assert.assertEquals(resultMsg.getText(), confirmResult);
        WebElement promptBtn = driver.findElement(By.cssSelector("button[onclick='jsPrompt()']"));
        promptBtn.click();
        String message = "cool";
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(message);
        driver.switchTo().alert().accept();
        Assert.assertTrue(resultMsg.isDisplayed());
        Assert.assertEquals(resultMsg.getText(), "You entered: " + message);
    }

    @Test(priority = 80)
    public void menuIsVisibleOnScroll(){
        WebElement floatingMenuLink = driver.findElement(By.linkText("Floating Menu"));
        floatingMenuLink.click();
        WebElement menu = driver.findElement(By.id("menu"));
        js.executeScript("window.scrollBy(0, 600)");
        Assert.assertTrue(menu.isDisplayed());
    }

    @Test(priority = 90)
    public void userCanAddMultipleElements(){
        WebElement addElementLink = driver.findElement(By.linkText("Add/Remove Elements"));
        addElementLink.click();
        WebElement elements = driver.findElement(By.id("elements"));
        List<WebElement> children = elements.findElements(By.className("added-manually"));
        Assert.assertEquals(children.size(), 0);
        WebElement addBtn = driver.findElement(By.cssSelector("button[onclick='addElement()']"));
        int numOfClicks = 5;
        for(int i =0; i<numOfClicks; i++){
            addBtn.click();
        }
        List<WebElement> addedItems = driver.findElements(By.className("added-manually"));
        Assert.assertEquals(numOfClicks, addedItems.size());
        for(int i = 0; i<addedItems.size(); i++){
            Assert.assertTrue(addedItems.get(i).isDisplayed());
            Assert.assertEquals(addedItems.get(i).getText(), "Delete");
        }
    }

    @Test(priority = 100)
    public void userCanDeleteItems(){
        WebElement addElementLink = driver.findElement(By.linkText("Add/Remove Elements"));
        addElementLink.click();
        WebElement addBtn = driver.findElement(By.cssSelector("button[onclick='addElement()']"));
        int numOfClicks = 5;
        for(int i =0; i<numOfClicks; i++){
            addBtn.click();
        }
        List<WebElement> addedItems = driver.findElements(By.className("added-manually"));
        Assert.assertEquals(addedItems.size(), numOfClicks);
        for(int i = 0; i<addedItems.size(); i++){
            addedItems.get(i).click();
        }
        WebElement elements = driver.findElement(By.id("elements"));
        List<WebElement> children = elements.findElements(By.className("added-manually"));
        Assert.assertEquals(children.size(), 0);
    }


    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }

    @AfterClass
    public void quitClass(){
//        driver.quit();
    }
}
