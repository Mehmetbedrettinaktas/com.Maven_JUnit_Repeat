package day06_RadioButton_CheckBox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C04_Assertions {
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
        driver.close();
    }
    @Test
    public void test01(){
        /*
        Eger Test method'umuzda hicbir test yoksa, test calistiktab sonra hic bir
        problemle karsilasilmadigini raporlamak icin "tests passed" yazisi cikar

        Eger testleri if ile yaparsak test faild olsa bile kodlar problemsiz calistigi icin kod calismasi bittiginde
        ekranin sol alt kisminda test passed yazacaktir.
         */
        driver.get("https://www.amazon.com");
/*
        // url'in https://www.facebok.com oldugunu test edin
        if (driver.getCurrentUrl().equals("https://www.facebok.com")){
            System.out.println("url testi PASSED");
        }else {
            System.out.println("url testi FAILED");
        }

 */
        String expectedUrl="https://www.facebok.com";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals("url beklenenden farkli ",expectedUrl,actualUrl);
        /*
        org.junit.ComparisonFailure:
        Expected :https://www.facebok.com
        Actual   :https://www.amazon.com/
         */

        /*
        assert ile yaptigimiz testlerde  assertion faild olursa Java kodlarin calismasini durdurur ve
        Assert class'i bizi hata konusunda bilgilendirir.

        org.junit.ComparisonFailure:
        Expected :url beklenenden farkli https://www.facebok.com
        Actual   :https://www.amazon.com/
        Boylece hatanin ne oldugunu arastirmamiza gerek kalmadan JUnit bize raporlamis olacak
         */
    }
}
