<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Employees List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<style>
    .img{
        width: 1300px;
        height: 200px;
    }
</style>
<div class="container mt-4">
    <img src="images/images.jpg" class="img" alt="Banner"/>

    <h3>Employees List</h3>
    <a href="employees?action=add&deptId=${department.id}" class="btn btn-primary mb-3">Add Employee</a>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name Employee</th>
            <th>Salary</th>
            <th>Dept</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${employees}">
            <tr>
                <td>${e.id}</td>
                <td>${e.name}</td>
                <td>${e.salary}</td>
                <td>${e.department}</td>
                <td>
                    <a href="employees?action=edit&id=${e.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="employees?action=delete&id=${e.id}&deptId=${department.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="departments" class="btn btn-secondary mt-3">Department</a>
</div>
</body>
</html>
