<!--
* Filename: updatemodal.jsp
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
-->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
    
  <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
      
        <div class="modal-header">
          <h5 class="modal-title">Notice</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        
        <div class="modal-body">Updated successfully</div>
        
        <div class="modal-footer">
          <button class="btn btn-primary" type="button" data-dismiss="modal" aria-label="Close">
            OK
          </button>
        </div>
        
      </div>
    </div>
  </div>