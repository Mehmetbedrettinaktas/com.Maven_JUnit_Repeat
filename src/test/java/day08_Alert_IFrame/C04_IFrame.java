package day08_Alert_IFrame;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C04_IFrame {
    // ● Bir class olusturun: IframeTest

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager .chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    @After
    public void tearDown(){
       // driver.close();
    }
    @Test
    public void test01() throws InterruptedException {
        //● https://the-internet.herokuapp.com/iframe adresine gidin.
        driver.get("https://the-internet.herokuapp.com/iframe");
        //● Bir metod olusturun: iframeTest
        //○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve konsolda yazdirin.
        WebElement baslikElementi= driver.findElement(By.xpath("//h3"));
        Assert.assertTrue(baslikElementi.isEnabled());
        System.out.println(baslikElementi.getText());
        //○Text Box’a “Merhaba Dunya!” yazin.
        /*
        texbox'i dogru locate etmemeize ragmen driver bulamadi bunun uzerine HTML kodlari inceleyince
        texbox'in aslinda bir IFrame icerisinde oldugunu gorduk bu durumda once iframe'i locate edip
        switchTo() ile o iFrame'e gecmeliyiz.
         */
        WebElement iFrameElementi= driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iFrameElementi);
        WebElement textKutusu= driver.findElement(By.xpath("//body[@id='tinymce']"));
        textKutusu.clear();
        textKutusu.sendKeys("Merhaba Dunya");


        //○TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu
        //dogrulayin ve konsolda yazdirin
        /*
        link yazi elementini dogru locate etmemize ragmen yazdirmadi cunku yukarida iFrame'e gecis yapmistik
        once oradan cikmamiz lazim.
         */
        driver.switchTo().defaultContent(); // bununla ana sayfaya donus yapiyoruz.
        WebElement linkyaziElementi= driver.findElement(By.xpath("//*[text()='Elemental Selenium']"));
        Assert.assertTrue(linkyaziElementi.isDisplayed());
        System.out.println("linkyaziElementi.isDisplayed() = " + linkyaziElementi.isDisplayed());
        Thread.sleep(5000);

    }
}
