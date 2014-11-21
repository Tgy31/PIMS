<%@ page pageEncoding="UTF-8"%>

<c:if test="${ !empty sessionScope.alertType }">
	<div class="alert ${ sessionScope.alertType } alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert">
			<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
		</button>
		${ sessionScope.alertMessage }
	</div>
</c:if>