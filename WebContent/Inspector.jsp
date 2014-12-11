<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>
<c:if test="${ !empty inspector }">

	<form class="form-horizontal" role="form" method="post">
	
	
	<c:choose>
	    <c:when test="${ sessionScope.user.isCoordinator() }">
	    
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Inspection weeks</h3>
		  </div>
		  <div class="panel-body">
	  		  <p>Inspection weeks can be edited in the <b>Inspections</b> menu.</p>
		  </div> <!-- panel body -->
		  
		  <table class="table">
		  	<thead>
			  	<tr>
			  		<th>Inspection</th>
			  		<th>Date</th>
			  		<th>Availability</th>
			  	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${ inspectionWeeks }" var="inspectionWeek" varStatus="i">
			  		<tr>
			  			<td>${ inspectionWeek.getInspection_title() }</td>
			  			<td>${ inspectionWeek.getFormattedStartDate() }</td>
			  			<td>
			  				<a href="${ rootPath }availability/?type=inspector&id=${ inspector.getInspector_id() }&week=${ inspectionWeek.getInspectionweek_id() }">Edit</a>
			  			</td>
			  		</tr>
		  		</c:forEach>
		  	</tbody>
		  </table>
		</div> <!-- panel -->
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Inspections</h3>
		  </div>
		  <div class="panel-body">
		  		
		  </div> <!-- panel body -->
		  
		  <table class="table">
		  	<thead>
			  	<tr>
			  		<th>Student</th>
			  		<th>Project</th>
			  		<th>Role</th>
			  		<th>Date</th>
			  		<th>Action</th>
			  	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${ inspections }" var="inspection" varStatus="i">
					<c:set var="student" value="${ servlet.studentForInspection(inspection) }"/>
			  		<tr>
			  			<td>${ student.getFullName() }</td>
			  			<td>${ student.getProject_title() }</td>
			  			<td>${ servlet.roleForInspectorInInspection(inspector, inspection) }</td>
			  			<td>${ inspection.getDisplayableDate() }</td>
			  			<td>
			  				<a href="${ rootPath }inspections/${ student.getModule_id() }/${ inspection.getInspectionweek_id() }/${ student.getStudent_id() }/">Edit</a>
			  			</td>
			  		</tr>
		  		</c:forEach>
		  	</tbody>
		  </table>
		</div> <!-- panel -->
	
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Inspector</h3>
		  </div>
		  <div class="panel-body">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Username</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ inspector.getUsername() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputFirstName" class="col-sm-2 control-label">First name</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First name" value="${ inspector.getFirst_name() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputLastName" class="col-sm-2 control-label">Last name</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last name" value="${ inspector.getLast_name() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="email@example.com" value="${ inspector.getEmail() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail" class="col-sm-2 control-label">Password</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="password" value="${ inspector.getPassword() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail" class="col-sm-2 control-label">Capacity</label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="inputCapacity" name="inputCapacity" value="${ inspector.getCapacity() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Actual load</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ load }/${ inspector.getCapacity() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Keywords</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">
			      	<a href="${ rootPath }keywords/?type=inspector&id=${ inspector.getInspector_id() }">Show</a>
			      </p>
			    </div>
			  </div>
	  
		  </div> <!-- panel body -->
		</div> <!-- panel -->
		
		</c:when>
		<c:otherwise>
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Inspection weeks</h3>
		  </div>
		  <div class="panel-body">
	  		  <p>Inspection weeks can be edited in the <b>Inspections</b> menu.</p>
		  </div> <!-- panel body -->
		  
		  <table class="table">
		  	<thead>
			  	<tr>
			  		<th>Inspection</th>
			  		<th>Date</th>
			  		<th>Availability</th>
			  	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${ inspectionWeeks }" var="inspectionWeek" varStatus="i">
			  		<tr>
			  			<td>${ inspectionWeek.getInspection_title() }</td>
			  			<td>${ inspectionWeek.getFormattedStartDate() }</td>
			  			<td>
			  				<a href="${ rootPath }availability/?type=inspector&id=${ inspector.getInspector_id() }&week=${ inspectionWeek.getInspectionweek_id() }">Edit</a>
			  			</td>
			  		</tr>
		  		</c:forEach>
		  	</tbody>
		  </table>
		</div> <!-- panel -->
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Inspections</h3>
		  </div>
		  <div class="panel-body">
		  </div> <!-- panel body -->
		  
		  <table class="table">
		  	<thead>
			  	<tr>
			  		<th>Student</th>
			  		<th>Project</th>
			  		<th>Role</th>
			  		<th>Date</th>
			  		<th>Action</th>
			  	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${ inspections }" var="inspection" varStatus="i">
					<c:set var="student" value="${ servlet.studentForInspection(inspection) }"/>
			  		<tr>
			  			<td>${ student.getFullName() }</td>
			  			<td>${ student.getProject_title() }</td>
			  			<td>${ servlet.roleForInspectorInInspection(inspector, inspection) }</td>
			  			<td>${ inspection.getDisplayableDate() }</td>
			  			<td>
			  				<a href="${ rootPath }inspections/${ student.getModule_id() }/${ inspection.getInspectionweek_id() }/${ student.getStudent_id() }/">View</a>
			  			</td>
			  		</tr>
		  		</c:forEach>
		  	</tbody>
		  </table>
		</div> <!-- panel -->
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Inspector</h3>
		  </div>
		  <div class="panel-body">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Username</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ inspector.getUsername() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputFirstName" class="col-sm-2 control-label">First name</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First name" value="${ inspector.getFirst_name() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputLastName" class="col-sm-2 control-label">Last name</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last name" value="${ inspector.getLast_name() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="email@example.com" value="${ inspector.getEmail() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail" class="col-sm-2 control-label">Password</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="password" value="${ inspector.getPassword() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail" class="col-sm-2 control-label">Capacity</label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="inputCapacity" name="inputCapacity" value="${ inspector.getCapacity() }" disabled>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Actual load</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ load }/${ inspector.getCapacity() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Keywords</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">
			      	<a href="${ rootPath }keywords/?type=inspector&id=${ inspector.getInspector_id() }">Show</a>
			      </p>
			    </div>
			  </div>
	  
		  </div> <!-- panel body -->
		</div> <!-- panel -->
		
		</c:otherwise>
	</c:choose>
		
	  
	  <div class="form-group">
	    <div class="pull-right">
	      <button type="submit" class="btn btn-primary margin-14">Save</button>
	    </div>
	  </div>
	</form>
	
</c:if>

<%@ include file="Footer.jsp" %>