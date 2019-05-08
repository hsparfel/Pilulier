<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="css/style.css">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Ton Profil</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/offcanvas.css" rel="stylesheet">

</head>

<body>
	<c:import url="header.jsp"></c:import>

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="jumbotron">
					<h1>Ton Profil</h1>
					<p>rien</p>
				</div>

				<div class="list-group">


					<c:forEach items="${ listeUtilisateurs }" var="utilisateur">

						<a href="#" class="userexistant btn btn-default btn-sm"> ${ utilisateur.login }</a>
						<br>
						<br>
					</c:forEach>

				</div>
				<div id="newuser" style="display:none">
					<form class="form-inline" action="AccueilAction"
						method="post">

						<div class="form-group">
							<input type="text" name="nomUtilisateur"
								placeholder="saisir le pseudo">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-sm btn-primary">Valider</button>
						</div>
						<br> <br>
					</form>
				</div>
				<button id="boutonplus" type="button" class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-plus"> Nouvel Utilisateur</span>
				</button>








			</div>

			
		</div>
		<!--/row-->

		<hr>

		<c:import url="footer.jsp"></c:import>


	</div>
	<!--/.container-->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/offcanvas.js"></script>
<script src="js/Accueil.js"></script>

</body>
</html>