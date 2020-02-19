<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />

<!-- Topbar -->
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">

		<!-- DYNAMIC NOTES POSTITALL-->
		<li class="nav-item"><a class="nav-link"> <span
				class="mr-2 d-lg-inline text-gray-600 small">

					<button class="btn btn-primary" id="postItAll">Add a Note</button>

			</span>
		</a></li>

		<div class="topbar-divider d-sm-block"></div>

		<!-- Nav Item - User Information -->
		<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="userDropdown"
			role="button" data-toggle="dropdown"> 
			<span class="mr-2 d-lg-inline text-gray-600 small" style="text-transform: capitalize">
			
				${sessionScope.firstName} ${sessionScope.lastName}

			</span>
		</a> <!-- Dropdown - User Information -->
			<div
				class="dropdown-menu dropdown-menu-right shadow animated--grow-in">

				<a class="dropdown-item" href="./settings.jsp"> <i
					class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> Settings
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#logoutModal"> <i
					class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
					Logout
				</a>
			</div></li>

	</ul>

</nav>
<!-- End of Topbar -->

