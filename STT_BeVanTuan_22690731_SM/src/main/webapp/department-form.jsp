<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department Information</title>
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

    <h3>Department Information</h3>

    <form action="departments?action=save" method="post">
        <div class="mb-3">
            <label class="form-label">Name:</label>
            <input type="text" name="name" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">Save</button>
    </form>

    <a href="departments" class="btn btn-secondary mt-3">Back</a>
</div>
</body>
</html>
