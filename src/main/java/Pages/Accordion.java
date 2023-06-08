/*
Выпадающий список в разделе «Вопросы о важном».
Локаторы и методы
 */

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Accordion {

    private final WebDriver driver;

    //локатор для аккардиона в блоке "Вопросы о важном"
    private final By accordion =  By.className("Home_FAQ__3uVm4");

    //конструктор класса
    public Accordion(WebDriver driver){
        this.driver = driver;
    }

    //часть локатора для заголовка аккариона
    private String xpathHeading = "//div[@id='accordion__heading-%s']";

    //часть локатора для текста аккариона
    private String xpathPanel = "//div[@id='accordion__panel-%s']/p";

    //метод кликает по заголовку элемента аккардиона
    public void clickHeading(int num) {
        driver.findElement(By.xpath(String.format(xpathHeading, num))).click();
    }

    //метод ожидания загрузки и скролл до аккордиона
    public void scrollToAccordion() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(accordion));

        WebElement element = driver.findElement(accordion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //метод для получения текста элемента полного текста
    public String textInPanel(int num){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpathPanel, num))));

        return driver.findElement(By.xpath(String.format(xpathPanel, num))).getText();
    }
}