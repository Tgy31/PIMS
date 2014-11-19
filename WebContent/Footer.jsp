<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	
	</div>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
    <!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.js"></script>
	<script src="https:////cdn.datatables.net/plug-ins/9dcbecd42ad/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	
	<!-- Include additional files -->
	<c:forEach items="${ javascriptFiles }" var="fileName">
		<script src="/PIMS/Ressources/js/${ fileName }"></script>
	</c:forEach>
	
  </body>
</html>