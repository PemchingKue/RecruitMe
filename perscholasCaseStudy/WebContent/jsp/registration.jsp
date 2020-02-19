<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Registration</title>
	
	<!-- Custom fonts for this template-->
	<link href="${root}/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">

	<!-- Custom styles for this template-->
	<link href="${root}/css/login_register.css" rel="stylesheet">

	<!-- BootStrap -->
	<link href="${root}/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<script src="${root}/bootstrap/jquery-3.4.1.js"></script>
	<script src="${root}/bootstrap/bootstrap.min.js"></script>
	
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3><i class="far fa-registered fa-2x rotate-n-15" style="color:#4e73df"></i>  Register</h3>
				</div>
				<div class="card-body">
					<form action="${root}/RegistrationServlet" method="POST">
						<div class="input-group form-group">
							<input type="text" class="form-control" placeholder="email" name="email">
						</div>
						<div class="input-group form-group">
							<input type="text" class="form-control" placeholder="firstname" name="firstname">
						</div>
						<div class="input-group form-group">
							<input type="text" class="form-control" placeholder="lastname" name="lastname">
						</div>
						<div class="input-group form-group">
							<input type="password" class="form-control" placeholder="password" name="password">
						</div>
						<div class="form-group">
							<input type="submit" value="Register" class="btn float-right login_btn">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>