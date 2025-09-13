package ru.yandex.praktikum.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.HomePage;
import ru.yandex.praktikum.page.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private HomePage homePage;
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
                {"chrome", "page", "Мария", "Петрова", "Москва, ул. Ленина, д. 5", "Арбатская", "+79997654321", "25.12.2025", "Не звоните, сплю"},
                {"firefox", "header", "Алексей", "Сидоров", "Москва, пр-т Мира, д. 15", "ВДНХ", "+79993456789", "30.12.2025", "Оставьте у консьержа"},
                {"firefox", "page", "Елена", "Кузнецова", "Москва, ул. Тверская, д. 1", "Тверская", "+79998887766", "01.01.2026", "Позвоните за 10 минут"}
        };
    }

    @Before
    public void setUp() {
        if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);

        homePage.open();
        homePage.acceptCookies();
    }

    @Test
    public void testScooterOrderFlow() {
        makeOrder(buttonType, name, surname, address, metroStation, phone, date, comment);

        assertTrue(
                "Ожидалось подтверждение заказа, но окно не появилось",
                orderPage.isOrderConfirmed()
        );
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
            homePage.clickHeaderOrderButton();
        } else {
            homePage.clickPageOrderButton();
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
