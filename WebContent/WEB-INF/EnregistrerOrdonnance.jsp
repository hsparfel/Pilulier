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
									class="custom-select" value="">
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
									required="required" name="date" placeholder="Sélectionner"
									maxlength="10" />
							</div>
							<span id="textHelpBlockDate" class="form-text text-muted d-none">ex:
								04/07/2019</span>
						</div>


					</div>
					<div class="form-group row">
						<button type="button" id="ajouterCommentaire"
							class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
							Commentaire</button>
						<div id="comment" class="col-5 d-none">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-pencil"></i>
									</div>
								</div>
								<textarea id="commentaire" name="commentaire"
									class="form-control " maxlength="300"></textarea>
								<button type="button" name="btnMoins" id="enleverCommentaire"
									class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>

							</div>

						</div>



					</div>
					<div class="listPresciption">
						<div class="d-none">
							<label>nb precription:</label>
							<div class="nbPrescription">0</div>
						</div>
						<div class="detailPrescription ">



							<div id="prescription1" class=" d-none">
								<div id="prescriptionLigne1" class="form-group row">
									<label for="idMedicament1"
										class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-eyedropper"></i>
												</div>
											</div>
											<select id="idMedicament1" name="idMedicament1"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeMedicaments }" var="medicament">
													<option value="${ medicament.id }">${ medicament.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerMedicament"> <span
										class="fa fa-plus-square-o fa-2x"></span>
									</a> <label for="quantiteDose1"
										class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
									<div class="col-1">

										<input id="quantiteDose1" name="quantiteDose1" type="number"
											required="required" class="form-control" step="0.5" value="0"
											min="0">
									</div>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-spoon"></i>
												</div>
											</div>
											<select id="idDose1" name="idDose1" class="custom-select"
												required="required">
												<option disabled selected>Sélectionner dose</option>
												<c:forEach items="${ listeDoses }" var="dose">
													<option value="${ dose.id }">${ dose.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerDose"> <span
										class="fa fa-plus-square-o fa-2x"></span>
									</a>
								</div>
								<div id="prescriptionLigne2" class="form-group row">
									<label for="quantiteFrequence1"
										class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
									<div class="col-1">
										<input id="quantiteFrequence1" name="quantiteFrequence1"
											type="number" class="form-control" required="required"
											value="0" min="0">
									</div>
									<label for="idFrequence1" class=" col-form-label ">fois
										par</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-clock-o"></i>
												</div>
											</div>
											<select id="idFrequence1" name="idFrequence1"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeDurees }" var="duree">
													<option value="${ duree }">${ duree.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div id="freqRadio1" class="col-2 form-group row d-none ">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio1" id="frequenceRadio1_0"
														type="radio" class="custom-control-input" value="1">
													<label for="frequenceRadio1_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio1" id="frequenceRadio1_1"
														type="radio" class="custom-control-input" value="2">
													<label for="frequenceRadio1_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio1" id="frequenceRadio1_2"
														type="radio" class="custom-control-input" value="3">
													<label for="frequenceRadio1_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<div id="freqCheck1" class="col-2 form-group row d-none">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox1_0"
														id="frequenceCheckbox1_0" type="checkbox1"
														class="custom-control-input" value="1"> <label
														for="frequenceCheckbox1_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox1_1"
														id="frequenceCheckbox1_1" type="checkbox1"
														class="custom-control-input" value="2"> <label
														for="frequenceCheckbox1_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox1_2"
														id="frequenceCheckbox1_2" type="checkbox1"
														class="custom-control-input" value="3"> <label
														for="frequenceCheckbox1_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<label class="col-1 col-form-label text-right ">pendant</label>
									<div class="col-1">
										<input id="nbDuree1" name="nbDuree1" type="number"
											class="form-control" required="required" value="0" min="0">
									</div>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar-plus-o"></i>
												</div>
											</div>
											<select id="idDuree1" name="idDuree1" class="custom-select"
												required="required">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeDurees }" var="duree">
													<option value="${ duree }">${ duree.toString() }</option>
												</c:forEach>
											</select>
										</div>

									</div>

								</div>
								<div class="form-group row">

									<button type="button" id="ajouterCommentairePrescription1"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentPrescription1" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentairePrescription1"
												name="commentairePrescription1" class="form-control "
												maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentairePrescription1"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapPrescription1" class="col-7 d-none"></div>

									<div class="col-3 ">
										<button type="button" id="btnValiderPrescription1"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierPrescription1"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerPrescription1"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
							</div>















						</div>
					</div>
					<div class="listAnalyse">
						<div class="d-none">
							<label>nb analyse:</label>
							<div class="nbAnalyse">0</div>
						</div>
						<div class="detailAnalyse">
							<div id="analyse1" class="rounded border d-none border-success">
								<div class="form-group row ">
									<label for="idAnalyse1"
										class="col-1 col-form-label font-weight-lighter text-right">Analyse</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-flask"></i>
												</div>
											</div>
											<select id="idAnalyse1" name="idAnalyse1" required="required"
												class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeAnalyses }" var="analyse">
													<option value="${ analyse }">${ analyse.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<label for="idCabinetAnalyse1"
										class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-institution"></i>
												</div>
											</div>
											<select id="idCabinetAnalyse1" name="idCabinetAnalyse1"
												required="required" class="custom-select">
												<option value="" disabled selected>Sélectionner</option>
												<c:forEach items="${ listeCabinets }" var="cabinet">
													<option value="${ cabinet.id }">${ cabinet.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerCabinet"> <span
										class="fa fa-plus-square-o fa-2x btnPlus"></span>
									</a> <label for="dateAnalyse1"
										class="col-1 col-form-label font-weight-lighter text-right">Date
									</label>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
											<input type="text" value="" id="dateAnalyse1"
												class="form-control" data-lang="fr" data-years="2019-2030"
												data-format="DD/MM/YYYY" required="required"
												name="dateAnalyse1" placeholder="Sélectionner"
												maxlength="10" />
										</div>
										<span id="textHelpBlockDate"
											class="form-text text-muted d-none">ex: 04/07/2019</span>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-9"></div>
									<div class="col-3">
										<button type="button" name="btnValiderAnalyse1"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" name="btnModifierAnalyse1"
											class="btn  btn-sm btn-outline-success">Modifier</button>
										<button type="button" name="btnAnnulerAnalyse1"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
							</div>




						</div>
						<div class="listExamen">
							<div class="d-none">
								<label>nb examen:</label>
								<div class="nbExamen">0</div>
							</div>
							<div class="detailExamen">
								<div id="examen1" class="rounded border d-none border-warning">
									<div class="form-group row">
										<label for="idExamen1"
											class="col-1 col-form-label font-weight-lighter text-right">Examen</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-stethoscope"></i>
													</div>
												</div>
												<select id="idExamen1" name="idExamen1" required="required"
													class="custom-select">
													<option disabled selected>Sélectionner</option>
													<c:forEach items="${ listeExamens }" var="examen">
														<option value="${ examen }">${ examen.toString() }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<label for="idCabinetExamen1"
											class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-institution"></i>
													</div>
												</div>
												<select id="idCabinetExamen1" name="idCabinetExamen1"
													required="required" class="custom-select">
													<option value="" disabled selected>Sélectionner</option>
													<c:forEach items="${ listeCabinets }" var="cabinet">
														<option value="${ cabinet.id }">${ cabinet.nom }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<a href="EnregistrerCabinet"> <span
											class="fa fa-plus-square-o fa-2x btnPlus"></span>
										</a> <label for="date1"
											class="col-1 col-form-label font-weight-lighter text-right">Date
										</label>
										<div class="col-2">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-calendar"></i>
													</div>
												</div>
												<input type="text" value="" id="dateExamen1"
													class="form-control" data-lang="fr" data-years="2019-2030"
													data-format="DD/MM/YYYY" required="required"
													name="dateExamen1" placeholder="Sélectionner"
													maxlength="10" />
											</div>
											<span id="textHelpBlockDate"
												class="form-text text-muted d-none">ex: 04/07/2019</span>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-9"></div>
										<div class="col-3 ">
											<button type="button" name="btnValiderExamen1"
												class="btn  btn-sm btn-outline-success">Enregistrer</button>
											<button type="button" name="btnModifierExamen1"
												class="btn  btn-sm btn-outline-success">Modifier</button>
											<button type="button" name="btnAnnulerExamen1"
												class="btn  btn-sm btn-outline-danger">Annuler</button>
										</div>
									</div>
								</div>



							</div>
						</div>
						<div class="form-group row"></div>
						<div class="form-group row"></div>
						<div class="form-group row d-none">
							<div class="offset-2 col-6">
								<button name="submit" type="submit" class="btn btn-primary">Valider</button>
								<a href="ModifUserProfil" id="cancel"
									class="btn btn-outline-secondary">Annuler</a>
							</div>
						</div>
				</form>
				<div class="row">
					<button type="button" id="ajouterPrescription"
						class="col-sm-3  btn btn-outline-primary btn-sm  text-left">+
						Prescription</button>
				</div>
				<div class=" row">
					<button type="button" id="ajouterAnalyse"
						class="col-sm-3  btn btn-outline-success btn-sm  text-left">+
						Analyse</button>
				</div>
				<div class=" row">
					<button type="button" id="ajouterExamen"
						class="col-sm-3  btn btn-outline-info btn-sm  text-left">+
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
            $("#dateAnalyse").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
            $("#dateExamen").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
        });
    </script>


</body>
</html>