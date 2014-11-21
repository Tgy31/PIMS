<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PIMS</title>

<!-- DataTables CSS -->
<link rel="stylesheet"
	href="${ rootPath }Ressources/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="${ rootPath }Ressources/css/dataTables.bootstrap.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${ rootPath }Ressources/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="${ rootPath }Ressources/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${ rootPath }Ressources/css/main.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="${ activeMenu }">
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">PIMS</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">

					<c:if test="${ !empty sessionScope.user }">

						<li class="projects"><a href="${ rootPath }projects/">Projects</a></li>
						<li class="students"><a href="${ rootPath }students/${ moduleSlug }/">Students</a></li>
						<li class="inspectors"><a href="${ rootPath }inspectors/${ moduleSlug }/">Inspectors</a></li>

					</c:if>

				</ul>
				<ul class="nav navbar-nav navbar-right">


					<c:choose>
						<c:when test="${ empty sessionScope.user }">

							<li class="${ rootPath }login/"><a href="${ rootPath }login">Log in</a></li>

						</c:when>
						<c:when test="${ !empty sessionScope.user }">

							<li class="dropdown user"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">${ sessionScope.user.getFirst_name() }
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="${ rootPath }logout/">Log out</a></li>
									<li class="divider"></li>
									<li class="dropdown-header">Profile</li>
									<li><a href="${ userProfilePath }">Edit</a></li>
								</ul></li>

						</c:when>
					</c:choose>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
	<div id="body-container" class="container">	
	
<c:choose>
    <c:when test="${ layoutType eq 'Grid' }">
	
			<div class="row">
			
				<div class="col-xs-12 col-lg-12">
					<ol class="breadcrumb">
					  <li><a href="#">Module name</a></li>
					  <li><a href="#">Entity type</a></li>
					  <li class="active">Entity name</li>
					</ol>
				</div>
				
				<div class="col-xs-3 col-lg-3">
					<div class="list-group">
						<a href="#" class="list-group-item">
							<h4 class="list-group-item-heading">List group item heading</h4>
							<p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
						</a>
						<a href="#" class="list-group-item">
							<h4 class="list-group-item-heading">List group item heading</h4>
							<p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
						</a>
						<a href="#" class="list-group-item">
							<h4 class="list-group-item-heading">List group item heading</h4>
							<p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
						</a>
						<a href="#" class="list-group-item">
							<h4 class="list-group-item-heading">List group item heading</h4>
							<p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
						</a>
					</div>
				</div>
				<div class="col-xs-9 col-lg-9">
		
		</c:when>
		<c:otherwise>
		
		</c:otherwise>	
</c:choose>
			

