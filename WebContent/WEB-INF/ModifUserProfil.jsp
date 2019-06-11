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
</head>

<body>
	<c:import url="header.jsp"></c:import>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8">
				<div class="jumbotron">
					<div class ="row"><h1 id="div_horloge"></h1>
					</div>
					<a class=" btn btn-default btn-sm" href="AssocierMedecin">Associer
						Medecin</a> <a class=" btn btn-default btn-sm" href="DissocierMedecin">Dissocier
						Medecin</a><a class=" btn btn-default btn-sm" href="EnregistrerPrise">Ajouter
						Prise</a> <a class=" btn btn-default btn-sm"
						href="EnregistrerOrdonnance">Ajouter Ordonnance</a>
				</div>
				<div class="col-xs-12 col-sm-6">
					
						<c:import url="sidebarMenuListMyPrise.jsp"></c:import>
						
					
				</div>
			</div>
			<div class="col-xs-6 col-sm-4" id="sidebar">
				<c:import url="sidebarMenuListMyRdv.jsp"></c:import>
				<c:import url="sidebarMenuListMyMedecin.jsp"></c:import>
				<c:import url="sidebarMenuListMyPrescription.jsp"></c:import>


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