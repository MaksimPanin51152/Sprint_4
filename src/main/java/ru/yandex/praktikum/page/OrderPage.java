package ru.yandex.praktikum.page;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;

public class OrderPage {
    private WebDriver driver;

    private By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private By aboutOrderHeader = By.xpath(".//div[text()='Про аренду']");
    private By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    private By orderNextButton = By.xpath(".//button[text()='Далее']");
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");
    private By confirmHeader = By.xpath(".//button[text()='Посмотреть статус']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }

    public String getConfirmHeader() {
        return driver.findElement(confirmHeader).getText();
    }

    public void isPageOpen(String headerText, String text) {
        MatcherAssert.assertThat(headerText, is(text));
    }

    public void acceptCookieButtonClick() { driver.findElement(acceptCookieButton).click(); }

    public void setName(String name) { driver.findElement(nameField).sendKeys(name); }
    public void setSurname(String surname) { driver.findElement(surnameField).sendKeys(surname); }
    public void setAddress(String address) { driver.findElement(addressField).sendKeys(address); }
    public void setSubway(String subway) {
        driver.findElement(subwayField).click();
        driver.findElement(By.xpath(".//div[text()='" + subway + "']")).click();
    }
    public void setPhoneNumber(String phoneNumber) { driver.findElement(phoneNumberField).sendKeys(phoneNumber); }

    public void clickOrderNextButton() { driver.findElement(orderNextButton).click(); }

    public void setDate(String date) { driver.findElement(dateField).sendKeys(date); }
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(aboutOrderHeader).click();
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }
    public void setColor(String color) {
        driver.findElement(By.xpath(".//label[text()='" + color + "']")).click();
    }
    public void setComment(String comment) { driver.findElement(commentField).sendKeys(comment); }

    public void clickOrderCreateButton() { driver.findElement(orderCreateButton).click(); }
    public void clickOrderConfirmButton() { driver.findElement(orderConfirmButton).click(); }
}
