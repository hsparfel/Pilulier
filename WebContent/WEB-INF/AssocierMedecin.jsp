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
				<div class="h1">
					<h1>Associer un medecin</h1>
				</div>

				<form action="AssocierMedecinAction" method="post">
					<!--  mettre en d-none -->
					<div class="form-group row">
						<label for="pagePrecedente" class="col-2 col-form-label">Url</label>
						<div class="col-6">
							<input id="pagePrecedente" name="pagePrecedente" type="text"
								class="form-control" >
						</div>
					</div>
					<div class="form-group row">
						<label for="idMedecin" class="col-2 col-form-label">Medecin</label>
						<div class="col-6">
							<select id="idMedecin" name="idMedecin" required="required"
								class="custom-select">
								<option disabled selected>Sélectionner</option>
								<c:forEach items="${ listeMedecinsExclus }" var="medecin">
									<option value="${ medecin.id }">${ medecin.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerMedecin"> <span
							class="fa fa-plus-square-o fa-2x btnPlus"></span>
						</a>
					</div>

					<div class="form-group row">
						<div class="offset-2 col-6">
							<button name="submit" type="submit"
								class="btn btn-primary btnSubmit">Associer</button>
							<a href="ModifUserProfil" id="cancel"
								class="btn btn-outline-secondary btnCancel">Annuler</a>
						</div>
					</div>
				</form>
			</div>
			<div class="col-xs-6 col-sm-3" id="sidebar">

				<c:import url="sidebarListMyMedecin.jsp"></c:import>
			</div>

		</div>
		<!--/row-->


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
	<script src="js/AssocMedecin.js"></script>



</body>
</html>