<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<nav class="navbar navbar-expand-sm bg-light">

	<!-- Links -->
	<ul class="navbar-nav">
		<li class="nav-item"><img id="imgPilules"
			src="images/pilules.png" alt="pilules" title="pilules" /></li>
		<li class="nav-item"><a class="nav-link" href="Accueil">Bienvenue</a>
		</li>
		<li class="nav-item"><c:if test="${ !empty sessionScope.login }">
				<a class="nav-link" href="ModifUserProfil"> ${ sessionScope.login }</a>
			</c:if></li>
		<li class="nav-item"><c:if test="${ !empty sessionScope.login }">
				<a class="nav-link" href="Deconnexion" id="logout">Logout</a>
			</c:if></li>
	</ul>

</nav>

