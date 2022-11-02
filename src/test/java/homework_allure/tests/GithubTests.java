package homework_allure.tests;

import homework_allure.pages.MainPage;
import homework_allure.pages.RepoPage;
import homework_allure.pages.SearchPage;
import homework_allure.steps.WebSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class GithubTests extends BaseTest {

    private static final String REPO_LINK = "AlexSpirid/demoqa-test-15";

    private static final String ISSUE_NAME = "Test issue";

    MainPage mainPage = new MainPage();

    SearchPage searchPage = new SearchPage();

    RepoPage repoPage = new RepoPage();

    WebSteps webSteps = new WebSteps();

    @Test
    @DisplayName("Проверить название issue – тест selenide")
    public void checkNameIssueSelenide() {

        mainPage.openPage().searchInputClick().searchInputSetValue(REPO_LINK).searchInputSubmit();
        searchPage.repoLinkClick();
        repoPage.issueTabClick().checkIssueTitle(ISSUE_NAME);
    }

    @Test
    @DisplayName("Проверить название issue – тест с lamda-функцией step")
    public void checkNameIssueLambdaOpen() {

        step("Открываем главную страницу", () -> {
            mainPage.openPage();
        });
        step("Ищем в поиске репозиторий " + REPO_LINK, () -> {
            mainPage.searchInputClick().searchInputSetValue(REPO_LINK).searchInputSubmit();
        });

        step("В результатах поиска кликаем по ссылке репозитория " + REPO_LINK, () -> {
            searchPage.repoLinkClick();
        });

        step("Нажимаем на таб Issues", () -> {
            repoPage.issueTabClick();
        });

        step("Проверяем название issue " + ISSUE_NAME, () -> {
            repoPage.checkIssueTitle(ISSUE_NAME);
        });
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Создать issue")
    @Owner("AlexSpirid")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверить название issue – тест с аннотацией @Step")
    public void checkNameIssueAnnotationStep() {

        webSteps.openMainPage();
        webSteps.searchRepo(REPO_LINK);
        webSteps.clickOnLinkRepo();
        webSteps.clickIssueTab();
        webSteps.checkIssueTitle(ISSUE_NAME);
    }

}