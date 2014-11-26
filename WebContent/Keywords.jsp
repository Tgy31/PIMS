<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Keywords for ${ user.getFirst_name() }</h3>
	</div>
	<div class="panel-body">
	
		<div class="input-group">	
			<select id="keywordSelect" class="form-control" name="keyword" data-bind="options: availableKeywords,
																		                        optionsText: 'name',
																		                        optionsValue: 'index'">
			</select>
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" data-bind="click: addKeyword, enable: availableKeywords().length > 0">Add keyword</button>
			</span>
		</div>
		
	</div> <!-- end panel body -->
	
	<ul class="list-group" data-bind="foreach: selectedKeywords">
	    <li class="list-group-item">
	    	<span data-bind="text: name">
	    	</span>
	    </li>
	</ul>
</div>

<%@ include file="Footer.jsp" %>