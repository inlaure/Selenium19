package Review;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Prvi {
    public static void main(String[] args) {
//Zadatak 2
//Otvoriti browser i jos 5 tabova
//Na svakom tabu otvoriti URL po zelji
//Zatvoriti sve tabove osim onog gde je otvoren Google
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for(int i = 0; i<5; i++){
            js.executeScript("window.open()");
        }

        ArrayList<String> urlList = new ArrayList<>(driver.getWindowHandles());

        System.out.println(urlList);

        driver.switchTo().window(urlList.get(0));
        driver.get("https://www.google.com/");
        driver.switchTo().window(urlList.get(1));
        driver.get("https://www.linkedin.com");
        driver.switchTo().window(urlList.get(2));
        driver.get("https://www.linkedin.com");
        driver.switchTo().window(urlList.get(3));
        driver.get("https://www.linkedin.com");
        driver.switchTo().window((urlList.get(4)));
        driver.get("https://www.linkedin.com");
//        driver.switchTo().window((urlList.get(5)));
//        driver.get("https://www.linkedin.com");

        for(int i = urlList.size()-1; i>=0; i--){
                driver.switchTo().window(urlList.get(i));
            if(!driver.getCurrentUrl().equals("https://www.google.com/")){
                driver.close();
            }
        }

    }
}
