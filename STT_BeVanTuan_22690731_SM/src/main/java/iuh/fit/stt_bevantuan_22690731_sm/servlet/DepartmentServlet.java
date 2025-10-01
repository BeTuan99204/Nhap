package iuh.fit.stt_bevantuan_22690731_sm.servlet;




import iuh.fit.stt_bevantuan_22690731_sm.dao.DepartmentDAO;
import iuh.fit.stt_bevantuan_22690731_sm.model.Department;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({"", "/departments"})
public class DepartmentServlet extends HttpServlet {
    @Resource(name="jdbc/qlnhanvien")
    private DataSource dataSource;
    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        departmentDAO = new DepartmentDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        System.out.println("Action = " + action);
        switch (action) {
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Department dept = departmentDAO.getDepartmentById(id);
                req.setAttribute("department", dept);
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;
            case "delete":
                departmentDAO.deleteDepartment(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("departments");
                break;
            case "search":
                String keyword = req.getParameter("keyword");
                List<Department> searchList = departmentDAO.searchDepartmentsByName(keyword);
                req.setAttribute("departments", searchList);
                req.setAttribute("keyword", keyword);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;
            default: // list
                List<Department> list = departmentDAO.getAllDepartments();

                System.out.println("Departments size = " + list);
                req.setAttribute("departments", list);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");

        if (idStr == null || idStr.isEmpty()) {
            departmentDAO.insertDepartment(new Department(0, name));
        } else {
            departmentDAO.updateDepartment(new Department(Integer.parseInt(idStr), name));
        }

        resp.sendRedirect("departments");
    }
}
