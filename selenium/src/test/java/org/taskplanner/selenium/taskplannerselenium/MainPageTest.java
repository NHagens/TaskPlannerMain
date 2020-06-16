package org.taskplanner.selenium.taskplannerselenium;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

public class MainPageTest {
    private WebDriver chromeDriver;
    private String baseUrl = "http://localhost:3000/";

    public MainPageTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\programs\\Selenium Webdrivers\\chromedriver83.exe");
        chromeDriver = new ChromeDriver();
    }

    private void getUser(WebDriver driver) {
        driver.get(baseUrl + "login");
        driver.findElement(By.id("Test1")).click();
    }

    @Test
    public void siteRunning() {
        chromeDriver.get(baseUrl);

        WebElement element = chromeDriver.findElement(By.id("root"));

        Assert.assertNotNull(element);

        chromeDriver.close();
    }

    @Test
    public void taskAdded() {
        getUser(chromeDriver);

        chromeDriver.navigate().to(baseUrl+"newTask");

        chromeDriver.findElement(By.id("TaskName")).sendKeys("Check1");
        chromeDriver.findElement(By.id("TaskDescription")).sendKeys("Check2");
        chromeDriver.findElement(By.id("StartTime")).sendKeys("2020-06-08T16:00");
        chromeDriver.findElement(By.id("EndTime")).sendKeys("2020-06-08T17:00");

        chromeDriver.findElement(By.id("Save")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assert.assertEquals(baseUrl + "index", currentUrl);

        chromeDriver.close();
    }

    @Test
    public void taskNoEmptyName() {
        getUser(chromeDriver);

        chromeDriver.navigate().to(baseUrl+"newTask");

        //chromeDriver.findElement(By.id("TaskName")).sendKeys("value", "");
        chromeDriver.findElement(By.id("TaskDescription")).sendKeys("Check2");
        chromeDriver.findElement(By.id("StartTime")).sendKeys("2020-06-08T16:00");
        chromeDriver.findElement(By.id("EndTime")).sendKeys("2020-06-08T17:00");

        chromeDriver.findElement(By.id("Save")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assert.assertEquals(baseUrl + "newTask", currentUrl);

        chromeDriver.close();
    }

    @Test
    public void taskNoInvalidDate() {
        getUser(chromeDriver);

        chromeDriver.navigate().to(baseUrl+"newTask");

        chromeDriver.findElement(By.id("TaskName")).sendKeys("Check1");
        chromeDriver.findElement(By.id("TaskDescription")).sendKeys("Check2");
        chromeDriver.findElement(By.id("StartTime")).sendKeys("0806" + Keys.TAB + "2020" + Keys.TAB + "1600");
        chromeDriver.findElement(By.id("EndTime")).sendKeys("0806" + Keys.TAB + "2020" + Keys.TAB + "1500");

        chromeDriver.findElement(By.id("Save")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assert.assertEquals(baseUrl + "newTask", currentUrl);

        chromeDriver.close();
    }
}
