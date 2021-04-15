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
        driver.navigate().to("http://patrickmcgon.com");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        Portfolio portfolio = new Portfolio(driver);

        WebDriverHelper.close(driver, true);

    }
}
