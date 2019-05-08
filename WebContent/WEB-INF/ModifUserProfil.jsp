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

<title>Modif Profil</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<c:import url="header.jsp"></c:import>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8">
				<div class="jumbotron">
					<h1>Mon Profil (${ sessionScope.login })</h1>
					<button id="BtnAddMedocInList" class=" btn btn-default btn-sm">Ajouter
						Prescription à ma Liste</button>
				</div>
				<div class="container">

					<form id="newMedocInList"
						class="form-row d-none needs-validation" novalidate
						action="ModifUserProfilAction" method="post">
						<div class="row form-row justify-content">
							<label>Medicament:</label> <select name="idmedicament" size="1"
								class="form-control col-sm-4" required>
								<option disabled selected value>Sélectionnez</option>
								<c:forEach items="${ listeMedicamentsTries }" var="medicament">
									<option value="${ medicament.id }">${ medicament.nom }</option>
								</c:forEach>
							</select>
						</div>
						<div class="row form-group">
							<label>Posologie:</label><input type="text"
								name="posologieQuantite" class="form-control col-sm-1"
								placeholder="qté" required> </input> <select name="posologieDose"
								size="1" class="form-control col-sm-3" required>
								<option disabled selected value>Sélectionnez</option>
								<c:forEach items="${ listeDoses }" var="dose">
									<option value="${ dose.id }">${ dose.nom }</option>
								</c:forEach>
							</select> <input type="text" name="posologieFrequence"
								class="form-control col-sm-1" placeholder="freq" required>fois
							par</input> <select name="posologieTypeFrequence" size="1"
								class="form-control col-sm-3" required>
								<option disabled selected value>Sélectionnez</option>
								<c:forEach items="${ listeFrequences }" var="frequence">
									<option value="${ frequence.id }">${ frequence.nom }</option>
								</c:forEach>
							</select>
						</div>


						<button type="submit" class="btn btn-sm btn-primary">Valider</button>

					</form>

					<form id="newMedocInList2"
						action="ModifUserProfilAction" method="post">
						<div class="form-row">
							<div class="col-md-4 mb-3">
								 <input
									type="text" class="form-control" id="validationDefault01"
									placeholder="First name" value="Mark" required>
							</div>
							<div class="col-md-4 mb-3">
								 <select name="posologieDose"
								size="1" class="form-control" required>
								<option disabled selected value>Sélectionnez</option>
								<c:forEach items="${ listeDoses }" var="dose">
									<option value="${ dose.id }">${ dose.nom }</option>
								</c:forEach>
							</select>
							</div>
							<div class="col-md-4 mb-3">
								
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroupPrepend21">@</span>
									</div>
									<input type="text" class="form-control"
										id="validationDefaultUsername" placeholder="Username"
										aria-describedby="inputGroupPrepend21" required>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6 mb-3">
								<input type="text"
									class="form-control" id="validationDefault03"
									placeholder="City" required>
							</div>
							<div class="col-md-3 mb-3">
								<input
									type="text" class="form-control" id="validationDefault04"
									placeholder="State" required>
							</div>
							<div class="col-md-3 mb-3">
								 <input type="text"
									class="form-control" id="validationDefault05" placeholder="Zip"
									required>
							</div>
						</div>
						<div class="form-group">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input"
									id="invalidCheck12" required> <label
									class="custom-control-label" for="invalidCheck">Agree
									to terms and conditions</label>
							</div>
						</div>
						<button class="btn btn-primary btn-sm" type="submit">Submit
							form</button>
					</form>


					















					<div id="newMedoc" class="row d-none">
						<a href="EnregistrerMedicament" class=" btn btn-default btn-sm">Ajouter
							Medicament</a>
					</div>
					<div class="row d-none">
						<form>


							<legend>Légende</legend>
							Text : <input type="text"> Textarea :
							<textarea id="textarea"></textarea>
							Select : <select>
								<option>Option 1</option>
								<option>Option 2</option>
								<option>Option 3</option>
							</select>
							<button>Envoyer</button>
						</form>





						<div class="form-group">
							<p>
								<label>Medicament</label> <select name="idmedicament" size="1">
									<option>Sélectionnez</option>
									<c:forEach items="${ listeMedicamentsTries }" var="medicament">
										<option value="${ medicament.id }">${ medicament.nom }</option>
									</c:forEach>
								</select>
							</p>
							<p></p>
						</div>
						<div>
							<button type="submit" class="btn btn-sm btn-primary">Valider</button>
						</div>

					</div>
				</div>
			</div>
			<div class="col-xs-6 col-sm-4" id="sidebar">
				<div class="list-group">
					<div class="list-group-item active">Liste de mes
						prescriptions</div>
					<c:forEach items="${ listePrescriptionsTries }" var="prescription">
						<div class="list-group-item">${ prescription }</div>
					</c:forEach>

				</div>
				<div class="list-group">
					<div class="list-group-item active">Liste de mes dernières
						prises</div>
					<c:forEach items="${ listePrises }" var="prise">
						<div class="list-group-item">${ prise }</div>
					</c:forEach>

				</div>
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
	<!--<script src="js/jquery.min.js"></script>-->
	<!--<script src="js/bootstrap.min.js"></script>-->


	<script src="js/ModifUserProfil.js"></script>
</body>
</html>