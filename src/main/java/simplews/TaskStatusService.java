package simplews;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service // Marks this class as a Spring Service bean so it can be injected
public class TaskStatusService {

    // Simulated data source for task statuses
    // In a real application, this data would typically come from a database or external system
    private final Map<String, String> simulatedTaskStatuses;

    public TaskStatusService() {
        // Initialize the map with sample task statuses
        simulatedTaskStatuses = new HashMap<>();
        // --- Ensure these key-value pairs exactly match your test expectations ---
        simulatedTaskStatuses.put("student123-task001", "Submitted"); // Key format: studentId-taskId
        simulatedTaskStatuses.put("student456-task002", "Under Review");
        simulatedTaskStatuses.put("student123-task003", "Completed - Feedback Available");
        simulatedTaskStatuses.put("student789-task004", "Submitted");
        // ------------------------------------------------------------------------
    }

    /**
     * Gets the status of a task based on student ID and task ID.
     *
     * @param studentId The ID of the student.
     * @param taskId    The ID of the task.
     * @return The status of the task, or "Invalid Task ID or Student ID" if the task/student combination is not found.
     */
    public String getTaskStatus(String studentId, String taskId) {
        // Create a unique key by combining student ID and task ID
        String key = studentId + "-" + taskId;

        // Look up the status using the created key
        String status = simulatedTaskStatuses.get(key);

        // Return the found status or the default message if the key was not in the map
        if (status == null) {
            // --- Ensure this string literal exactly matches the expected value in your Invalid Task test ---
            return "Invalid Task ID or Student ID";
            // ---------------------------------------------------------------------------------------------
        } else {
            return status;
        }
    }
}