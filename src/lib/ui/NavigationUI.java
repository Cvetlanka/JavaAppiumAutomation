package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final String
            BTN_SAVED = "//android.widget.FrameLayout[@content-desc='Saved']/android.view.ViewGroup/android.widget.TextView";

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }
    public void clickMyList(){
        this.waitForElementAndClick(By.xpath(BTN_SAVED),"Не найдена кнопка сохраненных списков 'Saved'", 7);
    }
}
