import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoQAPractice {
    WebDriver driver;
    @BeforeAll
    public void setup(){
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
//        driver.findElement(By.cssSelector("[type=text]")).sendKeys("test user");

       List<WebElement> txtFields =driver.findElements(By.className("form-control"));
       txtFields.get(0).sendKeys("Test User");
       txtFields.get(1).sendKeys("testuser@gmail.com");
       txtFields.get(2).sendKeys("Gulshan");
       txtFields.get(3).sendKeys("Dhaka");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");

       List<WebElement> btnSubmit = driver.findElements(By.tagName("button"));
       btnSubmit.get(1).click();

       // get/read data
       List<WebElement> txtResult= driver.findElements(By.tagName("p"));
       String actualName = txtResult.get(0).getText();
       String actualEmail = txtResult.get(1).getText();
       String actualCurrentAddress = txtResult.get(2).getText();
       String actualPermanentAddress = txtResult.get(3).getText();

       String expectedName = "Test User";
       String expectedEmail = "testuser@gmail.com";
       String expectedCurrentAddress = "Gulshan";
       String expectedPermanentAddress = "Dhaka";

       Assertions.assertTrue(actualName.contains(expectedName));
       Assertions.assertTrue(actualEmail.contains(expectedEmail));
       Assertions.assertTrue(actualCurrentAddress.contains(expectedCurrentAddress));
       Assertions.assertTrue(actualPermanentAddress.contains(expectedPermanentAddress));
    }
   // @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
