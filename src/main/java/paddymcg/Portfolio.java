package paddymcg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Portfolio {
    WebDriver driver;

    public Portfolio(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement navbarHeading(){
        return driver.findElement(By.cssSelector("a.navbar-brand"));
    }

    public List<WebElement> navbarItems(){
        return driver.findElements(By.cssSelector("li.nav-item"));
    }

}
