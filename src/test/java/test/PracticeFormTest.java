package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @Test
    void testForm() {
        registrationFormPage.openPage()
                .setFirstName("Aleksandr")
                .setLastName("Spiridonov")
                .setEmail("1234mail@mail.com")
                .setGender("Male")
                .setNumber("1234567890")
                .setBirthDate("10", "June", "1996")
                .setSubjects("Computer Science")
                .setHobbies("Sports")
                .setPicture("jpeg.1.png")
                .setAddress("Address, Street, Building", "NCR", "Delhi")
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Aleksandr Spiridonov")
                .checkResult("Student Email", "1234mail@mail.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890")
                .checkResult("Date of Birth", "10 June,1996")
                .checkResult("Subjects", "Computer Science")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "jpeg.1.png")
                .checkResult("Address", "Address, Street, Building")
                .checkResult("State and City", "NCR Delhi");
    }
}
