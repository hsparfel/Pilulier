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
					<h1>Enregistrer un medecin</h1>

				</div>

				<form id="newMedecinInList" action="EnregistrerMedecinAction"
					method="post">
					<div class="form-group row">
						<label for="nomMedecin" class="col-2 col-form-label">Nom</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-address-book"></i>
									</div>
								</div>
								<input id="nomMedecin" name="nomMedecin"
									placeholder="saisir le nom du medecin" type="text"
									aria-describedby="nomMedecinHelpBlock" required="required"
									class="form-control">
							</div>
						</div>
					</div>

					<div class="form-group row">
						<label for="idSpecialite" class="col-2 col-form-label">Specialite</label>
						<div class="col-6">
							<select id="idSpecialite" name="idSpecialite" required="required"
								class="custom-select" >
								<option value="" disabled selected>Sélectionner</option>
								<c:forEach items="${ listeSpecialites }" var="specialite">
									<option value="${ specialite.id }">${ specialite.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerSpecialite"> <span
							class="fa fa-plus-square-o fa-2x btnPlus"></span>
						</a>
					</div>
					<div class="form-group row">
						<label for="idCabinet" class="col-2 col-form-label">Cabinet</label>
						<div class="col-6">
							<select id="idCabinet" name="idCabinet" required="required"
								class="custom-select" >
								<option value="" disabled selected>Sélectionner</option>
								<c:forEach items="${ listeCabinets }" var="cabinet">
									<option value="${ cabinet.id }">${ cabinet.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerCabinet"> <span
							class="fa fa-plus-square-o fa-2x btnPlus"></span>
						</a>
					</div>
					<div class="form-group row">
						<label for="nomTelephone" class="col-2 col-form-label">Telephone</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-mobile-phone"></i>
									</div>
								</div>
								<input id="nomTelephone" name="nomTelephone"
									placeholder="ex: 0123456789" type="text" required="required"
									class="form-control" minlength ="10" maxlength="10">
							</div>
							<span id="textHelpBlockNomTelephone" class="form-text text-muted d-none">ex: 0123456789</span>
						
						</div>
					</div>

					<div class="form-group row">
						<label for="nomEmail" class="col-2 col-form-label">Email</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-envelope"></i>
									</div>
								</div>
								<input id="nomEmail" name="nomEmail"
									placeholder="ex: nom@domaine.com" type="text"
									class="form-control" required="required">
							</div>
							<span id="textHelpBlockNomEmail" class="form-text text-muted d-none">ex: nom@domaine.com</span>
						
						</div>
					</div>

					<div class="form-group row">
						<div class="offset-2 col-6">
							<button name="submit" type="submit" class="btn btn-primary btnSubmit">Valider</button>
							<a href="AssocierMedecin" id="cancel"
								class="btn btn-outline-secondary btnCancel">Annuler</a>
						</div>
					</div>
				</form>

			</div>

			<div class="col-xs-6 col-sm-4 " id="sidebar">
				<c:import url="sidebarListAllMedecin.jsp"></c:import>
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="js/EnrMedecin.js"></script>



</body>
</html>