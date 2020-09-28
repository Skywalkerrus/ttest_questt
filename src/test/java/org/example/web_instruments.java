package org.example;

import org.openqa.selenium.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class web_instruments {

    public static String get_path_to_driver()
    {
        String path;
        File driver;
        Scanner sc = new Scanner(System.in);

        System.out.println("Пожалуйста, введите путь до вашего веб-драйвера.\nПример: C:\\webdrivers\\geckodriver.exe");
        while (true) {
            path = sc.nextLine();
            if (path.equals("exit"))
                System.exit(0);
            driver = new File(path);
            if (driver.exists())
                break;
            else
                System.out.println("Такого файла не существует, пожалуйста, введите путь правильно. " +
                        "\nЕсли вы хотите выйти из программы: введите 'exit'");
        }
        return (path);
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

    public static void load(WebDriver driver, String url)
    {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void tup_button_by_text(WebDriver driver, String l_txt)
    {
        WebElement searchField = driver.findElement(By.linkText(l_txt));
        searchField.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void tup_button_by_xpath(WebDriver driver, String l_txt)
    {
        WebElement searchField = driver.findElement(By.xpath(l_txt));
        searchField.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void tup_button_by_class(WebDriver driver, String cl_name)
    {
        WebElement searchField = driver.findElement(By.className(cl_name));
        searchField.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
