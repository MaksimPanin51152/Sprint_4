package ru.yandex.praktikum.page;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;

public class HomePage {
    private WebDriver driver;

    // Локаторы вопросов
    private By question1 = By.xpath(".//div[@class='accordion__item'][1]");
    private By question2 = By.xpath(".//div[@class='accordion__item'][2]");
    private By question3 = By.xpath(".//div[@class='accordion__item'][3]");
    private By question4 = By.xpath(".//div[@class='accordion__item'][4]");
    private By question5 = By.xpath(".//div[@class='accordion__item'][5]");
    private By question6 = By.xpath(".//div[@class='accordion__item'][6]");
    private By question7 = By.xpath(".//div[@class='accordion__item'][7]");
    private By question8 = By.xpath(".//div[@class='accordion__item'][8]");

    // Локаторы ответов
    private By answer1 = By.id("accordion__panel-0");
    private By answer2 = By.id("accordion__panel-1");
    private By answer3 = By.id("accordion__panel-2");
    private By answer4 = By.id("accordion__panel-3");
    private By answer5 = By.id("accordion__panel-4");
    private By answer6 = By.id("accordion__panel-5");
    private By answer7 = By.id("accordion__panel-6");
    private By answer8 = By.id("accordion__panel-7");

    // Кнопки "Заказать"
    private By headerOrderButton = By.xpath("(//button[text()='Заказать'])[1]");
    private By pageOrderButton = By.xpath("(//button[text()='Заказать'])[2]");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // Методы для кликов по вопросам
    public void clickQuestion1() { driver.findElement(question1).click(); }
    public void clickQuestion2() { driver.findElement(question2).click(); }
    public void clickQuestion3() { driver.findElement(question3).click(); }
    public void clickQuestion4() { driver.findElement(question4).click(); }
    public void clickQuestion5() { driver.findElement(question5).click(); }
    public void clickQuestion6() { driver.findElement(question6).click(); }
    public void clickQuestion7() { driver.findElement(question7).click(); }
    public void clickQuestion8() { driver.findElement(question8).click(); }

    // Проверка текста ответа
    public void isCorrectText(String answer, String text) {
        MatcherAssert.assertThat(answer, is(text));
    }

    // Геттеры для текста ответов
    public String getAnswer1() { return driver.findElement(answer1).getText(); }
    public String getAnswer2() { return driver.findElement(answer2).getText(); }
    public String getAnswer3() { return driver.findElement(answer3).getText(); }
    public String getAnswer4() { return driver.findElement(answer4).getText(); }
    public String getAnswer5() { return driver.findElement(answer5).getText(); }
    public String getAnswer6() { return driver.findElement(answer6).getText(); }
    public String getAnswer7() { return driver.findElement(answer7).getText(); }
    public String getAnswer8() { return driver.findElement(answer8).getText(); }

    // Клики по кнопкам Заказать
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }
    public void clickPageOrderButton() {
        WebElement bigButton = driver.findElement(pageOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", bigButton);
        bigButton.click();
    }
}
