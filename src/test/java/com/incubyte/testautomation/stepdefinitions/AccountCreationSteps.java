package com.incubyte.testautomation.stepdefinitions;
import com.incubyte.testautomation.pageobjects.AccountCreationPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AccountCreationSteps {
    WebDriver driver;
    AccountCreationPage accountCreationPage;

    @Given("the user is on the account creation page")
    public void the_user_is_on_the_account_creation_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        accountCreationPage = new AccountCreationPage(driver);
    }

    @When("the user enters valid details")
    public void the_user_enters_valid_details() {
        accountCreationPage.setFirstName("John");
        accountCreationPage.setLastName("Doe");
        accountCreationPage.setEmail("johndoe@example.com");
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
        assert pageSource.contains("Thank you for registering with Main Website Store.") : "Account creation failed!";
        System.out.println("Account created successfully!");
        driver.quit();
    }
}
