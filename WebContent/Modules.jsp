<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Modules</h3>
	</div>
	<div class="panel-body">
	
		<table id="module-table" class="user-table row-border hover order-column">
	        <thead>
	            <tr>
	                <th>Name</th>
	                <th>Year</th>
	                <th>Active</th>
	                <th>Action</th>
	            </tr>
	        </thead>
	    </table>
	    
	</div>
</div>
    
    <!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body" id="modal-text">
	        ...
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal" id="modal-decline">Close</button>
	        <button type="button" class="btn btn-primary" id="modal-confirm">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>

<%@ include file="Footer.jsp" %>