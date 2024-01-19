package DomaciZadaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class DomaciZadatak2 {
    public static void main(String[] args) {
        //Domaci Zadatak 2:
        //https://demoqa.com/text-box napisati test case za dati text box
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/text-box");

        String expectedURL = "https://demoqa.com/text-box";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement pageTitle = driver.findElement(By.className("main-header"));
        Assert.assertEquals(pageTitle.getText(), "Text Box");

        WebElement formElement = driver.findElement(By.id("userForm"));
        Assert.assertTrue(formElement.isDisplayed());

        WebElement fullNameLabel = driver.findElement(By.id("userName-label"));
        String expectedFullNameLabel = "Full Name";
        Assert.assertTrue(fullNameLabel.isDisplayed());
        Assert.assertEquals(fullNameLabel.getText(), expectedFullNameLabel);

        WebElement fullNameInput = driver.findElement(By.id("userName"));
        fullNameInput.sendKeys("Laura Ant");

        WebElement emailLabel = driver.findElement(By.id("userEmail-label"));
        String expectedEmailLabel = "Email";
        Assert.assertTrue(emailLabel.isDisplayed());
        Assert.assertEquals(emailLabel.getText(), expectedEmailLabel);

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("koko@gmail.com");

        WebElement curAddressLabel = driver.findElement(By.id("currentAddress-label"));
        String expectedCurAddress = "Current Address";
        Assert.assertTrue(curAddressLabel.isDisplayed());
        Assert.assertEquals(curAddressLabel.getText(), expectedCurAddress);

        WebElement curAddressInput = driver.findElement(By.id("currentAddress"));
        curAddressInput.sendKeys("7 Juli, Nis");

        WebElement permAddressLabel = driver.findElement(By.id("permanentAddress-label"));
        String expectedPermAddressLabel = "Permanent Address";
        Assert.assertTrue(permAddressLabel.isDisplayed());
        Assert.assertEquals(permAddressLabel.getText(), expectedPermAddressLabel);

        WebElement permAddressInput = driver.findElement(By.id("permanentAddress"));
        permAddressInput.sendKeys("Gruzdziu g 26, Siauliai");

        WebElement submitBtn = driver.findElement(By.id("submit"));
        Assert.assertTrue(submitBtn.isDisplayed());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, "+submitBtn.getLocation().getY()+")");
        submitBtn.click();

        WebElement message = driver.findElement(By.cssSelector(".border.col-md-12.col-sm-12"));
        Assert.assertTrue(message.isDisplayed());

        WebElement nameText = driver.findElement(By.id("name"));
        Assert.assertEquals(nameText.getText(), "Name:Laura Ant");

        WebElement emailText = driver.findElement(By.id("email"));
        Assert.assertEquals(emailText.getText(), "Email:koko@gmail.com");

        List<WebElement> curAddresses = driver.findElements(By.id("currentAddress"));

        String expectedCurAddress1 = "Current Address :7 Juli, Nis";
        String enteredCurAddress = curAddresses.get(1).getText();
        Assert.assertEquals(enteredCurAddress, expectedCurAddress1);

        List<WebElement> permAddresses = driver.findElements(By.id("permanentAddress"));

        String expectedPermAddress1 = "Permananet Address :Gruzdziu g 26, Siauliai";
        String enteredPermAddress = permAddresses.get(1).getText();
        Assert.assertEquals(enteredPermAddress, expectedPermAddress1);

    }
}
