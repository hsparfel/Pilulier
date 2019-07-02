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

<title>Pilulier</title>

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
								class="col-sm-3 userexistant btn btn-default btn-sm text-primary">${ utilisateur.nom }</button>
							<form class="form-inline d-none" action="AccueilAction"
								method="post">
								<div class="form-group">
									<input type="text" name="nomUtilisateur"
										value=${ utilisateur.nom }>
								</div>
								<div class="form-group">
									<button id="ExistingUserSubmit${ utilisateur.id }"
										type="submit" class="btn btn-sm btn-primary">Valider</button>

								</div>
							</form>
						</c:forEach>
					</div>
				</div>

				<div class="row justify-content-md-center">

					<a class=" btn btn-default btn-sm text-muted"
						href="EnregistrerUtilisateur">Nouvel Utilisateur</a>
				</div>
			</div>
		</div>

		<!--/row-->
		
		<c:import url="footer.jsp"></c:import>
	</div>
	<!--/.container-->

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="js/Accueil.js"></script>

</body>
</html>