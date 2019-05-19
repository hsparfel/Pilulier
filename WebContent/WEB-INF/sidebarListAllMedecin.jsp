<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeMedecins }">
	<div class="list-group">
		<div class="list-group-item active">Liste des medecins</div>
		<c:forEach items="${ listeMedecins }" var="medecin">
			<a href="AfficherMedecin?id=${ medecin.id }" class="list-group-item">${ medecin.nom}-
				${ medecin.specialite.nom}<br>${ medecin.cabinet.nom}</a>
		</c:forEach>
	</div>
</c:if>

