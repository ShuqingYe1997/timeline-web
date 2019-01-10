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

import static org.junit.Assert.assertTrue;

public class LoadmoreTest {

    WebDriver driver;

    @Before
    public void init(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/demo/login");
    }

    @Test
    public void testLoadmore(){

        String username = "xiaoming";
        String password = "12345678";

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys(username);
        psdBox.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转

        WebElement onePost = driver.findElement(By.className("blogBox"));
        int width = onePost.getSize().getWidth();
        int height = onePost.getSize().getHeight();
        System.out.println(width);
        System.out.println(height);

        int x1,y1;
        WebElement loadmoreBtn = driver.findElement(By.id("loadMore"));
        x1 = loadmoreBtn.getRect().getX();
        y1 = loadmoreBtn.getRect().getY();
        System.out.println("x:"+x1);
        System.out.println("y:"+y1);
        loadmoreBtn.click();
        //加载更多

        int x2,y2;
        x2 = loadmoreBtn.getRect().getX();
        y2 = loadmoreBtn.getRect().getY();
        System.out.println("x:"+x2);
        System.out.println("y:"+y2);
        assertTrue(y2>y1 && x1==x2);
    }

    @After
    public void end(){
        driver.quit();
    }

}
