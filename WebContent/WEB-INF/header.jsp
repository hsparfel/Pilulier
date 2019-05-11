<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<nav class="navbar navbar-expand-sm bg-light">

	<!-- Links -->
	<ul class="navbar-nav">
		<li class="nav-item"><img id="imgPilules"
			src="images/pilules.png" alt="pilules" title="pilules" /></li>
		<li class="nav-item"><span class="nav-link">Bienvenue ${ sessionScope.login }</span>
		</li>
		<c:if test="${ !empty sessionScope.login }">
			<li class="nav-item"><a class="nav-link" href="ModifUserProfil">Mon
					Profil</a></li>
			<li class="nav-item"><a class="nav-link"
				href="EnregistrerPrescription">Prescription</a></li>
			<li class="nav-item"><a class="nav-link" href="#">a
					determiner</a></li>
			<li class="nav-item"><a class="nav-link" href="Deconnexion"
				id="logout">Logout</a></li>
		</c:if>
	</ul>

</nav>

