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
import ru.yandex.praktikum.page.Constants;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQTest {

    private WebDriver driver;
    private final String browserName;
    private final String questionId;
    private final String answerId;
    private final String expectedAnswer;

    public FAQTest(String browserName, String questionId, String answerId, String expectedAnswer) {
        this.browserName = browserName;
        this.questionId = questionId;
        this.answerId = answerId;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "Browser: {0}, FAQ Question: {1}")
    public static Object[][] getFAQData() {
        return new Object[][]{
                {"chrome", Constants.QUESTION_COST, Constants.ANSWER_COST},
                {"chrome", Constants.QUESTION_MULTIPLE_SCOOTERS, Constants.ANSWER_MULTIPLE_SCOOTERS},
                {"chrome", Constants.QUESTION_RENTAL_TIME, Constants.ANSWER_RENT_TIME},
                {"chrome", Constants.QUESTION_TODAY_ORDER, Constants.ANSWER_TODAY_ORDER},
                {"chrome", Constants.QUESTION_PROLONGATION, Constants.ANSWER_PROLONGATION},
                {"chrome", Constants.QUESTION_CHARGER, Constants.ANSWER_CHARGER},
                {"chrome", Constants.QUESTION_CANCEL, Constants.ANSWER_CANCEL},
                {"chrome", Constants.QUESTION_OUTSIDE_MKAD, Constants.ANSWER_OUTSIDE_MKAD},

                {"firefox", Constants.QUESTION_COST, Constants.ANSWER_COST},
                {"firefox", Constants.QUESTION_MULTIPLE_SCOOTERS, Constants.ANSWER_MULTIPLE_SCOOTERS},
                {"firefox", Constants.QUESTION_RENTAL_TIME, Constants.ANSWER_RENT_TIME},
                {"firefox", Constants.QUESTION_TODAY_ORDER, Constants.ANSWER_TODAY_ORDER},
                {"firefox", Constants.QUESTION_PROLONGATION, Constants.ANSWER_PROLONGATION},
                {"firefox", Constants.QUESTION_CHARGER, Constants.ANSWER_CHARGER},
                {"firefox", Constants.QUESTION_CANCEL, Constants.ANSWER_CANCEL},
                {"firefox", Constants.QUESTION_OUTSIDE_MKAD, Constants.ANSWER_OUTSIDE_MKAD},
        };
    }


    @Before
    public void setUp() {
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        driver.manage().window().maximize();
    }

    @Test
    public void testFAQAnswers() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.acceptCookies();
        homePage.scrollToFAQ();
        homePage.clickFAQ(questionId);
        String actualAnswer = homePage.getFAQAnswerText(answerId);

        assertEquals("FAQ answer mismatch for: " + questionId, expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
