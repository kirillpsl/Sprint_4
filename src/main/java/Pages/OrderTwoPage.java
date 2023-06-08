/*
Заказ самоката. Форма 2
Локаторы и методы
 */
package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderTwoPage {
    private final WebDriver driver;

    public OrderTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    //локатор элемента - "* Когда привезти самокат"
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //локатор срок аренды, для нажатия на стрелку
    private final By clickRent = By.xpath(".//div[text()='* Срок аренды']");

    //часть локатора для срока аренды
    private String xpathRent = "//div[@class ='Dropdown-option' and text()='%s']";

    //часть локатора цвета самоката
    private String xpathColourField = "//label[text()='%s']/input";

    //локатор элемента поле для ввода - "Комментарий для курьера"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //локатор для нижний кнопки "Заказать"
    private final By buttonOrder = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");

    //локатор кнопки "Да"
    private final By buttonYes = By.xpath(".//button[text()='Да']");

    //локатор успешного заказа
    private final By finishText = By.xpath(".//div[text()='Заказ оформлен']");

    //метод заполняет дату
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        //нажимаем кнопку ентер
        driver.findElement(dateField).sendKeys(Keys.ENTER);
    }

    //метод выбора периода
    public void setRent(String rent) {
        driver.findElement(clickRent).click();
        driver.findElement(By.xpath(String.format(xpathRent, rent))).click();
    }

    //метод выбора цвета
    public void setColourField(String colour) {
        driver.findElement(By.xpath(String.format(xpathColourField, colour))).click();
    }

    //метод заполняет поля "Комментарий для курьера"
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    //метод клика на кнопку "Заказать"
    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    //метод ожидания загрузки втрой страницы
    public void waitForLoadTwoOrder() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(dateField));
    }

    //метод ждет загрузку формы и заполнения ее, объединяет ввод всех полей на форме №2
    public void fillOrderTwo(String date, String rent, String colour, String comment) {
        waitForLoadTwoOrder();
        setDate(date);
        setRent(rent);
        setColourField(colour);
        setComment(comment);
        clickButtonOrder();
    }

    //метод ждет загрузки кнопки "Да" и затем кликает на нее
    public void clickButtonYes() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(buttonYes));

        driver.findElement(buttonYes).click();
    }

    // метод для получения полного финального текста
    public String getFinishText() {
        return driver.findElement(finishText).getText();
    }
}
