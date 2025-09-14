package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By faqSection = By.className("Home_FourPart__1uthg");
    private final String questionPattern = "//div[text()='%s']";
    private final By openedAnswer = By.xpath("//div[@class='accordion__panel' and not(@hidden)]");

    private final By orderButtonHeader = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By orderButtonBottom = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button");
    private final By cookieButton = By.id("rcc-confirm-button");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void acceptCookies() {
        try {
            WebElement cookie = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            cookie.click();
        } catch (Exception ignored) {
            // баннера может не быть
        }
    }

    public void scrollToFAQ() {
        WebElement faq = wait.until(ExpectedConditions.visibilityOfElementLocated(faqSection));
        faq.click();
    }

    public void clickQuestion(String questionText) {
        By questionLocator = By.xpath(String.format(questionPattern, questionText));
        wait.until(ExpectedConditions.elementToBeClickable(questionLocator)).click();
    }

    public String getAnswerText() {
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(openedAnswer));
        return answer.getText().trim();
    }

    public void clickOrderButtonHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonHeader)).click();
    }

    public void clickOrderButtonBottom() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
    }
}
