<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div class="row">
  <div class="col-xs-12 col-lg-12">
    <div class="thumbnail">
      <div class="caption">
        <h3>${ module }</h3>
        <hr/>
		<form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label for="inputName" class="col-sm-2 control-label">Name</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputName" placeholder="Name" value="${ module }">
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