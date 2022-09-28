package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void testForm() {
        open("/automation-practice-form");
        $("#firstName").setValue("Aleksandr");
        $("#lastName").setValue("Spiridonov");
        $("#userEmail").setValue("1234mail@mail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--010").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("jpeg.1.png");

        $("#currentAddress").setValue("Address, Street, Building");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text("Aleksandr Spiridonov"),
                text("1234mail@mail.com"),
                text("Male"),
                text("1234567890"),
                text("10 June,1996"),
                text("Computer Science"),
                text("Sports"),
                text("jpeg.1.png"),
                text("Address, Street, Building"),
                text("NCR Delhi"));
    }
}