package com.automation.pages;

import com.automation.drivermanager.ManageDriver;
import com.automation.utility.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.time.Duration;

import static org.openqa.selenium.Keys.BACK_SPACE;


public class BeymenPage extends Utility {
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    WebElement accceptCookieButton;
    @FindBy(xpath = "//button[@class='dn-slide-deny-btn']")
    WebElement dontAllowButton;
    @FindBy(xpath = "//button[@id='genderManButton']")
    WebElement genderMaleButton;
    @FindBy(xpath = "//a[@class='o-productDetail__brandLink']/following-sibling::span[1]")
    WebElement itemInfo;
    @FindBy(xpath = "//div[@class='m-price__list']//ins[1]")
    WebElement itemPrice;
    @FindBy(xpath = "//button[@class='m-addBasketFavorite__basket btn']")
    WebElement addToCartButton;
    @FindBy(xpath = "//a[@class='o-header__userInfo--item bwi-cart-o -cart']")
    WebElement goToCartButton;
    @FindBy(xpath = "(//span[@class='m-orderSummary__value'])[1]")
    WebElement itemPriceAtCart;
    @FindBy(xpath = "//select[@class='a-selectControl -small']")
    WebElement itemNumberDropdown;
    @FindBy(xpath = "//option[text()='2 adet ']")
    WebElement twoItem;
    @FindBy(xpath = "(//div[@class='m-basket__optionsItem']//button)[2]")
    WebElement emptytheCart;
    @FindBy(xpath = "//div[@class='vue-notification-group']")
    WebElement notificationBuble;
    @FindBy(xpath = "(//span[@class='m-variation__item -criticalStock'])[1]")
    WebElement sizeSelection;
    @FindBy(xpath = "//strong[text()='Sepetinizde Ürün Bulunmamaktadır']")
    WebElement emptyCartNotif;

    private static final Logger log = LogManager.getLogger(BeymenPage.class.getName());

    public void enterWordToSearchbox(int row1, int column1, int row2, int column2) throws InterruptedException {
        log.info("Step -> I enter \"<row1>\", \"<row2>\" and \"<column1>\",\"<column2>\" word to searchbox initalized.");
        clickOnElement(accceptCookieButton);
        clickOnElement(dontAllowButton);
        clickOnElement(genderMaleButton);
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Ürün, Marka Arayın']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@placeholder='Ürün, Marka Arayın']")));
        Thread.sleep(2000);
        String cellValue = null;
        String cellValue2 = null;

        try {
            FileInputStream file = new FileInputStream("src/main/java/com/automation/excel1.xlsx");
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(row2);
            Cell cell = row.getCell(column2);
            cellValue = cell.getStringCellValue();
            System.out.println("Word from Excel: " + cellValue);
            log.info("Word from Excel: " + cellValue);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(4000);
        sendTextToElement(searchBox, cellValue);
        Thread.sleep(3000);
        WebElement searchBox2 = driver.findElement(By.xpath("(//input[@placeholder='Ürün, Marka Arayın'])[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//input[@placeholder='Ürün, Marka Arayın'])[2]")));
        searchBox2.sendKeys(BACK_SPACE);
        searchBox2.sendKeys(BACK_SPACE);
        searchBox2.sendKeys(BACK_SPACE);
        searchBox2.sendKeys(BACK_SPACE);
        Thread.sleep(3000);

        try {
            FileInputStream file = new FileInputStream("src/main/java/com/automation/excel1.xlsx");
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(row1);
            Cell cell = row.getCell(column1);
            cellValue2 = cell.getStringCellValue();
            System.out.println("Word from Excel: " + cellValue2);
            log.info("Word from Excel: " + cellValue2);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//input[@placeholder='Ürün, Marka Arayın'])[2]")));
        sendTextToElement(searchBox2, cellValue2);
        Thread.sleep(4000);
        searchBox2.sendKeys(Keys.ENTER);
        log.info("Step Successful!");
    }

    public void pickRandomItemAndAddItToCard() throws InterruptedException {
        log.info("Step -> I pick a random item and add it to cart initialized");
        int randomNumber1 = generateRandomNumber();
        String randomNumber = Integer.toString(randomNumber1);
        WebElement randomItem = driver.findElement(By.xpath("(//div[@class='m-productCard__photo'])[" + randomNumber + "]"));
        clickOnElement(randomItem);
        String itemInfoText = getTextFromElement(itemInfo);
        String itemPriceText = getTextFromElement(itemPrice);
        String textToSave = itemInfoText + " / " + itemPriceText;
        saveTextToFile(textToSave, "data.text");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//span[@class='m-variation__item -criticalStock'])[1]")));
        clickOnElement(sizeSelection);
        clickOnElement(addToCartButton);
        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[@class='o-header__userInfo--item bwi-cart-o -cart']")));
        clickOnElement(goToCartButton);
        Thread.sleep(2000);

        String[] parts = itemPriceText.split(" "); // Metni boşluk karakterine göre ayırır
        String onlyNumber = parts[0].replaceAll("[^0-9]", ""); // Sadece sayıları alır
        System.out.println("Price on search section: " + onlyNumber); // Sadece sayıyı yazdırır
        log.info("Price on search section: " + onlyNumber);

        String itemPriceAtCartText = getTextFromElement(itemPriceAtCart);
        int index = itemPriceAtCartText.indexOf(","); // İlk virgül işaretinin indeksini bulur
        String parsedText = itemPriceAtCartText.substring(0, index); // Metnin başından virgül işaretine kadar olan kısmı alır
        int onlyNumber2 = Integer.parseInt(parsedText.replaceAll("[^\\d]", "")); // Sadece sayıları alır ve bir tamsayıya dönüştürür
        String onlyNumber2Text = Integer.toString(onlyNumber2);

        System.out.println("Price on cart section: " + onlyNumber2Text);
        log.info("Price on cart section: " + onlyNumber2Text);
        Assert.assertEquals(onlyNumber, onlyNumber2Text);
        log.info("Step Successful!");

    }

    public void addMoreItemAndEmptyTheCart() throws InterruptedException {
        log.info("Step -> I add more item and empty the cart initialized ");
        clickOnElement(itemNumberDropdown);
        try {
            clickOnElement(twoItem);
            System.out.println("Items found.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // if there is no element available
            System.out.println("No item found!");
        }
        Thread.sleep(3000);
        clickOnElement(emptytheCart);

        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        String emptyCartText = "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[text()='Sepetinizde Ürün Bulunmamaktadır']")));
        Thread.sleep(2000);
        String emptyCartNotifText = getTextFromElement(emptyCartNotif);
        Assert.assertEquals(emptyCartText, emptyCartNotifText);
        log.info("Test Successful!");
        log.info("                                      ---------------- ------------------");
    }
}



