package ru.domru.perm;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {

    public ChromeDriver driver;

    @Before
    public void _before() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/rosti/Documents/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void _after() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

}
