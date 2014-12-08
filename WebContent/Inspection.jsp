<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>



<c:if test="${ !empty sessionScope.alertType }">

	<div id="json-variables">
	[
	<c:forEach items="${ keywords }" var="keyword" varStatus="keywordStatus">
		{
			"id": "${ keyword.getKeyword_id() }",
			"name": "${ keyword.getKeyword_name() }"
		}
		<c:if test="${ keywordStatus.index < keywords.size() - 1 }">
		,
		</c:if>
	</c:forEach>
	]
	
	</div>
	
	<form class="form-horizontal" role="form" method="post">
	
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Project informations</h3>
		  </div>
		  <div class="panel-body">
	  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Student</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ student.getModule_id() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Title</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ student.getModule_id() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Description</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ student.getModule_id() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Keywords</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ student.getModule_id() }</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Supervisor</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">${ student.getModule_id() }</p>
			    </div>
			  </div>
				
			</div> <!-- end panel body -->
		</div>
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">First inspector</h3>
		  </div>
		  <div class="panel-body">
			  Select an inspector below to be the first inspector.
			</div> <!-- end panel body -->
			<table class="table">
				<thead>
					<tr>
						<th class="radio-cell">Selection</th>
						<th>Inspector</th>
						<th>Matching</th>
						<th>Capacity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ suggestedInspectors }" var="inspector">	
						<tr>
							<td class="radio-cell">
								<input type="radio" name="inputFirstInspector">
							</td>
							<td>
								${ inspector.getFirst_name() }
							</td>
							<td>
								Mobile, network
							</td>
							<td>
								3/8
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="radio-cell last">
							<input type="radio" name="inputFirstInspector">
						</td>
						<td colspan="3">
							<select  class="form-control">
								<option>Other inspector</option>
								<c:forEach items="${ otherInspectors }" var="inspector">
									<option>${ inspector.getFirst_name() }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Second inspector</h3>
		  </div>
		  <div class="panel-body">
			  Select an inspector below to be the second inspector.
			</div> <!-- end panel body -->
			<table class="table">
				<thead>
					<tr>
						<th class="radio-cell">Selection</th>
						<th>Inspector</th>
						<th>Matching</th>
						<th>Capacity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ suggestedInspectors }" var="inspector">	
						<tr>
							<td class="radio-cell">
								<input type="radio" name="inputSecondInspector">
							</td>
							<td>
								${ inspector.getFirst_name() }
							</td>
							<td>
								Mobile, network
							</td>
							<td>
								3/8
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="radio-cell last">
							<input type="radio" name="inputSecondInspector">
						</td>
						<td colspan="3">
							<select  class="form-control">
								<option>Other inspector</option>
								<c:forEach items="${ otherInspectors }" var="inspector">
									<option>${ inspector.getFirst_name() }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Date</h3>
		  </div>
		  <div class="panel-body">
			<div id="calendar"></div>
			<br />
			<br />	
			</div> <!-- end panel body -->
				
			<ul class="list-group" data-bind="foreach: keywords">
			    <li class="list-group-item">
			    	<span data-bind="text: name">
			    	</span>
			    	<button type="button" class="close" data-bind="click: $root.removeKeyword"><span aria-hidden="true">&times;</span><span class="sr-only">Remove</span></button>
			    </li>
			</ul>
		</div>
	  
	  <div class="form-group">
	    <div class="pull-right">
	      <button type="submit" id="submitButton" class="btn btn-primary margin-14">Save</button>
	    </div>
	  </div>
	</form>
</c:if>

<%@ include file="Footer.jsp" %>