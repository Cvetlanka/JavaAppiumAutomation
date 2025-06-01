package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "//*[@resource-id='pcs']//*[@class='android.view.View'][@index='0']//*[@class='android.view.View'][@index='0']", //  "//*[@resource-id='pcs']//*[@class='android.view.View']//*[@instance='2']",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            BTN_SAVE_FOR_LIST = "org.wikipedia:id/page_save",
            BTN_ADD_TO_LIST = "org.wikipedia:id/snackbar_action",
            TEXT_INPUT_TO_LIST = "org.wikipedia:id/text_input",
            BTN_OK = "android:id/button1",
            SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            ITEM_LIST_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']";

    public ArticlePageObject(AppiumDriver driver){
            super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getItemTitleElementList(String substring){
        return ITEM_LIST_BY_TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.xpath(TITLE), "Не найдена статья", 30);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Не найден конец статьи",20);
    }

    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON),"Не найдена кнопка возврата 'Navigate up'",5);
    }

    public void createListAndSaveArticleInIt(String article_title, String name_list) {

        //this.waitForElementAndClick(By.xpath(getResultSearchElement(article_title)),"Не найдена статья '" + article_title + "' в поиске",5);
        this.waitForElementAndClick(By.id(BTN_SAVE_FOR_LIST),"Не найдена кнопка 'Save' для сохранения в список",5);
        this.waitForElementAndClick(By.id(BTN_ADD_TO_LIST),"Не найдена кнопка 'Add to list'",5);

        this.waitForElementAndSendKeys(By.id(TEXT_INPUT_TO_LIST), name_list,"Не найден элемент 'Name of this list'",5);
        this.waitForElementAndClick(By.id(BTN_OK),"Не найдена кнопка 'OK'",5);
    }

    public void addToExistListAnyArticle(String article_title, String name_list){

        this.waitForElementAndClick(By.id(BTN_SAVE_FOR_LIST),"Не найдена кнопка 'Save' для сохранения в список",5);
        this.waitForElementAndClick(By.id(BTN_ADD_TO_LIST),"найдена кнопка 'Add to list'",5);

        this.waitForElementAndClick(By.xpath(getItemTitleElementList(name_list)),"Не найден список '" + name_list + "'",5);
    }

    public void assertTitleArticleText(String text){
        this.assertElementHasText(By.xpath(TITLE), text,"Заголовок статьи не содержит текст '" + text + "'");

    }
}