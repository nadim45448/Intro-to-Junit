import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoQA {
    WebDriver driver;
    @BeforeAll
    public  void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    //@Test
    public void visitUrl(){
        driver.get("https://demoqa.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "DEMOQA";
        Assertions.assertTrue(actualTitle.equals(expectedTitle));
    }
    @DisplayName("Fillup the user form")
    @Test
    public void fillupUserForm(){
        driver.get("https://demoqa.com/text-box");
//        driver.findElement(By.id("userName")).sendKeys("Test User");
//        driver.findElement(By.cssSelector("[type=text]")).sendKeys("nadim hossain");

        List<WebElement> txtFields= driver.findElements(By.className("form-control"));
         txtFields.get(0).sendKeys("Test User");
         txtFields.get(1).sendKeys("testuser@gmail.com");
         txtFields.get(2).sendKeys("Gulshan");
         txtFields.get(3).sendKeys("Dhaka");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");

         List<WebElement> btnSubmit =driver.findElements(By.tagName("button"));
         btnSubmit.get(1).click();

        List <WebElement> txtResults = driver.findElements(By.tagName("p"));
        String nameActual = txtResults.get(0).getText();
        String emailActual = txtResults.get(1).getText();
        String cAddressActual = txtResults.get(2).getText();
        String pAddressActual = txtResults.get(3).getText();

        String nameExpected = "Test User";
        String emailExpected="testuser@gmail.com";
        String cAddressExpected = "Gulshan";
        String pAddressExpected = "Dhaka";

        Assertions.assertTrue(nameActual.contains(nameExpected));
        Assertions.assertTrue(emailActual.contains(emailExpected));
        Assertions.assertTrue(cAddressActual.contains(cAddressExpected));
        Assertions.assertTrue(pAddressActual.contains(pAddressExpected));






    }



    //@AfterAll
    public void tearDown(){
        driver.quit();
    }
}
