import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class webwin {
    public static void main(String[] args) throws InterruptedException {
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
