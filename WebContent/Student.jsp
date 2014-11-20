<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<ol class="breadcrumb">
  <li><a href="#">${ moduleSlug }</a></li>
  <li><a href="#">Students</a></li>
  <li class="active">${ student.getFirst_name() }</li>
</ol>

<div class="row">
  <div class="col-xs-12 col-lg-12">
    <div class="thumbnail">
      <div class="caption">
		<form class="form-horizontal" role="form">
		
		  <h2>${ student.getFirst_name() } ${ student.getLast_name() }</h2>
		  <br />
		
		  <h3>Student</h3>
		
		  <hr>
		  
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
		      <p><a href="#">Show</a> | <a href="#">Edit</a></p>
		    </div>
		  </div>
		  
		  <br />
		  <h3>Project</h3>
		  
		  <hr>
		  
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
		    <label for="inputSupervisor" class="col-sm-2 control-label">Supervisor</label>
		    <div class="col-sm-10">
		      <select class="form-control">
			    <option>John Smith</option>
			    <option>Santa Claus</option>
			  </select>
		    </div>
		  </div>		  
		  
		  <br />
		  <br />
		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Save</button>
		      <button type="submit" class="btn btn-default">Cancel</button>
		    </div>
		  </div>
		</form>
      </div>
    </div>
  </div>
</div>

<%@ include file="Footer.jsp" %>