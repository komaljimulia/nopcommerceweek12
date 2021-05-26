package computer;

import browsertesting.BaseTest;
import homepage.TopMenuTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.*;

public class TestSuite extends BaseTest {

    String baseurl = " https://demo.nopcommerce.com/";


    @Before
    public void verifyProductArrangeInAlphaBaticalOrder() {
        openBrowser(baseurl);
        Actions actions = new Actions(driver);
        WebElement computer = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();
        WebElement sortBy = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sortBy);
        select.selectByValue("6");
        String expectedOrder = "Name: Z to A";
        WebElement actualOrder = driver.findElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));
        String realOrder = actualOrder.getText();
        Assert.assertEquals("The List Is not In Descending Order", expectedOrder, realOrder);
    }
    @Test
    public void  verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
       // verifyProductArrangeInAlphaBaticalOrder();
        WebElement sortBy = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sortBy);
        select.selectByValue("5");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@onclick='return AjaxCart.addproducttocart_catalog(\"/addproducttocart/catalog/1/1/1\"),!1']"));
        String expectedMessage = "Build your own computer";
        WebElement actualMessage = driver.findElement(By.xpath("//h1[text()='Build your own computer']"));
        String realMessage = actualMessage.getText();
        Assert.assertEquals("Computer is not available", expectedMessage, realMessage);
        WebElement dropDown = driver.findElement(By.xpath("//select[@id='product_attribute_1']"));
        select = new Select(dropDown);
        select.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        dropDown = driver.findElement(By.xpath("//select[@id='product_attribute_2']"));
        select = new Select(dropDown);
        select.selectByVisibleText("8GB [+$60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//label[text()= 'Vista Premium [+$60.00]']"));
        clickOnElement(By.id("product_attribute_5_12"));
        String expectedPrice = "$1,475.00";
        WebElement actualPrice = driver.findElement(By.xpath("//span[text()='$1,475.00']"));
        String realPrice = actualPrice.getText();
        Assert.assertEquals("Price is not match", expectedPrice, realPrice);
        clickOnElement(By.id("add-to-cart-button-1"));
        String finalMessage = "The product has been added to your shopping cart";
        WebElement message = driver.findElement(By.className("content"));
        String trueMessage = message.getText();
        Assert.assertEquals("This message should be appear", finalMessage, trueMessage);
        clickOnElement(By.className("close"));
        Actions actions = new Actions(driver);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        actions.moveToElement(shoppingCart).build().perform();
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        String expectedCartMessage = "Shopping cart";
        WebElement actualCartMessage = driver.findElement(By.xpath("//h1[text()='Shopping cart']"));
        String realCartMessage = actualCartMessage.getText();
        Assert.assertEquals("Veryfy cart message", expectedCartMessage, realCartMessage);
        //  driver.findElement(By.xpath("//input[@value = '1']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value = '1']")).clear();
        driver.findElement(By.xpath("//input[@value = '1']")).sendKeys("2");
        clickOnElement(By.xpath("//button[contains(text(),'Update shopping cart')]"));
        String expectedTotalAmount = "$2,950.00";
        WebElement actualTotalAmount = driver.findElement(By.xpath("//span[@class='product-subtotal']"));
        String actualTotalValue = actualTotalAmount.getText();
        Assert.assertEquals("Total Is Wrong", expectedTotalAmount, actualTotalValue);
        clickOnElement(By.cssSelector("#termsofservice"));
        clickOnElement(By.cssSelector("#checkout"));
        String expectedSignInMessage = "Welcome, Please Sign In!";
        WebElement actualSignInMessage = driver.findElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        String actualWelcomeSignInMessage = actualSignInMessage.getText();
        Assert.assertEquals("User Is Not On Sign In Page", expectedSignInMessage, actualWelcomeSignInMessage);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Checkout as Guest']")).click();
        // clickOnElement(By.xpath("//button[text()='Checkout as Guest']"));
        // xpath("//button[@class='button-1 checkout-as-guest-button']"));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_FirstName']")).sendKeys("Komal");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_LastName']")).sendKeys("Jimulia");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Email']")).sendKeys("komal.jimulia@gmail.com");
        WebElement dropdownCountry = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
        select = new Select(dropdownCountry);
        select.selectByValue("233");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys("Harrow");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys("7 Artisan place");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("HA3 5DS");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys("07988888867");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//label[contains(text(),'Credit Card')]"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        WebElement dropDownMasterCard = driver.findElement(By.xpath("//select[@id='CreditCardType']"));
        select = new Select(dropDownMasterCard);
        select.selectByValue("MasterCard");
        driver.findElement(By.xpath("//input[@id='CardholderName']")).sendKeys("ABC");
        driver.findElement(By.xpath("//input[@id='CardNumber']")).sendKeys("5367852429396419");
        WebElement dropDownMonth = driver.findElement(By.xpath("//select[@id='ExpireMonth']"));
        select = new Select(dropDownMonth);
        select.selectByValue("7");
        WebElement dropDownYear = driver.findElement(By.xpath("//select[@id='ExpireYear']"));
        select = new Select(dropDownYear);
        select.selectByValue("2026");
        driver.findElement(By.xpath("//input[@id='CardCode']")).sendKeys("123");
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
        String expectedText = "Payment Method: Credit Card";
        WebElement actualTxtDriver = driver.findElement(By.xpath("//li[@class='payment-method']"));
        String actualText = actualTxtDriver.getText();
        Assert.assertEquals("Please check your payment method", expectedText, actualText);
        String exptxt = "Shipping Method: Next Day Air";
        WebElement actultxtdriver = driver.findElement(By.xpath("//li[@class='shipping-method']"));
        String actultxt = actultxtdriver.getText();
        Assert.assertEquals("Please check your payment method", exptxt, actultxt);
        String expectedTotalText = "Total: $2,950.00";
        WebElement actualTotalTxt = driver.findElement(By.xpath("//tr[@class='order-total']"));
        String actualTotalIs = actualTotalTxt.getText();
        Assert.assertEquals("Total Order Is Incorrect", expectedTotalText, actualTotalIs);
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        String expectedThankYou = "Thank you";
        WebElement actualThankYou = driver.findElement(By.xpath("//h1[text()='Thank you']"));
        String realThankYou = actualThankYou.getText();
        Assert.assertEquals("Thank you message should display", expectedThankYou, realThankYou);
        String expectedSuccess = "Your order has been successfully processed!";
        WebElement actualSuccess = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        String realSuccess = actualSuccess.getText();
        Assert.assertEquals("This message should display", expectedSuccess, realSuccess);
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));
        String expectedLastMessage = "Welcome to our store";
        WebElement actualLastMessage = driver.findElement(By.xpath("//h2[text()='Welcome to our store']"));
        String realLastMessage = actualLastMessage.getText();
        Assert.assertEquals("This message should display", expectedLastMessage, realLastMessage);

    }
    @After
    public void tearDown(){
        closeBrowser();
    }












}

















