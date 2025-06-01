package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject{
    private static final String
            SEARCH_INIT_ELEMENT_SKIP = "org.wikipedia:id/fragment_onboarding_skip_button",  // Skip
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",  // Search Wikipedia
            SEARCH_INPUT = "//android.widget.EditText[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_RESULT_ELEMENT = "org.wikipedia:id/page_list_item_title",// Search
            SEARCH_EMPTY_RESULT_ELEMENT = "org.wikipedia:id/results_text",
            SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            SEARCH_TITLE = "page_list_item_title",
            SEARCH_DESCRIPTION = "page_list_item_description",
            SEARCH_DESCRIPTION_AND_TITLE_BY_SUBSTRING_TPL ="org.wikipedia:id/{SUBSTRING}";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getTitleAndDescriptionElement(String substring){
        return SEARCH_DESCRIPTION_AND_TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {

        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT_SKIP), "Не найдена кнопка 'Skip'");
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT_SKIP), "Не найдена кнопка 'Skip' для клика", 5);
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Не найден начальный поисковый элемент");
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Не найден начальный элемент поиска для клика", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Не найден элемент строки поиска для ввода поискового запроса");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Не найдена кнопка отмены поиска!", 15);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Кнопка отмены поиска всё еще присутствует!", 15);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Не найдена кнопка отмены поиска для клика", 5);
    }

    public void typeSearchLine(String search_line ){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,"Не найден элемент поиска для ввода поискового запроса", 15);
    }

    public void clearSearchLine() {
        this.waitForElementAndClear(By.xpath(SEARCH_INPUT), "Не найдено поле поиска", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Не найден результат поиска: '" + substring + "'");
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Не найден результат поиска для клика с подстрокой!" + substring, 10);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(By.id(SEARCH_RESULT_ELEMENT), "Не найдены статьи в поисковом запросе ", 15);

        return this.getAmountOfElements(By.id(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(By.id(SEARCH_EMPTY_RESULT_ELEMENT),"Не найден элемент 'No results'", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(By.id(SEARCH_RESULT_ELEMENT), "Не найдено результатов поиска");
    }

    public String getTextSearchInput(){
        WebElement title_element = this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Не найден элемент строки поиска для ввода поискового запроса");;
        return title_element.getAttribute("text");
    }

    public boolean assertHasTextSearchTitle(String text){
        List<WebElement> elements_search = driver.findElements(By.id(SEARCH_RESULT_ELEMENT));
        for(WebElement elem: elements_search)
            if (elem.getText().contains(text))
               return true;
        return false;

    }

    public int  waitForElementByTitleAndDescription(String s_title, String s_description){
        String s_search_title = getTitleAndDescriptionElement(SEARCH_TITLE);
        String s_search_description = getTitleAndDescriptionElement(SEARCH_DESCRIPTION);

        return this.getAmountPairElementsWithText(s_search_title, s_search_description, s_title, s_description);
    }
}
