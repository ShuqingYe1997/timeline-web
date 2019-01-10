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

public class RegisterTest {

    WebDriver driver;

    private String illegal_warning = "Please make sure your username and password are 6~20 characters long, including ONLY letters and digits.";
    private String inconsistent_warning = "Two passwords are not equal.";
    private String duplicated_warning = "Username already existed! Please change a name.";

    @Before
    public void init(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/register");
    }

    @Test
    public void test_normal_register(){

        String username = "RedSwan";
        String password = "sudengdeng";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password);
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        assertEquals(driver.getTitle(),"Login");

    }

    @Test
    public void test_illegal_username_register(){

        String username = "Red";
        String password = "sudengdeng";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password);
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,illegal_warning);

    }


    @Test
    public void test_illegal_password_register(){

        String username = "RedSwan01";
        String password = "@@@haipama?";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password);
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,illegal_warning);

    }

    @Test
    public void test_duplicated_username_register(){

        String username = "xiaoming";
        String password = "12345678";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password);
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,duplicated_warning);

    }

    @Test
    public void test_inconsistent_password_register(){

        String username = "RedSwan";
        String password = "sudengdeng";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password.concat("999"));
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,inconsistent_warning);

    }

    @Test
    public void test_illegal_username_and_password_register(){

        String username = "Red";
        String password = "@@@haipama?";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password);
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,illegal_warning);

    }

    @Test
    public void test_illegal_username_and_inconsistent_password_register(){

        String username = "Red";
        String password = "12345678";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password.concat("666"));
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,inconsistent_warning);

    }

    @Test
    public void test_duplicated_username_and_illegal_password_register(){

        String username = "xiaoming";
        String password = "@@@haipama?";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password);
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,duplicated_warning);

    }

    @Test
    public void test_duplicated_username_and_inconsistent_password_register(){

        String username = "xiaoming";
        String password = "12345678";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password.concat("666"));
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,inconsistent_warning);

    }

    @Test
    public void test_illegal_and_inconsistent_password_register(){

        String username = "RedSwan02";
        String password = "@@@haipama?";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password.concat("999"));
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,inconsistent_warning);

    }

    @Test
    public void test_illegal_username_and_illegal_inconsistent_password_register(){

        String username = "Red";
        String password = "@@@haipama?";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password.concat("666"));
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,inconsistent_warning);

    }

    @Test
    public void test_duplicated_username_and_illegal_inconsistent_password_register(){

        String username = "BenBen";
        String password = "@@@haipama?";

        WebElement userBox = driver.findElement(By.id("registerUsername"));
        WebElement psdBox1 = driver.findElement(By.id("registerPassword1"));
        WebElement psdBox2 = driver.findElement(By.id("registerPassword2"));
        userBox.sendKeys(username);
        psdBox1.sendKeys(password);
        psdBox2.sendKeys(password.concat("666"));
        WebElement registerBtn = driver.findElement(By.id("register"));
        registerBtn.click();
        //跳转
        WebElement messageBox = driver.findElement(By.id("message"));
        String errorMessage = messageBox.getText();
        assertEquals(errorMessage,inconsistent_warning);

    }

    @After
    public void end(){
        driver.quit();
    }
}
