package homework_allure.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RepoPage {
    private final SelenideElement issueTab = $("#issues-tab"), issueTitle = $("#issue_2_link");

    public RepoPage issueTabClick() {

        issueTab.click();
        return this;
    }

    public RepoPage checkIssueTitle(String name) {

        issueTitle.shouldHave(text(name));
        return this;
    }
}
