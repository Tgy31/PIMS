<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PIMS</title>
<link rel="stylesheet" href="/PIMS/Ressources/style/main.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

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

					<c:if test="${ !empty sessionScope.username }">

						<li class="projects"><a href="/PIMS/projects">Projects</a></li>
						<li class="students"><a href="/PIMS/students">Students</a></li>
						<li class="inspectors"><a href="/PIMS/inspectors">Inspectors</a></li>

					</c:if>

				</ul>
				<ul class="nav navbar-nav navbar-right">


					<c:choose>
						<c:when test="${ empty sessionScope.username }">

							<li class="login"><a href="/PIMS/login">Log in</a></li>

						</c:when>
						<c:when test="${ !empty sessionScope.username }">

							<li class="dropdown user"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">${ sessionScope.firstName }
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="/PIMS/logout">Log out</a></li>
									<li class="divider"></li>
									<li class="dropdown-header">Profile</li>
									<li><a href="#">Edit</a></li>
								</ul></li>

						</c:when>
					</c:choose>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div id="body-container" class="container">


		<c:if test="${ !empty alertType }">
			<div class="alert ${ alertType } alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				${ alertMessage }
			</div>
		</c:if>