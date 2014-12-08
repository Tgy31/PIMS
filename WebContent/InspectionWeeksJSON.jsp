<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
  "data": [
	<c:forEach items="${ inspectionWeeks }" var="inspectionWeek" varStatus="inspectionWeekStatus">
		[
			"${ inspectionWeek.getInspection_title() }",
			"${ inspectionWeek.getDisplayableStartDate() }",
			"${ inspectionWeek.getInspectionweek_id() }"
		]
		<c:if test="${ inspectionWeekStatus.index < inspectionWeeks.size() - 1 }">
		,
		</c:if>
	</c:forEach>
	]
}