<!--
* Filename: sidebar.jsp
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
-->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />

<!-- Sidebar -->
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center"
		href="./dashboard.jsp">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="far fa-registered"></i>
		</div>
		<div class="sidebar-brand-text mx-3">RecruitMe</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->
	<li class="nav-item"><a class="nav-link" href="./dashboard.jsp">
			<i class="fas fa-columns"></i> <span style="margin-left: 4px;">Dashboard</span>
	</a> <a class="nav-link" href="./clients.jsp"> <i
			class="fas fa-user-tag"></i> <span>Clients</span></a> <a class="nav-link"
		href="./candidates.jsp"> <i class="fas fa-users"></i> <span>Candidates</span></a>

		<a class="nav-link" href="./schedule.jsp"> <i
			class="far fa-calendar-alt"></i> <span style="margin-left: 5px;">Schedule</span></a>

		<a class="nav-link" href="./settings.jsp"> <i class="fas fa-cogs"></i>
			<span>Settings</span></a></li>

	<!-- Divider -->
	<hr class="sidebar-divider">

</ul>
<!-- End of Sidebar -->
