<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
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
<link href="css/Ordonnance.css" rel="stylesheet" type="text/css">
<!-- Custom styles for this template -->


</head>

<body>
	<c:import url="header.jsp"></c:import>


	<div class="container">

		<div class="row">
			<div class="col-xs-12 col-sm-8">
				<div class="h1">
					<h1>Afficher une ordonnance</h1>
				</div>
			</div>
			<div class="col-12">
				<form action="AfficherOrdonnanceAction" method="post"
					autocomplete="off" enctype="multipart/form-data">
					<div class="form-group row d-none">
						<label for="idOrdonnance" class="col-2 col-form-label">Id</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-key"></i>
									</div>
								</div>
								<input id="idOrdonnance" name="idOrdonnance"
									value="${ myOrdonnance.id }" type="text"
									aria-describedby="idOrdonnanceHelpBlock" required="required"
									class="form-control" readonly>
							</div>
						</div>
					</div>
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
									class="custom-select champDesactive" disabled>
									<option value="${ myOrdonnance.medecin.id }" selected>${ myOrdonnance.medecin.nom }</option>
									<c:forEach items="${ listeMedecins }" var="medecin">
										<c:if test="${ medecin.id != myOrdonnance.medecin.id }">
											<option value="${ medecin.id }">${ medecin.nom }</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<a href="EnregistrerMedecin"> <span
							class="fa fa-plus-square-o fa-2x btnPlus d-none btnMasques"></span>
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
								<input type="text" id="date" class="form-control champDesactive"
									data-lang="fr" data-years="2019-2030" data-format="DD/MM/YYYY"
									required="required" name="date" value="${ myOrdonnance.date }"
									disabled />
							</div>
							<span id="textHelpBlockDate" class="form-text text-muted d-none">ex:
								04/07/2019</span>
						</div>

						<div class="col-4">
							<!--  <div class="form-group row"> -->
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-file-text-o"></i>
									</div>
								</div>
								<div class="custom-file">
									<input type="file" class="custom-file-input champDesactive"
										id="customFile" name="fichier" lang="fr"
										accept=".jpg,.jpeg,.gif,.png,.pdf"
										value="${ myOrdonnance.fichier }" disabled> <label
										class="custom-file-label" for="customFile">${ myOrdonnance.fichier }</label>
								</div>
							</div>
							<!-- 	</div>-->
						</div>


					</div>
					<div class="form-group row">
						<c:if test="${ myOrdonnance.commentaire == null }">
							<button type="button" id="ajouterCommentaire"
								class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
								Commentaire</button>
						</c:if>
						<c:if test="${ myOrdonnance.commentaire != null }">
							<div id="comment" class="col-5 ">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-pencil"></i>
										</div>
									</div>
									<textarea id="commentaire" name="commentaire"
										class="form-control champDesactive" disabled maxlength="300">${ myOrdonnance.commentaire }</textarea>
									<button type="button" name="btnMoins" id="enleverCommentaire"
										class="btn btn-outline-danger fa fa-minus-square-o fa-2x d-none btnMasques"></button>

								</div>

							</div>

						</c:if>

					</div>
					<hr>



					<div class="listPrescription">
						<div class="d-none">
							<label>nb precription:</label> <input type="text"
								id="nbPrescription" name="nbPrescription"
								class="nbPrescription form-control" value="0"></input>
						</div>
						<div class="detailPrescription ">

							<c:import url="ordonnancePrescription1.jsp"></c:import>
							<c:import url="ordonnancePrescription2.jsp"></c:import>
							<c:import url="ordonnancePrescription3.jsp"></c:import>
							<c:import url="ordonnancePrescription4.jsp"></c:import>
							<c:import url="ordonnancePrescription5.jsp"></c:import>
						</div>
					</div>
					<div class="listAnalyse">
						<div class="d-none">
							<label>nb analyse:</label> <input type="text" id="nbAnalyse"
								name="nbAnalyse" class="nbAnalyse form-control" value="0"></input>
						</div>
						<div class="detailAnalyse">
							<c:import url="ordonnanceAnalyse1.jsp"></c:import>
							<c:import url="ordonnanceAnalyse2.jsp"></c:import>
							<c:import url="ordonnanceAnalyse3.jsp"></c:import>
						</div>
					</div>
					<div class="listExamen">
						<div class="d-none">
							<label>nb examen:</label> <input type="text" id="nbExamen"
								name="nbExamen" class="nbExamen form-control" value="0"></input>
						</div>
						<div class="detailExamen">
							<c:import url="ordonnanceExamen1.jsp"></c:import>
							<c:import url="ordonnanceExamen2.jsp"></c:import>
							<c:import url="ordonnanceExamen3.jsp"></c:import>
						</div>
					</div>
					<div class="form-group row"></div>
					<div class="form-group row"></div>
					<div class="form-group row">
						<div class="offset-2 col-10">
							<c:import url="Boutons1.jsp"></c:import>
							<a href="EnregistrerOrdonnance" id="ajouter"
								class="btn btn-outline-success btnAffiches">Ajouter</a>
							<c:import url="Boutons2.jsp"></c:import>
							<a href="AfficherOrdonnance?id=${ myOrdonnance.id }" id="cancel"
								class="btn btn-outline-secondary btnMasques d-none">Annuler</a>
						</div>
					</div>
				</form>
				<div class="row">
					<button type="button" id="ajouterPrescription"
						class="col-sm-3  btn btn-outline-primary btn-sm  text-left d-none">+
						Prescription</button>
				</div>
				<div class=" row">
					<button type="button" id="ajouterAnalyse"
						class="col-sm-3  btn btn-outline-success btn-sm  text-left d-none">+
						Analyse</button>
				</div>
				<div class=" row">
					<button type="button" id="ajouterExamen"
						class="col-sm-3  btn btn-outline-info btn-sm  text-left d-none">+
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

	<script src="js/moment-with-locales.min.js"></script>
	<script src="js/ion.calendar.min.js"></script>
	<script src="js/AfficherX.js"></script>
	<script src="js/AfficherOrdonnance.js"></script>

	<script>
        $(function () {
            $("input[type=file]").change(function (e) {
                $(this).next('.custom-file-label').text(e.target.files[0].name);
            })

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