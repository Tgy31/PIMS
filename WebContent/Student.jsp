<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<form class="form-horizontal" role="form">

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
		      <input type="text" class="form-control" id="inputFirstName" placeholder="First name" value="${ student.getFirst_name() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputLastName" class="col-sm-2 control-label">Last name</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputLastName" placeholder="Last name" value="${ student.getLast_name() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="inputEmail" placeholder="email@example.com" value="${ student.getEmail() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Unavailability</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">3 hours</p>
		      <p><a href="${ rootPath }/availability?type=student&id=${ student.getStudent_id() }">Show</a> | <a href="${ rootPath }/availability?type=student&id=${ student.getStudent_id() }">Edit</a></p>
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
		      <input type="text" class="form-control" id="inputTitle" placeholder="Title" value="${ student.getProject_title() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputDescription" class="col-sm-2 control-label">Description</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" rows="2">${ student.getProject_description() }</textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Keywords</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">5 keywords</p>
		      <p><a href="#">Show</a> | <a href="#">Edit</a></p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputSupervisor" class="col-sm-2 control-label">Supervisor</label>
		    <div class="col-sm-10">
		      <select class="form-control">
			    <option>John Smith</option>
			    <option>Santa Claus</option>
			  </select>
		    </div>
		  </div>
  		  
	  </div> <!-- panel body -->
	</div> <!-- panel -->
  
  <div class="form-group">
    <div class="pull-right">
      <button type="submit" class="btn btn-default margin-14">Cancel</button>
      <button type="submit" class="btn btn-primary margin-14">Save</button>
    </div>
  </div>
</form>

<%@ include file="Footer.jsp" %>