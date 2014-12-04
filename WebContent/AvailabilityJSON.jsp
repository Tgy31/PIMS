<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
  	"maxUnavailability": ${ maxUnavailableHours },
  	"defaultDate": "${ inspectionDate }",
	"slots": [
		<c:forEach items="${ timeslots }" var="timeslot" varStatus="timeslotStatus">
			{
				"start": "${ timeslot.getFormattedStartDate() }",
				"end": "${ timeslot.getFormattedEndDate() }"
			}
			<c:if test="${ timeslotStatus.index < timeslots.size() - 1 }">
			,
			</c:if>
		</c:forEach>
	]
}