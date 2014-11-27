<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div id="alert-zone">
</div>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Availability for ${ user.getFirst_name() }</h3>
	</div>
	<div class="panel-body">

		<div id="calendar"></div>
		<br />
		
		<br />
		<p data-bind="visible: slots().length > 0">
		    You have selected <span data-bind="text: slots().length"></span> keywords:
		</p>
		<p data-bind="visible: slots().length == 0">
		    <i>Select a keyword and click "Add keyword".</i>
		</p>
	
		<div class="progress">
			<div class="progress-bar progress-bar-warning" role="progressbar" style="width: 60%;">
				60%
			</div>
		</div>
		
	</div> <!-- end panel body -->
	
	<table class="table">
		<thead>
			<tr>
				<th>Title</th>
				<th>Start</th>
				<th>End</th>
				<th></th>
			</tr>
		</thead>
		<tbody data-bind="foreach: slots">
			<tr>
				<td data-bind="text: title"></td>
				<td data-bind="text: formattedStart()"></td>
				<td data-bind="text: formattedEnd()"></td>
				<td>
					<button type="button" class="close" data-bind="click: $root.removeSlot"><span aria-hidden="true">&times;</span><span class="sr-only">Remove</span></button>
				</td>
			</tr>
		</tbody>
	</table>
</div>

		
<div class="form-group">
		<button class="btn btn-success" data-bind="click: addSlot">Add unavailable slot</button>
		<button class="btn btn-danger" data-bind="click: removeAllSlots">Remove all</button>
	<div class="pull-right">
		<button class="btn btn-primary" data-bind="click: submitSlots">Save</button>
	</div>
</div>
<br />

<%@ include file="Footer.jsp" %>