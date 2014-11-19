<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
  "data": [
	<c:forEach items="${ inspectors }" var="inspector">
		[
			${ inspector.getFirst_name() },
			${ inspector.getLast_name() },
			${ inspector.getTitle() },
			${ inspector.getEmail() }
		]
	</c:forEach>
	]
}