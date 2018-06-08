package selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

public class MyTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();

    }


    @Test
    public void logIn() throws Exception {
        driver.navigate().to("http://localhost:8080/houses");
        Thread.sleep(2500);
        driver.findElement(By.name("btnLogin")).sendKeys(Keys.RETURN);
        Thread.sleep(2500);
        String url1 = driver.getCurrentUrl();
        assertEquals(url1, "https://localhost:8443/login");
        driver.findElement(By.name("login")).sendKeys("wrong");
        driver.findElement(By.name("pass")).sendKeys("pass_log");
        Thread.sleep(2500);
        driver.findElement(By.name("btnLogin")).sendKeys(Keys.RETURN);
        String url2 = driver.getCurrentUrl();
        assertEquals(url2, "https://localhost:8443/login?error");//Unknown username and password.
        (new WebDriverWait(driver, 1)).until((ExpectedCondition<Boolean>) (WebDriver d) ->
                d.getPageSource().contains("Некорректный пароль или логин"));
        Thread.sleep(2500);
        driver.quit();
    }


    @Test
    public void add_delete() throws Exception {
        driver.navigate().to("http://localhost:8080/houses");
        driver.findElement(By.name("btnLogin")).sendKeys(Keys.RETURN);
        driver.findElement(By.name("login")).sendKeys("user");
        driver.findElement(By.name("pass")).sendKeys("hello");
        driver.findElement(By.name("btnLogin")).sendKeys(Keys.RETURN);
        Thread.sleep(2500);
        driver.findElement(By.name("toMain")).sendKeys(Keys.RETURN);
        Thread.sleep(2500);
        driver.findElement(By.name("id")).sendKeys("10");
        driver.findElement(By.name("name")).sendKeys("name");
        driver.findElement(By.name("address")).sendKeys("address");
        driver.findElement(By.name("phone")).sendKeys("phone");
        driver.findElement(By.name("cost")).sendKeys("2025");
        Thread.sleep(2500);
        driver.findElement(By.name("addHouse")).sendKeys(Keys.RETURN);
        Thread.sleep(2500);

        (new WebDriverWait(driver, 1)).until((ExpectedCondition<Boolean>) (WebDriver d) -> d.getPageSource().contains("id")
                && d.getPageSource().contains("name")
                && d.getPageSource().contains("address")
                && d.getPageSource().contains("phone")
                && d.getPageSource().contains("2025"));


        (new WebDriverWait(driver, 1)).until((ExpectedCondition<Boolean>) (WebDriver d) ->
                d.getPageSource().contains("10"));
        Thread.sleep(2500);
        driver.findElement(By.name("delHouse")).sendKeys(Keys.RETURN);
        Thread.sleep(2500);
        (new WebDriverWait(driver, 1)).until((ExpectedCondition<Boolean>) (WebDriver d) ->
                !( d.getPageSource().contains("2025")));
        Thread.sleep(2500);
        driver.quit();
    }


    @Test
    public void searching() throws Exception {
        driver.navigate().to("http://localhost:8080/houses");
        Thread.sleep(2500);
        driver.findElement(By.name("toSearch")).sendKeys(Keys.RETURN);
        Thread.sleep(2500);
        driver.findElement(By.name("id")).sendKeys("2");
        Thread.sleep(2500);
        driver.findElement(By.name("btnSearch")).sendKeys(Keys.RETURN);
        (new WebDriverWait(driver, 1)).until((ExpectedCondition<Boolean>) (WebDriver d) -> d.getPageSource().contains("2")
                && d.getPageSource().contains("Имя2")
                && d.getPageSource().contains("М.Садовая 19")
                && d.getPageSource().contains("89118972874")
                && d.getPageSource().contains("17800"));
        Thread.sleep(2500);
        driver.quit();
    }

}