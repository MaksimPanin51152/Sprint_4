package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
    private final WebDriver driver;

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    private final By headerOrderButton = By.xpath("(//button[text()='Заказать'])[1]");
    private final By pageOrderButton = By.xpath("(//button[text()='Заказать'])[2]");

    private final By cookieAcceptButton = By.xpath(".//button[text()='да все привыкли']");

    private final By faqAccordion = By.cssSelector(".accordion");

    private final By questionCost = By.id("accordion__heading-0");
    private final By questionMultipleScooters = By.id("accordion__heading-1");
    private final By questionRentalTime = By.id("accordion__heading-2");
    private final By questionOrderToday = By.id("accordion__heading-3");
    private final By questionExtendReturn = By.id("accordion__heading-4");
    private final By questionCharger = By.id("accordion__heading-5");
    private final By questionCancel = By.id("accordion__heading-6");
    private final By questionOutskirts = By.id("accordion__heading-7");

    private final By answerCost = By.id("accordion__panel-0");
    private final By answerMultipleScooters = By.id("accordion__panel-1");
    private final By answerRentalTime = By.id("accordion__panel-2");
    private final By answerOrderToday = By.id("accordion__panel-3");
    private final By answerExtendReturn = By.id("accordion__panel-4");
    private final By answerCharger = By.id("accordion__panel-5");
    private final By answerCancel = By.id("accordion__panel-6");
    private final By answerOutskirts = By.id("accordion__panel-7");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void acceptCookies() {
        if (!driver.findElements(cookieAcceptButton).isEmpty()) {
            driver.findElement(cookieAcceptButton).click();
        }
    }

    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    public void clickPageOrderButton() {
        WebElement bigButton = driver.findElement(pageOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bigButton);
        bigButton.click();
    }

    public void scrollToFAQ() {
        WebElement accordion = driver.findElement(faqAccordion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordion);
    }

    public void clickFAQ(String questionId) {
        driver.findElement(By.id(questionId)).click();
    }

    public String getFAQAnswerText(String answerId) {
        return driver.findElement(By.id(answerId)).getText().trim();
    }
}
