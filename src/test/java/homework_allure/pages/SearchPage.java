package homework_allure.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;

public class SearchPage {
    private static final String REPO_LINK = "AlexSpirid/qa_guru_hw_demoqa";

    private final SelenideElement repoLink = $(linkText(REPO_LINK));

    public SearchPage repoLinkClick() {

        repoLink.click();
        return this;
    }
}
