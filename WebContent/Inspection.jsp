<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>



<c:if test="${ empty sessionScope.alertType }">

	<div id="json-variables">
	{
		"firstInspectorID": ${ firstInspector },
		"secondInspectorID": ${ secondInspector },
		"studentID": ${ student.getStudent_id() },
		"supervisorID": ${ supervisor.getInspector_id() },
		<c:if test="${ inspection.isDateSet() }">
		"inspectionStart": "${ inspection.getFormattedStartDate() }",
		"inspectionEnd": "${ inspection.getFormattedEndDate() }",
		</c:if>
		"firstDay": "${ inspectionWeek.getFormattedStartDate() }",
		"suggestedInspectors": [
		<c:forEach items="${ suggestedInspectors }" var="inspector" varStatus="inspectorStatus">
			{
				"id": ${ inspector.getInspector_id() },
				"name": "${ inspector.getFullName() }",
				"username": "${ inspector.getUsername() }",
				"keywords": "${ servlet.matchedKeywords(student, inspector) }",
				"load": ${ servlet.loadForInspector(inspector, inspection) },
				"capacity": ${ inspector.getCapacity() },
				"suggested": true
			}
			<c:if test="${ inspectorStatus.index < suggestedInspectors.size() - 1 }">
			,
			</c:if>
		</c:forEach>
		],
		"otherInspectors": [
		<c:forEach items="${ otherInspectors }" var="inspector" varStatus="inspectorStatus">
			{
				"id": ${ inspector.getInspector_id() },
				"name": "${ inspector.getFullName() }",
				"username": "${ inspector.getUsername() }",
				"keywords": "${ servlet.matchedKeywords(student, inspector) }",
				"load": ${ servlet.loadForInspector(inspector, inspection) },
				"capacity": ${ inspector.getCapacity() },
				"suggested": false
			}
			<c:if test="${ inspectorStatus.index < otherInspectors.size() - 1 }">
			,
			</c:if>
		</c:forEach>
		]
	}
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
	    	<h3 class="panel-title">Project informations</h3>
	  	</div>
	  	<div class="panel-body">
  			<form class="form-horizontal">
				<div class="form-group">
				  	<label class="col-sm-2 control-label">Student</label>
				    <div class="col-sm-10">
				    	<p class="form-control-static">${ student.getFullName() }</p>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-sm-2 control-label">Title</label>
				    <div class="col-sm-10">
				    	<p class="form-control-static">${ student.getProject_title() }</p>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-sm-2 control-label">Description</label>
				    <div class="col-sm-10">
				    	<p class="form-control-static">${ student.getProject_description() }</p>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-sm-2 control-label">Keywords</label>
				    <div class="col-sm-10">
				    	<p class="form-control-static">${ keywords }</p>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-sm-2 control-label">Supervisor</label>
				    <div class="col-sm-10">
				    	<p class="form-control-static">${ supervisor.getFullName() }</p>
					</div>
				</div>
			</form>
		</div> <!-- end panel body -->
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
	    	<h3 class="panel-title">First inspector</h3>
	  	</div>
	  	<div class="panel-body">
		  	<p>Select an inspector below to be the first inspector.</p>
  			<div class="checkbox">
  				<label>
					<input type="checkbox" data-bind="checked: showOverloaded"> <i>Show overloaded inspectors</i>
				</label>
			</div>
		</div> <!-- end panel body -->
		<table class="table">
			<thead>
				<tr>
					<th class="radio-cell">Suggested</th>
					<th>Name</th>
					<th>Matched keywords</th>
					<th>Capacity</th>
				</tr>
			</thead>
			<tbody>
				<!-- ko foreach: suggestedInspectors -->	
					<tr>
						<td class="radio-cell">
							<input type="radio" name="inputFirstInspector" data-bind="checkedValue: $data, checked: $root.firstInspector">
						</td>
						<td data-bind="text: name"></td>
						<td data-bind="text: keywords"></td>
						<td data-bind="text: formattedCapacity()"></td>
					</tr>
				<!-- /ko -->
				<tr data-bind="visible: suggestedInspectors().length == 0">
					<td></td>
					<td><i>No suggested inspector<i></i></td>
					<td></td>
					<td></td>
				</tr>					
				<thead>
					<th class="radio-cell">Other</th>
					<th></th>
					<th></th>
					<th></th>
				</thead>
				<tr>
					<td class="radio-cell cell-lg">
						<input type="radio" name="inputFirstInspector" data-bind="checkedValue: $root.firstOtherInspector(), checked: $root.firstInspector">
					</td>
					<td>
						<select  class="form-control" data-bind="options: otherInspectors,
																value: firstOtherInspector,
																optionsText: 'name'">
						</select>
					</td>
					<td class="cell-lg" data-bind="text: firstOtherInspector().keywords"></td>
					<td class="cell-lg" data-bind="text: firstOtherInspector().formattedCapacity()"></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">Second inspector</h3>
	  </div>
	  <div class="panel-body">
		  	<p>Select an inspector below to be the second inspector.</p>
  			<div class="checkbox">
  				<label>
					<input type="checkbox" data-bind="checked: showOverloaded"> <i>Show overloaded inspectors</i>
				</label>
			</div>
		</div> <!-- end panel body -->
		<table class="table">
			<thead>
				<tr>
					<th class="radio-cell">Suggested</th>
					<th>Name</th>
					<th>Matched keywords</th>
					<th>Capacity</th>
				</tr>
			</thead>
			<tbody>
				<!-- ko foreach: suggestedInspectors -->	
					<tr>
						<td class="radio-cell">
							<input type="radio" name="inputSecondInspector" data-bind="checkedValue: $data, checked: $root.secondInspector">
						</td>
						<td data-bind="text: name"></td>
						<td data-bind="text: keywords"></td>
						<td data-bind="text: formattedCapacity()"></td>
					</tr>
				<!-- /ko -->
				<tr data-bind="visible: suggestedInspectors().length == 0">
					<td></td>
					<td><i>No suggested inspector<i></i></td>
					<td></td>
					<td></td>
				</tr>
				<thead>
					<th class="radio-cell">Other</th>
					<th></th>
					<th></th>
					<th></th>
				</thead>
				<tr>
					<td class="radio-cell cell-lg">
						<input type="radio" name="inputSecondInspector" data-bind="checkedValue: $root.secondOtherInspector(), checked: $root.secondInspector">
					</td>
					<td>
						<select  class="form-control" data-bind="options: otherInspectors,
																value: secondOtherInspector,
																optionsText: 'name'">
						</select>
					</td>
					<td class="cell-lg" data-bind="text: secondOtherInspector().keywords"></td>
					<td class="cell-lg" data-bind="text: secondOtherInspector().formattedCapacity()"></td>
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
	  </div> <!-- end panel body -->
			
		<table class="table">
			<thead>
				<tr>
					<th>Person</th>
					<th>Role</th>
					<th>Available</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-info">
				    	<span class="glyphicon glyphicon-calendar"></span>
				    	<span>${ student.getFullName() }</span>
				    </td>
				    <td>
				    	Student
				    </td>
				    <td>
				    	<span class="glyphicon glyphicon-ok text-success" data-bind="visible: studentIsAvailable()"></span>
				    	<span class="glyphicon glyphicon-remove text-danger" data-bind="visible: !studentIsAvailable()"></span>
				    </td>
				</tr>
				<tr>
					<td class="text-danger">
				    	<span class="glyphicon glyphicon-calendar"></span>
				    	<span>${ supervisor.getFullName() }</span>
				    </td>
				    <td>
				    	Supervisor
				    </td>
				    <td>
				    	<span class="glyphicon glyphicon-ok text-success" data-bind="visible: supervisorIsAvailable()"></span>
				    	<span class="glyphicon glyphicon-remove text-danger" data-bind="visible: !supervisorIsAvailable()"></span>
				    </td>
				</tr>
				<tr data-bind="with: firstInspector">
					<td class="text-warning">
				    	<span class="glyphicon glyphicon-calendar"></span>
				    	<span data-bind="text: name"></span>
				    </td>
				    <td>
				    	First inspector
				    </td>
				    <td>
				    	<span class="glyphicon glyphicon-ok text-success" data-bind="visible: $root.firstInspectorIsAvailable()"></span>
				    	<span class="glyphicon glyphicon-remove text-danger" data-bind="visible: !$root.firstInspectorIsAvailable()"></span>
				    </td>
				</tr>
				<tr data-bind="with: secondInspector">
					<td class="text-success">
				    	<span class="glyphicon glyphicon-calendar"></span>
				    	<span data-bind="text: name"></span>
				    </td>
				    <td>
				    	Second inspector
				    </td>
				    <td>
				    	<span class="glyphicon glyphicon-ok text-success" data-bind="visible: $root.secondInspectorIsAvailable()"></span>
				    	<span class="glyphicon glyphicon-remove text-danger" data-bind="visible: !$root.secondInspectorIsAvailable()"></span>
				    </td>
				</tr>
			</tbody>
		</table>
	</div>
  
<div id="alert-zone">
</div>

  <div class="form-group">
    <div class="pull-right">
      <button class="btn btn-primary margin-14" data-bind="click: submitInspection">Save</button>
      <br />
      <br />
    </div>
  </div>
</c:if>

<%@ include file="Footer.jsp" %>