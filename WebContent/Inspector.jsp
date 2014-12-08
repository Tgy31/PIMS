<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>
<c:if test="${ empty sessionScope.alertType }">

	<form class="form-horizontal" role="form" method="post">
	
	
	<c:choose>
	    <c:when test="${ sessionScope.user.isCoordinator() }">
	
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
			      <input type="password" class="form-control" id="inputEmail" name="inputPassword" placeholder="password" value="${ inspector.getPassword() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Unavailability</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">
			      	<a href="${ rootPath }availability/?type=inspector&id=${ inspector.getInspector_id() }">Show</a>
			      </p>
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
			      <input type="password" class="form-control" id="inputEmail" name="inputPassword" placeholder="password" value="${ inspector.getPassword() }">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Unavailability</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">
			      	<a href="${ rootPath }availability/?type=inspector&id=${ inspector.getInspector_id() }">Show</a>
			      </p>
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