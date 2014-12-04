<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<form class="form-horizontal" role="form" method="post">


<c:choose>
    <c:when test="${ sessionScope.user.isCoordinator() }">

	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Student</h3>
	  </div>
	  <div class="panel-body">
  
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Module</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${ student.getModule_id() }</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Username</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${ student.getUsername() }</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputFirstName" class="col-sm-2 control-label">First name</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First name" value="${ student.getFirst_name() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputLastName" class="col-sm-2 control-label">Last name</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last name" value="${ student.getLast_name() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="email@example.com" value="${ student.getEmail() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="inputEmail" name="inputPassword" placeholder="password" value="${ student.getPassword() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Unavailability</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">
		      	<a href="${ rootPath }availability/?type=student&id=${ student.getStudent_id() }">Show</a>
		      </p>
		    </div>
		  </div>
  
	  </div> <!-- panel body -->
	</div> <!-- panel -->

	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Project</h3>
	  </div>
	  <div class="panel-body">
  
		  <div class="form-group">
		    <label for="inputTitle" class="col-sm-2 control-label">Title</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputTitle" name="inputTitle" placeholder="Title" value="${ student.getProject_title() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputDescription" class="col-sm-2 control-label">Description</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" name="inputDescription" rows="2">${ student.getProject_description() }</textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Keywords</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">
		      	<a href="${ rootPath }keywords/?type=student&id=${ student.getStudent_id() }">Show</a>
		      </p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputSupervisor" class="col-sm-2 control-label">Supervisor</label>
		    <div class="col-sm-10">
		      <select class="form-control" name="inputSupervisor">
			    <option value="245">John Smith</option>
			    <option value="456">Santa Claus</option>
			  </select>
		    </div>
		  </div>
  		  
	  </div> <!-- panel body -->
	</div> <!-- panel -->
	
	</c:when>
	<c:otherwise>
	
	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Student</h3>
	  </div>
	  <div class="panel-body">
  
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Module</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${ student.getModule_id() }</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Username</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${ student.getUsername() }</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">First name</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${ student.getFirst_name() }</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Last name</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${ student.getLast_name() }</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="email@example.com" value="${ student.getEmail() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="inputEmail" name="inputPassword" placeholder="password" value="${ student.getPassword() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Unavailability</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">
		      	<a href="${ rootPath }availability/?type=student&id=${ student.getStudent_id() }">Show</a>
		      </p>
		    </div>
		  </div>
  
	  </div> <!-- panel body -->
	</div> <!-- panel -->

	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Project</h3>
	  </div>
	  <div class="panel-body">
  
		  <div class="form-group">
		    <label for="inputTitle" class="col-sm-2 control-label">Title</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputTitle" name="inputTitle" placeholder="Title" value="${ student.getProject_title() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputDescription" class="col-sm-2 control-label">Description</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" name="inputDescription" rows="2">${ student.getProject_description() }</textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Keywords</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">
		      	<a href="${ rootPath }keywords/?type=student&id=${ student.getStudent_id() }">Show</a>
		      </p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputSupervisor" class="col-sm-2 control-label">Supervisor</label>
		    <div class="col-sm-10">
		      <select class="form-control" name="inputSupervisor" disabled>
			    <option value="245">John Smith</option>
			    <option value="456">Santa Claus</option>
			  </select>
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

<%@ include file="Footer.jsp" %>