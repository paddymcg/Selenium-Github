package paddymcg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;

import paddymcg.core.WebDriverHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        WebDriverHelper.setDriverLocation("C:\\WebDrivers");

        WebDriver driver = WebDriverHelper.open(BrowserType.CHROME);
        driver.navigate().to("https://twitter.com");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        Twitter twitter = new Twitter(driver);
        WebElement l = twitter.loginField();
        WebElement p = twitter.pwdField();

        l.sendKeys("qwwe");
        // p.sendKeys("pamsdnjasn");

        System.out.println(l);
        System.out.println(p);

        WebDriverHelper.close(driver, true);

    }
}
