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

			<div class="col-xs-12 col-sm-9">
				<div class="jumbotron">
					<h1>Enregistrer une prescription</h1>
				</div>

				<form id="newMedocInList">
					<div class="form-group row">
						<label for="idMedecin" class="col-2 col-form-label">Medecin</label>
						<div class="col-6">
							<select id="idMedecin" name="idMedecin" required="required"
								class="custom-select">
								<option disabled selected value>Sélectionner</option>
								<c:forEach items="${ listeMedecins }" var="medecin">
									<option value="${ medecin.id }">${ medecin.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerMedecin"> <span
							class="fa fa-plus-square-o fa-2x"></span>
						</a>
					</div>
					<div class="form-group row">
						<label for="idMedicament" class="col-2 col-form-label">Medicament</label>
						<div class="col-6">
							<select id="idMedicament" name="idMdicament" required="required"
								class="custom-select">
								<option disabled selected value>Sélectionner</option>
								<c:forEach items="${ listeMedicaments }" var="medicament">
									<option value="${ medicament.id }">${ medicament.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerMedicament"> <span
							class="fa fa-plus-square-o fa-2x"></span>
						</a>
					</div>
					<div class="form-group row">
						<label for="quantiteDose" class="col-2 col-form-label">Quantité</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-spoon"></i>
									</div>
								</div>
								<input id="quantiteDose" name="quantiteDose"
									placeholder="saisir une valeur" type="text" required="required"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="posologieDose" class="col-2 col-form-label">Dose</label>
						<div class="col-6">
							<select id="posologieDose" name="posologieDose"
								class="custom-select" required="required">
								<option disabled selected value>Sélectionner</option>
								<c:forEach items="${ listeDoses }" var="dose">
									<option value="${ dose.id }">${ dose.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerDose"> <span
							class="fa fa-plus-square-o fa-2x"></span>
						</a>
					</div>
					<div class="form-group row">
						<label for="quantiteFrequence" class="col-2 col-form-label">Interval</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-clock-o"></i>
									</div>
								</div>
								<input id="quantiteFrequence" name="quantiteFrequence"
									placeholder="saisir une valeur" type="text"
									class="form-control" required="required">
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="posologieFrequence" class="col-2 col-form-label">Fréquence</label>
						<div class="col-6">
							<select id="posologieFrequence" name="posologieFrequence"
								required="required" class="custom-select">
								<option disabled selected value>Sélectionnez</option>
								<c:forEach items="${ listeFrequences }" var="frequence">
									<option value="${ frequence.id }">${ frequence.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerFrequence"> <span
							class="fa fa-plus-square-o fa-2x"></span>
						</a>
						
					<div id="freqRadio" class="form-group row d-none">
						<div class="col-4"></div>
						<div class="col-8">
							<div class="custom-controls-stacked">
								<div class="custom-control custom-radio">
									<input name="frequenceRadio" id="frequenceRadio_0" type="radio"
										class="custom-control-input" value="1"> <label
										for="frequenceRadio_0" class="custom-control-label">Matin</label>
								</div>
							</div>
							<div class="custom-controls-stacked">
								<div class="custom-control custom-radio">
									<input name="frequenceRadio" id="frequenceRadio_1" type="radio"
										class="custom-control-input" value="2"> <label
										for="frequenceRadio_1" class="custom-control-label">Midi</label>
								</div>
							</div>
							<div class="custom-controls-stacked">
								<div class="custom-control custom-radio">
									<input name="frequenceRadio" id="frequenceRadio_2" type="radio"
										class="custom-control-input" value="3"> <label
										for="frequenceRadio_2" class="custom-control-label">Soir</label>
								</div>
							</div>
						</div>
					</div>
					<div id="freqCheck" class="form-group row d-none">
						<div class="col-4"></div>
						<div class="col-8">
							<div class="custom-controls-stacked">
								<div class="custom-control custom-checkbox">
									<input name="frequenceCheckbox" id="frequenceCheckbox_0"
										type="checkbox" class="custom-control-input" value="1">
									<label for="frequenceCheckbox_0" class="custom-control-label">Matin</label>
								</div>
							</div>
							<div class="custom-controls-stacked">
								<div class="custom-control custom-checkbox">
									<input name="frequenceCheckbox" id="frequenceCheckbox_1"
										type="checkbox" class="custom-control-input" value="2">
									<label for="frequenceCheckbox_1" class="custom-control-label">Midi</label>
								</div>
							</div>
							<div class="custom-controls-stacked">
								<div class="custom-control custom-checkbox">
									<input name="frequenceCheckbox" id="frequenceCheckbox_2"
										type="checkbox" class="custom-control-input" value="3">
									<label for="frequenceCheckbox_2" class="custom-control-label">Soir</label>
								</div>
							</div>
						</div>
					</div>
					
					
					
					
					
					
					</div>
					<div class="form-group row">
						<div class="offset-2 col-6">
							<button name="submit" type="submit" class="btn btn-primary">Valider</button>
							<a href="ModifUserProfil" id="cancel" name="cancel"
								class="btn btn-outline-secondary">Annuler</a>
						</div>
					</div>

					
					
					

				</form>
			</div>
			<div class="col-xs-6 col-sm-3 " id="sidebar">
				<c:if test="${!empty listePrescriptions }">
					<div class="list-group">
						<div class="list-group-item active">Liste de mes
							prescriptions</div>
						<c:forEach items="${ listePrescriptions }" var="prescription">
							<div class="list-group-item">${ prescription }</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</div>
		<!--/.sidebar-offcanvas-->
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
<script
		src="js/Prescription.js"
		></script>



</body>
</html>