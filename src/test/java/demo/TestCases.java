package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */

    @Test
    public void testCase01() throws InterruptedException {
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        Thread.sleep(3000);

        WebElement nameInputBox = driver.findElement(By.xpath("//input[@aria-labelledby='i1 i4']"));
        Wrappers.enterText(nameInputBox, "Crio Learner");
        Thread.sleep(3000);
        System.out.println("inserting the name");

        WebElement practicingAutomationTextArea = driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
        String practicingAutomationText = "I want to be the best QA Engineer! 1710572021";

        String epochTimeString = Wrappers.getCurruntTime();

        Wrappers.enterText(practicingAutomationTextArea, practicingAutomationText + " " + epochTimeString);
        Thread.sleep(3000);

        System.out.println("Selecting the experience");
        Wrappers.radioButton(driver, "0 - 2");
        Thread.sleep(3000);

        System.out.println("Selecting the programming languages");
        Wrappers.Checkbox(driver, "Java");
        Wrappers.Checkbox(driver, "Selenium");
        Wrappers.Checkbox(driver, "TestNG");

        WebElement dropDowebElement = driver.findElement(By.xpath("//div[@jsname='LgbsSe']"));
        Wrappers.clickOnElement(driver, dropDowebElement);
        Thread.sleep(2000);
        Wrappers.dropDownClick(driver, "Mr");
        Thread.sleep(3000);

        WebElement dataInputBox = driver.findElement(By.xpath("//input[@type='date']"));
        String sevenDaysAgoDate = Wrappers.getDateSevenDaysAgo();
        Wrappers.enterText(dataInputBox, sevenDaysAgoDate);

        WebElement timeInputBoxHour = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        Wrappers.enterText(timeInputBoxHour, "07");

        WebElement timeInputBoxMinute = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        Wrappers.enterText(timeInputBoxMinute, "30");

        WebElement submitButton = driver.findElement(By.xpath("//div[@aria-label='Submit']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmationText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Thanks for your response')]")));
        System.out.println("Confirmation Text: " + confirmationText.getText()); 

    }

    @BeforeTest(alwaysRun = true)
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(30);

    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}