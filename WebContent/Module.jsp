<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="Header.jsp" %>

<div id="json-variables">
[
<c:forEach items="${ keywords }" var="keyword" varStatus="keywordStatus">
		"${ keyword.getKeyword_name() }"
	<c:if test="${ keywordStatus.index < keywords.size() - 1 }">
	,
	</c:if>
</c:forEach>
]

</div>

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

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Keywords for ${ user.getFirst_name() }</h3>
		</div>
		<div class="panel-body">
		
			<div class="input-group">	
				<input type="text" id="inputKeywordName" class="form-control" placeholder="Web, mobile, network..."></input>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" data-bind="click: addKeyword">Add keyword</button>
				</span>
			</div>
			<br />
			<p data-bind="visible: keywords().length > 0">
			    You have registered <span data-bind="text: keywords().length"></span> keywords:
			</p>
			<p data-bind="visible: keywords().length == 0">
			    <i>enter a keyword and click "Add keyword".</i>
			</p>
			
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
      <button type="submit" class="btn btn-primary margin-14">Save</button>
    </div>
  </div>
</form>

<%@ include file="Footer.jsp" %>