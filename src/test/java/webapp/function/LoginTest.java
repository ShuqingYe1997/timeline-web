package webapp.function;

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

import static org.junit.Assert.assertEquals;


public class LoginTest {

    WebDriver driver;
    private String wrong_warning = "Wrong Username or password";
    private String empty_warning = "Username and/or password cannot be empty!";
    private String username_empty_warning = "username cannot be empty";
    private String psd_empty_warning = "password cannot be empty";

    @Before
    public void init(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/demo/login");
    }

    @Test
    public void test_normal_Login(){

        String username = "xiaoming";
        String password = "12345678";

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys(username);
        psdBox.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        assertEquals(driver.getTitle(),"My Timeline");
        WebElement usernameTitle = driver.findElement(By.id("usernameTitle"));
        assertEquals(usernameTitle.getText(),username.toUpperCase());
    }

    @Test
    public void test_wrong_username_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("xiaoming2b");
        psdBox.sendKeys("12345678");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,wrong_warning);

    }

    @Test
    public void test_wrong_password_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("xiaoming");
        psdBox.sendKeys("12345678bb");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,wrong_warning);

    }

    @Test
    public void test_wrong_username_and_password_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("xiaoming2b");
        psdBox.sendKeys("12345678bb");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,wrong_warning);

    }

    @Test
    public void test_null_username_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("");
        psdBox.sendKeys("12345678");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,username_empty_warning);

    }

    @Test
    public void test_null_password_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("xiaoming");
        psdBox.sendKeys("");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,psd_empty_warning);

    }

    @Test
    public void test_null_username_and_password_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("");
        psdBox.sendKeys("");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,empty_warning);

    }

    @Test
    public void test_null_username_and_wrong_password_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("");
        psdBox.sendKeys("meiyouzhegemima");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,wrong_warning);

    }

    @Test
    public void test_wrong_username_and_null_password_login(){

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys("meiyouzhegeyonghu");
        psdBox.sendKeys("");
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,wrong_warning);

    }

    @After
    public void end(){
        driver.quit();
    }
}
