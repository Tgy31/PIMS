<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
  "data": [
	<c:forEach items="${ students }" var="student" varStatus="studentStatus">
		[
			"${ student.getFirst_name() }",
			"${ student.getLast_name() }",
			"${ student.getEmail() }"
		]
		<c:if test="${ studentStatus.index < students.size() - 1 }">
		,
		</c:if>
	</c:forEach>
	]
}