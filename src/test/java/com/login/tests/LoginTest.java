package com.login.tests;

import com.login.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    LoginPage login;
    SecureAreaPage secure;

    @BeforeMethod
    public void setUp() {
        initSetup();
        login = new LoginPage(driver);
        secure = new SecureAreaPage(driver);
    }

    @Test
    public void validLoginTest() {
        login.enterUsername("tomsmith");
        login.enterPassword("SuperSecretPassword!");
        login.clickLogin();

        Assert.assertTrue(secure.getSuccessMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void invalidLoginTest() {
        login.enterUsername("wrongusername");
        login.enterPassword("wrongpassword");
        login.clickLogin();

        Assert.assertTrue(login.getErrorMessage().contains("Your username is invalid!"));
    }

    @Test
    public void emptyFieldsTest() {
        login.enterUsername("");
        login.enterPassword("");
        login.clickLogin();

        Assert.assertTrue(login.getErrorMessage().contains("Your username is invalid!"));
    }

    @AfterMethod
    public void quit() {
        tearDown();
    }
}

