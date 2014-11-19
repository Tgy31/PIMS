<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

	<h2>Inspectors</h2>
        
	${ message }
	
	<table id="inspector-table" class="user-table">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Title</th>
                <th>Email</th>
            </tr>
        </thead>
    </table>

<%@ include file="Footer.jsp" %>