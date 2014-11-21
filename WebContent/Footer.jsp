<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			</div>
		</div>
	</div>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${ rootPath }Ressources/js/jquery-2.1.1.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="${ rootPath }Ressources/js/bootstrap.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
    <!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="${ rootPath }Ressources/js/jquery.dataTables.min.js"></script>
	<script src="${ rootPath }Ressources/js/dataTables.bootstrap.js"></script>
	
	<!-- Include additional files -->
	<c:forEach items="${ javascriptFiles }" var="fileName">
		<script src="${ rootPath }Ressources/js/${ fileName }"></script>
	</c:forEach>
	
  </body>
</html>