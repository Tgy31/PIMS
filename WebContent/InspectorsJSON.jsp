<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
  "data": [
	<c:forEach items="${ inspectors }" var="inspector" varStatus="inspectorStatus">
		[
			"${ inspector.getFirst_name() }",
			"${ inspector.getLast_name() }",
			"${ inspector.getEmail() }",
			"${ inspector.getUsername() }"
		]
		<c:if test="${ inspectorStatus.index < inspectors.size() - 1 }">
		,
		</c:if>
	</c:forEach>
	]
}