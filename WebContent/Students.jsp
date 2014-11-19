<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

	<h2>Students</h2>
        
	${ message }
	
	<table id="student-table" class="user-table row-border hover order-column">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
        </thead>
    </table>

<%@ include file="Footer.jsp" %>