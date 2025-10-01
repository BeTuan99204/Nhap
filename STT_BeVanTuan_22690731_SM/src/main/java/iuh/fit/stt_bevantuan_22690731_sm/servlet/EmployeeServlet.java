package iuh.fit.bevantuan_22690731_tuan5_bai5.servlet;




import iuh.fit.stt_bevantuan_22690731_sm.dao.DepartmentDAO;
import iuh.fit.stt_bevantuan_22690731_sm.dao.EmployeeDAO;
import iuh.fit.stt_bevantuan_22690731_sm.model.Department;
import iuh.fit.stt_bevantuan_22690731_sm.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    @Resource(name="jdbc/qlnhanvien")
    private DataSource dataSource;
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        employeeDAO = new EmployeeDAO(dataSource);
        departmentDAO = new DepartmentDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Employee emp = employeeDAO.getEmployeeById(id);
                List<Department> departments = departmentDAO.getAllDepartments();
                req.setAttribute("employee", emp);
                req.setAttribute("departments", departments);
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            case "delete":
                employeeDAO.deleteEmployee(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employees?deptId=" + req.getParameter("deptId"));
                break;
            case "add":
                List<Department> depts = departmentDAO.getAllDepartments();
                req.setAttribute("departments", depts);
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            default: // list
                String deptIdStr = req.getParameter("deptId");
                if (deptIdStr != null) {
                    int deptId = Integer.parseInt(deptIdStr);
                    List<Employee> emps = employeeDAO.getEmployeesByDepartment(deptId);
                    Department dept = departmentDAO.getDepartmentById(deptId);
                    req.setAttribute("employees", emps);
                    req.setAttribute("department", dept);
                    req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("departments"); // fallback
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("departmentId"));

        Department dept = departmentDAO.getDepartmentById(deptId);
        Employee emp = new Employee();

        emp.setName(name);
        emp.setSalary(salary);
        emp.setDepartment(dept.getId());

        if (idStr == null || idStr.isEmpty()) {
            employeeDAO.insertEmployee(emp);
        } else {
            emp.setId(Integer.parseInt(idStr));
            employeeDAO.updateEmployee(emp);
        }

        resp.sendRedirect("employees?deptId=" + deptId);
    }
}
