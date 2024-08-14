package chezubaProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class chezubaTestcase {

    public static void main(String[] args) {
    	WebDriver driver = null;
    	
        try {
            driver = new ChromeDriver();  // Initialize WebDriver
            driver.manage().window().maximize();  // Maximize the browser window
            
            driver.get("https://www.amazon.in/");  // Navigate to Amazon's website

            if (driver.getCurrentUrl().contains("amazon")) {    // Verify that the current URL contains "amazon"
                System.out.println("Test passed: Valid URL");
            } else {
                System.out.println("Test failed: Invalid URL");
            }

            WebElement searchbox = driver.findElement(By.id("twotabsearchtextbox"));  // Locate the search box using its ID 
            searchbox.sendKeys("mobile"); //Enter the search term "mobile"

            WebElement searchbutton = driver.findElement(By.id("nav-search-submit-button")); //// Locate the search button using its ID and click it
            searchbutton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  //// Wait for the results area to be visible and locate it using an appropriate XPath
            WebElement resultsArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-color-state a-text-bold']")));

            String expectedText = "mobile"; //// Verify that the results area contains the expected text "mobile"
            if (resultsArea.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                System.out.println("Test passed: Expected text is present in the results area.");
            } else {
                System.out.println("Test failed: Expected text is not found in the results area.");
            }
        } catch (Exception e) {
            System.err.println("Test failed due to an exception: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();  // Ensure that the WebDriver is properly closed
            }
        }
    }
}
