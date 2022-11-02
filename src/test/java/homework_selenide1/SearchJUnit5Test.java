package homework_selenide1;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchJUnit5Test {

    @Test
    void searchJUnit5Test() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(".wiki-rightbar").shouldHave(text("SoftAssertions"));
        $$(".details-reset").findBy(visible).shouldBe(text("SoftAssertions")).click();
        $("#wiki-body").$(byText("3. Using JUnit5 extend test class:"))
                .shouldHave(text("3. Using JUnit5 extend test class:"));
    }
}
