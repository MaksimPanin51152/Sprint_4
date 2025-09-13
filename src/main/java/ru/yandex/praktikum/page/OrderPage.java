package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver driver;

    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-menu']/div[1]");
    private final By scooterColorBlack = By.id("black");
    private final By scooterColorGrey = By.id("grey");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final By confirmYesButton = By.xpath("//button[text()='Да']");
    private final By successModalTitle = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void enterAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void selectMetroStation(String station) {
        WebElement metroInput = driver.findElement(metroStationInput);
        metroInput.sendKeys(station);
        metroInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void enterDate(String date) {
        WebElement dateField = driver.findElement(dateInput);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);
    }

    public void selectRentalPeriod() {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(rentalPeriodOption).click();
    }

    public void chooseScooterColorBlack() {
        driver.findElement(scooterColorBlack).click();
    }

    public void chooseScooterColorGrey() {
        driver.findElement(scooterColorGrey).click();
    }

    public void enterComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOrderButton() {
        WebElement orderBtn = driver.findElement(orderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderBtn);
        orderBtn.click();
    }

    public void confirmOrder() {
        driver.findElement(confirmYesButton).click();
    }

    public boolean isOrderConfirmed() {
        return driver.findElement(successModalTitle).isDisplayed();
    }
}
