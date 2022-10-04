package test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import test.Data.Countries;
import java.util.List;
import static com.codeborne.selenide.Selenide.*;

public class WikiTest {
    static List<Arguments> cities() {
    return List.of(
            Arguments.of("Madrid"),
            Arguments.of("Paris"),
            Arguments.of("Moscow"),
            Arguments.of("Rome")
    );
}

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @CsvSource(value = {
            "From today's featured article", "In the news","Did you know ...","On this day"
    })
    @DisplayName("Тест проверки заголовков на главной странице")
    @ParameterizedTest
    void checkHeaders(String headers) {
        open("https://en.wikipedia.org/wiki/");
        $$("#bodyContent").shouldHave(CollectionCondition.texts(headers)
        );
    }

    @DisplayName("Тест поиска статьи про страну")
    @EnumSource(Countries.class)
    @ParameterizedTest()
    void checkCountrySearchTest(Countries country) {
        open("https://en.wikipedia.org/wiki/");
        $("#searchInput").setValue(country.name()).pressEnter();
        $$("#bodyContent").shouldHave(CollectionCondition.texts(country.name()));
    }

    @DisplayName("Тест поиска статьи про город")
    @MethodSource("cities")
    @ParameterizedTest()
    void checkCities (String city) {
        open("https://en.wikipedia.org/wiki/");
        $("#searchInput").setValue(city).pressEnter();
        $$("#bodyContent").shouldHave(CollectionCondition.texts(city)
        );
    }
}
