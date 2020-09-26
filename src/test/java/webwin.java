import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class webwin {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String first_result;
        String second_result;

        if ((load(driver, "https://yandex.ru/")) == 0) //грузим страницу яндекса
            System.out.println("Load OK");
        if ((tup_button_by_text(driver, "Маркет")) == 0) // переход на маркет
            System.out.println("Element find - OK");
        close_tab(driver); // закрытие лишней ввкладки

        Thread.sleep(10000);
        driver.navigate().refresh(); // обновляем стр для пропуска вопроса о местоположении

        if ((tup_button_by_xpath(driver, ".//*[text()='Компьютеры']/..")) == 0) // переход на раздел компьютеров
            System.out.println("OK");
        if ((tup_button_by_text(driver, "Ноутбуки")) == 0) // переход на раздел ноутбуков
            System.out.println("OK");
        Thread.sleep(5000);

        enter_text_to_elem_by_id(driver, "glpricefrom", "10000"); // ищем поле для фильтра цены, указываем интересующую нас цену
        Thread.sleep(5000);
        enter_text_to_elem_by_id(driver, "glpriceto", "30000"); // ищем поле для фильтра цены, указываем интересующую нас цену
        Thread.sleep(5000);

        tup_by_css(driver, "div._3_phr-spJh:nth-child(3) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > ul:nth-child(2) > li:nth-child(6) > div:nth-child(1) > a:nth-child(1) > label:nth-child(1) > div:nth-child(2) > span:nth-child(1)");
        // отмечаем интересующих нас производителей
        Thread.sleep(5000);
        tup_by_css(driver, "div._3_phr-spJh:nth-child(3) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > ul:nth-child(2) > li:nth-child(7) > div:nth-child(1) > a:nth-child(1) > label:nth-child(1) > div:nth-child(2) > span:nth-child(1)");
        Thread.sleep(5000);


        tup_button_by_class(driver, "vLDMfabyVq");
        Thread.sleep(4000);
        tup_by_css(driver, "button._35PaznpQ-g:nth-child(1)");
        // ставим отображение максимум 12 элементов на странице
        Thread.sleep(5000);

        if ((driver.findElements(By.cssSelector("article._1_IxNTwqll")).size()) != 12) // проверка на 12 элементов на странице
        {
            //проверяем, действительно ли отобразилось 12 элементов. Если нет, то пробуем опять выставить отображение в 12 элементов.
            tup_button_by_class(driver, "vLDMfabyVq");
            Thread.sleep(4000);
            tup_by_css(driver, "button._35PaznpQ-g:nth-child(1)");
            Thread.sleep(5000);
            /*try {

            } catch () */ // нужно будет обернуть
        }
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
