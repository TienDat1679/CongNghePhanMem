package crud.employee.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import crud.employee.models.EmployeeModel;
import crud.employee.services.EmployeeServiceImpl;
import crud.employee.services.IEmployeeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class EmployeeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IEmployeeService employeeService = new EmployeeServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertEmployee(req, resp);
                    break;
                case "/delete":
                    deleteEmployee(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateEmployee(req, resp);
                    break;
                default:
                    listEmployee(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}


	private void listEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        List<EmployeeModel> listEmployee = employeeService.selectAllEmployees();
        req.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/employee-list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/employee-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        EmployeeModel existingEmployee = employeeService.selectEmployee(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/employee-form.jsp");
        req.setAttribute("employee", existingEmployee);
        dispatcher.forward(req, resp);
    }

    private void insertEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        EmployeeModel newEmployee = new EmployeeModel();
        newEmployee.setName(name);
        newEmployee.setGender(gender);
        newEmployee.setEmail(email);
        employeeService.insertEmployee(newEmployee);
        resp.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        

        EmployeeModel employee = new EmployeeModel();
        employee.setId(id);
        employee.setName(name);
        employee.setGender(gender);
        employee.setEmail(email);
        

        employeeService.updateEmployee(employee);
        resp.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        employeeService.deleteEmployee(id);
        resp.sendRedirect("list");
    }

}

