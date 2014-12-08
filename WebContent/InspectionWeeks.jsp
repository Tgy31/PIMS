<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div id="json-variables">
	{
		"moduleID": ${ selectedModule.getModule_id() }
	}
</div>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Inspection weeks for ${ selectedModule.getModule_name() }</h3>
	</div>
	<div class="panel-body">
	
		<table id="inspection-week-table" class="user-table row-border hover order-column">
	        <thead>
	            <tr>
	                <th>Title</th>
	                <th>Start</th>
	                <th>Action</th>
	            </tr>
	        </thead>
	    </table>
	    
	</div>
</div>

<%@ include file="Footer.jsp" %>