package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "xpath://*[@resource-id='pcs']//*[@class='android.view.View'][@index='0']//*[@class='android.view.View'][@index='0']", //  "//*[@resource-id='pcs']//*[@class='android.view.View']//*[@instance='2']",
            FOOTER_ELEMENT = "xpath://*[@text='View article in browser']",
            BTN_SAVE_FOR_LIST = "id:org.wikipedia:id/page_save",
            BTN_ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action",
            TEXT_INPUT_TO_LIST = "id:org.wikipedia:id/text_input",
            BTN_OK = "id:android:id/button1",
            SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            ITEM_LIST_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']";

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
        return this.waitForElementPresent(TITLE, "Не найдена статья", 50);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Не найден конец статьи",20);
    }

    public void closeArticle(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Не найдена кнопка возврата 'Navigate up'",5);
    }

    public void createListAndSaveArticleInIt(String name_list) {

        this.waitForElementAndClick(BTN_SAVE_FOR_LIST,"Не найдена кнопка 'Save' для сохранения в список",5);
        this.waitForElementAndClick(BTN_ADD_TO_LIST,"Не найдена кнопка 'Add to list'",5);

        this.waitForElementAndSendKeys(TEXT_INPUT_TO_LIST, name_list,"Не найден элемент 'Name of this list'",5);
        this.waitForElementAndClick(BTN_OK,"Не найдена кнопка 'OK'",5);
    }

    public void addToExistListAnyArticle(String name_list){

        this.waitForElementAndClick(BTN_SAVE_FOR_LIST,"Не найдена кнопка 'Save' для сохранения в список",5);
        this.waitForElementAndClick(BTN_ADD_TO_LIST,"найдена кнопка 'Add to list'",5);

        this.waitForElementAndClick(getItemTitleElementList(name_list),"Не найден список '" + name_list + "'",5);
    }

    public void assertTitleArticleText(String text){
        this.assertElementHasText(TITLE, text,"Заголовок статьи не содержит текст '" + text + "'");

    }
}