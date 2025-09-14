package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5); // Selenium 3: время в секундах
    }

    // клик по вопросу
    public void clickQuestion(String questionText) {
        WebElement question = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(),'" + questionText + "')]")
                )
        );
        question.click();
    }

    // проверка ответа (ждём, пока текст появится)
    public void checkAnswerText(String questionText, String expectedAnswer) {
        WebElement answer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'accordion__panel') and not(@hidden)]")
                )
        );
        String actualText = answer.getText().trim();
        assertEquals("Текст ответа не совпадает для вопроса: " + questionText,
                expectedAnswer, actualText);
    }

    // скролл к FAQ
    public void scrollToFAQ() {
        WebElement faqBlock = driver.findElement(By.className("Home_FAQ__3uVm4"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqBlock);
    }

    // клики по кнопкам заказа и куки можешь оставить как есть
}
