<!--
* Filename: candidates.jsp
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
-->
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

  <title>Candidates</title>

  <!-- Custom fonts for this template-->
  <link href="${root}/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
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

  <!--DataTables-->
  <link rel="stylesheet" type="text/css" href="${root}/vendor/datatables/datatables.min.css" />
  <script src="${root}/vendor/datatables/datatables.min.js"></script>
  
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

          <div class="card shadow mb-4">
            <div class="card-header h3 mb-4 text-gray-800">
              Candidates
            </div>
            <div class="card-body" style="color: #858796">
              <div class="table-responsive">
                <table class="table table-bordered" id="candidateTable" style="color: #858796">
					<thead>
						<tr>
							<th>Name</th>
							<th>Company</th>
							<th>Position</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Resume</th>
							<th>Edit / Delete </th>
						</tr>
					</thead>
                </table>
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

<!-- Javascript for NOTES/DATATABLES -->
<script src="${root}/js/candidatetable.js"></script>
<script src="${root}/js/postitall.js"></script>

</html>