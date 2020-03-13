<!--
* Filename: calendarmodal.jsp
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
-->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />

<div class="modal fade" id="calendarModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Create new event</h5>
				<button class="close calendarCloseButton" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<form id="calendarForm">
			<div class="modal-body">
			
				<div class="row">
					<div class="col-md-12 center-block text-center">
						<label class="col-xs-4" for="title">Event title:</label> <input
							type="text" class="form-control text-center" name="title"
							id="title" />
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 center-block text-center">
						<label class="col-xs-4" for="starts-at">Starts at:</label> <input
							type="text" class="form-control text-center" name="starts_at"
							id="starts-at" />
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 center-block text-center">
						<label class="col-xs-4" for="ends-at">Ends at:</label> <input
							type="text" class="form-control text-center" name="ends_at"
							id="ends-at" />
					</div>
				</div>
			</div>
		
			<div class="modal-footer">
				<button class="btn btn-secondary calendarCloseButton" type="button" data-dismiss="modal">Cancel</button>		
				<input class="btn btn-primary" id="oButton" type="submit" value="Create" />
			</div>
		</form>
		</div>
	</div>
</div>

