package ru.yandex.praktikum.test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.FAQPage;
import ru.yandex.praktikum.page.HomePage;
import ru.yandex.praktikum.page.OrderPage;

public class OrderTest {

    @Test
    public void OrderPositiveTestChrome() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickHeaderOrderButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.acceptCookieButtonClick();
        objOrderPage.setName("Иван");
        objOrderPage.setSurname("Иванов");
        objOrderPage.setAddress("г. Москва, ул. Пушкина, д.10");
        objOrderPage.setSubway("Театральная");
        objOrderPage.setPhoneNumber("89151234567");
        objOrderPage.clickOrderNextButton();
        objOrderPage.setDate("01.01.2050");
        objOrderPage.setRentalPeriod("сутки");
        objOrderPage.setColor("чёрный жемчуг");
        objOrderPage.setComment("Не звонить в дверь");
        objOrderPage.clickOrderCreateButton();
        objOrderPage.clickOrderConfirmButton();

        objOrderPage.isPageOpen(objOrderPage.getConfirmHeader(), FAQPage.confirmHeader);

        driver.quit();
    }

    @Test
    public void OrderPositiveTestFirefox() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickHeaderOrderButton();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.acceptCookieButtonClick();
        objOrderPage.setName("Ирина");
        objOrderPage.setSurname("Авдеева");
        objOrderPage.setAddress("проспект Маяковского 6");
        objOrderPage.setSubway("Маяковская");
        objOrderPage.setPhoneNumber("+79657654321");
        objOrderPage.clickOrderNextButton();
        objOrderPage.setDate("10.10.2030");
        objOrderPage.setRentalPeriod("двое суток");
        objOrderPage.setColor("серая безысходность");
        objOrderPage.setComment("Привезите чистый самокат");
        objOrderPage.clickOrderCreateButton();
        objOrderPage.clickOrderConfirmButton();

        objOrderPage.isPageOpen(objOrderPage.getConfirmHeader(), FAQPage.confirmHeader);

        driver.quit();
    }
}
