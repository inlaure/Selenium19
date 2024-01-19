package c_2024_01_18_cetvrtak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Zadatak10 {
    public static void main(String[] args) {
        //Zadatak 10
//Otici na stranicu https://imgflip.com/memegenerator
//Uploadovati sliku od koje treba napraviti mim
//Mim mora biti vezan za IT, QA ili kurs
//Sliku rucno skinuti i ubaciti na Slack thread poruku
//Autor mima sa najvise lajkova dobija pivo na druzenju
//Napomena: Izazov zadatka je kod lokatora, assertove ne treba dodavati i ne treba raditi sa anotacijama

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        driver.get("https://imgflip.com/memegenerator");
        String imagePath = "C:\\Users\\PC\\Desktop\\java.jpg";

//        wait.until(ExpectedConditions.elementToBeClickable(By.id("mm-show-upload")));
        WebElement uploadTemplate = driver.findElement(By.id("mm-show-upload"));
        uploadTemplate.click();

        WebElement uploadFile = driver.findElement(By.id("mm-upload-file"));
        uploadFile.sendKeys(imagePath);

        WebElement uploadBtn = driver.findElement(By.id("mm-upload-btn"));
        uploadBtn.click();

        WebElement textField = driver.findElement(By.cssSelector("textarea[placeholder='Top Text']"));
        textField.sendKeys("Marko objasnjava Javu: \"AKo me razumes\"\n" +
                "Ja:");

        WebElement generateBtn = driver.findElement(By.cssSelector(".mm-generate.b.but"));
        generateBtn.click();

        //Save image
//        WebElement meme = driver.findElement(By.id("done-img"));
//        wait.until(ExpectedConditions.attributeContains(meme, "src", "imgflip.com"));
//        String link = meme.getAttribute("src");
//        URL imageURL = new URL(link);
//        BufferedImage saveImage = ImageIO.read(imageURL);
//        ImageIO.write(saveImage, "png", new File("C:\\Users\\drago\\Desktop\\meme.png"));

    }
}
