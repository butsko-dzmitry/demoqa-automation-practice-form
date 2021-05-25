package demoqa.practiceform;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    private String firstName = "Dzmitry";
    private String lastName = "Butsko";
    private String userMail = "butsko.dzmitry@gmail.com";
    private String userNumber = "0685016462";
    private String subject = "Computer Science";
    private String address = "Address";
    private String state = "NCR";
    private String city = "Delhi";


    @BeforeAll
    static void setUpConfig() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @BeforeEach
    void openPracticeFormPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @Test
    public void FillAllFieldsInPracticeForm() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userMail);
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--004").click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/avatar.jpg"));
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        // Assertions
        $(".modal-body")
                .shouldHave(text(firstName + " " + lastName),
                            text(userMail),
                            text("Male"),
                            text(userNumber),
                            text("04 September,1995"),
                            text(subject),
                            text("Sports"),
                            text("avatar.jpg"),
                            text(address),
                            text(state + " " + city));
        $("#closeLargeModal").shouldBe(visible).shouldBe(enabled);
    }
}
