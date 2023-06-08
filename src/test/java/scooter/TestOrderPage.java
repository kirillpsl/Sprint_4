/*
Заказ самоката. Весь флоу позитивного сценария.
Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.

Из чего состоит позитивный сценарий:
    Нажать кнопку «Заказать». На странице две кнопки заказа.
    Заполнить форму заказа.
    Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
 */
package scooter;
import Pages.OrderTwoPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Pages.OrderOnePage;

@RunWith(Parameterized.class)
public class TestOrderPage {
    WebDriver driver;

    private final int num; //нужен для выбора кнопки "Заказать"
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rent;
    private final String colour;
    private final String comment;

    public TestOrderPage(int num, String name, String surname, String address, String metro, String phone, String date, String rent, String colour, String comment) {
        this.num = num;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rent = rent;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getFormData() {
        return new Object[][] {
                {1,"Имя",    "Фамилия",    "Адрес", "Сокольники",   "89991112233",  "12.12.2023", "двое суток", "чёрный жемчуг"      ,"Привези по браЦки"},
                {2,"ИмяДва", "ФамилияДва", "Адрес", "Черкизовская", "+73472336633", "10/10/23",   "трое суток", "серая безысходность", " "},
        };
    }

    @Before
    public void startTest(){
        // драйвер для браузера Chrome
       driver = new ChromeDriver();

        // драйвер для браузера FireFox
        // WebDriverManager.firefoxdriver().setup();
        // driver = new FirefoxDriver();
        //Открыли сайт
       driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testTextAccordion(){
        //создание объекта класса OrderOnePage
        OrderOnePage objOrderOnePage = new OrderOnePage(driver);

        //ждем загрузки главной страницы
        objOrderOnePage.waitForLoadMainPage();

        if(num/2 == 0){ //если номер строки четный то жмем на нижнюю кнопку
            objOrderOnePage.clickButtonDown();
        }
        else { //если номер строки НЕ четный то жмем на верхнюю кнопку
            objOrderOnePage.clickButtonUp();
        }

        //ждем загрузки и заполняем форму №1
        objOrderOnePage.fillOrderOne(name, surname, address, metro, phone);

        //создание объекта класса OrderTwoPage
        OrderTwoPage objOrderTwoPage = new OrderTwoPage(driver);

        //ждем загрузки и заполняем форму №2
        objOrderTwoPage.fillOrderTwo(date, rent, colour, comment);

        //жмем кнопку "Да", для подтверждения заказа
        objOrderTwoPage.clickButtonYes();

        //переменная в которой хранится полный финишный текст
        String finishText = objOrderTwoPage.getFinishText();

        //сравниваем текст с обрезанным до 14 символов finishText
        Assert.assertEquals("Заказ не оформлен!", "Заказ оформлен", finishText.substring(0,14));
    }
    //закрыли браузер
    @After
    public void tearDown() {
        driver.quit();
    }
}