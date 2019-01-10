package webapp.function;

/**
 * Created by smcsvip on 2019/1/4.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class LogoutTest {

    WebDriver driver;

    @Before
    public void init(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/demo/login");
    }

    @Test
    public void testLogout(){

        String username = "xiaoming";
        String password = "12345678";

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys(username);
        psdBox.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement logoutBtn = driver.findElement(By.id("logout"));
        logoutBtn.click();
        assertEquals(driver.getTitle(),"Logout");

        WebElement backToLoginBtn = driver.findElement(By.id("back_to_login"));
        backToLoginBtn.click();
        //跳转
        assertEquals(driver.getTitle(),"Login");
    }


    @After
    public void end(){
        driver.quit();
    }

}
