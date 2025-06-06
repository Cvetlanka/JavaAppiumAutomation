package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test // // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex5: Тест: Сохранение двух статей)
    public void testSaveArticleToMyListAndDeleteFirstArticle_Ex5() {

        String search_line = "Sport";
        String name_list = "My List of Sport";
        String first_article  = "Sporting CP";
        String second_article = "Sport of athletics";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(first_article);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.createListAndSaveArticleInIt(name_list);
        ArticlePageObject.closeArticle();

        SearchPageObject.clickByArticleWithSubstring(second_article);

        ArticlePageObject.addToExistListAnyArticle(name_list);
        ArticlePageObject.closeArticle();

        SearchPageObject.clickCancelSearch();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_list);
        MyListPageObject.swipeByArticleToDelete(second_article);
        MyListPageObject.waitForArticleToAppearByTitle(first_article);
        MyListPageObject.waitForArticleAndClick(first_article);

        ArticlePageObject.assertTitleArticleText(first_article);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex5: Тест: Сохранение двух статей) завершён!");
    }
}
