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
        int xpathCount = 0;
        System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        if ((load(driver, "https://yandex.ru/")) == 0)
            System.out.println("Load OK");
        if ((tup_button_by_text(driver, "Маркет")) == 0)
            System.out.println("Element find - OK");
        close_tab(driver);

        Thread.sleep(10000);
        driver.navigate().refresh();

        if ((tup_button_by_xpath(driver, ".//*[text()='Компьютеры']/..")) == 0)
            System.out.println("OK");
        if ((tup_button_by_text(driver, "Ноутбуки")) == 0)
            System.out.println("OK");
        Thread.sleep(5000);
     // driver.manage().timeouts().pageLoadTimeout(30000, TimeUnit.MILLISECONDS);

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        enter_text_to_elem_by_id(driver, "glpricefrom", "10000");
        Thread.sleep(5000);
        enter_text_to_elem_by_id(driver, "glpriceto", "30000");
        Thread.sleep(5000);

        set_checkbox_elem(driver, "div._3_phr-spJh:nth-child(3) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > ul:nth-child(2) > li:nth-child(6) > div:nth-child(1) > a:nth-child(1) > label:nth-child(1) > div:nth-child(2) > span:nth-child(1)");
        Thread.sleep(5000);
        set_checkbox_elem(driver, "div._3_phr-spJh:nth-child(3) > div:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > ul:nth-child(2) > li:nth-child(7) > div:nth-child(1) > a:nth-child(1) > label:nth-child(1) > div:nth-child(2) > span:nth-child(1)");
        Thread.sleep(5000);
       // driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);

        tup_button_by_class(driver, "vLDMfabyVq");
        Thread.sleep(4000);
        set_checkbox_elem(driver, "button._35PaznpQ-g:nth-child(1)");
        Thread.sleep(5000);
        xpathCount = driver.findElements(By.cssSelector("article._1_IxNTwqll")).size();
        if (xpathCount != 12) // проверка на 12 элементов на странице
        {
            tup_button_by_class(driver, "vLDMfabyVq");
            Thread.sleep(4000);
            set_checkbox_elem(driver, "button._35PaznpQ-g:nth-child(1)");
            Thread.sleep(5000);
            /*try {

            } catch () */ // нужно будет обернуть
            set_checkbox_elem(driver, "button._35PaznpQ-g:nth-child(1)");
            Thread.sleep(5000);
        }
    }


    public static void fall_lists(WebDriver driver, String path)
    {
        WebElement selectElement = driver.findElement(By.className(path));
       new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id(path)));
        driver.findElement(By.id(path)).click();
        //Select select = new Select(selectElement);
        //select.selectByIndex(1);
    }

    public static void set_checkbox_elem(WebDriver driver, String path)  {
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
