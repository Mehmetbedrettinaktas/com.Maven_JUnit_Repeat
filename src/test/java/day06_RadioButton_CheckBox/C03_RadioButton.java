package day06_RadioButton_CheckBox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C03_RadioButton {
WebDriver driver;
@Before
    public  void setUp(){
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
    public void  test01() throws InterruptedException {
    // 1. Bir class oluşturun : RadioButtonTest
    //2. Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın.
    //- https://www.facebook.com adresine gidin
    driver.get("https://www.facebook.com");
    //- Cookies’i kabul edin
    driver.findElement(By.xpath("//*[text()='Temel ve isteğe bağlı çerezlere izin ver']")).click();
    //- “Create an Account” button’una basin
    driver.findElement(By.xpath("//*[text()='Yeni hesap oluştur']")).click();
    //- “radio buttons” elementlerini locate edin
    WebElement femaleButton= driver.findElement(By.xpath("//input[@value='1']"));
    WebElement maleButton= driver.findElement(By.xpath("//input[@value='2']"));
    WebElement customButton= driver.findElement(By.xpath("//input[@value='-1']"));
    //- Secili degilse cinsiyet butonundan size uygun olani secin
    if (!maleButton.isSelected()){
        maleButton.click();
    }
    Thread.sleep(3000);
}

}
