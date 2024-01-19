package c_2024_01_16_utorak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;

public class Selenium6 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://www.wordpress.com/");

        //        Desni klik -> Inspect -> Application -> Cookies sa leve strane i birate wordpress
        Cookie cookie = new Cookie("wordpress_logged_in","laurantnvt%7C1800042224%7C0IQcNC29tVghYiCKL33mn7sJ1CgzQ8DJssBeygtBrsP%7C37b688d106cd89c9c06f8ae8847dc7d38de158f3568b1d47f3b5ab571edaeb2b");
        driver.manage().addCookie(cookie);

        driver.navigate().refresh();

        Thread.sleep(4000);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
