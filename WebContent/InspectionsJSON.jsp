<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
  "data": [
	<c:forEach items="${ students }" var="student" varStatus="studentStatus">
		[
			"${ student.getFullName() }",
			"${ student.getUsername() }",
			${ servlet.studentHasInspection(student, inspectionWeek) },
			"${ inspectionWeek.getInspectionweek_id() }/${ student.getStudent_id() }"
		]
		<c:if test="${ studentStatus.index < students.size() - 1 }">
		,
		</c:if>
	</c:forEach>
	]
}