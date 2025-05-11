package simplews;

// Import necessary JUnit 4 annotations and assertion methods.
// @Before and @Test are used to mark setup and test methods.
import org.junit.Before;
import org.junit.Test;

// Import static assertion methods for easier use (e.g., assertEquals, assertNotNull).
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the TaskStatusService class.
 * These tests are written using the JUnit 4 framework.
 * Each test method verifies a specific behavior of the TaskStatusService.
 * Follows the Arrange, Act, Assert pattern.
 */
public class TaskStatusServiceTest {

    // Declare the service instance that will be tested.
    private TaskStatusService taskStatusService;

    /**
     * Setup method that runs before each individual test case.
     * Initializes a fresh instance of the TaskStatusService for every test.
     * This ensures tests are independent and start from a known state.
     */
    @Before // Marks this method to be run before each test method.
    public void setUp() { // In JUnit 4, setup methods are typically named 'setUp' and must be public and void.
        // Initialize the service instance.
        taskStatusService = new TaskStatusService();
    }

    /**
     * Test case to verify the correct status is returned for a "Submitted" task.
     */
    @Test // Marks this method as a test case to be executed by the JUnit runner.
    public void testGetTaskStatus_Submitted() { // Test methods must be public and void.
        // Arrange: Define the inputs and the expected output for this specific scenario.
        String studentId = "student123";
        String taskId = "task001";
        String expectedStatus = "Submitted";

        // Act: Call the method under test with the arranged inputs.
        String actualStatus = taskStatusService.getTaskStatus(studentId, taskId);

        // Assert: Verify that the actual result matches the expected result.
        assertNotNull(actualStatus); // Assert that the returned status is not null.
        // Use assertEquals to compare the expected and actual status strings.
        // Removed the custom message to get a clearer error output if it fails.
        assertEquals(expectedStatus, actualStatus);
    }

    /**
     * Test case to verify the correct status is returned for an "Under Review" task.
     */
    @Test // Marks this method as a test case.
    public void testGetTaskStatus_UnderReview() { // Test method must be public and void.
        // Arrange
        String studentId = "student456";
        String taskId = "task002";
        String expectedStatus = "Under Review";

        // Act
        String actualStatus = taskStatusService.getTaskStatus(studentId, taskId);

        // Assert
        assertNotNull(actualStatus);
        assertEquals(expectedStatus, actualStatus); // Compare expected and actual status.
    }

    /**
     * Test case to verify the correct status is returned for a "Completed - Feedback Available" task.
     */
    @Test // Marks this method as a test case.
    public void testGetTaskStatus_Completed() { // Test method must be public and void.
        // Arrange
        String studentId = "student123";
        String taskId = "task003";
        String expectedStatus = "Completed - Feedback Available";

        // Act
        String actualStatus = taskStatusService.getTaskStatus(studentId, taskId);

        // Assert
        assertNotNull(actualStatus);
        assertEquals(expectedStatus, actualStatus); // Compare expected and actual status.
    }

    /**
     * Test case to verify the correct status is returned for an invalid task ID or student ID.
     * This tests the scenario where the student/task combination is not in the simulated data.
     */
    @Test // Marks this method as a test case.
    public void testGetTaskStatus_InvalidTask() { // Test method must be public and void.
        // Arrange
        String studentId = "student999"; // Use input that is not in the simulated data.
        String taskId = "taskXXX";
        String expectedStatus = "Invalid Task ID or Student ID"; // This must match the exact string returned by TaskStatusService for invalid input.

        // Act
        String actualStatus = taskStatusService.getTaskStatus(studentId, taskId);

        // Assert
        assertNotNull(actualStatus);
        assertEquals(expectedStatus, actualStatus); // Compare expected and actual status.
    }


}
