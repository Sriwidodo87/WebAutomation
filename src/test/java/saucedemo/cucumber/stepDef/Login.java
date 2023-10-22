package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver;
    String baseUrl ="https://www.saucedemo.com";

    @Given("Homepage login")
    public void homepageLogin() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);

    }

    @When("Input username")
    public void inputUsername() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }
    @When("Input username kosong")
    public void inputUsernameKosong() {
        driver.findElement(By.id("user-name")).sendKeys("");
    }

    @And("Input password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @When("Input tipousername")
    public void inputTipousername() {
        driver.findElement(By.id("user-name")).sendKeys("STANDART_USER");
    }

    @And("Input password kosong")
    public void inputPasswordKosong() {
        driver.findElement(By.id("password")).sendKeys("");
    }

    @When("Input username locked")
    public void inputUsernameLocked() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
    }
    @And("click login button")
    public void click_login_button(){
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();
    }
    @Then("user in dashboard page")
    public void user_in_dashboard_page(){
    String dashboard = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
        Assert.assertEquals(dashboard,"Products");
        driver.close();
    }


    @Then("user in message locked")
    public void userInMessageLocked() {
        String error_user = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(error_user,"Epic sadface: Sorry, this user has been locked out.");
        //quit
        driver.close();
    }


    @Then("user in message pasuser kosong")
    public void userInMessagePasuserKosong() {
        String error_user = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(error_user,"Epic sadface: Username is required");
        //quit
        driver.close();
    }

    @Then("user in message typo")
    public void userInMessageTypo() {
        String error_user = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(error_user,"Epic sadface: Username and password do not match any user in this service");
        //quit
        driver.close();
    }

    @Then("user in message userregist")
    public void userInMessageUserregist() {
        String error_user = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(error_user,"Epic sadface: Password is required");
        //quit
        driver.close();
    }
}
