package day09_handelWindows_testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_handleWindows {
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
        driver.quit();
    }
    @Test
    public void test01(){
        // 1- Amazon anasayfasina gidin

        driver.get("https://www.amazon.com");
        String ilkSayfaHandleDegeri= driver.getWindowHandle();
        // 2- nuttela icin arama yaptirin
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella"+ Keys.ENTER);
        /*
        CDwindow-2F8CF14B7CACFA6ABD6681B8B3436537
        bu kod acilan sayfanin unique hash kodudur.
        Selenium sayfalar arasi geciste bu window handele degerini kullanir.

         eger sayfalar arasinda driver'imizi gezdiryorsak ve herhangi bir sayfadan suanda bulundugumuz sayfaya
         gecmek istiyorsak
        driver.switchTo().window("CDwindow-2F8CF14B7CACFA6ABD6681B8B3436537")
        bu sayfanin window handle degerini bu sayfaya gecisini saglayabiliriz.
         */

        // 3- ilk urunun resmini tiklayarak farkli bir tab olarak acin
       WebElement ilkUrunResmi= driver.findElement(By.xpath("(//div[@class='a-section aok-relative s-image-square-aspect'])[1]"));
       driver.switchTo().newWindow(WindowType.TAB);
       /*
       Bu komutu kullandigimizda driver otomatik olarak olusturulan new Tab'a gecer.
       yeni Tab'da gorevi gerceklestirmek icin
       adimlari bastan almamiz gerekir.
        */
        System.out.println("driver'in URL'i : "+ driver.getCurrentUrl());
        driver.get("https://www.amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella"+ Keys.ENTER);
        driver.findElement(By.xpath("(//div[@class='a-section aok-relative s-image-square-aspect'])[1]")).click();
        // 4- yeni tab'da acilan urunun title'ni yazdirin
        WebElement urunTittleElementi= driver.findElement(By.xpath("//span[@id='productTitle']"));
        System.out.println(urunTittleElementi.getText());

        // ilk sayfaya gecip url'i yazdiralim
        driver.switchTo().window(ilkSayfaHandleDegeri);
        System.out.println(driver.getCurrentUrl());
    }
}
