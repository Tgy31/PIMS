<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<script>
	var moduleKeywords = [];
</script>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Keywords for ${ user.getFirst_name() }</h3>
	</div>
	<div class="panel-body">
	
		<div class="input-group">	
			<select id="keywordSelect" class="form-control" name="keyword" data-bind="options: availableKeywords,
																		                        optionsText: 'name',
																		                        optionsValue: 'id',
																		                        enable: availableKeywords().length > 0">
			</select>
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" data-bind="click: addKeyword, enable: availableKeywords().length > 0">Add keyword</button>
			</span>
		</div>
		<br />
		<p data-bind="visible: selectedKeywords().length > 0">
		    You have selected <span data-bind="text: selectedKeywords().length"></span> keywords:
		</p>
		<p data-bind="visible: selectedKeywords().length == 0">
		    <i>Select a keyword and click "Add keyword".</i>
		</p>
		
	</div> <!-- end panel body -->
	
	<ul class="list-group" data-bind="foreach: selectedKeywords">
	    <li class="list-group-item">
	    	<span data-bind="text: name">
	    	</span>
	    	<button type="button" class="close" data-bind="click: $root.removeKeyword"><span aria-hidden="true">&times;</span><span class="sr-only">Remove</span></button>
	    </li>
	</ul>
</div>

<div class="form-group">
	<div class="pull-right">
		<button class="btn btn-primary" data-bind="click: submitKeywords">Save</button>
	</div>
</div>

<%@ include file="Footer.jsp" %>