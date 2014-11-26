<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
	"existingKeywords": [
		<c:forEach items="${ moduleKeywords }" var="keyword" varStatus="keywordStatus">
			[
				"${ keyword.getKeyword_id() }",
				"${ keyword.getKeyword_name() }"
			]
			<c:if test="${ keywordStatus.index < moduleKeywords.size() - 1 }">
			,
			</c:if>
		</c:forEach>
	],
	"selectedKeywords": [
		<c:forEach items="${ userKeywords }" var="keyword" varStatus="keywordStatus">
			[
				"${ keyword.getKeyword_id() }",
				"${ keyword.getKeyword_name() }"
			]
			<c:if test="${ keywordStatus.index < userKeywords.size() - 1 }">
			,
			</c:if>
		</c:forEach>
	]
}