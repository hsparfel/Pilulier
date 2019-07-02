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

<link rel="stylesheet" type="text/css" href="css/clockpicker.css">
<link rel="stylesheet" type="text/css" href="css/standalone.css">

<link href="css/ion.calendar.css" rel="stylesheet" type="text/css">
<!-- Custom styles for this template -->


</head>

<body>
	<c:import url="header.jsp"></c:import>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8">
				<div class="h1">
					<h1>Afficher un rendez-vous</h1>
				</div>
				<form action="AfficherRdvAction" method="post">
					<div class="form-group row d-none">
						<label for="idRdv" class="col-2 col-form-label">Id</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-key"></i>
									</div>
								</div>
								<input id="idRdv" name="idRdv" value="${ myRdv.id }" type="text"
									aria-describedby="idSpecialiteHelpBlock" required="required"
									class="form-control" readonly>
							</div>
						</div>
					</div>
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
									class="custom-select champDesactive" disabled>
									<option value="${ myRdv.medecin.id }" selected>${ myRdv.medecin.nom }</option>
									<c:forEach items="${ listeMedecins }" var="medecin">
										<c:if test="${ medecin.id != myRdv.medecin.id }">
											<option value="${ medecin.id }">${ medecin.nom }</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<a href="EnregistrerMedecin"> <span
							class="fa fa-plus-square-o fa-2x"></span>
						</a>
					</div>
					<div class="form-group row">
						<label for="date" class="col-2 col-form-label">Date/Heure</label>
						<div class="col-4">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-calendar"></i>
									</div>
								</div>

								<input type="text"  id="date" class="form-control champDesactive"
									data-lang="fr" data-years="2019-2030" data-format="DD/MM/YYYY"
									required="required" name="date" value="${ myRdv.date }" disabled />
							</div>
						</div>
						<div class="col-4">
							<div class="input-group clockpicker" data-autoclose="true">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-clock-o"></i>
									</div>
								</div>
								<input id="heure" type="text" class="form-control champDesactive" 
									required="required" name="heure" value="${ myRdv.heure }" disabled>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="commentaire" class="col-2 col-form-label">Commentaire</label>
						<div class="col-9">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-pencil"></i>
									</div>
								</div>
								<textarea id="commentaire" name="commentaire"
									class="form-control champDesactive" disabled>${ myRdv.commentaire }</textarea>

							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-2 col-10">
							<c:import url="Boutons1.jsp"></c:import>
							<a href="EnregistrerRdv" id="ajouter"
								class="btn btn-outline-success btnAffiches">Ajouter</a>
							<c:import url="Boutons2.jsp"></c:import>
							<a href="AfficherRdv?id=${ myRdv.id }" id="cancel"
								class="btn btn-outline-secondary btnMasques d-none">Annuler</a>
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
	<script src="js/AfficherX.js"></script>
	<script src="js/AfficherRdv.js"></script>
<script src="js/clockpicker.js"></script>
	<script type="text/javascript">
		$('.clockpicker').clockpicker();
	</script>
	<script src="js/moment-with-locales.min.js"></script>
	<script src="js/ion.calendar.min.js"></script>
	<script>
		$(function() {
			$("#date").ionDatePicker({
				hideArrows : true,
				sundayFirst : false
			});
		});
	</script>


</body>
</html>