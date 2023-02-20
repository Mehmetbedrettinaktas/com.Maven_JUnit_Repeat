package day09_handelWindows_testBase;

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
import java.util.Set;

public class C03_WindowsHandels {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @After
    public void tearDown(){
        //driver.quit();
    }
    @Test
    public void test01(){
        // Tests package’inda yeni bir class olusturun: WindowHandle3
        //● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        //● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement sayfadakiYaziElemnti= driver.findElement(By.xpath("//h3"));
        String expectedYazi="Opening a new window";
        String actualYazi=sayfadakiYaziElemnti.getText();
        Assert.assertEquals(expectedYazi,actualYazi);
        //● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedTitle="The Internet";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
        /*
        eger kontrolsuz acilan tab veya window varsa o zaman sayfalarin window handel
        degerlerini elde etmem gerekir. oncelikle 2.sayfa acilmadan once
        ilk sayfanin window handele degerini bir String'e atayalim
         */
        String ilksayfaWindowHandelDegeri=driver.getWindowHandle();
        //● Click Here butonuna basın.
        driver.findElement(By.xpath("//*[text()='Click Here']")).click();
        /*
        switchTo()newWindow() demeden link tiklayarak yeni tab veya window olusturdugunda
        biz driver'a yeni sayfaya gec demedikce, driver wski sayfada kalir ve yeni sayfa
        ile ilgili hicbir islem yapamaz yeni sayfada driver'i calistirmak isterseniz
        once driver'i yeni sayfaya yollamasiniz
         */
        /*
        yeni sayfaya gecebilmek icin oncelikle ikinciSayfaWindowHandelDegeri'ni bulmamiz gerekir
        bunun icin driver.getWindowHandels() method'unu kullanarak acik olan tum sayfalarin window handele
        degerini alip bir set'e assign ederiz.

        ilk sayfanin window handel degerini zaten biliyoruz Set'deki window handel degerini kontrol edip
        ilk sayfanin handele degerine esit olmayan ikinci sayfanin window handele degeridir deriz.

         */
        Set<String> windowHandelSeti=driver.getWindowHandles();
        System.out.println(windowHandelSeti);
        System.out.println(ilksayfaWindowHandelDegeri);
        String ikinciSayfaWindowHandelDegeri="";
        for (String each:windowHandelSeti
             ) {
            if (!each.equals(ilksayfaWindowHandelDegeri)){
                 ikinciSayfaWindowHandelDegeri=each;
            }
        }
        // artik ikinci sayfanin window handle degerini biliyoruz rahatlikla sayfalar arasi
       // gecisi kolaylikla yapabiliriz.
        driver.switchTo().window(ikinciSayfaWindowHandelDegeri);
        //● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        String expectedIkinciTitle="New Window";
        String actualIkinciTitle=driver.getTitle();
        Assert.assertEquals(expectedIkinciTitle,actualIkinciTitle);
        //● Sayfadaki textin “New Window” olduğunu doğrulayın.
        WebElement ikinciSayfaYaziElementi=driver.findElement(By.xpath("//h3"));
        String expectedIkinciYazi="New Window";
        String actualIkinciYazi=ikinciSayfaYaziElementi.getText();
        Assert.assertEquals(expectedIkinciYazi,actualIkinciYazi);
        //● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu
        //doğrulayın.

    }
}
