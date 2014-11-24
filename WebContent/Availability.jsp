<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Unavailability for ${ user.getUsername() }</h3>
	</div>
	<div class="panel-body">
	
		<div id='calendar'></div>
	    
	</div>
</div>

<%@ include file="Footer.jsp" %>