package c_2024_01_16_utorak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Exercise9 {
    public static void main(String[] args) throws InterruptedException {

        /*Zadatak 9
        https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
        Testirati dodavanje knjige u korpu i da li se knjiga obrise kada obrisete kolacice*/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");
        //Amazon requires captcha verification when automation tests are run
        //Thread sleep has been added to verify manually
        Thread.sleep(10000);

        //Since I'm verifying that an item can be added to cart, I need to assert that cart is empty
        //First I'm verifying that the number of cart items on cart badger is 0
        WebElement cartBeforeAdding = driver.findElement(By.id("nav-cart-count"));
        String cartNumber = cartBeforeAdding.getText();
        Assert.assertEquals(cartNumber, "0");

        //Then I enter cart to verify it's actually empty
        WebElement cartButton1 = driver.findElement(By.id("nav-cart"));
        cartButton1.click();

        //The class below is available only when cart is empty
        WebElement emptyCart = driver.findElement(By.cssSelector(".a-row.sc-your-amazon-cart-is-empty"));
        Assert.assertTrue(emptyCart.isDisplayed());

        //Going back to add book to cart
        driver.navigate().back();

        //Before adding book to cart, I find the title of the book in order to be able
        // to verify that the correct book has been added to cart
        WebElement book = driver.findElement(By.id("productTitle"));
        String bookTitleToBeAdded = book.getText();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        WebElement successfulMessage = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertTrue(successfulMessage.isDisplayed());

        //Verifying that the cart number has changed from 0 to 1
        WebElement cartAfterAdding = driver.findElement(By.id("nav-cart-count"));
        cartNumber = cartAfterAdding.getText();
        Assert.assertEquals(cartNumber, "1");

        //Opening the cart to verify the book has been added
        //Since the same button to enter cart is used,
        // element needs to be redefined
        // to avoid stale element exception
        WebElement cartButton2 = driver.findElement(By.id("nav-cart"));
        cartButton2.click();

        //Extracting the title of the book in cart
        WebElement bookInCart = driver.findElement(By.className("a-truncate-cut"));
        String bookTitleInCart = bookInCart.getText();

        //Comparing book in cart with book that needs to be added
        Assert.assertEquals(bookTitleToBeAdded, bookTitleInCart);

        //Deleting cookies and refreshing page
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        //Verifying that the number of items in cart is 0
        WebElement cartAfterRemoving = driver.findElement(By.id("nav-cart-count"));
        cartNumber = cartAfterRemoving.getText();
        Assert.assertEquals(cartNumber, "0");

        WebElement emptyCart1 = driver.findElement(By.cssSelector(".a-row.sc-your-amazon-cart-is-empty"));
        Assert.assertTrue(emptyCart1.isDisplayed());

    }
}