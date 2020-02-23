<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
    <%@ page session="true" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Profile Settings</title>

  <!-- Custom fonts for this template-->
  <link href="${root}/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- CSS-->
  <link href="${root}/css/main.css" rel="stylesheet">

  <!-- BootStrap -->
  <link href="${root}/bootstrap/bootstrap.min.css" rel="stylesheet" />
  <script src="${root}/bootstrap/jquery-3.4.1.js"></script>
  <script src="${root}/bootstrap/bootstrap.min.js"></script>

  <!-- POSTITALL CSS AND JS -->
  <link rel="stylesheet" href="${root}/vendor/postitall/jquery.postitall.css">
  <link rel="stylesheet" href="${root}/vendor/postitall/jquery.minicolors.css">
  <link rel="stylesheet" href="${root}/vendor/postitall/jquery-ui-timepicker-addon.css">
  <link rel="stylesheet" href="${root}/vendor/postitall/jquery-ui.css">

  <script src="${root}/vendor/postitall/jquery-ui.min.js"></script>
  <script src="${root}/vendor/postitall/jquery.postitall.js"></script>
  <script src="${root}/vendor/postitall/jquery.minicolors.js"></script>
  <script src="${root}/vendor/postitall/jquery-ui-timepicker-addon.js"></script>
  
</head>
<body>
<!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
	<%@include file = "../html/sidebar.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
		<%@include file = "../html/topbar.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="card shadow mb-4">
            <div class="card-header h3 mb-4 text-gray-800">
                Profile Settings
              </div>
            <div class="card-body">

                <div class="row">
                    <!-- left form column -->
                    <div class="col-md-6 col-sm-6 col-xs-12 personal-info">
                      <form class="form-horizontal" action="${root}/UpdateUserServlet" method="POST">
                        <div class="form-group">
                          <label class="col-lg-3 control-label">First name:</label>
                          <div class="col-lg-8">
                            <input class="form-control" style="text-transform: capitalize" value="${sessionScope.firstName }" type="text" name="firstName">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-lg-3 control-label">Last name:</label>
                          <div class="col-lg-8">
                            <input class="form-control" style="text-transform: capitalize" value="${sessionScope.lastName }" type="text" name="lastName">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-lg-3 control-label">Email:</label>
                          <div class="col-lg-8">
                            <input class="form-control" value="${sessionScope.email }" type="text" name="email">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-md-3 control-label"></label>
                          <div class="col-md-8">
                            <input class="btn btn-primary" value="Save Changes" type="Submit">
                            <span></span>
                            <input class="btn btn-primary" value="Cancel" type="reset">
                          </div>
                        </div>
                      </form>
                    </div>

                    <!-- right form column -->
                    <div class="col-md-6 col-sm-6 col-xs-12 personal-info">
                      <form class="form-horizontal" role="form" action="${root}/ChangePasswordServlet" method="POST">
                        <div class="form-group">
                          <label class="col-md-4 control-label">Old Password:</label>
                          <div class="col-lg-8">
                            <input class="form-control" type="password" name="oldpassword">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-md-4 control-label">New Password:</label>
                          <div class="col-lg-8">
                            <input class="form-control" type="password" name="newpassword">
                          </div>
                        </div>
						<div class="form-group">
                          <label class="col-md-4 control-label">Confirm New Password:</label>
                          <div class="col-lg-8">
                            <input class="form-control" type="password" name="cnewpassword">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-md-4 control-label"></label>
                          <div class="col-md-8">
                            <input class="btn btn-primary" value="Save Changes" type="Submit">
                            <span></span>
                            <input class="btn btn-primary" value="Cancel" type="reset">
                          </div>
                        </div>
                      </form>
                    </div>

                  </div>

            </div>
          </div>

      </div>
      <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->

    <!-- Footer -->
	<%@include file = "../html/footer.jsp" %>

  </div>
  <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Logout Modal-->
  <%@include file = "../html/modals/logoutmodal.jsp" %>
  
</body>

<!-- Javascript for NOTES -->
<script src="${root}/js/postitall.js"></script>

</html>