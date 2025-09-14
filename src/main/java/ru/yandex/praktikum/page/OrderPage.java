package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== Локаторы формы заказа =====
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.className("select-search__input");
    private final String metroOptionPattern = "//div[@class='select-search__select']//div[text()='%s']";
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-arrow");
    private final String rentalPeriodOptionPattern = "//div[@class='Dropdown-menu']/div[text()='%s']";
    private final String scooterColorPattern = "//input[@id='%s']";
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(text(),'Заказать')]");
    private final By confirmYesButton = By.xpath("//button[text()='Да']");
    private final By orderModalHeader = By.xpath("//div[contains(@class,'Order_ModalHeader')]");
    private final By orderModalText = By.xpath("//div[contains(@class,'Order_ModalText')]");

    // ===== Заголовок страницы заказа =====
    private final By orderPageTitle = By.xpath("//*[text()='Для кого самокат']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    // ===== Методы формы заказа =====
    public void fillName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void fillSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void fillAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void selectMetro(String metroStation) {
        driver.findElement(metroField).click();
        By metroOption = By.xpath(String.format(metroOptionPattern, metroStation));
        wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    public void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        By option = By.xpath(String.format(rentalPeriodOptionPattern, period));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void chooseScooterColor(String colorId) {
        By colorLocator = By.xpath(String.format(scooterColorPattern, colorId));
        driver.findElement(colorLocator).click();
    }

    public void fillComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void submitOrder() {
        driver.findElement(orderButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton)).click();
    }

    public String getOrderModalHeader() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader));
        return header.getText().trim();
    }

    public String getOrderModalText() {
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalText));
        return text.getText().trim();
    }

    // ===== Метод для проверки открытия страницы заказа =====
    public boolean isOrderPageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderPageTitle)).isDisplayed();
    }
}


