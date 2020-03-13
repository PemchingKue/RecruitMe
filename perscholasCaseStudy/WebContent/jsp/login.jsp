<!--
* Filename: login.jsp
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
-->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Login</title>

	<!-- Custom fonts for this template-->
	<link href="${root}/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">

	<!-- Custom styles for this template-->
	<link href="${root}/css/login_register.css" rel="stylesheet">

	<!-- BootStrap -->
	<link href="${root}/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<script src="${root}/bootstrap/jquery-3.4.1.js"></script>
	<script src="${root}/bootstrap/bootstrap.min.js"></script>
	
	<!-- Jquery Validation -->
	<script src="${root}/vendor/jquery-validation/jquery.validate.js"></script>
	
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					  
					<h3><i class="far fa-registered fa-2x rotate-n-15" style="color:#4e73df"></i>  Sign In</h3>

				</div>
				<div class="card-body">
					<form action="${root}/LoginServlet" id="loginform" method="POST">
						<div class="input-group form-group" style="margin-bottom: 2rem">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="email" name="email">
						</div>
						
						<div class="input-group form-group" style="margin-bottom: 2rem">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" placeholder="password" name="password">
						</div>
						
						<div class="form-group">
							<c:out value="${requestScope.error}"></c:out>
							<input type="submit" value="Login" class="btn float-right login_btn">
						</div>
						
					</form>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-center links">
						Don't have an account?<a href="${root}/jsp/registration.jsp">Sign Up</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<!--JQUERY VALIDATION -->
<script type="text/javascript">

	$("#loginform").validate({
        //SPECIFY RULES
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true
            }
        },
        // Specify validation error messages
        messages: {
            email: "<div id='loginerror'>*Please enter a valid email address</div>",
            password: {
                required: "<div id='loginerror'>*Please provide a password</div>"
            }
        }
	});
	
</script>


</html>