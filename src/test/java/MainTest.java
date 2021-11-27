import PageObject.PageObjectElementsDescription;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Rule;
import org.junit.Test;
import com.codeborne.selenide.junit.TextReport;
import WebDriverSettings.WebDriverSettings;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.extension.ExtendWith;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class MainTest extends WebDriverSettings {

    @Test
    @DisplayName("YandexPraktukumSelenideTest") // имя теста
    public void Test1() {
        PageObjectElementsDescription pageObjectElementsDescription = new PageObjectElementsDescription(); //Создали экземпляр класса MainPageObjectElementsDescription.
        open("https://qa-scooter.praktikum-services.ru/"); // Открыл окно бразуера.
    }
}
