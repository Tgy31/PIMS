<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Students from ${ selectedModule.getModule_name() }</h3>
	</div>
	<div class="panel-body">
	
		<table id="student-table" class="user-table row-border hover order-column">
	        <thead>
	            <tr>
	                <th>First Name</th>
	                <th>Last Name</th>
	                <th>Email</th>
	                <th>Action</th>
	            </tr>
	        </thead>
	    </table>
	    
	</div>
</div>

<%@ include file="Footer.jsp" %>