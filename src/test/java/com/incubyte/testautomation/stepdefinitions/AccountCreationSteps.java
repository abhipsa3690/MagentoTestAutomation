package com.incubyte.testautomation.stepdefinitions;
import com.incubyte.testautomation.pageobjects.AccountCreationPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

public class AccountCreationSteps {
    WebDriver driver;
    AccountCreationPage accountCreationPage;
    String randomEmail;

    @Given("the user is on the account creation page")
    public void the_user_is_on_the_account_creation_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        accountCreationPage = new AccountCreationPage(driver);
    }

    @When("the user enters valid details")
    public void the_user_enters_valid_details() {
        accountCreationPage.setFirstName("Test");
        accountCreationPage.setLastName("User");
        randomEmail = "user" + new Random().nextInt(10000) + "@example.com";
        accountCreationPage.setEmail(randomEmail);
        accountCreationPage.setPassword("Password123!");
        accountCreationPage.confirmPassword("Password123!");
    }

    @When("submits the form")
    public void submits_the_form() {
        accountCreationPage.clickSubmit();
    }

    @Then("the account is created successfully")
    public void the_account_is_created_successfully() {
        String pageSource = driver.getPageSource();
        assert pageSource.contains("Thank you for registering") : "Account creation failed!";
        System.out.println("Account created successfully with email: " + randomEmail);
        driver.quit();
    }
}
