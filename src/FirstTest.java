import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;


public class firstTest extends CoreTestCase {

    @Test // Первый тест из обучающего урока
    public void testSearch(){
        SearchPageObject SearhPageObject = new SearchPageObject(driver);

        SearhPageObject.initSearchInput();
        SearhPageObject.typeSearchLine("Java");
        SearhPageObject.waitForSearchResult("Java (programming language)");

        System.out.println("Первый тест из урока завершён!");
    }

    @Test // Второй тест из обучающего урока
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

        System.out.println("Второй тест из урока завершён!");
    }

    @Test // Третий тест из обучающего урока
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Неожиданный заголовок!",
                "Java (programming language)",
                article_title
        );
        System.out.println("Третий тест из урока завершён!");
    }

    @Test // Четвертый тест из обучающего урока
    public void testSwipeArticle() {
        String search_line = "Appium";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_line);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

        System.out.println("Четвертый тест из урока завершён!");
    }

    @Test // Тест из обучающего урока (проверка НЕ пустого результата поиска)
    public void testAmountOfNotEmptySearch() {
        String search_line = "Nirvana";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int count_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Не найдено ни одного результата поиска!",
                count_search_results > 0
        );

        System.out.println("Тест из обучающего урока (проверка НЕ пустого результата поиска) завершён!");
    }

    @Test // Тест из обучающего урока (проверка пустого результата поиска)
    public void testAmountOfEmptySearch() {
        String search_line = "dgdgdfsgdsfg";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

        System.out.println("Тест из обучающего урока (проверка пустого результата поиска) завершён!");
    }

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

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода)
    public void testElementHasText_Ex2() {
        String hasText = "Search Wikipedia";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        assertEquals(
                "Искомый текст элемента строки ввода '" + hasText + "' не найден!",
                hasText,
                SearchPageObject.getTextSearchInput()
        );
        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода) завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex3: Тест: отмена поиска)
    public void testCancelSearchWord_Ex3() {
        String search_line = "Sport";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        System.out.println("Найдено " + SearchPageObject.getAmountOfFoundArticles() + " статей в поиске");

        SearchPageObject.clearSearchLine();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex3: Тест: отмена поиска) завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex4*: Тест: проверка слов в поиске)
    public void testCheckSearchWordInArticle_Ex4() {
        String search_line = "Sport";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        assertTrue(
                "Ни один заголовок статьи НЕ содержит слово '" + search_line + "'",
                SearchPageObject.assertHasTextSearchTitle(search_line)
        );

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex4*: Тест: проверка слов в поиске) завершён!");
    }

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

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex6: Тест: assert title)
    public void testAssertTitle_Ex6() {
        String search_line = "Sport";
        String article  = "Sporting CP";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertTitleArticleText(article);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex6: Тест: assert title) завершён!");
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



