package org.taskplanner.selenium.taskplannerselenium;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageTest {
    private WebDriver chromeDriver;
    private String baseUrl = "http://localhost:3000/";

    public MainPageTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\programs\\Selenium Webdrivers\\chromedriver83.exe");
        chromeDriver = new ChromeDriver();
    }

    @Test
    public void seleniumWorks() {
        chromeDriver.get("https://google.com/");
        WebElement element = chromeDriver.findElement(By.id("hplogo"));

        Assert.assertNotNull(element);
        Assert.assertEquals("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png", element.getAttribute("src"));

        chromeDriver.close();
    }

    @Test
    public void taskAdded() {
        chromeDriver.get(baseUrl + "newTask");

        chromeDriver.findElement(By.id("TaskName")).sendKeys("value", "Check1");
        chromeDriver.findElement(By.id("TaskDescription")).sendKeys("value", "Check2");
        chromeDriver.findElement(By.id("StartTime")).sendKeys("value", "2020-06-08T$16:00");
        chromeDriver.findElement(By.id("EndTime")).sendKeys("value", "2020-06-08T$17:00");

        chromeDriver.findElement(By.id("Save")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assert.assertEquals("http://localhost:3000/index", currentUrl);

        chromeDriver.close();
    }

    @Test
    public void taskNoEmptyName() {
        chromeDriver.get(baseUrl + "newTask");

        chromeDriver.findElement(By.id("TaskName")).sendKeys("value", "");
        chromeDriver.findElement(By.id("TaskDescription")).sendKeys("value", "Check2");
        chromeDriver.findElement(By.id("StartTime")).sendKeys("value", "2020-06-08T$16:00");
        chromeDriver.findElement(By.id("EndTime")).sendKeys("value", "2020-06-08T$17:00");

        chromeDriver.findElement(By.id("Save")).click();

        WebElement alert = chromeDriver.findElement(By.id("invalidAlert"));
        String currentUrl = chromeDriver.getCurrentUrl();

        Assert.assertNotNull(alert);
        Assert.assertEquals("http://localhost:3000/newTask", currentUrl);

        chromeDriver.close();
    }
}
