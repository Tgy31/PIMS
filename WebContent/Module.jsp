<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>



<c:if test="${ empty sessionScope.alertType }">

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
		    <h3 class="panel-title">Module</h3>
		  </div>
		  <div class="panel-body">
	  
			  <div class="form-group">
			    <label for="inputTitle" class="col-sm-2 control-label">Name</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputTitle" name="inputTitle" placeholder="Title" value="${ module.getModule_name() }">
			    </div>
			  </div>
	  
			  <div class="form-group">
			    <label for="inputTitle" class="col-sm-2 control-label">Keywords</label>
			    <div class="col-sm-10">
					<p data-bind="visible: keywords().length > 0">
					    You have registered <b data-bind="text: keywords().length"></b> keywords.
					</p>
					<p data-bind="visible: keywords().length == 0">
					    <i>Enter a keyword and click "Add keyword".</i>
					</p>
					<div class="input-group">	
						<input type="text" id="inputKeywordName" class="form-control" placeholder="Web, mobile, network..."></input>
						<input type="hidden" id="inputKeywords" name="inputKeywords"></input>
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" data-bind="click: addKeyword">Add keyword</button>
						</span>
					</div>
			    </div>
			  </div>
				
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
</c:if>

<%@ include file="Footer.jsp" %>