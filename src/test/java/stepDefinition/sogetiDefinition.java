package stepDefinition;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import utils.helper;

import java.time.Duration;

public class sogetiDefinition {


    private DriverFactory driverFactory;
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor executor;
    private helper hp;

    private By btnAcceptAllCookies = By.xpath("//button[@class='acceptCookie']");
    private By serviceLink = By.xpath("//li[@data-levelname='level2']");
    private By automationSubMenu = By.linkText("Automation");
    private By automationText = By.xpath("//span[text()='Automation']");

    private By firstName1 = By.name("__field_123927");
    private By lastName1 = By.name("__field_123938");
    private By email = By.name("__field_123928");
    private By phone = By.name("__field_123929");
    private By company = By.name("__field_132738");
    private By messageTextBox = By.name("__field_123931");
    private By iAgreeCheckBox = By.xpath("//label[text()='I agree']");
    private By notARobotchkBox = By.id("recaptcha-anchor");
    private By btnSubmit = By.name("submit");
    private By thankyouMsg = By.xpath("//div[@class='Form__Status__Message Form__Success__Message']/p");
    private By worldWide = By.xpath("//span[text()='Worldwide']");



    public sogetiDefinition() {
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver("chrome");
        hp = new helper(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        executor = (JavascriptExecutor) driver;
    }

    @Given("user launches the application under test")
    public void user_launches_the_application_under_test() {
        driver.get("https://www.sogeti.com/");
    }

    @When("user accepts Allow All cookies")
    public void user_accepts_allow_all_cookies() {
        driver.findElement(btnAcceptAllCookies).click();
    }

    @When("Hover over Services Link and then Click Automation link.")
    public void hover_over_services_link_and_then_click_automation_link() {
        WebElement serviceLink = driver.findElement(By.xpath("//li[@data-levelname='level2']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(serviceLink).build().perform();
        driver.findElement(automationSubMenu).click();
    }

    @Then("Verify that Automation Screen is displayed and {string} text is visible in Page")
    public void verify_that_automation_screen_is_displayed_and_text_is_visible_in_page(String string) {
        String actualPagetitle = driver.getTitle();
        String expectedPageTitle = string;
        System.out.println("Actual and Expected Page title: " + expectedPageTitle + " " + actualPagetitle);
        Assert.assertEquals(expectedPageTitle, actualPagetitle);

        String actualAutomationtext = driver.findElement(automationText).getText();

        System.out.println("Actual Page Automation Text is: " + actualAutomationtext);

        Assert.assertEquals("Automation", actualAutomationtext);
    }

    @Then("Hover again over Services Link and Verify that the Services and Automation are selected")
    public void hover_again_over_services_link_and_verify_that_the_services_and_automation_are_selected() {
        WebElement serviceLink = driver.findElement(By.xpath("//*[@class='level0 clearfix']//li[@data-levelname='level2']//div[@class='wrapper']/span"));
        wait.until(ExpectedConditions.visibilityOf(serviceLink));

        Actions actions = new Actions(driver);
        actions.moveToElement(serviceLink).build().perform();
        String elementColourServiceLink = serviceLink.getCssValue("color");
        String finalElColourServiceLink = Color.fromString(elementColourServiceLink).asHex();
        System.out.println("colour of ServiceLink: " + finalElColourServiceLink);
        Assert.assertEquals("#ff304c", finalElColourServiceLink);

        WebElement automationSubMenu = driver.findElement(By.linkText("Automation"));
        String eleColourAutomationLink = automationSubMenu.getCssValue("color");
        String finalEleColourAutomationLink = Color.fromString(eleColourAutomationLink).asHex();
        System.out.println("colour of Automation Link is: " + finalEleColourAutomationLink);
        Assert.assertEquals("#ff304c", finalEleColourAutomationLink);
    }

    // ####### Test Case 2 Steps  #########

    @When("On Automation Page scroll down to the Contact us Form")
    public void on_automation_page_scroll_down_to_the_contact_us_form() {
        WebElement firstName = driver.findElement(By.name("__field_123927"));
        executor.executeScript("arguments[0].scrollIntoView(true);", firstName);
    }

    @When("enter contact information data")
    public void enter_contact_information_data() {
        driver.findElement(firstName1).sendKeys(hp.generateRandomString(5));
        driver.findElement(lastName1).sendKeys(hp.generateRandomString(5));
        String emailGenerated = hp.generateRandomString(5) + "@gmail.com";
        driver.findElement(email).sendKeys(emailGenerated);
        driver.findElement(phone).sendKeys(hp.generateRandomNumber(10));
        driver.findElement(company).sendKeys(hp.generateRandomString(5));
        WebElement countryDD = driver.findElement(By.name("__field_132596"));
        hp.selectDropDownByVisibleText_custom(countryDD, "Germany");
        driver.findElement(messageTextBox).sendKeys(hp.generateRandomString(100));
    }

    @When("Check the I agree checkbox")
    public void check_the_i_agree_checkbox() {
        driver.findElement(iAgreeCheckBox).click();
        WebElement frame = driver.findElement(By.xpath("//*[@title='reCAPTCHA']"));
        driver.switchTo().frame(frame);
        System.out.println("Switched to frame");
        hp.hardWait(1000);
        WebElement wb = driver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
        wb.click();
        hp.hardWait(10000);

        /*#### There is a CAPTCHA on this window which can't be automated.
        To overcome this we can either disable CAPTCHA in the lower environment or ask the developers to set the CAPTCHA
        always checked in lower environments
         */
    }

    @When("Click SUBMIT button")
    public void click_submit_button() {
        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        executor.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        submitBtn.click();
    }

    @Then("After clicking SUBMIT button the form is submitted and Thank you message is displayed. Assert the Thank you message.")
    public void after_clicking_submit_button_the_form_is_submitted_and_thank_you_message_is_displayed_assert_the_thank_you_message() {
        String thankYouMsg = driver.findElement(thankyouMsg).getText();
        Assert.assertEquals("Thank you for contacting us.", thankYouMsg);
    }

// ############ Test Case 3 Steps #############


    @When("Click the Worldwide Dropdown link in Page Header")
    public void click_the_worldwide_dropdown_link_in_page_header() {
        driver.findElement(worldWide).click();
    }

    @When("Assert that all the Country specific Sogeti links are working")
    public void assert_that_all_the_country_specific_sogeti_links_are_working() {
        Boolean status = hp.getDDList();
        Assert.assertEquals(true, status);
    }

    @After
    @When("Close the browser")
    public void close_the_browser() {
        driver.quit();
    }

}
