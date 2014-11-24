<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
  "data": [
	<c:forEach items="${ modules }" var="module" varStatus="moduleStatus">
		[
			"${ module.getModule_name() }",
			"${ module.getYear() }",
			true,
			"${ module.getModule_id() }"
		]
		<c:if test="${ moduleStatus.index < modules.size() - 1 }">
		,
		</c:if>
	</c:forEach>
	]
}