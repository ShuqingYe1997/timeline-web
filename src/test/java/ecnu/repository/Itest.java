package ecnu.repository;

/**
 * Created by smcsvip on 2019/1/2.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;


public class Itest {

    WebDriver driver;

    @Before
    public void init(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/get");
    }

    @Test
    public void testLogin(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("xiaoming");
        psdBox.sendKeys("12345678");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        assertEquals(driver.getTitle(),"My Timeline");
    }

    @Test
    public void testRegister(){
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        assertEquals(driver.getTitle(),"Register");
//        assertEquals(driver.getCurrentUrl(),"http://localhost:8080/register");
    }

    @After
    public void end(){
        driver.quit();
    }
}
