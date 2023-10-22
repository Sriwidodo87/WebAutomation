package saucedemo;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    @Test
   public void success_login(){
        WebDriver driver;
        String baseUrl ="https://www.saucedemo.com/";
        WebDriverManager.edgedriver().setup();
        //login edge driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        //input username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

       //input password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
       //click login
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();
        //quit
       driver.close();
    }
    @Test
    public void fail_login_blank_userpassword(){
        WebDriver driver;
        String baseUrl ="https://www.saucedemo.com/";
        WebDriverManager.edgedriver().setup();
        //login edge driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);


        //input username
        driver.findElement(By.id("user-name")).sendKeys("");
        //input password
        driver.findElement(By.id("password")).sendKeys("");
        //click login
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

        String error_user = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(error_user,"Epic sadface: Username is required");
        //quit
        driver.close();
    }
    @Test
    public void fail_login_typousername(){
        WebDriver driver;
        String baseUrl ="https://www.saucedemo.com/";
        WebDriverManager.edgedriver().setup();
        //login edge driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

        //input username TYPO
        driver.findElement(By.id("user-name")).sendKeys("STANDART_USER");
        //input password
        driver.findElement(By.id("password")).sendKeys("secret_sauc");
        //click login
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

      String error_user = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
       Assert.assertEquals(error_user,"Epic sadface: Username and password do not match any user in this service");
        //quit
       driver.close();
    }
    @Test
    public void fail_login_userregister_password_blank(){
        WebDriver driver;
        String baseUrl ="https://www.saucedemo.com/";
        WebDriverManager.edgedriver().setup();
        //login edge driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

        //input username
        driver.findElement(By.id("user-name")).sendKeys("standart_user");
        //input password blank
        driver.findElement(By.id("password")).sendKeys("");
        //click login
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

        String error_user = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(error_user,"Epic sadface: Password is required");
        //quit
        driver.close();
    }
    @Test
    public void fail_login_userlocked_out(){
        WebDriver driver;
        String baseUrl ="https://www.saucedemo.com/";
        WebDriverManager.edgedriver().setup();
        //login edge driver setup
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

        //input username
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        //input password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //click login
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

        String error_user = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Sorry, this user has been locked out.')]")).getText();
        Assert.assertEquals(error_user,"Epic sadface: Sorry, this user has been locked out.");
        //quit
        driver.close();
    }
}
