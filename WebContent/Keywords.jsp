<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Keywords for ${ user.getFirst_name() }</h3>
	</div>
	<div class="panel-body">
	
	</div>
	<ul class="list-group" data-bind="foreach: seats">
	    <li class="list-group-item">
	    	<span data-bind="text: name">
	    	</span>
	    </li>
	</ul>
</div>

<%@ include file="Footer.jsp" %>