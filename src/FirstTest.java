import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;
@Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\Leo\\Desktop\\Света\\Разное\\Курсы\\Автоматизация моб.приложений\\IdeaProjects\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        String spec = "http://127.0.0.1:4723/wd/hub";
        driver = new AndroidDriver(new URL(spec), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void firstTest()
    {
        System.out.println("УРА! У меня запустился и выполнился мой первый тест для Андроид! :)");
    }
}
