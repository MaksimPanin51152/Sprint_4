package ru.yandex.praktikum.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.page.FAQPage;
import ru.yandex.praktikum.page.HomePage;

public class FAQTest {

    @Test
    public void FAQCorrectAnswerTextChrome() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        WebElement tableFAQ = driver.findElement(By.xpath(".//div[@class='accordion']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);

        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickQuestion1();
        objHomePage.isCorrectText(objHomePage.getAnswer1(), FAQPage.answer1Text);

        objHomePage.clickQuestion2();
        objHomePage.isCorrectText(objHomePage.getAnswer2(), FAQPage.answer2Text);

        objHomePage.clickQuestion3();
        objHomePage.isCorrectText(objHomePage.getAnswer3(), FAQPage.answer3Text);

        objHomePage.clickQuestion4();
        objHomePage.isCorrectText(objHomePage.getAnswer4(), FAQPage.answer4Text);

        objHomePage.clickQuestion5();
        objHomePage.isCorrectText(objHomePage.getAnswer5(), FAQPage.answer5Text);

        objHomePage.clickQuestion6();
        objHomePage.isCorrectText(objHomePage.getAnswer6(), FAQPage.answer6Text);

        objHomePage.clickQuestion7();
        objHomePage.isCorrectText(objHomePage.getAnswer7(), FAQPage.answer7Text);

        objHomePage.clickQuestion8();
        objHomePage.isCorrectText(objHomePage.getAnswer8(), FAQPage.answer8Text);

        driver.quit();
    }

    @Test
    public void FAQCorrectAnswerTextFirefox() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        WebElement tableFAQ = driver.findElement(By.xpath(".//div[@class='accordion']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);

        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickQuestion1();
        objHomePage.isCorrectText(objHomePage.getAnswer1(), FAQPage.answer1Text);

        objHomePage.clickQuestion2();
        objHomePage.isCorrectText(objHomePage.getAnswer2(), FAQPage.answer2Text);

        objHomePage.clickQuestion3();
        objHomePage.isCorrectText(objHomePage.getAnswer3(), FAQPage.answer3Text);

        objHomePage.clickQuestion4();
        objHomePage.isCorrectText(objHomePage.getAnswer4(), FAQPage.answer4Text);

        objHomePage.clickQuestion5();
        objHomePage.isCorrectText(objHomePage.getAnswer5(), FAQPage.answer5Text);

        objHomePage.clickQuestion6();
        objHomePage.isCorrectText(objHomePage.getAnswer6(), FAQPage.answer6Text);

        objHomePage.clickQuestion7();
        objHomePage.isCorrectText(objHomePage.getAnswer7(), FAQPage.answer7Text);

        objHomePage.clickQuestion8();
        objHomePage.isCorrectText(objHomePage.getAnswer8(), FAQPage.answer8Text);

        driver.quit();
    }
}
