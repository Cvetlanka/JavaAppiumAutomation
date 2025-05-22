import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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

    @Test // Четвертый тест из обучающего урока
    public void testSwipeArticle() {
        waitForElementAndClick(By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Не найден элемент 'Skip'",
                5);

        waitForElementAndClick(By.xpath("//androidx.cardview.widget.CardView[@resource-id='org.wikipedia:id/search_container']"),
                "Не найден элемент 'Search Wikipedia'",
                5);

        waitForElementAndSendKeys(By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/search_src_text']"),
                "Appium",
                "Не найден элемент 'Search…'",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Не найдена статья 'Appium' в поиске",
                5
        );
         waitForElementPresent(
                By.xpath("//*[@class='android.view.View'][@text='Appium']"),
                "Не найдена статья",
                15
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                        "Не найден конец статьи",
                        20
        );
        System.out.println("Четвертый тест из урока завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода)
    public void testElementHasText_Ex2() {
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
    public void testCancelSearchWord_Ex3() {
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

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex4*: Тест: проверка слов в поиске)
    public void testCheckSearchWordInArticle_Ex4() {
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
        for(WebElement elem: elements_search)
            if (!elem.getText().contains(search_word))
                System.out.println("Заголовок статьи '" + elem.getText() + "' НЕ содержит слово '" + search_word + "'");

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ Ex4*: Тест: проверка слов в поиске завершён!");
    }

    @Test // // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex5: Тест: Сохранение двух статей)
    public void saveArticleToMyListAndDeleteFirstArticle_Ex5() {

        String search_word = "Sport";
        String name_list = "My List of Sport";
        String first_article  = "Sporting CP";
        String second_article = "Sport of athletics";

        waitForElementAndClick(By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Не найден элемент 'Skip'",
                5
        );
        waitForElementAndClick(By.xpath("//androidx.cardview.widget.CardView[@resource-id='org.wikipedia:id/search_container']"),
                "Не найден элемент 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/search_src_text']"),
                search_word,
                "Не найден элемент 'Search…'",
                5
        );

        createListAndSaveArticleInIt(first_article, name_list);
        addToExistListAnyArticle(second_article, name_list);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не найдена кнопка возврата 'Navigate up'",
                7
        );
        deleteArticleFromListAndCheckAnyArticle(second_article, name_list, first_article);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ Ex5: Тест: Сохранение двух статей завершён!");
    }

    private void createListAndSaveArticleInIt(String first_article, String name_list) {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='"+ first_article +"']"),
                "Не найдена статья '" + first_article + "' в поиске",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Не найдена кнопка 'Save' для сохранения в список",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Не найден элемент 'Add to list'",
                5
        );
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_list,
                "Не найден элемент 'Name of this list'",
                5
        );
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Не найдена кнопка 'OK'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Не найдена кнопка возврата 'Navigate up'",
                5
        );
    }

     private void addToExistListAnyArticle(String any_article, String name_list){
         waitForElementAndClick(
                 By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='"+ any_article +"']"),
                 "Не найдена статья '" + any_article + "' в поиске",
                 5
         );
         waitForElementAndClick(
                 By.id("org.wikipedia:id/page_save"),
                 "Не найдена кнопка 'Save' для сохранения в список",
                 5
         );
         waitForElementAndClick(
                 By.id("org.wikipedia:id/snackbar_action"),
                 "Не найден элемент 'Add to list'",
                 5
         );
         waitForElementAndClick(
                 By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='"+ name_list +"']"),
                 "Не найден список '" + name_list + "'",
                 5
         );
         waitForElementAndClick(
                 By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                 "Не найдена кнопка возврата 'Navigate up'",
                 5
         );
     }

     private void deleteArticleFromListAndCheckAnyArticle(String article, String name_list, String any_article){
         waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']/android.view.ViewGroup/android.widget.TextView"),
                "Не найдена кнопка сохраненных списков 'Saved'",
                7
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + name_list + "']"),
                "Не найден мой сохраненный список '" + name_list + "'",
                5
        );

        String article_xpath = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='"+ article +"']";
        String any_article_xpath = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='"+ any_article +"']";

        swipeElementToLeft(
                By.xpath(article_xpath),
                "Не найдена сохраненная статья '" + article + "' для свайпа в списке '" + name_list + "'"
        );
        waitForElementPresent(
                By.xpath(any_article_xpath),
                "Не найдена ранее сохраненная статья '" + any_article + "' в списке '" + name_list + "'",
                5
        );
        waitForElementAndClick(
               By.xpath(any_article_xpath),
               "Не найдена статья '" + any_article + "' в списке '" + name_list + "'",
               5
        );

        String title_path = "//*[@resource-id='pcs']//*//*[@class='android.view.View']";
        assertElementHasText(By.xpath(title_path),
                any_article,
                "Заголовок статьи не совпадает!"
        );
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

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height * 0.5);
        int end_y = (int)(size.height * 0.1);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by,String error_message, int max_swipes){
        int already_swiped = 0;
        while( driver.findElements(by).size() == 0 ){

            if( already_swiped > max_swipes ){
                waitForElementPresent(
                        by, "Не найден элемент при свайпе. \n" + error_message,
                        0
                );
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(By by, String error_message) {

        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }
}



