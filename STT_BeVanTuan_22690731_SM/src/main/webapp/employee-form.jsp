<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Add Employee</title>
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
  <img src="images/images.jpg" class="img" alt="Banner"/>

  <h3>Employee Information</h3>

  <form action="employees?" method="post">
    <input type="hidden" name="id" value="${employee.id}">
    <div class="mb-3">
      <label class="form-label">Name:</label>
      <input type="text" name="name" value="${employee.name}" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Salary:</label>
      <input type="number" name="salary" value="${employee.salary}" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Department:</label>
      <select name="departmentId" class="form-control">
        <c:forEach var="d" items="${departments}">
          <option value="${d.id}" ${employee.department==d.id ? "selected" : ""}>
              ${d.name}
          </option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-success">Save</button>
  </form>

  <a href="employees?deptId=${employee.department}" class="btn btn-secondary mt-3">Back</a>
</div>
</body>
</html>
