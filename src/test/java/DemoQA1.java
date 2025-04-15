import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoQA1 {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    //@Test
    public void clickButtons(){
        driver.get("https://demoqa.com/buttons");
       List<WebElement> btn = driver.findElements(By.tagName("button"));
        Actions actions = new Actions(driver); // double click, right click, left click korar jonno Actions class use korte hoy
        actions.doubleClick(btn.get(1)).perform();
        actions.contextClick(btn.get(2)).perform();
        actions.click(btn.get(3)).perform();
    }
    //@Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

//        driver.findElement(By.id("alertButton")).click();
//        Thread.sleep(2000);
//        driver.switchTo().alert().accept();
//
//        driver.findElement(By.id("timerAlertButton")).click();
//        Thread.sleep(7000);
//        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();

    }
//@Test
    public void handleDate(){
    driver.get("https://demoqa.com/date-picker");
    WebElement txtCalendar = driver.findElement(By.id("datePickerMonthYearInput"));
    txtCalendar.click();
    txtCalendar.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
    txtCalendar.sendKeys("04/10/2025");
    txtCalendar.sendKeys(Keys.ENTER);

    }

   // @Test
    public void selectDropDown() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        Select option = new Select(driver.findElement(By.id("oldSelectMenu")));
        option.selectByIndex(1);
        Thread.sleep(2000);

        option.selectByValue("2");
        Thread.sleep(2000);

        option.selectByVisibleText("Yellow");

    }
   // @Test
    public void selectDynamicDropdown(){
        driver.get("https://demoqa.com/select-menu");
       List<WebElement> txtDropdown = driver.findElements(By.className("css-1hwfws3"));
       txtDropdown.get(0).click();

       Actions actions = new Actions(driver);
       actions.moveToElement(txtDropdown.get(0)).sendKeys(Keys.ARROW_DOWN,Keys.ENTER).perform();
    }

   // @Test
    public void mouseHover(){
        driver.get("https://www.aiub.edu/");
       List<WebElement> menuAbout = driver.findElements(By.xpath("//a[contains(text(),\"About\")]"));
       Actions actions = new Actions(driver);
       actions.moveToElement(menuAbout.get(0)).perform();

       // sub menu
//        actions.click().submenu.get(2).perform()

    }
   // @Test
    public void handleMultipleTabs() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(3000);
        ArrayList<String> w = new ArrayList(driver.getWindowHandles());
        System.out.println(w);
        //switch to open tab
        driver.switchTo().window(w.get(1));
        System.out.println("New tab title: " + driver.getTitle());
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assertions.assertEquals(text, "This is a sample page");
        driver.close();
        driver.switchTo().window(w.get(0));

    }

    @Test
    public void handleWindow(){
        driver.get("https://demoqa.com/browser-windows");

        //Thread.sleep(5000);
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("windowButton")));
        driver.findElement(By.id(("windowButton"))).click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                String text = driver.findElement(By.id("sampleHeading")).getText();
                Assertions.assertTrue(text.contains("This is a sample page"));
            }

        }
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test
    public void scrapData(){
        driver.get("https://demoqa.com/webtables");
        WebElement table = driver.findElement(By.className("rt-tbody"));
        List<WebElement> allRows = table.findElements(By.cssSelector("[role=row]"));
        int i = 0;
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("[role=gridcell]"));
            for (WebElement cell : cells) {
                i++;
                System.out.println("num[" + i + "] " + cell.getText());

            }
        }
    }

    //@AfterAll
    public void tearDown() {
        driver.quit();
    }






}
