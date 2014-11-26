<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
	"existngKeywords": [
		<c:forEach items="${ existingKeywords }" var="keyword" varStatus="keywordStatus">
			[
				"${ module.getKeyword_id() }",
				"${ module.getKeyword_name() }"
			]
			<c:if test="${ keywordStatus.index < existingKeywords.size() - 1 }">
			,
			</c:if>
		</c:forEach>
	],
	"selectedKeywords": [
		<c:forEach items="${ userKeywords }" var="keyword" varStatus="keywordStatus">
			[
				"${ module.getKeyword_id() }",
				"${ module.getKeyword_name() }"
			]
			<c:if test="${ keywordStatus.index < userKeywords.size() - 1 }">
			,
			</c:if>
		</c:forEach>
	]
}