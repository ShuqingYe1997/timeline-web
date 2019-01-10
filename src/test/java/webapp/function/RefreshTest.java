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

import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RefreshTest {

    WebDriver driver;

    @Before
    public void init(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/demo/login");
    }

    @Test
    public void test_refresh_add_a_new_post() throws Exception{

        String username = "xiaoming";
        String password = "12345678";

        WebElement userBox = driver.findElement(By.id("inputUsername"));
        WebElement psdBox = driver.findElement(By.id("inputPassword"));
        userBox.sendKeys(username);
        psdBox.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.id("signin"));
        loginBtn.click();
        //跳转

        int _old = 0, _new = 0;
        List<WebElement> oldPosts = driver.findElements(By.tagName("li"));
        for(WebElement e: oldPosts){
            if(e.getAttribute("type").equals("post")){
                _old++;
            }
        }
        WebElement refreshBtn = driver.findElement(By.id("refresh"));
        refreshBtn.click();
        //刷新
        List<WebElement> newPosts = driver.findElements(By.tagName("li"));
        for(WebElement e: newPosts){
            if(e.getAttribute("type").equals("post")){
                _new++;
            }
        }
        assertEquals(_old+1,_new);

        WebElement newPublisher = driver.findElement(By.className("published"));
        assertThat(newPublisher.getText(), anyOf(
                containsString("nishishazima".toUpperCase()),
                containsString("hahaha".toUpperCase()),
                containsString("Goodbye886".toUpperCase()),
                containsString("BenBen".toUpperCase()),
                containsString("xiaoming".toUpperCase())
        ));

    }

    @After
    public void end(){
        driver.quit();
    }

}
