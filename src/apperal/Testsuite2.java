package apperal;

import browsertesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class Testsuite2 extends BaseTest {
    @Before
    public void verifyOpenBrowserAndLogIn() throws InterruptedException {
        String baseUrl="https://demo.nopcommerce.com/";
        openBrowser(baseUrl);
        clickOnElement(By.xpath("//a[@class='ico-register']"));
        Thread.sleep(2000);
        sendTextToElement(By.id("FirstName"),"Deeva");
        sendTextToElement(By.id("LastName"),"Jimulia");
        sendTextToElement(By.id("Email"),"diptitg@gmail.com"); //change every time
        sendTextToElement(By.id("Password"),"123456");
        sendTextToElement(By.id("ConfirmPassword"),"123456");
        clickOnElement(By.id("register-button"));
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

    }
    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfullyWithExistingUser() throws InterruptedException {
        mouseHoverToElement(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[3]/a[1]"));
        clickOnElement(By.xpath("//a[text()='Accessories ']"));
        String expactedaccesories  ="Accessories";
        String actualText = getTextFromElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[3]/div[1]/div[1]/h1[1]"));
        Assert.assertEquals("User Is Not On Accessories page ",expactedaccesories,actualText);
        Thread.sleep(1000);
        clickOnElement(By.xpath("//a[text()='Ray Ban Aviator Sunglasses']"));
        String rayBantextMsg="Ray Ban Aviator Sunglasses";
        String realRayBanTextMsg = getTextFromElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/h1[1]"));
        Assert.assertEquals("Customer Is Not On RayBan Glass Page",rayBantextMsg,realRayBanTextMsg);
        driver.findElement(By.id("product_enteredQuantity_33")).clear();
        sendTextToElement(By.id("product_enteredQuantity_33"),"3");
        clickOnElement(By.id("add-to-cart-button-33"));
        String topBarMessage = "The product has been added to your shopping cart";
        String realTopBarMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        Assert.assertEquals("product Not Added To The Cart",topBarMessage,realTopBarMessage);
        clickOnElement(By.xpath("//span[@class='close']"));
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[text()='Go to cart']"));
        String shoppingCartPage = "Shopping cart";
        String actualShoppingCartPage = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        Assert.assertEquals("Shopping Cart Page Is Not Displayed",shoppingCartPage,actualShoppingCartPage);
        String expectedQuantityIs = "(3)";
        String actualQuantityIs = getTextFromElement(By.xpath("//span[@class='cart-qty']"));
        Assert.assertEquals("Quantity Is Wrong",expectedQuantityIs,actualQuantityIs);
        String expectedTotalValue = "$75.00";
        String acutalTotalValue= getTextFromElement(By.className("product-subtotal"));
        Assert.assertEquals("Total Is Incorrect",expectedTotalValue,acutalTotalValue);
        Thread.sleep(3000);
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));
        String expectedVerifyWelcomeMsg = "Welcome, Please Sign In!";
        String realwelcomeText = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        Assert.assertEquals("Welcome Message Is Wrong",expectedVerifyWelcomeMsg,realwelcomeText);
        Thread.sleep(3000);
        sendTextToElement(By.id("Email"),"diptitg@gmail.com");
        sendTextToElement(By.id("Password"),"123456");
        clickOnElement(By.xpath("//button[contains(text(),'Log in')]"));
        Thread.sleep(3000);
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));
        clickOnElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"),"133");
        sendTextToElement(By.id("BillingNewAddress_City"),"India");
        sendTextToElement(By.id("BillingNewAddress_Address1"),"7-Artisan");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"12121");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"3434342");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.id("shippingoption_2") );
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']") );
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        selectByValueFromDropDown(By.id("CreditCardType"),"Amex");
        sendTextToElement(By.id("CardholderName"),"Mr.D Jimulia");
        sendTextToElement(By.id("CardNumber"),"341158029662487");
        selectByValueFromDropDown(By.id("ExpireMonth"),"5");
        selectByValueFromDropDown(By.id("ExpireYear"),"2024");
        sendTextToElement(By.id("CardCode"),"5634");
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));
        String paymentCard = "Payment Method: Credit Card";
        String actualPaymentCard = getTextFromElement(By.xpath("//li[@class='payment-method']"));
        Assert.assertEquals("Payment method Is Wrong",paymentCard,actualPaymentCard);
        String shippingMethod = "Shipping Method: 2nd Day Air";
        String actualShippingMethod  = getTextFromElement(By.xpath("//li[@class='shipping-method']"));
        Assert.assertEquals("Shipping method is Incorrect",shippingMethod,actualShippingMethod);
        String totalOrder = "Total: $75.00";
        String actualTotalOrder = getTextFromElement(By.xpath("//tr[@class='order-total']"));
        Assert.assertEquals("Total Order Is Wrong",totalOrder,actualTotalOrder);
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));    //click on continue
        String thanks = "Thank you";
        String actualThanksPage = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        Assert.assertEquals("Thank you Page Not Displayed",thanks,actualThanksPage);
        String orderConfirmation = "Your order has been successfully processed!";
        String realorderConfirmation = getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        Assert.assertEquals("Order Has Not Been Confirmed",orderConfirmation,realorderConfirmation);
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));  //last continue click
        clickOnElement(By.xpath("//a[@class='ico-logout']"));
        String expectedWelcomeMessage = "Welcome to our store";
        String actualWelcomeMessage  =getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        Assert.assertEquals("User Is Not On Main Page",expectedWelcomeMessage,actualWelcomeMessage);
        clickOnElement(By.xpath("//a[@class='ico-logout']"));
        String url = "https://demo.nopcommerce.com/";
        String realUrl = driver.getCurrentUrl();
        Assert.assertEquals("Wrong Url",url,realUrl);




    }
    @After
    public void tearDown(){
        closeBrowser();
    }

}





