package simplews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Spring MVC Controller to handle web requests related to task status checking.
 * It maps URLs to methods that interact with the TaskStatusService and prepare the view.
 */
@Controller // Marks this class as a Spring Controller, responsible for handling web requests.
public class RoutingServlet {

    // Declare a field for the TaskStatusService.
    // Spring will automatically inject an instance of TaskStatusService here.
    private final TaskStatusService taskStatusService;

    /**
     * Constructor for the RoutingServlet.
     * Uses Spring's @Autowired to inject the TaskStatusService dependency.
     *
     * @param taskStatusService The TaskStatusService instance injected by Spring.
     */
    @Autowired // Tells Spring to automatically inject dependencies via this constructor.
    public RoutingServlet(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    /**
     * Handles HTTP GET requests to the "/taskStatus" URL.
     * This method is used to display the initial web page containing the form
     * for checking task status.
     *
     * @return The logical name of the view (JSP/HTML file) to render.
     * Spring will resolve this name to the actual file path based on configuration (e.g., /WEB-INF/jsp/taskStatusView.jsp).
     */
    @GetMapping("/taskStatus") // Maps GET requests for the "/taskStatus" path to this method.
    public String showStatusForm() {
        System.out.println("Received request to show task status form.");
        // Return the name of the view file.
        return "taskStatusView";
    }

    /**
     * Handles HTTP GET requests to the "/checkTaskStatus" URL.
     * This method is triggered when the form on the taskStatusView page is submitted.
     * It extracts the studentId and taskId from the request parameters,
     * calls the TaskStatusService to get the status, and adds the result to the Model
     * so it can be displayed on the view.
     *
     * @param studentId The value of the "studentId" request parameter. @RequestParam automatically binds the request parameter to this method argument.
     * @param taskId    The value of the "taskId" request parameter.
     * @param model     The Spring Model object. Used to pass data (like the task status) from the controller to the view.
     * @return The logical name of the view (JSP/HTML file) to render. Typically returns the same view name to display the result on the form page.
     */
    @GetMapping("/checkTaskStatus") // Maps GET requests for the "/checkTaskStatus" path to this method.
    public String checkTaskStatus(@RequestParam("studentId") String studentId, // Get 'studentId' from request parameters.
                                  @RequestParam("taskId") String taskId,     // Get 'taskId' from request parameters.
                                  Model model) { // Model to pass data to the view.

        System.out.println("Received request to check status for Student: " + studentId + ", Task: " + taskId);

        // Call the TaskStatusService to get the status based on the provided student and task IDs.
        String status = taskStatusService.getTaskStatus(studentId, taskId);

        // Add the retrieved status to the Model. This makes the 'status' variable available to the JSP view under the name "taskStatus".
        model.addAttribute("taskStatus", status);
        // Also add the original input values back to the model so the form fields can retain their values after submission.
        model.addAttribute("queriedStudentId", studentId);
        model.addAttribute("queriedTaskId", taskId);


        System.out.println("Task Status: " + status);

        // Return the name of the view file. This will render the taskStatusView.jsp page again,
        // but now with the 'taskStatus', 'queriedStudentId', and 'queriedTaskId' data available in the model.
        return "taskStatusView";
    }
}
