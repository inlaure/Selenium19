package DomaciZadaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.openqa.selenium.Keys.ENTER;

public class DomaciZadatak1 {
    public static void main(String[] args) {
//        Domaci Zadatak:
//        Otici na Google, zatim preko browser-a otici na YouTube i pustiti neku pesmu po Vasem izboru.

//        Dodala sam implicitlyWait jer u vecini slucajeva sve se otvara kako treba, a ponekad zabaguje i uopste ne klikne na Youtube link ili na link pesme.
//        Primetila sam da sve radi kad je Chrome iskljucen i sve se radi ispocetka. Nadam se da i tebi ce raditi tako :D

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea")).sendKeys("YouTube");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea")).sendKeys(ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("/html/body/div[5]/div/div[9]/div[1]/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div/span/a")).click();
        driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/form/div[1]/div[1]/input")).sendKeys("marijonas mikutavicius trys milijonai krepsinis");
        driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/form/div[1]/div[1]/input")).sendKeys(ENTER);
//        driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a/yt-formatted-string")).click();
//        driver.quit();

    }
}
