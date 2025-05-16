import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Leo\\Desktop\\Света\\Разное\\Курсы\\Автоматизация моб.приложений\\IdeaProjects\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        String spec = "http://127.0.0.1:4723/wd/hub";
        driver = new AndroidDriver(new URL(spec), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test // Первый тест из обучающего урока
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Не найден элемент 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Не найден элемент 'Search…'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Не найден 'Object-oriented programming language' среди тем 'Java'",
                15
        );

        System.out.println("Первый тест из урока завершён!");
    }

    @Test // Второй тест из обучающего урока
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найден элемент 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Не найден элемент 'Search…'",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Не найдено поле поиска",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Не найдена кнопка 'X' для отмены поиска",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Кнопка 'X' всё еще присутствует на странице",
                5
        );
        System.out.println("Второй тест из урока завершён!");
    }

    @Test // Третий тест из обучающего урока
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Не найден элемент 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Не найден элемент 'Search…'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Не найден 'Object-oriented programming language' среди тем 'Java'",
                5
        );
        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Не найдена статья",
                15
        );
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Неожиданный заголовок!",
                "Java (programming language)",
                article_title
        );
        System.out.println("Третий тест из урока завершён!");
    }
    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода)
    public void testElementHasText() {
        String hasText = "Search Wikipedia";

        boolean res = assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                hasText,
                "Искомый текст '" + hasText + "' не найден!"
        );
        Assert.assertEquals(
                "Тест провален!",
                true,
                res
        );
        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода) завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex3: Тест: отмена поиска)
    public void testCancelSearchWord() {
        String search_word = "Sport";

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Не найден элемент 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_word,
                "Не найден элемент 'Search…'",
                5
        );

        List<WebElement> elements_search = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        if( elements_search.size() > 1 )
            System.out.println("Найдено несколько статей со словом '" + search_word + "'");

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Не найдено поле поиска",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Не найдена кнопка 'X' для отмены поиска",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Кнопка 'X' всё еще присутствует на странице",
                5
        );
        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ Ex3: Тест: отмена поиска завершён!");
    }

    private boolean assertElementHasText(By by, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(by,"Не найден элемент!'", 5 );

        if( !element.getAttribute("text").contains(expected_text) ) {
            System.out.println(error_message);
            return false;
        }
        return true;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}



