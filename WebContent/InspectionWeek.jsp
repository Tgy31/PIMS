<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<c:if test="${ empty sessionScope.alertType }">

<div id="json-variables">
	{
		"moduleID": ${ selectedModule.getModule_id() },
		"inspectionWeekID": ${ inspectionWeek.getInspectionweek_id() }
	}
</div>

	<form class="form-horizontal" role="form" method="post">
	<c:choose>
	    <c:when test="${ sessionScope.user.isCoordinator() }">
	
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Inspection Week</h3>
		  </div>
		  <div class="panel-body">
	  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Module</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ inspectionModule.getModule_name() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputTitle" class="col-sm-2 control-label">Title</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputTitle" name="inputTitle" placeholder="Title" value="${ inspectionWeek.getInspection_title() }">
			    </div>
			  </div>
	  
		  </div> <!-- panel body -->
		</div> <!-- panel -->
		
		</c:when>
	</c:choose>
		
	  
	  <div class="form-group">
	    <div class="pull-right">
	      <button type="submit" class="btn btn-primary margin-14">Save</button>
	    </div>
	  </div>
	</form>
	
	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Inspections</h3>
	  </div>
	  <div class="panel-body">
	  
		<table id="inspection-table" class="user-table row-border hover order-column">
	        <thead>
	            <tr>
	                <th>Name</th>
	                <th>Username</th>
	                <th>Inspection</th>
	                <th>Action</th>
	            </tr>
	        </thead>
	    </table>
  
	  </div> <!-- panel body -->
	</div> <!-- panel -->

</c:if>




<%@ include file="Footer.jsp" %>