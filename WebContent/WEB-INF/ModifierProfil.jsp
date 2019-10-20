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
					<h1>Modifier mon profil</h1>
				</div>

				<form action="ModifierProfilAction" method="post">
					<!--  mettre en d-none -->
					<div class="form-group row d-none">
						<label for="pagePrecedente" class="col-2 col-form-label">Url</label>
						<div class="col-6">
							<input id="pagePrecedente" name="pagePrecedente" type="text"
								class="form-control"></input>
						</div>
					</div>
					<div class="form-group row">
						<label for="dateDeNaissance" class="col-2 col-form-label">Naissance</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-birthday-cake"></i>
									</div>
								</div>
								<input id="dateDeNaissance" name="dateDeNaissance"
									data-lang="fr" data-years="1950-2030" data-format="DD/MM/YYYY"
									maxlength="10" type="text" class="form-control"
									value=${utilisateur.dateDeNaissance}></input>
							</div>
							<span id="textHelpBlockDateDeNaissance" class="form-text text-muted d-none">ex:
								04/07/2019</span>
						</div>
					</div>
					<div class="form-group row">
						<label for="sexe" class="col-2 col-form-label">Sexe</label>
						<div class="col-6">
							<select id="sexe" name="sexe" class="custom-select"
								required="required">
								<option disabled <c:if test="${empty utilisateur.sexe}" >selected</c:if>>Sélectionner</option>
								<c:forEach items="${ listeSexes }" var="sexe">
									<option value="${ sexe }" <c:if test="${sexe== utilisateur.sexe}" >selected</c:if>>${ sexe.toString() }</option>
								</c:forEach>
							</select>
						</div>
					</div>

					

					<div class="form-group row">
						<div class="col-10">
							<div class="form-group row ">
								<label for="poids" class=" col-5 col-form-label text-right">Mon
									Poids (kgs)</label> <input type="text" id="poids" name="poids"
									class="form-control col-3" value=${profil.poids }></input>
							</div>
							<div class=" row">
								<input type="range" min="1" max="110" step="0.1"
									class="form-control-range  offset-3" id="curseurPoids"
									value=${profil.poids }>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10">
							<div class="form-group row ">
								<label for="taille" class=" col-5 col-form-label text-right">Ma
									Taille (cms)</label> <input type="text" id="taille" name="taille"
									class="form-control col-3"></input>
							</div>
							<div class=" row">
								<input type="range" min="40" max="220"
									class="form-control-range  offset-3" id="curseurTaille"
									value=${profil.taille}>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10">
							<div class="form-group row ">
								<label for="imc" class=" col-5 col-form-label text-right">Mon
									IMC</label> <input type="text" id="imc" name="imc"
									class="form-control col-3"><input id="imcComm"
									name="imcComm" class=" col-3 form-control text-right"></input>
							</div>
						</div>
					</div>

<div class="form-group row">
						<label for="date"
							class="col-2 col-form-label font-weight-lighter text-right">Date
						</label>
						<div class="col-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
								<input type="text" id="date" class="form-control" data-lang="fr"
									data-years="1950-2030" data-format="DD/MM/YYYY" name="date"
									maxlength="10" />
							</div>
							<span id="textHelpBlockDate" class="form-text text-muted d-none">ex:
								04/07/2019</span>
						</div>

					</div>

					<div class="form-group row">
						<div class="offset-2 col-10">
							<button name="submit" type="submit" class="btn btn-primary">Valider</button>
							<a href="ModifUserProfil" id="cancel"
								class="btn btn-outline-secondary">Annuler</a>

						</div>
					</div>
				</form>
			</div>

			<!--	<div class="col-xs-6 col-sm-4 " id="sidebar">
				<c:import url="sidebarListAllDose.jsp"></c:import>
			</div>-->

		</div>


		<!--	<div class="row">
		
		
		
		</div>-->
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
	<script src="js/Profil.js"></script>

	<script src="js/moment-with-locales.min.js"></script>
	<script src="js/ion.calendar.min.js"></script>

	<script>
        $(function () {
            $("#date").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
        });
        $(function () {
            $("#dateDeNaissance").ionDatePicker({
                hideArrows : true,
                sundayFirst : false
            });
        });
    </script>

</body>
</html>