package electronics;

import browsertesting.BaseTest;
import org.junit.After;

import browsertesting.BaseTest;
import homepage.TopMenuTest;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.nio.charset.StandardCharsets;

    public class ElectronicsTestNew extends BaseTest {
        @Before
        public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
            String baseUrl = "https://demo.nopcommerce.com/";
            openBrowser(baseUrl);
            Actions action = new Actions(driver);
            WebElement electronics = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/a[1]"));
            WebElement cellphone = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
            action.moveToElement(electronics).moveToElement(cellphone).click().build().perform();
            String expectedCellPhoneText = "Cell phones";
            String actualCellPhoneText = getTextFromElement(By.xpath("//h1[text()='Cell phones']"));
            Assert.assertEquals("Customer Is Not On Cellphone page", expectedCellPhoneText, actualCellPhoneText);
        }

        @Test
        public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
            clickOnElement(By.xpath("//a[@class='viewmode-icon list']"));
            Thread.sleep(2000);
            clickOnElement(By.xpath("//a[text()='Nokia Lumia 1020']"));       //click on nokia phone
            String expectedName = "Nokia Lumia 1020";
            String actualName = getTextFromElement(By.xpath("//h1[text()='Nokia Lumia 1020']"));
            Assert.assertEquals("Wrong Phone Page", expectedName, actualName);
            String price = "$349.00";
            String priceActual = getTextFromElement(By.id("price-value-20"));
            Assert.assertEquals("price for Phone Is Wrong", price, priceActual);
            driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).clear();
            sendTextToElement(By.xpath("//input[@id='product_enteredQuantity_20']"), "2");
            clickOnElement(By.id("add-to-cart-button-20"));               //add to cart button
            String topMessage = "The product has been added to your shopping cart";
            String realTopMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
            Assert.assertEquals("product Not Added To The Cart", topMessage, realTopMessage);
            clickOnElement(By.xpath("//span[@class='close']"));
            mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));//mousehover on shopping cart
            clickOnElement(By.xpath("//button[text()='Go to cart']"));           //click on go to cart
            String shoppingCart = "Shopping cart";             //assert for shopping cart
            String actualCartPage = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
            Assert.assertEquals("Shopping Cart Page Is Not Displayed", shoppingCart, actualCartPage);
            String quantityIs = "(2)";                //verifying quantity is 2--Assert
            String actualQuantityIs = getTextFromElement(By.xpath("//span[@class='cart-qty']"));
            Assert.assertEquals("The Quantity Is Incorrect", quantityIs, actualQuantityIs);
            String expectedTotal = "$698.00";
            String acutalTotal = getTextFromElement(By.xpath("//span[text()='$698.00']"));
            Assert.assertEquals("Total Is Incorrect", expectedTotal, acutalTotal);
            Thread.sleep(2000);
            clickOnElement(By.id("termsofservice")); //i agree checkbox
            clickOnElement(By.id("checkout"));        //checkout button
            String expectedWelcomeMsg = "Welcome, Please Sign In!";
            String realwelcomeMsg = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
            Assert.assertEquals("Welcome Message Is Wrong", expectedWelcomeMsg, realwelcomeMsg);
            Thread.sleep(1000);
            clickOnElement(By.xpath("//button[@class='button-1 register-button']"));
            String register = "Register";
            String actualRegister = getTextFromElement(By.xpath("//h1[text()='Register']"));
            Assert.assertEquals("User Is Not On Registration Page", register, actualRegister);
            Thread.sleep(2000);
            sendTextToElement(By.id("FirstName"), "KOmal");   //firstname
            sendTextToElement(By.id("LastName"), "Jimulia");   //lastname
            sendTextToElement(By.id("Email"), "grishma@gmail.com"); //email id has to be changed before running
            sendTextToElement(By.id("Password"), "23456");  //password
            sendTextToElement(By.id("ConfirmPassword"), "23456");//confirm password
            clickOnElement(By.id("register-button"));                                    //register button
            String registerCompleted = "Your registration completed";
            String actualRegistrationText = getTextFromElement(By.xpath("//div[text()='Your registration completed']"));
            Assert.assertEquals("User Registration Is Not Completed", registerCompleted, actualRegistrationText);
            clickOnElement(By.xpath("//a[@class='button-1 register-continue-button']"));
            String expectedCartMsg = "Shopping cart";
            String actuaCartMsg = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
            Assert.assertEquals("User Is Not on Shopping Cart page", expectedCartMsg, actuaCartMsg);
            Thread.sleep(1000);
            clickOnElement(By.id("termsofservice"));  //click I agree,again
            clickOnElement(By.id("checkout"));      //click checkout,again
            selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "133");
            Thread.sleep(1000);
            sendTextToElement(By.id("BillingNewAddress_City"), "Ahmedabad");   //city
            sendTextToElement(By.id("BillingNewAddress_Address1"), "7 Samta "); //address
            sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "56565"); //zip code
            sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "56789"); //phone number
            clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));       //click on contnue button
            clickOnElement(By.id("shippingoption_2"));          //click on radio button
            clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));  //click on continue button
            clickOnElement(By.id("paymentmethod_1"));
            clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
            selectByValueFromDropDown(By.id("CreditCardType"), "visa");
            sendTextToElement(By.id("CardholderName"), "Mr.S COOPER");  //name on card
            sendTextToElement(By.id("CardNumber"), "4539926921009583"); //card number
            selectByValueFromDropDown(By.id("ExpireMonth"), "8"); //month
            selectByValueFromDropDown(By.id("ExpireYear"), "2025");
            sendTextToElement(By.id("CardCode"), "449");            //cvc cardcode
            Thread.sleep(1000);
            clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));         //click on continue
            String paymentCardMethod = "Payment Method: Credit Card";
            String actualPaymentCardMethod = getTextFromElement(By.xpath("//li[@class='payment-method']"));
            Assert.assertEquals("Payment method Is Wrong", paymentCardMethod, actualPaymentCardMethod);
            String shippingMethod = "Shipping Method: 2nd Day Air";
            String actualShippingMethod = getTextFromElement(By.xpath("//li[@class='shipping-method']"));
            Assert.assertEquals("Shipping method is Incorrect", shippingMethod, actualShippingMethod);
            String totalOrder = "Total: $698.00";
            String actualTotalOrder = getTextFromElement(By.xpath("//tr[@class='order-total']"));
            Assert.assertEquals("Total Order Is Wrong", totalOrder, actualTotalOrder);
            clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));    //click on continue
            String thanksMsgShown = "Thank you";
            String actualThanksPageShown = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
            Assert.assertEquals("Thank you Page Not Displayed", thanksMsgShown, actualThanksPageShown);
            String orderConfirmation = "Your order has been successfully processed!";
            String realorderConfirmation = getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
            Assert.assertEquals("Order Has Not Been Confirmed", orderConfirmation, realorderConfirmation);
            Thread.sleep(1000);
            clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));  //last continue click
            String expectedWelcomeMessageShown = "Welcome to our store";
            String actualWelcomeMessageShown = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
            Assert.assertEquals("User Is Not On Main Page", expectedWelcomeMessageShown, actualWelcomeMessageShown);
            clickOnElement(By.xpath("//a[@class='ico-logout']"));
            String url = "https://demo.nopcommerce.com/";
            String realUrl = driver.getCurrentUrl();
            Assert.assertEquals("Wrong Url", url, realUrl);

        }

        @After
        public void tearDown() {
            closeBrowser();   //closing browser
        }

    }


