
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Reporter;
import org.testng.Assert;


public class TestNG_DataProvider {

    ChromeDriver driver;

    @DataProvider(name="test-data")
    public Object[][] dataProvFunc(){
        return new Object[][] {
            {"First-value"},{"Second-value"},{"Third-value"}
        };
    }



    @BeforeMethod
    public void setUp(){
        System.out.println("The start of the Test");
        System.setProperty("webdriver.chrome.driver","C:/Users/mau99/OneDrive/Escritorio/SoftwareQuality/SoftwareTestAutomationLevel2/SeleniumProjects/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable --extensions");
        driver = new ChromeDriver(options);
        String url = "https://www.google.com";
        driver.get(url);
        driver.manage().window().maximize();

    }

    @Test(dataProvider = "test-data")
    public void search(String keyword){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement txtbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("APjFqb")));
        txtbox.sendKeys(keyword);
        System.out.println("The keyword entered is: " + keyword);
        Reporter.log("The keyword entered is: " + keyword);
        Assert.assertTrue(txtbox.getAttribute("value").equalsIgnoreCase(keyword));
        txtbox.sendKeys(Keys.ENTER);
        System.out.println("Search results have been displayed");
        Assert.assertTrue(driver.getTitle().contains(keyword));
    }


    @AfterMethod
    public void burnDown(){
        driver.quit();
    }

    
}
