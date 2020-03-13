<!--
* Filename: schedule.jsp
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

  <title>Schedule</title>

  <!-- Custom fonts for this template-->
  <link href="${root}/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <link href="${root}/css/main.css" rel="stylesheet">

  <!-- BootStrap -->
  <link href="${root}/bootstrap/bootstrap.min.css" rel="stylesheet" />
  <script src="${root}/bootstrap/jquery-3.4.1.js"></script>
  <script src="${root}/bootstrap/bootstrap.min.js"></script>

  <!-- Calendar CSS and JS-->
  <link href="${root}/vendor/fullcalendar/packages/core/main.css" rel="stylesheet" />
  <link href="${root}/vendor/fullcalendar/packages/daygrid/main.css" rel="stylesheet" />
  <link href="${root}/vendor/fullcalendar/packages/bootstrap/main.css" rel="stylesheet" />
  <link href="${root}/vendor/fullcalendar/packages/timegrid/main.css" rel="stylesheet" />

  <script src="${root}/vendor/fullcalendar/packages/core/main.js"></script>
  <script src='${root}/vendor/fullcalendar/packages/moment/main.js'></script>
  <script src="${root}/vendor/fullcalendar/packages/daygrid/main.js"></script>
  <script src="${root}/vendor/fullcalendar/packages/bootstrap/main.js"></script>
  <script src="${root}/vendor/fullcalendar/packages/timegrid/main.js"></script>
  <script src="${root}/vendor/fullcalendar/packages/interaction/main.js"></script>

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
            <div class="card-body">
              <div id="scheduleCalendar" style="color: #858796"></div>
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

  <!-- Modals -->
  <%@include file = "../html/modals/logoutmodal.jsp" %>
  <%@include file = "../html/modals/calendarmodal.jsp" %>
  <%@include file = "../html/modals/createmodal.jsp" %>
  <%@include file = "../html/modals/updatemodal.jsp" %>
  <%@include file = "../html/modals/deletemodal.jsp" %>
  
</body>

<!-- Javascript for NOTES and CALENDAR -->
<script src="${root}/js/postitall.js"></script>
<script src="${root}/js/schedulecalendar.js"></script>

</html>