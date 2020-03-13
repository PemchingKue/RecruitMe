<!--
* Filename: dashboard.jsp
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

  <title>Dashboard</title>

  <!-- Custom fonts for this template-->
  <link href="${root}/vendor/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- CSS-->
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

	<!-- Side Bar -->
    <%@include file = "../html/sidebar.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Top Bar -->
		<%@include file = "../html/topbar.jsp" %>
		
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">Dashboard</h1>


          <div class="row">
            <!-- Notes -->
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4">
                <!-- Card Header-->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Notes</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <textarea class="notes col-xl-12"></textarea>
                </div>
              </div>
            </div>

            <!-- Calendar -->
            <div class="col-xl-8 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header-->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Calendar</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div id="dashboardCalendar" style="color: #858796"></div>
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
  
  <!-- Modals -->
  <%@include file = "../html/modals/logoutmodal.jsp" %>
  <%@include file = "../html/modals/calendarmodal.jsp" %>
  <%@include file = "../html/modals/createmodal.jsp" %>
  <%@include file = "../html/modals/updatemodal.jsp" %>
  <%@include file = "../html/modals/deletemodal.jsp" %>
  
</body>

<!-- Javascript for NOTES and CALENDAR -->
<script src="${root}/js/postitall.js"></script>
<script src="${root}/js/notes.js"></script>
<script src="${root}/js/dashboardcalendar.js"></script>

</html>