package com.login.tests;

import com.login.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import com.opencsv.CSVReader;

import java.io.FileReader;

public class DataDrivenLoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {

        try (CSVReader reader = new CSVReader(new FileReader("src/test/resources/testdata/loginData.csv"))) {

            java.util.List<String[]> data = reader.readAll();
            
            data.remove(0);
            
            Object[][] output = new Object[data.size()][3];

            for (int i = 0; i < data.size(); i++) {
                output[i][0] = data.get(i)[0];  // username
                output[i][1] = data.get(i)[1];  // password
                output[i][2] = data.get(i)[2];  // expected result
            }
            return output;
        }
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String user, String pass, String expected) {

        initSetup();
        LoginPage login = new LoginPage(driver);
        SecureAreaPage secure = new SecureAreaPage(driver);

        login.enterUsername(user);
        login.enterPassword(pass);
        login.clickLogin();

        if (expected.equalsIgnoreCase("success")) {

            Assert.assertTrue(
                    secure.getSuccessMessage().contains("You logged into a secure area!"),
                    "Success message not found!"
            );

        } 
        else if (expected.equalsIgnoreCase("invalid")) {

            String err = login.getErrorMessage();
            Assert.assertTrue(
                    err.contains("Your username is invalid!"),
                    "Invalid credentials error not shown!"
            );

        } 
        else if (expected.equalsIgnoreCase("empty")) {

            String err = login.getErrorMessage();
            Assert.assertTrue(
                    err.contains("Your username is invalid!"),
                    "Empty fields error not shown!"
            );
        }


        tearDown();
    }
}
