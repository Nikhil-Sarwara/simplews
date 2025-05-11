<%--
    This JSP (JavaServer Pages) file represents the web page for the Task Status Check function.
    It contains an HTML form for the user to input Student ID and Task ID
    and a section to display the task status result retrieved from the server.
    Uses JSP Expression Language (EL) to access data passed from the Spring MVC controller (RoutingServlet).
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OnTrack Task Status Check</title>
<style>
    /* Basic styling for the page elements */
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f4f4f4;
        color: #333;
    }
    h2 {
        color: #0056b3;
    }
    form {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        width: fit-content; /* Adjust width based on content */
        margin-bottom: 20px;
    }
    label {
        display: block; /* Labels on their own line */
        margin-bottom: 5px;
        font-weight: bold;
        color: #555;
    }
    input[type="text"] {
        width: 250px; /* Fixed width for input fields */
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box; /* Include padding and border in element's total width and height */
    }
    input[type="submit"] {
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        transition: background-color 0.3s ease; /* Smooth hover effect */
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
    .status-display {
        margin-top: 20px;
        padding: 15px;
        border-radius: 8px;
        font-weight: bold;
        border: 1px solid #ccc;
        background-color: #e9e9e9;
    }
    /* Optional: Styling based on the status */
    .status-display.submitted { border-color: blue; background-color: #e0f7fa; color: blue; }
    .status-display.under-review { border-color: orange; background-color: #fff3e0; color: orange; }
    .status-display.completed { border-color: green; background-color: #e8f5e9; color: green; }
    .status-display.invalid { border-color: red; background-color: #ffebee; color: red; }
</style>
</head>
<body>

    <h2>OnTrack Task Status Checker</h2>

    <%--
        HTML Form for user input.
        The 'action' attribute points to the URL that the form data will be sent to,
        which matches the @GetMapping("/checkTaskStatus") method in your RoutingServlet.
        The 'method' attribute is "get" to match the @GetMapping.
    --%>
    <form action="/checkTaskStatus" method="get">
        <div>
            <label for="studentId">Student ID:</label>
            <%--
                Input field for Student ID.
                'name="studentId"' is crucial as it matches the @RequestParam("studentId") in the controller.
                'value="${queriedStudentId != null ? queriedStudentId : ''}"' uses EL to keep the previously entered value after submission.
                'required' makes the field mandatory in the browser.
            --%>
            <input type="text" id="studentId" name="studentId" value="${queriedStudentId != null ? queriedStudentId : ''}" required>
        </div>
        <div>
            <label for="taskId">Task ID:</label>
            <%--
                Input field for Task ID.
                'name="taskId"' matches the @RequestParam("taskId") in the controller.
                'value="${queriedTaskId != null ? queriedTaskId : ''}"' retains the value after submission.
                'required' makes the field mandatory.
            --%>
            <input type="text" id="taskId" name="taskId" value="${queriedTaskId != null ? queriedTaskId : ''}" required>
        </div>
        <div>
            <br/>
            <input type="submit" value="Check Status">
        </div>
    </form>

    <%--
        Section to display the task status result.
        This div is conditionally displayed only if the 'taskStatus' attribute exists in the model (i.e., after the form has been submitted and the controller returned a status).
        The content '${taskStatus != null ? "Status: " + taskStatus : ""}' uses EL to display "Status: " followed by the actual status value.
        The CSS class is dynamically set based on the status value for visual feedback.
    --%>
    <div class="status-display ${taskStatus != null ? (taskStatus == 'Submitted' ? 'submitted' : (taskStatus == 'Under Review' ? 'under-review' : (taskStatus == 'Completed - Feedback Available' ? 'completed' : (taskStatus == 'Invalid Task ID or Student ID' ? 'invalid' : '')))) : ''}">
        <%-- MODIFIED: Changed EL expression to avoid potential NumberFormatException --%>
        ${taskStatus != null ? 'Status: ' : ''}${taskStatus != null ? taskStatus : ''}
    </div>

    <%--
        Optional: Add navigation links or other content here.
        <br/><a href="/">Back to Home</a>
    --%>

</body>
</html>
