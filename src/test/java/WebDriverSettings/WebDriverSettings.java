package WebDriverSettings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.codeborne.selenide.AssertionMode.STRICT;

abstract public class WebDriverSettings {
    // Настройки браузера
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 16000;
        Configuration.holdBrowserOpen = false;// Закроется ли браузер после выполнения тестов (true - не закроется, false - закроется).
        Configuration.headless = true; // будем ли мы видеть браузер при выполнении тестов (false = будем, true = не будем).
    }

    @Rule
    public TextReport textReport = new TextReport();

    @Before
    public void init() {
        setUp();
    }

    @After
    public void tearDown (){
        Selenide.closeWebDriver();
    }

}