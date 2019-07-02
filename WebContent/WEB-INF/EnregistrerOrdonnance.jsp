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
				<div class="h1">
					<h1>Enregistrer une ordonnance</h1>
				</div>
			</div>
			<div class="col-12">
				<form action="EnregistrerOrdonnanceAction" method="post" autocomplete="off">
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
					<hr>



					<div class="listPrescription">
							<div class="d-none"> 
							<label>nb precription:</label>
							<input type="text" id="nbPrescription" name="nbPrescription" class="nbPrescription form-control"value="0"></input>
						</div>
						<div class="detailPrescription ">


							<!-- prescription1  -->
							<div id="prescription1" class=" d-none">
								<div id="prescription1Ligne1" class="form-group row">
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
										class="fa fa-plus-square-o fa-2x plusMedicament1"></span>
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
										class="fa fa-plus-square-o fa-2x plusDose1"></span>
									</a>
								</div>
								<div id="prescription1Ligne2" class="form-group row">
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
														id="frequenceCheckbox1_0" type="checkbox"
														class="custom-control-input" value="1"> <label
														for="frequenceCheckbox1_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox1_1"
														id="frequenceCheckbox1_1" type="checkbox"
														class="custom-control-input" value="2"> <label
														for="frequenceCheckbox1_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox1_2"
														id="frequenceCheckbox1_2" type="checkbox"
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
								<hr>
							</div>

							<!-- fin prescription1 -->

							<!-- prescription2  -->
							<div id="prescription2" class=" d-none">
								<div id="prescription2Ligne1" class="form-group row">
									<label for="idMedicament2"
										class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-eyedropper"></i>
												</div>
											</div>
											<select id="idMedicament2" name="idMedicament2"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeMedicaments }" var="medicament">
													<option value="${ medicament.id }">${ medicament.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerMedicament"> <span
										class="fa fa-plus-square-o fa-2x plusMedicament2"></span>
									</a> <label for="quantiteDose2"
										class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
									<div class="col-1">
										<input id="quantiteDose2" name="quantiteDose2" type="number"
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
											<select id="idDose2" name="idDose2" class="custom-select"
												required="required">
												<option disabled selected>Sélectionner dose</option>
												<c:forEach items="${ listeDoses }" var="dose">
													<option value="${ dose.id }">${ dose.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerDose"> <span
										class="fa fa-plus-square-o fa-2x plusDose2"></span>
									</a>
								</div>
								<div id="prescription2Ligne2" class="form-group row">
									<label for="quantiteFrequence2"
										class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
									<div class="col-1">
										<input id="quantiteFrequence2" name="quantiteFrequence2"
											type="number" class="form-control" required="required"
											value="0" min="0">
									</div>
									<label for="idFrequence2" class=" col-form-label ">fois
										par</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-clock-o"></i>
												</div>
											</div>
											<select id="idFrequence2" name="idFrequence2"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeDurees }" var="duree">
													<option value="${ duree }">${ duree.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div id="freqRadio2" class="col-2 form-group row d-none ">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio2" id="frequenceRadio2_0"
														type="radio" class="custom-control-input" value="1">
													<label for="frequenceRadio2_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio2" id="frequenceRadio2_1"
														type="radio" class="custom-control-input" value="2">
													<label for="frequenceRadio2_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio2" id="frequenceRadio2_2"
														type="radio" class="custom-control-input" value="3">
													<label for="frequenceRadio2_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<div id="freqCheck2" class="col-2 form-group row d-none">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox2_0"
														id="frequenceCheckbox2_0" type="checkbox"
														class="custom-control-input" value="1"> <label
														for="frequenceCheckbox2_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox2_1"
														id="frequenceCheckbox2_1" type="checkbox"
														class="custom-control-input" value="2"> <label
														for="frequenceCheckbox2_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox2_2"
														id="frequenceCheckbox2_2" type="checkbox"
														class="custom-control-input" value="3"> <label
														for="frequenceCheckbox2_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<label class="col-1 col-form-label text-right ">pendant</label>
									<div class="col-1">
										<input id="nbDuree2" name="nbDuree2" type="number"
											class="form-control" required="required" value="0" min="0">
									</div>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar-plus-o"></i>
												</div>
											</div>
											<select id="idDuree2" name="idDuree2" class="custom-select"
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
									<button type="button" id="ajouterCommentairePrescription2"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentPrescription2" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentairePrescription2"
												name="commentairePrescription2" class="form-control "
												maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentairePrescription2"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapPrescription2" class="col-7 d-none"></div>
									<div class="col-3 ">
										<button type="button" id="btnValiderPrescription2"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierPrescription2"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerPrescription2"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
								<hr>
							</div>

							<!-- fin prescription2 -->

							<!-- prescription3  -->
							<div id="prescription3" class=" d-none">
								<div id="prescription3Ligne1" class="form-group row">
									<label for="idMedicament3"
										class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-eyedropper"></i>
												</div>
											</div>
											<select id="idMedicament3" name="idMedicament3"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeMedicaments }" var="medicament">
													<option value="${ medicament.id }">${ medicament.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerMedicament"> <span
										class="fa fa-plus-square-o fa-2x plusMedicament3"></span>
									</a> <label for="quantiteDose3"
										class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
									<div class="col-1">
										<input id="quantiteDose3" name="quantiteDose3" type="number"
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
											<select id="idDose3" name="idDose3" class="custom-select"
												required="required">
												<option disabled selected>Sélectionner dose</option>
												<c:forEach items="${ listeDoses }" var="dose">
													<option value="${ dose.id }">${ dose.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerDose"> <span
										class="fa fa-plus-square-o fa-2x plusDose3"></span>
									</a>
								</div>
								<div id="prescription3Ligne2" class="form-group row">
									<label for="quantiteFrequence3"
										class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
									<div class="col-1">
										<input id="quantiteFrequence3" name="quantiteFrequence3"
											type="number" class="form-control" required="required"
											value="0" min="0">
									</div>
									<label for="idFrequence3" class=" col-form-label ">fois
										par</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-clock-o"></i>
												</div>
											</div>
											<select id="idFrequence3" name="idFrequence3"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeDurees }" var="duree">
													<option value="${ duree }">${ duree.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div id="freqRadio3" class="col-2 form-group row d-none ">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio3" id="frequenceRadio3_0"
														type="radio" class="custom-control-input" value="1">
													<label for="frequenceRadio3_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio3" id="frequenceRadio3_1"
														type="radio" class="custom-control-input" value="2">
													<label for="frequenceRadio3_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio3" id="frequenceRadio3_2"
														type="radio" class="custom-control-input" value="3">
													<label for="frequenceRadio3_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<div id="freqCheck3" class="col-2 form-group row d-none">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox3_0"
														id="frequenceCheckbox3_0" type="checkbox"
														class="custom-control-input" value="1"> <label
														for="frequenceCheckbox3_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox3_1"
														id="frequenceCheckbox3_1" type="checkbox"
														class="custom-control-input" value="2"> <label
														for="frequenceCheckbox3_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox3_2"
														id="frequenceCheckbox3_2" type="checkbox"
														class="custom-control-input" value="3"> <label
														for="frequenceCheckbox3_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<label class="col-1 col-form-label text-right ">pendant</label>
									<div class="col-1">
										<input id="nbDuree3" name="nbDuree3" type="number"
											class="form-control" required="required" value="0" min="0">
									</div>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar-plus-o"></i>
												</div>
											</div>
											<select id="idDuree3" name="idDuree3" class="custom-select"
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
									<button type="button" id="ajouterCommentairePrescription3"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentPrescription3" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentairePrescription3"
												name="commentairePrescription3" class="form-control "
												maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentairePrescription3"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapPrescription3" class="col-7 d-none"></div>
									<div class="col-3 ">
										<button type="button" id="btnValiderPrescription3"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierPrescription3"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerPrescription3"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
								<hr>
							</div>

							<!-- fin prescription3 -->


							<!-- prescription4  -->
							<div id="prescription4" class=" d-none">
								<div id="prescription4Ligne1" class="form-group row">
									<label for="idMedicament4"
										class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-eyedropper"></i>
												</div>
											</div>
											<select id="idMedicament4" name="idMedicament4"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeMedicaments }" var="medicament">
													<option value="${ medicament.id }">${ medicament.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerMedicament"> <span
										class="fa fa-plus-square-o fa-2x plusMedicament2"></span>
									</a> <label for="quantiteDose4"
										class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
									<div class="col-1">
										<input id="quantiteDose4" name="quantiteDose4" type="number"
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
											<select id="idDose4" name="idDose4" class="custom-select"
												required="required">
												<option disabled selected>Sélectionner dose</option>
												<c:forEach items="${ listeDoses }" var="dose">
													<option value="${ dose.id }">${ dose.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerDose"> <span
										class="fa fa-plus-square-o fa-2x plusDose4"></span>
									</a>
								</div>
								<div id="prescription4Ligne2" class="form-group row">
									<label for="quantiteFrequence4"
										class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
									<div class="col-1">
										<input id="quantiteFrequence4" name="quantiteFrequence4"
											type="number" class="form-control" required="required"
											value="0" min="0">
									</div>
									<label for="idFrequence4" class=" col-form-label ">fois
										par</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-clock-o"></i>
												</div>
											</div>
											<select id="idFrequence4" name="idFrequence4"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeDurees }" var="duree">
													<option value="${ duree }">${ duree.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div id="freqRadio4" class="col-2 form-group row d-none ">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio4" id="frequenceRadio4_0"
														type="radio" class="custom-control-input" value="1">
													<label for="frequenceRadio4_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio4" id="frequenceRadio4_1"
														type="radio" class="custom-control-input" value="2">
													<label for="frequenceRadio4_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio4" id="frequenceRadio4_2"
														type="radio" class="custom-control-input" value="3">
													<label for="frequenceRadio4_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<div id="freqCheck4" class="col-2 form-group row d-none">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox4_0"
														id="frequenceCheckbox4_0" type="checkbox"
														class="custom-control-input" value="1"> <label
														for="frequenceCheckbox4_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox4_1"
														id="frequenceCheckbox4_1" type="checkbox"
														class="custom-control-input" value="2"> <label
														for="frequenceCheckbox4_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox4_2"
														id="frequenceCheckbox4_2" type="checkbox"
														class="custom-control-input" value="3"> <label
														for="frequenceCheckbox4_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<label class="col-1 col-form-label text-right ">pendant</label>
									<div class="col-1">
										<input id="nbDuree4" name="nbDuree4" type="number"
											class="form-control" required="required" value="0" min="0">
									</div>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar-plus-o"></i>
												</div>
											</div>
											<select id="idDuree4" name="idDuree4" class="custom-select"
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
									<button type="button" id="ajouterCommentairePrescription4"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentPrescription4" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentairePrescription4"
												name="commentairePrescription4" class="form-control "
												maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentairePrescription4"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapPrescription4" class="col-7 d-none"></div>
									<div class="col-3 ">
										<button type="button" id="btnValiderPrescription4"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierPrescription4"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerPrescription4"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
								<hr>
							</div>

							<!-- fin prescription4 -->

							<!-- prescription4  -->
							<div id="prescription5" class=" d-none">
								<div id="prescription5Ligne1" class="form-group row">
									<label for="idMedicament5"
										class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-eyedropper"></i>
												</div>
											</div>
											<select id="idMedicament5" name="idMedicament5"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeMedicaments }" var="medicament">
													<option value="${ medicament.id }">${ medicament.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerMedicament"> <span
										class="fa fa-plus-square-o fa-2x plusMedicament2"></span>
									</a> <label for="quantiteDose5"
										class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
									<div class="col-1">
										<input id="quantiteDose5" name="quantiteDose5" type="number"
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
											<select id="idDose5" name="idDose5" class="custom-select"
												required="required">
												<option disabled selected>Sélectionner dose</option>
												<c:forEach items="${ listeDoses }" var="dose">
													<option value="${ dose.id }">${ dose.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerDose"> <span
										class="fa fa-plus-square-o fa-2x plusDose4"></span>
									</a>
								</div>
								<div id="prescription5Ligne2" class="form-group row">
									<label for="quantiteFrequence5"
										class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
									<div class="col-1">
										<input id="quantiteFrequence5" name="quantiteFrequence5"
											type="number" class="form-control" required="required"
											value="0" min="0">
									</div>
									<label for="idFrequence5" class=" col-form-label ">fois
										par</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-clock-o"></i>
												</div>
											</div>
											<select id="idFrequence5" name="idFrequence5"
												required="required" class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeDurees }" var="duree">
													<option value="${ duree }">${ duree.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div id="freqRadio5" class="col-2 form-group row d-none ">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio5" id="frequenceRadio5_0"
														type="radio" class="custom-control-input" value="1">
													<label for="frequenceRadio5_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio5" id="frequenceRadio5_1"
														type="radio" class="custom-control-input" value="2">
													<label for="frequenceRadio5_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-radio">
													<input name="frequenceRadio5" id="frequenceRadio5_2"
														type="radio" class="custom-control-input" value="3">
													<label for="frequenceRadio5_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<div id="freqCheck5" class="col-2 form-group row d-none">
										<div class="col-1">
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox5_0"
														id="frequenceCheckbox5_0" type="checkbox"
														class="custom-control-input" value="1"> <label
														for="frequenceCheckbox5_0" class="custom-control-label">Matin</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox5_1"
														id="frequenceCheckbox5_1" type="checkbox"
														class="custom-control-input" value="2"> <label
														for="frequenceCheckbox5_1" class="custom-control-label">Midi</label>
												</div>
											</div>
											<div class="custom-controls-stacked">
												<div class="custom-control custom-checkbox">
													<input name="frequenceCheckbox5_2"
														id="frequenceCheckbox5_2" type="checkbox"
														class="custom-control-input" value="3"> <label
														for="frequenceCheckbox5_2" class="custom-control-label">Soir</label>
												</div>
											</div>
										</div>
									</div>
									<label class="col-1 col-form-label text-right ">pendant</label>
									<div class="col-1">
										<input id="nbDuree5" name="nbDuree5" type="number"
											class="form-control" required="required" value="0" min="0">
									</div>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar-plus-o"></i>
												</div>
											</div>
											<select id="idDuree5" name="idDuree5" class="custom-select"
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
									<button type="button" id="ajouterCommentairePrescription5"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentPrescription5" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentairePrescription5"
												name="commentairePrescription5" class="form-control "
												maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentairePrescription5"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapPrescription5" class="col-7 d-none"></div>
									<div class="col-3 ">
										<button type="button" id="btnValiderPrescription5"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierPrescription5"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerPrescription5"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
								<hr>
							</div>

							<!-- fin prescription5 -->


						</div>
					</div>
					<div class="listAnalyse">
					  	<div class="d-none"> 
							<label>nb analyse:</label>
							<input type="text" id="nbAnalyse" name="nbAnalyse" class="nbAnalyse form-control" value="0"></input>
						</div>
						<div class="detailAnalyse">
							<!-- analyse1 -->
							<div id="analyse1" class="d-none">
								<div id="analyse1Ligne1" class="form-group row ">
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
												<option  disabled selected>Sélectionner</option>
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
											<input type="text"  id="dateAnalyse1"
												class="form-control" data-lang="fr" data-years="2019-2030"
												data-format="DD/MM/YYYY" 
												name="dateAnalyse1" placeholder="Sélectionner"
												maxlength="10" />
										</div>
										<span id="textHelpBlockDate"
											class="form-text text-muted d-none">ex: 04/07/2019</span>
									</div>
								</div>
								<div class="form-group row">
									<button type="button" id="ajouterCommentaireAnalyse1"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentAnalyse1" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentaireAnalyse1" name="commentaireAnalyse1"
												class="form-control " maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentaireAnalyse1"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapAnalyse1" class="col-7 d-none"></div>
									<div class="col-3 ">
										<button type="button" id="btnValiderAnalyse1"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierAnalyse1"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerAnalyse1"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
								<hr>
							</div>
							<!-- fin analyse1 -->

							<!-- analyse2 -->
							<div id="analyse2" class="d-none">
								<div id="analyse2Ligne1" class="form-group row ">
									<label for="idAnalyse2"
										class="col-1 col-form-label font-weight-lighter text-right">Analyse</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-flask"></i>
												</div>
											</div>
											<select id="idAnalyse2" name="idAnalyse2" required="required"
												class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeAnalyses }" var="analyse">
													<option value="${ analyse }">${ analyse.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<label for="idCabinetAnalyse2"
										class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-institution"></i>
												</div>
											</div>
											<select id="idCabinetAnalyse2" name="idCabinetAnalyse2"
												required="required" class="custom-select">
												<option  disabled selected>Sélectionner</option>
												<c:forEach items="${ listeCabinets }" var="cabinet">
													<option value="${ cabinet.id }">${ cabinet.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerCabinet"> <span
										class="fa fa-plus-square-o fa-2x btnPlus"></span>
									</a> <label for="dateAnalyse2"
										class="col-1 col-form-label font-weight-lighter text-right">Date
									</label>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
											<input type="text"  id="dateAnalyse2"
												class="form-control" data-lang="fr" data-years="2019-2030"
												data-format="DD/MM/YYYY" 
												name="dateAnalyse2" placeholder="Sélectionner"
												maxlength="10" />
										</div>
										<span id="textHelpBlockDate"
											class="form-text text-muted d-none">ex: 04/07/2019</span>
									</div>
								</div>
								<div class="form-group row">
									<button type="button" id="ajouterCommentaireAnalyse2"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentAnalyse2" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentaireAnalyse2" name="commentaireAnalyse2"
												class="form-control " maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentaireAnalyse2"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapAnalyse2" class="col-7 d-none"></div>
									<div class="col-3 ">
										<button type="button" id="btnValiderAnalyse2"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierAnalyse2"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerAnalyse2"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
								<hr>
							</div>
							<!-- fin analyse2 -->


<!-- analyse3 -->
							<div id="analyse3" class="d-none">
								<div id="analyse3Ligne1" class="form-group row ">
									<label for="idAnalyse3"
										class="col-1 col-form-label font-weight-lighter text-right">Analyse</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-flask"></i>
												</div>
											</div>
											<select id="idAnalyse3" name="idAnalyse3" required="required"
												class="custom-select">
												<option disabled selected>Sélectionner</option>
												<c:forEach items="${ listeAnalyses }" var="analyse">
													<option value="${ analyse }">${ analyse.toString() }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<label for="idCabinetAnalyse3"
										class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
									<div class="col-3">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-institution"></i>
												</div>
											</div>
											<select id="idCabinetAnalyse3" name="idCabinetAnalyse3"
												required="required" class="custom-select">
												<option  disabled selected>Sélectionner</option>
												<c:forEach items="${ listeCabinets }" var="cabinet">
													<option value="${ cabinet.id }">${ cabinet.nom }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<a href="EnregistrerCabinet"> <span
										class="fa fa-plus-square-o fa-2x btnPlus"></span>
									</a> <label for="dateAnalyse3"
										class="col-1 col-form-label font-weight-lighter text-right">Date
									</label>
									<div class="col-2">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
											<input type="text"  id="dateAnalyse3"
												class="form-control" data-lang="fr" data-years="2019-2030"
												data-format="DD/MM/YYYY" 
												name="dateAnalyse3" placeholder="Sélectionner"
												maxlength="10" />
										</div>
										<span id="textHelpBlockDate"
											class="form-text text-muted d-none">ex: 04/07/2019</span>
									</div>
								</div>
								<div class="form-group row">
									<button type="button" id="ajouterCommentaireAnalyse3"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentAnalyse3" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentaireAnalyse3" name="commentaireAnalyse3"
												class="form-control " maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentaireAnalyse3"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapAnalyse3" class="col-7 d-none"></div>
									<div class="col-3 ">
										<button type="button" id="btnValiderAnalyse3"
											class="btn  btn-sm btn-outline-success">Enregistrer</button>
										<button type="button" id="btnModifierAnalyse3"
											class="btn  btn-sm btn-outline-success d-none">Modifier</button>
										<button type="button" id="btnAnnulerAnalyse3"
											class="btn  btn-sm btn-outline-danger">Annuler</button>
									</div>
								</div>
								<hr>
							</div>
							<!-- fin analyse3 -->
							
							
						</div>
						<div class="listExamen">
								<div class="d-none"> 
								<label>nb examen:</label>
								<input type="text" id="nbExamen" name="nbExamen" class="nbExamen form-control"value="0"></input>
							</div>
							<div class="detailExamen">
							
							
							<!-- examen1 -->
								<div id="examen1" class="d-none">
									<div id="examen1Ligne1" class="form-group row">
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
													<option  disabled selected>Sélectionner</option>
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
												<input type="text"  id="dateExamen1"
													class="form-control" data-lang="fr" data-years="2019-2030"
													data-format="DD/MM/YYYY"
													name="dateExamen1" placeholder="Sélectionner"
													maxlength="10" />
											</div>
											<span id="textHelpBlockDate"
												class="form-text text-muted d-none">ex: 04/07/2019</span>
										</div>
									</div>
									<div class="form-group row">
										<button type="button" id="ajouterCommentaireExamen1"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentExamen1" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentaireExamen1" name="commentaireExamen1"
												class="form-control " maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentaireExamen1"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapExamen1" class="col-7 d-none"></div>
										<div class="col-3 ">
											<button type="button" id="btnValiderExamen1"
												class="btn  btn-sm btn-outline-success">Enregistrer</button>
											<button type="button" id="btnModifierExamen1"
												class="btn  btn-sm btn-outline-success d-none">Modifier</button>
											<button type="button" id="btnAnnulerExamen1"
												class="btn  btn-sm btn-outline-danger">Annuler</button>
										</div>
									</div>
								</div>
<!-- fin examen1 -->

							<!-- examen2 -->
								<div id="examen2" class="d-none">
									<div id="examen2Ligne1" class="form-group row">
										<label for="idExamen2"
											class="col-1 col-form-label font-weight-lighter text-right">Examen</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-stethoscope"></i>
													</div>
												</div>
												<select id="idExamen2" name="idExamen2" required="required"
													class="custom-select">
													<option disabled selected>Sélectionner</option>
													<c:forEach items="${ listeExamens }" var="examen">
														<option value="${ examen }">${ examen.toString() }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<label for="idCabinetExamen2"
											class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-institution"></i>
													</div>
												</div>
												<select id="idCabinetExamen2" name="idCabinetExamen2"
													required="required" class="custom-select">
													<option  disabled selected>Sélectionner</option>
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
												<input type="text"  id="dateExamen2"
													class="form-control" data-lang="fr" data-years="2019-2030"
													data-format="DD/MM/YYYY" 
													name="dateExamen2" placeholder="Sélectionner"
													maxlength="10" />
											</div>
											<span id="textHelpBlockDate"
												class="form-text text-muted d-none">ex: 04/07/2019</span>
										</div>
									</div>
									<div class="form-group row">
										<button type="button" id="ajouterCommentaireExamen2"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentExamen2" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentaireExamen2" name="commentaireExamen2"
												class="form-control " maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentaireExamen2"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapExamen2" class="col-7 d-none"></div>
										<div class="col-3 ">
											<button type="button" id="btnValiderExamen2"
												class="btn  btn-sm btn-outline-success">Enregistrer</button>
											<button type="button" id="btnModifierExamen2"
												class="btn  btn-sm btn-outline-success d-none">Modifier</button>
											<button type="button" id="btnAnnulerExamen2"
												class="btn  btn-sm btn-outline-danger">Annuler</button>
										</div>
									</div>
								</div>
<!-- fin examen2 -->

							<!-- examen3 -->
								<div id="examen3" class="d-none">
									<div id="examen3Ligne1" class="form-group row">
										<label for="idExamen3"
											class="col-1 col-form-label font-weight-lighter text-right">Examen</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-stethoscope"></i>
													</div>
												</div>
												<select id="idExamen3" name="idExamen3" required="required"
													class="custom-select">
													<option disabled selected>Sélectionner</option>
													<c:forEach items="${ listeExamens }" var="examen">
														<option value="${ examen }">${ examen.toString() }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<label for="idCabinetExamen3"
											class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-institution"></i>
													</div>
												</div>
												<select id="idCabinetExamen3" name="idCabinetExamen3"
													required="required" class="custom-select">
													<option  disabled selected>Sélectionner</option>
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
												<input type="text"  id="dateExamen3"
													class="form-control" data-lang="fr" data-years="2019-2030"
													data-format="DD/MM/YYYY" 
													name="dateExamen3" placeholder="Sélectionner"
													maxlength="10" />
											</div>
											<span id="textHelpBlockDate"
												class="form-text text-muted d-none">ex: 04/07/2019</span>
										</div>
									</div>
									<div class="form-group row">
										<button type="button" id="ajouterCommentaireExamen3"
										class="col-sm-2  btn btn-default btn-sm text-warning text-left">+
										Commentaire</button>
									<div id="commentExamen3" class="col-5 d-none">
										<div class="input-group">
											<div class="input-group-prepend">
												<div class="input-group-text">
													<i class="fa fa-pencil"></i>
												</div>
											</div>
											<textarea id="commentaireExamen3" name="commentaireExamen3"
												class="form-control " maxlength="300"></textarea>
											<button type="button" name="btnMoins"
												id="enleverCommentaireExamen3"
												class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
										</div>
									</div>
									<div id="recapExamen3" class="col-7 d-none"></div>
										<div class="col-3 ">
											<button type="button" id="btnValiderExamen3"
												class="btn  btn-sm btn-outline-success">Enregistrer</button>
											<button type="button" id="btnModifierExamen3"
												class="btn  btn-sm btn-outline-success d-none">Modifier</button>
											<button type="button" id="btnAnnulerExamen3"
												class="btn  btn-sm btn-outline-danger">Annuler</button>
										</div>
									</div>
								</div>
<!-- fin examen3 -->
							</div>
						</div>
						<div class="form-group row"></div>
						<div class="form-group row"></div>
						<div class="btnSubmitAndCancel form-group row d-none">
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
            $("#dateAnalyse1").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
            $("#dateAnalyse2").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
            $("#dateAnalyse3").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
            $("#dateExamen1").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
            $("#dateExamen2").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
            $("#dateExamen3").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
        });
    </script>


</body>
</html>