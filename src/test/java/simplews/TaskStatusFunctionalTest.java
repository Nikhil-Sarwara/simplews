package simplews;

// Import necessary Selenium WebDriver classes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; // Import the specific browser driver you are using
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Import necessary JUnit 4 annotations and assertion methods
import org.junit.Before; // Used for setup methods
import org.junit.After;    // Used for teardown methods
import org.junit.Test;     // Used to mark test methods

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue; // Useful for checking element visibility

/**
 * Functional tests for the Task Status Check web interface using Selenium WebDriver.
 * These tests simulate user interaction with the web page to verify the end-to-end functionality.
 * Each test method represents a specific scenario (e.g., checking a submitted task).
 */
public class TaskStatusFunctionalTest {

    // Declare the WebDriver instance. This will be used to control the browser.
    private WebDriver driver;

    // Define the base URL of your running web application.
    // Make sure your application is running locally (e.g., using 'mvn spring-boot:run') before running these tests.
    private static final String BASE_URL = "http://localhost:8080/taskStatus"; // Adjust port and path if necessary

    // Define the path to your WebDriver executable (e.g., chromedriver).
    // YOU MUST UPDATE THIS PATH TO MATCH THE LOCATION ON YOUR SYSTEM.
    private static final String WEBDRIVER_PATH = "/Users/nikhilsarwara/Downloads/chromedriver-mac-arm64/chromedriver";


    /**
     * Setup method that runs before each test case.
     * Initializes the WebDriver and sets up the browser.
     */
    @Before // Marks this method to be run before each test method.
    public void setUp() { // In JUnit 4, setup methods are typically named 'setUp' and must be public and void.
        // Set the system property to the path of your WebDriver executable.
        // This tells Selenium where to find the browser driver.
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH);

        // Initialize a new instance of the ChromeDriver (or the driver for your chosen browser).
        driver = new ChromeDriver();

