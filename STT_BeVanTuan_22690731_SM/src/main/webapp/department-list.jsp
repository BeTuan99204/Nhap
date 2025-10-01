<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Departments List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    .img{
        width: 1300px;
        height: 200px;
    }
</style>
<body>
<div class="container mt-4">

    <h2 class="mb-3">Departments List</h2>

    <!-- Form thêm phòng ban -->
    <img src="./images/images.jpg" class="img">
    <a href="department-form.jsp" style="display: block">Add department Page</a>
    <!-- Form tìm kiếm -->
    <form action="departments" method="get" class="d-flex mb-3">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" value="${keyword}" placeholder="Tìm phòng ban..." class="form-control me-2">
        <button type="submit" class="btn btn-secondary">Search</button>
    </form>

    <!-- Bảng danh sách -->
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>DEPT ID</th>
            <th>Name Department</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${departments}">
            <tr>
                <td>${d.id}</td>
                <td>${d.name}</td>
                <td>
                    <a href="departments?action=edit&id=${d.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="departments?action=delete&id=${d.id}" class="btn btn-danger btn-sm">Delete</a>
                    <a href="employees?deptId=${d.id}" class="btn btn-info btn-sm">Employees</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
