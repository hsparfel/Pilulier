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
			<li class="nav-item"><a class="nav-link ordonnance"
				href="EnregistrerOrdonnance">Ordonnance</a></li>
			<li class="nav-item"><a class="nav-link rdv"
				href="EnregistrerRdv">RDV</a></li>
		<!--  	<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Enregistrer </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item medicament" href="EnregistrerMedicament">Medicament</a>
					<a class="dropdown-item specialite" href="EnregistrerSpecialite">Specialite</a>
					<a class="dropdown-item rdv" href="EnregistrerRdv">Rdv</a> <a
						class="dropdown-item cabinet" href="EnregistrerCabinet">Cabinet</a>
					<a class="dropdown-item" href="EnregistrerUtilisateur">Utilisateur</a>
					<a class="dropdown-item ordonnance" href="EnregistrerOrdonnance">Ordonnance</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item dose" href="EnregistrerDose">Dose</a>
				</div></li>-->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Medecin </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item enregistrer" href="EnregistrerMedecin">Enregistrer</a>
					<a class="dropdown-item associer" href="AssocierMedecin">Associer</a>
					<a class="dropdown-item dissocier" href="DissocierMedecin">Dissocier</a>


				</div></li>
			<li class="nav-item"><a class="nav-link" href="Deconnexion"
				id="logout">Logout</a></li>
		</c:if>
	</ul>
</nav>

