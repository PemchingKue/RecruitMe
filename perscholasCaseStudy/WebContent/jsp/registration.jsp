<!--
* Filename: registration.jsp
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
-->
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
	
	<!-- Jquery Validation -->
	<script src="${root}/vendor/jquery-validation/jquery.validate.js"></script>
	
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card" style="height: 33rem">
				<div class="card-header">
					<h3><i class="far fa-registered fa-2x rotate-n-15" style="color:#4e73df"></i>  Register</h3>
				</div>
				<div class="card-body">
					<form action="${root}/RegistrationServlet" id="registerform" method="POST">
						
						<div class="input-group form-group" style="margin-bottom: 2rem">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="far fa-envelope"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="email" name="email">
						</div>
						
						<div class="input-group form-group" style="margin-bottom: 2rem">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-signature"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="firstname" name="firstname">
						</div>
							
						<div class="input-group form-group" style="margin-bottom: 2rem">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-signature"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="lastname" name="lastname">
						</div>
							
						<div class="input-group form-group" style="margin-bottom: 2rem">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" placeholder="password" name="password">
						</div>
						
						<div class="input-group form-group" style="margin-bottom: 2rem">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" id="rPass" placeholder="confirm password" name="confirm_password">
						</div>	

						<div class="form-group">
							<c:out value="${requestScope.error}"></c:out>
							<input type="submit" value="Register" class="btn float-right login_btn">
							<button type="button" onclick="window.location='${root}/jsp/login.jsp'" class="btn float-right login_btn" style="margin-right: 2px">Cancel</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<!--JQUERY VALIDATION -->
<script type="text/javascript">

		$("#registerform").validate({
            //SPECIFY RULES
            rules: {
                email: {
                    required: true,
                    email: true
                },
                firstname: "required",
                lastname: "required",
                password: {
                    required: true,
                    minlength: 5
                },
                confirm_password: {
                    equalTo: "#rPass"
                }
            },
            // Specify validation error messages
            messages: {
                firstname: "<div id='registererror'>*Please provide a firstname</div>",
                lastname: "<div id='registererror'>*Please provide a lastname</div>",
                password: {
                    required: "<div id='registererror'>*Please provide a password</div>",
                    minlength: "<div id='registererror'>*Your password must be at least 5 characters long</div>"
                },
                confirm_password: {
                    equalTo: "<div id='registererror'>*Please enter the same password</div>"
                },
                email: "<div id='registererror'>*Please enter a valid email address</div>"
            }
	});

</script>
</html>