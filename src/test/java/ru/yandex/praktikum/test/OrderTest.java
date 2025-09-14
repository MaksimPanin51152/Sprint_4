package ru.yandex.praktikum.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.Constants;
import ru.yandex.praktikum.page.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private OrderPage orderPage;

    private final String browser;
    private final String buttonType;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String comment;

    public OrderTest(String browser,
                     String buttonType,
                     String name,
                     String surname,
                     String address,
                     String metroStation,
                     String phone,
                     String date,
                     String comment) {
        this.browser = browser;
        this.buttonType = buttonType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Browser: {0}, Button: {1}, User: {2} {3}")
    public static Object[][] testData() {
        return new Object[][]{
                {"chrome", "header", "Иван", "Иванов", "Москва, ул. Пушкина, д. 10", "Сокольники", "+79991234567", "20.12.2025", "Позвоните за 30 минут"},
                {"firefox", "page", "Мария", "Петрова", "Москва, ул. Ленина, д. 5", "Арбатская", "+79997654321", "25.12.2025", "Не звоните, сплю"},
        };
    }

    @Before
    public void setUp() {
        if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testScooterOrderFlow() {
        makeOrder(buttonType, name, surname, address, metroStation, phone, date, comment);
        assertTrue("Ожидалось подтверждение заказа, но окно не появилось", orderPage.isOrderConfirmed());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void makeOrder(String buttonType,
                           String name,
                           String surname,
                           String address,
                           String metroStation,
                           String phone,
                           String date,
                           String comment) {

        if ("header".equals(buttonType)) {
            driver.findElement(By.xpath(Constants.ORDER_BUTTON_HEADER)).click();
        } else {
            WebElement bottomBtn = driver.findElement(By.xpath(Constants.ORDER_BUTTON_BOTTOM));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bottomBtn);
            bottomBtn.click();
        }

        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.selectMetroStation(metroStation);
        orderPage.enterPhone(phone);
        orderPage.clickNextButton();

        orderPage.enterDate(date);
        orderPage.selectRentalPeriod();
        orderPage.chooseScooterColorBlack();
        orderPage.enterComment(comment);
        orderPage.clickOrderButton();

        orderPage.confirmOrder();
    }
}
