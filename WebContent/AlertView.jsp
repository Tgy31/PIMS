<%@ page pageEncoding="UTF-8"%>

<c:if test="${ !empty alertType }">
	<div class="alert ${ alertType } alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert">
			<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
		</button>
		${ alertMessage }
	</div>
</c:if>