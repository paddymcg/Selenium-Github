package paddymcg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Twitter {
    WebDriver driver;

    public Twitter(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> loginFields(){
        return driver.findElements(By.cssSelector("input"));
    }
    public WebElement loginField(){
        // return loginFields().get(0);
        return driver.findElement(By.xpath("//*[@id='react-root']/div/div/div/main/div/div/div[1]/div[1]/div[1]/div/form/div/div[1]/div/label/div/div[2]/div/input"));
    }
    public WebElement pwdField(){
        return loginFields().get(1);
    }
    // public WebElement emailField(){
    //     return loginFields().;
    // }
}
