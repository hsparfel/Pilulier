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
				<!--  		<div class="jumbotron"> -->
				<div class="h1">
					<div class="row">
						<h1 id="div_horloge"></h1>
					</div>

				</div>
				<div class="row">
					<div class="col-7 ">

						<c:import url="sidebarMenuListMyPrise.jsp"></c:import>


					</div>
					<div class="col-5">
						<div class="form-group row">
							<label for="myPoids"
								class="col-5 col-form-label text-right ">Poids :</label>
							<div class="col-4 col-form-label text-left ">${ profil.poids }
								kgs</div>

						</div>
						<div class="form-group row">
							<label for="myTaille"
								class="col-5 col-form-label text-right ">Taille :</label>
							<div class="col-4 col-form-label text-left ">${ profil.taille }
								cms</div>

						</div>
						<div class="form-group row">
							<label for="myAge" class="col-5 col-form-label text-right ">Age :</label>
							<div id="myAge" class="col-4 col-form-label text-left "></div>

						</div>
						<div class="form-group row">
							<label for="myNaissance"
								class="col-5 col-form-label text-right ">Naissance :</label>
							<div id="dateNaissance" class="col-4 col-form-label text-left ">${ utilisateur.dateDeNaissance}</div>

						</div>
						<div class="form-group row">
							<label for="myImc" class="col-5 col-form-label text-right ">IMC :</label>
							<div class="col-4 col-form-label text-left ">${ profil.imc }</div>

						</div>
							<div class="form-group row">
							<label for="myImcComm" class="col-5 col-form-label text-right ">Mon
								Commentaire IMC :</label>
							<div class="col-4 col-form-label text-left ">${ profil.commentaire }</div>

						</div>
						<div class="form-group row">
							<div class="offset-2 col-10">
								<a href="ModifierProfil" id="btnModifier" name="btnModifier"
									type="button" class="btn btn-outline-primary btnAffiches">Modifier</a>
							</div>

						</div>
						<div class="form-group row">
							<div class="offset-2 col-10">
								<a href="GraphiqueProfil" id="btnGraphique" name="btnGraphique"
									type="button"
									class=" btn btn-outline-primary btnAffiches"> <img
									src="images/chart.png">
								</a> 

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-sm-4" id="sidebar">
				<c:import url="sidebarMenuListMyRdv.jsp"></c:import>
				<c:import url="sidebarMenuListMyAnalyse.jsp"></c:import>
				<c:import url="sidebarMenuListMyExamen.jsp"></c:import>
				<c:import url="sidebarMenuListMyPrescription.jsp"></c:import>
				<c:import url="sidebarMenuListMyMedecin.jsp"></c:import>

			</div>
		</div>

		<!--/row-->

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
	<script src="js/NavBar.js"></script>
</body>
</html>