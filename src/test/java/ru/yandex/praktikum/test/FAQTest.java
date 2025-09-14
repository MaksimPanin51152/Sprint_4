package ru.yandex.praktikum.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.Constants;
import ru.yandex.praktikum.page.HomePage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQTest {

    private WebDriver driver;
    private final String browserName;
    private final String questionText;
    private final String expectedAnswer;

    public FAQTest(String browserName, String questionText, String expectedAnswer) {
        this.browserName = browserName;
        this.questionText = questionText;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "Browser: {0}, Question: {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"chrome", "Сколько это стоит? И как оплатить?", Constants.ANSWER_COST},
                {"chrome", "Хочу сразу несколько самокатов! Так можно?", Constants.ANSWER_MULTIPLE_SCOOTERS},
                {"chrome", "Как рассчитывается время аренды?", Constants.ANSWER_RENT_TIME},
                {"chrome", "Можно ли заказать самокат прямо на сегодня?", Constants.ANSWER_TODAY_ORDER},
                {"chrome", "Можно ли продлить заказ или вернуть самокат раньше?", Constants.ANSWER_PROLONGATION},
                {"chrome", "Вы привозите зарядку вместе с самокатом?", Constants.ANSWER_CHARGER},
                {"chrome", "Можно ли отменить заказ?", Constants.ANSWER_CANCEL},
                {"chrome", "Я живу за МКАДом, привезёте?", Constants.ANSWER_OUTSIDE_MKAD},

                {"firefox", "Сколько это стоит? И как оплатить?", Constants.ANSWER_COST},
                {"firefox", "Хочу сразу несколько самокатов! Так можно?", Constants.ANSWER_MULTIPLE_SCOOTERS},
                {"firefox", "Как рассчитывается время аренды?", Constants.ANSWER_RENT_TIME},
                {"firefox", "Можно ли заказать самокат прямо на сегодня?", Constants.ANSWER_TODAY_ORDER},
                {"firefox", "Можно ли продлить заказ или вернуть самокат раньше?", Constants.ANSWER_PROLONGATION},
                {"firefox", "Вы привозите зарядку вместе с самокатом?", Constants.ANSWER_CHARGER},
                {"firefox", "Можно ли отменить заказ?", Constants.ANSWER_CANCEL},
                {"firefox", "Я живу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

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
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkFAQAnswer() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.scrollToFAQ();
        homePage.clickQuestion(questionText);

        String actualAnswer = homePage.getAnswerText();

        assertEquals("Текст ответа не совпадает для вопроса: " + questionText,
                expectedAnswer, actualAnswer);
    }
}
