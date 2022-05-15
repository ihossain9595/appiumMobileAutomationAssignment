package com.qa.test.calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CalculatorTest extends BaseCalculatorTest {
    @Test
    public void addSingleDigitShouldBeSucceed() {
        // Value 8
        WebElement eightButton = driver.findElement(By.id("digit_8"));
        wait.until(elementToBeClickable(eightButton)).click();
        String firstInput = driver.findElement(By.id("formula")).getText().trim();
        System.out.println("First Input: " + firstInput);

        // Click Add Button
        wait.until(elementToBeClickable(driver.findElement(By.id("op_add")))).click();

        // Value 2
        WebElement twoButton = driver.findElement(By.id("digit_2"));
        wait.until(elementToBeClickable(twoButton)).click();
        String secondInput = driver.findElement(By.id("formula")).getText().trim().split("\\+")[1].trim();
        System.out.println("Second Input: " + secondInput);

        // Click Equal Button
        wait.until(elementToBeClickable(driver.findElement(By.id("eq")))).click();

        // Final Result
        WebElement finalResult = driver.findElement(By.id("result_final"));
        String result = finalResult.getText().trim();
        System.out.println("Result: " + result);

        Assert.assertEquals("10", result);
    }
}
