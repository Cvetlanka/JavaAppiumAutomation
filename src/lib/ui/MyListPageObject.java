package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{
    private static final String
            FOLDER_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']",
            ARTICLE_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";

    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String substring){
         return FOLDER_BY_NAME_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getSavedArticleXpathByTitle(String substring){
        return ARTICLE_BY_TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void openFolderByName(String name_of_folder){
        this.waitForElementAndClick(By.xpath(getFolderXpathByName(name_of_folder)),"Не найден мой сохраненный список '" + name_of_folder + "'",10);
    }

    public void waitForArticleToAppearByTitle(String article_title){
        this.waitForElementPresent(By.xpath(getSavedArticleXpathByTitle(article_title)),"Статья '" + article_title + "' не найдена в списке",50);
    }

    public void waitForArticleToDisappearByTitle(String article_title){
        this.waitForElementNotPresent(By.xpath(getSavedArticleXpathByTitle(article_title)),"Статья '" + article_title + "' всё еще существует в списке",9);
    }

    public void swipeByArticleToDelete(String article_title){

        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(By.xpath(getSavedArticleXpathByTitle(article_title)),"Не найдена статья '" + article_title + "' для свайпа в списке");
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void  waitForArticleAndClick(String article_title){
        this.waitForElementAndClick(By.xpath(getSavedArticleXpathByTitle(article_title)),"Не найдена статья '" + article_title + "' для клика в списке",50);
    }

}
