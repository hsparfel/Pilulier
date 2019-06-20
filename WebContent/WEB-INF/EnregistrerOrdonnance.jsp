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
<link rel="stylesheet" type="text/css" href="css/standalone.css">

<link href="css/ion.calendar.css" rel="stylesheet" type="text/css">
<!-- Custom styles for this template -->


</head>

<body>
	<c:import url="header.jsp"></c:import>


	<div class="container">

		<div class="row">

			<div class="col-xs-12 col-sm-8">
				<div class="jumbotron">
					<h1>Enregistrer une ordonnance</h1>
				</div>
			</div>
			<div class="col-12">
				<form action="EnregistrerOrdonnanceAction" method="post">
					<div class="form-group row">
						<label for="idMedecin"
							class="col-1 col-form-label font-weight-lighter text-right">Medecin</label>
						<div class="col-3">
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
							class="fa fa-plus-square-o fa-2x"></span>
						</a> <label for="date"
							class="col-1 col-form-label font-weight-lighter text-right">Date
						</label>
						<div class="col-2">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
								<input type="text" value="" id="date" class="form-control"
									data-lang="fr" data-years="2019-2030" data-format="DD/MM/YYYY"
									required="required" name="date" placeholder="ex: 04/07/2019"
									maxlength="10" />
							</div>
							<span id="textHelpBlockDate" class="form-text text-muted d-none">ex:
								04/07/2019</span>
						</div>

					</div>







					
						<div class="listPresciption">
							<div class="d-none">
								<label>nb precription:</label>
								<div class="nbPrescription">0</div>
							</div>
							<div class="detailPrescription"></div>
						</div>
						<div class="listAnalyse">
							<div class="d-none">
								<label>nb analyse:</label>
								<div class="nbAnalyse">0</div>
							</div>
							<div class="detailAnalyse"></div>
						</div>
						<div class="form-group row listExamen">
							<div class="d-none">
								<label>nb examen:</label>
								<div class="nbExamen">0</div>
							</div>
							<div class="detailExamen"></div>
						</div>
	<!-- 			
					<div class="form-group row">
						<label for="idMedicament"
							class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
						<div class="col-3">
							<select id="idMedicament" name="idMedicament" required="required"
								class="custom-select">
								<option disabled selected>Sélectionner</option>
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
						<label for="quantiteDose"
							class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
						<div class="col-2">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-spoon"></i>
									</div>
								</div>
								<input id="quantiteDose" name="quantiteDose" type="number"
									required="required" class="form-control" step="0.5" value="0"
									min="0">
							</div>
						</div>


						<div class="col-3">
							<select id="idDose" name="idDose" class="custom-select"
								required="required">
								<option disabled selected>Sélectionner dose</option>
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
						<label for="quantiteFrequence"
							class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
						<div class="col-2">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-clock-o"></i>
									</div>
								</div>
								<input id="quantiteFrequence" name="quantiteFrequence"
									type="number" class="form-control" required="required"
									value="0" min="0">
							</div>
						</div>

						<label for="idFrequence" class="col-1 col-form-label ">fois
							par</label>
						<div class="col-2">
							<select id="idFrequence" name="idFrequence" required="required"
								class="custom-select">
								<option disabled selected>Sélectionnez</option>
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
										<input name="frequenceRadio" id="frequenceRadio_0"
											type="radio" class="custom-control-input" value="1">
										<label for="frequenceRadio_0" class="custom-control-label">Matin</label>
									</div>
								</div>
								<div class="custom-controls-stacked">
									<div class="custom-control custom-radio">
										<input name="frequenceRadio" id="frequenceRadio_1"
											type="radio" class="custom-control-input" value="2">
										<label for="frequenceRadio_1" class="custom-control-label">Midi</label>
									</div>
								</div>
								<div class="custom-controls-stacked">
									<div class="custom-control custom-radio">
										<input name="frequenceRadio" id="frequenceRadio_2"
											type="radio" class="custom-control-input" value="3">
										<label for="frequenceRadio_2" class="custom-control-label">Soir</label>
									</div>
								</div>
							</div>
						</div>
						<div id="freqCheck" class="form-group row d-none">
							<div class="col-4"></div>
							<div class="col-8">
								<div class="custom-controls-stacked">
									<div class="custom-control custom-checkbox">
										<input name="frequenceCheckbox_0" id="frequenceCheckbox_0"
											type="checkbox" class="custom-control-input" value="1">
										<label for="frequenceCheckbox_0" class="custom-control-label">Matin</label>
									</div>
								</div>
								<div class="custom-controls-stacked">
									<div class="custom-control custom-checkbox">
										<input name="frequenceCheckbox_1" id="frequenceCheckbox_1"
											type="checkbox" class="custom-control-input" value="2">
										<label for="frequenceCheckbox_1" class="custom-control-label">Midi</label>
									</div>
								</div>
								<div class="custom-controls-stacked">
									<div class="custom-control custom-checkbox">
										<input name="frequenceCheckbox_2" id="frequenceCheckbox_2"
											type="checkbox" class="custom-control-input" value="3">
										<label for="frequenceCheckbox_2" class="custom-control-label">Soir</label>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="form-group row">
						<label for="nbDuree"
							class="col-1 col-form-label font-weight-lighter   text-right">Duree</label>
						<label class="col-1 col-form-label ">pendant</label>
						<div class="col-2">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-calendar-plus-o"></i>
									</div>
								</div>
								<input id="nbDuree" name="nbDuree" type="number"
									class="form-control" required="required" value="0" min="0">
							</div>
						</div>

						<div class="col-2">
							<select id="idDuree" name="idDuree" class="custom-select"
								required="required">
								<option disabled selected>Sélectionner</option>
								<c:forEach items="${ listeDurees }" var="duree">
									<option value="${ duree.id }">${ duree.nom }</option>
								</c:forEach>
							</select>
						</div>
						<a href="EnregistrerDuree"> <span
							class="fa fa-plus-square-o fa-2x"></span>
						</a>
					</div>
-->
					<div class="form-group row">
						<div class="offset-2 col-6">
							<button name="submit" type="submit" class="btn btn-primary">Valider</button>
							<a href="ModifUserProfil" id="cancel"
								class="btn btn-outline-secondary">Annuler</a>
						</div>
					</div>

				</form>

				<div class="row">
					<button id="ajouterPrescription"
						class="col-sm-3  btn btn-default btn-sm text-primary text-left">+
						Prescription</button>
				</div>
				<div class=" row">
					<button id="ajouterAnalyse"
						class="col-sm-3  btn btn-default btn-sm text-primary text-left">+
						Analyse</button>
				</div>
				<div class=" row">
					<button id="ajouterExamen"
						class="col-sm-3  btn btn-default btn-sm text-primary text-left">+
						Examen</button>
				</div>
			</div>
			<div class="col-xs-6 col-sm-4 " id="sidebar">
				<c:import url="sidebarListMyPrescription.jsp"></c:import>
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
	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
		type="text/javascript"></script>
	<script src="js/datePicker.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="js/Ordonnance.js"></script>
	<script src="js/moment-with-locales.min.js"></script>
	<script src="js/ion.calendar.min.js"></script>
	<script>
        $(function () {
            $("#date").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
        });
    </script>


</body>
</html>