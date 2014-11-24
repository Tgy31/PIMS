<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<form class="form-horizontal" role="form">

	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Module</h3>
	  </div>
	  <div class="panel-body">
  
		  <div class="form-group">
		    <label for="inputTitle" class="col-sm-2 control-label">Name</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputTitle" placeholder="Title" value="${ module.getModule_name() }">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> active
		        </label>
		      </div>
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

<form class="form-horizontal" role="form" enctype="multipart/form-data" method="post">

	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Import students</h3>
	  </div>
	  <div class="panel-body">
  
		  <div class="form-group">
		    <label for="inputTitle" class="col-sm-2 control-label">File</label>
		    <div class="col-sm-10">
		      <input type="file" class="form-control" id="inputStudentFile" placeholder="students.csv" name="inputStudentFile">
		    </div>
		  </div>
  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		    	<button type="submit" class="btn btn-primary margin-14">Import</button>
		    </div>
		  </div>
  
	  </div> <!-- panel body -->
	</div> <!-- panel -->
</form>

<form class="form-horizontal" role="form" enctype="multipart/form-data" method="post">

	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Import inspectors</h3>
	  </div>
	  <div class="panel-body">
  
		  <div class="form-group">
		    <label for="inputTitle" class="col-sm-2 control-label">File</label>
		    <div class="col-sm-10">
		      <input type="file" class="form-control" id="inputInspectorFile" placeholder="inspectors.csv" name="inputInspectorFile">
		    </div>
		  </div>
  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		    	<button type="submit" class="btn btn-primary margin-14">Import</button>
		    </div>
		  </div>
  
	  </div> <!-- panel body -->
	</div> <!-- panel -->
</form>

<%@ include file="Footer.jsp" %>