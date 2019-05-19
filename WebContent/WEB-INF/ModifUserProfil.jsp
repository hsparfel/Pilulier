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
						Medecin</a> <a class=" btn btn-default btn-sm" href="DissocierMedecin">Dissocier
						Medecin</a><a class=" btn btn-default btn-sm" href="EnregistrerPrise">Ajouter
						Prise</a> <a class=" btn btn-default btn-sm"
						href="EnregistrerPrescription">Ajouter Prescription</a> <a
						class=" btn btn-default btn-sm" href="EnregistrerRdv">Ajouter
						Rendez-vous</a> 
				</div>
			</div>
			<div class="col-xs-6 col-sm-4" id="sidebar">

				<c:if test="${!empty listePrescriptions }">
					<div class="list-group">
						<button  class="list-group-item active">Mes
							prescriptions</button>
						<div >
							<c:forEach items="${ listePrescriptions }" var="prescription">
								<div class="list-group-item">${ prescription.medicament.getNom() }
									- ${ prescription.nbDose } ${ prescription.dose.getNom() }, ${ prescription.nbFrequence }
									fois par ${ prescription.frequence.getNom()} pendant
									${prescription.nbDuree } ${prescription.duree.getNom() }</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:import url="sidebarListMyPrescription.jsp"></c:import>
				<c:if test="${!empty listePrises }">
					<div class="list-group">
						<button  class="list-group-item active">Mes
							dernières prises</button>
						<div >
							<c:forEach items="${ listePrises }" var="prise">
								<div class="list-group-item">${ prise.medicament.getNom() }-
									${ prise.datePrise }</div>
							</c:forEach>
						</div>
					</div>
				</c:if>

				<c:import url="sidebarListMyPrise.jsp"></c:import>
				<c:import url="sidebarListMyMedecin.jsp"></c:import>
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
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="js/ModifUserProfil.js"></script>
</body>
</html>