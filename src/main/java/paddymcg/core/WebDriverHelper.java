package paddymcg.core;

// import java.net.http.WebSocket;
// import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// import javax.print.DocFlavor.STRING;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.edge.EdgeDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelper {
    private static String driverLocation = null;
    // private static Path currentDirectory = null;

    public static void setDriverLocation(String location){
        driverLocation = location;
    }

    public static String getDriverLocation(){
        return driverLocation != null ? driverLocation: Paths.get("").toAbsolutePath().toString();
    }

    public static WebDriver open(String browserType){
        WebDriver driver = null;

        switch(browserType){
            case BrowserType.IE:
            case BrowserType.IEXPLORE:
                driver = new InternetExplorerDriver(optionsIE());
                break;
            // case BrowserType.EDGE:
            //     driver = new EdgeDriver(optionsEdge());
            //     break;
            // case BrowserType.FIREFOX:
            //     driver = new FirefoxDriver(optionsFirefox());
            //     break;
            case BrowserType.CHROME:
            case BrowserType.GOOGLECHROME:
                driver = new ChromeDriver(optionsGC(false));
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver open(String browserType, String url) {
        WebDriver driver = open(browserType);

        System.out.println("Navigating to url : " + url);

        driver.navigate().to(url);
        
        return driver;
    }

    protected static InternetExplorerOptions optionsIE(){
        InternetExplorerOptions options = new InternetExplorerOptions();

        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        
        System.setProperty("webdriver.ie.driver", getDriverLocation() + "\\IEDriverServer.exe");

        return options;
    }

    protected static ChromeOptions optionsGC(Boolean isHeadless){
        ChromeOptions options = new ChromeOptions();

        if(isHeadless){
            List<String> args = new ArrayList<String>();
            args.add(0,"--headless");
            args.add(1,"--disable-gpu");
            args.add(2,"--windo-size-1600x960");

            options.addArguments(args);
        }

        System.setProperty("webdriver.chrome.driver", getDriverLocation()  +"\\chromedriver.exe");

        return options;
    }

    //region helper methods
    public static JavascriptExecutor getJavascriptExecutor(WebDriver driver){
        return((JavascriptExecutor) driver);
    }

    public static void scrollDown(WebDriver driver){
        getJavascriptExecutor(driver).executeScript("window.scrollTo(window.pageXOffset, window.pageYOffset + 500);");
    }

    public static void scrollUp(WebDriver driver){
        getJavascriptExecutor(driver).executeScript("window.scrollTo(window.pageXOffset, window.pageYOffset - 500);");
    }

    public static RemoteWebDriver getRemoteWebDriver(WebDriver driver){
        return((RemoteWebDriver) driver);
    }

    public static Capabilities getCapabilities(WebDriver driver){
        return getRemoteWebDriver(driver).getCapabilities();
    }
    
    public static void close(WebDriver driver){
        if(driver != null){
            if(getRemoteWebDriver(driver).getSessionId() != null){
                driver.close();
                driver.quit();
            }
        }
    }

    public static void close(WebDriver driver, boolean fromBaseTestSuite){
        if(driver != null){
            if(getRemoteWebDriver(driver).getSessionId() != null){
                driver.close();
                driver.quit();
            }
            if(fromBaseTestSuite){
                System.out.println("Cleaning up web driver...");
            }
        }
    }

    public static void hoverOver(WebDriver driver, WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static WebElement getLink(WebElement element){
        return element.findElement(By.cssSelector("a"));
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement getClickableLink(WebDriver driver, WebElement element){
        return waitForElementToBeClickable(driver, getLink(element));
    }
}