        // Optional: Maximize the browser window for better visibility during test execution.
        driver.manage().window().maximize();
    }

    /**
     * Test case to functionally verify checking a "Submitted" task status via the web interface.
     * Simulates a user entering student and task IDs and checking the displayed status.
     */
    @Test // Marks this method as a test case to be executed by the JUnit runner.
    public void testCheckSubmittedTaskStatus() { // Test methods must be public and void.
        // Arrange: Define the input data and the expected status displayed on the page.
        String studentId = "student123";
        String taskId = "task001";
        String expectedDisplayedStatus = "Status: Submitted"; // Must match the exact text displayed on the page

        // Act: Perform actions on the web page using Selenium.
        driver.get(BASE_URL); // Navigate to the task status form page.

        // Find the input fields by their HTML 'id' attributes (as defined in taskStatusView.jsp).
        WebElement studentIdInput = driver.findElement(By.id("studentId"));
        WebElement taskIdInput = driver.findElement(By.id("taskId"));

        // Enter the test data into the input fields.
        studentIdInput.sendKeys(studentId);
        taskIdInput.sendKeys(taskId);

        // Find the submit button by its HTML 'type' or 'value' or other locator.
        // Assuming the submit button has type="submit" and value="Check Status"
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][value='Check Status']"));

        // Click the submit button to submit the form.
        submitButton.click();

        // Wait for the status display element to become visible after the form submission.
        // This is important because the page might update dynamically.
        // Using a WebDriverWait is more robust than a simple Thread.sleep().
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait up to 10 seconds
        // Assuming the status is displayed in a div with class "status-display"
        WebElement statusDisplayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".status-display")));

        // As a debugging step if visibilityOfElementLocated fails, you could try:
        // WebElement statusDisplayElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".status-display")));
        // If presenceOfElementLocated works but visibilityOfElementLocated fails, the element is in the DOM but not visible.


        // Assert: Verify the outcome.
        assertNotNull(statusDisplayElement); // Ensure the status element was found.
        assertTrue(statusDisplayElement.isDisplayed()); // Ensure the status element is visible.

        // Get the actual text displayed in the status element.
        String actualDisplayedStatus = statusDisplayElement.getText();

        // Assert that the actual displayed text matches the expected status text.
        // MODIFIED: Removed the custom message here to get a clearer error output if it fails.
        assertEquals(expectedDisplayedStatus, actualDisplayedStatus);
    }

    /**
     * Test case to functionally verify checking an "Under Review" task status.
     */
    @Test // Marks this method as a test case.
    public void testCheckUnderReviewTaskStatus() { // Test method must be public and void.
        // Arrange
        String studentId = "student456";
        String taskId = "task002";
        String expectedDisplayedStatus = "Status: Under Review"; // Must match the exact text displayed on the page

        // Act
        driver.get(BASE_URL); // Navigate to the form page.
        WebElement studentIdInput = driver.findElement(By.id("studentId"));
        WebElement taskIdInput = driver.findElement(By.id("taskId"));
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][value='Check Status']"));

        studentIdInput.sendKeys(studentId);
        taskIdInput.sendKeys(taskId);
        submitButton.click();

        // --- Code that waits for and finds the status display element ---
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement statusDisplayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".status-display")));
        // -----------------------------------------------------------------

        // Assert
        assertNotNull(statusDisplayElement);
        assertTrue(statusDisplayElement.isDisplayed());
        String actualDisplayedStatus = statusDisplayElement.getText();
        // MODIFIED: Removed the custom message here.
        assertEquals(expectedDisplayedStatus, actualDisplayedStatus);
    }

    /**
     * Test case to functionally verify checking a "Completed - Feedback Available" task status.
     */
    @Test // Marks this method as a test case.
    public void testCheckCompletedTaskStatus() { // Test method must be public and void.
        // Arrange
        String studentId = "student123";
        String taskId = "task003";
        String expectedDisplayedStatus = "Status: Completed - Feedback Available"; // Must match the exact text displayed on the page

        // Act
        driver.get(BASE_URL); // Navigate to the form page.
        WebElement studentIdInput = driver.findElement(By.id("studentId"));
        WebElement taskIdInput = driver.findElement(By.id("taskId"));
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][value='Check Status']"));

        studentIdInput.sendKeys(studentId);
        taskIdInput.sendKeys(taskId);
        submitButton.click();

        // --- Code that waits for and finds the status display element ---
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement statusDisplayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".status-display")));
        // -----------------------------------------------------------------

        // Assert
        assertNotNull(statusDisplayElement);
        assertTrue(statusDisplayElement.isDisplayed());
        String actualDisplayedStatus = statusDisplayElement.getText();
        // MODIFIED: Removed the custom message here.
        assertEquals(expectedDisplayedStatus, actualDisplayedStatus);
    }

    /**
     * Test case to functionally verify checking an "Invalid Task ID or Student ID" scenario.
     */
    @Test // Marks this method as a test case.
    public void testCheckInvalidTaskStatus() { // Test method must be public and void.
        // Arrange
        String studentId = "student999"; // Use input that is not in the simulated data.
        String taskId = "taskXXX";
        String expectedDisplayedStatus = "Status: Invalid Task ID or Student ID"; // Must match the exact text displayed on the page

        // Act
        driver.get(BASE_URL); // Navigate to the form page.
        WebElement studentIdInput = driver.findElement(By.id("studentId"));
        WebElement taskIdInput = driver.findElement(By.id("taskId"));
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][value='Check Status']"));

        studentIdInput.sendKeys(studentId);
        taskIdInput.sendKeys(taskId);
        submitButton.click();

        // --- Code that waits for and finds the status display element ---
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement statusDisplayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".status-display")));
        // -----------------------------------------------------------------

        // Assert
        assertNotNull(statusDisplayElement);
        assertTrue(statusDisplayElement.isDisplayed());
        String actualDisplayedStatus = statusDisplayElement.getText();
        // MODIFIED: Removed the custom message here.
        assertEquals(expectedDisplayedStatus, actualDisplayedStatus);
    }


    /**
     * Teardown method that runs after each test case.
     * Quits the WebDriver and closes the browser.
     */
    @After // Marks this method to be run after each test method.
    public void tearDown() { // In JUnit 4, teardown methods are typically named 'tearDown' and must be public and void.
        // Check if the driver instance is not null before quitting.
        if (driver != null) {
            // Quit the WebDriver, closing all associated browser windows.
            driver.quit();
        }
    }
}
