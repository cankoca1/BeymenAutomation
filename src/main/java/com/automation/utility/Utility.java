package com.automation.utility;

import com.automation.drivermanager.ManageDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Jay Vaghani
 */
public class Utility extends ManageDriver {

    public static org.openqa.selenium.chrome.ChromeDriver getChromeDriver() {
        return ChromeDriver;
    }

    private static org.openqa.selenium.chrome.ChromeDriver ChromeDriver;

    /**
     * This method will generate random number
     */
    public int generateRandomNumber() {
        return (int) (Math.random() * 20 + 1);

    }

    /**
     * This method will click on element
     */
    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public static void mouseHoverByElement(WebElement elementHovered) {

        // TODO Auto-generated method stub
        Actions action = new Actions(driver);
        action.moveToElement(elementHovered).build().perform();
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    /**
     * This method will send text on element
     */
    public void sendTextToElement(WebElement element, String str) {
        element.sendKeys(str);
    }

    /**
     * This method will save data on file
     */

    public static void saveTextToFile(String info, String fileName) {
        try {
            // Dosyayı oluştur veya varsa üzerine yaz
            Path path = Paths.get("src/main/java/com/automation/data.txt");
            Files.write(path, info.getBytes());

            System.out.println("Data saved to " + fileName + ":)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



