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
</head>

<body>
	<c:import url="header.jsp"></c:import>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8">
				<div class="jumbotron">
					<h1>Mon Profil</h1>

					<a class=" btn btn-default btn-sm" href="AssocierMedecin">Associer
						Medecin</a> <a class=" btn btn-default btn-sm" href="EnregistrerPrise">Ajouter
						Prise</a> <a class=" btn btn-default btn-sm"
						href="EnregistrerPrescription">Ajouter Prescription</a> <a
						class=" btn btn-default btn-sm" href="EnregistrerRdv">Ajouter
						Rendez-vous</a>
				</div>

			</div>
			<div class="col-xs-6 col-sm-4" id="sidebar">
				<c:if test="${!empty listePrescriptions }">
					<div class="dropdown list-group">
						<button class="btn btn-primary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Mes
							prescriptions</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<c:forEach items="${ listePrescriptions }" var="prescription">
								<div class="dropdown-item">${ prescription.medicament.getNom() }
									- ${ prescription.nbDose } ${ prescription.dose.getNom() }, ${ prescription.nbFrequence }
									fois par ${ prescription.frequence.getNom() }</div>

							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty listePrises }">
					<div class="dropdown list-group">
						<button class="btn btn-primary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Mes dernières
							prises</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<c:forEach items="${ listePrises }" var="prise">
								<div class="dropdown-item">${ prise.medicament.getNom() }-
									${ prise.datePrise }</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty listeMedecins }">
					<div class="dropdown list-group">
						<button class="btn btn-primary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Mes medecins</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<c:forEach items="${ listeMedecins }" var="medecin">
								<div class="dropdown-item">${ medecin.getNom() }-${ medecin.getSpecialite().getNom() }</div>
							</c:forEach>

						</div>
					</div>
				</c:if>



			</div>
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
	<!--<script src="js/jquery.min.js"></script>-->
	<!--<script src="js/bootstrap.min.js"></script>-->


	<script src="js/ModifUserProfil.js"></script>
</body>
</html>