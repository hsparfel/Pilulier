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

<title>Connexion</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->

</head>

<body>
	<c:import url="header.jsp"></c:import>

	<div class="container">

		<div class="row">

			<div class="col-xs-12 ">
				<div class="jumbotron">
					<h1>Selectionner un pseudo</h1>
					<div class="row listeUserExistant">
						<c:forEach items="${ listeUtilisateurs }" var="utilisateur"
							varStatus="count">
							<button id="${ utilisateur.id }"
								class="col-sm-3 userexistant btn btn-default btn-sm text-primary">${ utilisateur.login }</button>
							<form class="form-inline d-none" action="AccueilAction"
								method="post">
								<div class="form-group">
									<input type="text" name="nomUtilisateur"
										value=${ utilisateur.login }>
								</div>
								<div class="form-group">
									<button id="ExistingUserSubmit${ utilisateur.id }"
										type="submit" class="btn btn-sm btn-primary">Valider</button>
								</div>
							</form>
						</c:forEach>
					</div>
				</div>
				<div id="newuser" class="d-none">
					<form class="form-inline" action="AccueilAction" method="post">

						<div class="form-group">
							<input type="text" name="nomUtilisateur"
								placeholder="saisir le pseudo">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-sm btn-primary">Valider</button>
						</div>
					</form>
				</div>
				<div class="row justify-content-md-center">
					<button id="boutonplus" type="button"
						class="col-sm-3 btn btn-default btn-sm text-muted">
						<span> Nouvel Utilisateur</span>
					</button>
				</div>
			</div>
		</div>

		<!--/row-->
		<hr>
		<c:import url="footer.jsp"></c:import>
	</div>
	<!--/.container-->

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="js/Accueil.js"></script>

</body>
</html>