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
			<div class="col-xs-12 col-sm-8">
				<div class="jumbotron">
					<h1>Enregistrer un cabinet</h1>
				</div>
				<form action="EnregistrerCabinetAction" method="post">
					<div class="form-group row">
						<label for="nomCabinet" class="col-2 col-form-label">Nom</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-institution"></i>
									</div>
								</div>
								<input id="nomCabinet" name="nomCabinet"
									placeholder="saisir le nom du cabinet" type="text"
									aria-describedby="nomCabinetHelpBlock" required="required"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="adresseCabinet" class="col-2 col-form-label">Adresse</label>
						<div class="col-6">
							<input id="adresseCabinet" name="adresseCabinet"
								placeholder="ex: 2 avenue de la republique" type="text"
								class="form-control" required="required">
						</div>
					</div>
					<div class="form-group row">
						<label for="cpCabinet" class="col-2 col-form-label">CP</label>
						<div class="col-6">
							<input id="cpCabinet" name="cpCabinet" placeholder="ex: 06100"
								type="text" class="form-control" required="required">
						</div>
					</div>
					<div class="form-group row">
						<label for="villeCabinet" class="col-2 col-form-label">Ville</label>
						<div class="col-6">
							<input id="villeCabinet" name="villeCabinet"
								placeholder="ex: nice" type="text" class="form-control"
								required="required">
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-2 col-10">
							<button name="submit" type="submit" class="btn btn-primary">Valider</button>
							<a href="EnregistrerMedecin" id="cancel" name="cancel"
								class="btn btn-outline-secondary">Annuler</a>
						</div>
					</div>
				</form>
			</div>
			<div class="col-xs-6 col-sm-4 " id="sidebar">
				<c:import url="sidebarListAllCabinet.jsp"></c:import>
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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	s
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>



</body>
</html>