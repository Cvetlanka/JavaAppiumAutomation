package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test // Седьмой тест из обучающего урока
    public void testCheckSearchArticleInBackground() {
        String search_line = "Sport";
        String article  = "Sporting CP";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(article);

        this.backgroundApp(5);
        SearchPageObject.waitForSearchResult(article);

        System.out.println("Седьмой тест из урока завершён!");
    }

    @Test // // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex7*: Поворот экрана)
    public void testChangeScreenOrientationOnScreenResults_Ex7(){
        String search_line = "Sport";
        String article  = "Sporting CP";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Заголовок статьи изменился после первого поворота экрана!\n Был '" + title_before_rotation + "'.\n Стал '" + title_after_rotation + "'.",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Заголовок статьи изменился после второго поворота экрана!\n Был '" + title_before_rotation + "'.\n Стал '" + title_after_second_rotation + "'.",
                title_before_rotation,
                title_after_second_rotation
        );

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex7*: Поворот экрана) завершён!");
    }
}
