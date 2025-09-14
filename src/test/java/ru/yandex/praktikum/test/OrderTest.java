package ru.yandex.praktikum.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.OrderPage;

import static org.junit.Assert.assertTrue;

public class OrderTest {

    private WebDriver driver;
    private final String browserName = "chrome"; // можно менять на "firefox"

    @Before
    public void setUp() {
        if ("chrome".equals(browserName)) {
            driver = new ChromeDriver();
        } else if ("firefox".equals(browserName)) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Браузер не поддерживается: " + browserName);
        }
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/order");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void createOrderSuccessfully() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.acceptCookies();

        orderPage.fillName("Иван");
        orderPage.fillSurname("Иванов");
        orderPage.fillAddress("Москва, Тверская 1");
        orderPage.selectMetro("Тверская");
        orderPage.fillPhone("+79998887766");
        orderPage.clickNext();

        orderPage.setDate("15.09.2025");
        orderPage.selectRentalPeriod("двое суток");
        orderPage.chooseScooterColor("black");
        orderPage.fillComment("Позвонить за 30 минут до приезда");
        orderPage.submitOrder();

        String header = orderPage.getOrderModalHeader();
        String text = orderPage.getOrderModalText();

        assertTrue("Окно подтверждения заказа не открылось",
                header.contains("Заказ оформлен") || text.contains("Заказ оформлен"));
    }
}


