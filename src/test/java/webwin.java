import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class webwin {
    public static void main(String[] args) throws InterruptedException
    {
        String path;

        path = get_path_to_driver();
        System.setProperty("webdriver.gecko.driver", path);
        WebDriver driver = new FirefoxDriver();
        Logic(driver);
    }

    public static String get_path_to_driver()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Пожалуйста, введите путь до вашего веб-драйвера.\nПример: C:\\webdrivers\\geckodriver.exe");
        return (sc.nextLine());
    }

    public static void Logic(WebDriver driver) throws InterruptedException {
        String first_result;
        String second_result;

        load(driver, "https://yandex.ru/"); //грузим страницу яндекса
        Thread.sleep(5000);
        jump_to_tab(driver);
        set_price(driver);
        set_checkbox(driver);
        set_twel_elements(driver);
        check_first_second_rez(driver);

    }
    public static void jump_to_tab(WebDriver driver) throws InterruptedException {
        tup_button_by_text(driver, "Маркет"); // переход на маркет
        close_tab(driver); // закрытие лишней ввкладки

        Thread.sleep(10000);
        driver.navigate().refresh(); // обновляем стр для пропуска вопроса о местоположении

        Thread.sleep(5000);
        tup_button_by_xpath(driver, ".//*[text()='Компьютеры']/.."); // переход на раздел компьютеров

        tup_button_by_text(driver, "Ноутбуки"); // переход на раздел ноутбуков
        Thread.sleep(5000);
    }
    public static void set_price(WebDriver driver) throws InterruptedException {
        enter_text_to_elem_by_id(driver, "glpricefrom", "10000"); // ищем поле для фильтра цены, указываем интересующую нас цену
        Thread.sleep(5000);

        enter_text_to_elem_by_id(driver, "glpriceto", "30000"); // ищем поле для фильтра цены, указываем интересующую нас цену
        Thread.sleep(5000);
    }

    public static void set_checkbox(WebDriver driver) throws InterruptedException {
        tup_by_css(driver, "div._3_phr-spJh:nth-child(3) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > ul:nth-child(2) > li:nth-child(6) > div:nth-child(1) > a:nth-child(1) > label:nth-child(1) > div:nth-child(2) > span:nth-child(1)");
        // отмечаем интересующих нас производителей
        Thread.sleep(5000);

        tup_by_css(driver, "div._3_phr-spJh:nth-child(3) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > ul:nth-child(2) > li:nth-child(7) > div:nth-child(1) > a:nth-child(1) > label:nth-child(1) > div:nth-child(2) > span:nth-child(1)");
        Thread.sleep(5000);
    }

    public static void set_twel_elements(WebDriver driver) throws InterruptedException {
        check_twen_elements(driver);
        if ((driver.findElements(By.cssSelector("article._1_IxNTwqll")).size()) != 12) // если список состоит не из 12 эл., то мы повторяем пред. действие
            check_twen_elements(driver);
    }

    //сравнение элементов из списка до поиска и после
    public static void check_first_second_rez(WebDriver driver) throws InterruptedException {
        String first_result,second_result;

        first_result = get_value(driver, "article._1_IxNTwqll:nth-child(1) > div:nth-child(4) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)");
        // сохраняем в переменную значение первого значения в списке
        enter_text_to_elem_by_id(driver, "header-search", first_result);
        // находим на странице строку поиска и вставляем в нее сохраненное ранее значение
        Thread.sleep(500);
        tup_by_css(driver, "._1XiEJDPVpk"); // жмем кнопку поиска
        Thread.sleep(5000);
        second_result = get_value(driver, "article._1_IxNTwqll:nth-child(1) > div:nth-child(4) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)");
        // сохраняем первое значение в списке
        if (second_result.equals(first_result)) // сравниваем старое значение с новым
            System.out.println("OK: " + first_result + " = " + second_result);
        else
            System.out.println("KO");
    }

    public static void check_twen_elements(WebDriver driver) throws InterruptedException {
        //проверка на отображение 12 элементов на стр
        tup_button_by_class(driver, "vLDMfabyVq");
        Thread.sleep(4000);

        tup_by_css(driver, "button._35PaznpQ-g:nth-child(1)");// ставим отображение максимум 12 элементов на странице
        Thread.sleep(5000);
    }

    public static String get_value(WebDriver driver, String path)
    {
        WebElement TxtBoxContent = driver.findElement(By.cssSelector(path));
        return (TxtBoxContent.getAttribute("title"));
    }

    public static void tup_by_css(WebDriver driver, String path)  {
        WebElement checkbox = driver.findElement(By.cssSelector(path));
        checkbox.click();
    }

    public static void enter_text_to_elem_by_id(WebDriver driver, String id, String text)
    {
        WebElement searchField = driver.findElement(By.id(id));

        searchField.sendKeys(text);
    }

    public static void close_tab(WebDriver driver)
    {
        Set<String> handlesSet = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<String>(handlesSet);
        driver.switchTo().window(handlesList.get(0));
        driver.close();
        driver.switchTo().window(handlesList.get(1));
    }

    public static int load(WebDriver driver, String url)
    {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return (0);
    }

    public static int tup_button_by_text(WebDriver driver, String l_txt)
    {
        WebElement searchField = driver.findElement(By.linkText(l_txt));
        searchField.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return (0);
    }

    public static int tup_button_by_xpath(WebDriver driver, String l_txt)
    {
        WebElement searchField = driver.findElement(By.xpath(l_txt));
        searchField.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return (0);
    }

    public static int tup_button_by_class(WebDriver driver, String cl_name)
    {
        WebElement searchField = driver.findElement(By.className(cl_name));
        searchField.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return (0);
    }
}
