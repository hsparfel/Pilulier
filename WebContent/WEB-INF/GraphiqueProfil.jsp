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
<link rel="stylesheet" type="text/css" href="css/jquery.jqplot.css" />
<!-- Custom styles for this template -->

</head>

<body class="dx-viewport">
	<c:import url="header.jsp"></c:import>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8">
				<div class="h1">
					<h1>Evolution</h1>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-7">
				<!--  <div id="chartdiv" style="height: 400px; width: 600px;"></div>-->
				<div id="chartdivPoids" style="height: 400px; width: 100%;"></div>
			</div>
			</div>
		<div class="row">
			<div class="col-7">
				<!--  <div id="chartdiv" style="height: 400px; width: 600px;"></div>-->
				<div id="chartdivTaille" style="height: 400px; width: 100%;"></div>
			</div>
					</div>
		<div class="row">
			<div class="col-7">
				<!--  <div id="chartdiv" style="height: 400px; width: 600px;"></div>-->
				<div id="chartdivImc" style="height: 400px; width: 100%;"></div>
			</div>
					</div>
		<div class="row d-none">
			<table id="tableau">
				<tr>
					
					<th>poids</th>
					<th>taille</th>
					<th>imc</th>
					<th>date</th>
					<th >compteur</th>
				</tr>

				<c:forEach items="${ listeProfils }" var="profil" varStatus="cptr">


					<tr id="ligneTableau${cptr.index}">
						
						<td>${profil.poids}</td>
						<td>${profil.taille}</td>
						<td>${profil.imc}</td>
						<td>${profil.date}</td>
						<td class="cptr">${cptr.index}</td>
					</tr>
				</c:forEach>





			</table>
		</div>
		
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



	<script language="javascript" type="text/javascript"
		src="js/jquery.jqplot.min.js"></script>
	<script src="js/GraphiquePoids.js"></script>
</body>
</html>
