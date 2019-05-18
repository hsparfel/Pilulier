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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Custom styles for this template -->

</head>

<body>
	<c:import url="header.jsp"></c:import>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<div class="jumbotron">
					<h1>Afficher une dose</h1>
				</div>

				<form action="AfficherDoseAction" method="post">
					<div class="form-group row d-none">
						<label for="idDose" class="col-2 col-form-label">Id</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-key"></i>
									</div>
								</div>
								<input id="idDose" name="idDose"
									value="${ maDose.id }" type="text"
									aria-describedby="idDoseHelpBlock" required="required"
									class="form-control" readonly>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="nomDose" class="col-2 col-form-label">Nom</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-spoon"></i>
									</div>
								</div>
								<input id="nomDose" name="nomDose"
									value="${ maDose.nom }"  type="text"
									aria-describedby="nomDoseHelpBlock" required="required"
									class="form-control" disabled>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-2 col-10">
								<button id="btnModifier" name="btnModifier" type="button"
								class="btn btn-outline-primary">Modifier</button>
							<input id="btnSupprimer" name="submit" value="Supprimer" type="submit" class="btn btn-danger"/>
							<input id="valid" name="submit" value="Valider" type="submit" class="btn btn-primary d-none"/>
							<a href="AfficherDose?id=${ maDose.id }" id="cancel"
								name="cancel" class="btn btn-outline-secondary d-none">Annuler</a>
						</div>
					</div>
				</form>
			</div>

			<div class="col-xs-6 col-sm-3 " id="sidebar">
				<c:import url="sidebarListAllDose.jsp"></c:import>
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
<script src="js/AfficherDose.js"></script>



</body>
</html>