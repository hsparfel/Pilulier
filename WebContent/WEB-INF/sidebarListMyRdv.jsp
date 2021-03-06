<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:if test="${!empty listeRdvs }">
	<div class="list-group">
		<div class="list-group-item active">Liste de mes rdvs</div>
		<c:forEach items="${ listeRdvs }" var="rdv">
			<a href="AfficherRdv?id=${ rdv.id }"
				class="list-group-item">${ rdv.medecin.nom}-
				${ rdv.date} à ${ rdv.heure}<br>${ rdv.medecin.cabinet.nom}</a>
		</c:forEach>
	</div>
</c:if>
