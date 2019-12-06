package ru.domru.perm;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TestOne extends WebDriverSettings {

    @Test
    public void _testOne() throws InterruptedException {

        // Открываем страницу
        driver.get("https://perm.domru.ru/digitalization");

        // Проверяем, та ли страница открылась сверяя заголовок с ожидаемым
        String title = driver.getTitle();
        Assert.assertEquals("Купить ТВ-приставку Movix от провайдера Дом.ru в Перми", title);

        // Клик по "Хочу подключить"
        driver.findElementByCssSelector("[href=\"#connect\"]").click();

        // Ждём завершения анимации прокрутки
        Thread.sleep(1000); // Значение взято из <digitalization.js>

        // Срабатывает раньше завершения анимации, способ не подошёл
        /*WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href=\"/service/feedback\"]")));*/

        // Клик по "Купить"
        //driver.findElement(By.xpath("//*[text()='Купить']")).click();

        // Клик по "Купить" - другой способ
        List<WebElement> list;
        list = driver.findElementsByCssSelector("[class=\"digitalization-purchase__item\"]");
        int i = 2; // 0 - "В аренду", 1 - "В рассрочку", 2 - "Купить";
        list.get(i).findElement(By.cssSelector("[class=\"digitalization-purchase__title\"]")).click();

        // Клик по "Оформить заявку"
        int j = 1; // 0 - "В аренду", 5 - "В рассрочку", 1 - "Купить"
        list.get(i).findElement(By.cssSelector("[data-type-id=\""+j+"\"]")).click();

        // Заполняем и отправляем заявку
        driver.findElementById("domrutvrequestform-name").sendKeys("TEST");
        driver.findElementById("domrutvrequestform-phone").sendKeys("9999999999");
        driver.findElementByCssSelector("[class=\"button__item button__item_adaptive js-button \"]").click();

        // Ждём завершения регистрации
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"text h-font_roboto-light h-color_green h-display_none js-request-success\"]")));

    }

}
