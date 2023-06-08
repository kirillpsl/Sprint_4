/*
Заказ самоката. Форма 1
Локаторы и методы
 */
package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderOnePage {
    private final WebDriver driver;

    //конструктор класса
    public OrderOnePage(WebDriver driver) {
        this.driver = driver;
    }

    //локатор главной страницы
    private final By mainPage = By.className("Home_FAQ__3uVm4");

    //локатор верхней кнопки "Заказать"
    private final By buttonUp = By.className("Button_Button__ra12g");

    //локатор нижней кнопки "Заказать"
    private final By buttonDown = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/button");

    //локатор элемента поле для ввода - "Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");

    //локатор элемента поле для ввода - "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    //локатор элемента поле для ввода - "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //локатор элемента поле для ввода - "Станция метро"
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    //  private final By metroField = (By.className("select-search__option"));

    //локатор элемента поле для ввода - "Телефон"
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //локатор нижней кнопки "Далее"
    private final By buttonNext = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button");

    //метод ожидания загрузки главной страницы
    public void waitForLoadMainPage(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage));
    }

    //метод клика по верхней кнопке "Заказать"
    public void clickButtonUp() {
        driver.findElement(buttonUp).click();
    }

    //метод скролит до нижней кнопки "Заказать" и кликает по ней
    public void clickButtonDown() {
        WebElement element = driver.findElement(buttonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(buttonDown).click();
    }

    //метод заполняет поля «Имя»
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //метод заполняет поля «Фамилия»
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    //метод заполняет поля «Адрес»
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //метод заполняет поля «Метро»
    public void setMetro(String metro) {
        //вводим название станции в поле
        driver.findElement(metroField).sendKeys(metro);
        //нажимаем кнопку вниз
        driver.findElement(metroField).sendKeys(Keys.ARROW_DOWN);
        //нажимаем кнопку ентер
        driver.findElement(metroField).sendKeys(Keys.ENTER);
    }

    //метод заполняет поля «Телефон»
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //метод клика по кнопки "Далее" на форме заказа
    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    //метод ожидания загрузки страницы для заказа
    public void waitForLoadOneOrder(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    //метод ждет загрузку формы и заполнения ее, объединяет ввод всех полей на форме №1
    public void fillOrderOne(String name, String surname, String address, String metro, String phone){
        waitForLoadOneOrder();
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
        clickButtonNext();
    }
}