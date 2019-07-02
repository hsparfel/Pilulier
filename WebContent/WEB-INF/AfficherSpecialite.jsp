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
					<h1>Afficher une specialite</h1>
				</div>

				<form action="AfficherSpecialiteAction" method="post">
					<div class="form-group row d-none">
						<label for="idSpecialite" class="col-2 col-form-label">Id</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-key"></i>
									</div>
								</div>
								<input id="idSpecialite" name="idSpecialite"
									value="${ maSpecialite.id }" type="text"
									aria-describedby="idSpecialiteHelpBlock" required="required"
									class="form-control" readonly>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="nomSpecialite" class="col-2 col-form-label">Nom</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-graduation-cap"></i>
									</div>
								</div>
								<input id="nomSpecialite" name="nomSpecialite"
									value="${ maSpecialite.nom }" type="text"
									aria-describedby="nomSpecialiteHelpBlock" required="required"
									class="form-control champDesactive" disabled>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-2 col-10">
							<c:import url="Boutons1.jsp"></c:import>
							<a href="EnregistrerSpecialite" id="ajouter"
								class="btn btn-outline-success btnAffiches">Ajouter</a>
							<c:import url="Boutons2.jsp"></c:import> <a
								href="AfficherSpecialite?id=${ maSpecialite.id }" id="cancel"
								class="btn btn-outline-secondary btnMasques d-none">Annuler</a>
						</div>
					</div>
				</form>
			</div>

			<div class="col-xs-6 col-sm-4 " id="sidebar">
				<c:import url="sidebarListAllSpecialite.jsp"></c:import>
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
	<script src="js/AfficherX.js"></script>



</body>
</html>