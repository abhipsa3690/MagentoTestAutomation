package com.incubyte.testautomation.stepdefinitions;
import com.incubyte.testautomation.pageobjects.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        loginPage = new LoginPage(driver);
    }

    @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("Password123!");
    }

    @When("the user enters invalid credentials")
    public void the_user_enters_invalid_credentials() {
        loginPage.enterEmail("invaliduser@example.com");
        loginPage.enterPassword("wrongpassword");
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user is logged in successfully")
    public void the_user_is_logged_in_successfully() {
        String pageSource = driver.getPageSource();
        assert pageSource.contains("Welcome") : "Login failed!";
        System.out.println("User logged in successfully!");
        driver.quit();
    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed() {
        String errorMessage = loginPage.getErrorMessage();
        assert errorMessage.contains("Invalid login or password.") : "Expected error message not displayed!";
        System.out.println("Error message displayed: " + errorMessage);
        driver.quit();
    }
}
