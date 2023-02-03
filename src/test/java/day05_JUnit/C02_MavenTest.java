package day05_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_MavenTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //1. http://zero.webappsecurity.com sayfasina gidin
        driver.get("http://zero.webappsecurity.com");
        //2. Signin buttonuna tiklayin
        WebElement signinButtonu=driver.findElement(By.xpath("//button[@id='signin_button']"));
        signinButtonu.click();
        //3. Login alanine “username” yazdirin
        WebElement loginAlan= driver.findElement(By.xpath("//*[@id='user_login']"));
        loginAlan.sendKeys("username");
        //4. Password alanine “password” yazdirin
        WebElement passwordAlan= driver.findElement(By.xpath("//*[@id='user_password']"));
        passwordAlan.sendKeys("password");
        //5. Sign in buttonuna tiklayin( hata mesaji icin back tusuna basin)
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.navigate().back();
        //6.Online Baking menusunden  Pay Bills sayfasina gidin
        driver.findElement(By.xpath("(//*[text()='Online Banking'])[1]")).click();
        driver.findElement(By.xpath("//span[@id='pay_bills_link']")).click();
        //7. amount kismina yatirmak istediginiz herhangi bir miktari yazin
        driver.findElement(By.xpath("//input[@id='sp_amount']")).sendKeys("500");
        //8. tarih kismina “2020-09-10” yazdirin
        driver.findElement(By.xpath("//input[@id='sp_date']")).sendKeys("2020-09-10");
        //9. Pay buttonuna tiklayin
        driver.findElement(By.xpath("//input[@id='pay_saved_payees']")).click();
        //10. “The payment was successfully submitted.” mesajinin ciktigini control edin
        WebElement sonucYazisiElementi= driver.findElement(By.xpath("//*[text()='The payment was successfully submitted.']"));
        if (sonucYazisiElementi.isDisplayed()){
            System.out.println("test PASSED");
        }else {
            System.out.println("test FAILED");
        }
        driver.close();
    }
}
