package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationFormPage;
import testData.User;

public class PracticeFormTestRandom {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void fillFormTest() {
        registrationFormPage.openPage()
                .setFirstName(User.firstName)
                .setLastName(User.lastName)
                .setEmail(User.email)
                .setGender(User.gender)
                .setNumber(User.number)
                .setBirthDate(User.day, User.month, User.year)
                .setSubjects(User.subjects)
                .setHobbies(User.hobbies)
                .setPicture(User.picturePath)
                .setAddress(User.address, User.state, User.city)
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", User.firstName + " " + User.lastName)
                .checkResult("Student Email", User.email)
                .checkResult("Gender", User.gender)
                .checkResult("Mobile", User.number)
                .checkResult("Date of Birth", User.birthDay)
                .checkResult("Subjects", User.subjects)
                .checkResult("Hobbies", User.hobbies)
                .checkResult("Picture", User.picture)
                .checkResult("Address", User.address)
                .checkResult("State and City", User.state + " " + User.city);
    }

    @Test
     void fillFormWithMinimumDataTest() {
        registrationFormPage.openPage()
                .setFirstName(User.firstName)
                .setLastName(User.lastName)
                .setGender(User.gender)
                .setNumber(User.number)
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", User.firstName + " " + User.lastName)
                .checkResult("Gender", User.gender)
                .checkResult("Mobile", User.number);
    }

    @AfterEach
    void addAttachments() {

        AllureAttachments.screenshotAs("Последний скриншот");
        AllureAttachments.pageSource();
        AllureAttachments.browserConsoleLogs();
        AllureAttachments.addVideo();
    }
}
