package day08_Alert_IFrame;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_Alerts {
    //● Bir class olusturun: Alerts
   static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //● https://the-internet.herokuapp.com/javascript_alerts adresine gidin.
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }
    @AfterClass
    public static void tearDown(){
        driver.close();
    }
    @Test
    public void acceptAlert(){
//● Bir metod olusturun: acceptAlert
        //○1. butona tıklayın,
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        // uyarıdaki OK butonuna tıklayın ve result mesajının
        driver.switchTo().alert().accept();
        //“You successfully clicked an alert” oldugunu test edin.
        String expectedResult="You successfully clicked an alert";
        WebElement sonucYaziElementi=driver.findElement(By.xpath("//*[text()='You successfully clicked an alert']"));
        String actualResult=sonucYaziElementi.getText();
        Assert.assertEquals(expectedResult,actualResult);


    }
    @Test
    public void dismissAlert(){
        //● Bir metod olusturun: dismissAlert
        //○ 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();

        //“successfuly” icermedigini test edin.
        String istenmeyenKelime="successfuly";
        WebElement sonucYazisiElementi= driver.findElement(By.xpath("//*[text()='You clicked: Cancel']"));
        String actualSonucYazisi=sonucYazisiElementi.getText();
        Assert.assertFalse(actualSonucYazisi.contains(istenmeyenKelime));
    }

@Test
    public void sendKeysAlert(){
    //● Bir metod olusturun: sendKeysAlert
    //○ 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna
    driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
    driver.switchTo().alert().sendKeys("Mehmet");
    driver.switchTo().alert().accept();
    //tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.
WebElement sonucYazisielementi=driver.findElement(By.xpath("//p[@id='result']"));
String sonucYazisiStr=sonucYazisielementi.getText();
String girilenIsim="Mehmet";
Assert.assertTrue(sonucYazisiStr.contains(girilenIsim));
}


}
