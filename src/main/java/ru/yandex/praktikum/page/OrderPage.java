package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы для первой страницы формы заказа
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.className("select-search__input");
    private final String metroOptionPattern = "//div[text()='%s']";
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы для второй страницы формы заказа
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private final String rentalPeriodOptionPattern = "//div[@class='Dropdown-option' and text()='%s']";
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderSubmitButton = By.xpath("//button[text()='Заказать']");

    // Модальное окно подтверждения
    private final By confirmYesButton = By.xpath("//button[text()='Да']");
    private final By modalHeader = By.className("Order_ModalHeader__3FDaJ");
    private final By modalText = By.className("Order_Modal__YZ-d3");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    // Методы для первой страницы
    public void fillName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).sendKeys(name);
    }

    public void fillSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void fillAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void selectMetro(String metro) {
        driver.findElement(metroField).click();
        By option = By.xpath(String.format(metroOptionPattern, metro));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    // Методы для второй страницы
    public void setDate(String date) {
        WebElement dateInput = driver.findElement(dateField);
        dateInput.sendKeys(date);
        dateInput.submit();
    }

    public void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        By option = By.xpath(String.format(rentalPeriodOptionPattern, period));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void chooseScooterColor(String color) {
        if ("black".equalsIgnoreCase(color)) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("grey".equalsIgnoreCase(color)) {
            driver.findElement(greyColorCheckbox).click();
        }
    }

    public void fillComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void submitOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderSubmitButton)).click();
    }

    // Подтверждение заказа в модалке
    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton)).click();
    }

    // Методы проверки модального окна
    public String getOrderModalHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader)).getText();
    }

    public String getOrderModalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalText)).getText();
    }

    // Проверка, что форма заказа открылась
    public boolean isOrderPageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).isDisplayed();
    }
}


