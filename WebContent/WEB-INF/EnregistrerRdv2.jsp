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
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
<!-- Custom styles for this template -->


</head>

<body>
	<c:import url="header.jsp"></c:import>


	<div class="container">

		<div class="row">

			<div class="col-xs-12 col-sm-8">
				<div class="jumbotron">
					<h1>Enregistrer un rendez-vous</h1>
				</div>
				<form action="EnregistrerRdvAction" method="post">
					<div class="form-group row">
						<label for="idMedecin" class="col-2 col-form-label">Medecin</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-address-book"></i>
									</div>
								</div>
								<select id="idMedecin" name="idMedecin" required="required"
									class="custom-select">
									<option disabled selected>Sélectionner</option>
									<c:forEach items="${ listeMedecins }" var="medecin">
										<option value="${ medecin.id }">${ medecin.nom }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<a href="EnregistrerMedecin"> <span
							class="fa fa-plus-square-o fa-2x btnPlus"></span>
						</a>
					</div>


					<div class="form-group row">
						<label for="date" class="col-2 col-form-label">Date/Heure</label>
						<div class="col-6">

							
								
								
						<input id="input" type="text"  required="required" name="date" class="form-control "/>

						</div>

					</div>
					<div class="form-group row">
						<label for="commentaire" class="col-2 col-form-label">Commentaire</label>
						<div class="col-8">

							
								
								
						<textarea id="commentaire"   name="commentaire" class="form-control "></textarea>

						</div>

					</div>





					<div class="form-group row">
						<div class="offset-2 col-6">
							<button name="submit" type="submit"
								class="btn btn-primary btnSubmit">Valider</button>
							<a href="ModifUserProfil" id="cancel"
								class="btn btn-outline-secondary btnCancel">Annuler</a>
						</div>
					</div>
				</form>

			</div>

			<div class="col-xs-6 col-sm-4 " id="sidebar">
				<c:import url="sidebarListMyRdv.jsp"></c:import>
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
	<script src="js/EnrRdv.js"></script>

	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
		type="text/javascript"></script>
	<script src="js/datepicker.js"></script>
</body>
</html>